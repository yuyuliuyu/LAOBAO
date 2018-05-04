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
import com.lingnet.hcm.entity.person.Award;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.entity.person.Punish;
import com.lingnet.hcm.entity.person.Record;
import com.lingnet.hcm.service.empdata.PunishService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.ToolUtil;

/**
 * 
 * @ClassName: DepartureAction 
 * @Description: 处罚Action 
 * @author zrl
 * @date 2017年3月10日 上午10:56:46 
 *
 */
@Controller
public class PunishAction extends BaseAction{

    private static final long serialVersionUID = -4533377460351784579L;
    
    private String flag;//标志位
    private String formdata;
    private File uploadFile;//上传文件
    private String msg;//标志位
    private String url;//证书编号
    private String personId;
    
    private BasicInformation info;
    private Punish punish;
    private Record record;
    
    @Resource(name="punishService")
    private PunishService punishService;
    
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
        return ajax(Status.success, JsonUtil.Encode(punishService.getListData(pager,searchData)));
    }
    
    /**
     * 添加页面
     */
    public String add(){
    	punish=this.getBeanByCriteria(Punish.class, Restrictions.eq("id", id));
    	String personId=null;
    	if(punish!=null){
    		personId=punish.getPersonId();
    	}
    	info=this.getBeanByCriteria(BasicInformation.class, Restrictions.eq("id", personId));
        return "add";
    }

    public String saveOrUpdate(){
        if(formdata==null||formdata==""){
            return ajax(Status.success,"数据错误请联系管理员!");
        }else{
        	punish=JsonUtil.toObject(formdata, Punish.class);
        } 
        try { 
            if(punish.getId()==null||"".equals(punish.getId().trim())){
                this.save(punish);
                record=new Record();
                record.setFileSize(punish.getId());
                record.setPersonId(punish.getPersonId());
                record.setJobNumber(punish.getJobNumber());
                record.setReName(punish.getFileName());
                record.setReNum(punish.getFileNum());
                record.setImgpath(punish.getUrl());
                record.setReType(3);
                this.save(record);
            }else{
            	Punish oldInfo=this.getBeanByCriteria(Punish.class, Restrictions.eq("id", punish.getId()));
            	if (oldInfo == null) {
                    throw new Exception("该条记录不存在");
                }
            	punish.setPersonId(oldInfo.getPersonId());
            	if(punish.getUrl()==null||"".equals(punish.getUrl())){
            		punish.setUrl(oldInfo.getUrl());
            	}
            	oldInfo.copyFrom(punish);
                this.update(oldInfo);
                Record re=this.getBeanByCriteria(Record.class, Restrictions.eq("fileSize", punish.getId()));
                re.setPersonId(oldInfo.getPersonId());
                re.setJobNumber(oldInfo.getJobNumber());
                re.setReName(oldInfo.getFileName());
                re.setReNum(oldInfo.getFileNum());
                re.setImgpath(oldInfo.getUrl());
                this.update(re);
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
    			punishService.delete(Punish.class, idArrs);
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
            File dirTest = new File(filePath+"/dangan");
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
        map.put("fileUrl", "/dangan/"+fileUrl);
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
    	 Punish oldInfo=this.getBeanByCriteria(Punish.class, Restrictions.eq("id", id));
    	 if(oldInfo==null){
    		 return "数据不存在！";
    	 }
    	 try {
    		 String Pathbase = ToolUtil.getPropert("config.properties", "real_path");
    		 String filepath=Pathbase+oldInfo.getUrl();//文件路径
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
	public Punish getPunish() {
		return punish;
	}
	public void setPunish(Punish punish) {
		this.punish = punish;
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
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}

}
