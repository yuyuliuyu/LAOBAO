package com.lingnet.qxgl.action.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.qxgl.entity.QxDepartment;
import com.lingnet.qxgl.entity.QxRoles;
import com.lingnet.qxgl.entity.QxUseDep;
import com.lingnet.qxgl.entity.QxUser;
import com.lingnet.qxgl.entity.QxUserDatauth;
import com.lingnet.qxgl.entity.QxUserShowauth;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.qxgl.service.AdminService;
import com.lingnet.qxgl.service.BackendRoleService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;

@Controller
/**
 * 使用注解配置返回的试图资源
 * 如果需使用namespace：
 * (1)包后直接添加名称如：com.lingnet.qxgl.action.admin
 * (2)使用注解@Namespace("/system")
 * 添加namespace后，返回的视图资源路径中也必须增加与namespace同名的文件夹，
 * 否则会无法找到视图资源，如：
 * 无namespace：jsp/admin_main
 * 有namespace：jsp/admin/admin_mian
 * @author     Jerry
 *
 */
public class AdminAction extends BaseAction {
    private static final long serialVersionUID = -1885854904242552572L;

    @Resource(name = "adminService")
    private AdminService adminService;
    
    @Resource(name = "backendRoleService")
    private BackendRoleService backendRoleService;
    
    private QxUser userinfo;
    private QxUsers admin;
    private List<QxRoles> backendRoles;
    
    private String depId;//部门id
    private String depName;//部门名称
    private String data;
    private String value;
    private String name; //转换部门时候页面展现的姓名
    private String key;
    private String rad;
    private String cid;
    private String flag;//转移部门时是否同时存在两个部门的标志 0 不是 1是
    private Map<String, String> roleMap;
    public  String changepwd(){
        userinfo=LingUtil.userinfo();
        return "changepwd";
    }
    public String savechangepwd(){
        QxUser uinfo=LingUtil.userinfo();
        try {
            userinfo=JsonUtil.toObject(data, QxUser.class);
        } catch (Exception e) { 
            return ajax(Status.success,"修改失败，未找到该用户");
        } 
        MessageDigestPasswordEncoder encoder = new MessageDigestPasswordEncoder("SHA-256");
        
        if(userinfo.getPassword()==null||"".equals(userinfo.getPassword())){
            return ajax(Status.success,"修改失败，密码未接收");
        }else{
            uinfo.setPassword(encoder.encodePassword(userinfo.getPassword(),uinfo.getUsername()));
        }
        QxUsers yser=adminService.get(uinfo.getId());
        yser.setPassword(uinfo.getPassword());
        adminService.update(yser);
        return ajax(Status.success,"success");
    }
    /**
     * 用户列表页数据展现
     */
    @SuppressWarnings("rawtypes")
    public String userList() {
        try {
            String json ="";
            HashMap result = adminService.getUsersByDepId(pager,depId,key,cid);
            json = JsonUtil.Encode(result);
            return ajax(Status.success, json);
        } catch (Exception e) {
          e.printStackTrace();
        }
      
        return null;
    }
    
    /**
	 * 登录页面分中心数据
	 * @Title: centerListData 
	 * @return 
	 * String 
	 * @author xuhp
	 * @since 2016-11-14 V 1.0
	 */
	public String centerListData(){
		List<Branch> branchs=this.getBeanListByCriteria(Branch.class,Restrictions.eq("isDelete", 0));
		List<Map<String,String>> result=new ArrayList<Map<String,String>>();
		for(Branch branch:branchs){
			Map<String,String> map=new HashMap<String, String>();
			map.put("id", branch.getId());
			map.put("pId", branch.getPid());
			map.put("name", branch.getFzx());
			map.put("isDefault", branch.getIsDefault()==null?"0":branch.getIsDefault().toString());
			result.add(map);
		}
		return ajax(Status.success, JsonUtil.toJson(result));
	}
    /**
     * 进入用户列表页面
     */
    public String list() {
        return "all";
    }
    
