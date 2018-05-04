package com.lingnet.hcm.action.empdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.entity.person.License;
import com.lingnet.hcm.service.empdata.LicenseService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.ToolUtil;

/**
 * 
 * @ClassName: DepartureAction 
 * @Description: 证照Action 
 * @author zrl
 * @date 2017年3月10日 上午10:56:46 
 *
 */
@Controller
public class LicenseAction extends BaseAction{

    private static final long serialVersionUID = -4533377460351784579L;
    
    private String flag;//标志位
    private String formdata;
    private File uploadFile;//上传文件
    private String msg;//标志位
    private String url;//证书编号
    private String personId;//标志位
    
    private BasicInformation info;
    private License license;
    
    private String licenseStatus;//证件状态（是否有效）
    private String loseDate;//距离失效天数
    
    @Resource(name="licenseService")
    private LicenseService licenseService;
    /**
     * 列表页面
     */
    public String list(){
        return LIST;
    }
    /**
     * 列表页面
     */
    public String perlist(){
        return LIST;
    }
    /**
     * 列获取数据
     */
    public String getListData() {
    	if(personId!=null&&!"".equals(personId)){
    		Map<String,String> dataMap = JsonUtil.parseProperties(searchData);
    		if(dataMap==null){
    			dataMap=new HashMap<String,String>();
    		}
			dataMap.put("personId", personId);
			
    		searchData=JsonUtil.Encode(dataMap);
    	}
        return ajax(Status.success, JsonUtil.Encode(licenseService.getListData(pager,searchData)));
    }
    
    /**
     * 添加页面
     */
    public String add(){
    	license=this.getBeanByCriteria(License.class, Restrictions.eq("id", id));
    	String personId=null;
    	if(license!=null){
    		personId=license.getPersonId();
    		url=license.getUrl();
    		if(license.getEnddate()!=null){
    			Date enddate=license.getEnddate();
        		Date newdate=new Date();
        		String contDate=countDate(newdate,enddate);
        		if(contDate!=null&&"-1".equals(contDate)){
        			licenseStatus="2";
        			loseDate="-1";
        		}else if(contDate==null){
        			
        		}else{
        			licenseStatus="1";
        			loseDate=contDate;
        		}
    		}
    	}
    	info=this.getBeanByCriteria(BasicInformation.class, Restrictions.eq("id", personId));
        return "add";
    }
    
