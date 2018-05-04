package com.lingnet.hcm.dao.impl.personnel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.hcm.dao.empdata.AwardDao;
import com.lingnet.hcm.dao.empdata.CareerDao;
import com.lingnet.hcm.dao.personnel.AuthenticateDao;
import com.lingnet.hcm.dao.personnel.CheckDao;
import com.lingnet.hcm.entity.person.Award;
import com.lingnet.hcm.entity.person.Career;
import com.lingnet.hcm.entity.person.PracticeAuthenticate;
import com.lingnet.hcm.entity.person.PracticeCheck;
import com.lingnet.util.Pager;

/**
 * 人员岗位兼职Dao实现类
 */
@Repository("checkDao")
public class CheckDaoImpl extends BaseDaoImplInit<PracticeCheck, String> implements CheckDao{

	@Override
	public HashMap<String, Object> getListData(Pager pager, String searchData) {
		StringBuilder sql=new StringBuilder()
		.append(" SELECT BAS.JOB_NUMBER,BAS.NAME PERNAME,BAS.FILM_NAME,BAS.DEPART_NAME,BAS.POST PERPOST,BAS.SPECIFIC_POST,  ")
		.append(" JA.ID,JA.AWARD_NAME,JA.AWARDDATE,JA.APPROVE_NUM,JA.APPROVE_OFFICE,JA.AWARD_CLASS,JA.AWARD_TYPE, ")
		.append(" JA.AWARD_LEVEL,JA.AWARD_CAUSE,JA.AWARD_STEP,JA.AWARD_AMOUNT,JA.FILE_NUM,JA.FILE_NAME,JA.REMARK,JA.URL ")
		.append(" FROM JC_AWARD JA ")
		.append(" LEFT JOIN JC_BASIC_INFORMATION BAS ON BAS.JOB_NUMBER=JA.JOB_NUMBER  ")
		.append(" ORDER BY JA.AWARDDATE DESC  ")
		;
		pager=this.findPagerBySql(pager, sql.toString());
		List list = pager.getResult();
        List dataList = new ArrayList();
        SimpleDateFormat form =new SimpleDateFormat("yyyy-MM-dd");
        String stamp = "";
		for (int i=0; i < list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("jobNumber", obj[0]);//
			map.put("pername", obj[1]);
			map.put("filmName", obj[2]);//
			map.put("departName", obj[3]);
			map.put("post", obj[4]);
			map.put("specificPost", obj[5]);
			
			map.put("id", obj[6]);
			map.put("awardName", obj[7]);
			if(obj[8]!=null&&!"".equals(obj[8])){
				stamp=form.format(obj[8]);
				map.put("awarddate", stamp);
			}
			map.put("approveNum", obj[9]);
			map.put("approveOffice", obj[10]);//人名字
			map.put("awardClass", obj[11]);
			map.put("awardType", obj[12]);
			map.put("awardLevel", obj[13]);
			map.put("awardCause", obj[14]);
			map.put("awardStep", obj[15]);
			map.put("awardAmount", obj[16]);
			map.put("fileNum", obj[17]);
			if(obj[18]!=null){
				map.put("fileName", "<a href='#' onClick=\"filedownload('"+obj[6]+"')\">"+obj[18]+"</a>");
			}
			map.put("remark", obj[19]);
			dataList.add(map);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        
		return map;
	}

	@Override
	public List<Map<String, Object>> getInfoByCond(Pager pager, String userId,
			String practiceNum, String state,String personId) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		String sql = "SELECT JPC.ID,JPC.CHECK_NAME,JPC.SCORE,JPC.CHECK_CONTENT , "
				+ " TO_CHAR(JPC.BEGINDATE,'YYYY-MM-DD'),TO_CHAR(JPC.ENDDATE,'YYYY-MM-DD'), "
				+ " JPC.GOAL_REACH,JPC.SELF_EVALUATE,JPC.USER_ID,JPC.PRACTICE_NUM, "
				+ " JPC.APPLY_NAME,JPC.PROCESS_ID,JPC.AUDIT_STATUS,JPC.NODE_ID "
				+ " FROM JC_PRACTICE_CHECK JPC "
				+ " LEFT JOIN JC_WORK_FLOWC JWF ON JWF.ID=JPC.NODE_ID "
				+ " where 1=1 ";
		
		if(personId!=null&&!"".equals(personId)){
			sql += "and JPC.USER_ID='" + personId + "' ";
		}else{
			if ("0".equals(state)){//审核页面
				sql += "and JPC.REMARK='"+ practiceNum +"' ";
			} else {//申请页面
				sql += "and JPC.USER_ID='" + userId + "' ";
			}
		}
		sql += "order by JPC.AUDIT_STATUS asc, BEGINDATE desc";
		Pager pageInfo = this.findPagerBySql(pager, sql);
		List<Object[]> list = (List<Object[]>) pageInfo.getResult();
		if (list.size() > 0){
			for (int i = 0; i < list.size(); i++){
				Map<String, Object> hashmap = new HashMap<String, Object>();
				hashmap.put("id", list.get(i)[0]);
				
				hashmap.put("checkName", list.get(i)[1]);
				hashmap.put("score", list.get(i)[2]);
				hashmap.put("checkContent", list.get(i)[3]);
				hashmap.put("begindate", list.get(i)[4]);
				hashmap.put("enddate", list.get(i)[5]);
				
				hashmap.put("goalReach", list.get(i)[6]);
				hashmap.put("selfEvaluate", list.get(i)[7]);
				hashmap.put("userId", list.get(i)[8]);
				hashmap.put("praticeNum", list.get(i)[9]);
				
				hashmap.put("applyName", list.get(i)[10]);
				hashmap.put("processId", list.get(i)[11]);
				
				hashmap.put("auditStatus", list.get(i)[12]);
				
				if (list.get(i)[13] == null){
                    hashmap.put("nodeName", "");
                    hashmap.put("nodeId", "");
				} else {
                    hashmap.put("nodeName", list.get(i)[13]);
                    hashmap.put("nodeId", list.get(i)[13]);
				}
				mapList.add(hashmap);
			}
		}
		return mapList;
	}
}
