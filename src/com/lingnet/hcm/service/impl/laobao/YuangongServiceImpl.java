package com.lingnet.hcm.service.impl.laobao;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.laobao.YuangongDao;
import com.lingnet.hcm.dao.personnel.PtJobDao;
import com.lingnet.hcm.entity.laobao.StaffInfo;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.entity.person.PtJob;
import com.lingnet.hcm.service.laobao.YuangongService;
import com.lingnet.qxgl.dao.BackendDepDao;
import com.lingnet.qxgl.dao.BranchDao;
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.qxgl.entity.QxDepartment;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

@Service("yuangongService")
public class YuangongServiceImpl extends BaseServiceImpl<BasicInformation, String>  implements YuangongService{

	@Resource(name="yuangongDao")
	private YuangongDao yuangongDao;
	@Resource(name="ptJobDao")
	private PtJobDao ptJobDao;
	
	@Resource(name="branchDao")
	private BranchDao branchDao;
	
	@Resource(name="backendDepDao")
	private BackendDepDao backendDepDao;
	//人员基本信息list页面
	@SuppressWarnings({ "rawtypes" })
	@Override
	public HashMap getPersonByDepId(Pager pager, String searchData) {

		return yuangongDao.getPersonByDepId(pager, searchData);
	}

	@Override
	public StaffInfo getStaffByJobNumber(String job_number) {
		return yuangongDao.getStaffByJobNumber(job_number);
	}
    /**
     * @Title: 人员基本信息保存
     * @param column
     * @param msg
     * @param length
     * @return 
     * String 
     * @author zrl
     * @since 2016年12月6日 V 1.0
     */
    public String saveOrUpdate(String formdata,String branchId) throws Exception {
    	BasicInformation information=JsonUtil.toObject(formdata, BasicInformation.class);
    	if (information != null) {
    		String id = information.getId();
    		if (StringUtils.isBlank(id)){
    			information.setIsDelete(0);
    			information.setCheckUnitId(information.getDepartId());
    			if(information.getFilmId()!=null&&!"".equals(information.getFilmId())){
    				Branch branch=branchDao.get(information.getFilmId());
    				String filmName=branch.getFzx();
    				information.setFilmName(filmName);
    			}
    			if(information.getDepartId()!=null&&!"".equals(information.getDepartId())){
    				
					QxDepartment dep=backendDepDao.get(information.getDepartId());
    				String depName=dep.getName();
    				information.setDepartName(depName);
    			}
    			if(information.getPostId()!=null&&!"".equals(information.getPostId())){//岗位
    				/*QxDepartment dep=backendDepDao.get(information.getPostId());
    				String depName=dep.getName();
    				information.setDepartName(depName);*/
    			}
    			if(information.getDepartId()!=null&&!"".equals(information.getDepartId())){//具体岗位
    				/*QxDepartment dep=backendDepDao.get(information.getDepartId());
    				String depName=dep.getName();
    				information.setDepartName(depName);*/
    			}
    			
        		id=this.save(information);
        		if (StringUtils.isBlank(id))
                    throw new Exception("保存失败");
        		//兼职信息
        		PtJob ptJob=new PtJob();
        		ptJob.setPersonId(id);
        		ptJob.setIsDelete(0);
        		ptJob.setIsHostPost(1);
        		ptJob.setDepId(information.getDepartId());//部门ID
        		ptJob.setFirmId(branchId);//单位ID
        		ptJob.setStandardPost(information.getPost());
        		ptJob.setSpePost(information.getSpecificPost());
        		ptJob.setBeginTime(information.getBeginTime());
        		
        		String ptid=ptJobDao.save(ptJob);//保存
        		if (StringUtils.isBlank(ptid))
                    throw new Exception("信息导入兼职信息时发生异常！");
        		
        	}else{
        		BasicInformation oldInfo=this.get(BasicInformation.class, Restrictions.eq("id", id), Restrictions.eq("isDelete", 0));
        		if (oldInfo == null) {
                    throw new Exception("该条记录不存在");
                }
        		oldInfo.copyFrom(information);
        		this.update(oldInfo);
        	}
    		
    	}else return "信息不能为空";
    	
    	return "success";
    }
}
