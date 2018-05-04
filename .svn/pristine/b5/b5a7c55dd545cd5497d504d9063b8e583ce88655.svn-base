package com.lingnet.hcm.action.check;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.hcm.service.personnel.PtJobService;
import com.lingnet.util.JsonUtil;
/**
 * 
 * @ClassName: Employee 
 * @Description: 考勤人员信息控制层 
 * @author wangqiang
 * @date 2017年3月7日 下午2:53:08 
 *
 */
public class EmployeeAction extends BaseAction{

	private static final long serialVersionUID = -3995971277716717501L;
	
	private Map<String, String> map;
	private String jobNumber;// 职工号
	private String name;//职工姓名
	private String classNo; //班号
	private File uploadFile;// 导入的文件
	private String depIds;//部门ID集合
		
	@Resource(name = "personnelService")
	private PersonnelService personnelService;
	
	@Resource(name = "ptJobService")
	private PtJobService ptJobService;
	
	/**
	 * 跳转到考勤人员信息列表页
	 */
	public String list(){
        return "list";
    }
	
	/**
	 * 获取考勤人员列表信息
	 */
	public String getData(){
		Map<String, Object> result = ptJobService.getDataByCond(pager, searchData);
        String json = JsonUtil.Encode(result);
        return ajax(Status.success, json);
	}
	
	/**
     * 进入编辑页面
     */
    public String edit() {
    	map = ptJobService.getPersonInfoById(id);
    	return "add";
    }
    
    /**
     * 保存或修改人员考勤基本信息
     */
    public String saveOrUpdate(){
		try {
			personnelService.updateCheckInfo(formdata);
		} catch (Exception e) {
			e.printStackTrace();
			return ajax(Status.success, "操作失败"); 
		}
		return ajax(Status.success, "success");
	}
    
    /**
     * 跳转到导入人员考勤基本信息页面
     * @Title: importInfos 
     * @return 
     * @author wangqiang
     * @since 2017年3月24日 V 1.0
     */
    public String importInfos(){
    	return "importInfos";
    }
    
    /**
     * 下载模板
     * @Title: download 
     * @return 
     * @author wangqiang
     * @since 2017年3月24日 V 1.0
     */
    public String download(){
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
        FileInputStream fis;
		try {
			//String real = ToolUtil.getPropert("doc_config.properties","real_path");
			// 下载的文件的服务器路径 + 类型相关物理路径＋文件名
			String fielName = "renyuanxinxi.xls";
			String name = "人员考勤基本信息模板.xls";
            String bath = request.getSession().getServletContext().getRealPath("");
            String separator = File.separator;//获取当前操作系统的分隔符
            String fileDownloadName = bath + separator + "data"+ separator +"template" + separator + fielName;  //文件路径
			fis = new FileInputStream(fileDownloadName);
			BufferedInputStream bis = new BufferedInputStream(fis);
            
            response.setContentType("application/x-msdownload");
            // 解决中文文件名乱码问题  
            if (request.getHeader("User-Agent").toLowerCase()  
                    .indexOf("firefox") > 0) {  
            	name = new String(name.getBytes("UTF-8"), "ISO8859-1"); // firefox浏览器  
            } else if (request.getHeader("User-Agent").toUpperCase()  
                    .indexOf("MSIE") > 0) {  
            	name = URLEncoder.encode(name, "UTF-8");// IE浏览器  
            }else if (request.getHeader("User-Agent").toUpperCase()  
                    .indexOf("CHROME") > 0) {  
            	name = new String(name.getBytes("UTF-8"), "ISO8859-1");// 谷歌  
            } else {
            	name = URLEncoder.encode(name, "UTF-8");
            }
            response.setHeader("Content-Disposition", "attachment;filename="+name);
            // 输出文件流
            ServletOutputStream out;
            out = response.getOutputStream();
            int len = -1;
            while ((len = bis.read()) != -1) {
                out.write(len);
            }
            bis.close();
	        out.close();
            return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return null;    
	}
    
    /**
     * 导入人员考勤基本信息
     * @Title: importSave 
     * @return 
     * @author wangqiang
     * @since 2017年3月24日 V 1.0
     */
    public String importSave() {
        MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) this.getRequest();
        // 获取上传文件的名字
        String[] fileName = wrapper.getFileNames("uploadFile");
        if (fileName.length == 0) {
            return ajax(Status.success, "请选择上传文件！");
        }
        // 文件后缀
        String endSuffix= fileName[0].substring(fileName[0].lastIndexOf("."), fileName[0].length());
        if (!endSuffix.toLowerCase().endsWith("xls")
                && !endSuffix.toLowerCase().endsWith("xlsx")) {
                return ajax(Status.success, "请选择正确的文件类型，必须是以.xls或.xlsx结尾！");
        }
        try {
            String message = personnelService.saveImportInfos(endSuffix, uploadFile);
            String msg = "";
            if (message.indexOf("@") == 0) {
                msg = message.substring(1, message.length());
            }
            ServletActionContext.getRequest().getSession().setAttribute("_repeatItem", msg);
            return ajax(Status.success, message);
        } catch (Exception e) {
            String message = e.getMessage();
            if (message.indexOf("@") == 0) {
                message = message.substring(1, message.length());
            }
            ServletActionContext.getRequest().getSession().setAttribute("_repeatItem", message);
            return ajax(Status.error, e.getMessage());
        }
    }
    
