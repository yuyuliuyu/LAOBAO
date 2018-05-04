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
import com.lingnet.common.action.BaseAction.Status;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.entity.person.Record;
import com.lingnet.hcm.entity.person.Relation;
import com.lingnet.hcm.service.empdata.RecordService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
import com.lingnet.util.ToolUtil;

/**
 * 
 * @ClassName: DepartureAction 
 * @Description: 档案Action 
 * @author zrl
 * @date 2017年3月10日 上午10:56:46 
 *
 */
@Controller
public class RecordAction extends BaseAction{

    private static final long serialVersionUID = -4533377460351784579L;
    
    private String flag;//标志位
    private String formdata;
    private File uploadFile;//上传文件
    private String msg;//标志位
    private String url;//证书编号
    private String path;// 上传路径
    private String personId;
    
    private BasicInformation info;
    private Record record;
    
    @Resource(name="recordService")
    private RecordService recordService;
    
    /**
     * 列表页面
     */
    public String list(){
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
        return ajax(Status.success, JsonUtil.Encode(recordService.getListData(pager,searchData)));
    }
    
    /**
     * 添加页面
     */
    public String add(){
    	record=this.getBeanByCriteria(Record.class, Restrictions.eq("id", id));
    	String personId=null;
    	if(record!=null){
    		personId=record.getPersonId();
    	}
    	info=this.getBeanByCriteria(BasicInformation.class, Restrictions.eq("id", personId));
        return "add";
    }

    /**
     * 数据保存
     * zrl
     */
    public String saveOrUpdate(){
    	String flg;
        try {
            flg = recordService.saveOrUpdata(formdata);
            if(flg.equals("success")){
                return ajax(Status.success, "success");
            }else{
                return ajax(Status.error,flg);
            }
        } catch (Exception e) {
            return ajax(Status.error,e.getMessage());
        }
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
    			recordService.delete(Record.class, idArrs);
            } catch (Exception e) {
                e.printStackTrace();
            }
    	}
        return ajax(Status.success, "success");
    }

    /**
     * 文件下载
     * @return
     * zrl
     */
    public String download(){
    	 HttpServletResponse response = getResponse();
    	 Record oldInfo=this.getBeanByCriteria(Record.class, Restrictions.eq("id", id));
    	 if(oldInfo==null){
    		 return "数据不存在！";
    	 }
    	 try {
    		 String Pathbase = ToolUtil.getPropert("config.properties", "real_path");
    		 String filepath=Pathbase+oldInfo.getImgpath();//文件路径
    		 String str = oldInfo.getImgpath().substring(oldInfo.getImgpath().lastIndexOf("."));
    		 String user=LingUtil.userName();
    		 response.setContentType("application/x-msdownload");
    		 response.setHeader("Content-Disposition", "attachment;filename="
			         + URLEncoder.encode(user+"档案文件"+str, "UTF-8")); //----------需要修改
    		 
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
	public Record getRecord() {
		return record;
	}
	public void setRecord(Record record) {
		this.record = record;
	}
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}

}
