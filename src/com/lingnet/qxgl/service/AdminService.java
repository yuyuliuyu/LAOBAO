package com.lingnet.qxgl.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.lingnet.common.service.BaseService;
import com.lingnet.qxgl.entity.QxResource;
import com.lingnet.qxgl.entity.QxRoles;
import com.lingnet.qxgl.entity.QxUser;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

public interface AdminService extends BaseService<QxUsers, String> {

	/**
	 * 根据用户名判断此管理员是否存在（不区分大小写）
	 * 
	 */
	public boolean isExistByUsername(String username);

    public String saveuser(QxUser admin, HashSet<QxRoles> rolelist );
	@SuppressWarnings("rawtypes")
    public HashMap getUsersByDepId (Pager pager,String depId, String key, String cid)throws ParseException;

	public QxUsers getUserByName(String name);
    
    public int getMenuSize(String name);
    
    public  List<QxResource> getResourceByUserName(String name,int leve, String modelArea);

    /** 
     * @Title: 保存用户的变换后的部门
     * @param ids
     * @param data 
     * void 
     * @author 栾胜鹏 代码整理实现方法放到impl中 
     * @param flag 转移部门时是否同时存在两个部门的标志 0 不是 1是
     * @since 2014-10-20 V 1.0
     */
    public String changedep(String[] ids, String data, String flag,String depId)throws Exception;

    /** 
     * @Title: 保存新用户
     * @param sut
     * @param data 
     * String
     * @author 栾胜鹏 代码整理实现方法放到impl中 
     * @param value  
     * @since 2014-10-20 V 1.0
     */
    public String savedata(JsonUtil sut, String data, String value,String rad,String depIds)throws Exception;
    
    /** 
     * @Title: saveTwo 
     * @param data 
     * void 
     * @author 栾胜鹏 代码整理实现方法放到impl中 
     * @since 2014-10-22 V 1.0
     */
    public String saveTwo(String data)throws Exception;
    /**
     * 删除
     * @Title: remove 
     * @param ids
     * @return
     * @throws Exception 
     * String 
     * @author lsp		
     * @since 2016-8-8 V 1.0
     */
    public String remove(String ids)throws Exception;
    /**
     * 获取科室
     * @Title: getks 
     * @param id
     * @return 
     * String 
     * @author lsp		
     * @since 2016-8-8 V 1.0
     */
    public String getks(String id,String key);

    /**
     * @Title: 根据用户权限展现不同的左侧菜单 
     * @param name
     * @param leve
     * @return 
     * String 
     * @author zhanghj
     * @since 2017年3月9日 V 1.0
     */
    public String getAuthManageMenu(String name, int leve);
    /**
     * 用户管理-展示权限
     * @Title: saveDatauth 
     * @param userId
     * @param depIds
     * @param flgs
     * @return 
     * String 
     * @author xuhp
     * @since 2017-3-16 V 1.0
     */
    public String saveDatauth(String userId,String depIds,String flgs);
    /**
     * 用户管理-数据权限
     * @Title: saveDatauth 
     * @param userId
     * @param depIds
     * @param flgs
     * @return 
     * String 
     * @author xuhp
     * @since 2017-3-16 V 1.0
     */
    public String saveshowauth(String userId,String depIds,String flgs);
    /**
     * 生成超级管理员权限
     * @Title:  
     * @param userId
     * @param depIds
     * @param flgs
     * @return 
     * String 
     * @author xuhp
     * @since 2017-3-16 V 1.0
     */
    public String superauth();

    /**
     * @Title: 获取用户列表
     * @param pager
     * @param key
     * @return 
     * Map<String,Object> 
     * @author zhanghj
     * @since 2017年9月19日 V 1.0
     */
    public Map<String, Object> getUserList(Pager pager,String depId,String key,String cid);
}