    /**
     * 计算时间差
     * zrl
     */
    public String countDate(Date date1,Date date2){
    	long time1 = date1.getTime();  
        long time2 = date2.getTime();  
        long diff ;  
        if(time1<time2) {  
            diff = time2 - time1;  
            long days = (diff / (1000 * 60 * 60 * 24))+1; 
            String day=String.valueOf(days);
            return day;
        } else {  
        	return "-1";  
        }  
    }
    /**
     * 数据保存
     * zrl
     */
    public String saveOrUpdate(){
        if(formdata==null||formdata==""){
            return ajax(Status.success,"数据错误请联系管理员!");
        }else{
        	license=JsonUtil.toObject(formdata, License.class);
        } 
        try { 
            if(license.getId()==null||"".equals(license.getId().trim())){
                this.save(license);
            }else{
            	License oldInfo=this.getBeanByCriteria(License.class, Restrictions.eq("id", license.getId()));
            	if (oldInfo == null) {
                    throw new Exception("该条记录不存在");
                }
            	license.setPersonId(oldInfo.getPersonId());
            	oldInfo.copyFrom(license);
                this.update(oldInfo);
            }
        } catch (Exception e) {
            return ajax(Status.success,"保存/修改失败");
        }
        return ajax(Status.success,"success");
    } 

    
    /**
     * 删除方法
     * @Title: remove 
     * @return 
     * String 
     * @author zrl
     * @since 2017年3月8日 V 1.0
     */
    public String remove() {
    	if (ids != null && !"".equals(ids)){
    		String[] idArrs = ids.split(",");
    		try {
    			licenseService.delete(License.class, idArrs);
            } catch (Exception e) {
                e.printStackTrace();
            }
    	}
        return ajax(Status.success, "success");
    }
    /**
     * 上传文件
     * @Title: remove 
     * @return 
     * String 
     * @author zrl
     * @since 2017年3月8日 V 1.0
     */
    public String uploadFile() {
    	MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) this.getRequest();
        // 获取上传文件的名字
        String[] fileName=wrapper.getFileNames("uploadFile");
        File[] files = wrapper.getFiles("uploadFile");
        if(files[0].length()>104857600){
			msg = "上传失败，文件大小不能超过100M！";
		}
    	//创建文件夹
    	String filePath = ToolUtil.getPropert("config.properties", "real_path");
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String stamp = sdf.format(new Date());
        String str = fileName[0].substring(fileName[0].lastIndexOf("."));
        //相对路径
        String fileUrl=stamp+str;
        try {
            //判断文件夹是否存在，不存在则创建
            File dirTest = new File(filePath+"/baseInfo");
            if (!dirTest.exists()) {
                dirTest.mkdirs();
            }
            InputStream in = null;
            OutputStream out = null;
            try {
                in = new FileInputStream(files[0]);
                File saveFile = new File(dirTest,fileUrl);
                out = new FileOutputStream(saveFile);
                byte[] buffer = new byte[1024 * 1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                msg="success";
            } catch (Exception e) {
            	msg="上传失败！";
            	e.printStackTrace();
            } finally {
                in.close();
                out.close();
            }
        } catch (Exception e) {
        	msg="上传失败！";
        	e.printStackTrace();
        }
        
        HashMap<String, String> map=new HashMap<String,String>();
        map.put("msg", msg);
        map.put("fileUrl", fileUrl);
        String json=JsonUtil.Encode(map);
        
        return ajax(json);
    }
    /**
     * 文件下载
     * @return
     * zrl
     */
    public String download(){
    	 HttpServletResponse response = getResponse();
    	 License oldInfo=this.getBeanByCriteria(License.class, Restrictions.eq("id", id));
    	 if(oldInfo==null){
    		 return "数据不存在！";
    	 }
    	 try {
    		 String Pathbase = ToolUtil.getPropert("config.properties", "real_path");
    		 String filepath=Pathbase+"/baseInfo/"+oldInfo.getUrl();//文件路径
    		 String str = oldInfo.getUrl().substring(oldInfo.getUrl().lastIndexOf("."));
    		 response.setContentType("application/x-msdownload");
    		 response.setHeader("Content-Disposition", "attachment;filename="
			         + URLEncoder.encode(oldInfo.getFileName()+str, "UTF-8")); 
    		 
    		 File file = new File(filepath);
             // 输入文件流
             FileInputStream in = new FileInputStream(file);
             // 输出文件流
             OutputStream  out = response.getOutputStream();
             //创建缓冲区，进行写文件
             byte buffer[] = new byte[1024];
             int len = 0;
             while((len=in.read(buffer))>-1){
                 out.write(buffer, 0, len);
             }
             out.close();
             in.close();
             
             return null;
    	 }catch(Exception ex){
    		 ex.printStackTrace();
    	 }
    	 return null; 
    }
    public String look() {
    	License oldInfo=this.getBeanByCriteria(License.class, Restrictions.eq("id", id));
    	String Pathbase = ToolUtil.getPropert("config.properties", "file_path");
		url=Pathbase+"/baseInfo/"+oldInfo.getUrl();//文件路径
		return "picture";
    }
   //**********************set/get**********************//
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getFormdata() {
		return formdata;
	}
	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}
	public BasicInformation getInfo() {
		return info;
	}
	public void setInfo(BasicInformation info) {
		this.info = info;
	}

	public File getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public License getLicense() {
		return license;
	}
	public void setLicense(License license) {
		this.license = license;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getLicenseStatus() {
		return licenseStatus;
	}
	public void setLicenseStatus(String licenseStatus) {
		this.licenseStatus = licenseStatus;
	}
	public String getLoseDate() {
		return loseDate;
	}
	public void setLoseDate(String loseDate) {
		this.loseDate = loseDate;
	}

}
