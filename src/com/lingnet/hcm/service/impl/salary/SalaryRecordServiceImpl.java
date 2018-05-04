package com.lingnet.hcm.service.impl.salary;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.salary.SalaryItemsDao;
import com.lingnet.hcm.dao.salary.SalaryRecordDao;
import com.lingnet.hcm.entity.DeptPosition;
import com.lingnet.hcm.entity.PostPosition;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.entity.person.Change;
import com.lingnet.hcm.entity.salary.SalaryAccount;
import com.lingnet.hcm.entity.salary.SalaryDeptDictionary;
import com.lingnet.hcm.entity.salary.SalaryGroup;
import com.lingnet.hcm.entity.salary.SalaryHistory;
import com.lingnet.hcm.entity.salary.SalaryItems;
import com.lingnet.hcm.entity.salary.SalaryPersonal;
import com.lingnet.hcm.entity.salary.SalaryRecord;
import com.lingnet.hcm.entity.salary.SalaryStandHistory;
import com.lingnet.hcm.entity.salary.SalaryValue;
import com.lingnet.hcm.service.personnel.ChangeService;
import com.lingnet.hcm.service.salary.SalaryAccountService;
import com.lingnet.hcm.service.salary.SalaryHistoryService;
import com.lingnet.hcm.service.salary.SalaryPersonalService;
import com.lingnet.hcm.service.salary.SalaryRecordService;
import com.lingnet.hcm.service.salary.SalaryStandHistoryService;
import com.lingnet.hcm.service.salary.SalaryValueService;
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.qxgl.entity.QxDepartment;
import com.lingnet.util.ExcelUtil;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;
import com.lingnet.util.ToolUtil;

/**
 * 工资档案
 * @ClassName: SalaryRecordDao 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年3月23日 下午5:12:18 
 *
 */
