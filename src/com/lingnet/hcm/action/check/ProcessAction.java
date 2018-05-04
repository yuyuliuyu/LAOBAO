package com.lingnet.hcm.action.check;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
/**
 * 
 * @ClassName: Content 
 * @Description: 工作流维护控制层 
 * @author wangqiang
 * @date 2017年3月7日 下午4:44:05 
 *
 */
public class ProcessAction extends BaseAction{

	private static final long serialVersionUID = 5623909238890670719L;
	
	private String processId;//工作流ID
	private String subId;//子节点ID
	
	/**
	 * 跳转到工作流维护页
	 */
	public String list(){
        return "list";
    }
	
	/**
	 * 获取工作流信息集合
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getData(){
		Map result = new HashMap();
		Map<String, String> map = new HashMap<String, String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		map.put("name", "测试流程1");
		map.put("type", "出差审批");
		map.put("createDate", sdf.format(new Date()));
		map.put("usingState", "否");
		map.put("processDes", "出差申请流程：开始-节点-结束");
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("name", "测试流程2");
		map2.put("type", "加班审批");
		map2.put("createDate", sdf.format(new Date()));
		map2.put("usingState", "否");
		map2.put("processDes", "加班申请流程：开始-节点-结束");
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		list.add(map);
		list.add(map2);
        result.put("data", list);
        result.put("total", pager.getTotalCount());
        String json = JsonUtil.Encode(result);
        return ajax(Status.success, json);
	}
	
	/**
	 * 获取工作流子节点信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getDataByParentId(){
		Map result = new HashMap();
		Map<String, String> map = new HashMap<String, String>();
		map.put("sequence", "1");
		map.put("headNode", "张三");
		map.put("department", "人事部");
		map.put("post", "经理");
        result.put("data", map);
        result.put("total", pager.getTotalCount());
        String json = JsonUtil.Encode(result);
        return ajax(Status.success, json);
	}
	
	/**
	 * 进入添加工作流信息页面
	 */
	public String add(){
		return "add";
	}
	
	/**
     * 进入编辑页面
     */
    public String edit() {
    	
    	return "add";
    }
    
    /**
     * 添加子节点
     * @Title: addSub 
     * @return 
     * @author wangqiang
     * @since 2017年3月13日 V 1.0
     */
    public String addSub(){
		return "addSub";
	}
    
    /**
     * 编辑工作流节点
     * @Title: editSub 
     * @return 
     * @author wangqiang
     * @since 2017年3月13日 V 1.0
     */
    public String editSub() {
    	
    	return "addSub";
    }

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}
    
}
