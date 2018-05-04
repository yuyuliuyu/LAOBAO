package com.lingnet.qxgl.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.qxgl.dao.LogDao;
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.qxgl.entity.QxLog;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.qxgl.service.LogService;
import com.lingnet.util.LingUtil;
import com.lingnet.util.Pager;

/**
 * 
 * @ClassName: LogServiceImpl 
 * @Description: 日志管理实现类 
 * @author 姜平豹
 * @date 2014-3-26 上午10:05:25 
 *
 */
@Service("logService")
public class LogServiceImpl extends BaseServiceImpl<QxLog, String> implements LogService  {
    
    @Resource(name = "logDao")
    private LogDao logDao;//日志dao
    
    /**
     * 日志查询方法
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public HashMap search(Pager pager, HashMap<String, String> searchMap) {
        
        List data = logDao.search(pager, searchMap);
        
        HashMap result = new HashMap();
        
        result.put("data", data);
        result.put("total", pager.getTotalCount());
        
        return result;
    }
    
    /**
     * 生成日志方法
     */
    @Override
    public String operate(String djType, String czType,String czdj) {
        InetAddress addr;
        String ip="";
        String address="";
        try {
            addr = InetAddress.getLocalHost();
            ip=addr.getHostAddress().toString();//获得本机IP
            address=addr.getHostName().toString();//获得本机名称
            
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        
        QxLog log=new QxLog();
        String username=LingUtil.userName();
        log.setCzUser(username);
        log.setCzDate(new Date());
        log.setDjType(djType);
        log.setCzType(czType);
        log.setIpDz(ip);
        log.setPcName(address);
        log.setCzdj(czdj);
        QxUsers users=get(QxUsers.class, Restrictions.eq("username",username),Restrictions.eq("isDelete",0));
        //分中心
        if(users!=null){
        	Branch branch=get(Branch.class, Restrictions.eq("id", users.getCid()));
        	if(branch!=null){
        		log.setFzx(branch.getFzx());
        	}
        }
        logDao.save(log);
        
        return null;
    }
}
