package com.lingnet.hcm.dao.impl.check;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.dao.check.EvectionRecordDao;
import com.lingnet.hcm.entity.check.CkEvectionRecord;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: EvectionRecordDaoImpl 
 * @Description: 出差申请记录Dao实现类 
 * @author wangqiang
 * @date 2017年5月3日 上午9:01:46 
 *
 */
@Repository("evectionRecordDao")
public class EvectionRecordDaoImpl extends BaseDaoImpl<CkEvectionRecord, String> implements EvectionRecordDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getInfoByCond(Pager pager, String userId, String jobNumber, String state) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		String sql = "select cer.ID, cer.JOB_NUMBER, cer.APPLY_NAME, cer.EVECTION_TYPE, to_char(cer.START_DATE, 'yyyy-MM-dd'), "
				+ "to_char(cer.END_DATE, 'yyyy-MM-dd'), cer.EVECTION_DAY, cer.PROCESS_ID, cer.NODE_ID, cer.AUDIT_STATUS "
				+ "from CK_EVECTION_RECORD cer "
				+ "left join jc_work_flowc jwf on cer.NODE_ID=jwf.ID "
				+ "where cer.IS_DELETE = 0 ";
		if ("0".equals(state)){//审核页面
			sql += "and cer.FIELD1='"+ jobNumber +"' ";
		} else {//申请页面
			sql += "and cer.EMP_ID='" + userId + "' ";
		}
		sql += "order by AUDIT_STATUS asc, APPLY_DATE desc";
		try {
		    this.findPagerBySql(pager, sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
		Pager pageInfo = this.findPagerBySql(pager, sql);
		List<Object[]> list = (List<Object[]>) pageInfo.getResult();
		if (list.size() > 0){
			for (int i = 0; i < list.size(); i++){
				Map<String, Object> hashmap = new HashMap<String, Object>();
				hashmap.put("id", list.get(i)[0]);
				hashmap.put("jobNumber", list.get(i)[1]);
				hashmap.put("applyName", list.get(i)[2]);
				if ("1".equals(list.get(i)[3])){
					hashmap.put("evectionType", "会议");
				} else if ("2".equals(list.get(i)[3])){
					hashmap.put("evectionType", "出差");
				} else if ("3".equals(list.get(i)[3])){
					hashmap.put("evectionType", "培训");
				} else if ("4".equals(list.get(i)[3])){
					hashmap.put("evectionType", "社会活动");
				} else {
					hashmap.put("evectionType", "");
				}
				hashmap.put("startDate", list.get(i)[4]);
				hashmap.put("endDate", list.get(i)[5]);
				hashmap.put("evectionDay", list.get(i)[6]);
				hashmap.put("processId", list.get(i)[7]);
				if (list.get(i)[8] == null){
                    hashmap.put("nodeName", "");
                    hashmap.put("nodeid", "");
				} else {
                    hashmap.put("nodeName", list.get(i)[8]);
                    hashmap.put("nodeid", list.get(i)[8]);
				}
                hashmap.put("auditStatus", list.get(i)[9]); 
				mapList.add(hashmap);
			}
		}
		return mapList;
	}
	
	public HashMap<String, Object> getListData(Pager pager, String searchData) {
		StringBuilder sql=new StringBuilder()
		.append("  select cer.ID, cer.JOB_NUMBER cerjobnumber, cer.APPLY_NAME, cer.EVECTION_TYPE, to_char(cer.START_DATE, 'yyyy-MM-dd'),    ")
		.append("  to_char(cer.END_DATE, 'yyyy-MM-dd'), cer.EVECTION_DAY, cer.PROCESS_ID, cer.NODE_ID, cer.AUDIT_STATUS, ")
		.append("  BAS.JOB_NUMBER,BAS.NAME PERNAME,BAS.FILM_NAME,BAS.DEPART_NAME,BAS.POST,BAS.SPECIFIC_POST,    ")
		.append("  cer.ADDRESS,cer.vehicle  ")
		.append("  from CK_EVECTION_RECORD cer  ")
		.append("  LEFT JOIN JC_BASIC_INFORMATION BAS ON BAS.JOB_NUMBER=cer.JOB_NUMBER      ")
		.append("  where cer.IS_DELETE = 0 and cer.AUDIT_STATUS = 2 ")
		;
		if(searchData!=null&&!"".equals(searchData)){
			Map<String,String> dataMap = JsonUtil.parseProperties(searchData);
			if (dataMap.get("jobNumber") != null && !"".equals(dataMap.get("jobNumber"))){
				sql.append(" and BAS.JOB_NUMBER LIKE'%"+dataMap.get("jobNumber")+"%'");
			}
			if (dataMap.get("name") != null && !"".equals(dataMap.get("name"))){
				sql.append(" and BAS.NAME like '%"+dataMap.get("name")+"%'");
			}
			
		}
		sql.append("   ORDER BY AUDIT_STATUS asc, APPLY_DATE desc  ");
		try {
			pager=this.findPagerBySql(pager, sql.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List list = pager.getResult();
        List dataList = new ArrayList();
        SimpleDateFormat form =new SimpleDateFormat("yyyy-MM-dd");
        String stamp = "";
		for (int i=0; i < list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", obj[0]);
			map.put("jobNumber", obj[1]);
			map.put("applyName", obj[2]);
			if ("1".equals(obj[3])){
				map.put("evectionType", "会议");
			} else if ("2".equals(obj[3])){
				map.put("evectionType", "出差");
			} else if ("3".equals(obj[3])){
				map.put("evectionType", "培训");
			} else if ("4".equals(obj[3])){
				map.put("evectionType", "社会活动");
			} else {
				map.put("evectionType", "");
			}
			map.put("startDate", obj[4]);
			map.put("endDate", obj[5]);
			map.put("evectionDay", obj[6]);
			map.put("processId", obj[7]);
			if (obj[8] == null){
				map.put("nodeName", "");
			} else {
				map.put("nodeName", obj[8]);
			}
			map.put("auditStatus", obj[9]);
			
			map.put("jobNumber", obj[10]);//
			map.put("pername", obj[11]);
			map.put("filmName", obj[12]);//
			map.put("departName", obj[13]);
			map.put("post", obj[14]);
			map.put("specificPost", obj[15]);
			map.put("address", obj[16]);
			map.put("vehicle", obj[17]);
			dataList.add(map);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        
		return map;
	}
	
}