    /**
     * 进入用户添加页面
     */
    public String add() {
        admin = new QxUsers();
        if(depName.contains("销售")){
            this.getRequest().setAttribute("state", 0);
        }else{
            this.getRequest().setAttribute("state", 1);
        }
        admin.setDepId(depId);
        admin.setCid(cid);
        return "add";
    }
    
    /**
     * 进入编辑页面
     */
    public String edit() {
        admin = getBeanByCriteria(QxUsers.class, Restrictions.eq("id", id));
       Set<QxRoles> roleset=admin.getQxRoles();
       roleMap = new HashMap<String, String>();
       for (QxRoles qxRoles : roleset) { 
//               value=value+qxRoles.getId();
           roleMap.put(qxRoles.getId(), qxRoles.getId());
       }
        List<QxUseDep> qxUseDep = adminService.getList(QxUseDep.class, Restrictions.eq("userId", admin.getId()));
        String name="";
        if (qxUseDep.size() == 1) {
        	depId=qxUseDep.get(0).getDepId();
            QxDepartment qxDepartment = adminService.get(QxDepartment.class, Restrictions.eq("id",depId ));
            if(qxDepartment!=null){
            	name = qxDepartment.getName();
            }
        } else {
            for (QxUseDep UseDep : qxUseDep) {
                if (null != UseDep&&null != UseDep.getDepId()) {
                    name = getDeprId(UseDep.getDepId());
                    if(depId==null){
                    	depId=UseDep.getDepId();
                    }else{
                    	depId=depId+","+UseDep.getDepId();
                    }
                }
            }
        }

        // 销售部或者客服部才显示折扣
        if(name.contains("销售") || name.contains("客服")){
            this.getRequest().setAttribute("state", 0);
        }else{
            this.getRequest().setAttribute("state", 1);
        }

        return "add";
    }

    /**
     * 进入部门选择界面
     */
    public String change(){
        String[] ids = id.split(",");
        name = "";
        
        for(int i=0;i<ids.length;i++){
            if(!ids[i].equals("")&&!ids[i].equals("null")){
                
                admin = adminService.get(ids[i]);
                if(!name.equals("")){
                    name = name+"、"+admin.getName();
                }else{
                    name =admin.getName();
                }
            }
        }
        return "change";
    }
    
    /**
     * 进入首页-修改个人资料页面
     */
    public String edit2() {
		Object oPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = ((UserDetails) oPrincipal).getUsername();// 用户名
		
		admin = adminService.get(Restrictions.eq("username", userName));
		if(admin!=null){
			id=admin.getId();
		    
		    List<QxUseDep> qxUseDep = adminService.getList(QxUseDep.class, Restrictions.eq("userId", admin.getId()));
		    String name="";
		    if (qxUseDep.size() == 1) {
		        QxDepartment qxDepartment = adminService.get(QxDepartment.class, Restrictions.eq("id", qxUseDep.get(0).getDepId()));
		        if(qxDepartment!=null){
		        	name = qxDepartment.getName();
		        }
		        
		    } else {
		        for (QxUseDep UseDep : qxUseDep) {
		            if (null != UseDep&&null != UseDep.getDepId()) {
		                name =name +";"+ getDeprId(UseDep.getDepId());
		            }
		        }
		    }
		    if(name.contains("客服部")){
		    	this.getRequest().setAttribute("show", 1);
		    }else{
		    	this.getRequest().setAttribute("show", 0);
		    }
		    
		}
        return "modify";
    }
    
    /**
     * 保存或更新用户信息
     */
    public String save() {
        JsonUtil sut = new JsonUtil();
        String flg;
        try {
            flg= adminService.savedata(sut,data,value,rad,depId);
        } catch (Exception e) {
            return ajax(Status.error,e.toString().substring(e.toString().indexOf(":")+1));
        }
        /*定义flg用于判断是否赋值给 redirectUrl 栾胜鹏  2014-10-20*/
        if(flg!=null){
            redirectUrl=flg;
        }
        return ajax(Status.success,"success");
    }
    