@Service("salaryRecordService")
public class SalaryRecordServiceImpl extends BaseServiceImpl<SalaryRecord, String>
              implements SalaryRecordService {

    @Resource(name="salaryPersonalService")
    private SalaryPersonalService salaryPersonalService;
    @Resource(name="salaryValueService")
    private SalaryValueService salaryValueService;
    @Resource(name="salaryHistoryService")
    private SalaryHistoryService salaryHistoryService;
    @Resource(name="salaryAccountService")
    private SalaryAccountService salaryAccountService;
    @Resource(name="changeService")
    private ChangeService changeService;
    @Resource(name="salaryStandHistoryService")
    private SalaryStandHistoryService salaryStandHistoryService;
    @Resource(name="salaryRecordDao")
    private SalaryRecordDao salaryRecordDao;
    @Resource(name="salaryItemsDao")
    private SalaryItemsDao salaryItemsDao;

    @Override
    public Map<String, Object> getStaffSalaryListData(String id, String deptId, String searchData, Pager pager) {
        return salaryRecordDao.getStaffSalaryListData(id, deptId, searchData, pager);
    }

    @Override
    public Map<String, Object> getStaffSalaryData(String id, String depId, String recordId) {
        return salaryRecordDao.getStaffSalaryData(id, depId, recordId);
    }

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String saveOrUpdate(String formdata, String griddata, String standardData, int isChange, int isGridChange) throws Exception {
        Map<String, String> map = JsonUtil.parseProperties(formdata);
        SalaryRecord record = new SalaryRecord();
        record.setStaffId(map.get("id"));
        record.setIsBzgs(Integer.valueOf(map.get("isBzgs")));
        record.setIsSpecialHour(Integer.valueOf(map.get("isSpecialHour")));
        record.setFilmName(map.get("filmName"));
        record.setDeptname(map.get("deptname"));
        record.setKqname(map.get("kqname"));
        record.setClassNo(map.get("classNo"));
        record.setPost(map.get("post"));
        record.setSpecificPost(map.get("specificPost"));
        record.setSalaryPost(map.get("salaryPost"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        record.setGdDate(sdf.parse(map.get("gdDate")));
        SalaryDeptDictionary dictionary = get(SalaryDeptDictionary.class, Restrictions.eq("id", map.get("salaryPost")));
        if (dictionary != null) {
            record.setSalaryPostName(dictionary.getName());
        }
        record.setIsJz(Integer.valueOf(map.get("isJz")));
        record.setFpqx(Integer.valueOf(map.get("fpqx")));
        record.setZfxs(Integer.valueOf(map.get("zfxs")));
        record.setGzxs(Integer.valueOf(map.get("gzxs")));
        record.setKjgl(Integer.valueOf(map.get("kjgl")));
        record.setKqdept(map.get("kqdept"));
        record.setSpecialMark(StringUtils.isBlank(map.get("specialMark"))? -1 : Integer.valueOf(map.get("specialMark")));
        SalaryRecord record2 = get(SalaryRecord.class, Restrictions.eq("staffId", record.getStaffId()), Restrictions.eq("id", map.get("recordId")));
        String recordMainId = "";
        if (record2 == null) {
            recordMainId = save(record);
            if (StringUtils.isBlank(recordMainId)) {
                return "发生异常，保存失败";
            }
        } else {
            record2.copyFrom(record);
            update(record2);
            recordMainId = record2.getId();
        }

        Boolean addSalary = false;
        String empChangeId = "";// 异动表ID
        // 判断是否需要记录异动信息
        if (!(isChange == 0 && isGridChange == 0)) {
            BasicInformation information = get(BasicInformation.class,
                    Restrictions.eq("id", map.get("id")), Restrictions.eq("isDelete", 0));
            if (information != null) {
                Change change = new Change();
                change.setJobNumber(information.getJobNumber());
                change.setChangeType(8);// 换部门
                change.setChangedate(new Date());
                change.setChBeginDate(new Date());
                change.setChEndDate(new Date());
                change.setChangeCause(2);// 其他
                change.setDepbeginDate(new Date());
                change.setPostbeginDate(new Date());

                String filmId = "";
                String deptId = "";
                String spPostId = "";
                String salaryDeptId = "";
                if (record2 == null) {
                    filmId = map.get("filmName");
                    deptId = map.get("deptname");
                    spPostId = map.get("specificPost");
                    salaryDeptId = map.get("salaryPost");
                } else {
                    filmId = record2.getFilmName();
                    deptId = record2.getDeptname();
                    spPostId = record2.getSpecificPost();
                    salaryDeptId = record2.getSalaryPost();
                }
                Branch branch = get(Branch.class, Restrictions.eq("id", filmId));
                if (branch != null)
                    change.setFilm(branch.getFzx());
                QxDepartment department = get(QxDepartment.class, Restrictions.eq("id", deptId));
                if (department !=null) change.setDep(department.getName());
                // TODO 原岗位
                DeptPosition deptPosition = get(DeptPosition.class, Restrictions.eq("id", spPostId));// 具体岗位
                if (deptPosition != null) change.setSpPost(deptPosition.getPositionName());
                change.setJobLevel(information.getJobLevel());
                change.setJgbz(information.getJgbz());
                change.setEmpType(information.getEmpType());
                change.setOnJob(information.getOnJob());
                change.setOnPost(information.getOnPost());

                SalaryDeptDictionary deptDictionary = get(SalaryDeptDictionary.class,
                        Restrictions.eq("id", salaryDeptId), Restrictions.eq("isDelete", 0));
                if (deptDictionary != null) 
                    change.setSalaryPostName(deptDictionary.getName());

                // 现任职
                change.setFilm2(record.getFilmName());
                change.setDep2(record.getDeptname());
                change.setSpPost2(record.getSpecificPost());
                change.setSpanPost2(record.getPost());
                change.setJobLevel2(information.getJobLevel());
                change.setJgbz2(information.getJgbz());

                Branch branch2 = get(Branch.class, Restrictions.eq("id", record.getFilmName()));
                if (branch2 != null)
                    change.setFilmName2(branch2.getFzx());
                QxDepartment department2 = get(QxDepartment.class, Restrictions.eq("id", record.getDeptname()));
                if (department2 !=null) change.setDepName2(department2.getName());
                DeptPosition deptPosition2 = get(DeptPosition.class, Restrictions.eq("id", record.getSpecificPost()));// 具体岗位
                if (deptPosition2 != null) change.setSpPostName2(deptPosition2.getPositionName());
                PostPosition postPosition2 = get(PostPosition.class, Restrictions.eq("id", record.getPost()));// 标准岗位
                if (postPosition2 != null) change.setSpanPostName2(postPosition2.getPositionName());
                change.setEmpType2(information.getEmpType());
                change.setOnJob2(information.getOnJob());
                change.setOnPost2(information.getOnPost());
                SalaryDeptDictionary deptDictionary2 = get(SalaryDeptDictionary.class,
                        Restrictions.eq("id", record.getSalaryPost()), Restrictions.eq("isDelete", 0));
                if (deptDictionary2 != null) 
                    change.setSalaryPostName2(deptDictionary2.getName());
                empChangeId = changeService.save(change);
                if (StringUtils.isBlank(empChangeId)) {
                    throw new Exception("异动数据保存失败");
                }
                addSalary = true;
            } else {
                throw new Exception("该员工不存在");
            }
        }

        List<Map<String, String>> gridAccountList = JsonUtil.getMapList(griddata);
        for (int i=0, l = gridAccountList.size(); i< l; i++) {
            Map<String, String> m = gridAccountList.get(i);
            SalaryAccount salaryValue = JsonUtil.toObject(JsonUtil.Encode(m), SalaryAccount.class);
            if ("removed".equals(m.get("_state"))) {
                SalaryAccount value = salaryAccountService.get(SalaryAccount.class, Restrictions.eq("id", salaryValue.getId()),
                        Restrictions.eq("isDelete", 0));
                if (value != null) {
                    value.setIsDelete(1);
                    salaryAccountService.update(value);
                }
            } else if ("modified".equals(m.get("_state"))) {
                SalaryAccount value = salaryAccountService.get(SalaryAccount.class, Restrictions.eq("id", salaryValue.getId()),
                        Restrictions.eq("isDelete", 0));
                if (value != null) {
                    value.copyFrom(salaryValue);
                    salaryAccountService.update(value);
                } else throw new Exception("该账号："+m.get("account") + "已经被删除!");
            } else if ("added".equals(m.get("_state"))) {
                salaryValue.setStaffId(record.getStaffId());
                salaryValue.setRecordMainId(recordMainId);
                salaryValue.setIsDelete(0);
                String result = salaryAccountService.save(salaryValue);
                if (StringUtils.isBlank(result)) {
                    throw new Exception("发生异常，保存失败!");
                }
            }
        }

        // 标准数据
        Map<String, String> standMapData = JsonUtil.parseProperties(standardData);
        List<SalaryStandHistory> standHistories = new ArrayList<SalaryStandHistory>();
        for (Entry<String, String> entry : standMapData.entrySet()) {
            Map<String, String> mapData = JsonUtil.parseProperties(entry.getValue());
            SalaryValue value = salaryAccountService.get(SalaryValue.class, Restrictions.eq("id", mapData.get("staticId")));
            if (value != null) {
                if (value.getIsDelete() == 1) throw new Exception("薪酬标准："+mapData.get("salaryName") + "已经被删除!");
                if (addSalary) {// 增加异动信息
                    SalaryStandHistory history = new SalaryStandHistory();
                    history.setStaffId(record.getStaffId());
                    history.setRecordMainId(recordMainId);
                    history.setEmpChangeId(empChangeId);
                    history.setIbfId(mapData.get("staticId"));
                    history.setItemsName(mapData.get("salaryName"));
                    history.setAdjustFront(value.getStaticValue());
                    history.setAdjustNext(mapData.get("staticValue"));
                    standHistories.add(history);
                }
                value.setStaticValue(mapData.get("staticValue").trim());
                salaryValueService.update(value);
            } else {
                SalaryValue salaryValue = new SalaryValue();
                salaryValue.setStaffId(record.getStaffId());
                salaryValue.setSalaryGroupId(mapData.get("groupId"));
                salaryValue.setSalaryItemsId(mapData.get("itemId"));
                salaryValue.setStaticValue(mapData.get("staticValue").trim());
                salaryValue.setRecordMainId(recordMainId);
                salaryValue.setIsDelete(0);
                String result = salaryValueService.save(salaryValue);
                if (StringUtils.isBlank(result)) {
                    throw new Exception("发生异常，保存失败!");
                }
            }
        }

        // 保存标准历史
        if (standHistories.size() > 0) salaryStandHistoryService.saveBatch(standHistories);

        return "success";
    }

    @Override
    public List<String[]> getStandardItems(String id, String recordId) {
        List<String[]> items = new ArrayList<String[]>();
        items.add(new String[] {"固定薪资项目", "../salary/salary_record!getSalaryStandardItems.action?type=1&isDisplay=1&id="+id+"&recordId="+recordId});
        items.add(new String[] {"异动项目", "../salary/salary_record!getSalaryStandardItems.action?type=2&isDisplay=1&id="+id+"&recordId="+recordId});
        items.add(new String[] {"标准类项目", "../salary/salary_record!getSalaryStandardItems.action?type=3&isDisplay=1&id="+id+"&recordId="+recordId});
        items.add(new String[] {"年金类项目", "../salary/salary_record!getSalaryStandardItems.action?type=4&isDisplay=1&id="+id+"&recordId="+recordId});
        items.add(new String[] {"统筹类项目", "../salary/salary_record!getSalaryStandardItems.action?type=5&isDisplay=1&id="+id+"&recordId="+recordId});

        return items;
    }

    @Override
    public List<Map<String, Object>> getSalaryStandardItems(String id, int type, int isDisplay, String recordId) {
        return salaryRecordDao.getSalaryStandardItems(id, type, isDisplay, recordId);
    }

    @Override
    public Map<String, Object> getHistorySalaryRecord(String id) {
        return salaryRecordDao.getHistorySalaryRecord(id);
    }

    @Override
    public Map<String, Object> getPracticeEmployeeListData(String depId, String searchData, Pager pager) {
        return salaryRecordDao.getPracticeEmployeeListData(depId, searchData, pager);
    }

    @Override
    public Map<String, Object> getEmployeeBaseInfo(String id) {
        return salaryRecordDao.getEmployeeBaseInfo(id);
    }

    @Override
    public List<Map<String, Object>> getSalaryGroupValueListData(String id) {
        return salaryRecordDao.getSalaryGroupValueListData(id);
    }

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String saveOrUpdateToStaff(String formdata, String griddata, String gridData, String gridAccountData) throws Exception {
        // 基本信息
        SalaryRecord record = JsonUtil.toObject(formdata, SalaryRecord.class);
        if (record != null) {
            SalaryDeptDictionary deptDictionary = get(SalaryDeptDictionary.class, Restrictions.eq("id", record.getSalaryPost()));
            String salaryPostName = null == deptDictionary? "" : deptDictionary.getName();
            record.setSalaryPostName(salaryPostName);
            String staffId = "";
            String recordMainId = "";
            if (StringUtils.isBlank(record.getId())) {
                record.setIsBzgs(1);// 标准工时
                record.setIsSpecialHour(1);// 特殊工时
                record.setGzxs(0);// 工资形式
                record.setFpqx(2);// 分配权限
                record.setZfxs(1);// 支付形式
                // 股东日期
                Calendar cal= Calendar.getInstance();
                cal.add(Calendar.DATE,-1);
                record.setGdDate(cal.getTime());
                recordMainId = save(record);
                if (StringUtils.isBlank(recordMainId)) {
                    return "发生异常，保存失败";
                }
                staffId = record.getStaffId();
            } else {
                SalaryRecord record2 = get(SalaryRecord.class, Restrictions.eq("id", record.getId()));
                if (record2 == null) {
                    return "该员工不存在";
                }
                record2.copyFrom(record);
                update(record2);
                staffId = record2.getStaffId();
                recordMainId = record2.getId();
            }
            List<Map<String, String>> mapList = JsonUtil.getMapList(griddata);
            for (int i=0, l = mapList.size(); i< l; i++) {
                Map<String, String> m = mapList.get(i);
                SalaryPersonal personal = JsonUtil.toObject(JsonUtil.Encode(m), SalaryPersonal.class);
                if ("removed".equals(m.get("_state"))) {
                    SalaryPersonal personal2 = salaryPersonalService.get(SalaryPersonal.class, Restrictions.eq("id", personal.getId()),
                            Restrictions.eq("isDelete", 0));
                    if (personal2 != null) {
                        personal2.setIsDelete(1);
                        salaryPersonalService.update(personal2);

                        // 删除对应的薪酬值
                        List<SalaryValue> list = getList(SalaryValue.class,
                                Restrictions.eq("staffId", staffId),
                                Restrictions.eq("recordMainId", recordMainId),
                                Restrictions.eq("salaryGroupId", personal2.getSalaryGroupId()),
                                Restrictions.eq("isDelete", 0));
                        for (SalaryValue salaryValue : list) {
                            salaryValue.setIsDelete(1);
                        }
                        salaryValueService.saveBatch(list);
                    }
                } else if ("modified".equals(m.get("_state"))) {
                    SalaryPersonal personal2 = salaryPersonalService.get(SalaryPersonal.class, Restrictions.eq("id", personal.getId()),
                            Restrictions.eq("isDelete", 0));
                    if (personal2 != null) {
                        // 判断是否更新了薪资组
                        if (!personal2.getSalaryGroupId().equals(personal.getSalaryGroupId())) {
                            List<SalaryValue> list = getList(SalaryValue.class,
                                    Restrictions.eq("staffId", staffId),
                                    Restrictions.eq("recordMainId", recordMainId),
                                    Restrictions.eq("salaryGroupId", personal2.getSalaryGroupId()),
                                    Restrictions.eq("isDelete", 0));
                            for (SalaryValue salaryValue : list) {
                                salaryValue.setIsDelete(1);
                            }
                            salaryValueService.saveBatch(list);
                        }
                        personal2.copyFrom(personal);
                        salaryPersonalService.update(personal2);
                    } else throw new Exception("该薪资组："+m.get("groupName") + "已经被删除!");
                } else if ("added".equals(m.get("_state"))) {
                    personal.setSalaryRecordId(staffId);
                    personal.setRecordMainId(recordMainId);
                    personal.setIsPei(0);
                    personal.setIsOff(0);
                    personal.setIsDelete(0);
                    String result = salaryPersonalService.save(personal);
                    if (StringUtils.isBlank(result)) {
                        throw new Exception("该薪资组："+m.get("groupName") + "保存失败!");
                    }

                    // 获取薪资组相关联的薪资项目
                    String itemSql = salaryItemsDao.getGroupToItems(personal.getSalaryGroupId());
                    List<?> items = findBySql(itemSql);
                    for (int j = 0, mm = items.size(); j < mm; j++) {
                        Object[] obj = (Object[]) items.get(j);
                        SalaryValue salaryValue = get(SalaryValue.class,
                                Restrictions.eq("staffId", staffId),
                                Restrictions.eq("recordMainId", recordMainId),
                                Restrictions.eq("salaryGroupId", personal.getSalaryGroupId()),
                                Restrictions.eq("salaryItemsId", obj[0]),
                                Restrictions.eq("isDelete", 0));
                        if (salaryValue == null) {
                            salaryValue = new SalaryValue();
                            salaryValue.setStaffId(staffId);
                            salaryValue.setRecordMainId(recordMainId);
                            salaryValue.setSalaryGroupId(personal.getSalaryGroupId());
                            salaryValue.setSalaryItemsId(obj[0].toString());
                            salaryValue.setStaticValue("0");
                            salaryValue.setIsDelete(0);
                            String valueId = salaryValueService.save(salaryValue);
                            if (StringUtils.isBlank(valueId)) {
                                throw new Exception("保存失败!");
                            }
                        }
                    }
                }
            }
            List<Map<String, String>> groupList = JsonUtil.getMapList(gridData);
            for (int i=0, l = groupList.size(); i< l; i++) {
                Map<String, String> m = groupList.get(i);
                SalaryValue salaryValue = JsonUtil.toObject(JsonUtil.Encode(m), SalaryValue.class);
                if ("removed".equals(m.get("_state"))) {
                    SalaryValue value = salaryPersonalService.get(SalaryValue.class, Restrictions.eq("id", salaryValue.getId()),
                            Restrictions.eq("isDelete", 0));
                    if (value != null) {
                        value.setIsDelete(1);
                        salaryValueService.update(value);
                    }
                } else if ("modified".equals(m.get("_state"))) {
                    SalaryValue value = salaryPersonalService.get(SalaryValue.class, Restrictions.eq("id", salaryValue.getId()),
                            Restrictions.eq("isDelete", 0));
                    if (value != null) {
                        value.copyFrom(salaryValue);
                        salaryValueService.update(value);
                    } else throw new Exception("该薪资组："+m.get("groupName") + "已经被删除!");
                } else if ("added".equals(m.get("_state"))) {
                    SalaryValue value = salaryPersonalService.get(SalaryValue.class,
                            Restrictions.eq("staffId", staffId),
                            Restrictions.eq("recordMainId", recordMainId),
                            Restrictions.eq("salaryGroupId", m.get("salaryGroupId")),
                            Restrictions.eq("salaryItemsId", m.get("salaryItemsId")),
                            Restrictions.eq("isDelete", 0));
                    if (value != null) {
                        value.setStaticValue(salaryValue.getStaticValue());
                        salaryValueService.update(value);
                    } else {
                        salaryValue.setStaffId(staffId);
                        salaryValue.setRecordMainId(recordMainId);
                        salaryValue.setIsDelete(0);
                        String result = salaryValueService.save(salaryValue);
                        if (StringUtils.isBlank(result)) {
                            throw new Exception("发生异常，保存失败!");
                        }
                    }
                }
            }
            List<Map<String, String>> gridAccountList = JsonUtil.getMapList(gridAccountData);
            for (int i=0, l = gridAccountList.size(); i< l; i++) {
                Map<String, String> m = gridAccountList.get(i);
                SalaryAccount salaryValue = JsonUtil.toObject(JsonUtil.Encode(m), SalaryAccount.class);
                if ("removed".equals(m.get("_state"))) {
                    SalaryAccount value = salaryAccountService.get(SalaryAccount.class, Restrictions.eq("id", salaryValue.getId()),
                            Restrictions.eq("isDelete", 0));
                    if (value != null) {
                        value.setIsDelete(1);
                        salaryAccountService.update(value);
                    }
                } else if ("modified".equals(m.get("_state"))) {
                    SalaryAccount value = salaryAccountService.get(SalaryAccount.class, Restrictions.eq("id", salaryValue.getId()),
                            Restrictions.eq("isDelete", 0));
                    if (value != null) {
                        value.copyFrom(salaryValue);
                        salaryAccountService.update(value);
                    } else throw new Exception("该账号："+m.get("account") + "已经被删除!");
                } else if ("added".equals(m.get("_state"))) {
                    salaryValue.setStaffId(staffId);
                    salaryValue.setRecordMainId(recordMainId);
                    salaryValue.setIsDelete(0);
                    String result = salaryAccountService.save(salaryValue);
                    if (StringUtils.isBlank(result)) {
                        throw new Exception("发生异常，保存失败!");
                    }
                }
            }

            // 获取调整记录
            Map<String, String> adjustMap = JsonUtil.parseProperties(formdata);
            String d = adjustMap.get("adjust");
            d = d.substring(1, d.length()-1);
            Map<String, String> map = JsonUtil.parseProperties(d);
            // 更改记录保存
            if (map != null) {
                List<SalaryHistory> histories = new ArrayList<SalaryHistory>();
                for (Entry<String, String> entry : map.entrySet()) {
                    // id==>员工id
                    Map<String, String> itemsMap = JsonUtil.parseProperties(entry.getValue());
                    for (Entry<String, String> entryItems : itemsMap.entrySet()) {
                        String itemId = entryItems.getKey();
                        String eiValue = entryItems.getValue();
                        eiValue = eiValue.substring(1, eiValue.length()-1);
                        String[] values = eiValue.split(",");
                        if (values.length == 1 && !values[0].equals(values[1])) {
                            SalaryHistory history = new SalaryHistory();
                            history.setStaffId(staffId);
                            history.setRecordMainId(recordMainId);
                            history.setItemsId(itemId);
                            history.setAdjustFront(values[0].replaceAll("\"", ""));
                            history.setAdjustNext(values[1].replaceAll("\"", ""));
                            history.setAdjustDate(new Date());
                            history.setNote(values[2]);
                            histories.add(history);
                        }
                    }
                }
                if (histories.size() > 0) {
                    salaryHistoryService.saveBatch(histories);
                }
            }

            // 得到异动表id
            Map<String, String> mapData = JsonUtil.parseProperties(formdata);
            if (mapData != null) {
                Change change = get(Change.class, Restrictions.eq("id", mapData.get("empChangeId")));
                if (change != null) {
                    change.setChBeginDate(record.getEffectDate());// 更新异动表的开始日期
                    change.setIsDeal(1);
                    changeService.update(change);
                }
            }
        } else {
            return "信息不能为空";
        }

        return "success";
    }

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String saveOrUpdateToStaffSalary(String gridData) throws Exception {
        List<Map<String, String>> list = JsonUtil.getMapList(gridData);
        for (int i=0, l = list.size(); i< l; i++) {
            Map<String, String> m = list.get(i);
            // 循环遍历项目值
            String[] itemsId = m.get("wmId").split(",");
            String[] svIds = m.get("svId").split(",");
            for (int j=0, ll = itemsId.length; j < ll; j++) {
                SalaryValue value = salaryPersonalService.get(SalaryValue.class, Restrictions.eq("staffId", m.get("id")),
                        Restrictions.eq("id", svIds[j]), Restrictions.eq("isDelete", 0));
                if (value != null) {
                    value.setSalaryItemsId(itemsId[j]);
                    value.setStaticValue(m.get("a"+ j).trim());
                    salaryValueService.update(value);
                } else throw new Exception(m.get("name")+" 的第"+j+"个薪资项目已经被删除!");
            }
        }

        return "success";
    }

    @Override
    public Map<String, Object> getAllSalaryAdjustListData(String depId, String searchData, Pager pager) {
        return salaryRecordDao.getAllSalaryAdjustListData(depId, searchData, pager);
    }

    @Override
    public List<Map<String, String>> getAllGroupFinancialList(String id) {
        return salaryRecordDao.getAllGroupFinancialList(id);
    }

    @Override
    public Map<String, Object> getAllRecordHistoryListData(String staffId, Pager pager) {
        return salaryRecordDao.getAllRecordHistoryListData(staffId, pager);
    }

    @Override
    public List<Map<String, Object>> getStaffForAdjustListData(String ids) {
        return salaryRecordDao.getStaffForAdjustListData(ids);
    }

    @Override
    public List<SalaryAccount> getSalaryAccountListData(String id, String recordId) {
        List<SalaryAccount> list = getList(SalaryAccount.class, Restrictions.eq("staffId", id),
                Restrictions.eq("recordMainId", recordId),
                Restrictions.eq("isDelete", 0));

        return list;
    }

    @Override
    public Map<String, Object> getChangeHistoryForStaff(String staffId, Pager pager) {
        return salaryRecordDao.getChangeHistoryForStaff(staffId, pager);
    }

    @Override
    public String uploadFixedItemsValues(String endSuffix, File uploadFile) throws Exception {
     // 读取数据
        List<String[]> list = ExcelUtil.readData(0, 2, endSuffix, uploadFile);

        // 导入名单只有表头没有人员信息
        if (list.size() == 1) {
            return "文件导入失败：导入名单中不存在项目信息";
        }

        // 第一行内容
        String[] firstRow = {"工号","薪资组","薪资项目","值"};
        // 单独把第一行抽取出来
        String[] firstValues = list.get(0);

        for (int i = 0, z = firstRow.length; i < z; i++) {
            if (!firstRow[i].trim().equals(firstValues[i].trim())) {
                return "文件导入失败：导入名单标题存在问题！<br/><font color='red'>" + firstValues[i].trim() + "</font>列必须是 " + firstRow[i].trim();
            }
        }

        // 薪资组薪资项目薪酬值表
        List<SalaryValue> salaryValues = new ArrayList<SalaryValue>();

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
            // 薪资组
            if (StringUtils.isBlank(columns[1])) {
                throw new Exception("第"+ (i+1) +"行【薪资组】不能为空");
            }
            // 薪资项目
            if (StringUtils.isBlank(columns[2])) {
                throw new Exception("第"+ (i+1) +"行【薪资项目】不能为空");
            }
            // 值
            if (StringUtils.isBlank(columns[3])) {
                throw new Exception("第"+ (i+1) +"行【值】不能为空");
            } else {
                try {
                    Double.valueOf(columns[3]);
                } catch (Exception e) {
                    throw new Exception("第"+ (i+1) +"行【值】必须是数字");
                }
            }

            // 查找员工ID
            BasicInformation info = get(BasicInformation.class, Restrictions.eq("jobNumber", columns[0]));
            if (info == null) {
                throw new Exception(columns[0] + "员工不存在");
            }
            SalaryGroup salaryGroup = get(SalaryGroup.class, Restrictions.eq("name", columns[1]), Restrictions.eq("isDelete", 0));
            if (salaryGroup == null) {
                throw new Exception(columns[1] + "薪资组不存在");
            }
            SalaryItems items = get(SalaryItems.class, Restrictions.eq("name", columns[2]), Restrictions.eq("isDelete", 0));
            if (items == null) {
                throw new Exception(columns[2] + "薪资项目不存在");
            }
            SalaryValue salaryValue = get(SalaryValue.class,
                    Restrictions.eq("staffId", info.getId()),
                    Restrictions.eq("salaryGroupId", salaryGroup.getId()),
                    Restrictions.eq("salaryItemsId", items.getId()),
                    Restrictions.eq("isDelete", 0));
            if (salaryValue == null) {
                throw new Exception("第"+ (i+1) +"行对应的固定薪资项目值不存在");
            }
            salaryValue.setStaticValue(columns[3].toString());
            salaryValues.add(salaryValue);
        }
        if (salaryValues.size() > 0) salaryValueService.saveBatch(salaryValues);

        return "success";
    }

    @Override
    public String uploadStandValues(String endSuffix, File uploadFile) throws Exception {
        // 读取数据
        List<String[]> list = ExcelUtil.readData(0, 2, endSuffix, uploadFile);

        // 导入名单只有表头没有人员信息
        if (list.size() == 1) {
            return "文件导入失败：导入名单中不存在项目信息";
        }

        // 第一行内容
        String[] firstRow = {"工号","薪资组","薪资项目","值"};
        // 单独把第一行抽取出来
        String[] firstValues = list.get(0);

        for (int i = 0, z = firstRow.length; i < z; i++) {
            if (!firstRow[i].trim().equals(firstValues[i].trim())) {
                return "文件导入失败：导入名单标题存在问题！<br/><font color='red'>" + firstValues[i].trim() + "</font>列必须是 " + firstRow[i].trim();
            }
        }

        // 薪资组薪资项目薪酬值表
        List<SalaryValue> salaryValues = new ArrayList<SalaryValue>();

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
            // 薪资组
            if (StringUtils.isBlank(columns[1])) {
                throw new Exception("第"+ (i+1) +"行【薪资组】不能为空");
            }
            // 薪资项目
            if (StringUtils.isBlank(columns[2])) {
                throw new Exception("第"+ (i+1) +"行【薪资项目】不能为空");
            }
            // 值
            if (StringUtils.isBlank(columns[3])) {
                throw new Exception("第"+ (i+1) +"行【值】不能为空");
            } else {
                try {
                    Double.valueOf(columns[3]);
                } catch (Exception e) {
                    throw new Exception("第"+ (i+1) +"行【值】必须是数字");
                }
            }

            // 查找员工ID
            BasicInformation info = get(BasicInformation.class, Restrictions.eq("jobNumber", columns[0]));
            if (info == null) {
                throw new Exception(columns[0] + "员工不存在");
            }
            SalaryRecord record = get(SalaryRecord.class, Restrictions.eq("staffId", info.getId()));
            if (null == record) throw new Exception(columns[0] + "员工档案不存在");
            SalaryGroup salaryGroup = get(SalaryGroup.class, Restrictions.eq("name", columns[1]), Restrictions.eq("isDelete", 0));
            if (salaryGroup == null) {
                throw new Exception(columns[1] + "薪资组不存在");
            }
            SalaryItems items = get(SalaryItems.class, Restrictions.eq("name", columns[2]), Restrictions.eq("isDelete", 0));
            if (items == null) {
                throw new Exception(columns[2] + "薪资项目不存在");
            }
            if (items.getIsDisplay() == 0) throw new Exception(columns[2] + "薪资项目不是标准项目");
            SalaryValue salaryValue = get(SalaryValue.class,
                    Restrictions.eq("staffId", info.getId()),
                    Restrictions.eq("salaryGroupId", salaryGroup.getId()),
                    Restrictions.eq("salaryItemsId", items.getId()),
                    Restrictions.eq("recordMainId", record.getId()),
                    Restrictions.eq("isDelete", 0));
            if (salaryValue == null) {
                throw new Exception("第"+ (i+1) +"行 "+columns[0] + " " + info.getName()+" 对应的薪资项目 "+columns[2]+" 标准值不存在");
            }
            salaryValue.setStaticValue(columns[3].toString());
            salaryValues.add(salaryValue);
        }
        if (salaryValues.size() > 0) salaryValueService.saveBatch(salaryValues);

        return "success";
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void export(String searchData) {
        try {
            searchData = URLDecoder.decode(searchData, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String sql = salaryRecordDao.getPracticeEmployeeListDataSql(searchData);
        List<?> list = findBySql(sql);
        ArrayList<ArrayList> tableData = new ArrayList<ArrayList>();
        for (int i = 0, l = list.size(); i < l; i++) {
            Object[] obj = (Object[]) list.get(i);
            StringBuilder sqlLite = new StringBuilder();
            sqlLite.append("    SELECT                                                         ");
            sqlLite.append("      JBI.JOB_NUMBER,SV.STATIC_VALUE,ITEMS.NAME,SG.NAME groupName  ");
            sqlLite.append("    FROM XC_SALARY_VALUE SV                                        ");
            sqlLite.append("    LEFT JOIN XC_SALARY_GROUP SG                                   ");
            sqlLite.append("    ON SV.SALARY_GROUP_ID = SG.ID                                  ");
            sqlLite.append("    LEFT JOIN XC_SALARY_ITEMS ITEMS                                ");
            sqlLite.append("    ON SV.SALARY_ITEMS_ID = ITEMS.ID                               ");
            sqlLite.append("    LEFT JOIN JC_BASIC_INFORMATION JBI                             ");
            sqlLite.append("    ON JBI.ID = SV.STAFF_ID                                        ");
            sqlLite.append("    WHERE SV.IS_DELETE=0                                           ");
            sqlLite.append("      AND SV.STAFF_ID='"+obj[0]+"'                                 ");
            sqlLite.append("    ORDER BY JBI.ID ASC                                            ");

            List<?> lists = findBySql(sqlLite.toString());
            for (int j = 0, m = lists.size(); j < m; j++) {
                Object[] objLite = (Object[]) lists.get(j);
                ArrayList rowList=new ArrayList();
                rowList.add(objLite[0]);// 工号
                rowList.add(obj[2]);// 姓名
                rowList.add(obj[18]);// 原公司
                rowList.add(obj[19]);// 原部门
                rowList.add(obj[20]);// 原岗位
                rowList.add(obj[3]);// 现公司
                rowList.add(obj[7]);// 现部门
                rowList.add(obj[9]);// 现具体岗位
                rowList.add(obj[22]);// 现标准岗位
                rowList.add(obj[21]);// 薪酬岗位
                rowList.add(objLite[3]);// 薪资组
                rowList.add(objLite[2]);// 薪资项目
                rowList.add(objLite[1]);// 值
                tableData.add(rowList);
            }
        }

        //表格标题
        String[] tableCaption ={"工号","姓名","原公司","原部门","原岗位","现公司","现部门","现具体岗位","现标准岗位","薪酬岗位","薪资组","薪资项目","值"};

        //表格脚部分
        String dcr=ToolUtil.userName();
        Date dcrq=new Date();
        ArrayList footData=new ArrayList();
        footData.add("导出人： "+ ExcelUtil.toString(dcr));
        footData.add("导出日期： "+ ExcelUtil.toString(dcrq));
        ExcelUtil.export("员工薪酬调整数据", null, tableCaption, tableData, footData);
    }

    @Override
    public String remove(String ids) {
        String[] idArray = ids.split(",");
        for (int i = 0, l = idArray.length; i < l; i++) {
            SalaryRecord group = get(SalaryRecord.class, Restrictions.eq("id", idArray[i]));
            if (group != null) {
                this.delete(group);
            }
        }

        return "success";
    }

}
