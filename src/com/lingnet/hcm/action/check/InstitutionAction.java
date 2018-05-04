package com.lingnet.hcm.action.check;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.check.CkInstitution;
import com.lingnet.hcm.service.check.InstitutionService;
import com.lingnet.util.JsonUtil;
/**
 * 
 * @ClassName: ClassGroup 
 * @Description: 班制名称维护控制层
 * @author wangqiang
 * @date 2017年3月7日 下午4:11:49 
 *
 */
public class InstitutionAction extends BaseAction{

	private static final long serialVersionUID = 5623909238890670719L;
	
	private CkInstitution institution;
	private String dayFlag;//白班展示标记（N 不展示）
	
	@Resource(name = "institutionService")
	private InstitutionService institutionService;
	
	/**
	 * 跳转到班制名称维护页
	 */
	public String list(){
        return "list";
    }
	
	/**
	 * 获取班制名称信息集合
	 */
	public String getData(){
		Map<String, Object> result = institutionService.getDataByCond(pager);
        String json = JsonUtil.Encode(result);
        return ajax(Status.success, json);
	}
	
	/**
	 * 进入添加班制名称信息页面
	 */
	public String add(){
		return "add";
	}
	
	/**
     * 进入编辑页面
     */
    public String edit() {
    	institution = institutionService.get(id);
    	return "add";
    }
    
    /**
     * 保存或修改班制名称信息
     */
    public String saveOrUpdate(){
    	Map<String,String> dataMap = JsonUtil.parseProperties(formdata);
		if ("".equals(dataMap.get("id"))){//保存
			CkInstitution institution = institutionService.getValidateInstitueName(dataMap.get("instituteName"));
			if (institution != null){
				return ajax(Status.error, "班制名称已存在！"); 
			}
		}
		try {
			institutionService.saveOrUpdateInfo(formdata);
		} catch (Exception e) {
			e.printStackTrace();
			return ajax(Status.success, "操作失败"); 
		}
		return ajax(Status.success, "success");
	}
    
    /**
     * 删除选中班制名称信息
     * @Title: remove 
     * @return 
     * @author wangqiang
     * @since 2017年3月31日 V 1.0
     */
    public String remove() {
    	if (ids != null && !"".equals(ids)){
    		String[] idArrs = ids.split(",");
    		institutionService.delete(CkInstitution.class, idArrs);
    	}
        return ajax(Status.success, "success");
    }
    
    /**
     * 导出班制名称信息集合
     * @Title: exportInstitutionInfo  
     * @author wangqiang
     * @since 2017年3月31日 V 1.0
     */
    public void exportInstitutionInfo(){
    	HttpServletResponse resp = getResponse(); 
    	try {
            HSSFWorkbook hwb = institutionService.exportInfos();
            resp.setContentType("application/x-msdownload");
            resp.setHeader("Content-Disposition", "attachment;filename=\""
            		+ new String("班制名称信息".getBytes("gb2312"), "ISO8859-1")+ ".xls" + "\"");
            OutputStream out = resp.getOutputStream();
            hwb.write(out);
            out.flush();
            out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
    }
    
    /**
     * 获得班制名称下拉
     * @Title: getInstituteList 
     * @return 
     * @author wangqiang
     * @since 2017年4月5日 V 1.0
     */
    public String getInstitutionList(){
    	List<Map<String, String>> result = institutionService.getInstitutionList(dayFlag);
        String json = JsonUtil.Encode(result);
        return ajax(Status.success, json);
    }

	public CkInstitution getInstitution() {
		return institution;
	}

	public void setInstitution(CkInstitution institution) {
		this.institution = institution;
	}

	public String getDayFlag() {
		return dayFlag;
	}

	public void setDayFlag(String dayFlag) {
		this.dayFlag = dayFlag;
	}
    
}
