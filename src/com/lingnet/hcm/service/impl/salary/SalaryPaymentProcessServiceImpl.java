package com.lingnet.hcm.service.impl.salary;

import java.text.DecimalFormat;
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
import com.lingnet.hcm.dao.salary.SalaryPaymentProcessDao;
import com.lingnet.hcm.entity.salary.PaymentInsurance;
import com.lingnet.hcm.entity.salary.PaymentProcessArea;
import com.lingnet.hcm.entity.salary.PaymentProcessCompany;
import com.lingnet.hcm.entity.salary.PaymentProcessDept;
import com.lingnet.hcm.entity.salary.PaymentProcessFuli;
import com.lingnet.hcm.entity.salary.ProcessPayment;
import com.lingnet.hcm.entity.salary.SalaryPaymentProcess;
import com.lingnet.hcm.service.salary.PaymentInsuranceService;
import com.lingnet.hcm.service.salary.PaymentProcessAreaService;
import com.lingnet.hcm.service.salary.PaymentProcessCompanyService;
import com.lingnet.hcm.service.salary.PaymentProcessDeptService;
import com.lingnet.hcm.service.salary.PaymentProcessFuliService;
import com.lingnet.hcm.service.salary.ProcessPaymentService;
import com.lingnet.hcm.service.salary.SalaryInsuranceService;
import com.lingnet.hcm.service.salary.SalaryPaymentProcessService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

/**
 * 保险缴费过程表
 * @ClassName: SalaryPaymentProcessServiceImpl 
 * @Description: 保险缴费过程表 
 * @author zhanghj
 * @date 2017年4月26日 上午11:31:10 
 *
 */
