package com.lingnet.qxgl.action.depart;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.common.action.BaseAction.Status;
import com.lingnet.qxgl.entity.QxDepartment;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.qxgl.service.AdminService;
import com.lingnet.qxgl.service.BackendDepService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.ToolUtil;

/** 
 * @ClassName: BackendDepAction 
 * @Description: 部门修改操作类
 * @author 张明峰
 * @date 2013-6-14 下午4:57:11 
 *  
 */
@Controller
@ParentPackage("depart")
public class BackendDepAction extends BaseAction  {
    
    private static final long serialVersionUID = 3774707022962529455L;
    
    @Resource(name="adminService")
    private AdminService adminService;
    
    @Resource(name="backendDepService")
    private BackendDepService backendDepService;
    
    
    private QxDepartment department;

    private String name ;//部门名称
    private String description ;//描述
    private String parent ;//父级部门
    private String child ;//子结点
    private String bid;//部门id
    private String pname;//父级部门名称
    private String formData;
    private String pid;//部门父节点
    private String did;//当前记录id
    private String cid;//分中心ID
    private String branchId;//单位ID
    /**
     * 跳转到部门列表
     */
    public String list(){
        return "list";
    }
    
    /**
     * 跳转到部门添加页面
     */
    public String add(){
        
        department = new QxDepartment();
        pname=null;
        return "add";
    }
    /**
     * 跳转到树展现页面
     */
    public String tree(){
        
        return "tree";
    }
  public String tree1(){
        
        return "tree1";
    }
    /**
     * 跳转到编辑部门
     */
    public String edit(){
        
        department = backendDepService.get(id);
        if(department!=null){
            String pid = department.getParent();
            if(!pid.equals("ROOT")){
                QxDepartment pdepartment = backendDepService.get(QxDepartment.class, Restrictions.eq("id", department.getParent()));
                pname = pdepartment.getName();
            }else{
                pname = "ROOT";
            }
            
            return "add";
        }else{
            return ajax(Status.error,"数据不存在或被删除，请关闭页面重试!");
        }
       
    }
    
    /**
     * 获取部门列表内容
     */
    public String getdata(){
        
        @SuppressWarnings("rawtypes")
        List data = backendDepService.getAll();
        String json = JsonUtil.Encode(data);
        
        return ajax(Status.success, json);
    }
    
    /**
     * 删除部门
     */
    public String delete(){
        
        String json = "";
        
        try {
            json = backendDepService.remove(pager,id);
        } catch (Exception e) {
            
            return ajax(Status.error,e.getMessage());
        }
        
        
        return ajax(Status.success,json);
    }
    
    @SuppressWarnings("rawtypes")
    public String treedata1(){
        List data = backendDepService.getTreeAll1(cid);
        
        String json = JsonUtil.Encode(data);
        
        return ajax(Status.success, json);
       
    }
    /**
     * 获取部门选择树，添加页面用到
     */
    @SuppressWarnings("rawtypes")
    public String treedata(){
        List data = backendDepService.getTreeAll();
        
        String json = JsonUtil.Encode(data);
        
        return ajax(Status.success, json);
       
    }
    
    /**
     * 根据单位ID查询下属部门集合
     * @Title: getDepList 
     * @return 
     * @author wangqiang
     * @since 2017年4月19日 V 1.0
     */
    public String getDepList(){
        String json = "";
        json = backendDepService.getDepListByBranchId(branchId);
       return ajax(Status.success, json); 
    }
    
    /**
     * 获取部门选择树，添加页面用到
     */
    @SuppressWarnings("rawtypes")
    public String treedata2(){
        List data = backendDepService.getTreeAll2();
        
        String json = JsonUtil.Encode(data);
        
        return ajax(Status.success, json);
    }
    
    /**
     * 获取人员部门树列表，人员部门页面用到
     */
    public String  treelist(){
        @SuppressWarnings("rawtypes")
        List data = backendDepService.getTreeList();
        
        String json = JsonUtil.Encode(data);
        
        return ajax(Status.success, json);
        
    }
    
