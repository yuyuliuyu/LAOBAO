package com.lingnet.hcm.service.impl; 
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service; 

import com.lingnet.hcm.service.LingnetService;
import com.lingnet.qxgl.entity.QxUserDatauth;
import com.lingnet.qxgl.entity.QxUserShowauth;
import com.lingnet.qxgl.service.QxUserDatauthService;
import com.lingnet.qxgl.service.QxUserShowauthService;

@Service("lingnetService")
public class LingnetServiceImpl implements LingnetService { 
    @Resource(name = "qushowauthService")
    QxUserShowauthService qushowauthservice; 
    @Override
    public String getdeptbypid(String deptid,String userid,boolean blst) { 
        /*获取用户的部门权限*/
        String deptsubid="";
       List<QxUserShowauth> userauthlist=new ArrayList<QxUserShowauth>();
       try {
           userauthlist=qushowauthservice.getList(
                   Restrictions.eq("userid",userid),
                   Restrictions.eq("flg", "1")); 
            } catch (Exception e) { e.printStackTrace();
                userauthlist=new ArrayList<QxUserShowauth>();
            }
       if(userauthlist!=null&&userauthlist.size()>0){
           for (int i = 0; i < userauthlist.size(); i++) {
               if(deptid==null||"".equals(deptid.trim())){
                   if(i==0){
                       if(blst){
                           deptsubid=userauthlist.get(i).getBranchDep();
                       }else{ 
                           deptsubid="'"+userauthlist.get(i).getBranchDep()+"'";
                       }
                   }else{

                       if(blst){
                           deptsubid=deptsubid+","+userauthlist.get(i).getBranchDep(); 
                       }else{ 
                           deptsubid=deptsubid+",'"+userauthlist.get(i).getBranchDep()+"'";
                       }
                   }
               }else{
                   if(deptid.equals(userauthlist.get(i).getBranchDep())){ 
                       return deptid;
                   }else{
                       continue;
                   }
               }
           }
           if("".equals(deptsubid)){
               return "&";
           }
           return deptsubid;
       }else{
           return "&";
       }
    }

}