@Service("salaryPaymentProcessService")
public class SalaryPaymentProcessServiceImpl extends BaseServiceImpl<SalaryPaymentProcess, String>
              implements SalaryPaymentProcessService {

    @Resource(name="paymentProcessCompanyService")
    private PaymentProcessCompanyService paymentProcessCompanyService;
    @Resource(name="paymentProcessAreaService")
    private PaymentProcessAreaService paymentProcessAreaService;
    @Resource(name="paymentProcessFuliService")
    private PaymentProcessFuliService paymentProcessFuliService;
    @Resource(name="paymentProcessDeptService")
    private PaymentProcessDeptService paymentProcessDeptService;
    @Resource(name="processPaymentService")
    private ProcessPaymentService processPaymentService;
    @Resource(name="paymentInsuranceService")
    private PaymentInsuranceService paymentInsuranceService;
    @Resource(name="salaryInsuranceService")
    private SalaryInsuranceService salaryInsuranceService;
    @Resource(name="salaryPaymentProcessDao")
    private SalaryPaymentProcessDao salaryPaymentProcessDao;

    @Override
    public Map<String, Object> getPayMentListData(String companyId, Pager pager) {
        return salaryPaymentProcessDao.getPayMentListData(companyId, pager);
    }

    @Override
    public Map<String, Object> getJfProcessData(String id) {
        return salaryPaymentProcessDao.getJfProcessData(id);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String saveOrUpdate(String formdata) throws Exception {
        Map<String, String> map = JsonUtil.parseProperties(formdata);
        if (map != null) {
            String resultId = "";
            SalaryPaymentProcess process = new SalaryPaymentProcess();
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
                List<SalaryPaymentProcess> list = getList(SalaryPaymentProcess.class,
                        Restrictions.eq("isDelete", 0),
                        Restrictions.eq("name", process.getName()));
                if (list.size() > 0) {
                    return "存在相同的名称"+process.getName();
                }

                // 相同组织、相同地域的计算过程只能有一个
                if (!salaryPaymentProcessDao.getSampleOrgAndAreaData(
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
                List<SalaryPaymentProcess> list = getList(SalaryPaymentProcess.class,
                        Restrictions.eq("isDelete", 0),
                        Restrictions.ne("id", map.get("id")),
                        Restrictions.eq("name", process.getName()));
                if (list.size() > 0) {
                    return "存在相同的名称"+process.getName();
                }
                SalaryPaymentProcess paymentProcess = get(SalaryPaymentProcess.class,
                        Restrictions.eq("isDelete", 0),
                        Restrictions.eq("id", map.get("id")));
                if (paymentProcess == null) {
                    return "该缴费计算过程"+process.getName()+"不存在，已经被删除";
                }

                // 相同组织、相同地域的计算过程只能有一个
                if (!salaryPaymentProcessDao.getSampleOrgAndAreaData(
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
            List<PaymentProcessCompany> companyList = new ArrayList<PaymentProcessCompany>();
            for (String string : companys) {
                PaymentProcessCompany company = new PaymentProcessCompany();
                company.setCompanyId(string);
                company.setSalaryPaymentProcessId(resultId);
                company.setIsDelete(0);
                companyList.add(company);
            }
            paymentProcessCompanyService.saveBatch(companyList);

            // 保险缴费过程缴费地域中间表
            String[] areas = map.get("areaId").split(",");
            List<PaymentProcessArea> areaList = new ArrayList<PaymentProcessArea>();
            for (String string : areas) {
                PaymentProcessArea area = new PaymentProcessArea();
                area.setAreaId(string);
                area.setSalaryPaymentProcessId(resultId);
                area.setIsDelete(0);
                areaList.add(area);
            }
            paymentProcessAreaService.saveBatch(areaList);

            // 保险缴费过程缴费福利中间表
            String[] fulis = map.get("fuliId").split(",");
            List<PaymentProcessFuli> fuliList = new ArrayList<PaymentProcessFuli>();
            for (String string : fulis) {
                PaymentProcessFuli fuli = new PaymentProcessFuli();
                fuli.setFuliId(string);
                fuli.setSalaryPaymentProcessId(resultId);
                fuli.setIsDelete(0);
                fuliList.add(fuli);
            }
            paymentProcessFuliService.saveBatch(fuliList);

            // 保险缴费过程参与组织中间表
            String[] depts = map.get("deptId").split(",");
            List<PaymentProcessDept> deptList = new ArrayList<PaymentProcessDept>();
            for (String string : depts) {
                PaymentProcessDept dept = new PaymentProcessDept();
                dept.setDeptId(string);
                dept.setSalaryPaymentProcessId(resultId);
                dept.setIsDelete(0);
                deptList.add(dept);
            }
            paymentProcessDeptService.saveBatch(deptList);

            // 将全部正式员工纳入计算范畴之内
            if (isAdd) {
                int year = Integer.valueOf(map.get("effectiveYear"));
                int month = Integer.valueOf(map.get("effectiveMonth"));
                String sql = salaryPaymentProcessDao.getCanSbPeoples(map.get("deptId"), "", year, month, "{fuli: \""+map.get("fuliId")+"\"}");
                List select = findBySql(sql);
                if (select.size() > 0) {
                    List<Object[]> listObj = select;
                    for (int i = 0, l= listObj.size(); i < l; i++) {
                        Object[] obj = listObj.get(i);
                        ProcessPayment payment = new ProcessPayment();
                        payment.setSalaryPaymentProcessId(resultId);
                        payment.setStaffId(obj[0].toString());// 员工ID
                        payment.setJfYear(year);
                        payment.setJfMonth(month);
                        payment.setIsPayBack(0);
                        payment.setIsDaijiao(Integer.valueOf(obj[8].toString()));
                        payment.setIsDelete(0);
                        String processId = processPaymentService.save(payment);
                        if (StringUtils.isBlank(processId)) {
                            throw new Exception("员工缴费计算发生异常");
                        }

                        // 福利项目
                        String[] fuli = obj[9].toString().split(",");
                        // 缴费地域
                        String[] areaId = obj[11].toString().split(",");
                        // 缴费单位
                        String[] companyId = obj[12].toString().split(",");
                        if (obj[13] == null) {
                            throw new Exception("员工"+obj[2]+"公司缴费基数不存在");
                        }
                        // 公司缴费基数
                        String[] baseCompany = obj[13].toString().split(",");
                        // 员工缴费基数
                        String[] basePersonal = obj[14].toString().split(",");
                        // 公司缴费
                        String[] sCompany = obj[15].toString().split(",");
                        // 员工缴费
                        String[] sPersonal = obj[16].toString().split(",");
                        for (int j=0, ll = fuli.length; j < ll; j++) {
                            PaymentInsurance insurance = new PaymentInsurance();
                            insurance.setSalaryPaymentProcessId(processId);
                            insurance.setIbfId(fuli[j]);
                            insurance.setArea(areaId[j]);
                            insurance.setJfCompany(companyId[j]);
                            insurance.setBaseCompany(baseCompany[j].trim());
                            insurance.setBasePersonal(basePersonal[j].trim());
                            insurance.setPaymentCompany(sCompany[j].trim());
                            insurance.setPaymentPersonal(sPersonal[j].trim());
                            insurance.setIsDelete(0);
                            String insuranceId = paymentInsuranceService.save(insurance);
                            if (StringUtils.isBlank(insuranceId)) {
                                throw new Exception("员工缴费计算发生异常");
                            }
                        }
                    }
                }
            }
        } else return "内容不能为空";

        return "success";
    }

    @Override
    public String remove(String ids) {
        String[] idArray = ids.split(",");
        for (int i = 0,l=idArray.length; i < l; i++) {
            SalaryPaymentProcess insurance = get(SalaryPaymentProcess.class,
                    Restrictions.eq("id", idArray[i]), Restrictions.eq("isDelete", 0));
            if (insurance != null) {
                insurance.setIsDelete(1);
                update(insurance);
            }
        }

        return "success";
    }

    @Override
    public Map<String, Object> getInsuranceItemsListData(String id, String fuli, String searchData, Pager pager) {
        return salaryPaymentProcessDao.getInsuranceItemsListData(id, fuli, searchData, pager);
    }

    @Override
    public List<Map<String, Object>> getCanJoinStaffsListData(String ids, int year, int month) {
        return salaryPaymentProcessDao.getCanJoinStaffsListData(ids, year, month);
    }

    @Override
    public Map<String, Object> getPersonalToAllocationListData(String id, String ids, int year, int month, Pager pager) {
        return salaryPaymentProcessDao.getPersonalToAllocationListData(id, ids, year, month, pager);
    }

    @Override
    @Transactional(rollbackFor=java.lang.Throwable.class,propagation=Propagation.REQUIRED)
    public String saveOrUpdateToPerson(String id, String griddata, String columns) throws Exception {
        List<Map<String, String>> list = JsonUtil.getMapList(griddata);
        for (int i = 0, l = list.size(); i < l; i++) {
            Map<String, String> m = list.get(i);
            if ("removed".equals(m.get("_state"))) {
                ProcessPayment payment = get(ProcessPayment.class,
                        Restrictions.eq("salaryPaymentProcessId", id),
                        Restrictions.eq("id", m.get("id")),
                        Restrictions.eq("isDelete", 0));
                if (payment != null) {
                    payment.setIsDelete(1);
                    processPaymentService.update(payment);
                }
            } else if ("added".equals(m.get("_state"))) {
                ProcessPayment payment = new ProcessPayment();
                payment.setStaffId(m.get("staffId"));
                payment.setSalaryPaymentProcessId(id);
                payment.setPjgz(m.get("pjgz"));
                String jfDate = m.get("jfDate");
                payment.setJfYear(Integer.valueOf(jfDate.substring(0, jfDate.indexOf("年"))));
                payment.setJfMonth(Integer.valueOf(jfDate.substring(jfDate.indexOf("年") + 1, jfDate.indexOf("月"))));
                payment.setIsDaijiao(Integer.valueOf(m.get("isDaiJiao")));
                payment.setIsPayBack(0);
                payment.setIsDelete(0);
                int isPayBack = Integer.valueOf(m.get("isPayBack"));
                payment.setIsPayBack(isPayBack);
                if (isPayBack == 1) {
                    payment.setPayBackYear(Integer.valueOf(m.get("payBackYear")));
                    payment.setPayBackMonth(Integer.valueOf(m.get("payBackMonth")));

                    /*// 判断缴费年月是否存在 TODO 缴费判断
                    ProcessPayment payDate = processPaymentService.get(ProcessPayment.class,
                            Restrictions.eq("jfYear", payment.getPayBackYear()),
                            Restrictions.eq("jfMonth", payment.getPayBackMonth()),
                            Restrictions.eq("isDelete", 0));
                    if (payDate != null) {
                        throw new Exception(payment.getPayBackYear() + "年" + payment.getPayBackMonth() + "月的缴费过程不存在");
                    }
                    // 判断补缴年月对应的缴费信息是否存在
                    List<ProcessPayment> payments = processPaymentService.getList(ProcessPayment.class,
                            Restrictions.eq("staffId", m.get("staffId")),
                            Restrictions.eq("jfYear", payment.getPayBackYear()),
                            Restrictions.eq("jfMonth", payment.getPayBackMonth()),
                            Restrictions.eq("isDelete", 0));
                    if (payments.size() > 0) {
                        throw new Exception("该员工：" + m.get("name") + payment.getPayBackYear() + "年" + payment.getPayBackMonth() + "月的缴费信息存在");
                    }*/
                }

                // 查找是否存在相同的员工，除了补缴之外
                List<ProcessPayment> payments = processPaymentService.getList(ProcessPayment.class,
                        Restrictions.eq("staffId", m.get("staffId")),
                        Restrictions.eq("salaryPaymentProcessId", id),
                        Restrictions.eq("isPayBack", 1),
                        Restrictions.eq("isDelete", 0));
                if (payments.size() > 0) {
                    throw new Exception("存在相同的员工：" + m.get("name"));
                }

                String processId = processPaymentService.save(payment);
                if (StringUtils.isBlank(processId)) {
                    throw new Exception("保存失败");
                }

                // 遍历相关的福利项目
                List<PaymentInsurance> insurances = new ArrayList<PaymentInsurance>();
                String[] fuliId = columns.split(",");
                for (String key : fuliId) {
                    // 保存参保人员缴费表
                    PaymentInsurance insurance = new PaymentInsurance();
                    insurance.setSalaryPaymentProcessId(processId);
                    insurance.setIbfId(key);
                    String AREA = m.get("AREA" + key);
                    String COMD = m.get("COMD" + key);
                    String COMB = m.get("COMB" + key);
                    String PERB = m.get("PERB" + key);
                    if (StringUtils.isBlank(AREA)) {
                        continue;
                    }
                    insurance.setArea(AREA);
                    insurance.setJfCompany(COMD);
                    insurance.setBaseCompany(COMB);
                    insurance.setBasePersonal(PERB);
                    insurance.setIsDelete(0);
                    insurances.add(insurance);

                    // 保存人员缴费信息
                    paymentInsuranceService.saveBatch(insurances);
                }

            }
        }

        return "success";
    }

    @Override
    public String updateReloadCalculation(String id) {
        List<Map<String, Object>> list = salaryPaymentProcessDao.getAllStaffPaymentsData(id);
        List<PaymentInsurance> objs = new ArrayList<PaymentInsurance>();
        DecimalFormat df = new DecimalFormat();
        for (int i=0, l = list.size(); i < l; i++) {
            Map<String, Object> map = list.get(i);
            String[] ids = map.get("id").toString().split(",");
            String[] payCarryRules = map.get("payCarryRule").toString().split(",");
            String[] xss = map.get("xs").toString().split(",");
            for (int j = 0, ll = ids.length; j < ll; j++) {
                PaymentInsurance insurance = get(PaymentInsurance.class,
                        Restrictions.eq("id", ids[j]),
                        Restrictions.eq("isDelete", 0));
                if (insurance != null) {
                    String[] baseCompany = map.get("baseCompany").toString().split(",");
                    String[] basePersonal = map.get("basePersonal").toString().split(",");
                    String[] sCompany = map.get("sCompany").toString().split(",");
                    String[] sPersonal = map.get("sPersonal").toString().split(",");
                    insurance.setBaseCompany(baseCompany[j]);
                    insurance.setBasePersonal(basePersonal[j]);
                    insurance.setPaymentCompany(salaryInsuranceService.ceilMath(df,
                            Integer.valueOf(payCarryRules[j]), xss[j],
                            Double.valueOf(sCompany[j])));
                    insurance.setPaymentPersonal(salaryInsuranceService.ceilMath(df,
                            Integer.valueOf(payCarryRules[j]), xss[j],
                            Double.valueOf(sPersonal[j])));
                    objs.add(insurance);
                }
            }
        }
        paymentInsuranceService.saveBatch(objs);

        return "success";
    }

    @Override
    public List<Map<String, Object>> getStaffPaymentData(String id, String staffId) {
        return salaryPaymentProcessDao.getStaffPaymentData(id, staffId);
    }

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String saveOrUpdateToPayment(String griddata) throws Exception {
        List<Map<String, String>> listData = JsonUtil.getMapList(griddata);
        DecimalFormat df = new DecimalFormat();
        for (Map<String, String> map : listData) {
            Map<String, Object> data = salaryPaymentProcessDao.getPaymentceilMathData(map.get("id"));
            PaymentInsurance insurance = get(PaymentInsurance.class,
                    Restrictions.eq("id", map.get("id")),
                    Restrictions.eq("isDelete", 0));
            if (insurance == null) {
                throw new Exception("福利项目" + map.get("name") + "缴费信息不存在");
            }
            insurance.setPaymentCompany(salaryInsuranceService.ceilMath(df,
                    Integer.valueOf(data.get("payCarryRule").toString()), data.get("xs").toString(),
                    Double.valueOf(map.get("paymentCompany"))));
            insurance.setPaymentPersonal(salaryInsuranceService.ceilMath(df,
                    Integer.valueOf(data.get("payCarryRule").toString()), data.get("xs").toString(),
                    Double.valueOf(map.get("paymentPersonal"))));
            paymentInsuranceService.update(insurance);
        }

        return "success";
    }

    @Override
    public Map<String, Object> getStaffPaymentHistory(String staffId, int year, Pager pager) {
        return salaryPaymentProcessDao.getStaffPaymentHistory(staffId, year, pager);
    }

}
