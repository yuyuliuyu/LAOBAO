package com.lingnet.hcm.action.check;

import java.io.OutputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.check.CkClass;
import com.lingnet.hcm.service.check.ClassGroupService;
import com.lingnet.util.JsonUtil;
/**
 * 
 * @ClassName: ClassGroup 
 * @Description: 班组信息控制层 
 * @author wangqiang
 * @date 2017年3月7日 下午4:11:49 
 *
 */
public class ClassGroupAction extends BaseAction{

	private static final long serialVersionUID = 5623909238890670719L;
	
	private CkClass ckClass;//班组信息实体类
	
	@Resource(name = "classGroupService")
	private ClassGroupService classGroupService;
	
	/**
	 * 跳转到班组名称维护页
	 */
	public String list(){
        return "list";
    }
	
	/**
	 * 获取所有班组信息下拉集合
	 * @Title: getClassInfos 
	 * @return 
	 * @author wangqiang
	 * @since 2017年3月22日 V 1.0
	 */
	public String getClassInfos(){
        return ajax(Status.success, JsonUtil.Encode(classGroupService.getAllClassInfos()));
	}
	
	/**
	 * 获取班组名称信息集合
	 */
	public String getData(){
		Map<String, Object> result = classGroupService.getDataByCond(pager);
        String json = JsonUtil.Encode(result);
        return ajax(Status.success, json);
	}
	
	/**
	 * 进入添加班组名称信息页面
	 */
	public String add(){
		return "add";
	}
	
	/**
	 * 保存或修改班组信息
	 */
	public String saveOrUpdate(){
		Map<String,String> dataMap = JsonUtil.parseProperties(formdata);
		if ("".equals(dataMap.get("id"))){//保存
			CkClass cc = classGroupService.getValidateClassNo(dataMap.get("classNo"));
			if (cc != null){
				return ajax(Status.error, "班号已存在！"); 
			}
		}
		try {
			classGroupService.saveOrUpdateInfo(formdata);
		} catch (Exception e) {
			e.printStackTrace();
			return ajax(Status.error, "操作失败"); 
		}
		return ajax(Status.success, "success");
	}
	
	/**
     * 进入编辑页面
     */
    public String edit() {
        ckClass=this.getBeanById(CkClass.class,id.trim());
//    	ckClass = classGroupService.get(id);
    	return "add";
    }
    
    /**
     * 删除班组信息集合
     * @Title: remove 
     * @return 
     * @author wangqiang
     * @since 2017年4月6日 V 1.0
     */
    public String remove() {
    	if (ids != null && !"".equals(ids)){
    		String[] idArrs = ids.split(",");
    		classGroupService.delete(CkClass.class, idArrs);
    	}
        return ajax(Status.success, "success");
    }
    
    /**
     * 导出班组信息
     * @Title: exportClassInfo  
     * @author wangqiang
     * @since 2017年4月6日 V 1.0
     */
    public void exportClassInfo(){
    	HttpServletResponse resp = getResponse(); 
    	try {
            HSSFWorkbook hwb = classGroupService.exportInfos();
            resp.setContentType("application/x-msdownload");
            resp.setHeader("Content-Disposition", "attachment;filename=\""
            		+ new String("考勤班组信息".getBytes("gb2312"), "ISO8859-1")+ ".xls" + "\"");
            OutputStream out = resp.getOutputStream();
            hwb.write(out);
            out.flush();
            out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
    }

	public CkClass getCkClass() {
		return ckClass;
	}

	public void setCkClass(CkClass ckClass) {
		this.ckClass = ckClass;
	}
    
}
