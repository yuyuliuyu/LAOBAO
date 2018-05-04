package com.lingnet.hcm.service.impl.check;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.check.RestRecordDao;
import com.lingnet.hcm.dao.personnel.PersonnelDao;
import com.lingnet.hcm.entity.WorkFlowChild;
import com.lingnet.hcm.entity.check.CkRestRecord;
import com.lingnet.hcm.service.check.RestRecordService;
import com.lingnet.qxgl.dao.WorkFlowChildDao;
import com.lingnet.util.LingUtil;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: RestRecordServiceImpl 
 * @Description: 休假申请记录service  
 * @author wangqiang
 * @date 2017年5月4日 下午2:23:23 
 *
 */
@Service("restRecordService")
public class RestRecordServiceImpl extends BaseServiceImpl<CkRestRecord, String> implements RestRecordService{

	@Resource(name = "restRecordDao")
	private RestRecordDao restRecordDao;
	
	@Resource(name = "workFlowChildDao")
	private WorkFlowChildDao workFlowChildDao;
	
	@Resource(name="personnelDao")
	private PersonnelDao personnelDao;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> getDataByCond(Pager pager, String state) {
		String userId = toolUtil.getUserId();
		String jobNumber = LingUtil.userinfo().getUsername();
		//获得考勤内容信息集合
		List<Map<String, Object>> datas = restRecordDao.getInfoByCond(pager, userId, jobNumber, state);
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
