package com.lingnet.qxgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lingnet.common.service.BaseService;
import com.lingnet.qxgl.entity.QxRoles;
import com.lingnet.qxgl.security.manage.metadata.BackendSecurityMetadataSource;
import com.lingnet.util.Pager;

public interface BackendRoleService extends BaseService<QxRoles, String>{
    
    public QxRoles findRoleByName(String name);
    
    public List<QxRoles> findRole(String name,int start,int end);
    
    @SuppressWarnings("rawtypes")
    public HashMap findUserByRoleId(Pager pager,String roleId);
    
    public List<QxRoles>  findRolseByResource(String resourceUrl);

    /** 
     * @Title: saveOrUpdate 
     * @param id
     * @param name
     * @param desc
     * @param data 
     * void 
     * @author 栾胜鹏
     * @since 2014-10-20 V 1.0
     */
    public void saveOrUpdate(String id, String name, String desc, String data,BackendSecurityMetadataSource backendSecurityMetadataSource)throws Exception;

    /** 
     * @Title: 获取所有的子节点 
     * @param listData
     * @return 
     * List<Map<String,String>> 
     * @author 栾胜鹏 代码整理实现方法放到impl中 
     * @since 2014-10-20 V 1.0
     */
    public List<Map<String, String>> dgChile(List<Map<String, String>> listData);

    /** 
     * @Title: 根据用户 查出所有的用户id。
     * @param username
     * @param pager
     * @return 
     * String 
     * @author 栾胜鹏  代码整理实现方法放到impl中 
     * @since 2014-10-20 V 1.0
     */
    public String treeShow(String username, Pager pager);

    /** 
     * @Title: 获取角色信息 
     * @param key
     * @param pager
     * @return 
     * String 
     * @author 栾胜鹏 代码整理实现方法放到impl中 
     * @since 2014-10-20 V 1.0
     */
    public String json(String key, Pager pager);

    /** 
     * @Title: 获取权限展现详细内容，树状表示
     * @param id
     * @return 
     * String 
     * @author 栾胜鹏 代码整理实现方法放到impl中 
     * @since 2014-10-20 V 1.0
     */
    public String resource(String id);

    /** 
     * @Title: 保存修改权限信息
     * @param key
     * @param id 
     * @param desc 
     * @param name 
     * void 
     * @author 栾胜鹏 代码整理实现方法放到impl中 
     * @since 2014-10-20 V 1.0
     */
    public void savedata(String[] key, String id, String name, String desc)throws Exception;
    
    /** 
     * 删除
     * @Title: del 
     * @param id 
     * void 
     * @author 栾胜鹏 代码整理实现方法放到impl中 
     * @since 2014-10-22 V 1.0
     */
    public void del(String id)throws Exception;

}
