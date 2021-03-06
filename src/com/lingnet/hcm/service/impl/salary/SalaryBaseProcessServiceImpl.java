package com.lingnet.hcm.service.impl.salary;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.MapContext;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.salary.SalaryBaseProcessDao;
import com.lingnet.hcm.entity.salary.BaseItems;
import com.lingnet.hcm.entity.salary.BasePayment;
import com.lingnet.hcm.entity.salary.BaseProcessArea;
import com.lingnet.hcm.entity.salary.BaseProcessCompany;
import com.lingnet.hcm.entity.salary.BaseProcessDept;
import com.lingnet.hcm.entity.salary.BaseProcessFuli;
import com.lingnet.hcm.entity.salary.InsuranceItems;
import com.lingnet.hcm.entity.salary.InsuranceMiddle;
import com.lingnet.hcm.entity.salary.MonthSalary;
import com.lingnet.hcm.entity.salary.ProcessPayment;
import com.lingnet.hcm.entity.salary.SalaryBaseProcess;
import com.lingnet.hcm.service.salary.BaseItemsService;
import com.lingnet.hcm.service.salary.BasePaymentService;
import com.lingnet.hcm.service.salary.BaseProcessAreaService;
import com.lingnet.hcm.service.salary.BaseProcessCompanyService;
import com.lingnet.hcm.service.salary.BaseProcessDeptService;
import com.lingnet.hcm.service.salary.BaseProcessFuliService;
import com.lingnet.hcm.service.salary.InsuranceItemsService;
import com.lingnet.hcm.service.salary.SalaryAssignationService;
import com.lingnet.hcm.service.salary.SalaryBaseProcessService;
import com.lingnet.hcm.service.salary.SalaryInsuranceService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

/**
 * 缴费基数过程表
 * @ClassName: SalaryBaseProcessServiceImpl 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年6月19日 下午4:11:12 
 *
 */
