package com.lingnet.hcm.action.contract;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.DeptPosition;
import com.lingnet.hcm.entity.ImageInfo;
import com.lingnet.hcm.entity.check.ContractLogs;
import com.lingnet.hcm.entity.contract.ContractManager;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.qxgl.entity.QxDepartment;
import com.lingnet.qxgl.entity.QxUser;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
import com.lingnet.util.ToolUtil;
/**
 * @ClassName: ContractManagerAction
 * @Description: TODO
 * @author 马晓鹏
 * @date 2017年5月11日 下午2:45:13
 * 合同
 */
@SuppressWarnings("serial")
public class ContractManagerAction extends BaseAction{
    private String id;       //主键id   
    private String formcode; //图片所属的识别号
    private String formid;   //图片所属id
    private String jsondata; 
    private ContractManager contract;
    private BasicInformation information;//基础数据
    private String state;
    private String username;
    private String deptname;
    private String remark;
    private String imagename;
    private String pname;
    private String msg;
    
    private String show;//查看合同使用
    private String personId;
    private String flag;
    
    private List<ContractManager> contractlist=new ArrayList<ContractManager>();
    @Resource(name="personnelService")
    private PersonnelService personnelService;
    public String list(){
        return "list";
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String jsonlist(){
        try {
        	if(personId!=null&&!"".equals(personId)){
        		pager=this.findPagerByOrder(ContractManager.class, pager,Order.desc("userid"),Restrictions.isNotNull("id"),
        				Restrictions.eq("userid", personId));
        	}else{
        		pager=this.findPagerByOrder(ContractManager.class, pager,Order.desc("userid"),Restrictions.isNotNull("id"));
        	}
        
        } catch (Exception e) { 
            e.printStackTrace();
        }   
        Map result = new HashMap();
        contractlist=(List<ContractManager>) pager.getResult();
        List<Map<String, String>> list=new ArrayList<Map<String,String>>();
        for (int i = 0; i < contractlist.size(); i++) {
            Map<String, String> map=new HashMap<String, String>();
            map.put("id", contractlist.get(i).getId());
            try {
            	
            	information=personnelService.get(Restrictions.eq("id", contractlist.get(i).getUserid()));
            	if(information!=null){
                	map.put("jobNumber", information.getJobNumber());
                	map.put("pername", information.getName());
                	map.put("filmName", information.getFilmName());
                	map.put("departName", information.getDepartName());
                	map.put("post", information.getPost());
                	map.put("specificPost", information.getSpecificPost());
                }
            	
            	
            } catch (Exception e) {
                map.put("username","");
            } 
              map.put("userid", contractlist.get(i).getUserid());
              map.put("contractid", contractlist.get(i).getContractid());
              if("0".equals(contractlist.get(i).getContracttype().trim())){
                  map.put("contracttype", "劳动合同"); 
              }else if("1".equals(contractlist.get(i).getContracttype().trim())){
                  map.put("contracttype", "聘用合同"); 
              }else{
                  map.put("contracttype", ""); 
              }
              try {
                  map.put("contrstart", new SimpleDateFormat("yyyy/MM/dd").format(contractlist.get(i).getContrstart()));
            } catch (Exception e) {
                map.put("contrstart", "");
            }
              try {
                  map.put("contractend",new SimpleDateFormat("yyyy/MM/dd").format(contractlist.get(i).getContractend()));
            } catch (Exception e) {
                map.put("contractend", "");
            }
              map.put("workaddr", contractlist.get(i).getWorkaddr());
              map.put("worksys", contractlist.get(i).getWorksys()); 
              map.put("renewstate", contractlist.get(i).getRenewstate());
 
              if("0".equals(contractlist.get(i).getContractstate().trim())){
                  map.put("contractstate", "履行合同"); 
              }else if("1".equals(contractlist.get(i).getContractstate().trim())){
                  map.put("contractstate", "中止合同"); 
              }else{
                  map.put("contractstate", "解除合同"); 
              }
              map.put("getbackup", contractlist.get(i).getGetbackup()); 
            list.add(map);
        }

        result.put("data", list);
        result.put("total", pager.getTotalCount());
        String json = JsonUtil.Encode(result);
        return ajax(Status.success,json); 
    }
    /**
     * 材料列表
     * @Title: uploadjson 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年5月16日 V 1.0
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String uploadjson(){ 
        try {
            pager=this.findPagerByOrder(ImageInfo.class, pager,Order.desc("formid"),Restrictions.eq("formid", formid));
        } catch (Exception e) { 
            e.printStackTrace();
        }   
        Map result = new HashMap();
        List<ImageInfo> imagelist=((List<ImageInfo>) pager.getResult());
        List<Map<String, String>> list=new ArrayList<Map<String,String>>();
        for (int i = 0; i < imagelist.size(); i++) {
            Map<String, String> map=new HashMap<String, String>();
            map.put("id", imagelist.get(i).getId()); 
            map.put("imagename", imagelist.get(i).getImagename());
            map.put("imageurl", imagelist.get(i).getImageurl());
            map.put("remark", imagelist.get(i).getRemark()); 
            if(imagelist.get(i).getImageurl()!=null){
                String pathbase = ToolUtil.getPropert("config.properties", "file_path");
                msg=pathbase+"/baseInfo/" +imagelist.get(i).getImageurl();
            }
            map.put("realimageurl", msg);
            list.add(map);
        }

        result.put("data", list);
        result.put("total", pager.getTotalCount());
        String json = JsonUtil.Encode(result);
        return ajax(Status.success,json); 
    }
    /**
     * 打开图片上传页面
     * @Title: addimage 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年5月16日 V 1.0
     */
    public String addimage(){
        return "addimage";
    }
    /**
     * 上传图片
     * @Title: uploadimage 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年5月16日 V 1.0
     */
    public String uploadimage(){
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
                ImageInfo imginfo=new ImageInfo();
                imginfo.setFormcode(formcode);
                imginfo.setFormid(formid);
                imginfo.setImagename(imagename);
                imginfo.setRemark(remark);
                imginfo.setImageurl(fileUrl);
                this.save(imginfo);
                msg = "上传成功，请关闭此页面";
                return "addimage";
            } catch (Exception e) {
                e.printStackTrace(); 
                msg = "上传失败";
                return "addimage";
            } finally {
                in.close();
                out.close();
            }
        } catch (Exception e) { 
            e.printStackTrace();
            msg= "上传失败！";  
            return "addimage";
        } 
    }
    public String removeimage(){
        if(id==null||"".equals(id.trim())){
            return ajax(Status.success,"数据错误，删除失败");
        }else{
            this.deleteByCriteria(ImageInfo.class,Restrictions.eq("id",id));
        }
        return ajax(Status.success,"删除成功");
    }
    /*public String showimage(){ 
        if(id==null||"".equals(id.trim())){
            return ajax(Status.success,"数据错误，删除失败");
        }else{
            ImageInfo imginfo=this.getBeanById(ImageInfo.class,id);
            msg=imginfo.getImageurl(); 
            remark=imginfo.getRemark();
            String pathbase = ToolUtil.getPropert("config.properties", "file_path");
            msg=pathbase+"/baseInfo/" +msg;
        }  
        return "show";
    }*/
    public String add(){
        if(id==null||"".equals(id.trim())){
            contract=new ContractManager();
            contract.setContractstate("0");
        }else{
            contract=this.getBeanById(ContractManager.class, id);
            try {
                username=personnelService.get(Restrictions.eq("id", contract.getUserid())).getName();
            } catch (Exception e) {
                username="";
            }
            try {
                deptname=this.getBeanByCriteria(QxDepartment.class, Restrictions.eq("id", contract.getDepartid())).getName();
            } catch (Exception e) {
                deptname="";
            }
            try {
//                pname=this.getBeanByCriteria(PositionGrade.class, Restrictions.eq("id", contract.getPostid())).getName();
                pname=this.getBeanByCriteria(DeptPosition.class, Restrictions.eq("id", contract.getPostid())).getPositionName();
            } catch (Exception e) {
                pname="";
            }
        }
        return "add";
    }
    public String saveOrUpdate(){
        if(jsondata==null||"".equals(jsondata.trim())){
            return ajax(Status.success,"数据错误，请查证后重新操作");
        } 
        contract=JsonUtil.toObject(jsondata,ContractManager.class);
        if(contract==null){
            return ajax(Status.success,"数据错误，请查证后重新操作");
        }else if(contract.getId()==null||"".equals(contract.getId().trim())){
//            contract.setState(1);
            this.save(contract);
        }else{
            this.update(contract);
             ContractLogs logs=new ContractLogs();
             QxUser userinfo=LingUtil.userinfo();
             logs.setChangemanid(userinfo.getId());
             logs.setChangeman(userinfo.getName());
             logs.setContractid(contract.getId());
             logs.setCreateDate(new Date());
             logs.setModifyDate(new Date());
             logs.setChengememo(contract.getContractid());
             this.save(logs);
        }
        return ajax(Status.success,"保存/修改成功");
    }
