package com.lingnet.qxgl.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImplInit;
import com.lingnet.qxgl.dao.BackendResourceDao;
import com.lingnet.qxgl.entity.QxResource;
import com.lingnet.qxgl.entity.QxRoles;
import com.lingnet.qxgl.service.BackendRoleService;

@Repository("backendResourceDao")
public class BackendResourceDaoImpl extends BaseDaoImplInit<QxResource, String> implements
		BackendResourceDao {
	
    @Resource(name="backendRoleService")
    private BackendRoleService backendRoleService;
    
    @Override
    public QxResource getByName(String name){
        
        String Hql = "from QxResource as res where res.resourcename = ?"; 
        
        return uniqueResult(Hql, name);
    }
    
    @Override
    public QxResource getById(String id){
        
        String sql = "select Qx_Resource.id,Qx_Resource.RESOURCENAME,Qx_Resource.RESOURCEURL,Qx_Resource.TYPE,Qx_Resource.DESCRIPTION,Qx_Resource.STATE,Qx_Resource.SORTORDER from Qx_Resource where Qx_Resource.id = '"+id+"'"; 
        
        return  getJdbcTemplate().queryForObject(sql,new RowMapper<QxResource>() {
            public QxResource mapRow(ResultSet resultSet,
                    int rowNum) throws SQLException {
                QxResource resource = new QxResource();
                resource.setId(resultSet.getString(1));
                resource.setResourcename(resultSet.getString(2));
                resource.setResourceurl(resultSet.getString(3));
                resource.setType(resultSet.getString(4));
                resource.setDescription(resultSet.getString(5));
                resource.setState(resultSet.getString(6));
                resource.setSortorder(Integer.parseInt(resultSet.getString(7)));
                return resource;
            }
        });
        
    }
    @Override
    public List<QxResource> getByRoleType(String roleId,String type){
        String hql = "from QxResource as res where res.type='"+type+"' and res.id in(select resourceId from QxRoleResourceId as r where r.roleId='"+roleId+"')";
        return find(hql);
    }
    
    @SuppressWarnings({ "rawtypes", "unused" })
    @Override
    public Map<String, Collection<ConfigAttribute>> ListToCollection(String db){
        
        Map<String, Collection<ConfigAttribute>> backendResourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        
        Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
        
        int i =0 ;
        
        StringBuffer buffer = new StringBuffer();
        
        List list = new ArrayList();
        if(db.equals("oracle")){
        	 i =1 ;
                buffer.append(" select                                           ");
                buffer.append("         resourceurl                              ");
                
                List<QxRoles> roles= backendRoleService.getAllList(QxRoles.class);
                for(QxRoles role:roles){
                    buffer.append(" ,max(decode(t,"+i+",name)) name"+i+" ");
                    i++;
                }  
                
                buffer.append("     from                                         ");
                buffer.append("         (select                                  ");
                buffer.append("             resourceurl,                         ");
                buffer.append("             name,                                ");
                buffer.append("             row_number() over(partition          ");
                buffer.append("         by                                       ");
                buffer.append("             resourceurl                          ");
                buffer.append("         order by                                 ");
                buffer.append("             roleid) t                            ");
                buffer.append("         from                                     ");
                buffer.append("             (select                              ");
                buffer.append("                 res.resourceurl,                 ");
                buffer.append("                 role.name,                       ");
                buffer.append("                 role.id roleid                   ");
                buffer.append("             from                                 ");
                buffer.append("                 qx_role_resource rore            ");
                buffer.append("             join                                 ");
                buffer.append("                 qx_resource res                  ");
                buffer.append("                     on res.id = rore.resource_id ");
                buffer.append("             join                                 ");
                buffer.append("                 qx_roles role                    ");
                buffer.append("                     on rore.role_id=role.id)) qx ");
                buffer.append("         group by                                 ");
                buffer.append("             resourceurl                          ");
                
                list = findBySql(buffer.toString());
                
                for(int j=0;j<list.size();j++){
                    Object[] obj = (Object[]) list.get(j);
                    String url= obj[0].toString();
                    
                    configAttributes = new ArrayList<ConfigAttribute>();
                    for(int k=0;k<i-1;k++){
                        if(obj[k+1]!=null&&!"".equals(obj[k+1].toString())){
                            configAttributes.add(new SecurityConfig(obj[k+1].toString())); 
                            
                            /*if(url.equals("/sell/create_combo!list.action")){
                            	System.out.println(obj[k].toString()+"+++==========");
                            }*/
                           
                        }
                    }
                    if(configAttributes.size()<=0)
                        configAttributes.add(new SecurityConfig("{}"));
                    backendResourceMap.put(url, configAttributes);
                }
        
        }else{
            List<QxRoles> roles= backendRoleService.getAllList(QxRoles.class);
            buffer.append(" select  d.resourceurl "); 
            for(QxRoles role:roles){
                buffer.append(" ,max(name"+i+") as name"+i+" ");
                i++;
            } 
            buffer.append(" from ( "); 
            buffer.append(" select  q.resourceurl "); 
           /* buffer.append("         case rank WHEN 0 THEN NAME  END  name0,                                                       "); 
            buffer.append("                 case rank WHEN 1 THEN NAME  END  name1,                                                       "); 
            buffer.append("         case rank WHEN 2 THEN NAME  END  name2,                                                       "); 
            buffer.append("         case rank WHEN 3 THEN NAME  END  name3,                                                       "); 
            buffer.append("                 case rank WHEN 4 THEN NAME  END  name4,                                                       "); 
            buffer.append("                 case rank WHEN 5 THEN NAME  END  name5,                                                       "); 
            buffer.append("                 case rank WHEN 6 THEN NAME  END  name6                                                        "); */
            i =1;
            for(QxRoles role:roles){
                buffer.append(" ,case rank WHEN "+i+" THEN NAME  END as name"+(i-1)+" ");
                i++;
            } 
            buffer.append("           from (select                                                                                "); 
            buffer.append("                       resourceurl,                                                                    "); 
            buffer.append("                       name,                                                                           "); 
            buffer.append("                       rank                                                                            "); 
            buffer.append("                 from (                                                                               "); 
            buffer.append("                        select                                                                      "); 
            buffer.append("                               heyf_tmp.resourceurl,                                                 "); 
            buffer.append("                               heyf_tmp.name,                                                        "); 
            buffer.append("                               @rownum:=@rownum+1 ,                                                  "); 
            buffer.append("                               if(@resourceurl=heyf_tmp.resourceurl,@rank:=@rank+1,@rank:=1) as rank,"); 
            buffer.append("                               @resourceurl:=heyf_tmp.resourceurl                                    "); 
            buffer.append("                        from (                                                                    "); 
            buffer.append("                               select                                                              "); 
            buffer.append("                                      res.resourceurl,                                              "); 
            buffer.append("                                     role.name,                                                    "); 
            buffer.append("                                     role.id roleid                                                "); 
            buffer.append("                               from                                                                "); 
            buffer.append("                                     qx_role_resource rore                                         "); 
            buffer.append("                               join                                                                "); 
            buffer.append("                                     qx_resource res                                               "); 
            buffer.append("                               on res.id = rore.resource_id                                  "); 
            buffer.append("                               join                                                        "); 
            buffer.append("                                     qx_roles role                                         "); 
            buffer.append("                               on rore.role_id=role.id order by res.resourceurl asc ) heyf_tmp ,"); 
            buffer.append("                                    (select @rownum:=0 , @resourceurl:= null ,@rank:=0) a "); 
            buffer.append("                              )  "); 
            buffer.append("                        result) q"); 
            buffer.append(" ) d GROUP BY d.resourceurl"); 
            
            list = getJdbcTemplate().queryForList(buffer.toString());
            
            Iterator it = list.iterator();
            while(it.hasNext()){
               Map obj = (Map) it.next();  
               String url= obj.get("resourceurl").toString();
               configAttributes = new ArrayList<ConfigAttribute>();
               for(int k=0;k<i;k++){
                   if(obj.get("name"+(k))!=null&&!"".equals(obj.get("name"+(k)).toString())){
                       configAttributes.add(new SecurityConfig(obj.get("name"+(k)).toString())); 
                   }
               }
               if(configAttributes.size()<=0)
                   configAttributes.add(new SecurityConfig("{}"));
               backendResourceMap.put(url, configAttributes);
            }
        }

        
        
        return backendResourceMap;
    }
    
    @SuppressWarnings({ "rawtypes", "unused" })
    @Override
    public Collection<ConfigAttribute> ListToCollection(String db,String url1){

        
        Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
        
        int i =0 ;
        
        StringBuffer buffer = new StringBuffer();
        
        List list = new ArrayList();
        
            List<QxRoles> roles= backendRoleService.getAllList(QxRoles.class);
            buffer.append(" select  d.resourceurl "); 
            for(QxRoles role:roles){
                buffer.append(" ,max(name"+i+") as name"+i+" ");
                i++;
            } 
            buffer.append(" from ( "); 
            buffer.append(" select  q.resourceurl "); 
            i =1;
            for(QxRoles role:roles){
                buffer.append(" ,case rank WHEN "+i+" THEN NAME  END as name"+(i-1)+" ");
                i++;
            } 
            buffer.append("           from (select                                                                                "); 
            buffer.append("                       resourceurl,                                                                    "); 
            buffer.append("                       name,                                                                           "); 
            buffer.append("                       rank                                                                            "); 
            buffer.append("                 from (                                                                               "); 
            buffer.append("                        select                                                                      "); 
            buffer.append("                               heyf_tmp.resourceurl,                                                 "); 
            buffer.append("                               heyf_tmp.name,                                                        "); 
            buffer.append("                               @rownum:=@rownum+1 ,                                                  "); 
            buffer.append("                               if(@resourceurl=heyf_tmp.resourceurl,@rank:=@rank+1,@rank:=1) as rank,"); 
            buffer.append("                               @resourceurl:=heyf_tmp.resourceurl                                    "); 
            buffer.append("                        from (                                                                    "); 
            buffer.append("                               select                                                              "); 
            buffer.append("                                      res.resourceurl,                                              "); 
            buffer.append("                                     role.name,                                                    "); 
            buffer.append("                                     role.id roleid                                                "); 
            buffer.append("                               from                                                                "); 
            buffer.append("                                     qx_role_resource rore                                         "); 
            buffer.append("                               join                                                                "); 
            buffer.append("                                     qx_resource res                                               "); 
            buffer.append("                               on res.id = rore.resource_id                                  "); 
            buffer.append("                               join                                                        "); 
            buffer.append("                                     qx_roles role                                         "); 
            buffer.append("                               on rore.role_id=role.id where res.resourceurl='"+url1+"'" );
            buffer.append("                               order by res.resourceurl asc ) heyf_tmp ,"); 
            		
            buffer.append("                                    (select @rownum:=0 , @resourceurl:= null ,@rank:=0) a "); 
            buffer.append("                              )  "); 
            buffer.append("                        result) q"); 
            buffer.append(" ) d GROUP BY d.resourceurl"); 
            
            list = getJdbcTemplate().queryForList(buffer.toString());
            
            Iterator it = list.iterator();
            while(it.hasNext()){
               Map obj = (Map) it.next();  
               //String url= obj.get("resourceurl").toString();
              // configAttributes = new ArrayList<ConfigAttribute>();
               for(int k=0;k<i;k++){
                   if(obj.get("name"+(k))!=null&&!"".equals(obj.get("name"+(k)).toString())){
                       configAttributes.add(new SecurityConfig(obj.get("name"+(k)).toString())); 
                   }
               }
               if(configAttributes.size()<=0)
                   configAttributes.add(new SecurityConfig("{}"));
            }
        
        return configAttributes;
    
    }
}