    /**
     * 导出人员档案基本信息
     * @Title: expertEmpInfo  
     * @author wangqiang
     * @since 2017年3月28日 V 1.0
     */
    public void expertEmpInfo(){
    	HttpServletResponse resp = getResponse(); 
    	List<String> depIdList = new ArrayList<String>();
    	if (!"".equals(depIds)){
    		String[] depIdArrs = depIds.split(",");
    		for (String depId:depIdArrs){
    			if (depId != null && !"".equals(depId)){
    				depIdList.add(depId);
    			}
    		}
    	}
    	try {
            HSSFWorkbook hwb = ptJobService.exportInfoByDepIds(depIdList);
            resp.setContentType("application/x-msdownload");
            resp.setHeader("Content-Disposition", "attachment;filename=\""
            		+ new String("人员考勤档案基本信息".getBytes("gb2312"), "ISO8859-1")+ ".xls" + "\"");
            OutputStream out = resp.getOutputStream();
            hwb.write(out);
            out.flush();
            out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
    }
    
    /**
     * 跳转到更换班组页面
     * @Title: changeClass 
     * @return 
     * @author wangqiang
     * @since 2017年4月6日 V 1.0
     */
    public String changeClass(){
    	return "changeClass";
    }
    
    /**
     * 批量更换人员班组
     * @Title: updateBeachClass 
     * @return 
     * @author wangqiang
     * @since 2017年4月6日 V 1.0
     */
    public String updateBeachClass(){
    	Map<String,String> dataMap = JsonUtil.parseProperties(formdata);
    	String empIds = "";
    	if(dataMap.get("ids") != null && !"".equals(dataMap.get("ids"))){
    		String[] empIdArrs = dataMap.get("ids").split(",");
    		for (String empId:empIdArrs){
    			if (!"".equals(empId)){
    				empIds += ("'"+empId+"', ");
    			}
    		}
    	}
    	String depmentIds = "";
    	if(dataMap.get("depIds") != null && !"".equals(dataMap.get("depIds"))){
    		String[] depmentIdArrs = dataMap.get("depIds").split(",");
    		for (String depmentId:depmentIdArrs){
    			if (!"".equals(depmentId)){
    				depmentIds += ("'"+depmentId+"', ");
    			}
    		}
    	}
    	String classNo = dataMap.get("classNo");
    	try{
    		personnelService.updateBatchClass(empIds==""?empIds:empIds.substring(0, empIds.length()-2), 
    				depmentIds==""?depmentIds:depmentIds.substring(0, depmentIds.length()-2), classNo);
    	} catch(Exception e){
    		e.printStackTrace();
    		return ajax(Status.error, "操作失败");
    	}
    	return ajax(Status.success, "success");
    }

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getDepIds() {
		return depIds;
	}

	public void setDepIds(String depIds) {
		this.depIds = depIds;
	}
    
}
