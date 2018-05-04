package com.lingnet.qxgl.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;

import com.lingnet.common.service.BaseService;
import com.lingnet.qxgl.entity.QxResource;

public interface BackendResourceService extends BaseService<QxResource, String>{


	/**
	 * 根据用户名获取该用户所有资源属性
	 * 
	 * @param userName
	 * @return
	 */
	public List<QxResource> getResourceByUserName(String userName);
	

   // public List<QxResource> getResourceByRoleId(String roleId,String type);
    
    
    public List<HashMap<String, String>> getTreeList();
    
    public List<HashMap<String, String>> getTreeData();
    
    public QxResource getByName(String name);
    
//    public List<QxResource> getQxResourceList();
    
    public QxResource getById(String id);
    
    public List<HashMap<String, String>> getAuthList(String id);
    
    public Map<String, Collection<ConfigAttribute>> ListToCollection();
    public Collection<ConfigAttribute>  ListToCollection(String url);
    
    /** 
     * 删除资源信息
     * @Title: del 
     * @param id
     * @return 
     * String 
     * @author 栾胜鹏 代码整理实现方法放到impl中 
     * @since 2014-10-22 V 1.0
     */
    public String del(String id);
    
    /** 
     * 删除权限信息
     * @Title: remove 
     * @param id
     * @return 
     * String 
     * @author 张丽丽
     * @since 2014-11-12 V 1.0
     */
    public String remove(String id);
    
    /** 
     * @Title: 保存更新数据
     * @param map
     * @param uname 
     * void 
     * @author 栾胜鹏 代码整理实现方法放到impl中 
     * @param action 
     * @since 2014-10-20 V 1.0
     */
    @SuppressWarnings("rawtypes")
    public String saveOrUpdate(Map map, String[] uname, String[] action)throws Exception;
    
    /** 
     * @Title: 权限增加
     * @param res
     * @param parent 
     * void 
     * @author 栾胜鹏  代码整理实现方法放到impl中 
     * @param id 
     * @since 2014-10-20 V 1.0
     */
    public String authadd(String data, String parent)throws Exception;
}
