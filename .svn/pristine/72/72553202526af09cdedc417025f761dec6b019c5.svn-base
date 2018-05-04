package com.lingnet.qxgl.interceptor;

import java.net.InetAddress;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Restrictions;

import com.lingnet.qxgl.entity.Branch;
import com.lingnet.qxgl.entity.QxLog;
import com.lingnet.qxgl.entity.QxResource;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.qxgl.service.LogService;
import com.lingnet.util.LingUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 记录日志拦截器
 * @ClassName: FilterInterceptor 
 * @Description: TODO 
 * @author wsx
 * @date 2016-8-24 上午8:38:29 
 *
 */
public class FilterInterceptor extends MethodFilterInterceptor {
	private static final long serialVersionUID = 1L;

	@Resource(name = "logService")
    private LogService logService;
    

    private static final String SPRING_SECURITY_LAST_EXCEPTION = "SPRING_SECURITY_LAST_EXCEPTION";

    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        
        HttpServletRequest request = ServletActionContext.getRequest();
        String path = request.getRequestURI();
        String actionPath = path.substring(8);
        
        // 访问服务器所带有的参数信息
        String czdj = "";
        String queryInfo = request.getQueryString();
        
        if ((!path.contains("add")) && queryInfo != null
                && (!queryInfo.equals(""))) {
            
            if (queryInfo.contains("id")) {
                int start = queryInfo.indexOf("id");
                String info = queryInfo.substring(start + 3);
                if (info.contains("&")) {
                    czdj = info.substring(0, info.indexOf("&"));
                } else {
                    czdj = info;
                }
            }
            
        }
        InetAddress addr = InetAddress.getLocalHost();
        //ip地址
        String ip = addr.getHostAddress().toString();
        //主机名
        String host = addr.getHostName().toString();
        String result = invocation.invoke();

        //初始化日志信息
        QxLog log = new QxLog();
        // 操作时间
        log.setCzDate(new Date());
        // 操作人
        String username=LingUtil.userName();
        log.setCzUser(username);
        QxUsers users=logService.get(QxUsers.class, Restrictions.eq("username",username),Restrictions.eq("isDelete",0));
        //分中心
        if(users!=null){
        	Branch branch=logService.get(Branch.class, Restrictions.eq("id", users.getCid()));
        	if(branch!=null){
        		log.setFzx(branch.getFzx());
        	}
        }
        // URL
        log.setCzObject(actionPath);
        QxResource resource=logService.get(QxResource.class, Restrictions.eq("resourceurl",actionPath));
        if(resource!=null){
        	if("MENU".equals(resource.getType())){
        		log.setDjType(resource.getDescription());
        	}else if("ACTION".equals(resource.getType())){
        		log.setDjType(resource.getPresource());
        	}
        }
        //通过方法名来判断操作类型
        if (actionPath.contains("add")) {
            log.setCzType("增加");
        } else if (actionPath.contains("edit")) {
            log.setCzType("编辑");
        } else if (actionPath.contains("remove")) {
            log.setCzType("删除");
        } else if (actionPath.contains("look")) {
            log.setCzType("查看");
        } else if (actionPath.contains("list")) {
            log.setCzType("查询");
        } else if (actionPath.contains("lock")) {
            log.setCzType("启用");
        } else if (actionPath.contains("unlock")) {
            log.setCzType("禁用");
        } else if (actionPath.contains("push")) {
            log.setCzType("推送");
        } else if (actionPath.contains("unpush")) {
            log.setCzType("撤销推送");
        }else{
        	log.setCzType("无");
        }
        
        //单据信息
        log.setCzdj(czdj);
        //ip地址
        log.setIpDz(ip);
        // 主机名
        log.setPcName(host);

        Map<String, Object> session = ActionContext.getContext().getSession();
        
        // 返回Spring Security返回的异常
        Exception springSecurityException = (Exception) session
                .get(SPRING_SECURITY_LAST_EXCEPTION);
        
        if (springSecurityException != null) {
            // 异常信息
            log.setRmk(springSecurityException.getMessage());
        }

        //保存日志信息
        logService.save(log);

        return result;
    }

}
