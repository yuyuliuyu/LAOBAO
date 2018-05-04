package com.lingnet.qxgl.dao;

import java.util.List;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.qxgl.entity.QxResource;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.util.Pager;

public interface AdminDao extends BaseDao<QxUsers, String> {

	/**
	 * 根据用户名，判断此管理员是否存在（不区分大小写）
	 * 
	 * @param userName
	 * @return
	 */
	public boolean isExistByUsername(String username);

	/**
	 * 根据用户名，获取管理员（不区分大小写）
	 * 
	 * @param userName
	 * @return
	 */
	public QxUsers findUserByName(String username);
	
	public List<QxResource> getResourceByUserName(String userName,int moudleId, String modelArea);
	
	@SuppressWarnings("rawtypes")
    public List getUsersByDepId(Pager pager,String depId,String key,String cid);
	
	public Integer getMenuSize(String userName);

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
    
    public String getRolesByUser(String userid);

}


