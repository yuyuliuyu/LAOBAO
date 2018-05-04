package com.lingnet.hcm.service.impl.branch;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.branch.PerformanceAppraisalDao;
import com.lingnet.hcm.entity.basic.PerformanceAppraisal;
import com.lingnet.hcm.service.branch.PerformanceAppraisalService;
import com.lingnet.util.ExcelUtil;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
import com.lingnet.util.Pager;
import com.lingnet.util.ToolUtil;

/**
 * 绩效考核
 * @ClassName: PerformanceAppraisalServiceImpl 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年7月12日 下午5:26:34 
 *
 */
@Service("performanceAppraisalService")
public class PerformanceAppraisalServiceImpl extends BaseServiceImpl<PerformanceAppraisal, String>
              implements PerformanceAppraisalService {

    @Resource(name="performanceAppraisalDao")
    private PerformanceAppraisalDao performanceAppraisalDao;

    @Override
    public Map<String, Object> jsonlist(String searchData, Pager pager) {
        pager = findPager(PerformanceAppraisal.class, pager, Restrictions.eq("isDelete", 0));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", pager.getResult());
        map.put("total", pager.getTotalCount());

        return map;
    }

    @Override
    public String saveOrUpdate(String formdata) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        PerformanceAppraisal appraisal = JsonUtil.toObject(formdata, PerformanceAppraisal.class);
        appraisal.setCtrId(LingUtil.userinfo().getId());
        if (StringUtils.isBlank(appraisal.getId())) {
            appraisal.setIsDelete(0);
//            appraisal.setPerforAppr(0);
            appraisal.setPerforState(1);

            // 只能填写本月的考核
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(appraisal.getPerforDate());
            if (!(cal1.get(Calendar.YEAR) == year && (cal1.get(Calendar.MONTH) + 1) == month)) {
                return "只能填写当月考核";
            }
            String id = save(appraisal);
            if (StringUtils.isBlank(id)) {
                return "保存失败";
            }
        } else {
            PerformanceAppraisal appraisalNew = get(PerformanceAppraisal.class,
                    Restrictions.eq("id", appraisal.getId()),
                    Restrictions.eq("isDelete", 0));
            if (appraisalNew == null) {
                return "该条信息不存在，已经被删除";
            } else {
                // 判断考核日期是否在当月10号之前
                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(appraisalNew.getPerforDate());

                // 得到上月
                cal.add(Calendar.MONTH, -1);
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH) + 1;

                // 本月考核在本月至下月10号之前可以修改
                if (cal1.get(Calendar.YEAR) == year) {
                    if (!(((cal1.get(Calendar.MONTH) + 1) - month) == 1 ||
                            ((cal1.get(Calendar.MONTH) + 1) == month && day <= 10))) {// 当月或者下一个月10号之前
                        return "只能修改上一个月至本月10号之前的考核数据";
                    }
                } else return "只能修改上一个月至本月10号之前的考核数据";

                appraisalNew.copyFrom(appraisal);
                update(appraisalNew);
            }
        }

        return "success";
    }

    @Override
    public String remove(String ids) {
        String[] idsArr = ids.split(",");
        List<PerformanceAppraisal> list = new ArrayList<PerformanceAppraisal>();
        for (int i = 0, l = idsArr.length; i < l; i++) {
            PerformanceAppraisal appraisalNew = get(PerformanceAppraisal.class,
                    Restrictions.eq("id", idsArr[i]),
                    Restrictions.eq("isDelete", 0));
            if (appraisalNew == null) {
                return "选中的第"+(i+1)+"条信息不存在，已经被删除";
            }
            appraisalNew.setIsDelete(1);
            list.add(appraisalNew);
        }
        if (list.size() > 0) saveBatch(list);

        return "success";
    }

    @Override
    public String rebiut(String ids) {
        String[] idsArr = ids.split(",");
        List<PerformanceAppraisal> list = new ArrayList<PerformanceAppraisal>();
        for (int i = 0; i < idsArr.length; i++) {
            PerformanceAppraisal appraisalNew = get(PerformanceAppraisal.class,
                    Restrictions.eq("id", idsArr[i]),
                    Restrictions.eq("isDelete", 0));
            if (appraisalNew == null) {
                return "选中的第"+(i+1)+"条信息不存在，已经被删除";
            }
            if (null == appraisalNew.getPerforAppr() || appraisalNew.getPerforAppr() == 0) {
                appraisalNew.setPerforAppr(1);
                appraisalNew.setPerforApprId(LingUtil.userinfo().getId());
                list.add(appraisalNew);
            }
        }
        if (list.size() > 0) saveBatch(list);

        return "success";
    }

    @Override
    public Map<String, Object> getCurComStaffDatas(String searchData, Pager pager) {
        return performanceAppraisalDao.getCurComStaffDatas(searchData, pager);
    }

    @Override
    public Map<String, Object> getCurAndDeptDatas(String userId, String searchData, Pager pager) {
        return performanceAppraisalDao.getCurAndDeptDatas(userId, searchData, pager);
    }

    @Override
    public String checkSure(String ids) {
        String[] idsArr = ids.split(",");
        List<PerformanceAppraisal> list = new ArrayList<PerformanceAppraisal>();
        for (int i = 0; i < idsArr.length; i++) {
            PerformanceAppraisal appraisalNew = get(PerformanceAppraisal.class,
                    Restrictions.eq("id", idsArr[i]),
                    Restrictions.eq("isDelete", 0));
            if (appraisalNew == null) {
                return "选中的第"+(i+1)+"条信息不存在，已经被删除";
            }
            if (appraisalNew.getPerforState() == 0) {
                appraisalNew.setPerforState(1);
                appraisalNew.setPerforStateId(LingUtil.userinfo().getId());
                list.add(appraisalNew);
            }
        }
        if (list.size() > 0) saveBatch(list);

        return "success";
    }

    @Override
    public String checkCancel(String ids) {
        String[] idsArr = ids.split(",");
        List<PerformanceAppraisal> list = new ArrayList<PerformanceAppraisal>();
        for (int i = 0; i < idsArr.length; i++) {
            PerformanceAppraisal appraisalNew = get(PerformanceAppraisal.class,
                    Restrictions.eq("id", idsArr[i]),
                    Restrictions.eq("isDelete", 0));
            if (appraisalNew == null) {
                return "选中的第"+(i+1)+"条信息不存在，已经被删除";
            }
            if (appraisalNew.getPerforState() == 1) {
                appraisalNew.setPerforState(0);
                appraisalNew.setPerforStateId(LingUtil.userinfo().getId());
                list.add(appraisalNew);
            }
        }
        if (list.size() > 0) saveBatch(list);

        return "success";
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void export(String searchData) {
        StringBuilder sql = new StringBuilder();
        sql.append("   SELECT                                                                              ");
        sql.append("   JPA.ID,JPA.DEPTID,JPA.DEPT_NAME,JPA.USERID,JPA.USER_NAME,JPA.PERFOR_DEPT_ID,        ");
        sql.append("   JPA.PERFOR_DEPT_NAME,JQD.DIC_NAME,JPA.PERFOR_THING,JPA.PERFOR_DATE,                 ");
        sql.append("   JPA.PERFOR_APPR,JPA.PERFOR_STATE,JPA.PERFOR_REWARD_SCROE,JPA.PERFOR_REWARD_MONEY,   ");
        sql.append("   JPA.PERFOR_PUNISH_SCROE,JPA.PERFOR_PUNISH_MONEY,JPA.CTR_ID,JPA.JOB_NUMBER           ");
        sql.append("   FROM JC_PERFORMANCE_APPRAISAL JPA                                                   ");
        sql.append("   LEFT JOIN JC_QX_DECTIONARY JQD                                                      ");
        sql.append("   ON JPA.PERFOR_STYLE = JQD.DIC_VALUE                                                 ");
        sql.append("   AND JQD.PID = '402881945d359d52015d35e45d3e0009'                                    ");
        sql.append("   WHERE JPA.IS_DELETE = 0                                                             ");

        Map<String, String> mapData = JsonUtil.parseProperties(searchData);
        if (mapData != null) {
            // 责任人
            if (!StringUtils.isBlank(mapData.get("userName"))) {
                sql.append("   AND JPA.USER_NAME LIKE '%"+mapData.get("userName").trim()+"%'                     ");
            }
        }
        sql.append("   ORDER BY JPA.CREATEDATE DESC                                                        ");
        List<?> list = findBySql(sql.toString());
        ArrayList<ArrayList> tableData = new ArrayList<ArrayList>();
        for (int i = 0; i < list.size(); i++) {
            ArrayList rowList=new ArrayList();
            Object[] obj = (Object[]) list.get(i);
            rowList.add(i+1);//序号
            rowList.add(obj[2]);//考核部门
            rowList.add(obj[17]);//工号
            rowList.add(obj[4]);//责任人
            rowList.add(obj[6]);//考核部门
            rowList.add(obj[7]);//考核分类
            rowList.add(obj[8]);//考核事件
            rowList.add(obj[12]);//考核奖励（考核分数）
            rowList.add(obj[13]);//考核奖励（考核金额）
            rowList.add(obj[14]);//考核扣罚（考核分数）
            rowList.add(obj[15]);//考核扣罚（考核金额）
            rowList.add(obj[9]);//考核日期
            String[] isDisplay = {"不确认","确认"};
            rowList.add(obj[10]==null?"":isDisplay[Integer.valueOf(obj[10].toString())]);// 考核确认
            String[] status = {"不兑现","兑现"};
            rowList.add(obj[11]==null?"":status[Integer.valueOf(obj[11].toString())]);// 考核兑现
            tableData.add(rowList);
        }

        //表格标题
        String[] tableCaption ={"序号","部门","工号","责任人","考核部门","考核分类","考核事件","考核奖励（考核分数）","考核奖励（考核金额）","考核扣罚（考核分数）","考核扣罚（考核金额）",
                "考核日期","考核确认","考核兑现"};

        //表格脚部分
        String dcr=ToolUtil.userName();
        Date dcrq=new Date();
        ArrayList footData=new ArrayList();
        footData.add("导出人： "+ ExcelUtil.toString(dcr));
        footData.add("导出日期： "+ ExcelUtil.toString(dcrq));
        ExcelUtil.export("绩效考核", null, tableCaption, tableData, footData);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void exportForPer(String userId, String searchData) {
        List<?> list = findBySql(performanceAppraisalDao.getCurAndDeptDatasSql(userId, searchData)[0]);
        ArrayList<ArrayList> tableData = new ArrayList<ArrayList>();
        for (int i = 0; i < list.size(); i++) {
            ArrayList rowList=new ArrayList();
            Object[] obj = (Object[]) list.get(i);
            rowList.add(i+1);//序号
            rowList.add(obj[2]);//考核部门
            rowList.add(obj[17]);//工号
            rowList.add(obj[4]);//责任人
            rowList.add(obj[6]);//考核部门
            rowList.add(obj[7]);//考核分类
            rowList.add(obj[8]);//考核事件
            rowList.add(obj[12]);//考核奖励（考核分数）
            rowList.add(obj[13]);//考核奖励（考核金额）
            rowList.add(obj[14]);//考核扣罚（考核分数）
            rowList.add(obj[15]);//考核扣罚（考核金额）
            rowList.add(obj[9]);//考核日期
            String[] isDisplay = {"不确认","确认"};
            rowList.add(obj[10]==null?"":isDisplay[Integer.valueOf(obj[10].toString())]);// 考核确认
            String[] status = {"不兑现","兑现"};
            rowList.add(obj[11]==null?"":status[Integer.valueOf(obj[11].toString())]);// 考核兑现
            tableData.add(rowList);
        }

        //表格标题
        String[] tableCaption ={"序号","部门","工号","责任人","考核部门","考核分类","考核事件","考核奖励（考核分数）","考核奖励（考核金额）","考核扣罚（考核分数）","考核扣罚（考核金额）",
                "考核日期","考核确认","考核兑现"};

        //表格脚部分
        String dcr=ToolUtil.userName();
        Date dcrq=new Date();
        ArrayList footData=new ArrayList();
        footData.add("导出人： "+ ExcelUtil.toString(dcr));
        footData.add("导出日期： "+ ExcelUtil.toString(dcrq));
        ExcelUtil.export("绩效考核", null, tableCaption, tableData, footData);
    }

}
