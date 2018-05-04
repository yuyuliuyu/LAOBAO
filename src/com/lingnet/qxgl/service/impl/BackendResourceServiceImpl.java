package com.lingnet.qxgl.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.qxgl.dao.BackendResourceDao;
import com.lingnet.qxgl.entity.QxResource;
import com.lingnet.qxgl.entity.QxRoles;
import com.lingnet.qxgl.service.BackendResourceService;
import com.lingnet.qxgl.service.RoleResourceService;
import com.lingnet.util.JsonUtil;

@Service("backendResourceService")
public class BackendResourceServiceImpl extends BaseServiceImpl<QxResource, String> implements BackendResourceService {
    
    @Resource(name = "backendResourceDao")
    private BackendResourceDao backendResourceDao;
    
    @Resource(name = "RoleResourceService")
    private RoleResourceService roleResourceService;
    
    private String jdbcUrl;
    
    @Resource(name = "backendResourceDao")
    public void setBackendResourceDao(BackendResourceDao backendResourceDao) {
        super.setBaseDao(backendResourceDao);
    }
    
    /** 
     * 获取所有权限信息展现
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List<HashMap<String, String>> getAuthList(String id) {
        //获取所有的菜单信息
        List<QxResource> lResource= getList(QxResource.class, Restrictions.eq("type", "MENU"),Restrictions.eq("state", "1"));
        
        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        
        //得到的list数据根据“排列次序号”进行排序（升序）
        Collections.sort(lResource, new Comparator() {
            public int compare(Object o1, Object o2) {
                QxResource obj1 = (QxResource) o1;
                QxResource obj2 = (QxResource) o2;
                
                return (obj1.getSortorder()).compareTo(obj2.getSortorder());
            }
        });
        
        //获取被选中中的权限
        Map maplist = roleResourceService.getResourceByRole(id);
        
        //遍历所有的信息
        for (int i = 0; i < lResource.size(); i++) {
            QxResource resource = lResource.get(i);
            
            HashMap record = new HashMap();
            //判断是否为根
            if(resource.getPresource().equals("MENU_ROOT")){
                record.put("pid", "-1");
            }else{
                record.put("pid", resource.getPresource());
            }
            record.put("name", resource.getDescription());
            record.put("id", resource.getResourcename());
            
            //根据资源名称获取权限
            List<QxResource> list =  this.getOrderList(QxResource.class,Order.asc("sortorder"),Restrictions.eq("type", "ACTION"),Restrictions.eq("state", "1"),Restrictions.eq("presource", resource.getResourcename()));
            ArrayList<HashMap<String, String>> map = new ArrayList<HashMap<String, String>>();
            for(QxResource re:list){
                HashMap has = new HashMap();
                
                has.put("action", re.getResourcename());
                has.put("name", re.getResourcename());
                if(maplist.containsKey(re.getId())){
                    has.put("checked", true);
                }else{
                    has.put("checked", false);
                }
                map.add(has);
            }
            if(map.size()>0){
                record.put("functions", map);
            }
            data.add(record);
        }
        return data;
    }
    
    /** 
     * 递归获取资源的主干资源
     */
    public String getFare(String fare){
        QxResource resource = this.get(Restrictions.eq("resourcename", fare));
        String status = "0";
        if(resource!=null){
             status = resource.getState();
            if(!resource.getPresource().equals("MENU_ROOT")){
                status = getFare(resource.getPresource());
            }
        }
        return status;
    }
    
    /** 
     * 获取所有资源信息
     */
//    @Override
//    public List<QxResource> getQxResourceList() {
//        List<QxResource>  lResource=  backendResourceDao.getAllList();
//        
//        return lResource;
//    }
    
