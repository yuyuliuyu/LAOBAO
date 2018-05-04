package com.lingnet.hcm.service.impl.salary;

import java.io.File;
import java.sql.Clob;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.salary.SalaryAssignationDao;
import com.lingnet.hcm.entity.DeptPosition;
import com.lingnet.hcm.entity.WorkFlowChild;
import com.lingnet.hcm.entity.WorkFlowPrent;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.entity.salary.AssignationRecord;
import com.lingnet.hcm.entity.salary.AssignationStaff;
import com.lingnet.hcm.entity.salary.AssignationStaffSalary;
import com.lingnet.hcm.entity.salary.CheckMonthSalary;
import com.lingnet.hcm.entity.salary.CheckProcess;
import com.lingnet.hcm.entity.salary.CheckSalaryColor;
import com.lingnet.hcm.entity.salary.SalaryAgain;
import com.lingnet.hcm.entity.salary.SalaryAgainItems;
import com.lingnet.hcm.entity.salary.SalaryAmountItem;
import com.lingnet.hcm.entity.salary.SalaryAssignation;
import com.lingnet.hcm.entity.salary.SalaryItems;
import com.lingnet.hcm.entity.salary.SalaryRecord;
import com.lingnet.hcm.entity.salary.SalaryTotalAmount;
import com.lingnet.hcm.entity.salary.SalaryWageMain;
import com.lingnet.hcm.entity.salary.SalaryWageSecond;
import com.lingnet.hcm.service.salary.AssignationRecordService;
import com.lingnet.hcm.service.salary.AssignationStaffSalaryService;
import com.lingnet.hcm.service.salary.AssignationStaffService;
import com.lingnet.hcm.service.salary.CheckMonthSalaryService;
import com.lingnet.hcm.service.salary.CheckProcessService;
import com.lingnet.hcm.service.salary.SalaryAgainItemsService;
import com.lingnet.hcm.service.salary.SalaryAgainService;
import com.lingnet.hcm.service.salary.SalaryAmountItemService;
import com.lingnet.hcm.service.salary.SalaryAssignationService;
import com.lingnet.hcm.service.salary.SalaryFormulaService;
import com.lingnet.hcm.service.salary.SalaryTotalAmountService;
import com.lingnet.hcm.service.salary.SalaryWageMainService;
import com.lingnet.hcm.service.salary.SalaryWageSecondService;
import com.lingnet.qxgl.entity.QxDepartment;
import com.lingnet.qxgl.entity.QxUser;
import com.lingnet.util.ExcelUtil;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
import com.lingnet.util.Pager;
import com.lingnet.util.ToolUtil;

/**
 * 薪酬分配过程
 * @ClassName: SalaryAssignationServiceImpl 
 * @Description: 薪酬分配过程 
 * @author zhanghj
 * @date 2017年5月9日 下午5:01:51 
 *
 */
