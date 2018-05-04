package com.lingnet.hcm.service.impl.check;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.check.OvertimeRecordDao;
import com.lingnet.hcm.dao.personnel.PersonnelDao;
import com.lingnet.hcm.entity.WorkFlowChild;
import com.lingnet.hcm.entity.check.CkOvertimeRecord;
import com.lingnet.hcm.service.check.OvertimeRecordService;
import com.lingnet.qxgl.dao.WorkFlowChildDao;
import com.lingnet.util.LingUtil;
import com.lingnet.util.Pager;
/**
 * 
 * @ClassName: OvertimeRecordServiceImpl 
 * @Description: 加班申请记录service   
 * @author wangqiang
 * @date 2017年5月5日 上午11:09:26 
 *
 */
@Service("overtimeRecordService")
public class OvertimeRecordServiceImpl extends BaseServiceImpl<CkOvertimeRecord, String> implements OvertimeRecordService{

	@Resource(name = "overtimeRecordDao")
	private OvertimeRecordDao overtimeRecordDao;
	
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
		List<Map<String, Object>> datas = overtimeRecordDao.getInfoByCond(pager, userId, jobNumber, state);
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
