package com.lingnet.qxgl.security.authentication.details;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.HashSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lingnet.qxgl.dao.UserDao;
import com.lingnet.qxgl.entity.QxLog;
import com.lingnet.qxgl.entity.QxRoles;
import com.lingnet.qxgl.entity.QxUser;

public class BackendUserDetailsService implements UserDetailsService {

	@Resource(name = "userDao")
	private UserDao userDao;

	public void setAdminDao(UserDao userDao) {
		this.userDao = userDao;
	}

	// 因为user实现了userDatils 接口，所以就可以直接返回user
	// 在这里必须启用事务管理,否则会导致session提前关闭
	@Transactional
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		QxUser qxusers = userDao.get(Restrictions.eq("username",username),Restrictions.eq("isDelete", 0));

		if (qxusers == null) {
			throw new BadCredentialsException("UserName Not Found");
		}else if(qxusers.isUserEnabled()==false){
			throw new DisabledException("UserName Not ENABLE");
		}
		qxusers.setAuthorities(getGrantedAuthorities(qxusers));
		String ip="";
        //String address="";
        try {
        	HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        	//HttpServletRequest req=ServletActionContext.getRequest();  
            String ipAddress = req.getHeader("x-forwarded-for");  
                if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress))   
                    ipAddress = req.getHeader("Proxy-Client-IP");               
                if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress))   
                    ipAddress = req.getHeader("WL-Proxy-Client-IP");      
                if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress))   
                    ipAddress = req.getHeader("HTTP_X_FORWARDED_FOR");       
                if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress))   
                    ipAddress = req.getHeader("HTTP_CLIENT_IP");         
                if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress))   
                    ipAddress = req.getRemoteAddr();  
            ip = ipAddress;
            if(ip.equals("0:0:0:0:0:0:0:1")){
                InetAddress addr = InetAddress.getLocalHost();
                ip=addr.getHostAddress().toString();//获得本机IP
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!ip.equals(qxusers.getLoginIp())){
        	qxusers.setLoginIp(ip);
        	qxusers.setLoginDate(new java.util.Date());
        	userDao.update(qxusers);
        }else{
        	qxusers.setLoginDate(new java.util.Date());
        	userDao.update(qxusers);
        }
		// 添加用户登陆记录 
        QxLog Log = new QxLog();
        Log.setCzUser(qxusers.getUsername());
        Log.setCzDate(new java.util.Date());
        Log.setCzType("登录");
        Log.setDjType("登录用户");
        Log.setCzdj("登录");
        Log.setCreateDate(new java.util.Date());
        Log.setIpDz(ip);
        Log.setModifyDate(new java.util.Date());
        userDao.save(Log);
		return qxusers;
	}

	// 获取用户权限集合，权限使用用GrantedAuthority表示，框架中 有他的实现类
	// GrantedAuthorityImpl 只需把角色名称放入即可
	public Collection<GrantedAuthority> getGrantedAuthorities(QxUser user) {
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

		for (QxRoles backendRole : user.getQxRoles()) {
		    authorities.add(new GrantedAuthorityImpl(backendRole
                    .getName()));
		}
		return authorities;
	}
	
	//获得客户端ip
    public static String getIpAddr() throws IOException{  
        HttpServletRequest req=ServletActionContext.getRequest();  
        String ipAddress = req.getHeader("x-forwarded-for");  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress))   
                ipAddress = req.getHeader("Proxy-Client-IP");               
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress))   
                ipAddress = req.getHeader("WL-Proxy-Client-IP");      
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress))   
                ipAddress = req.getHeader("HTTP_X_FORWARDED_FOR");       
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress))   
                ipAddress = req.getHeader("HTTP_CLIENT_IP");         
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress))   
                ipAddress = req.getRemoteAddr();  
            //String host = req.getRemoteHost();  
            String ip = ipAddress;  
           // System.out.println("访问者IP="+ip);  
            return ip;  
     }

}
