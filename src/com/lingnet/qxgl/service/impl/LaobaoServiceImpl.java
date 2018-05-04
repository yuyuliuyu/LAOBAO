package com.lingnet.qxgl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;

import com.lingnet.qxgl.entity.Branch;
import com.lingnet.qxgl.service.BackendDepService;
import com.lingnet.qxgl.service.LaobaoService;

import com.lingnet.util.JsonUtil;
import com.lingnet.util.ToolUtil;

@Service("laobaoService")
public class LaobaoServiceImpl extends BaseServiceImpl<Branch, String> implements LaobaoService{
	

	@Resource(name = "toolUtil")
    public ToolUtil toolUtil;
	@Resource(name="backendDepService")
    private BackendDepService backendDepService;
    
	 @SuppressWarnings("rawtypes")
		public String getTreeListByUser(){
	    	 List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
	    	 String userid=toolUtil.getUserId();
	         List data=this.findBySql("SELECT B.ID,B.PID,B.FZX,B.FLG FROM BRANCH2 B " +
	         		"WHERE B.IS_DELETE=0 ");
	         if(data!=null&&data.size()>0){
	             for(int i=0,l=data.size();i<l;i++){
	            	 Object[]b=(Object[]) data.get(i);
	                 HashMap<String, String> map = new HashMap<String, String>();
	                 map.put("id", b[0]==null?"":b[0].toString());
	                 map.put("pid",b[1]==null?"":b[1].toString());
	                 map.put("name", b[2]==null?"":b[2].toString());
	                 map.put("flg", b[3]==null?"":b[3].toString());
	                 map.put("branchId", b[0]==null?"":b[0].toString());
	                 map.put("img", "../jsp/basis/company.gif");
	                 list.add(map);
	                 
/*	                 	//获取下级部门
	                 List dataP = backendDepService.findBySql("SELECT D.PARENT,D.ID,D.BARCH_ID,D.NAME,D.FLG FROM QX_DEPARTMENT D " +
	                 		"INNER JOIN QX_USER_DATAUTH UD ON UD.BRANCH_DEP=D.ID AND UD.FLG='1' AND UD.USERID='"+userid+"' " +
	                 		"WHERE D.BARCH_ID='"+b[0]+"' AND D.IS_DELETE=0 ");
	                 for(int k=0,s=dataP.size();k<s;k++){
	                	 Object[] dep=(Object[])dataP.get(k);
	              	     map = new HashMap<String, String>();
	              	     String branchId=dep[2]==null?"":dep[2].toString();
	              	     String parent=dep[0]==null?"":dep[0].toString();
	                     if("ROOT".equals(parent)){
	                  	     map.put("id", dep[1]==null?"":dep[1].toString());
	                         map.put("pid",branchId);
	                         map.put("name", dep[3]==null?"":dep[3].toString());
	                         map.put("flg", dep[4]==null?"":dep[4].toString());
	                         map.put("branchId", branchId);
	                         map.put("img", "../jsp/basis/dep.png");
	                         list.add(map);
	                     }else{
	                  	     map.put("id", dep[1]==null?"":dep[1].toString());
	                         map.put("pid",parent);
	                         map.put("name", dep[3]==null?"":dep[3].toString());
	                         map.put("flg", dep[4]==null?"":dep[4].toString());
	                         map.put("branchId", branchId);
	                         map.put("img", "../jsp/basis/dep.png");
	                         list.add(map);
	                     }
	                 }*/
	             }
	         }
	          return JsonUtil.Encode(list);
	    }
}
