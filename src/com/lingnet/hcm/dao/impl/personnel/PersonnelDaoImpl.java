package com.lingnet.hcm.dao.impl.personnel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.hcm.dao.personnel.PersonnelDao;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: PersonnelDaoImpl 
 * @Description:  
 * @author zrl
 * @date 2017年3月20日 下午12:08:54 
 *
 */
@Repository("personnelDao")
public class PersonnelDaoImpl extends BaseDaoImplInit<BasicInformation, String> 
	implements PersonnelDao{
	
	@Override
	public BasicInformation getByJobNumber(String jobNumber) {
		BasicInformation info = (BasicInformation) this.getSession().createCriteria(BasicInformation.class)
				.add(Restrictions.eq("jobNumber", jobNumber)).uniqueResult();
		return info;
	}

	@Override
	public List<Object> getAllJobNumbers() {
		String sql = "select JOB_NUMBER from JC_BASIC_INFORMATION where IS_DELETE = '0'";
		return this.getSession().createSQLQuery(sql).list();
	}

	@Override
	public List<Object> getAllCheckNumbers() {
		String sql = "select CHECK_NO from JC_BASIC_INFORMATION where IS_DELETE = '0'";
		return this.getSession().createSQLQuery(sql).list();
	}

	@Override
	public void updateBatchClass(String empIds, String depIds, String classNo) {
		String sql = "update JC_BASIC_INFORMATION set CLASS_NO = '"+classNo+"' where IS_DELETE = 0 ";
		if (!"".equals(empIds)){
			sql += "and ID IN("+empIds+") ";
		}
		if (!"".equals(depIds)){
			sql += "and DEPART_ID IN("+depIds+") ";
		}
		this.getSession().createSQLQuery(sql).executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getCheckInfosByDepIds(String depIds) {
		String sql = "select jbi.JOB_NUMBER, jbi.CLASS_NO, ci.ID "
			+"from JC_BASIC_INFORMATION jbi "
			+"left join CK_CLASS cc on jbi.CLASS_NO = cc.CLASS_NO "
			+"left join CK_INSTITUTION ci ON ci.ID = cc.INSTITUTE_ID "
			+"where jbi.IS_DELETE = 0 and jbi.CLASS_NO is not null ";
		if (!"".equals(depIds)){
			sql += "and jbi.FILM_ID ='"+depIds+"'";
		}
		return this.getSession().createSQLQuery(sql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getEmpIdsByDepIds(String depIds) {
		String empIds = "";
		String sql = "select ID from JC_BASIC_INFORMATION where IS_DELETE = 0 and DEPART_ID = '"+depIds+"'";
		List<Object> list = this.getSession().createSQLQuery(sql).list();
		if (list.size() > 0){
			for (Object obj:list){
				empIds += (obj + ",");
			}
		}
		if (!"".equals(empIds)){
			empIds = empIds.substring(0, empIds.length()-1);
		}
		return empIds;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getDataByEmpIds(Pager pager, List<String> empIdArrs) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String empIds = "";
		if (empIdArrs.size() > 0){
			for (String empId:empIdArrs){
				empIds += ("'" + empId + "', ");
			}
		}
		if (!"".equals(empIds)){
			String sql = "select jbi.ID, cc.class_name, jbi.name, jbi.job_number, jbi.post "
				+"from jc_basic_information jbi "
				+"left join CK_CLASS cc on jbi.class_no = cc.class_no "
				+"where jbi.id in("+empIds.substring(0, empIds.length()-2)+")";
			List<Object[]> objList = this.getSession().createSQLQuery(sql).list();
			if (objList.size() > 0){
				for (Object[] obj:objList){
					Map<String, String> map = new HashMap<String, String>();
					map.put("empId", obj[0]+"");
					map.put("className", obj[1]+"");
					map.put("empName", obj[2]+"");
					map.put("jobNumber", obj[3]+"");
					map.put("post", obj[4]+"");
					list.add(map);
				}
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getJobNumbersByDepIds(String depIds) {
		String sql = "select JOB_NUMBER from JC_BASIC_INFORMATION where CHECK_UNIT_ID in("+depIds+")";
		List<Object> objs = this.getSession().createSQLQuery(sql).list();
		String jobNumbers = "";
		if (objs != null){
			for (Object obj:objs){
				jobNumbers += ("'" +obj+ "', ");
			}
		}
		if (!"".equals(jobNumbers)){
			jobNumbers = jobNumbers.substring(0, jobNumbers.length()-2);
		}
		return jobNumbers;
	}

    @SuppressWarnings("unchecked")
    @Override
    public String getChecknoByDepIds(String depIds) {
        String sql = "select CHECK_NO from JC_BASIC_INFORMATION where CHECK_UNIT_ID in("+depIds+")";
        List<Object> objs = this.getSession().createSQLQuery(sql).list();
        String jobNumbers = "";
        if (objs != null){
            for (Object obj:objs){
                jobNumbers += ("'" +obj+ "', ");
            }
        }
        if (!"".equals(jobNumbers)){
            jobNumbers = jobNumbers.substring(0, jobNumbers.length()-2);
        }
        return jobNumbers;
    }
	
	@Override
	public String getCheckUnitIdByJobNumber(String jobNumber) {
		String sql = "select CHECK_UNIT_ID from JC_BASIC_INFORMATION where JOB_NUMBER ='"+jobNumber+"'";
		Object obj = this.getSession().createSQLQuery(sql).uniqueResult();
		return (obj==null?"":obj+"");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getAnnualLeaveData(Pager pager,
			String searchData, String depIds) {
		List<Map<String, String>> listDate = new ArrayList<Map<String, String>>();
		String sql = "select JOB_NUMBER, NAME, CLASS_NAME, to_char(SET_WORK_DATE, 'yyyy-MM-dd'), "
				+ "to_char(IN_PORT_TIME, 'yyyy-MM-dd') "
				+ "from JC_BASIC_INFORMATION jbi "
				+ "left join jc_pt_job jpj on jpj.PERSON_ID = jbi.ID "
				+ "left join CK_CLASS cc on jbi.CLASS_NO = cc.CLASS_NO "
				+ "where jbi.IS_DELETE = 0 and jpj.IS_HOST_POST = '1' ";
		if (searchData != null){
			Map<String,String> dataMap = JsonUtil.parseProperties(searchData);
			if (dataMap.get("depId") != null && !"".equals(dataMap.get("depId"))){
				String[] depIdArrs = dataMap.get("depId").split(",");
				sql += "and jbi.CHECK_UNIT_ID in(";
				for (int i = 0; i < depIdArrs.length; i++){
					if (!"".equals(depIdArrs[i])){
						sql += ("'" + depIdArrs[i] + "', ");
					}
				}
				sql = sql.substring(0, sql.length()-2);
				sql += ") ";
			} else {
				sql += "and jbi.CHECK_UNIT_ID IN("+ depIds +") ";
			}
		} else {
			sql += "and jbi.CHECK_UNIT_ID IN("+ depIds +") ";
		}
		Pager pageInfo = this.findPagerBySql(pager, sql);
		List<Object[]> list = (List<Object[]>) pageInfo.getResult();
		if (list.size() > 0){
			for (Object[] obj:list){
				Map<String, String> map = new HashMap<String, String>();
				map.put("jobNumber", obj[0]+"");
				map.put("name", obj[1]+"");
				if(obj[2]==null){
	                map.put("className","");
				}else{
	                map.put("className", obj[2]+""); 
				}
				map.put("workDate", obj[3]+"");//起始工作日期
				map.put("portDate", obj[4]+"");//入港日期
				map.put("legalDay", "");//全部
				map.put("shouldTake", "");//应休
				map.put("month1", "");
				map.put("month2", "");
				map.put("month3", "");
				map.put("month4", "");
				map.put("month5", "");
				map.put("month6", "");
				map.put("month7", "");
				map.put("month8", "");
				map.put("month9", "");
				map.put("month10", "");
				map.put("month11", "");
				map.put("month12", "");
				map.put("haveTake", "");//已休
				map.put("surplus", "");//剩余
				listDate.add(map);
			}
		}
		return listDate;
	}
	@Override
	public String getCheckUnitIdById(String id) {
		String sql = "select jbi.CHECK_UNIT_ID from QX_USERS qu "
				+ "left join JC_BASIC_INFORMATION jbi on qu.USERNAME = jbi.JOB_NUMBER "
				+ "where qu.ID ='"+id+"'";
		Object obj = this.getSession().createSQLQuery(sql).uniqueResult();
		return (obj==null?"":obj+"");
	}
	//**************************************************************************//
	/**
	 * 查询员工总页面
	 */
	@Override
	public HashMap<String, Object> getPersonByDepId(Pager pager,String searchData,String depIds) {
		
		StringBuilder sql=new StringBuilder()
		.append("  SELECT BAS.ID, BAS.DEPART_ID, BAS.JOB_NUMBER, BAS.NAME,BAS.AGE,  ")
		.append("  BAS.SEX,BAS.IS_MARRIED,BAS.JOB_LEVEL,BAS.EMP_TYPE,  ")
		.append("  BAS.ON_JOB,BAS.ON_POST,PP.POSITION_NAME PPNAME,DP.POSITION_NAME DPNAME,  ")
		.append("  BAS.FILM_ID,BR.FZX,QD.NAME DEPNAME,BAS.JOB_NUMBER_JT,BAS.BORTH_DATE,  ")
		.append("  BAS.ID_TYPE,BAS.ID_NUMBER,BAS.LABOR_COMPANY,JEE.EDUCATION_LEVER JEELEVER,JEE2.EDUCATION_LEVER JEELEVER2, ")
		.append("  JPJ.IS_HOST_POST ")
		.append("  FROM JC_PT_JOB JPJ ")
		.append("  LEFT JOIN JC_BASIC_INFORMATION BAS ON JPJ.PERSON_ID=BAS.ID ")
		
		.append("  LEFT JOIN BRANCH BR ON BR.ID = BAS.FILM_ID  ")
		.append("  LEFT JOIN QX_DEPARTMENT QD ON QD.ID = BAS.DEPART_ID  ")
		.append("  LEFT JOIN POST_POSITION PP ON PP.ID = BAS.POST_ID  ")
		.append("  LEFT JOIN DEPT_POSITION DP ON DP.ID = BAS.SPECIFIC_POST_ID  ")
		.append("  LEFT JOIN JC_EDUCATION_EXPERRIENCE JEE ON JEE.PERSON_ID = BAS.ID AND JEE.HIGHEST_EDUCATION=1 AND JEE.IS_ALLDAY='1'  ")
		.append("  LEFT JOIN JC_EDUCATION_EXPERRIENCE JEE2 ON JEE2.PERSON_ID = BAS.ID AND JEE2.HIGHEST_EDUCATION=1 AND JEE2.IS_ALLDAY='0'  ")
		.append("  WHERE BAS.IS_DELETE = 0 AND BAS.ON_JOB NOT IN(0,3,4) AND BAS.EMP_TYPE IN(0,1) ")
		;
		if (searchData != null){
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
				
				sql.append(" and JPJ.DEP_ID in("+resql+")");
				
			} else {
				sql.append(" AND JPJ.DEP_ID IN("+ depIds+") ");
			}
			if (dataMap.get("jobNumber") != null && !"".equals(dataMap.get("jobNumber"))){
				sql.append(" and BAS.JOB_NUMBER LIKE'"+dataMap.get("jobNumber")+"'");
			}
			if (dataMap.get("name") != null && !"".equals(dataMap.get("name"))){
				sql.append(" and BAS.NAME like '%"+dataMap.get("name")+"%'");
			}
			if (dataMap.get("personId") != null && !"".equals(dataMap.get("personId"))){
				sql.append(" and BAS.ID = '"+dataMap.get("personId")+"'");
			}
			if (dataMap.get("positionId") != null && !"".equals(dataMap.get("positionId"))){
				sql.append(" and BAS.POST_ID = '"+dataMap.get("positionId")+"'");
			}
			if (dataMap.get("jobLevel") != null && !"".equals(dataMap.get("jobLevel"))){
				sql.append(" and BAS.JOB_LEVEL = '"+dataMap.get("jobLevel")+"'");
			}
			if (dataMap.get("agebegin") != null && !"".equals(dataMap.get("agebegin"))){
				sql.append(" and to_char(sysdate,'YYYY')-to_char(BAS.BORTH_DATE,'YYYY') >= '"+dataMap.get("agebegin")+"'");
			}
			if (dataMap.get("ageend") != null && !"".equals(dataMap.get("ageend"))){
				sql.append(" and to_char(sysdate,'YYYY')-to_char(BAS.BORTH_DATE,'YYYY') <= '"+dataMap.get("ageend")+"'");
			}
			if (dataMap.get("empType") != null && !"".equals(dataMap.get("empType"))){
				sql.append(" and BAS.EMP_TYPE = '"+dataMap.get("empType")+"'");
			}
			if (dataMap.get("onJob") != null && !"".equals(dataMap.get("onJob"))){
				sql.append(" and BAS.ON_JOB = '"+dataMap.get("onJob")+"'");
			}
			if (dataMap.get("onPost") != null && !"".equals(dataMap.get("onPost"))){
				sql.append(" and BAS.ON_POST = '"+dataMap.get("onPost")+"'");
			}
			if (dataMap.get("sex") != null && !"".equals(dataMap.get("sex"))){
				sql.append(" and BAS.SEX = '"+dataMap.get("sex")+"'");
			}
			if (dataMap.get("isMarried") != null && !"".equals(dataMap.get("isMarried"))){
				sql.append(" and BAS.IS_MARRIED = '"+dataMap.get("isMarried")+"'");
			}
			// zhanghj 部门岗位
			if (dataMap.get("deptName") != null && !"".equals(dataMap.get("deptName"))){
			    sql.append(" and DP.POSITION_NAME LIKE '%"+dataMap.get("deptName")+"%'");
			}
		} else {
			sql.append(" AND JPJ.DEP_ID IN("+ depIds+") ");
		}
		sql.append(" order by QD.DESCRIPTION,PP.POSITION_CODE ");
		
		pager=this.findPagerBySql(pager, sql.toString());
		List list = pager.getResult();
		
        List dataList = new ArrayList();
		
		for (int i=0; i < list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", obj[0]);
			map.put("depId", obj[1]);
			map.put("jobNumber", obj[2]);
			map.put("name", obj[3]);
			if(obj[17]!=null&&!"".equals(obj[17])){
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
            	String thisDay = sdf.format(new Date());
            	String brithDay = sdf.format(obj[17]);
            	Integer age=Integer.parseInt(thisDay)-Integer.parseInt(brithDay);
            	map.put("age", age);
        	}
			map.put("sex", obj[5]);
			map.put("isMarried", obj[6]);
			//map.put("post", obj[7]);
			map.put("jobLevel", obj[7]);
			/*map.put("jobLevel", obj[8]);*/
			map.put("empType", obj[8]);
			
			map.put("onJob", obj[9]);
			map.put("onPost", obj[10]);
			map.put("post", obj[11]);
			map.put("specificPost", obj[12]);
			map.put("filmId", obj[13]);
			map.put("filmName", obj[14]);
			map.put("departName", obj[15]);
			map.put("jobNumberJt", obj[16]);
			
			map.put("idType", obj[18]);
			map.put("idNumber", obj[19]);
			map.put("laborCompany", obj[20]);
			map.put("edu1", obj[21]);
			map.put("edu2", obj[22]);
			/*map.put("lbpost", obj[24]);*/
			if(obj[23]!=null){
				if(Integer.valueOf(obj[23].toString())==0){
					map.put("ptjob", "兼职");
				}else if(Integer.valueOf(obj[23].toString())==1){
					map.put("ptjob", "本职");
				}
			}
			dataList.add(map);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        
		return map;
		
	}

	@Override
    public Map<String, Object> getProcessStaffDatas(Pager pager,String searchData,String depIds) {
        StringBuilder sql=new StringBuilder()
        .append("  SELECT BAS.ID, BAS.DEPART_ID, BAS.JOB_NUMBER, BAS.NAME,BAS.AGE,  ")
        .append("  BAS.SEX,BAS.IS_MARRIED,BAS.JOB_LEVEL,BAS.EMP_TYPE,  ")
        .append("  BAS.ON_JOB,BAS.ON_POST,PP.POSITION_NAME PPNAME,DP.POSITION_NAME DPNAME,  ")
        .append("  BAS.FILM_ID,BR.FZX,QD.NAME DEPNAME,BAS.JOB_NUMBER_JT,BAS.BORTH_DATE,  ")
        .append("  BAS.ID_TYPE,BAS.ID_NUMBER,BAS.LABOR_COMPANY,JEE.EDUCATION_LEVER JEELEVER,JEE2.EDUCATION_LEVER JEELEVER2 ")
        .append("  FROM JC_BASIC_INFORMATION BAS ")
        .append("  LEFT JOIN BRANCH BR ON BR.ID = BAS.FILM_ID  ")
        .append("  LEFT JOIN QX_DEPARTMENT QD ON QD.ID = BAS.DEPART_ID  ")
        .append("  LEFT JOIN POST_POSITION PP ON PP.ID = BAS.POST_ID  ")
        .append("  LEFT JOIN DEPT_POSITION DP ON DP.ID = BAS.SPECIFIC_POST_ID  ")
        .append("  LEFT JOIN JC_EDUCATION_EXPERRIENCE JEE ON JEE.PERSON_ID = BAS.ID AND JEE.HIGHEST_EDUCATION=1 AND JEE.IS_ALLDAY='1'  ")
        .append("  LEFT JOIN JC_EDUCATION_EXPERRIENCE JEE2 ON JEE2.PERSON_ID = BAS.ID AND JEE2.HIGHEST_EDUCATION=1 AND JEE2.IS_ALLDAY='0'  ")
        .append("  WHERE BAS.IS_DELETE = 0 AND BAS.ON_JOB NOT IN(0,3,4) AND BAS.EMP_TYPE IN(0,1) ")
        ;
        if (searchData != null){
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
//                sql.append(" AND BAS.DEPART_ID IN("+ depIds+") ");
            }
            if (dataMap.get("jobNumber") != null && !"".equals(dataMap.get("jobNumber"))){
                sql.append(" and BAS.JOB_NUMBER LIKE'"+dataMap.get("jobNumber")+"'");
            }
            if (dataMap.get("name") != null && !"".equals(dataMap.get("name"))){
                sql.append(" and BAS.NAME like '%"+dataMap.get("name")+"%'");
            }
            if (dataMap.get("personId") != null && !"".equals(dataMap.get("personId"))){
                sql.append(" and BAS.ID = '"+dataMap.get("personId")+"'");
            }
            /*,jobLevel,agebegin,ageend,empType,onJob,onPost,sex,isMarried*/
            if (dataMap.get("positionId") != null && !"".equals(dataMap.get("positionId"))){
                sql.append(" and BAS.POST_ID = '"+dataMap.get("positionId")+"'");
            }
            if (dataMap.get("jobLevel") != null && !"".equals(dataMap.get("jobLevel"))){
                sql.append(" and BAS.JOB_LEVEL = '"+dataMap.get("jobLevel")+"'");
            }
            if (dataMap.get("agebegin") != null && !"".equals(dataMap.get("agebegin"))){
                sql.append(" and to_char(sysdate,'YYYY')-to_char(BAS.BORTH_DATE,'YYYY') >= '"+dataMap.get("agebegin")+"'");
            }
            if (dataMap.get("ageend") != null && !"".equals(dataMap.get("ageend"))){
                sql.append(" and to_char(sysdate,'YYYY')-to_char(BAS.BORTH_DATE,'YYYY') <= '"+dataMap.get("ageend")+"'");
            }
            if (dataMap.get("empType") != null && !"".equals(dataMap.get("empType"))){
                sql.append(" and BAS.EMP_TYPE = '"+dataMap.get("empType")+"'");
            }
            if (dataMap.get("onJob") != null && !"".equals(dataMap.get("onJob"))){
                sql.append(" and BAS.ON_JOB = '"+dataMap.get("onJob")+"'");
            }
            if (dataMap.get("onPost") != null && !"".equals(dataMap.get("onPost"))){
                sql.append(" and BAS.ON_POST = '"+dataMap.get("onPost")+"'");
            }
            if (dataMap.get("sex") != null && !"".equals(dataMap.get("sex"))){
                sql.append(" and BAS.SEX = '"+dataMap.get("sex")+"'");
            }
            if (dataMap.get("isMarried") != null && !"".equals(dataMap.get("isMarried"))){
                sql.append(" and BAS.IS_MARRIED = '"+dataMap.get("isMarried")+"'");
            }
        } else {
//            sql.append(" AND BAS.DEPART_ID IN("+ depIds+") ");
        }
        sql.append(" order by QD.DESCRIPTION,PP.POSITION_CODE ");
        
        pager=this.findPagerBySql(pager, sql.toString());
        List list = pager.getResult();
        
        List dataList = new ArrayList();
        
        for (int i=0; i < list.size(); i++){
            Object[] obj = (Object[]) list.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            
            map.put("id", obj[0]);
            map.put("depId", obj[1]);
            map.put("jobNumber", obj[2]);
            map.put("name", obj[3]);
            if(obj[17]!=null&&!"".equals(obj[17])){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
                String thisDay = sdf.format(new Date());
                String brithDay = sdf.format(obj[17]);
                Integer age=Integer.parseInt(thisDay)-Integer.parseInt(brithDay);
                map.put("age", age);
            }
            map.put("sex", obj[5]);
            map.put("isMarried", obj[6]);
            //map.put("post", obj[7]);
            map.put("jobLevel", obj[7]);
            /*map.put("jobLevel", obj[8]);*/
            map.put("empType", obj[8]);
            
            map.put("onJob", obj[9]);
            map.put("onPost", obj[10]);
            map.put("post", obj[11]);
            map.put("specificPost", obj[12]);
            map.put("filmId", obj[13]);
            map.put("filmName", obj[14]);
            map.put("departName", obj[15]);
            map.put("jobNumberJt", obj[16]);
            
            map.put("idType", obj[18]);
            map.put("idNumber", obj[19]);
            map.put("laborCompany", obj[20]);
            map.put("edu1", obj[21]);
            map.put("edu2", obj[22]);
            
            dataList.add(map);
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        
        return map;
        
    }

    /**
	 * getZsgPerson
	 * 试岗期页面
	 * zrl
	 * @throws Exception 
	 */
	@Override
	public HashMap<String, Object> getSgqPerson(Pager pager,String searchData,String depIds) throws Exception {
		StringBuilder sql=new StringBuilder()
		.append("  SELECT BAS.ID, BAS.DEPART_ID, BAS.JOB_NUMBER, BAS.NAME,BAS.AGE,  ")
		.append("  BAS.SEX,BAS.IS_MARRIED,BAS.JOB_LEVEL,BAS.EMP_TYPE,  ")
		.append("  BAS.ON_JOB,BAS.ON_POST,PP.POSITION_NAME PPNAME,DP.POSITION_NAME DPNAME,  ")
		.append("  BAS.FILM_ID,BR.FZX,QD.NAME DEPNAME,BAS.JOB_NUMBER_JT,  ")
		.append("  TO_CHAR(BAS.ZZ_DATE,'YYYY-MM-DD'),TO_CHAR(BAS.JSSY_DATE,'YYYY-MM-DD'),BAS.BORTH_DATE,  ")
		.append("  TO_CHAR(RCM.TRIAL_START,'YYYY-MM-DD'),TO_CHAR(RCM.TRIAL_END,'YYYY-MM-DD')  ")
		.append("  FROM JC_BASIC_INFORMATION BAS ")
		.append("  LEFT JOIN BRANCH BR ON BR.ID = BAS.FILM_ID  ")
		.append("  LEFT JOIN QX_DEPARTMENT QD ON QD.ID = BAS.DEPART_ID  ")
		.append("  LEFT JOIN POST_POSITION PP ON PP.ID = BAS.POST_ID  ")
		.append("  LEFT JOIN DEPT_POSITION DP ON DP.ID = BAS.SPECIFIC_POST_ID  ")
		.append("  LEFT JOIN RS_CONTRACT_MANAGER RCM ON RCM.USERID = BAS.ID  ")
		.append("  where BAS.ON_JOB = 1 AND BAS.ON_POST = 0  AND BAS.EMP_TYPE IN(0,1) ")
		;
		if (searchData != null){
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
				sql.append(" and BAS.JOB_NUMBER ='"+dataMap.get("jobNumber")+"'");
			}
			if (dataMap.get("name") != null && !"".equals(dataMap.get("name"))){
				sql.append(" and BAS.NAME like '%"+dataMap.get("name")+"%'");
			}
			
		} else {
			sql.append(" AND BAS.DEPART_ID IN("+ depIds+") ");
		}
		sql.append(" order by QD.DESCRIPTION,PP.POSITION_CODE ");
		
		pager=this.findPagerBySql(pager, sql.toString());
		List list = pager.getResult();
		
        List dataList = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
		for (int i=0; i < list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", obj[0]);
			map.put("depId", obj[1]);
			map.put("jobNumber", obj[2]);
			map.put("name", obj[3]);
			if(obj[19]!=null&&!"".equals(obj[19])){
            	String thisDay = sdf1.format(new Date());
            	String brithDay = sdf1.format(obj[19]);
            	Integer age=Integer.parseInt(thisDay)-Integer.parseInt(brithDay);
            	map.put("age", age);
        	}
			map.put("sex", obj[5]);
			map.put("isMarried", obj[6]);
			map.put("jobLevel", obj[7]);
			map.put("empType", obj[8]);
			map.put("onJob", obj[9]);
			map.put("onPost", obj[10]);
			map.put("post", obj[11]);
			map.put("specificPost", obj[12]);
			map.put("filmId", obj[13]);
			map.put("filmName", obj[14]);
			map.put("departName", obj[15]);
			map.put("jobNumberJt", obj[16]);
			map.put("zzDate", obj[20]);
			map.put("jssyDate", obj[21]);
			if(obj[21]!=null&&!"".equals(obj[21])){
            	Date thisDay = sdf.parse(sdf.format(new Date()));
            	Date brithDay = sdf.parse(obj[21].toString());
            	String contDate="";
            	if(brithDay.getTime()>thisDay.getTime()){
            		contDate=countDate(thisDay,brithDay);
            	}else{
            		contDate="已超期"+countDate(brithDay,thisDay)+"天";
            	}
            	
            	map.put("contDate", contDate);
        	}
			dataList.add(map);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        
		return map;
		
	}
	/**
	 * getZsgPerson
	 * 正式工页面
	 * zrl
	 */
	@Override
	public HashMap<String, Object> getZsgPerson(Pager pager,String searchData,String depIds) {
		//普通员工里面的正式工
		StringBuilder sql=new StringBuilder()
		.append("  SELECT BAS.ID, BAS.DEPART_ID, BAS.JOB_NUMBER, BAS.NAME,BAS.AGE,  ")
		.append("  BAS.SEX,BAS.IS_MARRIED,BAS.JOB_LEVEL,BAS.EMP_TYPE,  ")
		.append("  BAS.ON_JOB,BAS.ON_POST,PP.POSITION_NAME PPNAME,DP.POSITION_NAME DPNAME,  ")
		.append("  BAS.FILM_ID,BR.FZX,QD.NAME DEPNAME,BAS.JOB_NUMBER_JT,BAS.BORTH_DATE  ")
		.append("  FROM JC_PT_JOB JPJ  ")
		.append("  LEFT JOIN JC_BASIC_INFORMATION BAS ON JPJ.PERSON_ID=BAS.ID  ")
		.append("  LEFT JOIN BRANCH BR ON BR.ID = BAS.FILM_ID  ")
		.append("  LEFT JOIN QX_DEPARTMENT QD ON QD.ID = BAS.DEPART_ID  ")
		.append("  LEFT JOIN POST_POSITION PP ON PP.ID = BAS.POST_ID  ")
		.append("  LEFT JOIN DEPT_POSITION DP ON DP.ID = BAS.SPECIFIC_POST_ID  ")
		.append("  where BAS.EMP_TYPE = 0  AND BAS.EMP_TYPE IN(0,1) ")
		;
		if (searchData != null){
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
				
				sql.append(" AND JPJ.DEP_ID in("+resql+")");
				
			} else {
				sql.append(" AND JPJ.DEP_ID IN("+ depIds+") ");
			}
			if (dataMap.get("jobNumber") != null && !"".equals(dataMap.get("jobNumber"))){
				sql.append(" and BAS.JOB_NUMBER ='"+dataMap.get("jobNumber")+"'");
			}
			if (dataMap.get("name") != null && !"".equals(dataMap.get("name"))){
				sql.append(" and BAS.NAME like '%"+dataMap.get("name")+"%'");
			}
			
		} else {
			sql.append(" AND JPJ.DEP_ID IN("+ depIds+") ");
		}
		sql.append(" order by QD.DESCRIPTION,PP.POSITION_CODE ");
		
		pager=this.findPagerBySql(pager, sql.toString());
		List list = pager.getResult();
		
        List dataList = new ArrayList();
		
		for (int i=0; i < list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", obj[0]);
			map.put("depId", obj[1]);
			map.put("jobNumber", obj[2]);
			map.put("name", obj[3]);
			if(obj[17]!=null&&!"".equals(obj[17])){
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
            	String thisDay = sdf.format(new Date());
            	String brithDay = sdf.format(obj[17]);
            	Integer age=Integer.parseInt(thisDay)-Integer.parseInt(brithDay);
            	map.put("age", age);
        	}
			map.put("sex", obj[5]);
			map.put("isMarried", obj[6]);
			map.put("jobLevel", obj[7]);
			map.put("empType", obj[8]);
			map.put("onJob", obj[9]);
			map.put("onPost", obj[10]);
			map.put("post", obj[11]);
			map.put("specificPost", obj[12]);
			map.put("filmId", obj[13]);
			map.put("filmName", obj[14]);
			map.put("departName", obj[15]);
			map.put("jobNumberJt", obj[16]);
			dataList.add(map);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        
		return map;
		
	}
	/**
	 * getZsgPerson
	 * 实习生list页面
	 * zrl
	 */
	@Override
	public HashMap<String, Object> getSxsPerson(Pager pager, String searchData,String depIds) {
		StringBuilder sql=new StringBuilder()
		.append("  SELECT BAS.ID, BAS.DEPART_ID, BAS.JOB_NUMBER, BAS.NAME,BAS.AGE,  ")
		.append("  BAS.SEX,BAS.IS_MARRIED,BAS.JOB_LEVEL,BAS.EMP_TYPE,  ")
		.append("  BAS.ON_JOB,BAS.ON_POST,PP.POSITION_NAME PPNAME,DP.POSITION_NAME DPNAME,  ")
		.append("  BAS.FILM_ID,BAS.FILM_NAME,BAS.DEPART_NAME,BAS.JOB_NUMBER_JT,BAS.IS_PRACTICE,BAS.PRACTICE_NUM,BAS.BORTH_DATE  ")
		.append("  FROM JC_BASIC_INFORMATION BAS ")
		.append("  LEFT JOIN POST_POSITION PP ON PP.ID = BAS.POST_ID  ")
		.append("  LEFT JOIN DEPT_POSITION DP ON DP.ID = BAS.SPECIFIC_POST_ID  ")
		.append(" where BAS.IS_DELETE = 0 AND BAS.IS_PRACTICE IN (0, 1 ,2) ")//0为结束实习的，1为正在实习的，2已经转正
		;
		if (searchData != null){
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
			if (dataMap.get("practiceNum") != null && !"".equals(dataMap.get("practiceNum"))){
				sql.append(" and BAS.PRACTICE_NUM like '%"+dataMap.get("practiceNum")+"%'");
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
		sql.append(" order by BAS.CREATEDATE DESC ");
		
		pager=this.findPagerBySql(pager, sql.toString());
		List list = pager.getResult();
		
        List dataList = new ArrayList();
		
		for (int i=0; i < list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", obj[0]);
			map.put("depId", obj[1]);
			map.put("jobNumber", obj[2]);
			map.put("name", obj[3]);
			if(obj[19]!=null&&!"".equals(obj[19])){
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
            	String thisDay = sdf.format(new Date());
            	String brithDay = sdf.format(obj[19]);
            	Integer age=Integer.parseInt(thisDay)-Integer.parseInt(brithDay);
            	map.put("age", age);
        	}
			map.put("sex", obj[5]);
			map.put("isMarried", obj[6]);
			map.put("jobLevel", obj[7]);
			map.put("empType", obj[8]);
			map.put("onJob", obj[9]);
			map.put("onPost", obj[10]);
			map.put("post", obj[11]);
			map.put("specificPost", obj[12]);
			map.put("filmId", obj[13]);
			map.put("filmName", obj[14]);
			map.put("departName", obj[15]);
			map.put("jobNumberJt", obj[16]);
			map.put("isPractice", obj[17]);
			map.put("practiceNum", obj[18]);
			dataList.add(map);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        
		return map;
	}
	/**
	 * getZsgPerson
	 * 劳务外包list页面
	 * zrl
	 */
	@Override
	public HashMap<String, Object> getLwwbPerson(Pager pager, String searchData,String depIds) {
		StringBuilder sql=new StringBuilder()
		.append("  SELECT BAS.ID, BAS.DEPART_ID, BAS.JOB_NUMBER, BAS.NAME,BAS.AGE,  ")
		.append("  BAS.SEX,BAS.IS_MARRIED,BAS.JOB_LEVEL,BAS.EMP_TYPE,  ")
		.append("  BAS.ON_JOB,BAS.ON_POST,PP.POSITION_NAME PPNAME,DP.POSITION_NAME DPNAME,  ")
		.append("  BAS.FILM_ID,BR.FZX,QD.NAME DEPNAME,BAS.JOB_NUMBER_JT,BAS.BORTH_DATE  ")
		.append("  FROM JC_PT_JOB JPJ ")
		.append("  LEFT JOIN JC_BASIC_INFORMATION BAS ON JPJ.PERSON_ID=BAS.ID ")
		.append("  LEFT JOIN BRANCH BR ON BR.ID = BAS.FILM_ID  ")
		.append("  LEFT JOIN QX_DEPARTMENT QD ON QD.ID = BAS.DEPART_ID  ")
		.append("  LEFT JOIN POST_POSITION PP ON PP.ID = BAS.POST_ID  ")
		.append("  LEFT JOIN DEPT_POSITION DP ON DP.ID = BAS.SPECIFIC_POST_ID  ")
		.append("  where BAS.IS_DELETE = 0 AND BAS.EMP_TYPE = 1 AND BAS.ON_POST = 0 ")
		;
		if (searchData != null){
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
				
				sql.append(" and JPJ.DEP_ID in("+resql+")");
				
			} else {
				sql.append(" AND JPJ.DEP_ID IN("+ depIds+") ");
			}
			if (dataMap.get("jobNumber") != null && !"".equals(dataMap.get("jobNumber"))){
				sql.append(" and BAS.JOB_NUMBER ='"+dataMap.get("jobNumber")+"'");
			}
			if (dataMap.get("name") != null && !"".equals(dataMap.get("name"))){
				sql.append(" and BAS.NAME like '%"+dataMap.get("name")+"%'");
			}
			
		} else {
			sql.append(" AND JPJ.DEP_ID IN("+ depIds+") ");
		}
		sql.append(" order by QD.DESCRIPTION,PP.POSITION_CODE ");
		
		pager=this.findPagerBySql(pager, sql.toString());
		List list = pager.getResult();
		
        List dataList = new ArrayList();
		
		for (int i=0; i < list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", obj[0]);
			map.put("depId", obj[1]);
			map.put("jobNumber", obj[2]);
			map.put("name", obj[3]);
			if(obj[17]!=null&&!"".equals(obj[17])){
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
            	String thisDay = sdf.format(new Date());
            	String brithDay = sdf.format(obj[17]);
            	Integer age=Integer.parseInt(thisDay)-Integer.parseInt(brithDay);
            	map.put("age", age);
        	}
			map.put("sex", obj[5]);
			map.put("isMarried", obj[6]);
			map.put("jobLevel", obj[7]);
			map.put("empType", obj[8]);
			map.put("onJob", obj[9]);
			map.put("onPost", obj[10]);
			map.put("post", obj[11]);
			map.put("specificPost", obj[12]);
			map.put("filmId", obj[13]);
			map.put("filmName", obj[14]);
			map.put("departName", obj[15]);
			map.put("jobNumberJt", obj[16]);
			dataList.add(map);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        
		return map;
	}
	/**
	 * getZsgPerson
	 * 业务外包list页面
	 * zrl
	 */
	@Override
	public HashMap<String, Object> getYwwbPerson(Pager pager, String searchData,String depIds) {
		StringBuilder sql=new StringBuilder()
		.append("  SELECT BAS.ID, BAS.DEPART_ID, BAS.JOB_NUMBER, BAS.NAME,BAS.AGE,  ")
		.append("  BAS.SEX,BAS.IS_MARRIED,BAS.JOB_LEVEL,BAS.EMP_TYPE,  ")
		.append("  BAS.ON_JOB,BAS.ON_POST,PP.POSITION_NAME PPNAME,DP.POSITION_NAME DPNAME,  ")
		.append("  BAS.FILM_ID,BR.FZX,QD.NAME DEPNAME,BAS.JOB_NUMBER_JT,BAS.BORTH_DATE  ")
		.append("  FROM JC_BASIC_INFORMATION BAS ")
		.append("  LEFT JOIN BRANCH BR ON BR.ID = BAS.FILM_ID  ")
		.append("  LEFT JOIN QX_DEPARTMENT QD ON QD.ID = BAS.DEPART_ID  ")
		.append("  LEFT JOIN POST_POSITION PP ON PP.ID = BAS.POST_ID  ")
		.append("  LEFT JOIN DEPT_POSITION DP ON DP.ID = BAS.SPECIFIC_POST_ID  ")
		.append("  where BAS.IS_DELETE = 0 AND BAS.EMP_TYPE = 2 AND BAS.ON_POST = 0 ")
		;
		if (searchData != null){
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
				sql.append(" and BAS.JOB_NUMBER ='"+dataMap.get("jobNumber")+"'");
			}
			if (dataMap.get("name") != null && !"".equals(dataMap.get("name"))){
				sql.append(" and BAS.NAME like '%"+dataMap.get("name")+"%'");
			}
			
		} else {
			sql.append(" AND BAS.DEPART_ID IN("+ depIds+") ");
		}
		sql.append(" order by QD.DESCRIPTION,PP.POSITION_CODE ");
		
		pager=this.findPagerBySql(pager, sql.toString());
		List list = pager.getResult();
		
        List dataList = new ArrayList();
		
		for (int i=0; i < list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", obj[0]);
			map.put("depId", obj[1]);
			map.put("jobNumber", obj[2]);
			map.put("name", obj[3]);
			if(obj[17]!=null&&!"".equals(obj[17])){
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
            	String thisDay = sdf.format(new Date());
            	String brithDay = sdf.format(obj[17]);
            	Integer age=Integer.parseInt(thisDay)-Integer.parseInt(brithDay);
            	map.put("age", age);
        	}
			map.put("sex", obj[5]);
			map.put("isMarried", obj[6]);
			map.put("jobLevel", obj[7]);
			map.put("empType", obj[8]);
			map.put("onJob", obj[9]);
			map.put("onPost", obj[10]);
			map.put("post", obj[11]);
			map.put("specificPost", obj[12]);
			map.put("filmId", obj[13]);
			map.put("filmName", obj[14]);
			map.put("departName", obj[15]);
			map.put("jobNumberJt", obj[16]);
			dataList.add(map);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        
		return map;
	}
	
	/**
	 * getZsgPerson
	 * 退休list页面
	 * 劳务外包人员，业务外包人员，正式工的退休
	 * zrl
	 */
	@Override
	public HashMap<String, Object> getTxPerson(Pager pager, String searchData) {
		StringBuilder sql=new StringBuilder()
		.append("  SELECT BAS.ID, BAS.DEPART_ID, BAS.JOB_NUMBER, BAS.NAME,BAS.AGE,  ")
		.append("  BAS.SEX,BAS.IS_MARRIED,BAS.JOB_LEVEL,BAS.EMP_TYPE,  ")
		.append("  BAS.ON_JOB,BAS.ON_POST,PP.POSITION_NAME PPNAME,DP.POSITION_NAME DPNAME,  ")
		.append("  BAS.FILM_ID,BR.FZX,QD.NAME DEPNAME,BAS.JOB_NUMBER_JT,  ")
		.append("  JER.LEAVE_TYPE,TO_CHAR(JER.LEAVE_DATE,'YYYY-MM-DD'),JER.PRE_FIRM,JER.PRE_DEP,  ")
		.append("  BR.FZX FZX2,QD.NAME DEPNAME2,JER.APPROVE_FIRM,JER.REMARK,BAS.BORTH_DATE  ")
		.append("  FROM JC_BASIC_INFORMATION BAS ")
		.append("  LEFT JOIN POST_POSITION PP ON PP.ID = BAS.POST_ID  ")
		.append("  LEFT JOIN DEPT_POSITION DP ON DP.ID = BAS.SPECIFIC_POST_ID  ")
		.append("  LEFT JOIN JC_EMP_RETIRE JER ON BAS.ID=JER.PERSON_ID  ")
		.append("  LEFT JOIN BRANCH BR ON BR.ID = JER.LATER_FIRM  ")
		.append("  LEFT JOIN QX_DEPARTMENT QD ON QD.ID = JER.LATER_DEP  ")
		.append("  where BAS.IS_DELETE = 0 AND BAS.ON_JOB = 4 AND BAS.EMP_TYPE IN(0,1) ")
		;
		if (searchData != null){
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
				
			}
			if (dataMap.get("jobNumber") != null && !"".equals(dataMap.get("jobNumber"))){
				sql.append(" and BAS.JOB_NUMBER ='"+dataMap.get("jobNumber")+"'");
			}
			if (dataMap.get("name") != null && !"".equals(dataMap.get("name"))){
				sql.append(" and BAS.NAME like '%"+dataMap.get("name")+"%'");
			}
			
		}
		sql.append(" order by JER.LEAVE_DATE DESC ");
		
		pager=this.findPagerBySql(pager, sql.toString());
		List list = pager.getResult();
		
        List dataList = new ArrayList();
		
		for (int i=0; i < list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", obj[0]);
			map.put("depId", obj[1]);
			map.put("jobNumber", obj[2]);
			map.put("name", obj[3]);
			if(obj[25]!=null&&!"".equals(obj[25])){
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
            	String thisDay = sdf.format(new Date());
            	String brithDay = sdf.format(obj[25]);
            	Integer age=Integer.parseInt(thisDay)-Integer.parseInt(brithDay);
            	map.put("age", age);
        	}
			map.put("sex", obj[5]);
			map.put("isMarried", obj[6]);
			map.put("jobLevel", obj[7]);
			map.put("empType", obj[8]);
			map.put("onJob", obj[9]);
			map.put("onPost", obj[10]);
			map.put("post", obj[11]);
			map.put("specificPost", obj[12]);
			map.put("filmId", obj[13]);
			map.put("filmName", obj[14]);
			map.put("departName", obj[15]);
			map.put("jobNumberJt", obj[16]);
			map.put("leaveType", obj[17]);
			map.put("leaveDate", obj[18]);
			map.put("preFirm", obj[19]);
			map.put("preDep", obj[20]);
			map.put("laterFirm", obj[21]);
			map.put("laterDep", obj[22]);
			map.put("approveFirm", obj[23]);
			map.put("remark", obj[24]);
			dataList.add(map);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        
		return map;
	}
	/**
	 * getZsgPerson
	 * 离职list页面
	 * 劳务外包人员，业务外包人员，正式工的离职
	 * zrl
	 */
	@Override
	public HashMap<String, Object> getLzPerson(Pager pager, String searchData) {
		StringBuilder sql=new StringBuilder()
		.append("  SELECT BAS.ID PERSONID, BAS.DEPART_ID, BAS.JOB_NUMBER, BAS.NAME,BAS.AGE,  ")
		.append("  BAS.SEX,BAS.IS_MARRIED,BAS.JOB_LEVEL,BAS.EMP_TYPE,  ")
		.append("  BAS.ON_JOB,BAS.ON_POST,PP.POSITION_NAME PPNAME,DP.POSITION_NAME DPNAME,  ")
		.append("  BAS.FILM_ID,BAS.FILM_NAME,BAS.DEPART_NAME,BAS.JOB_NUMBER_JT,  ")
		
		.append("  JED.LEAVE_TYPE,TO_CHAR(JED.LEAVE_DATE,'YYYY-MM-DD'),JED.DIRECTION,  ")
		.append("  JED.FILE_NUMBER,JED.APPROVER,JED.ISHITLIST,JED.ISCOMPENSATE,JED.COST,  ")
		.append("  JED.ISDTZJ,JED.REMARK,BAS.BORTH_DATE  ")
		.append("  FROM JC_BASIC_INFORMATION BAS ")
		.append("  LEFT JOIN POST_POSITION PP ON PP.ID = BAS.POST_ID  ")
		.append("  LEFT JOIN DEPT_POSITION DP ON DP.ID = BAS.SPECIFIC_POST_ID  ")
		.append("  LEFT JOIN JC_EMP_DIMISSION JED ON JED.PERSON_ID = BAS.ID  ")
		.append("  where BAS.IS_DELETE = 0 AND BAS.ON_JOB = 3 AND BAS.EMP_TYPE IN(0,1) ")
		;
		if (searchData != null){
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
				
			}
			if (dataMap.get("jobNumber") != null && !"".equals(dataMap.get("jobNumber"))){
				sql.append(" and BAS.JOB_NUMBER ='"+dataMap.get("jobNumber")+"'");
			}
			if (dataMap.get("name") != null && !"".equals(dataMap.get("name"))){
				sql.append(" and BAS.NAME like '%"+dataMap.get("name")+"%'");
			}
			
		}
		sql.append(" order by JED.LEAVE_DATE DESC ");
		
		pager=this.findPagerBySql(pager, sql.toString());
		List list = pager.getResult();
		
        List dataList = new ArrayList();
		
		for (int i=0; i < list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", obj[0]);
			map.put("depId", obj[1]);
			map.put("jobNumber", obj[2]);
			map.put("name", obj[3]);
			if(obj[27]!=null&&!"".equals(obj[27])){
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
            	String thisDay = sdf.format(new Date());
            	String brithDay = sdf.format(obj[27]);
            	Integer age=Integer.parseInt(thisDay)-Integer.parseInt(brithDay);
            	map.put("age", age);
        	}
			map.put("sex", obj[5]);
			map.put("isMarried", obj[6]);
			map.put("jobLevel", obj[7]);
			map.put("empType", obj[8]);
			map.put("onJob", obj[9]);
			map.put("onPost", obj[10]);
			map.put("post", obj[11]);
			map.put("specificPost", obj[12]);
			map.put("filmId", obj[13]);
			map.put("filmName", obj[14]);
			map.put("departName", obj[15]);
			map.put("jobNumberJt", obj[16]);
			map.put("leaveType", obj[17]);
			map.put("leaveDate", obj[18]);
			map.put("direction", obj[19]);
			map.put("fileNumber", obj[20]);
			map.put("approver", obj[21]);
			map.put("ishitlist", obj[22]);
			map.put("iscompensate", obj[23]);
			map.put("cost", obj[24]);
			map.put("isdtzj", obj[25]);
			map.put("remark", obj[26]);
			dataList.add(map);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        
		return map;
	}
	/**
	 * getZsgPerson
	 * 返聘list页面
	 * 劳务外包人员，，正式工的返聘
	 * zrl
	 */
	@Override
	public HashMap<String, Object> getFpPerson(Pager pager, String searchData,String depIds) {
		StringBuilder sql=new StringBuilder()
		.append("  SELECT BAS.ID, BAS.DEPART_ID, BAS.JOB_NUMBER, BAS.NAME,BAS.AGE,  ")
		.append("  BAS.SEX,BAS.IS_MARRIED,BAS.JOB_LEVEL,BAS.EMP_TYPE,  ")
		.append("  BAS.ON_JOB,BAS.ON_POST,PP.POSITION_NAME PPNAME,DP.POSITION_NAME DPNAME,  ")
		.append("  BAS.FILM_ID,BR.FZX,QD.NAME DEPNAME,BAS.JOB_NUMBER_JT,BAS.BORTH_DATE  ")
		.append("  FROM JC_BASIC_INFORMATION BAS ")
		.append("  LEFT JOIN POST_POSITION PP ON PP.ID = BAS.POST_ID  ")
		.append("  LEFT JOIN DEPT_POSITION DP ON DP.ID = BAS.SPECIFIC_POST_ID  ")
		.append("  LEFT JOIN BRANCH BR ON BR.ID = BAS.FILM_ID  ")
		.append("  LEFT JOIN QX_DEPARTMENT QD ON QD.ID = BAS.DEPART_ID  ")
		.append(" where BAS.IS_DELETE = 0 AND BAS.ON_JOB = 5 AND BAS.ON_POST = 0 AND BAS.EMP_TYPE IN(0,1) ")
		;
		if (searchData != null){
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
				sql.append(" and BAS.JOB_NUMBER ='"+dataMap.get("jobNumber")+"'");
			}
			if (dataMap.get("name") != null && !"".equals(dataMap.get("name"))){
				sql.append(" and BAS.NAME like '%"+dataMap.get("name")+"%'");
			}
			
		} else {
			sql.append(" AND BAS.DEPART_ID IN("+ depIds+") ");
		}
		sql.append(" order by BAS.CREATEDATE DESC ");
		
		pager=this.findPagerBySql(pager, sql.toString());
		List list = pager.getResult();
		
        List dataList = new ArrayList();
		
		for (int i=0; i < list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", obj[0]);
			map.put("depId", obj[1]);
			map.put("jobNumber", obj[2]);
			map.put("name", obj[3]);
			if(obj[17]!=null&&!"".equals(obj[17])){
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
            	String thisDay = sdf.format(new Date());
            	String brithDay = sdf.format(obj[17]);
            	Integer age=Integer.parseInt(thisDay)-Integer.parseInt(brithDay);
            	map.put("age", age);
        	}
			map.put("sex", obj[5]);
			map.put("isMarried", obj[6]);
			map.put("jobLevel", obj[7]);
			map.put("empType", obj[8]);
			map.put("onJob", obj[9]);
			map.put("onPost", obj[10]);
			map.put("post", obj[11]);
			map.put("specificPost", obj[12]);
			map.put("filmId", obj[13]);
			map.put("filmName", obj[14]);
			map.put("departName", obj[15]);
			map.put("jobNumberJt", obj[16]);
			dataList.add(map);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        
		return map;
	}
	/**
	 * getZsgPerson
	 * 返聘list页面
	 * 退伍军人
	 * zrl
	 */
	@Override
	public HashMap<String, Object> getFyjrPerson(Pager pager, String searchData,String depIds) {
		StringBuilder sql=new StringBuilder()
		.append("  SELECT BAS.ID, BAS.DEPART_ID, BAS.JOB_NUMBER, BAS.NAME,BAS.AGE,  ")
		.append("  BAS.SEX,BAS.IS_MARRIED,BAS.JOB_LEVEL,BAS.EMP_TYPE,  ")
		.append("  BAS.ON_JOB,BAS.ON_POST,PP.POSITION_NAME PPNAME,DP.POSITION_NAME DPNAME,  ")
		.append("  BAS.FILM_ID,BR.FZX,QD.NAME DEPNAME,BAS.JOB_NUMBER_JT,BAS.BORTH_DATE  ")
		.append("  FROM JC_BASIC_INFORMATION BAS ")
		.append("  LEFT JOIN BRANCH BR ON BR.ID = BAS.FILM_ID  ")
		.append("  LEFT JOIN QX_DEPARTMENT QD ON QD.ID = BAS.DEPART_ID  ")
		.append("  LEFT JOIN POST_POSITION PP ON PP.ID = BAS.POST_ID  ")
		.append("  LEFT JOIN DEPT_POSITION DP ON DP.ID = BAS.SPECIFIC_POST_ID  ")
		.append(" where BAS.IS_DELETE = 0 AND BAS.IS_SOLDIER = 1 AND BAS.EMP_TYPE IN(0,1) ")
		;
		if (searchData != null){
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
				sql.append(" and BAS.JOB_NUMBER ='"+dataMap.get("jobNumber")+"'");
			}
			if (dataMap.get("name") != null && !"".equals(dataMap.get("name"))){
				sql.append(" and BAS.NAME like '%"+dataMap.get("name")+"%'");
			}
			
		} else {
			sql.append(" AND BAS.DEPART_ID IN("+ depIds+") ");
		}
		sql.append(" order by QD.DESCRIPTION,PP.POSITION_CODE ");
		
		pager=this.findPagerBySql(pager, sql.toString());
		List list = pager.getResult();
		
        List dataList = new ArrayList();
		
		for (int i=0; i < list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", obj[0]);
			map.put("depId", obj[1]);
			map.put("jobNumber", obj[2]);
			map.put("name", obj[3]);
			if(obj[17]!=null&&!"".equals(obj[17])){
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
            	String thisDay = sdf.format(new Date());
            	String brithDay = sdf.format(obj[17]);
            	Integer age=Integer.parseInt(thisDay)-Integer.parseInt(brithDay);
            	map.put("age", age);
        	}
			map.put("sex", obj[5]);
			map.put("isMarried", obj[6]);
			map.put("jobLevel", obj[7]);
			map.put("empType", obj[8]);
			map.put("onJob", obj[9]);
			map.put("onPost", obj[10]);
			map.put("post", obj[11]);
			map.put("specificPost", obj[12]);
			map.put("filmId", obj[13]);
			map.put("filmName", obj[14]);
			map.put("departName", obj[15]);
			map.put("jobNumberJt", obj[16]);
			dataList.add(map);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        
		return map;
	}

	/**
	 * getZsgPerson
	 * 非在岗人员
	 * zrl
	 */
	@Override
	public HashMap<String, Object> getFzgPerson(Pager pager, String searchData,String depIds) {
		StringBuilder sql=new StringBuilder()
		.append("  SELECT BAS.ID, BAS.DEPART_ID, BAS.JOB_NUMBER, BAS.NAME,BAS.AGE,  ")
		.append("  BAS.SEX,BAS.IS_MARRIED,BAS.JOB_LEVEL,BAS.EMP_TYPE,  ")
		.append("  BAS.ON_JOB,BAS.ON_POST,PP.POSITION_NAME PPNAME,DP.POSITION_NAME DPNAME,  ")
		.append("  BAS.FILM_ID,BR.FZX,QD.NAME DEPNAME,BAS.JOB_NUMBER_JT,BAS.BORTH_DATE  ")
		.append("  FROM JC_BASIC_INFORMATION BAS ")
		.append("  LEFT JOIN BRANCH BR ON BR.ID = BAS.FILM_ID  ")
		.append("  LEFT JOIN QX_DEPARTMENT QD ON QD.ID = BAS.DEPART_ID  ")
		.append("  LEFT JOIN POST_POSITION PP ON PP.ID = BAS.POST_ID  ")
		.append("  LEFT JOIN DEPT_POSITION DP ON DP.ID = BAS.SPECIFIC_POST_ID  ")
		.append(" where BAS.IS_DELETE = 0 AND BAS.ON_POST <> 0  AND BAS.EMP_TYPE IN(0,1) ")
		;
		if (searchData != null){
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
				sql.append(" and BAS.JOB_NUMBER ='"+dataMap.get("jobNumber")+"'");
			}
			if (dataMap.get("name") != null && !"".equals(dataMap.get("name"))){
				sql.append(" and BAS.NAME like '%"+dataMap.get("name")+"%'");
			}
			
		} else {
			sql.append(" AND BAS.DEPART_ID IN("+ depIds+") ");
		}
		sql.append(" order by BAS.CREATEDATE DESC ");
		
		pager=this.findPagerBySql(pager, sql.toString());
		List list = pager.getResult();
		
        List dataList = new ArrayList();
		
		for (int i=0; i < list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", obj[0]);
			map.put("depId", obj[1]);
			map.put("jobNumber", obj[2]);
			map.put("name", obj[3]);
			if(obj[17]!=null&&!"".equals(obj[17])){
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
            	String thisDay = sdf.format(new Date());
            	String brithDay = sdf.format(obj[17]);
            	Integer age=Integer.parseInt(thisDay)-Integer.parseInt(brithDay);
            	map.put("age", age);
        	}
			map.put("sex", obj[5]);
			map.put("isMarried", obj[6]);
			map.put("jobLevel", obj[7]);
			map.put("empType", obj[8]);
			map.put("onJob", obj[9]);
			map.put("onPost", obj[10]);
			map.put("post", obj[11]);
			map.put("specificPost", obj[12]);
			map.put("filmId", obj[13]);
			map.put("filmName", obj[14]);
			map.put("departName", obj[15]);
			map.put("jobNumberJt", obj[16]);
			dataList.add(map);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        
		return map;
	}

	/**
	 * getZsgPerson
	 * 技术工人
	 * zrl
	 */
	@Override
	public HashMap<String, Object> getJsgrPerson(Pager pager, String searchData,String depIds) {
		StringBuilder sql=new StringBuilder()
		.append("  SELECT BAS.ID, BAS.DEPART_ID, BAS.JOB_NUMBER, BAS.NAME,BAS.AGE,  ")
		.append("  BAS.SEX,BAS.IS_MARRIED,BAS.JOB_LEVEL,BAS.EMP_TYPE,  ")
		.append("  BAS.ON_JOB,BAS.ON_POST,PP.POSITION_NAME PPNAME,DP.POSITION_NAME DPNAME,  ")
		.append("  BAS.FILM_ID,BR.FZX,QD.NAME DEPNAME,BAS.JOB_NUMBER_JT,BAS.BORTH_DATE  ")
		.append("  FROM JC_BASIC_INFORMATION BAS ")
		.append("  LEFT JOIN BRANCH BR ON BR.ID = BAS.FILM_ID  ")
		.append("  LEFT JOIN QX_DEPARTMENT QD ON QD.ID = BAS.DEPART_ID  ")
		.append("  LEFT JOIN POST_POSITION PP ON PP.ID = BAS.POST_ID  ")
		.append("  LEFT JOIN DEPT_POSITION DP ON DP.ID = BAS.SPECIFIC_POST_ID  ")
		.append(" where BAS.IS_DELETE = 0 AND BAS.ON_JOB IN(1,2,5) AND BAS.IS_SKILLED=1  AND BAS.EMP_TYPE IN(0,1)")
		;
		if (searchData != null){
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
				sql.append(" and BAS.JOB_NUMBER ='"+dataMap.get("jobNumber")+"'");
			}
			if (dataMap.get("name") != null && !"".equals(dataMap.get("name"))){
				sql.append(" and BAS.NAME like '%"+dataMap.get("name")+"%'");
			}
			
		} else {
			sql.append(" AND BAS.DEPART_ID IN("+ depIds+") ");
		}
		sql.append(" order by QD.DESCRIPTION,PP.POSITION_CODE ");
		
		pager=this.findPagerBySql(pager, sql.toString());
		List list = pager.getResult();
		
        List dataList = new ArrayList();
		
		for (int i=0; i < list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", obj[0]);
			map.put("depId", obj[1]);
			map.put("jobNumber", obj[2]);
			map.put("name", obj[3]);
			if(obj[17]!=null&&!"".equals(obj[17])){
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
            	String thisDay = sdf.format(new Date());
            	String brithDay = sdf.format(obj[17]);
            	Integer age=Integer.parseInt(thisDay)-Integer.parseInt(brithDay);
            	map.put("age", age);
        	}
			map.put("sex", obj[5]);
			map.put("isMarried", obj[6]);
			map.put("jobLevel", obj[7]);
			map.put("empType", obj[8]);
			map.put("onJob", obj[9]);
			map.put("onPost", obj[10]);
			map.put("post", obj[11]);
			map.put("specificPost", obj[12]);
			map.put("filmId", obj[13]);
			map.put("filmName", obj[14]);
			map.put("departName", obj[15]);
			map.put("jobNumberJt", obj[16]);
			dataList.add(map);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        
		return map;
	}

	/**
	 * getZsgPerson
	 * 专业技术人员
	 * zrl
	 */
	@Override
	public HashMap<String, Object> getZyjsryPerson(Pager pager,
			String searchData,String depIds) {
		StringBuilder sql=new StringBuilder()
		.append("  SELECT BAS.ID, BAS.DEPART_ID, BAS.JOB_NUMBER, BAS.NAME,BAS.AGE,  ")
		.append("  BAS.SEX,BAS.IS_MARRIED,BAS.JOB_LEVEL,BAS.EMP_TYPE,  ")
		.append("  BAS.ON_JOB,BAS.ON_POST,PP.POSITION_NAME PPNAME,DP.POSITION_NAME DPNAME,  ")
		.append("  BAS.FILM_ID,BR.FZX,QD.NAME DEPNAME,BAS.JOB_NUMBER_JT,BAS.BORTH_DATE  ")
		.append("  FROM JC_BASIC_INFORMATION BAS ")
		.append("  LEFT JOIN BRANCH BR ON BR.ID = BAS.FILM_ID  ")
		.append("  LEFT JOIN QX_DEPARTMENT QD ON QD.ID = BAS.DEPART_ID  ")
		.append("  LEFT JOIN POST_POSITION PP ON PP.ID = BAS.POST_ID  ")
		.append("  LEFT JOIN DEPT_POSITION DP ON DP.ID = BAS.SPECIFIC_POST_ID  ")
		.append("  where BAS.IS_DELETE = 0 AND BAS.ON_JOB IN(1,2,5) AND BAS.IS_SPECIALTY=1  AND BAS.EMP_TYPE IN(0,1) ")
		;
		if (searchData != null){
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
				sql.append(" and BAS.JOB_NUMBER ='"+dataMap.get("jobNumber")+"'");
			}
			if (dataMap.get("name") != null && !"".equals(dataMap.get("name"))){
				sql.append(" and BAS.NAME like '%"+dataMap.get("name")+"%'");
			}
			
		} else {
			sql.append(" AND BAS.DEPART_ID IN("+ depIds+") ");
		}
		sql.append(" order by QD.DESCRIPTION,PP.POSITION_CODE ");
		
		pager=this.findPagerBySql(pager, sql.toString());
		List list = pager.getResult();
		
        List dataList = new ArrayList();
		
		for (int i=0; i < list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", obj[0]);
			map.put("depId", obj[1]);
			map.put("jobNumber", obj[2]);
			map.put("name", obj[3]);
			if(obj[17]!=null&&!"".equals(obj[17])){
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
            	String thisDay = sdf.format(new Date());
            	String brithDay = sdf.format(obj[17]);
            	Integer age=Integer.parseInt(thisDay)-Integer.parseInt(brithDay);
            	map.put("age", age);
        	}
			map.put("sex", obj[5]);
			map.put("isMarried", obj[6]);
			map.put("jobLevel", obj[7]);
			map.put("empType", obj[8]);
			map.put("onJob", obj[9]);
			map.put("onPost", obj[10]);
			map.put("post", obj[11]);
			map.put("specificPost", obj[12]);
			map.put("filmId", obj[13]);
			map.put("filmName", obj[14]);
			map.put("departName", obj[15]);
			map.put("jobNumberJt", obj[16]);
			dataList.add(map);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("data", dataList);
        map.put("total", pager.getTotalCount());
        
		return map;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getAnnualDataList(String searchData, String depIds) {
		List<Map<String, String>> listDate = new ArrayList<Map<String, String>>();
		String sql = "select JOB_NUMBER, NAME, CLASS_NAME, to_char(SET_WORK_DATE, 'yyyy-MM-dd'), "
				+ "to_char(IN_PORT_TIME, 'yyyy-MM-dd') "
				+ "from JC_BASIC_INFORMATION jbi "
				+ "left join CK_CLASS cc on jbi.CLASS_NO = cc.CLASS_NO "
				+ "where jbi.IS_DELETE = 0 ";
		if (searchData != null){
			Map<String,String> dataMap = JsonUtil.parseProperties(searchData);
			if (dataMap.get("depId") != null && !"".equals(dataMap.get("depId"))){
				String[] depIdArrs = dataMap.get("depId").split(",");
				sql += "and jbi.CHECK_UNIT_ID in(";
				for (int i = 0; i < depIdArrs.length; i++){
					if (!"".equals(depIdArrs[i])){
						sql += ("'" + depIdArrs[i] + "', ");
					}
				}
				sql = sql.substring(0, sql.length()-2);
				sql += ") ";
			} else {
				sql += "and jbi.CHECK_UNIT_ID IN("+ depIds +") ";
			}
		} else {
			sql += "and jbi.CHECK_UNIT_ID IN("+ depIds +") ";
		}
		List<Object[]> list = this.getSession().createSQLQuery(sql).list();
		if (list.size() > 0){
			for (Object[] obj:list){
				Map<String, String> map = new HashMap<String, String>();
				map.put("jobNumber", obj[0]+"");
				map.put("name", obj[1]+"");
				map.put("className", obj[2]+"");
				map.put("workDate", obj[3]+"");//起始工作日期
				map.put("portDate", obj[4]+"");//入港日期
				map.put("legalDay", "");//全部
				map.put("shouldTake", "");//应休
				map.put("month1", "");
				map.put("month2", "");
				map.put("month3", "");
				map.put("month4", "");
				map.put("month5", "");
				map.put("month6", "");
				map.put("month7", "");
				map.put("month8", "");
				map.put("month9", "");
				map.put("month10", "");
				map.put("month11", "");
				map.put("month12", "");
				map.put("haveTake", "");//已休
				map.put("surplus", "");//剩余
				listDate.add(map);
			}
		}
		return listDate;
	}
	
	/**
     * 计算时间差
     * zrl
     */
    public String countDate(Date date1,Date date2){
    	long time1 = date1.getTime();  
        long time2 = date2.getTime();  
        long diff ;  
        
        diff = time2 - time1;  
        long days = (diff / (1000 * 60 * 60 * 24)); 
        String day=String.valueOf(days);
        return day;
         
    }

    
    
    /**
	 * 动态报表导出
	 */
	public List getExceptPerson(String searchData,String depIds,String thetype) {
		StringBuilder sql=new StringBuilder()
		.append("  SELECT BAS.ID, BAS.DEPART_ID, BAS.JOB_NUMBER, BAS.NAME,BAS.AGE,  ")
		.append("  BAS.SEX,BAS.IS_MARRIED,BAS.JOB_LEVEL,BAS.EMP_TYPE,  ")
		.append("  BAS.ON_JOB,BAS.ON_POST,PP.POSITION_NAME PPNAME,DP.POSITION_NAME DPNAME,  ")
		.append("  BAS.FILM_ID,BR.FZX,QD.NAME DEPNAME,BAS.JOB_NUMBER_JT,BAS.BORTH_DATE,  ")
		.append("  BAS.ID_TYPE,BAS.ID_NUMBER,BAS.LABOR_COMPANY,JEE.EDUCATION_LEVER JEELEVER,JEE2.EDUCATION_LEVER JEELEVER2, ")
		.append("  BAS.WORK_TYPE,BAS.OLD_NAME,BAS.NATION,BAS.BORTH_PLACE_P,BAS.BORTH_PLACE_C,BAS.BORTH_PLACE_A,BAS.BORTH_PLACE,  ")
		.append("  BAS.NATIVE_PLACE_P,BAS.NATIVE_PLACE_C,BAS.NATIVE_PLACE_A,BAS.NATIVE_PLACE,TO_CHAR(BAS.SET_WORK_DATE,'YYYY-MM-DD'),  ")
		.append("  BAS.ANNUAL_LEAVE,BAS.POLITICS_STATUS,BAS.HIGHEST_TITLE,BAS.PRO_QUA_LEVEL,BAS.FOREIGN_TYPE,  ")
		.append("  BAS.ENGLISH,BAS.COMPUTER_LEVEL,BAS.HOBBY,BAS.EMP_SOURCE,BAS.FORMER_IDENTITY,BAS.HEIGHT,BAS.WEIGHT,  ")
		.append("  BAS.BLOOD,BAS.HEALTH,BAS.IS_SOLDIER,BAS.REMARK1,BAS.REMARK3,  ")
		.append("  BAS.OFFICE_PHONE,BAS.PHONE,BAS.OFFICE_EMAIL,BAS.EMAIL,BAS.RESIDENCE_PLACE_P,  ")
		.append("  BAS.RESIDENCE_PLACE_C,BAS.RESIDENCE_PLACE_A,BAS.RESIDENCE_PLACE,  ")
		.append("  BAS.ADDRESS_P,BAS.ADDRESS_C,BAS.ADDRESS_A,BAS.ADDRESS,BAS.MESSAGE_ADDRESS,  ")
		.append("  BAS.URGENT_PEOPLE,BAS.UP_PHONE,BAS.UP_ADDRESS_P,BAS.UP_ADDRESS_C,BAS.UP_ADDRESS_A,  ")
		.append("  BAS.UP_ADDRESS,TO_CHAR(BAS.IN_COMPANY_TIME,'YYYY-MM-DD'),TO_CHAR(BAS.IN_COMPANY_TIME2,'YYYY-MM-DD'),  ")
		.append("  TO_CHAR(BAS.IN_PORT_TIME,'YYYY-MM-DD'),TO_CHAR(BAS.IN_PORT_TIME2,'YYYY-MM-DD'),  ")
		.append("  BAS.LABOR_COMPANY COM11,BAS.IS_SKILLED,BAS.IS_SPECIALTY,BAS.JGBZ,TO_CHAR(BAS.BEGIN_TIME,'YYYY-MM-DD'),BAS.CAREER, ")
		.append("  JEE.DEGREE DEGREE1,JEE2.DEGREE DEGREE2,TO_CHAR(JEE.ENTRANCE_DATE,'YYYY-MM-DD') ENTRANCE_DATE1, ")
		.append("  TO_CHAR(JEE2.ENTRANCE_DATE,'YYYY-MM-DD') ENTRANCE_DATE2,TO_CHAR(JEE.GRADUATE_DATE,'YYYY-MM-DD') GRADUATE_DATE1,  ")
		.append("  TO_CHAR(JEE2.GRADUATE_DATE,'YYYY-MM-DD') GRADUATE_DATE2,JEE.SCHOOL SCOOL1,JEE2.SCHOOL SCHOOL2,JEE.PROFESSION PROFESSION1,JEE2.PROFESSION PROFESSION2  ")
		;
		
		sql.append("  FROM JC_PT_JOB JPJ ");
		sql.append("  LEFT JOIN JC_BASIC_INFORMATION BAS ON JPJ.PERSON_ID=BAS.ID ");
		sql.append("  LEFT JOIN BRANCH BR ON BR.ID = BAS.FILM_ID  ");
		sql.append("  LEFT JOIN QX_DEPARTMENT QD ON QD.ID = BAS.DEPART_ID  ");
		sql.append("  LEFT JOIN POST_POSITION PP ON PP.ID = BAS.POST_ID  ");
		sql.append("  LEFT JOIN DEPT_POSITION DP ON DP.ID = BAS.SPECIFIC_POST_ID  ");
		sql.append("  LEFT JOIN JC_EDUCATION_EXPERRIENCE JEE ON JEE.PERSON_ID = BAS.ID AND JEE.HIGHEST_EDUCATION=1 AND JEE.IS_ALLDAY='1'  ");
		sql.append("  LEFT JOIN JC_EDUCATION_EXPERRIENCE JEE2 ON JEE2.PERSON_ID = BAS.ID AND JEE2.HIGHEST_EDUCATION=1 AND JEE2.IS_ALLDAY='0'  ");
		
		sql.append("  WHERE BAS.IS_DELETE = 0 AND BAS.ON_JOB NOT IN(0,3,4) AND BAS.EMP_TYPE IN(0,1) ");
		if (searchData != null){
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
				
				sql.append(" and JPJ.DEP_ID in("+resql+")");
				
			} else {
				sql.append(" AND JPJ.DEP_ID IN("+ depIds+") ");
			}
			if (dataMap.get("jobNumber") != null && !"".equals(dataMap.get("jobNumber"))){
				sql.append(" and BAS.JOB_NUMBER LIKE'"+dataMap.get("jobNumber")+"'");
			}
			if (dataMap.get("name") != null && !"".equals(dataMap.get("name"))){
				sql.append(" and BAS.NAME like '%"+dataMap.get("name")+"%'");
			}
			if (dataMap.get("personId") != null && !"".equals(dataMap.get("personId"))){
				sql.append(" and BAS.ID = '"+dataMap.get("personId")+"'");
			}
			if (dataMap.get("positionId") != null && !"".equals(dataMap.get("positionId"))){
				sql.append(" and BAS.POST_ID = '"+dataMap.get("positionId")+"'");
			}
			if (dataMap.get("jobLevel") != null && !"".equals(dataMap.get("jobLevel"))){
				sql.append(" and BAS.JOB_LEVEL = '"+dataMap.get("jobLevel")+"'");
			}
			if (dataMap.get("agebegin") != null && !"".equals(dataMap.get("agebegin"))){
				sql.append(" and to_char(sysdate,'YYYY')-to_char(BAS.BORTH_DATE,'YYYY') >= '"+dataMap.get("agebegin")+"'");
			}
			if (dataMap.get("ageend") != null && !"".equals(dataMap.get("ageend"))){
				sql.append(" and to_char(sysdate,'YYYY')-to_char(BAS.BORTH_DATE,'YYYY') <= '"+dataMap.get("ageend")+"'");
			}
			if (dataMap.get("empType") != null && !"".equals(dataMap.get("empType"))){
				sql.append(" and BAS.EMP_TYPE = '"+dataMap.get("empType")+"'");
			}
			if (dataMap.get("onJob") != null && !"".equals(dataMap.get("onJob"))){
				sql.append(" and BAS.ON_JOB = '"+dataMap.get("onJob")+"'");
			}
			if (dataMap.get("onPost") != null && !"".equals(dataMap.get("onPost"))){
				sql.append(" and BAS.ON_POST = '"+dataMap.get("onPost")+"'");
			}
			if (dataMap.get("sex") != null && !"".equals(dataMap.get("sex"))){
				sql.append(" and BAS.SEX = '"+dataMap.get("sex")+"'");
			}
			if (dataMap.get("isMarried") != null && !"".equals(dataMap.get("isMarried"))){
				sql.append(" and BAS.IS_MARRIED = '"+dataMap.get("isMarried")+"'");
			}
			// zhanghj 部门岗位
			if (dataMap.get("deptName") != null && !"".equals(dataMap.get("deptName"))){
			    sql.append(" and DP.POSITION_NAME LIKE '%"+dataMap.get("deptName")+"%'");
			}
		} else {
			sql.append(" AND JPJ.DEP_ID IN("+ depIds+") ");
		}
		sql.append(" order by QD.DESCRIPTION,PP.POSITION_CODE ");
		
		SQLQuery query=this.getSession().createSQLQuery(sql.toString());
		List list=query.list();
        
		return list;
		
	}
}
