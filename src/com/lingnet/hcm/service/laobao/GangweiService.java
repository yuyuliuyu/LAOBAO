package com.lingnet.hcm.service.laobao;

import java.util.HashMap;

import com.lingnet.common.service.BaseService;
import com.lingnet.hcm.entity.laobao.StaffInfo;
import com.lingnet.qxgl.entity.Branch;

import com.lingnet.qxgl.security.manage.metadata.BackendSecurityMetadataSource;
import com.lingnet.util.Pager;

public interface GangweiService extends BaseService<Branch, String> {

    /**
     * 获取当前用户有数据权限的部门树 数据
     * @Title: getTreeListByUser 
     * @return 
     * String 
     * @author xuhp
     * @since 2017-3-16 V 1.0
     */
    public String getTreeListByUser();
    
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
    
    @SuppressWarnings("rawtypes")
    public HashMap findUserByRoleId(Pager pager,String roleId);
    
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

	public StaffInfo getStaffByJobNumber(String job_number);

	public Object getPersonByDepId(Pager pager, String searchData);
}