@Service("salaryBaseProcessService")
public class SalaryBaseProcessServiceImpl extends BaseServiceImpl<SalaryBaseProcess, String>
              implements SalaryBaseProcessService {

    @Resource(name="baseProcessCompanyService")
    private BaseProcessCompanyService baseProcessCompanyService;
    @Resource(name="baseProcessFuliService")
    private BaseProcessFuliService baseProcessFuliService;
    @Resource(name="baseProcessDeptService")
    private BaseProcessDeptService baseProcessDeptService;
    @Resource(name="baseProcessAreaService")
    private BaseProcessAreaService baseProcessAreaService;
    @Resource(name="basePaymentService")
    private BasePaymentService basePaymentService;
    @Resource(name="baseItemsService")
    private BaseItemsService baseItemsService;
    @Resource(name="salaryAssignationService")
    private SalaryAssignationService salaryAssignationService;
    @Resource(name="salaryInsuranceService")
    private SalaryInsuranceService salaryInsuranceService;
    @Resource(name="insuranceItemsService")
    private InsuranceItemsService insuranceItemsService;
    @Resource(name="salaryBaseProcessDao")
    private SalaryBaseProcessDao salaryBaseProcessDao;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String saveOrUpdate(String formdata) throws Exception {
        Map<String, String> map = JsonUtil.parseProperties(formdata);
        if (map != null) {
            String resultId = "";
            SalaryBaseProcess process = new SalaryBaseProcess();
            process.setName(map.get("name"));
            process.setCompanyId(map.get("companyId"));
            process.setEffectiveYear(Integer.valueOf(map.get("effectiveYear")));
            process.setEffectiveMonth(Integer.valueOf(map.get("effectiveMonth")));
            process.setIsDelete(0);
            process.setNote(map.get("note"));

            // 是否是新增
            Boolean isAdd = false;

            // 新增
            if (StringUtils.isBlank(map.get("id"))) {
                // 是否存在相同的名称
                List<SalaryBaseProcess> list = getList(SalaryBaseProcess.class,
                        Restrictions.eq("isDelete", 0),
                        Restrictions.eq("name", process.getName()));
                if (list.size() > 0) {
                    return "存在相同的名称"+process.getName();
                }

                // 相同组织、相同地域的计算过程只能有一个
                if (!salaryBaseProcessDao.getSampleOrgAndAreaData(
                        "", map.get("deptId"), map.get("areaId"),
                        Integer.valueOf(map.get("effectiveYear")),
                        Integer.valueOf(map.get("effectiveMonth"))).equals(
                        "success")) {
                    return "存在相同的组织、相同的地域";
                }
                resultId = save(process);
                if (StringUtils.isBlank(resultId)) {
                    return "发生异常，保存失败";
                }
                isAdd = true;
            } else {
                // 是否存在相同的名称
                List<SalaryBaseProcess> list = getList(SalaryBaseProcess.class,
                        Restrictions.eq("isDelete", 0),
                        Restrictions.ne("id", map.get("id")),
                        Restrictions.eq("name", process.getName()));
                if (list.size() > 0) {
                    return "存在相同的名称"+process.getName();
                }
                SalaryBaseProcess paymentProcess = get(SalaryBaseProcess.class,
                        Restrictions.eq("isDelete", 0),
                        Restrictions.eq("id", map.get("id")));
                if (paymentProcess == null) {
                    return "该缴费计算过程"+process.getName()+"不存在，已经被删除";
                }

                // 相同组织、相同地域的计算过程只能有一个
                if (!salaryBaseProcessDao.getSampleOrgAndAreaData(
                        map.get("id"), map.get("deptId"), map.get("areaId"),
                        Integer.valueOf(map.get("effectiveYear")),
                        Integer.valueOf(map.get("effectiveMonth"))).equals(
                        "success")) {
                    return "存在相同的组织、相同的地域";
                }
                paymentProcess.copyFrom(process);
                update(paymentProcess);
                resultId = process.getId();
            }

            // 保存保险缴费过程缴费单位中间表
            String[] companys = map.get("companyId").split(",");
            List<BaseProcessCompany> companyList = new ArrayList<BaseProcessCompany>();
            for (String string : companys) {
                BaseProcessCompany company = new BaseProcessCompany();
                company.setCompanyId(string);
                company.setSalaryPaymentProcessId(resultId);
                company.setIsDelete(0);
                companyList.add(company);
            }
            baseProcessCompanyService.saveBatch(companyList);

            // 保险缴费过程缴费地域中间表
            String[] areas = map.get("areaId").split(",");
            List<BaseProcessArea> areaList = new ArrayList<BaseProcessArea>();
            for (String string : areas) {
                BaseProcessArea area = new BaseProcessArea();
                area.setAreaId(string);
                area.setSalaryPaymentProcessId(resultId);
                area.setIsDelete(0);
                areaList.add(area);
            }
            baseProcessAreaService.saveBatch(areaList);

            // 保险缴费过程缴费福利中间表
            String[] fulis = map.get("fuliId").split(",");
            List<BaseProcessFuli> fuliList = new ArrayList<BaseProcessFuli>();
            for (String string : fulis) {
                BaseProcessFuli fuli = new BaseProcessFuli();
                fuli.setFuliId(string);
                fuli.setSalaryPaymentProcessId(resultId);
                fuli.setIsDelete(0);
                fuliList.add(fuli);
            }
            baseProcessFuliService.saveBatch(fuliList);

            // 保险缴费过程参与组织中间表
            String[] depts = map.get("deptId").split(",");
            List<BaseProcessDept> deptList = new ArrayList<BaseProcessDept>();
            for (String string : depts) {
                BaseProcessDept dept = new BaseProcessDept();
                dept.setDeptId(string);
                dept.setSalaryPaymentProcessId(resultId);
                dept.setIsDelete(0);
                deptList.add(dept);
            }
            baseProcessDeptService.saveBatch(deptList);

            // 将全部正式员工纳入计算范畴之内
            if (isAdd) {
                List<InsuranceItems> itemsList = new ArrayList<InsuranceItems>();
                int year = Integer.valueOf(map.get("effectiveYear"));
                int month = Integer.valueOf(map.get("effectiveMonth"));
                String sql = salaryBaseProcessDao.getCanSbPeoples(map.get("deptId"), map.get("areaId"), map.get("fuliId"));
                List select = findBySql(sql);
                if (select.size() > 0) {
                    List<Object[]> listObj = select;
                    Object staff = "";
                    String processId = "";
                    JexlContext jc = new MapContext();
                    for (int i = 0, l= listObj.size(); i < l; i++) {
                        Object[] obj = listObj.get(i);
                        if (!staff.equals(obj[0])) {
                            BasePayment payment = new BasePayment();
                            payment.setSalaryPaymentProcessId(resultId);
                            payment.setStaffId(obj[0].toString());// 员工ID
                            payment.setJfYear(year);
                            payment.setJfMonth(month);
                            payment.setIsDaijiao(0);
                            payment.setIsDelete(0);

                            // 获取平均工资
                            MonthSalary monthSalary = get(MonthSalary.class,
                                    Restrictions.eq("staffId", payment.getStaffId()),
                                    Restrictions.eq("effectiveYear", year),
                                    Restrictions.eq("isDelete", 0));
                            String avgSalary = "0";
                            if (monthSalary != null) avgSalary= monthSalary.getAverageSalary();
                            payment.setPjgz(avgSalary);
                            processId = basePaymentService.save(payment);
                            if (StringUtils.isBlank(processId)) {
                                throw new Exception("员工缴费计算发生异常");
                            }
                            staff = obj[0];
                        }

                        // 福利项目
                        String fuli = obj[3].toString();
                        // 缴费地域
                        String areaName = obj[2].toString();
                        // 公司缴费基数公式
                        String baseCompanyCnt = obj[4].toString();
                        // 员工缴费基数公式
                        String basePersonalCnt = obj[5].toString();

                        // 计算公式
                        jc.set("staffId", staff);
//                        Object bCom = salaryAssignationService.formula(baseCompanyCnt, jc);
//                        Object bPer = salaryAssignationService.formula(basePersonalCnt, jc);
                        Object bCom = 11;
                        Object bPer = 12;
                        BaseItems insurance = new BaseItems();
                        insurance.setBasePaymentId(processId);
                        insurance.setIbfId(fuli);
                        insurance.setArea(areaName);
                        insurance.setBaseCompany(bCom.toString());
                        insurance.setBasePersonal(bPer.toString());
                        insurance.setIsDelete(0);
                        String insuranceId = baseItemsService.save(insurance);
                        if (StringUtils.isBlank(insuranceId)) {
                            throw new Exception("员工缴费计算发生异常");
                        }

                        // 更新缴费基数
                        InsuranceMiddle middle = get(InsuranceMiddle.class, Restrictions.eq("staffId", staff), Restrictions.eq("isDelete", 0));
                        if (middle != null) {
                            InsuranceItems items = get(InsuranceItems.class,
                                    Restrictions.eq("insuranceMiddleId", middle.getId()),
                                    Restrictions.eq("ibfId", insurance.getIbfId()),
                                    Restrictions.eq("isDelete", 0));
                            if (items != null) {
                                items.setBaseCompany(insurance.getBaseCompany());
                                items.setBasePersonal(insurance.getBasePersonal());
                                itemsList.add(items);
                            }
                        }
                    }
                }
                if (itemsList.size() > 0) insuranceItemsService.saveBatch(itemsList);
            }
        } else return "内容不能为空";

        return "success";
    }

    @Override
    public Map<String, Object> getJsBaseSalaryDatas(String companyId, Pager pager) {
        return salaryBaseProcessDao.getJsBaseSalaryDatas(companyId, pager);
    }

    @Override
    public Map<String, Object> getBaseJfProcessData(String id) {
        return salaryBaseProcessDao.getBaseJfProcessData(id);
    }

    @Override
    public Map<String, Object> getJsBaseSalaryListDatas(String id, String fuli,
            String searchData, Pager pager) {
        return salaryBaseProcessDao.getJsBaseSalaryListDatas(id, fuli, searchData, pager);
    }

    @Override
    @Transactional(rollbackFor=java.lang.Throwable.class,propagation=Propagation.REQUIRED)
    public String saveOrUpdateToBasePerson(String id, String griddata, String columns) throws Exception {
        List<Map<String, String>> list = JsonUtil.getMapList(griddata);
        for (int i = 0, l = list.size(); i < l; i++) {
            Map<String, String> m = list.get(i);
            if ("removed".equals(m.get("_state"))) {
                BasePayment payment = get(BasePayment.class,
                        Restrictions.eq("salaryPaymentProcessId", id),
                        Restrictions.eq("id", m.get("id")),
                        Restrictions.eq("isDelete", 0));
                if (payment != null) {
                    payment.setIsDelete(1);
                    basePaymentService.update(payment);
                }
            } else if ("added".equals(m.get("_state"))) {
                BasePayment payment = new BasePayment();
                payment.setStaffId(m.get("staffId"));
                payment.setSalaryPaymentProcessId(id);
                payment.setPjgz(m.get("pjgz"));
                String jfDate = m.get("jfDate");
                payment.setJfYear(Integer.valueOf(jfDate.substring(0, jfDate.indexOf("年"))));
                payment.setJfMonth(Integer.valueOf(jfDate.substring(jfDate.indexOf("年") + 1, jfDate.indexOf("月"))));
                payment.setIsDaijiao(0);
                payment.setIsDelete(0);

                // 查找是否存在相同的员工，除了补缴之外
                List<ProcessPayment> payments = basePaymentService.getList(ProcessPayment.class,
                        Restrictions.eq("staffId", m.get("staffId")),
                        Restrictions.eq("salaryPaymentProcessId", id),
                        Restrictions.eq("isDelete", 0));
                if (payments.size() > 0) {
                    throw new Exception("存在相同的员工：" + m.get("name"));
                }

                String processId = basePaymentService.save(payment);
                if (StringUtils.isBlank(processId)) {
                    throw new Exception("保存失败");
                }

                // 遍历相关的福利项目
                List<BaseItems> insurances = new ArrayList<BaseItems>();
                String[] fuliId = columns.split(",");
                for (String key : fuliId) {
                    // 保存参保人员缴费表
                    BaseItems insurance = new BaseItems();
                    insurance.setBasePaymentId(processId);
                    insurance.setIbfId(key);
                    String AREA = m.get("AREA" + key);
                    String COMB = m.get("COMB" + key);
                    String PERB = m.get("PERB" + key);
                    if (StringUtils.isBlank(AREA)) {
                        continue;
                    }
                    insurance.setArea(AREA);
                    insurance.setBaseCompany(COMB);
                    insurance.setBasePersonal(PERB);
                    insurance.setIsDelete(0);
                    insurances.add(insurance);

                    // 保存人员缴费基数信息
                    baseItemsService.saveBatch(insurances);
                }

            }
        }

        return "success";
    }

    @Override
    public String updateReloadBase(String id) throws Exception {
        String sql = salaryBaseProcessDao.getAllStaffBasesDataSql(id);
        List<?> list = findBySql(sql);
        List<BasePayment> payments = new ArrayList<BasePayment>();
        List<BaseItems> objs = new ArrayList<BaseItems>();
        List<InsuranceItems> itemsList = new ArrayList<InsuranceItems>();
        DecimalFormat df = new DecimalFormat();
        JexlContext jc = new MapContext();
        Object bpId = "";
        String staffId = "";
        for (int i=0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            if (!bpId.equals(obj[7])) {
                BasePayment basePayment = get(BasePayment.class, Restrictions.eq("id", obj[7]), Restrictions.eq("isDelete", 0));
                if (basePayment == null) throw new Exception("发生异常");

                // 获取平均工资
                MonthSalary monthSalary = get(MonthSalary.class,
                        Restrictions.eq("staffId", basePayment.getStaffId()),
                        Restrictions.eq("effectiveYear", basePayment.getJfYear()),
                        Restrictions.eq("isDelete", 0));
                String avgSalary = "0";
                if (monthSalary != null) avgSalary= monthSalary.getAverageSalary();
                basePayment.setPjgz(avgSalary);
                payments.add(basePayment);
                staffId = basePayment.getStaffId();
            }
            bpId = obj[7];
            BaseItems insurance = get(BaseItems.class, Restrictions.eq("id", obj[0]), Restrictions.eq("isDelete", 0));
            if (insurance == null) {
                throw new Exception("发生异常");
            }

            // 公司缴费基数公式
            String baseCompanyCnt = obj[3].toString();
            // 员工缴费基数公式
            String basePersonalCnt = obj[4].toString();

            // 计算公式
            jc.set("staffId", obj[1]);
//            Object bCom = salaryAssignationService.formula(baseCompanyCnt, jc);
//            Object bPer = salaryAssignationService.formula(basePersonalCnt, jc);
            String payCarryRules = obj[5].toString();
            String xss = obj[6].toString();
            insurance.setBaseCompany(salaryInsuranceService.ceilMath(df,
                    Integer.valueOf(payCarryRules), xss,
                    Double.valueOf("1")));
            insurance.setBasePersonal(salaryInsuranceService.ceilMath(df,
                    Integer.valueOf(payCarryRules), xss,
                    Double.valueOf("2")));

            objs.add(insurance);
            // 更新缴费基数
            InsuranceMiddle middle = get(InsuranceMiddle.class, Restrictions.eq("staffId", staffId), Restrictions.eq("isDelete", 0));
            if (middle != null) {
                InsuranceItems items = get(InsuranceItems.class,
                        Restrictions.eq("insuranceMiddleId", middle.getId()),
                        Restrictions.eq("ibfId", insurance.getIbfId()),
                        Restrictions.eq("isDelete", 0));
                if (items != null) {
                    items.setBaseCompany(insurance.getBaseCompany());
                    items.setBasePersonal(insurance.getBasePersonal());
                    itemsList.add(items);
                }
            }
        }
        if (payments.size() > 0) basePaymentService.saveBatch(payments);
        if (objs.size() > 0) baseItemsService.saveBatch(objs);
        if (itemsList.size() > 0) insuranceItemsService.saveBatch(itemsList);

        return "success";
    }

    @Override
    public Map<String, Object> getChoseBasePersonalListData(String id,
            String ids, int year, int month, Pager pager) {
        return salaryBaseProcessDao.getChoseBasePersonalListData(id, ids, year, month, pager);
    }

}
