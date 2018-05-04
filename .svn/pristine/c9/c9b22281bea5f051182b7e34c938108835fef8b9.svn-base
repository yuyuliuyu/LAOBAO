package com.lingnet.hcm.dao.impl.empdata;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.dao.empdata.LicenseDao;
import com.lingnet.hcm.entity.person.License;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

/**
 * 证照管理Dao实现类
 */
@Repository("licenseDao")
public class LicenseDaoImpl extends BaseDaoImpl<License, String> implements LicenseDao{
	@Override
	public HashMap<String, Object> getListData(Pager pager, String searchData,String depIds) {
		StringBuilder sql=new StringBuilder()
		.append(" SELECT BAS.JOB_NUMBER,BAS.NAME PERNAME,BR.FZX,QD.NAME,PP.POSITION_NAME PPNAME,DP.POSITION_NAME DPNAME,   ")
		.append(" JL.ID,JL.LICENSE_TYPE,JL.LICENSE_NUM,JL.LICENSE_STATUS,TO_CHAR(JL.BEGINDATE,'YYYY-MM-DD'), ")
		.append(" TO_CHAR(JL.ENDDATE,'YYYY-MM-DD'),JL.VALIDITY,JL.LOSE_DATE,JL.OFFICE, ")
		.append(" JL.IS_RECHECK,TO_CHAR(JL.LAST_RECHECKDAY,'YYYY-MM-DD'),TO_CHAR(JL.NEXT_RECHECKDAY,'YYYY-MM-DD'), ")
		.append(" JL.RECHECK_DAYS,JL.IS_BACKPAY,TO_CHAR(JL.BACKPAY_DATE,'YYYY-MM-DD'),JL.TACK_OFFICE,JL.TACK_NAME,  ")
		.append(" TO_CHAR(JL.TACK_DATE,'YYYY-MM-DD'),JL.DELIVER_NAME,TO_CHAR(JL.DELIVER_DATE,'YYYY-MM-DD'),JL.REMARK,JL.THE_CLASS  ")
		.append(" FROM JC_LICENSE JL  ")
		.append(" LEFT JOIN JC_BASIC_INFORMATION BAS ON BAS.ID=JL.PERSON_ID   ")
		.append(" LEFT JOIN BRANCH BR ON BR.ID = BAS.FILM_ID  ")
		.append(" LEFT JOIN QX_DEPARTMENT QD ON QD.ID = BAS.DEPART_ID  ")
		.append(" LEFT JOIN POST_POSITION PP ON PP.ID = BAS.POST_ID  ")
		.append(" LEFT JOIN DEPT_POSITION DP ON DP.ID = BAS.SPECIFIC_POST_ID  ")
		.append(" WHERE 1=1  ")
		;
		if(searchData!=null&&!"".equals(searchData)){
			Map<String,String> dataMap = JsonUtil.parseProperties(searchData);
			if (dataMap.get("depId") != null && !"".equals(dataMap.get("depId"))){
				String[] depIdArrs = dataMap.get("depId").split(",");
				
				String  resql="";
				for (int i = 0; i < depIdArrs.length; i++){
					if (!"".equals(depIdArrs[i])){
						resql+="'" + depIdArrs[i] + "', ";
					}
				}
				resql = resql.substring(0, resql.length()-2);
				
				sql.append(" and BAS.DEPART_ID in("+resql+")");
				
			} else {
				sql.append(" AND BAS.DEPART_ID IN("+ depIds+") ");
			}
			if (dataMap.get("jobNumber") != null && !"".equals(dataMap.get("jobNumber"))){
				sql.append(" and BAS.JOB_NUMBER LIKE'%"+dataMap.get("jobNumber")+"%'");
			}
			if (dataMap.get("name") != null && !"".equals(dataMap.get("name"))){
				sql.append(" and BAS.NAME like '%"+dataMap.get("name")+"%'");
			}
			if (dataMap.get("personId") != null && !"".equals(dataMap.get("personId"))){
				sql.append(" and BAS.ID = '"+dataMap.get("personId")+"'");
			}
		} else {
			sql.append(" AND BAS.DEPART_ID IN("+ depIds+") ");
		}
		sql.append(" ORDER BY JL.BEGINDATE DESC  ");
		pager=this.findPagerBySql(pager, sql.toString());
		List list = pager.getResult();
        List dataList = new ArrayList();
        
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
			map.put("licenseType", obj[7]);
			map.put("licenseNum", obj[8]);
			if(obj[11]!=null&&!"".equals(obj[11])){
				String countdate=countDate(obj[11].toString());
				if(countdate!=null&&"-1".equals(countdate)){
					map.put("licenseStatus", "2");
					map.put("loseDate", "-1");
	    		}else if(countdate==null){
	    			
	    		}else{
	    			map.put("licenseStatus", "1");
					map.put("loseDate", countdate);
	    		}
			}
			//map.put("licenseStatus", obj[9]);
			map.put("begindate", obj[10]);//
			map.put("enddate", obj[11]);
			map.put("validity", obj[12]);
			//map.put("loseDate", obj[13]);
			map.put("office", obj[14]);
			map.put("isRecheck", obj[15]);
			map.put("lastRecheckday", obj[16]);
			map.put("nextRecheckday", obj[17]);
			map.put("recheckDays", obj[18]);
			map.put("isBackpay", obj[19]);
			map.put("backpayDate", obj[20]);
			map.put("tackOffice", obj[21]);
			map.put("tackName", obj[22]);
			map.put("tackDate", obj[23]);
			map.put("deliverName", obj[24]);
			map.put("deliverDate", obj[25]);
			map.put("remark", obj[26]);
			map.put("theClass", obj[27]);
			dataList.add(map);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        
		return map;
	}
	
