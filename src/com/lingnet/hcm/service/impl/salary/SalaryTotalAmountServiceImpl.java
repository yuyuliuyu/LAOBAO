package com.lingnet.hcm.service.impl.salary;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.salary.SalaryTotalAmountDao;
import com.lingnet.hcm.entity.salary.Perioddata;
import com.lingnet.hcm.entity.salary.SalaryAgain;
import com.lingnet.hcm.entity.salary.SalaryAmountItem;
import com.lingnet.hcm.entity.salary.SalaryTotalAmount;
import com.lingnet.hcm.service.salary.SalaryAgainService;
import com.lingnet.hcm.service.salary.SalaryAmountItemService;
import com.lingnet.hcm.service.salary.SalaryTotalAmountService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

/**
 * 总量表
 * @ClassName: SalaryTotalAmountServiceImpl 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年5月17日 上午8:14:40 
 *
 */
@Service("salaryTotalAmountService")
public class SalaryTotalAmountServiceImpl extends BaseServiceImpl<SalaryTotalAmount, String>
              implements SalaryTotalAmountService {

    @Resource(name="salaryAgainService")
    private SalaryAgainService salaryAgainService;
    @Resource(name="salaryAmountItemService")
    private SalaryAmountItemService salaryAmountItemService;
    @Resource(name="salaryTotalAmountDao")
    private SalaryTotalAmountDao salaryTotalAmountDao;

    @Override
    public Map<String, Object> getAmountListData(String companyId, String searchData, Pager pager) {
        return salaryTotalAmountDao.getAmountListData(companyId, searchData, pager);
    }

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String saveOrUpdate(String period, String fpqx, String griddata) throws Exception {
        List<Map<String, String>> list = JsonUtil.getMapList(griddata);
        for (int i = 0, l = list.size(); i < l; i++) {
            Map<String, String> map = list.get(i);
            // 检查公司总量数据是否存在
            SalaryTotalAmount amount = get(SalaryTotalAmount.class,
                    Restrictions.eq("id", map.get("id")),
                    Restrictions.eq("fpqx", fpqx),
                    Restrictions.eq("isDelete", 0));
            if (amount == null) {
                throw new Exception("第"+(i+1)+"条数据不存在，已经被删除");
            } else if (amount.getIsDelete() == 1) {
                throw new Exception(map.get("deptName") + "的总量数据不存在，已经被删除");
            }
            amount.setLastMonthAmount(map.get("lastMonthAmount"));// 上月结余
            amount.setByjxldkh(map.get("byjxldkh"));// 本月绩效联动考核
            amount.setSpecialReward(map.get("specialReward"));// 特殊激励
            amount.setOtherReward(map.get("otherReward"));// 其他1
            amount.setOtherReward2(map.get("otherReward2"));// 其他2
            amount.setOtherReward3(map.get("otherReward3"));// 其他3
            amount.setRewardTotal(map.get("rewardTotal"));// 合计
            amount.setBykfpzl(map.get("bykfpzl"));// 本月可分配总量
            amount.setCurMonthAmount(map.get("curMonthAmount"));// 本月结余
            amount.setNote(map.get("note"));// 备注
            update(amount);

            // 其他薪资项目
            String[] otherIds = map.get("otherId").split(",");
            for (int j = 0, m = otherIds.length; j < m; j++) {
                SalaryAmountItem amountItem = get(SalaryAmountItem.class, Restrictions.eq("id", otherIds[j]), Restrictions.eq("isDelete", 0));
                if (null == amountItem) throw new Exception(map.get("deptName") + "的其他分配总量中第"+(j+1)+"条数据不存在，已经被删除");
                amountItem.setNeedAmount(map.get("other"+amountItem.getSalaryItemsId()));
                salaryAmountItemService.update(amountItem);
            }
        }

        return "success";
    }

    @Override
    public Map<String, Object> getAmountForPeriodListData(String period, String companyId, Pager pager) {
        return salaryTotalAmountDao.getAmountForPeriodListData(period, companyId, pager);
    }

    @Override
    public String updateUnCheck(String period, String companyId) {
        Perioddata perioddata = get(Perioddata.class, Restrictions.eq("id", period));
        if (perioddata == null) {
            return "该总量薪酬期间不存在";
        }
        if (!salaryAgainService.isCanReport(companyId, period)) {
            return "该总量相关的分配过程已经提交审批或者已经通过，不能操作";
        }
        List<SalaryAgain> agains = getList(SalaryAgain.class,
                Restrictions.eq("companyId", companyId),
                Restrictions.eq("fpDate", period),
                Restrictions.eq("isDelete", 0));
        // 更新状态
        for (int i = 0, l = agains.size(); i < l; i++) {
            SalaryAgain again = agains.get(i);
            again.setIsSp(2);
        }

        if (agains.size() > 0) {
            salaryAgainService.saveBatch(agains);
        }

        return "success";
    }

}
