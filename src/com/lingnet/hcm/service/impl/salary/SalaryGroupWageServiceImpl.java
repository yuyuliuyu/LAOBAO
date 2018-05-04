package com.lingnet.hcm.service.impl.salary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.salary.SalaryGroupWageDao;
import com.lingnet.hcm.entity.salary.GroupWageAndType;
import com.lingnet.hcm.entity.salary.SalaryAssignation;
import com.lingnet.hcm.entity.salary.SalaryFormulaDeploy;
import com.lingnet.hcm.entity.salary.SalaryGroupWage;
import com.lingnet.hcm.service.salary.GroupWageAndTypeService;
import com.lingnet.hcm.service.salary.SalaryFormulaDeployService;
import com.lingnet.hcm.service.salary.SalaryFormulaService;
import com.lingnet.hcm.service.salary.SalaryGroupWageService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

/**
 * 薪资组工资套
 * @ClassName: SalaryGroupWageService 
 * @Description: 薪资组工资套 
 * @author zhanghj
 * @date 2017年4月5日 下午4:30:53 
 *
 */
@Service("salaryGroupWageService")
public class SalaryGroupWageServiceImpl extends BaseServiceImpl<SalaryGroupWage, String>
              implements SalaryGroupWageService {

    @Resource(name="groupWageAndTypeService")
    private GroupWageAndTypeService groupWageAndTypeService;
    @Resource(name="salaryFormulaService")
    private SalaryFormulaService salaryFormulaService;
    @Resource(name="salaryFormulaDeployService")
    private SalaryFormulaDeployService salaryFormulaDeployService;
    @Resource(name="salaryGroupWageDao")
    private SalaryGroupWageDao salaryGroupWageDao;

    @Override
    public Map<String, Object> getSalaryGroupWageListData(String id, String searchData, Pager pager) {
        return salaryGroupWageDao.getSalaryGroupWageListData(id, searchData, pager);
    }

    @Override
    public List<Map<String, Object>> getGroupWageListData(String companyId, String groupId) {
        return salaryGroupWageDao.getGroupWageListData(companyId, groupId);
    }

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String saveOrUpdateItems(String formdata, String griddata, String gridData) throws Exception {
        SalaryGroupWage salaryWage = JsonUtil.toObject(formdata, SalaryGroupWage.class);
        if (salaryWage != null) {
            String id ="";
            if (StringUtils.isBlank(salaryWage.getId())) {
                salaryWage.setIsDelete(0);
                id = save(salaryWage);
                if (StringUtils.isBlank(id)) {
                    throw new Exception("保存失败");
                }
            } else {
                SalaryGroupWage newsal = get(SalaryGroupWage.class, Restrictions.eq("id", salaryWage.getId()), Restrictions.eq("isDelete", 0));
                if (newsal == null) {
                    return "该工资套不存在";
                }

                // 查找薪资组工资套是否存在工资分配中以及为完成发放
                String checkResult = checkWageStatus(newsal.getId(), newsal.getCompanyId());
                if (!checkResult.equals("success")) {
                    return newsal.getName() + "薪资组工资套正在发放中，不能删除";
                }
                salaryWage.setIsDelete(0);
                newsal.copyFrom(salaryWage);
                update(newsal);
                id = newsal.getId();
            }

            // 将引用的薪资项目删除
            List<GroupWageAndType> listYin = getList(GroupWageAndType.class,
                    Restrictions.eq("sign", 1),
                    Restrictions.eq("wageId", id),
                    Restrictions.eq("isDelete", 0));
            for (GroupWageAndType groupWageAndType : listYin) {
                groupWageAndType.setIsDelete(1);
                groupWageAndTypeService.update(groupWageAndType);
            }

            // 增加引用的薪资项目
            List<GroupWageAndType> newListType = JsonUtil.toList(griddata, GroupWageAndType.class);
            for (int i =0, l = newListType.size(); i < l; i++) {
                GroupWageAndType bean = newListType.get(i);
                bean.setWageId(id);
                bean.setIsDelete(0);
                bean.setSign(1);
                String result = groupWageAndTypeService.save(bean);
                if (StringUtils.isBlank(result))
                    throw new Exception("保存失败");
            }
            List<Map<String, String>> list = JsonUtil.getMapList(gridData);
            for (int i =0, l = list.size(); i < l; i++) {
                Map<String, String> m = list.get(i);
                GroupWageAndType bean = JsonUtil.toObject(JsonUtil.Encode(m), GroupWageAndType.class);
                bean.setWageId(id);
                if ("removed".equals(m.get("_state"))) {
                    GroupWageAndType itemAndType = get(GroupWageAndType.class,
                            Restrictions.eq("id", m.get("id")), Restrictions.eq("wageId", id), Restrictions.eq("isDelete", 0));
                    if (itemAndType != null) {
                        itemAndType.setIsDelete(1);
                        groupWageAndTypeService.update(itemAndType);
                    }
                } else if ("modified".equals(m.get("_state"))) {
                    GroupWageAndType itemAndType = get(GroupWageAndType.class,
                            Restrictions.eq("id", m.get("id")), Restrictions.eq("wageId", id), Restrictions.eq("isDelete", 0));
                    if (itemAndType != null) {
                        itemAndType.copyFrom(bean);
                        groupWageAndTypeService.update(itemAndType);
                    } else throw new Exception("发生异常");
                } else if ("added".equals(m.get("_state"))) {
                    bean.setIsDelete(0);
                    bean.setSign(2);
                    String result = groupWageAndTypeService.save(bean);
                    if (StringUtils.isBlank(result))
                        throw new Exception("保存失败");
                }
            }
        } else {
            return "信息不能为空";
        }

        return "success";
    }

    @Override
    public String checkWageStatus(String salaryWageId, String companyId) {
        // 查找薪资组工资套是否存在工资分配中以及为完成发放
        List<SalaryAssignation> assignations = getList(SalaryAssignation.class,
                Restrictions.eq("salaryWageId", salaryWageId),
                Restrictions.eq("companyId", companyId),
                Restrictions.ne("isFafang", 3),
                Restrictions.eq("isDelete", 0));
        if (assignations.size() > 0) {
            return "此工资套正在发放中，只能查看";
        } else return "success";
    }

    @Override
    public String remove(String ids) {
        String[] idArray = ids.split(",");
        for (int i = 0, l = idArray.length; i < l; i++) {
            SalaryGroupWage salaryWage = get(SalaryGroupWage.class, Restrictions.eq("id", idArray[i]), Restrictions.eq("isDelete", 0));
            if (salaryWage != null) {
                // 查找薪资组工资套是否存在工资分配中以及为完成发放
                String checkResult = checkWageStatus(salaryWage.getId(), salaryWage.getCompanyId());
                if (!checkResult.equals("success")) {
                    return salaryWage.getName() + "薪资组工资套正在发放中，不能删除";
                }
                salaryWage.setIsDelete(1);
                this.update(salaryWage);
            } else {
                return "第"+(i+1)+"条记录不存在";
            }
        }

        return "success";
    }

    @Override
    public Map<String, Object> getGroupWageTypeListData(String id, int sign) {
        return salaryGroupWageDao.getGroupWageTypeListData(id, sign);
    }

    @Override
    public List<Map<String, Object>> getValueIsZeroNoSendRecords(String id) {
        return salaryGroupWageDao.getValueIsZeroNoSendRecords(id);
    }

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String saveDeploy(String id) throws Exception {
        SalaryGroupWage salaryGroupWage = get(SalaryGroupWage.class, Restrictions.eq("id", id), Restrictions.eq("isDelete", 0));
        if (null == salaryGroupWage) return "该薪资组工资套不存在，已经被删除";

        // 删除
        deleteByCriteria(SalaryFormulaDeploy.class, Restrictions.eq("groupWageId", id), Restrictions.eq("isDelete", 0));
        String sql = salaryGroupWageDao.getDeployFormulaSql(id);
        List<?> list = findBySql(sql);
        List<SalaryFormulaDeploy> deploys = new ArrayList<SalaryFormulaDeploy>();
        for (int i = 0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            SalaryFormulaDeploy formulaDeploy = new SalaryFormulaDeploy();
            formulaDeploy.setIsDelete(0);
            formulaDeploy.setGroupWageId(id);
            formulaDeploy.setGwatId(obj[0].toString());
            int step = salaryFormulaService.getFourmulaCount(obj[1]==null?"":obj[1].toString());
            if (step == -1) throw new Exception(obj[2]+" 公式解析失败");
            formulaDeploy.setExecuteCount(step);
            deploys.add(formulaDeploy);
        }
        if (deploys.size() > 0) salaryFormulaDeployService.saveBatch(deploys);

        return "success";
    }

}
