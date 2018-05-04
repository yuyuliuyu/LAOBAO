package com.lingnet.qxgl.action.system;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;



import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.lingnet.common.action.BaseAction;
import com.lingnet.qxgl.entity.QxResource;
import com.lingnet.qxgl.entity.QxRoles;
import com.lingnet.qxgl.security.manage.metadata.BackendSecurityMetadataSource;
import com.lingnet.qxgl.service.BackendResourceService;
import com.lingnet.qxgl.service.BackendRoleService;
import com.lingnet.qxgl.service.RoleResourceService;
import com.lingnet.util.JsonUtil;

@Controller
@ParentPackage("system")
public class BackendRoleAction extends BaseAction {
    private static final long serialVersionUID = -2684708119893418263L;

    @Resource(name = "backendRoleService")
    private BackendRoleService backendRoleService;
    
    @Resource(name = "backendResourceService")
    private BackendResourceService backendResourceService;
    
    @Resource(name = "RoleResourceService")
    private RoleResourceService roleResourceService;
    private QxRoles backendRole;
    private List<QxResource> backendResources;
    
    private String data;//被选中的权限内容
    private String name;
    private String desc;
    private String json;//返回数据
    private String lastname; 
   
    private BackendSecurityMetadataSource backendSecurityMetadataSource;
    
    public BackendSecurityMetadataSource getBackendSecurityMetadataSource() {
        return backendSecurityMetadataSource;
    }
    
    public void setBackendSecurityMetadataSource(
            BackendSecurityMetadataSource backendSecurityMetadataSource) {
        this.backendSecurityMetadataSource = backendSecurityMetadataSource;
    }
    
    /**
     * 角色管理列表页
     */
    public String list() {
        
        return "all";
    }
    
     /** 
     * 跳转到角色管理增加页
     */
    public String function(){
    	backendRole = backendRoleService.get(id); 
        
        return "function";
    }
    
    /**
     * 跳转到角色管理修改页
     */
    public String edit() {
        backendRole = backendRoleService.get(id); 
        
        return "function";
    }
    
    /**
     * 添加
     */
    public String add() {
        return "add";
    }
    
    public String show(){
        backendRole = backendRoleService.get(id); 
        
        return "show";
    }
    
    /**
     * 角色保存或修改方法
     */
    public String saveOrUpdate(){
        try{
            backendRoleService.saveOrUpdate(id,name,desc,data,backendSecurityMetadataSource);
        }catch (Exception e){
        	e.printStackTrace();
            return ajax(Status.success, e.toString().substring(e.toString().indexOf(":")+1));
        }
        return ajax(Status.success, "success");
    }
    /**
     * 获取所有的子节点 
     */
    //整理代码将实现方法放到impl中  栾胜鹏  2014-10-20
    public List<Map<String, String>> dgChile(List<Map<String, String>> listData){
        
        List<Map<String, String>> data = backendRoleService.dgChile(listData);
        
        return data;
    }
    
    /**
     * 获取角色信息
     */
    // 整理代码将实现方法放到impl中  栾胜鹏  2014-10-20
    public String json(){
        String json = backendRoleService.json(key,pager);
        
        return ajax(Status.success, json);
    }
    
    /**
     * 删除
     */
    public String delete(){
        try {
            /*将实现方法放到del中*/
            backendRoleService.del(id);
        } catch (Exception e) {
            return ajax(Status.error,e.toString().substring(e.toString().indexOf(":")+1));
        }
        return ajax(Status.success, "success");
    }
    
    /**
     * 权限树展现跳转页面
     */
    public String resouce(){
        if(id!=null&&!id.equals("null")){
            backendRole = backendRoleService.get(QxRoles.class, Restrictions.eq("id", id));
        }
        return "resouce";
    }
    
    /**
     * 获取权限展现详细内容，树状表示
     */
    // 整理代码将实现方法放到impl中  栾胜鹏  2014-10-20
    public String resource(){
        String json = backendRoleService.resource(id);
        return ajax(Status.success, json);
    }
    
    /**
     * 保存修改权限信息
     */
    // 整理代码将实现方法放到impl中  栾胜鹏  2014-10-20
    public String savedata(){
        String[] key = data.split(",");
        try {
            backendRoleService.savedata(key,id,name,desc);
        } catch (Exception e) {
            return ajax(Status.error, e.toString().substring(e.toString().indexOf(":")+1));
        }
        return ajax(Status.success, "success");
    }
    
    /**
     * 获取角色对应用户名称,分页展现
     */
    @SuppressWarnings("rawtypes")
    public String getuser(){
        HashMap result = backendRoleService.findUserByRoleId(pager,id);
        
        json = JsonUtil.Encode(result);
        
        return ajax(Status.success, json);
    }
    
    /**
     * 保存
     */
    @Validations(requiredStrings = { @RequiredStringValidator(fieldName = "backendRole.name", message = "角色名称不允许为空!") }, requiredFields = { @RequiredFieldValidator(fieldName = "backendResources", message = "角色权限不允许为空!") })
    @InputConfig(resultName = "error")
    public String save() throws Exception {
        backendRole.setQxResources(new HashSet<QxResource>(backendResources));
        
        backendRoleService.save(backendRole);
        redirectUrl = "backend_role!list.action";
        
        return SUCCESS;
    }
    
    /**
     * 更新
     */
    @Validations(requiredStrings = { @RequiredStringValidator(fieldName = "backendRole.name", message = "角色名称不允许为空!") }, requiredFields = { @RequiredFieldValidator(fieldName = "backendResources", message = "角色权限不允许为空!") })
    @InputConfig(resultName = "error")
    public String update() throws Exception {
        QxRoles persistent = backendRoleService.get(QxRoles.class, Restrictions.eq("id", id));
        
        if (persistent.getIsSystem()) {
            addActionError("该角色为系统内置角色，不允许修改！");
            return ERROR;
        }
        persistent.setQxResources(new HashSet<QxResource>(backendResources));
        
        BeanUtils.copyProperties(backendRole, persistent, new String[] { "id",
                "createDate", "backendResources", "modifyDate", "isSystem" });
        
        backendRoleService.update(persistent);
        
        redirectUrl = "backend_role!list.action";
        
        return SUCCESS;
    }
    
    /**
     * 获取所有资源集合
     */
    public List<QxResource> getAllResourceList() {
        return backendResourceService.getAllList();
    }
    
    ///////////////////////////////////////////////////////////////////////
    public BackendRoleService getBackendRoleService() {
        return backendRoleService;
    }

    public void setBackendRoleService(BackendRoleService backendRoleService) {
        this.backendRoleService = backendRoleService;
    }

    public BackendResourceService getBackendResourceService() {
        return backendResourceService;
    }

    public void setBackendResourceService(
            BackendResourceService backendResourceService) {
        this.backendResourceService = backendResourceService;
    }

    public QxRoles getBackendRole() {
        return backendRole;
    }

    public void setBackendRole(QxRoles backendRole) {
        this.backendRole = backendRole;
    }

    public List<QxResource> getBackendResources() {
        return backendResources;
    }

    public void setBackendResources(List<QxResource> backendResources) {
        this.backendResources = backendResources;
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
   
    public String getDesc() {
        return desc;
    }
    
    public void setDesc(String desc) {
        this.desc = desc;
    }
   
    public RoleResourceService getRoleResourceService() {
        return roleResourceService;
    }
   
    public void setRoleResourceService(RoleResourceService roleResourceService) {
        this.roleResourceService = roleResourceService;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
  
}