    /**
     * 添加机构部门
     */
    public String saveOrUpdata(){
        String flg="";
        try {
            flg = backendDepService.saveOrUpdata(formData,child);
        } catch (Exception e) {
            e.printStackTrace();
            return ajax(Status.error,e.getMessage());
        }
        return ajax(Status.success,flg);
    } 
    /**
     * 获取部门treegrid数据
     * @Title: treeGridData 
     * @return 
     * String 
     * @author lsp		
     * @since 2016-8-3 V 1.0
     */
    public String treeGridData(){
        String json = "";
        HashMap<String, String> map = new HashMap<String, String>();
        json = backendDepService.treeGridData(map);
        return ajax(Status.success, json);
    }
    /**
     * 获取所有功能科室
     * @Title: getSyks 
     * @return 
     * String 
     * @author lsp		
     * @since 2016-8-16 V 1.0
     */
    public String getSyks(){
        String json = "";
        json = backendDepService.getStyks(key);
        return ajax(Status.success, json);
    }
    /**
     * 获取所有功能科室
     * @Title: getSyks 
     * @return 
     * String 
     * @author lsp		
     * @since 2016-8-16 V 1.0
     */
    public String getAllks(){
        String json = "";
        json = backendDepService.getAllks(key);
        return ajax(Status.success, json);
    }
    
    /**
     * 获取开单医师
     * @Title: getKdys 
     * @return 
     * String 
     * @author lsp		
     * @since 2016-8-17 V 1.0
     */
    public String getKdys(){
        String json="";
        json = backendDepService.getKdys(key);
        return ajax(Status.success, json);
    }
    
    /**
     * 获取加项医师
     * @Title: getJxys 
     * @return 
     * String 
     * @author lsp		
     * @since 2016-8-18 V 1.0
     */
    public String getJxys(){
        String json = "";
        json = backendDepService.getJxys(key);
        return ajax(Status.success,json);
            
    }
    
    /**
     * 判断是否为销售部门
     * @Title: isXsb 
     * @return 
     * String 
     * @author mbx
     * @since 2016-8-23 V 1.0
     */
    public String isXsb(){
        return ajax(Status.success,JsonUtil.Encode(backendDepService.isXsb(pid,did)));
    }
    
    /**
     * 获取分中心下某部门下的所有人员信息
     * @Title: getCenDepUserData 
     * @return 
     * String 
     * @author lsp		
     * @since 2016-8-24 V 1.0
     */
    public String getCenDepUserData(){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("cid", cid);
        map.put("name", name);
        String json = "";
        json = backendDepService.getCenDepUserData(pager,map);
        return ajax(Status.success, json);
    }
    /**
     * 获取医师
     * @Title: getDoctor 
     * @return 
     * String 
     * @author xuhp
     * @since 2016-12-30 V 1.0
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public String getDoctor(){
    	String userName = ToolUtil.userName();
        QxUsers user = adminService.getUserByName(userName);
        String cid = user.getCid();
        Conjunction con=new Conjunction();
        con.add(Restrictions.eq("isDelete", 0));
        con.add(Restrictions.eq("cid", cid));
        con.add(Restrictions.eq("isDoc",1));
        if(StringUtils.isNotEmpty(key)){
        	con.add(Restrictions.like("inputCode", key.trim().toUpperCase(),MatchMode.ANYWHERE));
        }
    	List<QxUsers> data=(List<QxUsers>) backendDepService.findPagerByOrder(QxUsers.class, pager, Order.desc("createDate")
    			,con).getResult();
    	List<Map<String,String>> list=new ArrayList<Map<String,String>>();
    	for(QxUsers os:data){
    		Map<String,String>map=new HashMap<String, String>();
    		map.put("name", os.getName());
    		map.put("inputCode", os.getInputCode());
    		list.add(map);
    	}
    	HashMap result=new HashMap();
    	result.put("data", list);
    	result.put("total", pager.getTotalCount());
    	return ajax(Status.success, JsonUtil.toJson(result));
    }
    
    public BackendDepService getBackendDepService() {
        return backendDepService;
    }

    public void setBackendDepService(BackendDepService backendDepService) {
        this.backendDepService = backendDepService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }


    public QxDepartment getDepartment() {
        return department;
    }


    public void setDepartment(QxDepartment department) {
        this.department = department;
    }
    
    public AdminService getAdminService() {
        return adminService;
    }

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }


    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getFormData() {
        return formData;
    }

    public void setFormData(String formData) {
        this.formData = formData;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

}
