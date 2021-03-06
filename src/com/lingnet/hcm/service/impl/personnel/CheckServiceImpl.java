package com.lingnet.hcm.service.impl.personnel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.empdata.AwardDao;
import com.lingnet.hcm.dao.personnel.AuthenticateDao;
import com.lingnet.hcm.dao.personnel.CheckDao;
import com.lingnet.hcm.dao.personnel.PersonnelDao;
import com.lingnet.hcm.entity.WorkFlowChild;
import com.lingnet.hcm.entity.person.Award;
import com.lingnet.hcm.entity.person.PracticeAuthenticate;
import com.lingnet.hcm.entity.person.PracticeCheck;
import com.lingnet.hcm.service.empdata.AwardService;
import com.lingnet.hcm.service.personnel.AuthenticateService;
import com.lingnet.hcm.service.personnel.CheckService;
import com.lingnet.qxgl.dao.WorkFlowChildDao;
import com.lingnet.qxgl.service.AdminService;
import com.lingnet.util.LingUtil;
import com.lingnet.util.Pager;

/**
 * 实习生鉴定信息
 * service实现类
 */
@Service("checkService")
public class CheckServiceImpl extends BaseServiceImpl<PracticeCheck, String> implements CheckService{
	@Resource(name = "checkDao")
	private CheckDao checkDao;
	
	@Resource(name = "workFlowChildDao")
	private WorkFlowChildDao workFlowChildDao;
	
	@Resource(name="personnelDao")
	private PersonnelDao personnelDao;

	@Resource(name = "adminService")
    public AdminService adminService;
	@Override
	public HashMap getListData(Pager pager, String searchData) {
		return checkDao.getListData(pager,searchData);
	}
	@Override
	public Map<String, Object> getDataByCond(Pager pager, String state,String personId) {
		String userId = toolUtil.getUserId();
		String jobNumber = LingUtil.userinfo().getUsername();
		String theNum=personnelDao.get(personId)==null?"":personnelDao.get(personId).getJobNumber();//试岗期考核
		if(theNum==null||"".equals(theNum)){//实习生
			theNum=personnelDao.get(personId)==null?"":personnelDao.get(personId).getPracticeNum();
		}
		personId=adminService.getUserByName(theNum)==null?"":adminService.getUserByName(theNum).getId();
		//获得考勤内容信息集合
		List<Map<String, Object>> datas = checkDao.getInfoByCond(pager, userId, jobNumber, state,personId);
		if (datas.size() > 0){
			for (Map<String, Object> map:datas){
				if ("2".equals(map.get("auditStatus")+"")){//审核通过
					map.put("nodeName", "已完成");
				} else {
					if("".equals(map.get("nodeName")+"")){
						map.put("nodeName", "");
					} else {
						WorkFlowChild flow = workFlowChildDao.get(map.get("nodeName")+"");//获得当前节点的负责人
						if (flow != null){
							map.put("nodeName", "待(" +flow.getAppman()+ ")审核");
						} else {
							map.put("nodeName", "");
						}
					}
				}
			}
		}
        HashMap result = new HashMap();
        result.put("data", datas);
        result.put("total", pager.getTotalCount());
		return result;
	}
}
