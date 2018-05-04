package com.lingnet.hcm.service.impl.salary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.salary.SalaryGroupWageDao;
import com.lingnet.hcm.entity.salary.SalaryAssignation;
import com.lingnet.hcm.entity.salary.SalaryGroup;
import com.lingnet.hcm.entity.salary.SalaryPersonal;
import com.lingnet.hcm.service.salary.SalaryGroupService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

/**
 * 薪资组
 * @ClassName: SalaryGroupServiceImpl 
 * @Description: 薪资组
 * @author zhanghj
 * @date 2017年3月21日 下午2:46:24 
 *
 */
@Service("salaryGroupService")
public class SalaryGroupServiceImpl extends BaseServiceImpl<SalaryGroup, String>
              implements SalaryGroupService {

    @Resource(name="salaryGroupWageDao")
    private SalaryGroupWageDao salaryGroupWageDao;

    @Override
    public Map<String, Object> getSalaryGroupListData(String companyId, String searchData, Pager pager) {
        Conjunction and = Restrictions.conjunction();
        if (!StringUtils.isBlank(companyId)) {
            and.add(Restrictions.eq("companyId", companyId));
        }
        Map<String, String> map = JsonUtil.parseProperties(searchData);
        if (map != null) {
            // 薪资组名称
            if (!StringUtils.isBlank(map.get("name"))) {
                and.add(Restrictions.like("name", map.get("name"), MatchMode.ANYWHERE));
            }
            // 发薪频率
            if (!StringUtils.isBlank(map.get("salaryPeriod"))) {
                and.add(Restrictions.eq("salaryPeriod", map.get("salaryPeriod")));
            }
        }
        pager = findPagerByOrder(SalaryGroup.class, pager, Order.desc("createDate"), Restrictions.eq("isDelete", 0), and);
        HashMap<String, Object> m = new HashMap<String, Object>();
        m.put("data", pager.getResult());
        m.put("total", pager.getTotalCount());

        return m;
    }

    @Override
    public Map<String, Object> getGroupAllListData(String companyId, String searchData, Pager pager) {
        return salaryGroupWageDao.getSalaryGroupListData(companyId, searchData, pager);
    }

    @Override
    public List<SalaryGroup> getSalaryGroupEffectListData(String companyId, String searchData) {
        Conjunction and = Restrictions.conjunction();
        if (!StringUtils.isBlank(companyId)) {
            and.add(Restrictions.eq("companyId", companyId));
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            String date = cal.get(Calendar.YEAR) + "年" + (cal.get(Calendar.MONTH) +1) + "月" + cal.get(Calendar.DATE) + "日 23时59分59秒";
            cal.setTime(dateFormat.parse(date));
            and.add(Restrictions.le("effectDate", cal.getTime()));
        } catch (ParseException e) {
        }
        and.add(Restrictions.eq("type", 1));
        List<SalaryGroup> list = getList(SalaryGroup.class, Restrictions.eq("isDelete", 0), and);

        return list;
    }

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String saveOrUpdate(String formdata) throws Exception {
        SalaryGroup group = JsonUtil.toObject(formdata, SalaryGroup.class);
        if (group != null) {
            String id = group.getId();
            if (StringUtils.isBlank(id)) {
                SalaryGroup groupMore = get(SalaryGroup.class, Restrictions.eq("depId", group.getDepId()),
                        Restrictions.eq("isDelete", 0), Restrictions.eq("name", group.getName()));
                if (groupMore != null) {
                    return "存在相同的薪资组";
                }
                group.setIsDelete(0);
                id = save(group);
                if (StringUtils.isBlank(id))
                    throw new Exception("保存失败");
            } else {
                SalaryGroup sit = get(SalaryGroup.class, Restrictions.eq("id", id), Restrictions.eq("isDelete", 0));
                if (sit == null) {
                    throw new Exception("该条记录不存在");
                }

                // 查找薪资组是否存在工资分配中以及为完成发放
                String checkResult = checkGroupStatus(sit.getId(), sit.getDepId());
                if (!checkResult.equals("success")) {
                    return checkResult;
                }
                group.setIsDelete(0);
                sit.copyFrom(group);
                update(sit);
            }
        } else return "信息不能为空";

        return "success";
    }

    @Override
    public String checkGroupStatus(String salaryGroupId, String companyId) {
        // 查找薪资组是否存在工资分配中以及为完成发放
        List<SalaryAssignation> assignations = getList(SalaryAssignation.class,
                Restrictions.eq("salaryGroupId", salaryGroupId),
                Restrictions.eq("companyId", companyId),
                Restrictions.ne("isFafang", 3),
                Restrictions.eq("isDelete", 0));
        if (assignations.size() > 0) {
            return "此薪资组正在发放中，只能查看";
        } else return "success";
    }

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String remove(String ids) throws Exception {
        String[] idArray = ids.split(",");
        for (int i = 0, l = idArray.length; i < l; i++) {
            SalaryGroup group = get(SalaryGroup.class, Restrictions.eq("id", idArray[i]), Restrictions.eq("isDelete", 0));
            if (group != null) {
                // 查找此薪资组下是否已经分配了人
                List<SalaryPersonal> list = getList(SalaryPersonal.class,
                        Restrictions.eq("salaryGroupId", idArray[i]),
                        Restrictions.eq("isDelete", 0));
                if (list.size() > 0) throw new Exception(group.getName() + "薪资组已经分配了员工，不能删除");

                // 查找薪资组是否存在工资分配中以及为完成发放
                String checkResult = checkGroupStatus(group.getId(), group.getCompanyId());
                if (!checkResult.equals("success")) {
                    throw new Exception(group.getName() + "薪资组正在发放中，不能删除");
                }
                group.setIsDelete(1);
                this.update(group);
            }
        }

        return "success";
    }
}