    /** 
     * 获取所有资源信息
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<HashMap<String, String>> getTreeList() {
        //List<QxResource> lResource = backendResourceDao.getList(Restrictions.eq("type", "MENU"));//会报错，注掉改为一下方法 2014-12-11 栾胜鹏
        List<QxResource> lResource = getList(QxResource.class, Restrictions.eq("type", "MENU"));
        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        
        //得到的list数据根据“排列次序号”进行排序（升序）
        Collections.sort(lResource, new Comparator() {
            public int compare(Object o1, Object o2) {
                QxResource obj1 = (QxResource) o1;
                QxResource obj2 = (QxResource) o2;
                
                return (obj1.getSortorder()).compareTo(obj2.getSortorder());
            }
        });
        
        //遍历所有的信息
        for (int i = 0; i < lResource.size(); i++) {
            QxResource resource = lResource.get(i);
            
            HashMap<String,String> record = new HashMap<String,String>();
            
            //判断是否为跟部门
            if(resource.getPresource().equals("MENU_ROOT")){
                record.put("ParentTaskUID", "-1");
            }else{
                record.put("ParentTaskUID", resource.getPresource());
            }
            record.put("UID", resource.getResourcename());
            record.put("Name", resource.getResourcename());
            if(resource.getSortorder()!=null)
                record.put("Sortorder", resource.getSortorder().toString());
            record.put("PercentComplete", resource.getDescription());
            if(resource.getModuleid().toString().equals("0")){
                record.put("ModuleId", "根菜单");
            }else{
                record.put("ModuleId", resource.getModuleid().toString()+"级菜单");
            }
            record.put("Id", resource.getId());
            if(resource.getState().equals("1")){
                record.put("Show","√");
            }else{
                record.put("Show","×");
            }
            SimpleDateFormat mat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            record.put("Createdate", mat.format(resource.getCreateDate()));
            record.put("Url", resource.getResourceurl());
            record.put("modelArea", resource.getModulename());
            
            data.add(record);
        }
        return data;
    }
    
    /** 
     * 获取所有资源信息
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<HashMap<String, String>> getTreeData() {
        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        //获取所有部门内容
        List<QxResource> lResource = backendResourceDao.getList(Restrictions.eq("type", "MENU"));
        
        //得到的list数据根据“排列次序号”进行排序（升序）
        Collections.sort(lResource, new Comparator() {
            public int compare(Object o1, Object o2) {
                QxResource obj1 = (QxResource) o1;
                QxResource obj2 = (QxResource) o2;
                
                return (obj1.getSortorder()).compareTo(obj2.getSortorder());
            }
        });
        
        //遍历部门内容，进行
        for (int i = 0; i < lResource.size(); i++) {
            
            HashMap<String,String> record = new HashMap<String,String>();
            
            QxResource resource = lResource.get(i);
            
            //判断是否为跟部门
            if(resource.getPresource().equals("MENU_ROOT")){
                record.put("pid", "-1");
            }else{
                record.put("pid", resource.getPresource());
            }
            record.put("id", resource.getResourcename());
            record.put("text", resource.getDescription()+"("+resource.getResourcename()+")");
            record.put("Id", resource.getId());
            record.put("modelArea", resource.getModulename());// 所属模块
            
            data.add(record);
        }
        return data;
    }
    
    @Override
    public QxResource getByName(String name){
        QxResource res = backendResourceDao.getByName(name);
        
        return res;
    }
    
    @Override
    public Map<String, Collection<ConfigAttribute>> ListToCollection(){
        String db = "";
        //判断当前链接是mysql还是oracle
        if(jdbcUrl.indexOf("oracle")!=-1){
            db = "oracle";
        }else if(jdbcUrl.indexOf("mysql")!=-1){
            db = "mysql" ;
        }else{
            db = "msdn" ;
        }
        return backendResourceDao.ListToCollection(db);
    }
    
    @Override
    public Collection<ConfigAttribute>  ListToCollection(String url){
        String db = "";
        //判断当前链接是mysql还是oracle
        /*if(jdbcUrl.indexOf("oracle")!=-1){
            db = "oracle";
        }else if(jdbcUrl.indexOf("mysql")!=-1){
            db = "mysql" ;
        }else{
            db = "msdn" ;
        }*/
        return backendResourceDao.ListToCollection(db,url);
    }
    
    /**
     * 删除资源信息
     */
    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String del(String id) {
        String flg = "";
        //循环删除资源信息
        String[] ids = id.split(",");
        //判断资源下是否有角色
        for(int i=0;i<ids.length;i++){
            QxResource resource = this.get(QxResource.class, Restrictions.eq("id", (ids[i])));
            if(resource.getIssystem()!=null&&resource.getIssystem()){
                flg = "该资源为系统内置资源，删除失败！";
                return flg;
            }
            Set<QxRoles> set = resource.getQxRoles();
            if(set.size()>0){
                flg = "该资源存在角色占用，删除失败！";
                return flg;
            }
        }
        
        for(int i=0;i<ids.length;i++){
            QxResource resource = this.get(QxResource.class, Restrictions.eq("id", ids[ids.length-1-i]));
            
            //operate("资源管理", "删除",resource.getResourcename());
            
            List<QxResource> qxList = this.getList(QxResource.class,Restrictions.eq("presource", resource.getResourcename()));
            for(QxResource qx:qxList){
                this.delete(qx);
            }
            this.delete(ids[ids.length-1-i]);
        }
        
        flg = "success";
        
        return flg;
    }
    
    /**
     * 删除权限信息
     */
    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String remove(String id) {
        String flg = "";
        //循环删除权限信息
        String[] ids = id.split(",");
        //判断权限下是否有角色
        for(int i=0;i<ids.length;i++){
            QxResource resource = this.get(QxResource.class,Restrictions.eq("id", ids[i]));
            if(resource.getIssystem()){
                flg = "该权限为系统内置权限，删除失败！";
                return flg;
            }
            Set<QxRoles> set = resource.getQxRoles();
            if(set.size()>0){
                flg = "该权限存在角色占用，删除失败！";
                return flg;
            }
        }
        
        for(int i=0;i<ids.length;i++){
            QxResource resource = this.get(QxResource.class, Restrictions.eq("id", ids[ids.length-1-i]));
            
            //operate("资源管理-权限操作", "删除",resource.getResourcename());
            
            List<QxResource> qxList = this.getList(QxResource.class,Restrictions.eq("presource", resource.getResourcename()));
            for(QxResource qx:qxList){
                this.delete(qx);
            }
            this.delete(ids[ids.length-1-i]);
        }
        
        flg = "success";
        
        return flg;
    }
    
    /**
     * 保存更新数据
     */
    @SuppressWarnings("rawtypes")
    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String saveOrUpdate(Map map, String[] uname,String[] action) throws Exception {
        String id = (String) map.get("id");
        String name = (String) map.get("name");
        String url = (String) map.get("url");
        String description =  (String) map.get("description");
        String show = (String) map.get("show");
        String imgvalue = (String) map.get("imgvalue");
        String sortorder = (String) map.get("sortorder");
        String parent = (String) map.get("parent");
        String menu = (String) map.get("menu");
        String onauth = (String) map.get("onauth");
        String child = (String) map.get("child");
        String modulename = (String) map.get("modulename");
        
        if(id!=null && !id.equals("") && !id.equals("null")){
            //更新操作
            QxResource resource = getByName(name);
            
            if(resource!=null){
                if(!resource.getId().equals(id)){
                    throw new Exception("该资源名称已经存在，请重新录入！");
                }
            }
            QxResource qxs = this.get(QxResource.class, Restrictions.eq("resourceurl", url));
            if(qxs!=null&&!id.equals(qxs.getId())){
                throw new Exception("“"+qxs.getResourcename()+"”已经录入该资源路径，请勿重复录入！");
            }
            resource = this.get(QxResource.class, Restrictions.eq("id", id));
            
            resource.setDescription(description);
            resource.setResourcename(name);
            resource.setModulename(modulename);
            resource.setState(show);
            resource.setResourceurl(url);
            resource.setImgvalue(imgvalue);
            resource.setIssystem(false);
            resource.setSortorder(Integer.parseInt(sortorder));
            resource.setType("MENU");
            Date now = new Date(System.currentTimeMillis());
            resource.setModifyDate(now);
            
            //判断是否是父级
            if(parent!=null&&!parent.equals("null")&&!parent.equals("")){
                resource.setPresource(parent);
            }else{
                resource.setPresource("MENU_ROOT");
            }
            resource.setModuleid(menu);
            //更新修改的数据
            this.update(resource);
            
            //存在子节点更新子节点信息
            String[] allid = child.split(",");
            for(int i=0;i<allid.length;i++){
                if(!allid[i].equals("null")&&!allid[i].equals("")){
                    resource = this.get(QxResource.class, Restrictions.eq("id", allid[i]));
                    resource.setPresource(name);
                    this.update(resource);
                }
            }
            //operate("资源管理", "编辑",resource.getResourcename());
        }else{
            //插入操作
            QxResource resource =  this.getByName(name);
            
            if(resource!=null){
                throw new Exception("该资源名称已经存在，请重新录入！");
            }
            resource = new QxResource();
            
            resource.setDescription(description);
            resource.setResourcename(name);
            resource.setModulename(modulename);
            resource.setIssystem(false);
            resource.setSortorder(Integer.parseInt(sortorder));
            Date now = new Date(System.currentTimeMillis());
            resource.setCreateDate(now);
            if(url.equals("")||url.equals("null")){
                resource.setResourceurl("404");
            }else{
                resource.setResourceurl(url);
            }
            QxResource qxs = this.get(QxResource.class, Restrictions.eq("resourceurl", resource.getResourceurl()));
            if(qxs!=null){
                throw new Exception("“"+qxs.getResourcename()+"”已经录入该资源路径，请勿重复录入！");
            }
            resource.setImgvalue(imgvalue);
            
            //判断是否是父级
            if(parent!=null&&!parent.equals("null")&&!parent.equals("")){
                resource.setPresource(parent);
            }else{
                resource.setPresource("MENU_ROOT");
            }
            resource.setState(show);
            resource.setModuleid(menu);
            resource.setType("MENU");
            
            //保存新入数据
            this.save(resource);
            //增加权限
            if(onauth.equals("1")){
                for(int i=0;i<uname.length;i++){
                    if(url.indexOf("/")!=-1&&url.indexOf("!")!=-1){
                        resource = new QxResource(); 
                        
                        resource.setIssystem(false);
                        resource.setResourcename(uname[i]);
                        resource.setResourceurl(url.substring(url.indexOf("/"),url.indexOf("!")+1)+action[i]);
                        
                        qxs = this.get(QxResource.class, Restrictions.eq("resourceurl", resource.getResourceurl()));
                        if(qxs!=null){
                            throw new Exception("“"+qxs.getResourcename()+"”已经录入该资源路径，请勿重复录入！");
                        }
                        
                        resource.setType("ACTION");
                        resource.setPresource(name);
                        resource.setDescription(uname[i]);
                        resource.setState("1");
                        resource.setSortorder(i);
                        resource.setIssystem(false);
                        
                        this.save(resource);
                    }
                }
            }
            //operate("资源管理", "增加",resource.getResourcename());
        }
        return "success";
    }

    /**
     * 权限增加
     */
    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String authadd(String data, String parent)throws Exception {
        QxResource res = JsonUtil.toObject(data,QxResource.class);
        
        if(res.getId()!=null && !res.getId().equals("")){
            QxResource qxs = this.get(QxResource.class, Restrictions.eq("resourceurl", res.getResourceurl()));
            if(qxs!=null && !res.getId().equals(qxs.getId())){
                throw new Exception("该权限路径已经存在，请重新录入！");
            }
            if(StringUtils.isNotEmpty(parent)&&parent.equals(res.getResourcename())){
            	throw new Exception("权限名称不能与父级资源同名，请重新录入！");
            }
            if(qxs==null){
                qxs = new QxResource();
            }
            qxs.setId(res.getId());
            qxs.setIssystem(false);
            qxs.setType("ACTION");
            qxs.setPresource(parent);
            qxs.setDescription(res.getDescription());
            qxs.setResourcename(res.getResourcename());
            qxs.setResourceurl(res.getResourceurl());
            qxs.setState(res.getState());
            qxs.setSortorder(res.getSortorder());
            
            this.update(qxs);
            
            //operate("资源管理-权限操作", "编辑",res.getResourcename());
        }else{
            QxResource qxs = this.get(QxResource.class, Restrictions.eq("resourceurl", res.getResourceurl()));
            if(qxs!=null){
                throw new Exception("该权限路径已经存在，请重新录入！");
            }
            if(StringUtils.isNotEmpty(parent)&&parent.equals(res.getResourcename())){
            	throw new Exception("权限名称不能与父级资源同名，请重新录入！");
            }
            res.setIssystem(false);
            res.setType("ACTION");
            res.setPresource(parent);
            
            this.save(res);
            
            //operate("资源管理-权限操作", "增加",res.getResourcename());
        }
        return "success";
    }
    
    /*以上是代码整理：将放在action中的实现方法放到impl中  栾胜鹏*/
    
    @Override
    public QxResource getById(String id){
        QxResource res = backendResourceDao.getById(id);
        
        return res;
    }

    @Transactional
    public List<QxResource> getResourceByUserName(String userName) {
        return null;
    }
    
    @Transactional
    public List<QxResource> getResourceByRoleId(String roleId) {
        return null;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

}
