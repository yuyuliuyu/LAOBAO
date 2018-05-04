package com.lingnet.qxgl.action.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.qxgl.entity.QxResource;
import com.lingnet.qxgl.service.BackendResourceService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.ReflectionUtil;

/** 
 * @ClassName: BackendResAction 
 * @Description: 资源操作类 
 * @author 张明峰
 * @date 2013-6-20 上午8:55:33 
 *  
 */
@ParentPackage("resource")
public class BackendResAction extends BaseAction {
    
    private static final long serialVersionUID = -5303744700972601552L;
    
    @Resource(name="backendResourceService")
    private BackendResourceService resourceService;
    
    String[] uname = {"展现","增加","修改","删除","查看","保存","审核"};
    String[] action = {"list.action","add.action","edit.action","delete.action","look.action","saveOrUpdate.action","check.action"};
    
    private QxResource resource;
    private String name ;
    private String description ;
    private String modulename ;// 模块名称
    private String parent;//父级资源
    private String menu ;//菜单层级
    private String show ;//显示
    private String url;//连接地址
    private String imgvalue;//图标
    private String sortorder;//排列次序
    private String child;//子节点
    private String json;
    private String data;
    private String onauth;//是否添加权限
    
    /**
     * 跳转到资源显示页面
     */
    public String list(){
        
        return LIST;
    }
    
    /** 
     * 返回资源结构树信息
     */
    public String getdata(){
  
        List<HashMap<String, String>> data = resourceService.getTreeList();
        
        json = JsonUtil.Encode(data);
        
        return ajax(Status.success,json);
    }
    
    /** 
     * 返回资源结构树信息
     */
    public String treedata(){
        
        List<HashMap<String, String>> data = resourceService.getTreeData();
        
        json = JsonUtil.Encode(data);
        
        return ajax(Status.success,json);
    }
    
    /** 
     * 跳转到资源树页面
     */
    public String tree(){
        
        return "tree";
    }
    
    /** 
     * 跳转到资源添加页面
     */
    public String add(){
        
        return "add";
    }
    
    /** 
     * 跳转到资源修改页面
     */
    public String edit(){
        resource = resourceService.get(QxResource.class, Restrictions.eq("id", id));
        
        return "add";
    }
    
    /** 
     * 删除资源信息
     * 整理代码将实现方法放到impl中  栾胜鹏  2014-10-22
     */
    public String delete(){
        
        /*将实现代码放到del中*/
        String flg = resourceService.del(id);
        
        if(flg.equals("success")){
            return ajax(Status.success,"success");
        }else{
            return ajax(Status.error,flg); 
        }
    }
    
    /** 
     * 删除权限信息
     * @Title: delete 
     * @return 
     * String 
     * @author 张丽丽
     * @since 2014-11-12 V 1.0
     */
    public String remove(){
        String flg = resourceService.remove(id);
        
        if(flg.equals("success")){
            return ajax(Status.success,"success");
        }else{
            return ajax(Status.error,flg); 
        }
    }
    
    
    /** 
     * 保存更新数据
     * 整理代码将实现方法放到impl中  栾胜鹏  2014-10-20
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String updata(){
        //存放传递的数据
        Map map = new HashMap();
        
        map.put("id",id);
        map.put("name",name);
        map.put("url", url);
        map.put("description", description);
        map.put("show", show);
        map.put("imgvalue",imgvalue);
        map.put("sortorder", sortorder);
        map.put("parent", parent);
        map.put("menu", menu);
        map.put("onauth",onauth);
        map.put("child",child);
        map.put("modulename",modulename);
        
        String flg = "";
        try {
            flg = resourceService.saveOrUpdate(map,uname,action);
        } catch (Exception e) {
            return ajax(Status.error,e.toString().substring(e.toString().indexOf(":")+1));
        }
        if(flg.equals("success")){
            return ajax(Status.success,"success");
        }else{
            return ajax(Status.error,flg);
        }
    }
    
    /**
     * 权限增加
     * 整理代码将实现方法放到impl中  栾胜鹏  2014-10-20
     */
    public String authadd(){
        String flg = "";
        try {
            flg = resourceService.authadd(data,parent);
        } catch (Exception e) {
            return ajax(Status.error,e.toString().substring(e.toString().indexOf(":")+1));
        }
        if(flg.equals("success")){
            return ajax(Status.success,"success");
        }else{
            return ajax(Status.error,flg);
        }
    }
    
    /**
     * 权限展现
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public String getauth() throws Exception{
        
        
		List list = this.getOrderBeanListByCriteria(QxResource.class, Order.asc("sortorder"),eq("type", "ACTION"),eq("presource",parent));
        
        String json = JsonUtil.Encode(ReflectionUtil.getMapList(list));
        
        return ajax(Status.success, json);
    }
    
    public String getaudetail() throws Exception{
        List<HashMap<String, String>> data = resourceService.getAuthList(id);
        
        json = JsonUtil.Encode(data);
        
        return ajax(Status.success,json);
    }
    
    /** 
     * 跳转到权限操作页面
     */
    public String auth(){
       
        return "auth";
    }
    
    /** 
     * 跳转到权限页面
     */
    public String view(){
        resource = resourceService.getById(id);
        
        json = JsonUtil.Encode(resource);
        
        return ajax(Status.success,json);
    }
    
    ///////////////////////////////////////////////////////
    public BackendResourceService getResourceService() {
        return resourceService;
    }

    public void setResourceService(BackendResourceService resourceService) {
        this.resourceService = resourceService;
    }
    
    public QxResource getResource() {
        return resource;
    }

    public void setResource(QxResource resource) {
        this.resource = resource;
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

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getImgvalue() {
        return imgvalue;
    }

    public void setImgvalue(String imgvalue) {
        this.imgvalue = imgvalue;
    }

    public String getSortorder() {
        return sortorder;
    }

    public void setSortorder(String sortorder) {
        this.sortorder = sortorder;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOnauth() {
        return onauth;
    }

    public void setOnauth(String onauth) {
        this.onauth = onauth;
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename;
    }
    
}
