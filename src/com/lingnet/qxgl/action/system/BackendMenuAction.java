package com.lingnet.qxgl.action.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.qxgl.entity.QxDepartment;
import com.lingnet.qxgl.entity.QxResource;
import com.lingnet.qxgl.entity.QxUseDep;
import com.lingnet.qxgl.service.AdminService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.ToolUtil;

@Controller
@ParentPackage("system")
public class BackendMenuAction extends BaseAction {

	private static final long serialVersionUID = 6465259795235263493L;
	
	 @Resource(name = "adminService")
     private AdminService adminService;
	 @Resource(name = "toolUtil")
     private ToolUtil toolUtil;

	 private final int iMenuRoot = 0;//根菜单

	// 管理员
	public String admin() {
		return "admin";
	}

	// 主体
	public String main() {
		return "main";
	}

	// 头部
	public String header() {
		return "header";
	}
	
	//获取菜单展现内容
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String list(){
	    
	    String json = "";
	    //获取登录用户信息
	    Object oPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
       // Collection cUse = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
	    
        if(oPrincipal instanceof UserDetails){
            
            String userName = ((UserDetails) oPrincipal).getUsername();
            
            //获取最大菜单层级
            
            int size = adminService.getMenuSize(userName);
            
            //根据用户信息获取权限
            List<QxResource> list  = adminService.getResourceByUserName(userName,iMenuRoot, id);
            
            Map<String, String> map = new HashMap<String, String>(); 
            
            Hashtable allData = new Hashtable();
            
            ArrayList<Map> jsonList = new ArrayList<Map>();
                for (int i = 0; i < list.size(); i++) {
                    if(list.get(i).getPresource().equals("MENU_ROOT")){
                        //获取根菜单
                        map = new HashMap<String, String>(); 
                        map.put( "id", list.get(i).getResourcename() );  
                        map.put( "text",list.get(i).getDescription() );  
                        map.put( "iconCls", "fa fa-"+list.get(i).getImgvalue());
                        map.put( "iconStyle", "" );  
                        if(list.get(i).getResourceurl()!=""&&list.get(i).getResourceurl()!=null)
                            map.put( "url",list.get(i).getResourceurl() );
                        jsonList.add(map);
                        
                        allData.put(list.get(i).getResourcename(), list.get(i).getResourcename());
                    }                
                }
            
            //循环获取下级菜单
            for(int i=1;i<size+1;i++){
                List<QxResource> list0  = adminService.getResourceByUserName(userName,i, id);
                for(int j=0;j<list0.size();j++){
                    if(allData.containsKey(list0.get(j).getPresource())){
                        map = new HashMap<String, String>();  
                        map.put( "id",list0.get(j).getResourcename());  
                        map.put( "text",list0.get(j).getDescription() );
                        if(list0.get(j).getImgvalue()!=null&&list0.get(j).getImgvalue().length()>0){
                        	map.put( "iconCls", "fa fa-"+ list0.get(j).getImgvalue() ); 
                        }
                        
                        
                        map.put( "pid", list0.get(j).getPresource() ); 
                        
                        if(list0.get(j).getResourceurl()!=""&&list0.get(j).getResourceurl()!=null)
                            map.put( "url",list0.get(j).getResourceurl() );
                        
                        map.put("iconPostion", "top");
                        jsonList.add(map);
                           
                        allData.put(list0.get(j).getResourcename(), list0.get(j).getResourcename());
                    }
                       
                }
            }
            
              
            json = JsonUtil.Encode(jsonList);
            allData.clear();
            jsonList.clear();
           
            
            //将映射服务加入session
            String path = ToolUtil.getPropert(
                    "config.properties",
                    "file_path");
            ServletActionContext.getRequest().getSession().setAttribute("path", path);
            
            List<QxUseDep> listq = this.getBeanListByCriteria(QxUseDep.class, Restrictions.eq("userId", toolUtil.getUserId()));
            String depId = "";
            for(QxUseDep dep:listq){
            	QxDepartment ment = this.getBeanByCriteria(QxDepartment.class, Restrictions.eq("id", dep.getDepId()));
            	if(ment!=null){
            		depId = ment.getId();
            		break;
            	}
            }
            ServletActionContext.getRequest().getSession().setAttribute("depId",depId );
            //获取的权限放入request对象中
            //ServletActionContext.getRequest().setAttribute("data", json);
            /*QxUsers user = this.getBeanByCriteria(QxUsers.class,Restrictions.eq("username", userName));
            ActionContext actionContext = ActionContext.getContext();
            Map session = actionContext.getSession();*/
            
        }
        return ajax(Status.success, json);
	}

	/**
	 * @Title: 获取首页左侧菜单 
	 * @return 
	 * String 
	 * @author zhanghj
	 * @since 2017年3月9日 V 1.0
	 */
	public String getAuthMenu() {
	    //获取登录用户信息
	    String authMenu = "";
        Object oPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(oPrincipal instanceof UserDetails){
            String userName = ((UserDetails) oPrincipal).getUsername();
            authMenu = adminService.getAuthManageMenu(userName, iMenuRoot);
        }

        return ajax(Status.success, authMenu);
	}

	 public AdminService getAdminService() {
         return adminService;
     }

     public void setAdminService(AdminService adminService) {
         this.adminService = adminService;
     }

}