	/**
     * 计算时间差
     * zrl
	 * @throws ParseException 
     */
    public String countDate(String date2){
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	Date aaa=new Date();
    	Date bbb=null;
		try {
			bbb = sdf.parse(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	long time1 = aaa.getTime();  
        long time2 = bbb.getTime();  
        long diff ;  
        if(time1<time2) {  
            diff = time2 - time1;  
            long days = (diff / (1000 * 60 * 60 * 24))+1; 
            String day=String.valueOf(days);
            return day;
        } else {  
        	return "-1";  
        }  
    }
    
    @Override
	public List<Object[]> getExceptData(String thePersonId) {
		StringBuilder sql=new StringBuilder()
		.append("  SELECT JL.ID,JL.LICENSE_TYPE,JL.LICENSE_NUM,JL.LICENSE_STATUS,TO_CHAR(JL.BEGINDATE,'YYYY-MM-DD'),  ")
		.append(" TO_CHAR(JL.ENDDATE,'YYYY-MM-DD'),JL.VALIDITY,JL.LOSE_DATE,JL.OFFICE, ")
		.append(" JL.IS_RECHECK,TO_CHAR(JL.LAST_RECHECKDAY,'YYYY-MM-DD'),TO_CHAR(JL.NEXT_RECHECKDAY,'YYYY-MM-DD'), ")
		.append(" JL.RECHECK_DAYS,JL.IS_BACKPAY,TO_CHAR(JL.BACKPAY_DATE,'YYYY-MM-DD'),JL.TACK_OFFICE,JL.TACK_NAME,  ")
		.append(" TO_CHAR(JL.TACK_DATE,'YYYY-MM-DD'),JL.DELIVER_NAME,TO_CHAR(JL.DELIVER_DATE,'YYYY-MM-DD'),JL.REMARK,JL.THE_CLASS  ")
		.append(" FROM JC_LICENSE JL  ")
		.append("  WHERE JL.PERSON_ID='"+thePersonId+"'")
		;
		SQLQuery query=this.getSession().createSQLQuery(sql.toString());
		List<Object[]> list=query.list();
		return list;
	}
    
    
}
