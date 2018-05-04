package com.lingnet.hcm.action.check;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.check.CkContent;
import com.lingnet.hcm.service.check.ContentService;
import com.lingnet.util.JsonUtil;
/** 
 * @ClassName: Content 
 * @Description: 考勤内容维护控制层 
 * @author wangqiang
 * @date 2017年3月7日 下午4:44:05 
 *
 */
public class ContentAction extends BaseAction{

	private static final long serialVersionUID = 5623909238890670719L;
	
	private CkContent ckContent;
	private String contentName;//考勤内容名称
	
	@Resource(name = "contentService")
	private ContentService contentService;
	
	/**
	 * 跳转到考勤内容维护页
	 */
	public String list(){
        return "list";
    }
	
	/**
	 * 获取考勤内容信息集合
	 */
	public String getData(){
        Map<String, Object> result =null;
        
	    if(contentName==null){ 
            result = contentService.getDataByCond(pager);
	    }else{
            result =contentService.getDataByCond(null);
	    }
        String json = JsonUtil.Encode(result);
        return ajax(Status.success, json);
	}
	
	/**
	 * 进入添加考勤内容信息页面
	 */
	public String add(){
	    if(contentName==null||"0".equals(contentName)){ 
	        return "model";
	    }else{
	        return "add";
	    }
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public String loadcontent(){
        List<CkContent> contlist=this.getBeanListByCriteria(CkContent.class, Restrictions.isNull("depId")); 
        Map result = new HashMap();
         List<Map<String, String>> list = new ArrayList<Map<String, String>>(); 
         if(contlist!=null && contlist.size()>0){
             for (int i = 0; i < contlist.size(); i++) {
                 CkContent content=contlist.get(i);
                 Map<String, String> map = new HashMap<String, String>(); 
                 map.put("id",content.getId()); 
                 map.put("text", content.getContent());
                 list.add(map);
            }
         }
         result.put("data", list);
         result.put("total", pager.getTotalCount());
         String json = JsonUtil.Encode(result);
         return ajax(Status.success,json); 
	}
    /**
     * 进入编辑页面
     */
    public String modeladd(){
        ckContent = contentService.get(id);
        ckContent.setId(null);
        return "add";
    }
	/**
     * 进入编辑页面
     */
    public String edit(){
    	ckContent = contentService.get(id);
    	if (null != ckContent) {
    	    contentName = ckContent.getDepId();
    	}
    	if(contentName==null||"0".equals(contentName)){ 
            return "model";
        }else{
            return "add";
        }
    }
    
    /**
     * 保存或修改考勤内容信息
     */
    public String saveOrUpdate(){
    	Map<String,String> dataMap = JsonUtil.parseProperties(formdata);
    	if ("".equals(dataMap.get("id"))){
    		CkContent content = contentService.getValidateContent(dataMap.get("content"));
        	if (content != null){//若已存在
        		return ajax(Status.error, "考勤内容名称已存在！"); 
        	}
    	}
		try {
			contentService.saveOrUpdateInfo(contentName,formdata);
		} catch (Exception e) {
			e.printStackTrace();
			return ajax(Status.error, "操作失败"); 
		}
		return ajax(Status.success, "success");
	}
    
    /**
     * 删除考勤内容信息
     * @Title: remove 
     * @return 
     * @author wangqiang
     * @since 2017年4月1日 V 1.0
     */
    public String remove() {
    	if (ids != null && !"".equals(ids)){
    		String[] idArrs = ids.split(",");
    		contentService.delete(CkContent.class, idArrs);
    	}
        return ajax(Status.success, "success");
    }
    
    /**
     * 导出考勤内容信息
     * @Title: exportContentInfo  
     * @author wangqiang
     * @since 2017年4月1日 V 1.0
     */
    public void exportContentInfo(){
    	HttpServletResponse resp = getResponse(); 
    	try {
            HSSFWorkbook hwb = contentService.exportInfos();
            resp.setContentType("application/x-msdownload");
            resp.setHeader("Content-Disposition", "attachment;filename=\""
            		+ new String("考勤内容信息".getBytes("gb2312"), "ISO8859-1")+ ".xls" + "\"");
            OutputStream out = resp.getOutputStream();
            hwb.write(out);
            out.flush();
            out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
    }
    
    /**
     * 获取考勤内容下拉选
     * @Title: getContentList 
     * @return 
     * @author wangqiang
     * @since 2017年4月5日 V 1.0
     */
    public String getContentList(){
    	List<Map<String, String>> result = contentService.getContentList();
        String json = JsonUtil.Encode(result);
        return ajax(Status.success, json);
    }
    
	public CkContent getCkContent() {
		return ckContent;
	}

	public void setCkContent(CkContent ckContent) {
		this.ckContent = ckContent;
	}

	public String getContentName() {
		return contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
    
}