//    public String remove(){
//        if(id==null||"".equals(id.trim())){
//            return ajax(Status.success,"数据错误，删除失败");
//        }else{
//            this.deleteByCriteria(ContractManager.class,Restrictions.eq("id",id));
//        }
//        return ajax(Status.success,"删除成功");d
//    }
    public String changestate(){

      if(id==null||"".equals(id.trim())){
          return ajax(Status.success,"数据错误，操作失败");
      }else{
          contract =this.getBeanById(ContractManager.class,id);
          contract.setContractstate(state);
          this.update(contract);
      }
      return ajax(Status.success,"操作成功");
    }
    
    /*-----------------------------------------getter and  setter----------------------------------------------------*/
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getJsondata() {
        return jsondata;
    }
    public void setJsondata(String jsondata) {
        this.jsondata = jsondata;
    }
    public ContractManager getContract() {
        return contract;
    }
    public void setContract(ContractManager contract) {
        this.contract = contract;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public List<ContractManager> getContractlist() {
        return contractlist;
    }
    public void setContractlist(List<ContractManager> contractlist) {
        this.contractlist = contractlist;
    }
    public String getDeptname() {
        return deptname;
    }
    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }
    public String getPname() {
        return pname;
    }
    public void setPname(String pname) {
        this.pname = pname;
    }
    public String getFormcode() {
        return formcode;
    }
    public void setFormcode(String formcode) {
        this.formcode = formcode;
    }
    public String getFormid() {
        return formid;
    }
    public void setFormid(String formid) {
        this.formid = formid;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getImagename() {
        return imagename;
    }
    public void setImagename(String imagename) {
        this.imagename = imagename;
    }
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public BasicInformation getInformation() {
		return information;
	}
	public void setInformation(BasicInformation information) {
		this.information = information;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
