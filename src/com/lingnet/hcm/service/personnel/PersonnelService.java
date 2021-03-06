package com.lingnet.hcm.service.personnel;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.util.Pager;

/**
 * 
 * @ClassName: EmployeeService 
 * @Description: 人员信息service 
 * @author zhangruiling
 * @date 2017年3月20日 下午1:30:07 
 *
 */
public interface PersonnelService extends BaseService<BasicInformation, String>{
	
	/**
	 *  人员list列表
	 * @Title: getInfosByDepId 
	 * @param depId
	 * @return 
	 * @author zrl
	 * @since 2017年3月21日 V 1.0
	 */
	public HashMap getPersonByDepId(Pager pager,String searchData);
	
	/**
	 * 修改人员考勤基本信息
	 * @Title: updateCheckInfo 
	 * @param formdata
	 * @throws Exception 
	 * @author wangqiang
	 * @since 2017年3月23日 V 1.0
	 */
	public void updateCheckInfo(String formdata) throws Exception;
	
	/**
	 * 保存导入的考勤基本信息
	 * @Title: saveImportInfos 
	 * @param uploadFile
	 * @return
	 * @throws Exception 
	 * @author wangqiang
	 * @since 2017年3月24日 V 1.0
	 */
	public String saveImportInfos(String endSuffix, File uploadFile) throws Exception;
	/**
	 * 保存基本信息
	 * @Title: saveImportInfos 
	 * @param uploadFile
	 * @return
	 * @throws Exception 
	 * @author zrl
	 * @since 2017年3月24日 V 1.0
	 */
	public String saveOrUpdate(String formdata,String depId) throws Exception;
	
	/**
	 * 批量更换人员班组
	 * @Title: updateBatchClass 
	 * @param empIds
	 * @return
	 * @throws Exception 
	 * @author wangqiang
	 * @since 2017年4月6日 V 1.0
	 */
	public void updateBatchClass(String empIds, String depIds, String classNo) throws Exception;
	
	/**
	 * 根据员工ID集合查询员工信息集合
	 * @Title: getDataByEmpIds 
	 * @param pager
	 * @param empIdArrs
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月20日 V 1.0
	 */
	public List<Map<String, String>> getDataByEmpIds(Pager pager,List<String> empIdArrs);
	
	/**
	 * 根据职工号查询职工所属考勤单位Id
	 * @Title: getCheckUnitIdByJobNumber 
	 * @param jobNumber
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月22日 V 1.0
	 */
	public String getCheckUnitIdByJobNumber(String jobNumber);
	
	/**
	 * 根据条件获得年假信息
	 * @Title: getAnnualLeaveData 
	 * @return 
	 * @author wangqiang
	 * @since 2017年4月24日 V 1.0
	 */
	public Map<String, Object> getAnnualLeaveData(Pager pager, String searchData);
	
	/**
	 * 正式工list页面
	 * @Title: getInfosByDepId 
	 * @param depId
	 * @return 
	 * @author zrl
	 * @since 2017年3月21日 V 1.0
	 */
	public HashMap<String, Object> getZsgPerson(Pager pager,String searchData);
	/**
	 * 试岗期list页面
	 * @Title: getInfosByDepId 
	 * @param depId
	 * @return 
	 * @author zrl
	 * @since 2017年3月21日 V 1.0
	 */
	public HashMap<String, Object> getSgqPerson(Pager pager,String searchData)throws Exception;
	/**
	 * 实习生list页面
	 * @Title: getInfosByDepId 
	 * @param depId
	 * @return 
	 * @author zrl
	 * @since 2017年3月21日 V 1.0
	 */
	public HashMap<String, Object> getSxsPerson(Pager pager,String searchData);
	/**
	 * 劳务外包list页面
	 * @Title: getInfosByDepId 
	 * @param depId
	 * @return 
	 * @author zrl
	 * @since 2017年3月21日 V 1.0
	 */
	public HashMap<String, Object> getLwwbPerson(Pager pager,String searchData);
	/**
	 * 业务外包list页面
	 * @Title: getInfosByDepId 
	 * @param depId
	 * @return 
	 * @author zrl
	 * @since 2017年3月21日 V 1.0
	 */
	public HashMap<String, Object> getYwwbPerson(Pager pager,String searchData);
	/**
	 * 退休人员list页面
	 * @Title: getInfosByDepId 
	 * @param depId
	 * @return 
	 * @author zrl
	 * @since 2017年3月21日 V 1.0
	 */
	public HashMap<String, Object> getTxPerson(Pager pager,String searchData);
	/**
	 * 离职人员list页面
	 * @Title: getInfosByDepId 
	 * @param depId
	 * @return 
	 * @author zrl
	 * @since 2017年3月21日 V 1.0
	 */
	public HashMap<String, Object> getLzPerson(Pager pager,String searchData);
	/**
	 * 返聘人员list页面
	 * @Title: getInfosByDepId 
	 * @param depId
	 * @return 
	 * @author zrl
	 * @since 2017年3月21日 V 1.0
	 */
	public HashMap<String, Object> getFpPerson(Pager pager,String searchData);
	/**
	 * 复员军人list页面
	 * @Title: getInfosByDepId 
	 * @param depId
	 * @return 
	 * @author zrl
	 * @since 2017年3月21日 V 1.0
	 */
	public HashMap<String, Object> getFyjrPerson(Pager pager,String searchData);
	/**
	 * 非在岗list页面
	 * @Title: getInfosByDepId 
	 * @param depId
	 * @return 
	 * @author zrl
	 * @since 2017年3月21日 V 1.0
	 */
	public HashMap<String, Object> getFzgPerson(Pager pager, String searchData);
	/**
	 * 技术工人list页面
	 * @Title: getInfosByDepId 
	 * @param depId
	 * @return 
	 * @author zrl
	 * @since 2017年3月21日 V 1.0
	 */
	public HashMap<String, Object> getJsgrPerson(Pager pager, String searchData);
	/**
	 * 专业技术人员list页面
	 * @Title: getInfosByDepId 
	 * @param depId
	 * @return 
	 * @author zrl
	 * @since 2017年3月21日 V 1.0
	 */
	public HashMap<String, Object> getZyjsryPerson(Pager pager, String searchData);
	
	public String getAllDepIdStrs();

	/**
	 * @Title: 获取工作流程定义人员列表
	 * @param pager
	 * @param searchData
	 * @return 
	 * Map<String,Object> 
	 * @author zhanghj
	 * @since 2017年7月13日 V 1.0
	 */
    public Map<String, Object> getProcessStaffDatas(Pager pager, String searchData);
    
    public List getOtherExcept(String searchData,String thetype);
}
