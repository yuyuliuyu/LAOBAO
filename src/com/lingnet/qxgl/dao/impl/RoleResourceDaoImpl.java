/**
 * @RoleResourceDaoImpl.java
 * @com.lingnet.qxgl.dao.impl
 * @Description：
 * 
 * @author zhmf 
 * @copyright  2013
 * @version V
 * @since 2013-6-9
 */
package com.lingnet.qxgl.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.qxgl.dao.RoleResourceDao;
import com.lingnet.qxgl.entity.QxRoleResource;
import com.lingnet.qxgl.entity.QxRoleResourceId;

/** 
 * @ClassName: RoleResourceDaoImpl 
 * @Description: 
 * @author 张明峰
 * @date 2013-6-9 下午3:33:20 
 *  
 */
@Repository("RoleResourceDao")
public class RoleResourceDaoImpl extends BaseDaoImplInit<QxRoleResource, String> implements RoleResourceDao{

    /* (non-Javadoc)
     * 根据角色获取用户信息
     */
    @SuppressWarnings("rawtypes")
    @Override
    public List getResourceByRole(String roleId) {
        
        String sql = "select * from QX_ROLE_RESOURCE where ROLE_ID = ?";
        
        
        
        return getJdbcTemplate().query(sql, new Object[] {roleId}, new RowMapper<QxRoleResourceId>() {
            public QxRoleResourceId mapRow(ResultSet resultSet,
                    int rowNum) throws SQLException {
                QxRoleResourceId resource = new QxRoleResourceId();
                resource.setRoleId(resultSet.getString(1));
                resource.setResourceId(resultSet.getString(2));
 
                return resource;
            }
        });
    }

    /* (non-Javadoc)
     * @see com.lingnet.qxgl.dao.RoleResourceDao#saveId(com.lingnet.qxgl.entity.QxRoleResourceId)
     */
    @Override
    public int saveId(QxRoleResourceId qxRoleResourceId) {
        String sql = "insert into QX_ROLE_RESOURCE (ROLE_ID,RESOURCE_ID) values(?,?)";
        
        int types[] = new int[]{Types.VARCHAR,Types.VARCHAR}; 
        
        int m = getJdbcTemplate().update(sql, new Object[] {qxRoleResourceId.getRoleId(),qxRoleResourceId.getResourceId()},types);
        return m;
    }

    /* (non-Javadoc)
     * @see com.lingnet.qxgl.dao.RoleResourceDao#deleId(com.lingnet.qxgl.entity.QxRoleResourceId)
     */
    @Override
    public int deleId(QxRoleResourceId qxRoleResourceId) {
        String sql = "delete from QX_ROLE_RESOURCE where ROLE_ID = ? and RESOURCE_ID = ?";
        
        int types[] = new int[]{Types.VARCHAR,Types.VARCHAR}; 
        
        int m = getJdbcTemplate().update(sql, new Object[] {qxRoleResourceId.getRoleId(),qxRoleResourceId.getResourceId()},types);
        
        
        return m;
    }

    
       
    
}
