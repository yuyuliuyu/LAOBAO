package com.lingnet.hcm.service.impl.salary;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.salary.SalaryNjProgramDao;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.entity.salary.SalaryAgeQx;
import com.lingnet.hcm.entity.salary.SalaryAgeXs;
import com.lingnet.hcm.entity.salary.SalaryNjAge;
import com.lingnet.hcm.entity.salary.SalaryNjFormula;
import com.lingnet.hcm.entity.salary.SalaryNjProgram;
import com.lingnet.hcm.entity.salary.SalaryNjTotal;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.hcm.service.salary.SalaryAgeQxService;
import com.lingnet.hcm.service.salary.SalaryAgeXsService;
import com.lingnet.hcm.service.salary.SalaryNjAgeService;
import com.lingnet.hcm.service.salary.SalaryNjFormulaService;
import com.lingnet.hcm.service.salary.SalaryNjProgramService;
import com.lingnet.hcm.service.salary.SalaryNjTotalService;
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

/**
 * 企业年金缴费分配方案
 * @ClassName: SalaryNjProgramServiceImpl 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年6月15日 上午11:37:36 
 *
 */
@Service("salaryNjProgramService")
public class SalaryNjProgramServiceImpl extends BaseServiceImpl<SalaryNjProgram, String>
              implements SalaryNjProgramService {

    @Resource(name="salaryAgeQxService")
    private SalaryAgeQxService salaryAgeQxService;
    @Resource(name="salaryAgeXsService")
    private SalaryAgeXsService salaryAgeXsService;
    @Resource(name="salaryNjAgeService")
    private SalaryNjAgeService salaryNjAgeService;
    @Resource(name="salaryNjFormulaService")
    private SalaryNjFormulaService salaryNjFormulaService;
    @Resource(name="salaryNjTotalService")
    private SalaryNjTotalService salaryNjTotalService;
    @Resource(name="personnelService")
    private PersonnelService personnelService;
    @Resource(name="salaryNjProgramDao")
    private SalaryNjProgramDao salaryNjProgramDao;

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String saveOrUpdate(String companyId, String gridData1,
            String gridData2, String gridData3, String gridData4, String formdata)
            throws Exception {
        List<Map<String , String>> njPrograms = JsonUtil.getMapList(gridData1);
        for (Map<String, String> map : njPrograms) {
            if ("removed".equals(map.get("_state"))) {
                SalaryNjProgram njProgram = get(SalaryNjProgram.class,
                        Restrictions.eq("id", map.get("id")),
                        Restrictions.eq("companyId", companyId),
                        Restrictions.eq("isDelete", 0));
                if (njProgram == null) {
                    throw new Exception("发生异常");
                }
                njProgram.setIsDelete(1);
                update(njProgram);
            } else if ("modified".equals(map.get("_state"))) {
                SalaryNjProgram njProgram = get(SalaryNjProgram.class,
                        Restrictions.eq("id", map.get("id")),
                        Restrictions.eq("companyId", companyId),
                        Restrictions.eq("isDelete", 0));
                if (njProgram == null) {
                    throw new Exception("发生异常");
                }
                njProgram.setDeptLevel(map.get("deptLevel"));
                njProgram.setZjjfbl(map.get("zjjfbl"));
                njProgram.setZjxs(map.get("zjxs"));
                update(njProgram);
            } else if ("added".equals(map.get("_state"))) {
                SalaryNjProgram njProgram = new SalaryNjProgram();
                njProgram.setDeptLevel(map.get("deptLevel"));
                njProgram.setZjjfbl(map.get("zjjfbl"));
                njProgram.setZjxs(map.get("zjxs"));
                njProgram.setCompanyId(companyId);
                njProgram.setIsDelete(0);
                String id = save(njProgram);
                if (StringUtils.isBlank(id)) {
                    throw new Exception("保存失败");
                }
            }
        }

        List<Map<String , String>> salaryAgeQxs = JsonUtil.getMapList(gridData2);
        for (Map<String, String> map : salaryAgeQxs) {
            if ("removed".equals(map.get("_state"))) {
                SalaryAgeQx njProgram = get(SalaryAgeQx.class,
                        Restrictions.eq("id", map.get("id")),
                        Restrictions.eq("companyId", companyId),
                        Restrictions.eq("isDelete", 0));
                if (njProgram == null) {
                    throw new Exception("发生异常");
                }
                njProgram.setIsDelete(1);
                salaryAgeQxService.update(njProgram);
            } else if ("modified".equals(map.get("_state"))) {
                SalaryAgeQx njProgram = get(SalaryAgeQx.class,
                        Restrictions.eq("id", map.get("id")),
                        Restrictions.eq("companyId", companyId),
                        Restrictions.eq("isDelete", 0));
                if (njProgram == null) {
                    throw new Exception("发生异常");
                }
                njProgram.setSexMan(Integer.valueOf(map.get("sexMan")));
                njProgram.setSexWoman(Integer.valueOf(map.get("sexWoman")));
                njProgram.setZjxs(map.get("zjxs"));
                salaryAgeQxService.update(njProgram);
            } else if ("added".equals(map.get("_state"))) {
                SalaryAgeQx njProgram = new SalaryAgeQx();
                njProgram.setSexMan(Integer.valueOf(map.get("sexMan")));
                njProgram.setSexWoman(Integer.valueOf(map.get("sexWoman")));
                njProgram.setZjxs(map.get("zjxs"));
                njProgram.setCompanyId(companyId);
                njProgram.setIsDelete(0);
                String id = salaryAgeQxService.save(njProgram);
                if (StringUtils.isBlank(id)) {
                    throw new Exception("保存失败");
                }
            }
        }

        List<Map<String , String>> salaryAgeXss = JsonUtil.getMapList(gridData3);
        for (Map<String, String> map : salaryAgeXss) {
            if ("removed".equals(map.get("_state"))) {
                String[] ids = map.get("id").split(",");
                for (String id : ids) {
                    SalaryAgeXs njProgram = get(SalaryAgeXs.class,
                            Restrictions.eq("id", id),
                            Restrictions.eq("companyId", companyId),
                            Restrictions.eq("isDelete", 0));
                    if (njProgram == null) {
                        throw new Exception("发生异常");
                    }
                    njProgram.setIsDelete(1);
                    salaryAgeXsService.update(njProgram);
                }
            } else if ("modified".equals(map.get("_state"))) {
                String[] ids = map.get("id").split(",");
                SalaryAgeXs njProgram = get(SalaryAgeXs.class,
                        Restrictions.eq("id", ids[0]),
                        Restrictions.eq("companyId", companyId),
                        Restrictions.eq("isDelete", 0));
                if (njProgram == null) {
                    throw new Exception("发生异常");
                }
                njProgram.setZgnlMin(Integer.valueOf(map.get("sexManLow")));
                njProgram.setZgnlMax(Integer.valueOf(map.get("sexManHigh")));
                njProgram.setZjxs(map.get("zjxs"));
                salaryAgeXsService.update(njProgram);

                SalaryAgeXs njProgram2 = get(SalaryAgeXs.class,
                        Restrictions.eq("id", ids[1]),
                        Restrictions.eq("companyId", companyId),
                        Restrictions.eq("isDelete", 0));
                if (njProgram2 == null) {
                    throw new Exception("发生异常");
                }
                njProgram2.setZgnlMin(Integer.valueOf(map.get("sexWomanLow")));
                njProgram2.setZgnlMax(Integer.valueOf(map.get("sexWomanHigh")));
                njProgram2.setZjxs(map.get("zjxs"));
                salaryAgeXsService.update(njProgram2);
            } else if ("added".equals(map.get("_state"))) {
                SalaryAgeXs njProgram = new SalaryAgeXs();
                njProgram.setZgnlMin(Integer.valueOf(map.get("sexManLow")));
                njProgram.setZgnlMax(Integer.valueOf(map.get("sexManHigh")));
                njProgram.setCompanyId(companyId);
                njProgram.setZjxs(map.get("zjxs"));
                njProgram.setIsDelete(0);
                njProgram.setSex(0);
                String id = salaryAgeXsService.save(njProgram);
                if (StringUtils.isBlank(id)) {
                    throw new Exception("保存失败");
                }
                SalaryAgeXs njProgram2 = new SalaryAgeXs();
                njProgram2.setZgnlMin(Integer.valueOf(map.get("sexWomanLow")));
                njProgram2.setZgnlMax(Integer.valueOf(map.get("sexWomanHigh")));
                njProgram2.setCompanyId(companyId);
                njProgram2.setZjxs(map.get("zjxs"));
                njProgram2.setIsDelete(0);
                njProgram2.setSex(1);
                String id2 = salaryAgeXsService.save(njProgram2);
                if (StringUtils.isBlank(id2)) {
                    throw new Exception("保存失败");
                }
            }
        }

        List<Map<String , String>> salaryAgeDates = JsonUtil.getMapList(gridData4);
        for (Map<String, String> map : salaryAgeDates) {
            SalaryNjAge age = JsonUtil.toObject(JsonUtil.Encode(map), SalaryNjAge.class);
            if ("removed".equals(map.get("_state"))) {
                SalaryNjAge njProgram = get(SalaryNjAge.class,
                        Restrictions.eq("id", map.get("id")),
                        Restrictions.eq("companyId", companyId),
                        Restrictions.eq("isDelete", 0));
                if (njProgram == null) {
                    throw new Exception("发生异常");
                }
                njProgram.setIsDelete(1);
                salaryNjAgeService.update(njProgram);
            } else if ("modified".equals(map.get("_state"))) {
                SalaryNjAge njProgram = get(SalaryNjAge.class,
                        Restrictions.eq("id", map.get("id")),
                        Restrictions.eq("companyId", companyId),
                        Restrictions.eq("isDelete", 0));
                if (njProgram == null) {
                    throw new Exception("发生异常");
                }
                njProgram.copyFrom(age);
                salaryNjAgeService.update(njProgram);
            } else if ("added".equals(map.get("_state"))) {
                age.setCompanyId(companyId);
                age.setIsDelete(0);
                String id = salaryNjAgeService.save(age);
                if (StringUtils.isBlank(id)) {
                    throw new Exception("保存失败");
                }
            }
        }

        Map<String, String> map = JsonUtil.parseProperties(formdata);
        if (map != null) {
            SalaryNjTotal njTotal = get(SalaryNjTotal.class,
                    Restrictions.eq("id", map.get("id")),
                    Restrictions.eq("isDelete", 0));
            if (njTotal != null) {
                njTotal.setQyjfbl(map.get("qyjfbl"));
                salaryNjTotalService.update(njTotal);
            } else {
                SalaryNjTotal njTotalNew = new SalaryNjTotal();
                njTotalNew.setCompanyId(companyId);
                Branch b = get(Branch.class, Restrictions.eq("id", companyId));
                String companyName = "";
                if (b != null) {
                    companyName = b.getFzx();
                }
                njTotalNew.setCompanyName(companyName);
                // TODO
//                njTotalNew.setYear(companyName);
                njTotalNew.setQyjfbl(map.get("qyjfbl"));
                njTotalNew.setIsDelete(0);
                String id = salaryNjTotalService.save(njTotalNew);
                if (StringUtils.isBlank(id)) {
                    throw new Exception("保存失败");
                }
            }
        }

        return "success";
    }

    @Override
    public Map<String, Object> getNjListData(String searchData, String companyId, Pager pager) {
        return salaryNjProgramDao.getNjListData(searchData, companyId, pager);
    }

    @Override
    public List<SalaryNjProgram> getZjjfblData(String companyId) {
        List<SalaryNjProgram> list = getOrderList(SalaryNjProgram.class, Order.asc("createDate"),
                Restrictions.eq("companyId", companyId),
                Restrictions.eq("isDelete", 0));

        return list;
    }

    @Override
    public List<SalaryAgeQx> getNlqxxsData(String companyId) {
        List<SalaryAgeQx> list = getOrderList(SalaryAgeQx.class, Order.desc("sexMan"),
                Restrictions.eq("companyId", companyId),
                Restrictions.eq("isDelete", 0));

        return list;
    }

    @Override
    public List<Map<String, Object>> getNlxsData(String companyId) {
        return salaryNjProgramDao.getNlxsData(companyId);
    }

    @Override
    public List<SalaryNjAge> getNlJzDateData(String companyId) {
        List<SalaryNjAge> list = getOrderList(SalaryNjAge.class, Order.asc("createDate"),
                Restrictions.eq("companyId", companyId),
                Restrictions.eq("isDelete", 0));

        return list;
    }

    @Override
    public String updateInit(String companyId, String qyjfbl) {
        deleteByCriteria(SalaryNjFormula.class, Restrictions.eq("companyId", companyId));
        String sql = salaryNjProgramDao.getAllStaffListData(companyId);
        List<?> list = findBySql(sql);
        if (list.size() == 0) return "没有员工可以计算";
        List<SalaryNjFormula> formulas = new ArrayList<SalaryNjFormula>();
        DecimalFormat df = new DecimalFormat("0.00");
        for (int i = 0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            SalaryNjFormula formula = new SalaryNjFormula();
            formula.setCompanyId(obj[6]==null?"":obj[6].toString());
            formula.setCompanyName(obj[7]==null?"":obj[7].toString());
            formula.setStaffId(obj[0]==null?"":obj[0].toString());
            formula.setStaffName(obj[1]==null?"":obj[1].toString());
            formula.setLastYearTotal(obj[16]==null?"0":obj[16].toString());// 上年度月均工资总额
            formula.setDeptLevel(obj[5]==null?"":obj[5].toString());// 职务类别
            formula.setSflb("");// 身份类别
            formula.setIsStop(0);// 停止缴费
            formula.setEndAge(obj[10]==null?"0":obj[10].toString());// 截至XXX年龄
            formula.setJzsnmnl(obj[11]==null?"0":obj[11].toString());// 截至上年末年龄
            formula.setQxxjfzjbl(obj[12]==null?"0":obj[12].toString());// 倾斜性缴费职级比例
            formula.setQxxjfnlxs(obj[13]==null?"0":obj[13].toString());// 倾斜性缴费年龄系数
            String qxxjfje = "0";
            if (formula.getIsStop() != 1) {
                qxxjfje = df.format(Double.valueOf(formula.getLastYearTotal()) * Double.valueOf(formula.getQxxjfzjbl()) * Double.valueOf(formula.getQxxjfnlxs()));
            }
            formula.setQxxjfje(qxxjfje);// 倾斜性缴费金额
            formula.setJbjfzjxs(obj[14]==null?"0":obj[14].toString());// 基本缴费职级系数
            formula.setJbjfnlxs(obj[15]==null?"0":obj[15].toString());// 基本缴费年龄系数
            formula.setJbjfje("0");// 基本缴费金额
            formula.setTotalMoney("0");// 合计月缴费金额
            formula.setIsDelete(0);
            formulas.add(formula);
        }
        if (formulas.size() > 0) salaryNjFormulaService.saveBatch(formulas);
        else return "不存在可以计算的员工信息";

        return "success";
    }

    @Override
    public String updateReCalculate(String companyId, String qyjfbl) {
        String sql = salaryNjProgramDao.getAllStaffListDataForRe(companyId);
        List<?> list = findBySql(sql);
        if (list.size() == 0) return "没有员工可以计算";
        List<SalaryNjFormula> formulas = new ArrayList<SalaryNjFormula>();
        DecimalFormat df = new DecimalFormat("0.00");
        for (int i = 0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            SalaryNjFormula formula = get(SalaryNjFormula.class, Restrictions.eq("id", obj[0]), Restrictions.eq("isDelete", 0));
            if (formula == null) {
                return "第"+(i+1)+"个员工不存在";
            }
            formula.setEndAge(obj[10]==null?"0":obj[10].toString());// 截至XXX年龄
            formula.setJzsnmnl(obj[11]==null?"0":obj[11].toString());// 截至上年末年龄
            formula.setQxxjfzjbl(obj[12]==null?"0":obj[12].toString());// 倾斜性缴费职级比例
            formula.setQxxjfnlxs(obj[13]==null?"0":obj[13].toString());// 倾斜性缴费年龄系数
            String qxxjfje = "0";
            if (formula.getIsStop() != 1) {
                qxxjfje = df.format(Double.valueOf(formula.getLastYearTotal()) * Double.valueOf(formula.getQxxjfzjbl()) * Double.valueOf(formula.getQxxjfnlxs()));
            }
            formula.setQxxjfje(qxxjfje);// 倾斜性缴费金额
            formula.setJbjfzjxs(obj[14]==null?"0":obj[14].toString());// 基本缴费职级系数
            formula.setJbjfnlxs(obj[15]==null?"0":obj[15].toString());// 基本缴费年龄系数
            formula.setJbjfje("0");// 基本缴费金额
            formula.setTotalMoney("0");// 合计月缴费金额
            formula.setIsDelete(0);
            formulas.add(formula);
        }
        if (formulas.size() > 0) salaryNjFormulaService.saveBatch(formulas);
        else return "不存在可以计算的员工信息";

        String totalSql = salaryNjProgramDao.getTotalDatas(companyId);
        List<?> totalList = findBySql(totalSql);
        String lastYearTotal = "0";
        String qxxjfje = "0";
        String jbjfzjxs = "0";
        String jbjfnlxs = "0";
        if (totalList.size() > 0) {
            Object[] obj = (Object[]) totalList.get(0);
            lastYearTotal = obj[0].toString();
            qxxjfje = obj[3].toString();
            jbjfzjxs = obj[4].toString();
            jbjfnlxs = obj[5].toString();
        }

        // 计算基本缴费金额、合计月缴费金额
        List<SalaryNjFormula> formulas2 = getList(SalaryNjFormula.class,
                Restrictions.eq("companyId", companyId),
//                Restrictions.eq("year", companyId),
                Restrictions.eq("isDelete", 0));
        for (int i = 0, l = formulas2.size(); i < l; i++) {
            SalaryNjFormula formula = formulas2.get(i);
            Double chu = (Double.valueOf(jbjfzjxs) + Double.valueOf(jbjfnlxs));
            Double b = chu==0? 0 : (Double.valueOf(lastYearTotal) * Double.valueOf(qyjfbl) - Double.valueOf(qxxjfje))
                    * (Double.valueOf(formula.getJbjfzjxs()) + Double.valueOf(formula.getJbjfnlxs()))
                    / chu;
            formula.setJbjfje(df.format(b));
            formula.setTotalMoney(df.format(Double.valueOf(formula.getQxxjfje()) + Double.valueOf(formula.getJbjfje())));
        }
        if (formulas2.size() > 0) salaryNjFormulaService.saveBatch(formulas2);
        else return "不存在可以计算的员工信息";

        return "success";
    }

    @Override
    public Map<String, Object> getNjTotalListData(String companyId) {
        String sql = salaryNjProgramDao.getTotalDatas(companyId);
        List<?> list = findBySql(sql);
        Map<String, Object> map = new HashMap<String, Object>();
        DecimalFormat df = new DecimalFormat("0.0000");
        if (list.size() > 0) {
            Object[] obj = (Object[]) list.get(0);
            map.put("id", obj[8]);
            map.put("companyId", obj[9]);
            map.put("compnayName", obj[10]);
            map.put("qyjfbl", obj[11]);
            map.put("cjnjjhrs", obj[12]);
            map.put("ztjfrs", obj[13]);
            map.put("sndqyygzze", obj[0]);
            map.put("qyjfze", obj[7]);
            map.put("yqxxjfze", obj[3]);
            map.put("yjbjfze", obj[6]);
            Double sndqyygzze = Double.valueOf(map.get("sndqyygzze").toString());
            map.put("qxxjfzb", df.format(sndqyygzze==0? 0: Double.valueOf(map.get("yqxxjfze").toString())/ sndqyygzze));
            map.put("jbjfzb", df.format(sndqyygzze==0? 0: Double.valueOf(map.get("yjbjfze").toString())/ sndqyygzze));
        }

        return map;
    }

    @Override
    public String saveOrUpdateNjTotal(String griddata, String companyId) throws Exception {
        List<Map<String, String>> groupData = JsonUtil.getMapList(griddata);
        for (int i = 0, l = groupData.size(); i < l; i++) {
            Map<String,  String> map = groupData.get(i);
            SalaryNjFormula njFormula = JsonUtil.toObject(JsonUtil.Encode(map), SalaryNjFormula.class);
            if ("removed".equals(map.get("_state"))) {
                SalaryNjFormula formula = get(SalaryNjFormula.class,
                        Restrictions.eq("id", map.get("id")), Restrictions.eq("isDelete", 0));
                // 判断是否为空
                if (null != formula) {
                    formula.setIsDelete(1);
                    salaryNjFormulaService.update(formula);
                }
            } else if ("modified".equals(map.get("_state"))) {
                SalaryNjFormula formula = get(SalaryNjFormula.class,
                        Restrictions.eq("id", map.get("id")), Restrictions.eq("isDelete", 0));
                // 判断是否为空
                if (null != formula) {
                    formula.copyFrom(njFormula);
                    salaryNjFormulaService.update(formula);
                    BasicInformation information = get(BasicInformation.class,
                            Restrictions.eq("id", formula.getStaffId()),
                            Restrictions.eq("isDelete", 0));
                    if (information != null) {
                        information.setAnnuityType(formula.getDeptLevel());
                        information.setAnnuityState(formula.getIsStop()+"");
                        personnelService.update(information);
                    }
                } else throw new Exception("保存失败："+map.get("staffName") +" 分组名称已经重复!");
            } else if ("added".equals(map.get("_state"))) {
                
            }
        }

        return "success";
    }

    @Override
    public Map<String, Object> getDeptLevelData(String id, String deptLevel, String companyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        SalaryNjProgram njProgram = get(SalaryNjProgram.class, Restrictions.eq("deptLevel", deptLevel),
                Restrictions.eq("companyId", companyId), Restrictions.eq("isDelete", 0));
        String zjjfbl = "0";
        String zjxs = "0";
        if (njProgram != null) {
            zjjfbl = njProgram.getZjjfbl();
            zjxs = njProgram.getZjxs();
        }
        map.put("zjjfbl", zjjfbl);
        map.put("zjxs", zjxs);

        return map;
    }

}