@Service("salaryAssignationService")
public class SalaryAssignationServiceImpl extends BaseServiceImpl<SalaryAssignation, String>
              implements SalaryAssignationService {

    @Resource(name="assignationStaffService")
    private AssignationStaffService assignationStaffService;
    @Resource(name="assignationStaffSalaryService")
    private AssignationStaffSalaryService assignationStaffSalaryService;
    @Resource(name="salaryFormulaService")
    private SalaryFormulaService salaryFormulaService;
    @Resource(name="salaryTotalAmountService")
    private SalaryTotalAmountService salaryTotalAmountService;
    @Resource(name="salaryAmountItemService")
    private SalaryAmountItemService salaryAmountItemService;
    @Resource(name="assignationRecordService")
    private AssignationRecordService assignationRecordService;
    @Resource(name="salaryWageMainService")
    private SalaryWageMainService salaryWageMainService;
    @Resource(name="salaryWageSecondService")
    private SalaryWageSecondService salaryWageSecondService;
    @Resource(name="checkMonthSalaryService")
    private CheckMonthSalaryService checkMonthSalaryService;
    @Resource(name="salaryAgainItemsService")
    private SalaryAgainItemsService salaryAgainItemsService;
    @Resource(name="checkProcessService")
    private CheckProcessService checkProcessService;
    @Resource(name="salaryAgainService")
    private SalaryAgainService salaryAgainService;
    @Resource(name="salaryAssignationDao")
    private SalaryAssignationDao salaryAssignationDao;

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String saveOrUpdate(String formdata) throws Exception {
        SalaryAssignation assignation = JsonUtil.toObject(formdata, SalaryAssignation.class);
        Boolean add = false;

        // 分配过程ID
        String id = "";
        if (StringUtils.isBlank(assignation.getId())) {
            assignation.setIsDelete(0);

            // 判断是否存在已经发放的日期
            SalaryAssignation check  = get(SalaryAssignation.class,
                    Restrictions.eq("companyId", assignation.getCompanyId()),
                    Restrictions.eq("salaryGroupId", assignation.getSalaryGroupId()),
                    Restrictions.eq("salaryPeriod", assignation.getSalaryPeriod()),
                    Restrictions.eq("isDelete", 0));
            if (check != null) return "此薪资组对应的薪酬期间已经存在";
            assignation.setIsSp(0);
            assignation.setIsFafang(0);
            assignation.setAllowEdit(0);// 不允许保险修改
            id = this.save(assignation);
            if (StringUtils.isBlank(id))
                return "保存失败";
            add = true;
        } else {
            SalaryAssignation assignationNew  = get(SalaryAssignation.class, Restrictions.eq("id", assignation.getId()), Restrictions.eq("isDelete", 0));
            if (assignationNew == null) {
                return "该分配过程不存在，已经被删除";
            }
            // 已经提交不能更改
            if (assignationNew.getIsSp() == 1 || assignationNew.getIsSp() == 3) {
                return "该分配过程已提交";
            }

            // 判断是否存在已经发放的日期
            SalaryAssignation check  = get(SalaryAssignation.class,
                    Restrictions.ne("id", assignation.getId()),
                    Restrictions.eq("salaryGroupId", assignation.getSalaryGroupId()),
                    Restrictions.eq("salaryPeriod", assignation.getSalaryPeriod()),
                    Restrictions.eq("isDelete", 0));
            if (check != null) return "此薪资组、薪酬期间对应的分配过程已经存在";
            assignationNew.copyFrom(assignation);
            this.update(assignationNew);
            id = assignationNew.getId();
        }

        // 发放数据
        if (add) {
            String message = saveNewAssignData(id, assignation.getSalaryGroupId(), assignation.getSalaryWageId());
            if (!message.equals("success")) {
                throw new Exception(message);
            }
        }

        return "success：" + id;
    }

    @Override
    public Map<String, Object> getSalaryAssignationListData(String companyId, String searchData, Pager pager) {
        return salaryAssignationDao.getSalaryAssignationListData(companyId, searchData, pager);
    }

    @Override
    public Map<String, Object> getCheckListData(String id, Pager pager) {
        return salaryAssignationDao.getCheckListData(id, pager);
    }

    @Override
    public Map<String, Object> getSalaryAssignationForAuthListData(String userName, String searchData, Pager pager) {
        return salaryAssignationDao.getSalaryAssignationForAuthListData(userName, searchData, pager);
    }

    @Override
    public Map<String, Object> getSalaryAssignationForAuthListData2(String userName, String searchData, Pager pager) {
        return salaryAssignationDao.getSalaryAssignationForAuthListData2(userName, searchData, pager);
    }

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String remove(String ids) throws Exception {
        String[] idArray = ids.split(",");
        for (int i = 0, l = idArray.length; i < l; i++) {
            SalaryAssignation assignationNew  = get(SalaryAssignation.class, Restrictions.eq("id", idArray[i]), Restrictions.eq("isDelete", 0));
            if (assignationNew != null) {
                // 是否已经通过
                if (assignationNew.getIsSp() == 1 || assignationNew.getIsSp() == 3) {
                    throw new Exception(assignationNew.getName()+"核算过程已经提交，不能删除");
                }

                // 二次分配和总量的状态修改
                List<SalaryAgain> salaryAgain = getList(SalaryAgain.class,
                        Restrictions.eq("salaryAssignationId", assignationNew.getId()),
                        Restrictions.eq("isDelete", 0));
//                if (salaryAgain.size() == 0) throw new Exception(assignationNew.getName()+"对应的二次分配数据不存在");
                for (SalaryAgain sa : salaryAgain) {
                    sa.setIsDelete(1);
                    salaryAgainService.update(sa);
                }
                List<SalaryTotalAmount> salaryTotalAmount = getList(SalaryTotalAmount.class,
                        Restrictions.eq("salaryAssignationId", assignationNew.getId()),
                        Restrictions.eq("isDelete", 0));
//                if (salaryTotalAmount.size() == 0) throw new Exception(assignationNew.getName()+"对应的总量数据不存在");
                for (SalaryTotalAmount amount : salaryTotalAmount) {
                    amount.setIsDelete(1);
                    salaryTotalAmountService.update(amount);
                }

                assignationNew.setIsDelete(1);
                update(assignationNew);
            } else {
                throw new Exception("选择的第"+(i+1)+"个分配过程不存在，已经被删除");
            }
        }

        return "success";
    }

    @Override
    public List<Map<String, Object>> getNeedAssignationData(String groupId, String wageId) {
        return salaryAssignationDao.getNeedAsignData(groupId, wageId);
    }

    @Override
    public Map<String, Object> getAssignationForStaffData(String id, String ids, String searchData, Pager pager) {
        return salaryAssignationDao.getAssignationForStaffData(id, ids, searchData, pager);
    }

    @Override
    public Map<String, Object> getCompareAssignationForStaffData(String id, String ids, String searchData, String conditionData) {
        return salaryAssignationDao.getCompareAssignationForStaffData(id, ids, searchData, conditionData);
    }

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String updateAssignationForStaffData(String id) throws Exception {
        SalaryAssignation staff = get(SalaryAssignation.class,
                Restrictions.eq("id", id),
                Restrictions.eq("isDelete", 0));
        // 判断分配过程是否存在
        if (staff == null) {
            return "该薪酬核算过程不存在";
        }
        staff.setAllowEdit(0);// 不允许保险修改
        update(staff);

        // 首先删除相关的核算数据主表数据
//        StringBuilder sql = new StringBuilder();
//        sql.append("  SELECT                                   ");
//        sql.append("  XAS.ID, XSA.ID ITEMID                    ");
//        sql.append("  FROM XC_ASSIGNATION_STAFF XAS            ");
//        sql.append("  LEFT JOIN XC_SALARY_AGAIN SAGAIN         ");
//        sql.append("  ON XAS.COMPANY_ID = SAGAIN.COMPANY_ID    ");
//        sql.append("  AND XAS.DEPT_ID = SAGAIN.DEP_ID          ");
//        sql.append("  LEFT JOIN XC_SALARY_AGAIN_ITEMS XSA      ");
//        sql.append("  ON SAGAIN.ID = XSA.SALARY_AGAIN_ID       ");
//        sql.append("  AND XAS.STAFF_ID = XSA.STAFF_ID          ");
//        sql.append("  AND XSA.SALARY_GROUP_ID = '"+staff.getSalaryGroupId()+"'  ");
//        sql.append("  AND XSA.IS_DELETE = 0                    ");
//        sql.append("  WHERE XAS.SALARY_ASSIGNATION_ID = '"+staff.getId()+"'     ");
//        sql.append("  AND XAS.IS_DELETE = 0                    ");
//        sql.append("  AND SAGAIN.IS_DELETE = 0                    ");
//        List<?> list = findBySql(sql.toString());
//        Object tmp = "";
//        for (int i=0, l = list.size(); i < l; i++) {
//            Object[] obj = (Object[]) list.get(i);
//            if (!tmp.equals(obj[0]))
//                deleteByCriteria(AssignationStaffSalary.class,
//                        Restrictions.eq("assignationStaffId", obj[0]),
//                        Restrictions.eq("isDelete", 0));
//            if (obj[1] != null) {
//                deleteByCriteria(SalaryAgainItems.class, Restrictions.eq("id", obj[1]), Restrictions.eq("isDelete", 0));
//            }
//            tmp = obj[0];
//        }
//        deleteByCriteria(AssignationStaff.class, Restrictions.eq("salaryAssignationId", staff.getId()), Restrictions.eq("isDelete", 0));
//
//        // 删除二次分配主表该公司数据
//        deleteByCriteria(SalaryAgain.class, Restrictions.eq("salaryAssignationId", staff.getId()), Restrictions.eq("isDelete", 0),
//                Restrictions.eq("companyId", staff.getCompanyId()), Restrictions.eq("fpDate", staff.getSalaryPeriod()));
//
//        // 删除总量表该公司数据
//        deleteByCriteria(SalaryTotalAmount.class, Restrictions.eq("salaryAssignationId", staff.getId()), Restrictions.eq("isDelete", 0),
//                Restrictions.eq("companyId", staff.getCompanyId()), Restrictions.eq("fpDate", staff.getSalaryPeriod()));

        // 调用存储过程
        String str = staff.getSalaryGroupId() + "," + id + "," + staff.getCompanyId() + "," + staff.getSalaryPeriod();
        String success = salaryAssignationDao.doFunction("PD_SALARYCHECKDATA", str);
        if (!success.equals("success")) throw new Exception("操作失败");

        // 重新分配
        String message = saveNewAssignData(id, staff.getSalaryGroupId(), staff.getSalaryWageId());
        if (!message.equals("success")) {
            throw new Exception(message);
        }

        return "success";
    }

    /**
     * @Title: 分配核算过程数据
     * @param assignId
     * @param groupId
     * @param wageId
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年5月10日 V 1.0
     */
    @Deprecated
    private String saveAssignData(String assignId, String groupId, String wageId) throws Exception {
        SalaryAssignation assignation = get(SalaryAssignation.class, Restrictions.eq("id", assignId), Restrictions.eq("isDelete", 0));
        if (assignation == null) {
            return "该薪酬核算过程不存在";
        }
        String periodId = assignation.getSalaryPeriod();
        List<Map<String, Object>> needStaffs = getNeedAssignationData(groupId, wageId);
        if (needStaffs.size() == 0) return "不存在需要分配的员工";
        List<AssignationStaffSalary> assignationStaffSalaries = new ArrayList<AssignationStaffSalary>();
        DecimalFormat df = new DecimalFormat();
        Set<Object> dept = new HashSet<Object>();
        Map<String, Integer> depts = new HashMap<String, Integer>();
        Map<String, String> concurrentMap = new HashMap<String, String>();
        List<SalaryAgainItems> salaryAgainItems = new ArrayList<SalaryAgainItems>();
        Map<String, Object> jc = new HashMap<String, Object>();
        for (int i = 0, n = needStaffs.size(); i < n; i++) {
            Map<String, Object> staffInfo = needStaffs.get(i);
            AssignationStaff staff = new AssignationStaff();
            staff.setSalaryAssignationId(assignId);
            staff.setStaffId(staffInfo.get("id").toString());
            staff.setJobNumber(staffInfo.get("jobNumber").toString());
            staff.setStaffName(staffInfo.get("name").toString());
            staff.setCompanyId(staffInfo.get("companyId").toString());
            staff.setCompanyName(staffInfo.get("companyName").toString());
            staff.setDeptId(staffInfo.get("departId").toString());
            staff.setDeptName(staffInfo.get("deptName").toString());
            staff.setGwId(staffInfo.get("jtgwId").toString());
            staff.setGwName(staffInfo.get("jtgwName").toString());
            staff.setBggwId(staffInfo.get("bzgwId").toString());
            staff.setBggwName(staffInfo.get("bzgwName").toString());
            staff.setXcgwId(staffInfo.get("xcgwId").toString());
            staff.setXcgwName(staffInfo.get("xcgwName").toString());
            staff.setIsAllMonth(1);
            staff.setIsDelete(0);
            String assignMainId= assignationStaffService.save(staff);
            if (StringUtils.isBlank(assignMainId))
                return "发生异常，薪酬核算数据分配失败";

            jc.put("B_staffId", staff.getStaffId());
            jc.put("B_companyId", staff.getCompanyId());
            jc.put("T_salaryPeriod", periodId);
            jc.put("B_salaryGroupId", assignation.getSalaryGroupId());
            jc.put("T_assignationId", assignation.getId());

            if (staffInfo.get("fpqx") == null) {
                return "职工号" +staff.getJobNumber() +"员工"+ staff.getStaffName() + "分配权限不能为空";
            }
            // 查找是否存在相同的公司、部门、发薪期间、分配权限
            String comb = staff.getCompanyId() + "-" + staff.getDeptId() + "-" + periodId + "-" + staffInfo.get("fpqx");
            String againId= concurrentMap.get(comb);
            if (StringUtils.isBlank(againId)) {
                SalaryAgain again = get(SalaryAgain.class, Restrictions.eq("isDelete", 0), Restrictions.eq("salaryAssignationId", assignation.getId()),
                        Restrictions.eq("companyId", staff.getCompanyId()), Restrictions.eq("depId", staff.getDeptId()),
                        Restrictions.eq("fpDate", periodId), Restrictions.eq("fpqx", Integer.valueOf(staffInfo.get("fpqx").toString())));
                if (again !=null) againId = again.getId();
                else {
                    again = new SalaryAgain();
                    again.setSalaryAssignationId(assignation.getId());
                    again.setCompanyId(staff.getCompanyId());
                    again.setDepId(staff.getDeptId());
                    again.setFpDate(periodId);
                    again.setFpqx(Integer.valueOf(staffInfo.get("fpqx").toString()));
                    again.setRewardTotal("0");
                    again.setIsDelete(0);
                    again.setReportStatus(0);
                    again.setIsSp(0);
                    againId = salaryAgainService.save(again);
                    if (StringUtils.isBlank(againId)) return "发生异常，二次分配失败";
                }
                concurrentMap.put(comb, againId);
            }

            // 拆分薪资项目
            Object itemsObj = staffInfo.get("itemsId");
            if (itemsObj != null) {
                String[] items = itemsObj.toString().split(",;");
                String[] itemsName = staffInfo.get("itemsName").toString().split(",;");
                String[] formulas = staffInfo.get("formula").toString().split(",;");
                String[] xss = staffInfo.get("xs").toString().split(",;");
                String[] sxs = staffInfo.get("sx").toString().split(",;");
                String[] salaryValue = staffInfo.get("salaryValue").toString().split(",;");
                String[] isDisplay = staffInfo.get("isDisplay").toString().split(",;");// 二次分配
                String[] isNumber = staffInfo.get("isNumber").toString().split(",;");
                String[] addOrLess = staffInfo.get("addOrLess").toString().split(",;");
                String[] defaultDisplay = staffInfo.get("defaultDisplay").toString().split(",;");
                for (int j = 0, m = items.length; j < m; j++) {
                    AssignationStaffSalary staffSalary = new AssignationStaffSalary();
                    staffSalary.setAssignationStaffId(assignMainId);
                    staffSalary.setIbfId(items[j]);
                    staffSalary.setIbfName(itemsName[j]);
                    staffSalary.setIsDisplay(Integer.valueOf(isDisplay[j]));
                    staffSalary.setIsNumber(Integer.valueOf(isNumber[j]));
                    staffSalary.setAddOrLess(Integer.valueOf(addOrLess[j]));
                    staffSalary.setNumberAccuracy(xss[j]);
                    staffSalary.setIsDefaultDisplay(Integer.valueOf(defaultDisplay[j]));

                    // 没有公式则按照档案中的标准数据
                    String v = "";
                    try {
                        v = numberAccuracy(df, xss[j], salaryFormulaService.formula(formulas[j], jc));// 根据公式计算项目值
//                        v = numberAccuracy(df, xss[j], salaryValue[j]);// 获取设置的项目值
                    } catch (Exception e) {
                        return itemsName[j] + " 公式解析发生异常";
                    }
                    staffSalary.setAssignationCharge(v);
                    staffSalary.setSx(Integer.valueOf(sxs[j]));
                    staffSalary.setIsDelete(0);
                    assignationStaffSalaries.add(staffSalary);

                    if (staffSalary.getIsDisplay() == 1) {
                        // 二次分配薪资表
                        SalaryAgainItems againItems = new SalaryAgainItems();
                        againItems.setStaffId(staff.getStaffId());
                        againItems.setSalaryAgainId(againId);
                        againItems.setSalaryItemsId(items[j]);
                        againItems.setSalaryItemsName(itemsName[j]);
                        againItems.setNeedAmount("0");
                        againItems.setReportAmount("0");
                        againItems.setIsDelete(0);
                        againItems.setSx(Integer.valueOf(sxs[j]));
                        againItems.setNumberAccuracy(xss[j]);
                        againItems.setStaffName(staff.getStaffName());
                        againItems.setJobNumber(staff.getJobNumber());
                        againItems.setSalaryGroupId(assignation.getSalaryGroupId());
                        againItems.setSalaryGroupName(assignation.getSalaryGroupName());
                        againItems.setFpqx(Integer.valueOf(staffInfo.get("fpqx").toString()));
                        againItems.setJtgwId(staff.getGwId());
                        againItems.setJtgw(staff.getGwName());
                        againItems.setBggwId(staff.getBggwId());
                        againItems.setBzgw(staff.getBggwName());
                        againItems.setXcgwId(staff.getXcgwId());
                        againItems.setXcgw(staff.getXcgwName());
                        againItems.setGzxs(Integer.valueOf(staffInfo.get("gzxs").toString()) == 0? "计时":"计件");
                        againItems.setSpecialMark(staffInfo.get("specialMark").toString());
                        againItems.setStaticValue(salaryValue[j]);
                        againItems.setIsDefaultDisplay(staffSalary.getIsDefaultDisplay());
                        salaryAgainItems.add(againItems);
                    }
                }
            }
            Object departId = staffInfo.get("departId");
            if (departId != null && !departId.equals(" ")) {
                dept.add(staffInfo.get("departId"));
                int count = 0;
                if (null != depts.get(departId.toString())) {
                    count = depts.get(departId.toString()) + 1;
                } else count = 1;
                depts.put(departId.toString(), count);
            }
        }

        // 保存薪酬核算数据中间表
        if (assignationStaffSalaries.size() > 0) {
            assignationStaffSalaryService.saveBatch(assignationStaffSalaries);
        }
        if (salaryAgainItems.size() > 0) salaryAgainItemsService.saveBatch(salaryAgainItems);
//
//        // 公司、基层
//        String[] fpqx = {"1", "2"};
//        for (String i : fpqx) {
//            for (Object deptId : dept) {
//                // 查找分配权限的人数
//                Map<String, Object> fpqxPeopleCount = salaryAssignationDao.getFpqxPeopleCount(assignId, i, deptId.toString());
//                SalaryTotalAmount totalAmount = new SalaryTotalAmount();
//                totalAmount.setCompanyId(assignation.getCompanyId());
//                int peopleCount = fpqxPeopleCount.get(i) == null ? 0 : Integer.valueOf(fpqxPeopleCount.get(i).toString());
//                totalAmount.setPeopleCount(peopleCount);
//                totalAmount.setDepId(deptId.toString());
//                totalAmount.setByjxldkh("0");
//                totalAmount.setSpecialReward("0");
//                totalAmount.setOtherReward("0");
//                totalAmount.setRewardTotal("0");
//                totalAmount.setBykfpzl("0");
//                totalAmount.setFpDate(assignation.getSalaryPeriod());
//                totalAmount.setCurMonthAmount("0");
//                totalAmount.setIsDelete(0);
//                totalAmount.setFpqx(i);
//                String salaryPeriod = salaryAssignationDao.getLastMonthSalaryPeriod(assignation.getSalaryPeriod());
//                Map<String, String> map = JsonUtil.parseProperties(salaryPeriod);
//                if (map.get("success").equals("1")) {
//                    // 查找上月结余
//                    SalaryTotalAmount latMonth = get(SalaryTotalAmount.class,
//                            Restrictions.eq("companyId", assignation.getCompanyId()),
////                            Restrictions.eq("depId", assignation.getCompanyId()),
//                            Restrictions.eq("fpDate", map.get("info")),
//                            Restrictions.eq("fpqx", i),
//                            Restrictions.eq("isDelete", 0));
//                    if (latMonth != null) {
//                        totalAmount.setLastMonthAmount(latMonth.getCurMonthAmount());
//                    } else {
//                        totalAmount.setLastMonthAmount("0");
//                    }
//                    String result = salaryTotalAmountService.save(totalAmount);
//                    if (StringUtils.isBlank(result))
//                        return "发生异常，薪酬核算数据分配失败";
//                } else {
//                    return map.get("info");
//                }
//            }
//        }

        return "success";
    }

    /**
     * @Title: 新分配核算过程数据
     * @param assignId
     * @param groupId
     * @param wageId
     * @return
     * @throws Exception 
     * String 
     * @author zhanghj
     * @since 2017年9月12日 V 1.0
     */
    private String saveNewAssignData(String assignId, String groupId, String wageId) throws Exception {
        SalaryAssignation assignation = get(SalaryAssignation.class, Restrictions.eq("id", assignId), Restrictions.eq("isDelete", 0));
        if (assignation == null) {
            return "该薪酬核算过程不存在";
        }
        String periodId = assignation.getSalaryPeriod();
        List<Map<String, Object>> needStaffs = getNeedAssignationData(groupId, wageId);
        if (needStaffs.size() == 0) return "不存在需要分配的员工";
        List<AssignationStaffSalary> assignationStaffSalaries = new ArrayList<AssignationStaffSalary>();
//        DecimalFormat df = new DecimalFormat();
        Map<String, String> concurrentMap = new HashMap<String, String>();
        List<SalaryAgainItems> salaryAgainItems = new ArrayList<SalaryAgainItems>();
//        Map<String, Object> jc = new HashMap<String, Object>();

        Object jbiId = "";
        String assignMainId = "";
        String againId = "";
        for (int i = 0, n = needStaffs.size(); i < n; i++) {
            Map<String, Object> staffInfo = needStaffs.get(i);
            if (!staffInfo.get("id").equals(jbiId)) {
                AssignationStaff staff = new AssignationStaff();
                staff.setSalaryAssignationId(assignId);
                staff.setStaffId(staffInfo.get("id").toString());
                staff.setJobNumber(staffInfo.get("jobNumber").toString());
                staff.setStaffName(staffInfo.get("name").toString());
                staff.setCompanyId(staffInfo.get("companyId").toString());
                staff.setCompanyName(staffInfo.get("companyName").toString());
                staff.setDeptId(staffInfo.get("departId").toString());
                staff.setDeptName(staffInfo.get("deptName").toString());
                staff.setGwId(staffInfo.get("jtgwId").toString());
                staff.setGwName(staffInfo.get("jtgwName").toString());
                staff.setBggwId(staffInfo.get("bzgwId").toString());
                staff.setBggwName(staffInfo.get("bzgwName").toString());
                staff.setXcgwId(staffInfo.get("xcgwId").toString());
                staff.setXcgwName(staffInfo.get("xcgwName").toString());
                staff.setIsAllMonth(1);
                staff.setIsDelete(0);
                assignMainId= assignationStaffService.save(staff);
                if (StringUtils.isBlank(assignMainId))
                    return "发生异常，薪酬核算数据分配失败";

//                jc.put("B_staffId", staff.getStaffId());
//                jc.put("B_companyId", staff.getCompanyId());
//                jc.put("T_salaryPeriod", periodId);
//                jc.put("B_salaryGroupId", assignation.getSalaryGroupId());
//                jc.put("T_assignationId", assignation.getId());

                if (staffInfo.get("fpqx") == null) {
                    return "职工号" +staff.getJobNumber() +"员工"+ staff.getStaffName() + "分配权限不能为空";
                }
                // 查找是否存在相同的公司、部门、发薪期间、分配权限
                String comb = staff.getCompanyId() + "-" + staff.getDeptId() + "-" + periodId + "-" + staffInfo.get("fpqx");
                againId= concurrentMap.get(comb);
                if (StringUtils.isBlank(againId)) {
                    SalaryAgain again = new SalaryAgain();
                    again.setSalaryAssignationId(assignation.getId());
                    again.setCompanyId(staff.getCompanyId());
                    again.setDepId(staff.getDeptId());
                    again.setFpDate(periodId);
                    again.setFpqx(Integer.valueOf(staffInfo.get("fpqx").toString()));
                    again.setRewardTotal("0");
                    again.setIsDelete(0);
                    again.setReportStatus(0);
                    again.setIsSp(0);
                    againId = salaryAgainService.save(again);
                    if (StringUtils.isBlank(againId)) return "发生异常，二次分配失败";
                    concurrentMap.put(comb, againId);
                }
                jbiId = staffInfo.get("id");
            }

            AssignationStaffSalary staffSalary = new AssignationStaffSalary();
            staffSalary.setAssignationStaffId(assignMainId);
            staffSalary.setIbfId(staffInfo.get("itemsId").toString());
            staffSalary.setIbfName(staffInfo.get("itemsName").toString());
            staffSalary.setIsDisplay(Integer.valueOf(staffInfo.get("isDisplay").toString()));
            staffSalary.setIsNumber(Integer.valueOf(staffInfo.get("isNumber").toString()));
            staffSalary.setAddOrLess(Integer.valueOf(staffInfo.get("addOrLess").toString()));
            staffSalary.setNumberAccuracy(staffInfo.get("xs").toString());
            staffSalary.setIsDefaultDisplay(Integer.valueOf(staffInfo.get("defaultDisplay").toString()));

            // 没有公式则按照档案中的标准数据
            String v = "0";
//            if ("1".equals(gsjs)) {
//                try {
//                    v = numberAccuracy(df, staffSalary.getNumberAccuracy(), salaryFormulaService.formula(staffInfo.get("formula").toString(), jc));// 根据公式计算项目值
////                v = numberAccuracy(df, xss[j], salaryValue[j]);// 获取设置的项目值
//                } catch (Exception e) {
//                    return staffSalary.getIbfName() + " 公式解析发生异常";
//                }
//            }
            staffSalary.setAssignationCharge(v);
            staffSalary.setSx(Integer.valueOf(staffInfo.get("sx").toString()));
            staffSalary.setIsDelete(0);
            assignationStaffSalaries.add(staffSalary);
            if (assignationStaffSalaries.size() % 1000 == 0) {
                assignationStaffSalaryService.saveBatch(assignationStaffSalaries);
                assignationStaffSalaryService.flush();
                assignationStaffSalaryService.clear();
                assignationStaffSalaries.clear();
            }

            if (staffSalary.getIsDisplay() == 1) {
                // 二次分配薪资表
                SalaryAgainItems againItems = new SalaryAgainItems();
                againItems.setStaffId(jbiId.toString());
                againItems.setSalaryAgainId(againId);
                againItems.setSalaryItemsId(staffSalary.getIbfId());
                againItems.setSalaryItemsName(staffSalary.getIbfName());
                againItems.setNeedAmount("0");
                againItems.setReportAmount("0");
                againItems.setIsDelete(0);
                againItems.setSx(staffSalary.getSx());
                againItems.setNumberAccuracy(staffSalary.getNumberAccuracy());
                againItems.setStaffName(staffInfo.get("name").toString());
                againItems.setJobNumber(staffInfo.get("jobNumber").toString());
                againItems.setSalaryGroupId(assignation.getSalaryGroupId());
                againItems.setSalaryGroupName(assignation.getSalaryGroupName());
                againItems.setFpqx(Integer.valueOf(staffInfo.get("fpqx").toString()));
                againItems.setJtgwId(staffInfo.get("jtgwId").toString());
                againItems.setJtgw(staffInfo.get("jtgwName").toString());
                againItems.setBggwId(staffInfo.get("bzgwId").toString());
                againItems.setBzgw(staffInfo.get("bzgwName").toString());
                againItems.setXcgwId(staffInfo.get("xcgwId").toString());
                againItems.setXcgw(staffInfo.get("xcgwName").toString());
                againItems.setGzxs(Integer.valueOf(staffInfo.get("gzxs").toString()) == 0? "计时":"计件");
                againItems.setSpecialMark(staffInfo.get("specialMark").toString());
                againItems.setStaticValue(staffInfo.get("salaryValue").toString());
                againItems.setIsDefaultDisplay(staffSalary.getIsDefaultDisplay());
                salaryAgainItems.add(againItems);
                if (salaryAgainItems.size() % 1000 == 0) {
                    salaryAgainItemsService.saveBatch(salaryAgainItems);
                    salaryAgainItemsService.flush();
                    salaryAgainItemsService.clear();
                    salaryAgainItems.clear();
                }
            }
        }

        // 保存薪酬核算数据中间表
        if (assignationStaffSalaries.size() > 0) {
            assignationStaffSalaryService.saveBatch(assignationStaffSalaries);
        }
        if (salaryAgainItems.size() > 0) salaryAgainItemsService.saveBatch(salaryAgainItems);

        return "success";
    }

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String updateTotalAmount(String id) throws Exception {
        SalaryAssignation assignation = get(SalaryAssignation.class, Restrictions.eq("id", id), Restrictions.eq("isDelete", 0));
        if (assignation == null) {
            return "该薪酬核算过程不存在";
        }

        // 公司、基层
        String[] fpqx = {"1", "2"};
        for (String i : fpqx) {
            // 查找该公司下薪酬期间不同部门的分配权限的人数
            List<Map<String, Object>> fpqxPeopleCount = salaryAssignationDao.getFpqxPeopleCount(assignation.getId(), assignation.getCompanyId(), i, assignation.getSalaryPeriod());
            for (Map<String, Object> maps : fpqxPeopleCount) {
                SalaryTotalAmount amount = get(SalaryTotalAmount.class,
                        Restrictions.eq("salaryAssignationId", assignation.getId()),
                        Restrictions.eq("companyId", assignation.getCompanyId()),
                        Restrictions.eq("depId", maps.get("deptId")),
                        Restrictions.eq("fpDate", assignation.getSalaryPeriod()),
                        Restrictions.eq("fpqx", i),
                        Restrictions.eq("isDelete", 0));
                int peopleCount = maps.get("peoples") == null ? 0 : Integer.valueOf(maps.get("peoples").toString());
                String secondId = "";
                if (amount == null) {
                    SalaryTotalAmount totalAmount = new SalaryTotalAmount();
                    totalAmount.setSalaryAssignationId(assignation.getId());
                    totalAmount.setCompanyId(assignation.getCompanyId());
                    totalAmount.setPeopleCount(peopleCount);
                    totalAmount.setDepId(maps.get("deptId").toString());
                    totalAmount.setByjxldkh("0");
                    totalAmount.setSpecialReward("0");
                    totalAmount.setOtherReward("0");
                    totalAmount.setOtherReward2("0");
                    totalAmount.setOtherReward3("0");
                    totalAmount.setRewardTotal("0");
                    totalAmount.setBykfpzl("0");
                    totalAmount.setFpDate(assignation.getSalaryPeriod());
                    totalAmount.setCurMonthAmount("0");
                    totalAmount.setIsDelete(0);
                    totalAmount.setFpqx(i);
                    String salaryPeriod = salaryAssignationDao.getLastMonthSalaryPeriod(assignation.getSalaryPeriod());
                    Map<String, String> map = JsonUtil.parseProperties(salaryPeriod);
                    if (map.get("success").equals("1")) {
                        // 查找上月结余
                        SalaryTotalAmount latMonth = get(SalaryTotalAmount.class,
                                Restrictions.eq("companyId", assignation.getCompanyId()),
                                Restrictions.eq("depId", map.get("deptId")),
                                Restrictions.eq("fpDate", map.get("info")),
                                Restrictions.eq("fpqx", i),
                                Restrictions.eq("isDelete", 0));
                        if (latMonth != null) {
                            totalAmount.setLastMonthAmount(latMonth.getCurMonthAmount());
                        } else {
                            totalAmount.setLastMonthAmount("0");
                        }
                        secondId = salaryTotalAmountService.save(totalAmount);
                        if (StringUtils.isBlank(secondId))
                            throw new Exception("发生异常，总量分配失败");
                    } else {
                        throw new Exception(map.get("info"));
                    }
                } else {
                    amount.setPeopleCount(peopleCount);
                    secondId = amount.getId();
                    salaryTotalAmountService.update(amount);
                }

                // 查找二次分配1之外的薪资项目
                List<?> secondOther = findBySql(salaryAssignationDao.getSecondOtherItemsData(assignation.getCompanyId(), maps.get("deptId").toString(),
                        i, assignation.getSalaryPeriod()));
                for (int ii=0, ll = secondOther.size(); ii < ll; ii++) {
                    Object[] obj = (Object[]) secondOther.get(ii);
                    SalaryAmountItem salaryAmountItem = get(SalaryAmountItem.class,
                            Restrictions.eq("totalAmountId", secondId),
                            Restrictions.eq("depId", maps.get("deptId").toString()),
                            Restrictions.eq("salaryItemsId", obj[0]),
                            Restrictions.eq("isDelete", 0));
                    if (null != salaryAmountItem) {
                        salaryAmountItem.setNeedAmount(obj[2].toString());
                        salaryAmountItem.setSx(Integer.valueOf(obj[3].toString()));
                        salaryAmountItem.setNumberAccuracy(obj[4].toString());
                        salaryAmountItemService.update(salaryAmountItem);
                    } else {
                        SalaryAmountItem amountItem = new SalaryAmountItem();
                        amountItem.setTotalAmountId(secondId);
                        amountItem.setDepId(maps.get("deptId").toString());
                        amountItem.setSalaryItemsId(obj[0].toString());
                        amountItem.setSalaryItemsName(obj[1].toString());
                        amountItem.setNeedAmount(obj[2].toString());
                        amountItem.setSx(Integer.valueOf(obj[3].toString()));
                        amountItem.setNumberAccuracy(obj[4].toString());
                        amountItem.setIsDelete(0);
                        if (StringUtils.isBlank(salaryAmountItemService.save(amountItem))) {
                            throw new Exception("发生异常，总量分配失败");
                        }
                    }
                }
            }
        }

        return "success";
    }

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String saveItemsFormula(String assignId, String assignStaffId) throws Exception {
        SalaryAssignation assignation = get(SalaryAssignation.class, Restrictions.eq("id", assignId), Restrictions.eq("isDelete", 0));
        if (assignation == null) {
            return "该薪酬核算过程不存在";
        }
        String periodId = assignation.getSalaryPeriod();

        Map<String, Object> jc = new HashMap<String, Object>();
        jc.put("T_salaryPeriod", periodId);
        jc.put("B_salaryGroupId", assignation.getSalaryGroupId());
        jc.put("T_assignationId", assignation.getId());
        jc.put("B_companyId", assignation.getCompanyId());

        // 查找该分配过程中每个薪资项目对应的公式
        String formulas = salaryAssignationDao.getCurItemsFormula(assignation.getId(), assignStaffId);
        List<?> list = findBySql(formulas);
        Object param = null;
        DecimalFormat df = new DecimalFormat();
        List<Map<String, Object>> charges = new ArrayList<Map<String,Object>>();
        for (int ii = 0, ll = list.size(); ii < ll; ii++) {
            Object[] obj = (Object[]) list.get(ii);
            jc.put("B_staffId", obj[4]);
            jc.put("B_formula", obj[2]);
            jc.put("B_xs", obj[4]);
            jc.put("B_addOrLess", obj[11]);
            jc.put("B_itemSalaryId", obj[3]);
            String v = "0";
            try {
                v = numberAccuracy(df, obj[8].toString(), salaryFormulaService.formula(obj[2].toString(), jc));// 根据公式计算项目值
            } catch (Exception e) {
                throw new Exception(obj[6] + " 公式解析发生异常");
            }

            Map<String, Object> map = new HashMap<String, Object>();
            if (param !=null && !obj[5].equals(param)) {
                Object[] obj1 = (Object[]) list.get(ii-1);
                String success = salaryAssignationDao.saveSalaryItemsValue(charges);
                if (success.equals("fail")) throw new Exception(obj1[6] + " 公式解析更新发生异常");
                charges.clear();// 释放内存
            }
            map.put("id", obj[3]);
            map.put("charge", v);
            charges.add(map);

            param = obj[5];
        }
        if (charges.size() > 0) {
            Object[] obj1 = (Object[]) list.get(list.size()-1);
            String success = salaryAssignationDao.saveSalaryItemsValue(charges);
            if (success.equals("fail")) throw new Exception(obj1[6] + " 公式解析更新发生异常");
        }

        return "success";
    }

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String saveSelectStaffData(String assignId, String selectData) throws Exception {
        SalaryAssignation assignation = get(SalaryAssignation.class, Restrictions.eq("id", assignId),  Restrictions.eq("isDelete", 0));
        if (assignation == null) {
            return "该薪酬核算过程不存在，已经被删除";
        }
        String periodId = assignation.getSalaryPeriod();
        List<Map<String, String>> needStaffs = JsonUtil.getMapList(selectData);
        List<AssignationStaffSalary> assignationStaffSalaries = new ArrayList<AssignationStaffSalary>();

        // 部门存储容器
        Map<String, String> deptCounts = new HashMap<String, String>();
        Map<String, String> concurrentMap = new HashMap<String, String>();
        DecimalFormat df = new DecimalFormat();
        List<SalaryAgainItems> salaryAgainItems = new ArrayList<SalaryAgainItems>();
        Map<String, Object> jc = new HashMap<String, Object>();
        for (int i = 0, n = needStaffs.size(); i < n; i++) {
            Map<String, String> staffInfo = needStaffs.get(i);

            // 查找人员部门
            SalaryRecord record = get(SalaryRecord.class, Restrictions.eq("staffId", staffInfo.get("id")),
                    Restrictions.eq("deptname", staffInfo.get("deptId")));
            if (record == null) {
                throw new Exception("第"+(i+1)+"个员工不存在，已经被删除");
            }
            if (null == record.getFpqx()) {
                throw new Exception("职工号" +staffInfo.get("jobNumber") +"员工"+ staffInfo.get("name") + "分配权限不能为空");
            }

            AssignationStaff staff = new AssignationStaff();
            staff.setSalaryAssignationId(assignId);
            staff.setStaffId(staffInfo.get("id").toString());
            staff.setJobNumber(staffInfo.get("jobNumber").toString());
            staff.setStaffName(staffInfo.get("name").toString());
            if (StringUtils.isBlank(staffInfo.get("companyId"))) {
                throw new Exception(staffInfo.get("name") + "没有维护员工档案");
            }
            staff.setCompanyId(staffInfo.get("companyId").toString());
            staff.setCompanyName(staffInfo.get("company").toString());
            staff.setDeptId(staffInfo.get("deptId").toString());
            staff.setDeptName(staffInfo.get("deptName").toString());
            staff.setGwId(staffInfo.get("jtgwId").toString());
            staff.setGwName(staffInfo.get("jtgwName").toString());
            staff.setBggwId(staffInfo.get("bzgwId").toString());
            staff.setBggwName(staffInfo.get("bzgwName").toString());
            staff.setXcgwId(staffInfo.get("xcgwId").toString());
            staff.setXcgwName(staffInfo.get("xcgwName").toString());
            staff.setIsAllMonth(1);
            staff.setIsDelete(0);
            String assignMainId= assignationStaffService.save(staff);
            if (StringUtils.isBlank(assignMainId))
                throw new Exception("发生异常，薪酬核算数据分配失败");

            jc.put("B_staffId", staff.getStaffId());
            jc.put("B_companyId", staff.getCompanyId());
            jc.put("T_salaryPeriod", periodId);
            jc.put("B_salaryGroupId", assignation.getSalaryGroupId());
            jc.put("T_assignationId", assignation.getId());

            // 查找是否存在相同的公司、部门、发薪期间、分配权限
            String comb = staff.getCompanyId() + "-" + staff.getDeptId() + "-" + periodId + "-" + staffInfo.get("fpqx");
            String againId= concurrentMap.get(comb);
            if (staffInfo.get("fpqx") == null) {
                throw new Exception("职工号" +staff.getJobNumber() +"员工"+ staff.getStaffName() + "分配权限不能为空");
            }
            if (StringUtils.isBlank(againId)) {
                SalaryAgain again = get(SalaryAgain.class, Restrictions.eq("isDelete", 0),
                        Restrictions.eq("companyId", staff.getCompanyId()), Restrictions.eq("depId", staff.getDeptId()),
                        Restrictions.eq("fpDate", periodId), Restrictions.eq("fpqx", Integer.valueOf(staffInfo.get("fpqx").toString())));
                if (again !=null) againId = again.getId();
                else {
                    again = new SalaryAgain();
                    again.setSalaryAssignationId(assignation.getId());
                    again.setCompanyId(staff.getCompanyId());
                    again.setDepId(staff.getDeptId());
                    again.setFpDate(periodId);
                    again.setFpqx(Integer.valueOf(staffInfo.get("fpqx").toString()));
                    again.setRewardTotal("0");
                    again.setIsDelete(0);
                    again.setReportStatus(0);
                    again.setIsSp(0);
                    againId = salaryAgainService.save(again);
                    if (StringUtils.isBlank(againId)) throw new Exception("发生异常，二次分配失败");
                    concurrentMap.put(comb, againId);
                }
            }

            // 拆分薪资项目
            Object itemsObj = staffInfo.get("itemsId");
            if (itemsObj != null) {
                String[] items = itemsObj.toString().split(",;");
                String[] itemsName = staffInfo.get("itemsName").toString().split(",;");
                String[] sxs = staffInfo.get("sx").toString().split(",;");
                String[] formulas = staffInfo.get("formula").toString().split(",;");
                String[] xss = staffInfo.get("xs").toString().split(",;");
                String[] salaryValue = staffInfo.get("salaryValue").toString().split(",;");
                String[] isDisplay = staffInfo.get("isDisplay").toString().split(",;");
                String[] isNumber = staffInfo.get("isNumber").toString().split(",");
                String[] addOrLess = staffInfo.get("addOrLess").toString().split(",");
                for (int j = 0, m = items.length; j < m; j++) {
                    AssignationStaffSalary staffSalary = new AssignationStaffSalary();
                    staffSalary.setAssignationStaffId(assignMainId);
                    staffSalary.setIbfId(items[j]);
                    staffSalary.setIbfName(itemsName[j]);
                    staffSalary.setIsDisplay(Integer.valueOf(isDisplay[j]));
                    staffSalary.setIsNumber(Integer.valueOf(isNumber[j]));
                    staffSalary.setAddOrLess(Integer.valueOf(addOrLess[j]));
                    staffSalary.setNumberAccuracy(xss[j]);

                    // 没有公式则按照档案中的标准数据
                    String v = "";
                    try {
                        v = numberAccuracy(df, xss[j], salaryFormulaService.formula(formulas[j], jc));// 根据公式计算项目值
//                        v = numberAccuracy(df, xss[j], salaryValue[j]);// 获取设置的项目值
                    } catch (Exception e) {
                        throw new Exception("公式解析发生异常");
                    }
                    staffSalary.setAssignationCharge(v);
                    staffSalary.setSx(Integer.valueOf(sxs[j]));
                    staffSalary.setIsDelete(0);
                    assignationStaffSalaries.add(staffSalary);

                    if (staffSalary.getIsDisplay() == 1) {
                        // 二次分配薪资表
                        SalaryAgainItems againItems = new SalaryAgainItems();
                        againItems.setStaffId(staff.getStaffId());
                        againItems.setSalaryAgainId(againId);
                        againItems.setSalaryItemsId(items[j]);
                        againItems.setSalaryItemsName(itemsName[j]);
                        againItems.setNeedAmount("0");
                        againItems.setReportAmount("0");
                        againItems.setIsDelete(0);
                        againItems.setSx(Integer.valueOf(sxs[j]));
                        againItems.setNumberAccuracy(xss[j]);
                        againItems.setStaffName(staff.getStaffName());
                        againItems.setJobNumber(staff.getJobNumber());
                        againItems.setSalaryGroupId(assignation.getSalaryGroupId());
                        againItems.setSalaryGroupName(assignation.getSalaryGroupName());
                        againItems.setFpqx(Integer.valueOf(staffInfo.get("fpqx").toString()));
                        againItems.setJtgw(staffInfo.get("jtgwName").toString());
                        againItems.setBzgw(staffInfo.get("bzgwName").toString());
                        againItems.setXcgw(staffInfo.get("xcgwName").toString());
                        againItems.setGzxs(Integer.valueOf(staffInfo.get("gzxs").toString()) == 0? "计时":"计件");
                        againItems.setSpecialMark(staffInfo.get("specialMark").toString());
                        againItems.setStaticValue(salaryValue[j]);
                        salaryAgainItems.add(againItems);
                    }
                }
            }

            // 存储各部门的人员数量
            String combine = deptCounts.get(staffInfo.get("deptId"));
            Integer curCount = 1;
            if (!StringUtils.isBlank(combine)) {
                curCount = Integer.valueOf(combine.substring(0, combine.indexOf("fpqx:")));
                ++curCount;
            }
            deptCounts.put(staffInfo.get("deptId"), curCount + "fpqx:" + record.getFpqx());
        }

        // 保存薪酬核算数据中间表
        if (assignationStaffSalaries.size() > 0) {
            assignationStaffSalaryService.saveBatch(assignationStaffSalaries);

//            for (Entry<String, String> entry : deptCounts.entrySet()) {
//                // 拆分
//                Integer peopleCount = Integer.valueOf(entry.getValue().substring(0, entry.getValue().indexOf("fpqx:")));
//                String fpqx = entry.getValue().substring(entry.getValue().indexOf("fpqx:") + 5);
//                SalaryTotalAmount amount = get(SalaryTotalAmount.class,
//                        Restrictions.eq("companyId", assignation.getCompanyId()),
//                        Restrictions.eq("depId", entry.getKey()),
//                        Restrictions.eq("fpDate", assignation.getSalaryPeriod()),
//                        Restrictions.eq("fpqx", fpqx),
//                        Restrictions.eq("isDelete", 0));
//                if (amount != null) {
//                    amount.setPeopleCount(amount.getPeopleCount() + peopleCount);
//                    salaryTotalAmountService.update(amount);
//                }
//            }
        }
        if (salaryAgainItems.size() > 0) salaryAgainItemsService.saveBatch(salaryAgainItems);

        return "success";
    }

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String removeForAssign(String ids) throws Exception {
        String[] idsArray = ids.split(",");
        List<AssignationStaff> assignationStaffSalaries = new ArrayList<AssignationStaff>();
        String assignId = "";

        // 部门存储容器
        Map<String, String> deptCounts = new HashMap<String, String>();
        for (int i = 0; i < idsArray.length; i++) {
            AssignationStaff salary = get(AssignationStaff.class,
                    Restrictions.eq("id", idsArray[i]),
                    Restrictions.eq("isDelete", 0));
            if (salary == null) {
                throw new Exception("第"+(i+1)+"个员工不存在，已经被删除");
            }
            salary.setIsDelete(1);
            assignationStaffSalaries.add(salary);

            // 查找人员部门
            SalaryRecord record = get(SalaryRecord.class, Restrictions.eq("staffId", salary.getStaffId()));
            if (record == null) {
                throw new Exception("第"+(i+1)+"个员工不存在，已经被删除");
            }

            // 存储各部门的人员数量
            String combine = deptCounts.get(record.getDeptname());
            Integer curCount = 1;
            if (!StringUtils.isBlank(combine)) {
                curCount = Integer.valueOf(combine.substring(0, combine.indexOf("fpqx:")));
                ++curCount;
            }
            deptCounts.put(record.getDeptname(), curCount + "fpqx:" + record.getFpqx());
            if (StringUtils.isBlank(assignId)) {
                assignId = salary.getSalaryAssignationId();
                SalaryAssignation assignation = get(SalaryAssignation.class, Restrictions.eq("id", assignId),  Restrictions.eq("isDelete", 0));
                if (null != assignation) {
                    SalaryAgain again = get(SalaryAgain.class,
                            Restrictions.eq("companyId", salary.getCompanyId()),
                            Restrictions.eq("depId", salary.getDeptId()),
                            Restrictions.eq("fpDate", assignation.getSalaryPeriod()),
                            Restrictions.eq("fpqx", record.getFpqx()),
                            Restrictions.eq("isDelete", 0));
                    if (null != again) {
                        List<SalaryAgainItems> againItems = getList(SalaryAgainItems.class,
                                Restrictions.eq("salaryAgainId", again.getId()),
                                Restrictions.eq("salaryGroupId", assignation.getSalaryGroupId()),
                                Restrictions.eq("staffId", salary.getStaffId()),
                                Restrictions.eq("isDelete", 0));
                        for (SalaryAgainItems salaryAgainItems : againItems) {
                            salaryAgainItems.setIsDelete(1);
                            salaryAgainItemsService.update(salaryAgainItems);
                        }
                    }
                }
            }
        }
        assignationStaffService.saveBatch(assignationStaffSalaries);

        // 更新总量表数据
        SalaryAssignation assignation = get(SalaryAssignation.class, Restrictions.eq("id", assignId),  Restrictions.eq("isDelete", 0));
        if (assignation != null) {
            for (Entry<String, String> entry : deptCounts.entrySet()) {
                // 拆分
                Integer peopleCount = Integer.valueOf(entry.getValue().substring(0, entry.getValue().indexOf("fpqx:")));
                String fpqx = entry.getValue().substring(entry.getValue().indexOf("fpqx:") + 5);
                SalaryTotalAmount amount = get(SalaryTotalAmount.class,
                        Restrictions.eq("companyId", assignation.getCompanyId()),
                        Restrictions.eq("depId", entry.getKey()),
                        Restrictions.eq("fpDate", assignation.getSalaryPeriod()),
                        Restrictions.eq("fpqx", fpqx),
                        Restrictions.eq("isDelete", 0));
                if (amount != null) {
                    amount.setPeopleCount(amount.getPeopleCount() - peopleCount);
                    salaryTotalAmountService.update(amount);
                }
            }
        } else {
            throw new Exception("该薪酬核算过程不存在，已经被删除");
        }

        return "success";
    }

    @Override
    public String updateReCalculateStaffsItem(String assignStaffId, String assignId, String id) {
        List<Map<String, Object>> list = salaryAssignationDao.getSelectedStaffAssignData(assignStaffId, assignId, id);
        List<AssignationStaffSalary> staffSalaries = new ArrayList<AssignationStaffSalary>();
        DecimalFormat df = new DecimalFormat();
        Map<String, Object> jc = new HashMap<String, Object>();
        for (int i = 0, l = list.size(); i < l; i++) {
            Map<String, Object> map = list.get(i);
            // 中间表ID
            String[] staffSalaryIds = map.get("id").toString().split(",;");

            // 默认公式
            String[] formulas = map.get("formula").toString().split(",;");

            for (int j = 0, m = staffSalaryIds.length; j < m; j++) {
                // 查找中间表数据
                AssignationStaffSalary staffSalary = assignationStaffSalaryService.get(AssignationStaffSalary.class,
                        Restrictions.eq("id", staffSalaryIds[j]),
                        Restrictions.eq("isDelete", 0));
                if (staffSalary != null) {
                    // 计算公式
                    Object result = salaryFormulaService.formula(formulas[j], jc);
                    SalaryItems items = get(SalaryItems.class, Restrictions.eq("id", staffSalary.getIbfId()));
                    if (items != null) {
                        // 小数位数
                        staffSalary.setAssignationCharge(numberAccuracy(df, items.getNumberAccuracy(), result == null ? "0" : result.toString()));
                        staffSalaries.add(staffSalary);
                    }
                }
            }
        }

        // 更新值
        assignationStaffSalaryService.saveBatch(staffSalaries);

        return "success";
    }

    @Override
    public String updateSelectReCalculateStaffsItem(String ids, String itemID, String formula) {
        List<AssignationStaffSalary> staffSalaries = new ArrayList<AssignationStaffSalary>();
        Map<String, Object> jc = new HashMap<String, Object>();
        DecimalFormat df = new DecimalFormat();
        String sql = salaryAssignationDao.getSelectReCalculateStaffsItem(ids, itemID);
        // 查找薪酬区间
        List<?> list = findBySql(sql);
        String periodId = "";
        String checkId = "";
        String salaryGroupId = "";
        if (list.size() > 0) {
            Object[] obj = (Object[]) list.get(0);
            SalaryAssignation assignation = get(SalaryAssignation.class,
                    Restrictions.eq("id", obj[4]),
                    Restrictions.eq("isDelete", 0));
            if (assignation == null) {
                return "该薪酬核算过程不存在";
            }
            checkId = assignation.getId();
            salaryGroupId = assignation.getSalaryGroupId();
            periodId = assignation.getSalaryPeriod();
        }
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            AssignationStaffSalary staffSalary = get(AssignationStaffSalary.class,
                    Restrictions.eq("id", obj[0]),
                    Restrictions.eq("isDelete", 0));
            if (staffSalary != null) {
                jc.put("B_staffId", obj[1]);
                jc.put("B_companyId", obj[3]);
                jc.put("T_salaryPeriod", periodId);
                jc.put("B_salaryGroupId", salaryGroupId);
                jc.put("T_assignationId", checkId);
                // 计算公式
                Object result = salaryFormulaService.formula(formula, jc);
                // 小数位数
                staffSalary.setAssignationCharge(numberAccuracy(df, obj[2].toString(), result));
                staffSalaries.add(staffSalary);
            }
        }

        // 更新值
        if (staffSalaries.size() > 0) assignationStaffSalaryService.saveBatch(staffSalaries);

        return "success";
    }

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String updateSelectStaffSalaryData(String griddata, String columns) throws Exception {
        List<Map<String, String>> gridSalarys = JsonUtil.getMapList(griddata);
        String[] salaryId = columns.split(",");
        for (int i = 0, l = gridSalarys.size(); i < l; i++) {
            Map<String, String> map = gridSalarys.get(i);
            if ("modified".equals(map.get("_state"))) {
                AssignationStaff staff = assignationStaffService.get(AssignationStaff.class,
                        Restrictions.eq("id", map.get("id")),
                        Restrictions.eq("isDelete", 0));
                if (staff == null) {
                    throw new Exception(map.get("name") + "核算数据不存在，已经被删除");
                }
                staff.setIsAllMonth(Integer.valueOf(map.get("isAllMonth")));
                for (String str : salaryId) {
                    AssignationStaffSalary staffSalary = assignationStaffSalaryService.get(AssignationStaffSalary.class,
                            Restrictions.eq("assignationStaffId", staff.getId()),
                            Restrictions.eq("ibfId", str),
                            Restrictions.eq("isDelete", 0));
                    if (staffSalary == null) {
                        throw new Exception(map.get("name") + "核算数据不存在，已经被删除");
                    }
                    String item = map.get(staffSalary.getIbfId());
                    staffSalary.setAssignationCharge(item);
                    assignationStaffSalaryService.update(staffSalary);
                }
            }
        }

        return "success";
    }

    @Override
    public List<Map<String, String>> getAllGroupWageFinancialList(String assignStaffId, String itemid) {
        return salaryAssignationDao.getAllGroupWageFinancialList(assignStaffId, itemid);
    }

    /**
     * @Title: 计算保留值
     * @param df
     * @param xs
     * @param value
     * @return 
     * Object 
     * @author zhanghj
     * @since 2017年5月12日 V 1.0
     */
    private String numberAccuracy(DecimalFormat df, String xs, Object value) {
        // 小数位数
        if (Integer.valueOf(xs) == 0) {
            df.applyPattern("0");
        } else {
            df.applyPattern("0."+String.format("%0"+ xs +"d%n", 0));
        }

        return df.format(Double.valueOf(value.toString())).trim();
    }

    @Override
    public String commitCheck(String id) {
        // 查找该审核过程是否存在
        SalaryAssignation assignation = get(SalaryAssignation.class,
                Restrictions.eq("id", id),
                Restrictions.eq("isDelete", 0));
        if (assignation == null) {
            return "该核算过程不存在，已经被删除";
        }

        // 是否已经提交审核
        if (assignation.getIsSp() == 1) {
            return "该核算过程已经提交";
        }

        // 是否已经通过
        if (assignation.getIsSp() == 3) {
            return "该核算过程已经审核通过";
        }
        assignation.setIsSp(1);
        update(assignation);

        return "success";
    }

    @Override
    public String batchCommitCheck(String ids, int isIgnore, String formData) {
        String[] idsArray = ids.split(",");
        List<SalaryAssignation> list = new ArrayList<SalaryAssignation>();
        List<AssignationRecord> listRecords = new ArrayList<AssignationRecord>();
        List<CheckProcess> checkProcesses = new ArrayList<CheckProcess>();
        Map<String, String> mapData = JsonUtil.parseProperties(formData);
        // 目前只能单个提交审批
        for (int i = 0, l = idsArray.length; i < l; i++) {
            // 查找该审核过程是否存在
            SalaryAssignation assignation = get(SalaryAssignation.class,
                    Restrictions.eq("id", idsArray[i]),
                    Restrictions.eq("isDelete", 0));
            if (assignation == null) {
                return "第"+(i+1)+"个核算过程不存在，已经被删除";
            }

            // 是否已经提交审核
            if (assignation.getIsSp() == 1) {
                return "第"+(i+1)+"个核算过程已经提交";
            }

            // 是否已经通过
            if (assignation.getIsSp() == 3) {
                return "第"+(i+1)+"个核算过程已经审核通过";
            }

            // 判断该公司下所有部门的员工是否已经都上报
            if (isIgnore == 1) {
                List<SalaryAgain> agains = getList(SalaryAgain.class,
                        Restrictions.eq("salaryAssignationId", assignation.getId()),
                        Restrictions.eq("companyId", assignation.getCompanyId()),
                        Restrictions.eq("fpDate", assignation.getSalaryPeriod()),
                        Restrictions.eq("fpqx", 2),
                        Restrictions.eq("reportStatus", 0),
                        Restrictions.eq("isDelete", 0));
                Set<String> set = new HashSet<String>();
                for (SalaryAgain again : agains) {
                    List<SalaryAgainItems> items = getList(SalaryAgainItems.class,
                            Restrictions.eq("salaryAgainId", again.getId()),
                            Restrictions.eq("isDelete", 0));
                    if (items.size() > 0) {
                        QxDepartment department = get(QxDepartment.class, Restrictions.eq("id", again.getDepId()));
                        set.add(department.getName());
                    }
                }
                if (set.size() > 0) return set.toString()+" 部门人员二次分配没有上报";
            }
            assignation.setIsSp(1);
            list.add(assignation);

            // 存在审批记录，作废
            List<AssignationRecord> records = getList(AssignationRecord.class,
                    Restrictions.eq("salaryAssignationId", assignation.getId()),
                    Restrictions.eq("isGiveup", 0),
                    Restrictions.eq("isDelete", 0));
            for (AssignationRecord assignationRecord : records) {
                assignationRecord.setIsGiveup(1);
                listRecords.add(assignationRecord);
            }

            // 保存审批流程
            CheckProcess checkProcess = new CheckProcess();
            checkProcess.setSalaryAssignationId(idsArray[i]);
            checkProcess.setSpgwId(mapData.get("spgw"));
            checkProcess.setStaffId(mapData.get("spr"));
            checkProcess.setNodeId(mapData.get("nodeId"));
            checkProcess.setSpgwName(mapData.get("spgwName"));
            checkProcess.setStaffName(mapData.get("staffName"));
            WorkFlowChild child = get(WorkFlowChild.class, Restrictions.eq("id", mapData.get("nodeId")));
            if (child != null) checkProcess.setNodeName(child.getAppman());
            checkProcess.setIsDelete(0);
            checkProcesses.add(checkProcess);
        }

        if (list.size() > 0) {
            saveBatch(list);
            if (listRecords.size() > 0) assignationRecordService.saveBatch(listRecords);
            if (checkProcesses.size() > 0) checkProcessService.saveBatch(checkProcesses);
        } else {
            return "该核算过程已经通过";
        }

        return "success";
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void export(String id, String searchData) {
        // 查找该审核过程是否存在
        SalaryAssignation assignation = get(SalaryAssignation.class,
                Restrictions.eq("id", id),
                Restrictions.eq("isDelete", 0));
        if (assignation == null) {
            return;
        }
        String sql = salaryAssignationDao.getAssignationForStaffDataSql(id, "", searchData);
        List<?> list = findBySql(sql);

        ArrayList<ArrayList> tableData = new ArrayList<ArrayList>();
        for (int i = 0; i < list.size(); i++) {
            ArrayList rowList=new ArrayList();
            Object[] obj = (Object[]) list.get(i);
            rowList.add(i+1);//序号
            rowList.add(obj[2]);// 工号
            rowList.add(obj[3]);// 姓名
            rowList.add(obj[12]);// 部门
            rowList.add(obj[13]);// 具体岗位
            rowList.add(obj[15]);// 标准岗位
            rowList.add(obj[16]);// 薪酬岗位
            String[] charge = LingUtil.ClobToString((Clob)obj[6]).split(",;");
            String[] addOrLess = LingUtil.ClobToString((Clob)obj[10]).toString().split(",;");
            for (int j = 0, ll = charge.length; j < ll; j++) {
                if (Integer.valueOf(addOrLess[j]) == 2) {
                    Double d = Double.valueOf(charge[j]);
                    rowList.add(d==0?0:"-"+charge[j]);// 薪资项目对应的值
                } else rowList.add(charge[j]);// 薪资项目对应的值
            }
            tableData.add(rowList);
        }

        // 汇总数据
        List<Map<String, Object>> totalList = salaryAssignationDao.getTotalData(id);

        //表格标题
        String[] tableCaption ={"序号","工号","姓名","部门","具体岗位","标准岗位","薪酬岗位"};
        if (list.size() > 0) {
            // 取出薪资项目
            Object[] obj = (Object[]) list.get(0);
            String[] items = LingUtil.ClobToString((Clob)obj[5]).toString().split(",;");
            int len1 = tableCaption.length;
            int len2 = items.length;

            // 扩容第一个数组
            tableCaption = Arrays.copyOf(tableCaption, len1 + len2);
            System.arraycopy(items, 0, tableCaption, len1, len2);

            // 薪资ID,增加合计一行
            String[] itemsID = LingUtil.ClobToString((Clob)obj[4]).toString().split(",;");
            ArrayList rowList=new ArrayList();
            rowList.add("合计");
            rowList.add("");
            rowList.add("");
            rowList.add("");
            rowList.add("");
            rowList.add("");
            rowList.add("");
            for (String item : itemsID) {
                Boolean isExist = false;
                for (Map<String, Object> map : totalList) {
                    if (map.get("IBFID").equals(item)) {
                        rowList.add(map.get("CHARGE"));
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    rowList.add("0");
                }
            }
            tableData.add(rowList);
        }

        //表格脚部分
        String dcr=ToolUtil.userName();
        Date dcrq=new Date();
        ArrayList footData=new ArrayList();
        footData.add("导出人： "+ ExcelUtil.toString(dcr));
        footData.add("导出日期： "+ ExcelUtil.toString(dcrq));
        ExcelUtil.export(assignation.getName() +"导出薪酬数据", null, tableCaption, tableData, footData);
    }

    @Override
    public Map<String, Object> getNeedCheckStaffData(String id, Pager pager) {
        return salaryAssignationDao.getNeedCheckStaffData(id, pager);
    }

    @Override
    public String updateReleaseData(String ids) {
        String[] idsArray = ids.split(",");
        List<SalaryAssignation> assignations = new ArrayList<SalaryAssignation>();
        for (int i = 0, l = idsArray.length; i < l; i++) {
            // 查找该审核过程是否存在
            SalaryAssignation assignation = get(SalaryAssignation.class,
                    Restrictions.eq("id", idsArray[i]),
                    Restrictions.eq("isDelete", 0));
            if (assignation == null) {
                return "该核算过程不存在，已经被删除";
            }

            // 已经发放
            if (assignation.getIsFafang() == 1) {
                return assignation.getName() + "核算过程已发放";
            }

            // 已经发布
            if (assignation.getIsFafang() == 3) {
                return assignation.getName() + "核算过程已发布";
            }

            // 未提交
            if (assignation.getIsSp() == 0) {
                return assignation.getName() + "核算过程未提交，不能发放";
            }

            // 未通过
            if (assignation.getIsSp() == 2) {
                return assignation.getName() + "核算过程未通过，不能发放";
            }

            // 未通过
            if (assignation.getIsSp() != 3) {
                return assignation.getName() + "核算过程未通过，不能发放";
            }

            assignation.setIsFafang(1);
            assignation.setFafangAccount(LingUtil.userinfo().getId());
            assignation.setFafangDate(new Date());
            assignations.add(assignation);
        }

        // 更新状态
        saveBatch(assignations);

        return "success";
    }

    @Override
    public String updateNoReleaseData(String ids) {
        String[] idsArray = ids.split(",");
        List<SalaryAssignation> assignations = new ArrayList<SalaryAssignation>();
        for (int i = 0, l = idsArray.length; i < l; i++) {
            // 查找该审核过程是否存在
            SalaryAssignation assignation = get(SalaryAssignation.class,
                    Restrictions.eq("id", idsArray[i]),
                    Restrictions.eq("isDelete", 0));
            if (assignation == null) {
                return "该核算过程不存在，已经被删除";
            }

            // 未发放
            if (assignation.getIsFafang() == 0) {
                return assignation.getName() + "核算过程未发放";
            }

            // 已发布
            if (assignation.getIsFafang() == 3) {
                return assignation.getName() + "核算过程已发布";
            }

            assignation.setIsFafang(0);
            assignation.setFafangAccount(LingUtil.userinfo().getId());
            assignation.setFafangDate(new Date());
            assignations.add(assignation);
        }

        // 更新状态
        if (assignations.size() > 0) saveBatch(assignations);
        else return "没有核算过程可以发放";

        return "success";
    }

    @Override
    public String isCheckAUth(String ids, String userName) {
        return salaryAssignationDao.isCheckAUth(ids, userName);
    }

    @Override
    public String isCheckAUth2(String ids, String userName) {
        return salaryAssignationDao.isCheckAUth2(ids, userName);
    }

    @Override
    public String updateCheckPassed(String ids, String userName, String formdata) {
        String[] idsArray = ids.split(",");
        Map<String, String> map = JsonUtil.parseProperties(formdata);
        List<SalaryAssignation> assignations = new ArrayList<SalaryAssignation>();
        List<AssignationRecord> records = new ArrayList<AssignationRecord>();
        int isSp = Integer.valueOf(map.get("isSp")) == 0 ? 2: 3;
        int sp = Integer.valueOf(map.get("isSp"));
        for (int i = 0; i < idsArray.length; i++) {
            SalaryAssignation assignation = get(SalaryAssignation.class,
                    Restrictions.eq("id", idsArray[i]),
                    Restrictions.eq("isDelete", 0));
            if (assignation == null) {
                return "选择的第"+(i+1)+"个分配过程不存在，已经被删除";
            }

            // 查找当前审批者是否已经审批了相同结果
            List<AssignationRecord> asRecords = getOrderList(AssignationRecord.class, Order.asc("checkDate"),
                    Restrictions.eq("salaryAssignationId", idsArray[i]),
                    Restrictions.eq("assignationAccount", userName),
                    Restrictions.eq("isGiveup", 0),
                    Restrictions.eq("isDelete", 0));
            if (asRecords.size() > 0 && asRecords.get(asRecords.size() - 1).getIsSp() == sp) {
                continue;
            }

            // 是否启用审批
            WorkFlowPrent flowPrent = get(WorkFlowPrent.class,
                    Restrictions.eq("id", "402881945bec28ab015bec383aee0011"));
            if (flowPrent == null) {
                return "不存在审批流程";
            } else if (flowPrent.getState() == 0) {
                return "审批流程没有启用";
            }

            // 是否是最后一个审批者
            List<WorkFlowChild> childs = getOrderList(WorkFlowChild.class, Order.asc("sort"),
                    Restrictions.eq("pid", "402881945bec28ab015bec383aee0011"));

            // 是最后一个审批者
            if (sp == 0 || childs.get(childs.size()-1).getAppid().equals(userName)) {
                assignation.setIsSp(isSp);
                assignations.add(assignation);
            }

            // 审批记录
            AssignationRecord record = new AssignationRecord();
            record.setCheckDate(new Date());
            record.setIsSp(sp);
            record.setSalaryAssignationId(idsArray[i]);
            record.setAssignationAccount(userName);
            record.setNote(map.get("note"));
            record.setIsGiveup(0);
            record.setIsDelete(0);
            records.add(record);
        }

        // 更新分配过程
        if (assignations.size() > 0) saveBatch(assignations);
        if (records.size() > 0) assignationRecordService.saveBatch(records);

        return "success";
    }

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String updateCheckPassed2(String ids, String userName, String formdata) throws Exception {
        String[] idsArray = ids.split(",");
        Map<String, String> map = JsonUtil.parseProperties(formdata);
        List<SalaryAssignation> assignations = new ArrayList<SalaryAssignation>();
        List<AssignationRecord> records = new ArrayList<AssignationRecord>();
        List<CheckProcess> checkProcesses = new ArrayList<CheckProcess>();
        int isSp = Integer.valueOf(map.get("isSp")) == 0 ? 2: 3;
        int sp = Integer.valueOf(map.get("isSp"));
        for (int i = 0; i < idsArray.length; i++) {
            SalaryAssignation assignation = get(SalaryAssignation.class,
                    Restrictions.eq("id", idsArray[i]),
                    Restrictions.eq("isDelete", 0));
            if (assignation == null) {
                throw new Exception("选择的第"+(i+1)+"个分配过程不存在，已经被删除");
            }

            // 查找当前审批者是否已经审批了相同结果
            List<AssignationRecord> asRecords = getOrderList(AssignationRecord.class, Order.asc("checkDate"),
                    Restrictions.eq("salaryAssignationId", idsArray[i]),
                    Restrictions.eq("assignationAccount", userName),
                    Restrictions.eq("isGiveup", 0),
                    Restrictions.eq("isDelete", 0));
            if (asRecords.size() > 0 && asRecords.get(asRecords.size() - 1).getIsSp() == sp) {
                continue;
            }

            // 是否启用审批
            WorkFlowPrent flowPrent = get(WorkFlowPrent.class,
                    Restrictions.eq("id", assignation.getProcess()));
            if (flowPrent == null) {
                throw new Exception("不存在审批流程");
            } else if (flowPrent.getState() == 0) {
                throw new Exception("审批流程没有启用");
            }

            List<WorkFlowChild> childs = getOrderList(WorkFlowChild.class, Order.desc("sort"), Restrictions.eq("pid", flowPrent.getId()));
            if (childs.size() == 0) throw new Exception("审批流程下不存在审批岗位");

            if (!StringUtils.isBlank(map.get("spgw"))) {
                DeptPosition deptPosition = get(DeptPosition.class,
                        Restrictions.eq("id", map.get("spgw")),
                        Restrictions.eq("isDelete", 0));
                if (deptPosition ==null) throw new Exception("该审批岗位不存在");
                QxUser qxUser = get(QxUser.class, Restrictions.eq("username", map.get("spr")), Restrictions.eq("isDelete", 0));
                if (qxUser == null) throw new Exception("该审批人不存在");

                // 审批通过则增加审批流程
                if (sp == 1) {
                    // 保存审批流程
                    CheckProcess checkProcess = new CheckProcess();
                    checkProcess.setSalaryAssignationId(idsArray[i]);
                    checkProcess.setSpgwId(map.get("spgw"));
                    checkProcess.setStaffId(map.get("spr"));
                    checkProcess.setNodeId(map.get("nodeId"));
                    checkProcess.setSpgwName(map.get("spgwName"));
                    checkProcess.setStaffName(map.get("staffName"));
                    WorkFlowChild child = get(WorkFlowChild.class, Restrictions.eq("id", map.get("nodeId")));
                    if (child != null) checkProcess.setNodeName(child.getAppman());
                    checkProcess.setIsDelete(0);
                    checkProcesses.add(checkProcess);
                }
            } else {
                Boolean isCanCheck = false;
                if (sp == 0) isCanCheck = true;
                else {
                    // 不存在审批岗位,判断是否是最后的审批人
                    BasicInformation baseinfolist = get(BasicInformation.class, Restrictions.eq("jobNumber", userName), Restrictions.eq("isDelete", 0));
                    if (baseinfolist != null) {
                        // 是最后一个审批岗位中的人
                        DeptPosition deptPosition = get(DeptPosition.class,
                                Restrictions.eq("positionId", childs.get(0).getAppid()),
                                Restrictions.eq("id", baseinfolist.getSpecificPostId()),
                                Restrictions.eq("isDelete", 0));
                        if (deptPosition != null) isCanCheck = true;
                        else throw new Exception("该审批岗位不存在");
                    } else {
                        throw new Exception("当前登录账户对应的人事信息不存在");
                    }
                }

                // 可以审批
                if (isCanCheck) {
                    assignation.setIsSp(isSp);
                    assignations.add(assignation);
                }
            }

            // 审批不通过，取消二次分配上报
            if (sp == 0) {
                List<SalaryAgain> again = getList(SalaryAgain.class, Restrictions.eq("companyId", assignation.getCompanyId()),
                        Restrictions.eq("fpDate", assignation.getSalaryPeriod()), Restrictions.eq("isDelete", 0));
                for (SalaryAgain salaryAgain : again) {
                    salaryAgain.setReportStatus(0);
                    salaryAgain.setIsSp(0);
                    List<SalaryAgainItems> againItems = getList(SalaryAgainItems.class,
                            Restrictions.eq("salaryAgainId", salaryAgain.getId()), Restrictions.eq("isDelete", 0));
                    for (int ii = 0, m = againItems.size(); ii < m; ii++) {
                        SalaryAgainItems items = againItems.get(ii);
                        items.setReportAmount("0");
                        salaryAgainItemsService.update(items);
                    }

                    salaryAgain.setRewardTotal("0");
                    salaryAgainService.update(salaryAgain);
                }
                
            }

            // 审批记录
            AssignationRecord record = new AssignationRecord();
            record.setCheckDate(new Date());
            record.setIsSp(sp);
            record.setSalaryAssignationId(idsArray[i]);
            record.setAssignationAccount(userName);
            record.setNote(map.get("note"));
            record.setIsGiveup(0);
            record.setIsDelete(0);
            records.add(record);
        }

        // 更新分配过程
        if (assignations.size() > 0) saveBatch(assignations);
        if (records.size() > 0) assignationRecordService.saveBatch(records);
        if (checkProcesses.size() > 0) checkProcessService.saveBatch(checkProcesses);

        return "success";
    }

    @Override
    public String createBankFile(String ids) {
        String[] idsArray = ids.split(",");
        for (int i = 0; i < idsArray.length; i++) {
            SalaryAssignation assignation = get(SalaryAssignation.class,
                    Restrictions.eq("id", idsArray[i]),
                    Restrictions.eq("isDelete", 0));
            if (assignation == null) {
                return "选择的第"+(i+1)+"个分配过程不存在，已经被删除";
            }
            if (assignation.getIsFafang() == 0) {
                return "选择的第"+(i+1)+"个分配过程没有发放不可以生成报盘文件";
            }
        }

        return "success";
    }

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String updateSendSalaryRecord(String ids) throws Exception {
        String[] idsArray = ids.split(",");

        // 发布过的删除
        deleteByCriteria(SalaryWageMain.class, Restrictions.in("salaryAssignationId", idsArray));
        List<SalaryAssignation> assignations = new ArrayList<SalaryAssignation>();
        for (int i = 0, l = idsArray.length; i < l; i++) {
            // 查找该审核过程是否存在
            SalaryAssignation assignation = get(SalaryAssignation.class,
                    Restrictions.eq("id", idsArray[i]),
                    Restrictions.eq("isDelete", 0));
            if (assignation == null) {
                return "该核算过程不存在，已经被删除";
            }

            // 未发放
            if (assignation.getIsFafang() == 0) {
                return assignation.getName() + "核算过程未发放";
            }

            // 已发布
            if (assignation.getIsFafang() == 3) {
                continue;
            }

            assignation.setIsFafang(3);
            assignation.setFafangAccount(LingUtil.userinfo().getId());
            assignation.setFafangDate(new Date());
            assignations.add(assignation);
        }

        // 更新状态
        if (assignations.size() > 0) saveBatch(assignations);

        // 工资条发放
        String recordsSql = salaryAssignationDao.getNeedSendSalaryRecordsSql(ids);
        List<?> needSendSalaryRecords = findBySql(recordsSql);

        // 工资条关联主表ID
        String mainResult = "";
        List<SalaryWageSecond> salaryWageSeconds = new ArrayList<SalaryWageSecond>();
        for (int i = 0, l = needSendSalaryRecords.size(); i < l; i++) {
            Object[] obj = (Object[]) needSendSalaryRecords.get(i);
            SalaryWageMain wageMain = new SalaryWageMain();
            wageMain.setSalaryAssignationId(obj[0].toString());
            wageMain.setStaffId(obj[1].toString());
            wageMain.setJobNumber(obj[2].toString());
            wageMain.setStaffName(obj[3].toString());
            wageMain.setCompanyId(obj[7].toString());
            wageMain.setCompanyName(obj[8].toString());
            wageMain.setDeptId(obj[9].toString());
            wageMain.setDeptName(obj[10].toString());
            wageMain.setIsRelease(1);
            wageMain.setIsDelete(0);
            mainResult = salaryWageMainService.save(wageMain);
            if (StringUtils.isBlank(mainResult)) {
                throw new Exception("发生异常");
            }

            if (obj[4] != null) {
                String[] itemIds = obj[4].toString().split(",");
                String[] itemNames = obj[5].toString().split(",");
                String[] charges = obj[6].toString().split(",");
                String[] sx = obj[11].toString().split(",");
                for (int j = 0, m = itemIds.length; j < m; j++) {
                    SalaryWageSecond wageSecond = new SalaryWageSecond();
                    wageSecond.setSalaryWageMainId(mainResult);
                    wageSecond.setIbfId(itemIds[j]);
                    wageSecond.setItemsName(itemNames[j]);
                    wageSecond.setSalaryValue(charges[j]);
                    wageSecond.setIsDelete(0);
                    wageSecond.setSx(Integer.valueOf(sx[j]));
                    salaryWageSeconds.add(wageSecond);
                }
            }
        }
        if (salaryWageSeconds.size() > 0) salaryWageSecondService.saveBatch(salaryWageSeconds);

        return "success";
    }

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String updateNoSendSalaryRecord(String ids) throws Exception {
        String[] idArray = ids.split(",");
        for (int i = 0, l = idArray.length; i < l; i++) {
            SalaryAssignation assignationNew  = get(SalaryAssignation.class, Restrictions.eq("id", idArray[i]), Restrictions.eq("isDelete", 0));
            if (assignationNew == null) {
                throw new Exception("选择的第"+(i+1)+"个分配过程不存在，已经被删除");
            }
            if (assignationNew.getIsFafang() != 3) {
                throw new Exception(assignationNew.getName()+"分配过程没有发布");
            }
            assignationNew.setIsFafang(2);
            update(assignationNew);
            SalaryWageMain salaryWageMain  = get(SalaryWageMain.class,
                    Restrictions.eq("salaryAssignationId", assignationNew.getId()), Restrictions.eq("isDelete", 0));
            if (salaryWageMain != null) {
                if (salaryWageMain.getIsRelease() == 0) {
                    salaryWageMain.setIsRelease(0);
                    salaryWageMainService.update(salaryWageMain);
                }
            } else {
                throw new Exception("发生异常");
            }
        }

        return "success";
    }

    @Override
    public String checkPeriodData(String id, String conditionData, String periods) {
        // 删除之前已经存在的核对数据
        deleteByCriteria(CheckMonthSalary.class, Restrictions.eq("checkId", id), Restrictions.eq("isDelete", 0));

        String sql = salaryAssignationDao.getCheckPeriodData(id, "{periods:\""+periods+"\"}", conditionData);
        List<CheckMonthSalary> checkMonthSalaries = new ArrayList<CheckMonthSalary>();
        List<?> list = findBySql(sql);
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            Object jobNumber = obj[2];
            int type = Integer.valueOf(obj[11].toString());

            CheckMonthSalary monthSalary = new CheckMonthSalary();
            monthSalary.setCheckId(obj[13].toString());
            monthSalary.setStaffId(obj[1].toString());
            monthSalary.setIsDelete(0);

            Map<String, String> prop = new HashMap<String, String>();
            if ((i+1) < l) {
                Object[] nextObj = (Object[]) list.get(i+1);
                Object nextJobNumber = nextObj[2];
                if (jobNumber.equals(nextJobNumber)) {
                    if ((obj[4] != null && !obj[4].equals(""))
                            && (obj[6] != null && !obj[6].equals(""))
                            && (nextObj[4] != null && !nextObj[4].equals(""))
                            && (nextObj[6] != null && !nextObj[6].equals(""))) {
                        String[] itemIds = obj[4].toString().split(",");
                        String[] charges = obj[6].toString().split(",");
                        String[] nextCharges = nextObj[6].toString().split(",");
                        for (int j = 0, m = itemIds.length; j < m; j++) {
                            Double curCharge = Double.valueOf(charges[j]);
                            Double nextCharge = Double.valueOf(nextCharges[j]);
                            if (curCharge > nextCharge || nextCharge > curCharge) {
                                CheckSalaryColor color = get(CheckSalaryColor.class,
                                        Restrictions.eq("ibfId", itemIds[j]),
                                        Restrictions.eq("companyId", obj[15]),
                                        Restrictions.eq("isDelete", 0));
                                if (color != null) {
                                    if (Math.abs(curCharge - nextCharge) >= Double.valueOf(color.getColorDiff())) {
                                        prop.put(itemIds[j], "color: " +color.getColor());
                                    }
                                } else {
                                    prop.put(itemIds[j], "color: red");
                                }
                            }
                        }
                    }
                    i++;
                    monthSalary.setField1("1");
                    monthSalary.setNote(JsonUtil.Encode(prop));
                    checkMonthSalaries.add(monthSalary);
                } else {
                    if ((obj[4] != null && !obj[4].equals(""))
                            && (obj[6] != null && !obj[6].equals(""))) {
                        String[] itemIds = obj[4].toString().split(",");
                        for (int j = 0, m = itemIds.length; j < m; j++) {
                            prop.put(itemIds[j], "background: #4876FF");
                        }
                        monthSalary.setField1("1");
                    } else if ((nextObj[4] != null && !nextObj[4].equals(""))
                            && (nextObj[6] != null && !nextObj[6].equals(""))) {
                        String[] itemIds = nextObj[4].toString().split(",");
                        for (int j = 0, m = itemIds.length; j < m; j++) {
                            prop.put(itemIds[j], "background: #CCFFCC");
                        }
                        monthSalary.setField1("2");
                    }
                    monthSalary.setNote(JsonUtil.Encode(prop));
                    checkMonthSalaries.add(monthSalary);
                }
            } else {
                String color = "#CCFFCC";
                monthSalary.setField1("2");
                if (type == 1) {
                    color = "#4876FF";
                    monthSalary.setField1("1");
                }
                if ((obj[4] != null && !obj[4].equals(""))
                        && (obj[6] != null && !obj[6].equals(""))) {
                    String[] itemIds = obj[4].toString().split(",");
                    for (int j = 0, m = itemIds.length; j < m; j++) {
                        prop.put(itemIds[j], "background: " + color);
                    }
                }
                monthSalary.setNote(JsonUtil.Encode(prop));
                checkMonthSalaries.add(monthSalary);
            }
        }
        if (checkMonthSalaries.size() > 0) checkMonthSalaryService.saveBatch(checkMonthSalaries);

        return "success";
    }

    @Override
    public String updateAllot(String id) {
        SalaryAssignation staff = get(SalaryAssignation.class,
                Restrictions.eq("id", id),
                Restrictions.eq("isDelete", 0));
        // 判断分配过程是否存在
        if (staff == null) {
            return "该薪酬核算过程不存在";
        }

        String sql = salaryAssignationDao.getStaffItemsCharge(staff.getId());
        List<?> staffsCharge = findBySql(sql);
        int len = staffsCharge.size();
        if (len == 0) {
            return "二次分配数据不存在";
        }
        List<SalaryAgainItems> items = new ArrayList<SalaryAgainItems>();
        for (int i = 0; i < len; i++) {
            Object[] obj = (Object[]) staffsCharge.get(i);
            if (Integer.valueOf(obj[5].toString()) == 1) {
                return "该二次分配存在上报状态，不可以分配";
            }
            SalaryAgainItems againItems = get(SalaryAgainItems.class,
                    Restrictions.eq("id", obj[3]),
                    Restrictions.eq("isDelete", 0));
            if (againItems == null) {
                return "薪资项目 " + obj[4] + "二次分配数据不存在";
            }
            againItems.setNeedAmount(obj[2].toString());
            items.add(againItems);
        }
        if (items.size() > 0) salaryAgainItemsService.saveBatch(items);

        return "success";
    }

    @Override
    public String updateCheckSecondData(String id, String ids) {
        SalaryAssignation staff = get(SalaryAssignation.class,
                Restrictions.eq("id", id),
                Restrictions.eq("isDelete", 0));
        // 判断分配过程是否存在
        if (staff == null) {
            return "该薪酬核算过程不存在";
        }

        // 上报才可以
        List<SalaryAgain> agains = getList(SalaryAgain.class,
                Restrictions.eq("companyId", staff.getCompanyId()),
                Restrictions.eq("fpDate", staff.getSalaryPeriod()),
                Restrictions.eq("fpqx", 2),
                Restrictions.eq("reportStatus", 0),
                Restrictions.eq("isDelete", 0));
        Set<String> set = new HashSet<String>();
        for (SalaryAgain again : agains) {
            List<SalaryAgainItems> items = getList(SalaryAgainItems.class,
                    Restrictions.eq("salaryAgainId", again.getId()),
                    Restrictions.eq("isDelete", 0));
            if (items.size() > 0) {
                QxDepartment department = get(QxDepartment.class, Restrictions.eq("id", again.getDepId()));
                set.add(department.getName());
            }
        }
        if (set.size() > 0) return set.toString()+" 部门人员二次分配没有上报";
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                 ");
        sql.append("     ASS.ID,                                              ");
        sql.append("     SAI.SALARY_ITEMS_ID,                                 ");
        sql.append("     SAI.SALARY_ITEMS_NAME,                               ");
        sql.append("     SAI.NEED_AMOUNT                                      ");
        sql.append("   FROM XC_SALARY_AGAIN_ITEMS SAI                         ");
        sql.append("   LEFT JOIN XC_SALARY_AGAIN SAGAIN                       ");
        sql.append("   ON SAI.SALARY_AGAIN_ID = SAGAIN.ID                     ");
        sql.append("   AND SAGAIN.IS_DELETE = 0                               ");
        sql.append("   LEFT JOIN XC_ASSIGNATION_STAFF ASTAFF                  ");
        sql.append("   ON SAI.STAFF_ID = ASTAFF.STAFF_ID                      ");
        sql.append("   AND SAGAIN.SALARY_ASSIGNATION_ID = ASTAFF.SALARY_ASSIGNATION_ID  ");
        sql.append("   LEFT JOIN XC_ASSIGNATION_STAFF_SALARY ASS              ");
        sql.append("   ON ASTAFF.ID            = ASS.ASSIGNATION_STAFF_ID     ");
        sql.append("   AND SAI.SALARY_ITEMS_ID = ASS.IBF_ID                   ");
        sql.append("   AND ASS.IS_DELETE     = 0                              ");
        sql.append("   WHERE SAI.IS_DELETE   = 0                              ");
        sql.append("   AND ASTAFF.SALARY_ASSIGNATION_ID ='"+id+"'             ");
        sql.append("   AND ASTAFF.ID IN('"+StringUtils.join(ids.split(","), "','")+"')      ");
        sql.append("   ORDER BY SAI.STAFF_ID ASC                              ");

        List<?> list = findBySql(sql.toString());
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            AssignationStaffSalary salary = get(AssignationStaffSalary.class, Restrictions.eq("id", obj[0]), Restrictions.eq("isDelete", 0));
            if (null != salary) {
                salary.setAssignationCharge(obj[3].toString());
                assignationStaffSalaryService.update(salary);
            }
        }

        return "success";
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void exportCheckData(String id, String searchData, String conditionData) {
        String sql = salaryAssignationDao.getCheckPeriodData(id, searchData, conditionData);
        List<?> list = findBySql(sql);
        ArrayList<ArrayList> tableData = new ArrayList<ArrayList>();
        for (int i = 0; i < list.size(); i++) {
            ArrayList rowList=new ArrayList();
            Object[] obj = (Object[]) list.get(i);
            rowList.add(i+1);//序号
            rowList.add(obj[2]);// 工号
            rowList.add(obj[3]);// 姓名
            String[] charge = obj[6].toString().split(",");
            String[] addOrLess = obj[10].toString().split(",");
            for (int j = 0, ll = charge.length; j < ll; j++) {
                if (Integer.valueOf(addOrLess[j]) == 2) {
                    Double d = Double.valueOf(charge[j]);
                    rowList.add(d==0?0:"-"+charge[j]);// 薪资项目对应的值
                } else rowList.add(charge[j]);// 薪资项目对应的值
            }
            tableData.add(rowList);
        }

        //表格标题
        String[] tableCaption ={"序号","工号","姓名"};
        if (list.size() > 0) {
            // 取出薪资项目
            Object[] obj = (Object[]) list.get(0);
            String[] items = obj[5].toString().split(",");
            int len1 = tableCaption.length;
            int len2 = items.length;

            // 扩容第一个数组
            tableCaption = Arrays.copyOf(tableCaption, len1 + len2);
            System.arraycopy(items, 0, tableCaption, len1, len2);
        }

        //表格脚部分
        String dcr=ToolUtil.userName();
        Date dcrq=new Date();
        ArrayList footData=new ArrayList();
        footData.add("导出人： "+ ExcelUtil.toString(dcr));
        footData.add("导出日期： "+ ExcelUtil.toString(dcrq));
        ExcelUtil.export("导出薪酬审批数据", null, tableCaption, tableData, footData);
    }

    @Override
    public List<Map<String, Object>> getCurComDepts(String companyId) {
        String userId = LingUtil.userinfo().getId();
        List<?> dataP = findBySql("SELECT D.PARENT,D.ID,D.BARCH_ID,D.NAME,D.FLG FROM QX_DEPARTMENT D " +
                "INNER JOIN QX_USER_DATAUTH UD ON UD.BRANCH_DEP=D.ID AND UD.FLG='1' AND UD.USERID='"+userId+"' " +
                "WHERE D.BARCH_ID='"+companyId+"' AND D.IS_DELETE=0   order by d.description ASC");
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(int k=0,s=dataP.size();k<s;k++){
            Object[] dep=(Object[])dataP.get(k);
            Map<String, Object> map = new HashMap<String, Object>();
            String branchId=dep[2]==null?"":dep[2].toString();
            String parent=dep[0]==null?"":dep[0].toString();
            if("ROOT".equals(parent)){
                map.put("id", dep[1]==null?"":dep[1].toString());
                map.put("pid",branchId);
                map.put("name", dep[3]==null?"":dep[3].toString());
                map.put("text", dep[3]==null?"":dep[3].toString());
                map.put("flg", dep[4]==null?"":dep[4].toString());
                map.put("branchId", branchId);
                map.put("img", "../jsp/basis/dep.png");
                list.add(map);
            }else{
                map.put("id", dep[1]==null?"":dep[1].toString());
                map.put("pid",parent);
                map.put("text", dep[3]==null?"":dep[3].toString());
                map.put("name", dep[3]==null?"":dep[3].toString());
                map.put("flg", dep[4]==null?"":dep[4].toString());
                map.put("branchId", branchId);
                map.put("img", "../jsp/basis/dep.png");
                list.add(map);
            }
        }

        return list;
    }

    @Override
    public String allowIbfEdit(String id) {
        SalaryAssignation assignation = get(SalaryAssignation.class,
                Restrictions.eq("id", id), Restrictions.eq("isDelete", 0));
        if (assignation == null) {
            return "该分配过程不存在";
        }
        assignation.setAllowEdit(1);
        update(assignation);

        return "success";
    }

    @Override
    public String uploadSecondItems(String endSuffix, File uploadFile, String id) throws Exception {
        SalaryAssignation assignation = get(SalaryAssignation.class, Restrictions.eq("id", id),
                Restrictions.eq("isDelete", 0));
        if (assignation == null) throw new Exception("该分配过程不存在，已经被删除");

        // 读取数据
        List<String[]> list = ExcelUtil.readData(0, 2, endSuffix, uploadFile);

        // 导入名单只有表头没有人员信息
        if (list.size() == 1) {
            return "文件导入失败：导入名单中不存在项目信息";
        }

        // 第一行内容
        String[] firstRow = {"工号","薪资项目","值"};
        // 单独把第一行抽取出来
        String[] firstValues = list.get(0);

        for (int i = 0, z = firstRow.length; i < z; i++) {
            if (!firstRow[i].trim().equals(firstValues[i].trim())) {
                return "文件导入失败：导入名单标题存在问题！<br/><font color='red'>" + firstValues[i].trim() + "</font>列必须是 " + firstRow[i].trim();
            }
        }

        DecimalFormat df = new DecimalFormat();

        // 循环遍历存储的Excel值
        for (int i = 1, z = list.size(); i < z; i++) {
            // 组装列
            String[] columns = new String[firstValues.length];
            String[] values = list.get(i);
            // 判断是否整行是空行
            Boolean isEmpty= true;
            for(int q=0;q<values.length;q++){
                if (q < firstValues.length) {
                    columns[q]=values[q].trim();
                    if (!StringUtils.isBlank(columns[q])) {
                        isEmpty = false;
                    }
                } else continue;
            }

            // 存在空白行
            if (isEmpty) {
                // 文档结束
                break;
            }
            for(int k=values.length;k<firstValues.length;k++){// 补充剩余的列值
                columns[k]="";
            }

            // 工号
            if (StringUtils.isBlank(columns[0])) {
                throw new Exception("第"+ (i+1) +"行【工号】不能为空");
            }

            // 薪资项目
            if (StringUtils.isBlank(columns[1])) {
                throw new Exception("第"+ (i+1) +"行【薪资项目】不能为空");
            }

            // 值
            if (!StringUtils.isBlank(columns[2])) {
                try {
                    Double.valueOf(columns[2]);
                } catch (Exception e) {
                    throw new Exception("第"+ (i+1) +"行【实际分配】必须是数字");
                }
            } else throw new Exception("第"+ (i+1) +"行【实际分配】不能为空");

            AssignationStaff staff = get(AssignationStaff.class,
                    Restrictions.eq("salaryAssignationId", assignation.getId()),
                    Restrictions.eq("jobNumber", columns[0]),
                    Restrictions.eq("isDelete", 0));
            if (staff == null) throw new Exception("该职工号"+columns[0]+"员工不存在，已经被删除");
            AssignationStaffSalary staffSalary = get(AssignationStaffSalary.class,
                    Restrictions.eq("assignationStaffId", staff.getId()),
                    Restrictions.eq("ibfName", columns[1]),
                    Restrictions.eq("isDelete", 0));
            if (staffSalary == null) throw new Exception("该职工号"+columns[1]+"薪资项目不存在，已经被删除");
            staffSalary.setAssignationCharge(numberAccuracy(df, staffSalary.getNumberAccuracy(), columns[2]));
            assignationStaffSalaryService.update(staffSalary);
        }

        return "success";
    }

    @Override
    public Map<String, Object> getDeptsData(String id, String searchData) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                                                   ");
        sql.append("   LISTAGG(TT.ITEMSID, ',') WITHIN GROUP(ORDER BY TT.SX ASC, TT.ITEMSID ASC) ITEMSID,   ");
        sql.append("   LISTAGG(TT.ITEMSNAME, ',') WITHIN GROUP(ORDER BY TT.SX ASC, TT.ITEMSID ASC) ITEMSNAME,   ");
        sql.append("   LISTAGG(TT.ASSIGNATION_CHARGE, ',') WITHIN GROUP(ORDER BY TT.SX ASC, TT.ITEMSID ASC) ASSIGNATION_CHARGE,   ");
        sql.append("   MIN(TT.STAFF_IDS) STAFF_IDS,MIN(TT.DEPT_NAME) DEPT_NAME                                  ");
        sql.append("   FROM                                                                                     ");
        sql.append("   (                                                                                        ");
        sql.append("     SELECT COUNT(ASTAFF.STAFF_ID) STAFF_IDS,                                               ");
        sql.append("      ASTAFF.DEPT_ID,                                                                       ");
        sql.append("       MIN(ASS.IBF_ID) ITEMSID,                                                             ");
        sql.append("       MIN(ASS.IBF_NAME) ITEMSNAME,                                                         ");
        sql.append("       MIN(ASS.SX) SX,                                                                      ");
        sql.append("       SUM(ASS.ASSIGNATION_CHARGE) ASSIGNATION_CHARGE,                                      ");
        sql.append("       MIN(ASTAFF.COMPANY_NAME) COMPANY_NAME,                                               ");
        sql.append("       MIN(ASTAFF.DEPT_NAME) DEPT_NAME                                                      ");
        sql.append("     FROM XC_ASSIGNATION_STAFF ASTAFF                                                       ");
        sql.append("     LEFT JOIN XC_ASSIGNATION_STAFF_SALARY ASS                                              ");
        sql.append("     ON ASTAFF.ID                       = ASS.ASSIGNATION_STAFF_ID                          ");
        sql.append("     AND ASS.IS_DELETE                  = 0                                                 ");
        sql.append("     WHERE ASTAFF.SALARY_ASSIGNATION_ID = '"+id+"'                                                ");
        sql.append("     AND ASTAFF.IS_DELETE               = 0                                                 ");
        sql.append("     GROUP BY ASTAFF.DEPT_ID,ASS.IBF_ID                                                     ");
        sql.append("   ) TT                                                                                     ");
        sql.append("   LEFT JOIN HCM2.QX_DEPARTMENT QX                                                          ");
        sql.append("   ON TT.DEPT_ID = QX.ID                                                                    ");

        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        if (mapData != null) {
        }
        sql.append("   GROUP BY TT.DEPT_ID                                                                      ");
        sql.append("   ORDER BY MIN(QX.DESCRIPTION) ASC                                                         ");

        List<?> list = findBySql(sql.toString());
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemsId", obj[0]);
            map.put("itemsName", obj[1]);
            map.put("charge", obj[2]);
            map.put("count", obj[3]);
            map.put("deptName", obj[4]);
            dataList.add(map);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("summary", salaryAssignationDao.getDeptTotalData(id));// 汇总计算

        return map;
    }

}