    /**
     * 删除用户信息
     */
    public String remove() {
        String flg = "";
        try {
            flg = adminService.remove(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return ajax(Status.error, e.getMessage());
        }
        return ajax(Status.success, flg);
    }
    
    public String authEdit() { 
    	admin = adminService.get(id);
        List<QxUseDep> qxUseDep = adminService.getList(QxUseDep.class, Restrictions.eq("userId", admin.getId()));
        String name=""; 
        if (qxUseDep.size() == 1) {
        	depId=qxUseDep.get(0).getDepId();
            QxDepartment qxDepartment = adminService.get(QxDepartment.class, Restrictions.eq("id",depId ));
            if(qxDepartment!=null){
            	name = qxDepartment.getName();
            }
            
        } else {
            for (QxUseDep UseDep : qxUseDep) {
                if (null != UseDep&&null != UseDep.getDepId()) {
                    name = getDeprId(UseDep.getDepId());
                    if(depId==null){
                    	depId=UseDep.getDepId();
                    }else{
                    	depId=depId+","+UseDep.getDepId();
                    }
                }
            }
        }
    	return "auth_edit";
    }
    
    /**
     * 禁用用户信息
     */
    public String unable() {
        String flg = "success";
        try {
        	QxUsers user = this.getBeanByCriteria(QxUsers.class, Restrictions.eq("id", ids));
        	user.setUserEnabled(false);
//        	user.setIsDelete(1);
        	this.update(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ajax(Status.error, e.getMessage());
        }
        return ajax(Status.success, flg);
    }
    
    /**
     * 启用用户信息
     */
    public String able() {
        String flg = "success";
        try {
        	QxUsers user = this.getBeanByCriteria(QxUsers.class, Restrictions.eq("id", ids));
        	user.setUserEnabled(true);
        	this.update(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ajax(Status.error, e.getMessage());
        }
        return ajax(Status.success, flg);
    }
    
    
    /**
     * 用户转移部门方法
     */
    public String changedep(){
        String flg="";
        String[] ids = id.split(",");
        
        try {
            flg = adminService.changedep(ids,data,flag,depId);
        } catch (Exception e) {
           return ajax(Status.error, e.toString().substring(e.toString().indexOf(":")+1));
        }
        
        if(flg.equals("success")){
            return ajax(Status.success, "success");
        }else{
            return ajax(Status.error, flg); 
        }
    }
    
    /**
     * 首页-个人信息修改方法
     */
    public String savetwo() {
        String flg = "";
        
        try{
            flg = adminService.saveTwo(data);
        }catch(Exception e){
            return ajax(Status.error, e.toString().substring(e.toString().indexOf(":")+1));
        }
        
        if(flg.equals("success")){
            return ajax(Status.success, "success");
        }else{
            return ajax(Status.error, flg); 
        }
    }
    
    
    
    /**
     * 获取所有管理角色集合
     */
    public List<QxRoles> getAllRoleList() {
        
        return backendRoleService.getAllList();
    }
    
    /**
     * 用户名是否可用验证
     */
    public String checkUsername() {
        if (adminService.isExistByUsername(admin.getUsername())) {
            return ajax("false");
        } else {
            return ajax("true");
        }
    }
    
    /**
     * 邮箱是否可用验证
     */
    public String checkEmail() {
        
        return ajax("true");
    }
    
    /**
     * 转向用户列表展示
     */
    public String show() {
        return "show";
    }
    /**
     * 获取科室
     * @Title: getKs 
     * @return 
     * String 
     * @author lsp		
     * @since 2016-8-8 V 1.0
     */
    public String getKs(){
        String json = "";
        json = adminService.getks(id,key);
        return ajax(Status.success,json);
    }
    /**
     * 跳转用户管理-展示权限
     * @Title: datauthAdd 
     * @return 
     * String 
     * @author xuhp
     * @since 2017-3-16 V 1.0
     */
    public String datauthAdd(){
    	List<QxUserDatauth> auths=getBeanListByCriteria(QxUserDatauth.class,Restrictions.eq("userid",id));
    	int l=auths.size();
        depId="";
    	if(l>0){
    		StringBuilder builder=new StringBuilder();
    		for(int i=0;i<l;i++){
    			builder.append(auths.get(i).getBranchDep()+",");
    		}
    		depId=builder.substring(0,builder.length()-1);
    	}
    	return "datauth_add";
    }

    /**
     * 跳转用户管理-数据权限
     * @Title: datauthAdd 
     * @return 
     * String 
     * @author xuhp
     * @since 2017-3-16 V 1.0
     */
    public String datauthshow(){
        List<QxUserShowauth> auths=getBeanListByCriteria(QxUserShowauth.class,Restrictions.eq("userid",id));
        int l=auths.size();
        depId="";
        if(l>0){
            StringBuilder builder=new StringBuilder();
            for(int i=0;i<l;i++){
                builder.append(auths.get(i).getBranchDep()+",");
            }
            depId=builder.substring(0,builder.length()-1);
        }
        return "datauth_show";
    }
    /**
     * 用户管理-展示权限保存
     * @Title: saveDatauth 
     * @return 
     * String 
     * @author xuhp
     * @since 2017-3-16 V 1.0
     */
    public String saveDatauth(){
    	String result="";
    	try {
			result=adminService.saveDatauth(id, depId, flag);
		} catch (Exception e) {
			e.printStackTrace();
			result="保存失败！";
		}
    	return ajax(Status.success,result);
    }
    /**
     * 生成超级管理员权限
     * @Title: superauth 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年6月28日 V 1.0
     */
    public String superauth(){
        String result="";
        result=adminService.superauth();
        return ajax(Status.success,result);
    } 
    /**
     * 用户管理-数据权限保存
     * @Title: saveDatauth 
     * @return 
     * String 
     * @author xuhp
     * @since 2017-3-16 V 1.0
     */
    public String saveshowauth(){
        String result="";
        try {
            result=adminService.saveshowauth(id, depId, flag);
        } catch (Exception e) {
            e.printStackTrace();
            result="保存失败！";
        }
        return ajax(Status.success,result);
    }

    /**
     * @Title: 查找用户
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年9月19日 V 1.0
     */
    public String getUserList() {
        return ajax(Status.success, JsonUtil.Encode(adminService.getUserList(pager, depId, key, cid)));
    }
    /////////////////////////////////////////////////////////////////////
    public AdminService getAdminService() {
        return adminService;
    }

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public BackendRoleService getBackendRoleService() {
        return backendRoleService;
    }

    public void setBackendRoleService(BackendRoleService backendRoleService) {
        this.backendRoleService = backendRoleService;
    }

    public QxUsers getAdmin() {
        return admin;
    }

    public void setAdmin(QxUsers admin) {
        this.admin = admin;
    }

    public List<QxRoles> getBackendRoles() {
        return backendRoles;
    }

    public void setBackendRoles(List<QxRoles> backendRoles) {
        this.backendRoles = backendRoles;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRad() {
        return rad;
    }

    public void setRad(String rad) {
        this.rad = rad;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * @Title: 递归查询部门 
     * @param id
     * @return 
     * String 
     * @author zhanghj
     * @since 2016-9-7 V 1.0
     */
    private String getDeprId(String id) {
        QxDepartment qxDepartment = adminService.get(QxDepartment.class, Restrictions.eq("id", id));
        if(qxDepartment!=null){
        	QxDepartment parent = adminService.get(QxDepartment.class, Restrictions.eq("id", qxDepartment.getParent()));
            // 说明是部门
            if (parent==null || "ROOT".equals(parent.getParent())) {
                return qxDepartment.getName();
            }
            
            return getDeprId(qxDepartment.getParent());
        }else{
        	return null;
        }
        
    }
    public Map<String, String> getRoleMap() {
        return roleMap;
    }
    public void setRoleMap(Map<String, String> roleMap) {
        this.roleMap = roleMap;
    }
}
