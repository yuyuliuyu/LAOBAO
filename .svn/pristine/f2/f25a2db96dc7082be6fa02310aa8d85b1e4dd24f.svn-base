package com.lingnet.hcm.action.check;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lingnet.common.action.BaseAction;
import com.lingnet.util.JsonUtil;
/**
 * 
 * @ClassName: Evection 
 * @Description: 出差管理控制层 
 * @author wangqiang
 * @date 2017年3月7日 下午4:44:05 
 *
 */
public class EvectionAction extends BaseAction{

	private static final long serialVersionUID = 5623909238890670719L;
	
	/**
	 * 跳转到出差申请列表页
	 */
	public String applyList(){
        return "applyList";
    }
	
	/**
	 * 获取出差申请信息集合
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getApplyData(){
		Map result = new HashMap();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("jobNumber", "0486013");
		map.put("name", "郭振");
		map.put("evectionType", "出差");
		map.put("beginTime", "2017-03-06");
		map.put("endTime", "2017-03-15");
		map.put("evectionDays", "9");
		map.put("status", "待经理审核");
		list.add(map);
        result.put("data", list);
        result.put("total", pager.getTotalCount());
        String json = JsonUtil.Encode(result);
        return ajax(Status.success, json);
	}
	
	/**
	 * 进入添加出差申请页面
	 */
	public String add(){
		return "add";
	}
	
	/**
     * 进入编辑出差申请页面
     */
    public String edit() {
    	
    	return "add";
    }
    
    /**
     * 查看审核列表
     * @Title: viewOptionList 
     * @return 
     * @author wangqiang
     * @since 2017年3月16日 V 1.0
     */
    public String viewOptionList(){
    	
        return "viewOptionList";
    }
    
    /**
     * 获取审核信息集合
     * @Title: getOptionData 
     * @return 
     * @author wangqiang
     * @since 2017年3月17日 V 1.0
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public String getOptionData(){
		Map result = new HashMap();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("deptName", "人事部");
		map.put("name", "张三");
		map.put("auditStatus", "通过");
		map.put("auditTime", "2017-03-16");
		list.add(map);
        result.put("data", list);
        result.put("total", pager.getTotalCount());
        String json = JsonUtil.Encode(result);
        return ajax(Status.success, json);
	}
    
    /**
     * 查看审核意见
     * @Title: viewOption 
     * @return 
     * @author wangqiang
     * @since 2017年3月17日 V 1.0
     */
    public String viewOption(){
    	return "viewOption";
    }
    
    /***************************** 审核 ***************************************/
    
    /**
     * 跳转到出差审核列表页面
     * @Title: auditList 
     * @return 
     * @author wangqiang
     * @since 2017年3月16日 V 1.0
     */
    public String auditList(){
        return "auditList";
    }
    
    /**
     * 获取审核信息集合
     * @Title: getAuditData 
     * @return 
     * @author wangqiang
     * @since 2017年3月16日 V 1.0
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public String getAuditData(){
		Map result = new HashMap();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("deptName", "信息技术部");
		map.put("jobNumber", "0486013");
		map.put("name", "郭振");
		map.put("evectionType", "出差申请");
		map.put("beginTime", "2017-03-06");
		map.put("endTime", "2017-03-15");
		map.put("evectionDays", "9");
		map.put("status", "待审核");
		list.add(map);
        result.put("data", list);
        result.put("total", pager.getTotalCount());
        String json = JsonUtil.Encode(result);
        return ajax(Status.success, json);
	}
    
    /**
     * 跳转到查看员工出差申请详情页
     * @Title: view 
     * @return 
     * @author wangqiang
     * @since 2017年3月16日 V 1.0
     */
    public String view(){
    	
        return "view";
    }
    
    /**
     * 跳转到审核页面
     * @Title: showApprove 
     * @return 
     * @author wangqiang
     * @since 2017年3月16日 V 1.0
     */
    public String showApprove(){
    	
    	return "approve";
    }
}
