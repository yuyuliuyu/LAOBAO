package com.lingnet.hcm.service.impl.laobao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.laobao.GangweiDao;
import com.lingnet.hcm.entity.laobao.StaffInfo;
import com.lingnet.hcm.service.laobao.GangweiService;
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.qxgl.security.manage.metadata.BackendSecurityMetadataSource;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

@Service("gangweiService")
public class GangweiServiceImpl extends BaseServiceImpl<Branch, String> implements GangweiService{
	
	
	/*以下是代码整理：将放在action中的实现方法放到impl中  栾胜鹏*/
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public void saveOrUpdate(String id, String name, String desc, String data,BackendSecurityMetadataSource backendSecurityMetadataSource) throws Exception {
    	List<Map<String, String>> listMap =null;
        List<Map<String, String>> listData =null;
        List<Map<String, String>> listFun =null;
        

        
        Map map = new HashMap();
        
       // if(id!=null&&!"null".equals(id)){
            //保存权限
        	//Branch2 backendRole = get(Branch2.class,Restrictions.eq("id", id));
            
            //backendRole.setName(name);
            
          //  backendRole.setDescription(desc);
          // QxRoles backendRole = this.findRoleByName(name);
          // if(backendRole!=null){
          //     throw new Exception("该角色名称已经存在，请重新录入！");
          // }
           Branch branch2 = new Branch();
           branch2.setFzx(name);
           
           branch2.setRemark(desc);
          
          
           branch2.setPid("-1");
           branch2.setIsDelete(0);
            
           this.save(branch2);
            
           // operate("角色管理", "增加",backendRole.getName());
       // }
        
      //  backendSecurityMetadataSource.refresh();
    }
	
	public HashMap findUserByRoleId(Pager pager,String roleId){
//	    
//	     List lite = laobaoDao.getUsersByRoleId(pager,roleId);
//	        ArrayList data = new ArrayList();
//	        for (int i = 0, l = lite.size(); i < l; i++)
//	        {
//	            HashMap<String, String> record = new HashMap<String, String>();
//	            QxUsers user = (QxUsers) lite.get(i);
//	            record.put("id", user.getId());
//	            record.put("username", user.getUsername());
//	            record.put("name", user.getName());
//	            record.put("email", user.getEmail());
//	            SimpleDateFormat mat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	            record.put("createdate", mat.format(user.getCreateDate()));
//	            if (lite.get(i) == null) continue;
//	            //if (start <= i && i < end)
//	            //{
//	                data.add(record);
//	          //  }
//	        }
//
//	        HashMap result = new HashMap();
//	        result.put("data", data);
//	        result.put("total", pager.getTotalCount());
//	        
//	        
	        return null;
	}

	@Override
	public String getTreeListByUser() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
     * 获取角色信息 
     */
    @SuppressWarnings("unchecked")
    @Override
    public String json(String key, Pager pager) {
        @SuppressWarnings("rawtypes")
        ArrayList data = new ArrayList();
        
        pager = this.findPager(Branch.class, pager);
      
        @SuppressWarnings("rawtypes")
        List list = pager.getResult();
        //获取角色详细数据转成json
        for (int i = 0; i < list.size(); i++) {
            HashMap<String,String> record = new HashMap<String,String>();
            Branch qx = (Branch) list.get(i);
            
            record.put("id", qx.getId());
            record.put("name", qx.getFzx());
//            if(qx.getIsSystem()){
//                record.put("isSystem", "√");
//            }else{
//                record.put("isSystem", "×");
//            }
           
            record.put("description", qx.getRemark());
            if(qx.getCreateDate()!=null){
            	SimpleDateFormat mat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                record.put("createdate", mat.format(qx.getCreateDate()));
            }
            

            data.add(record);
        }
           @SuppressWarnings("rawtypes")
           HashMap result = new HashMap();
           
           result.put("data", data);
           result.put("total", pager.getTotalCount());
           
           String json = JsonUtil.Encode(result);
           
           return json;
    }

	@Override
	public StaffInfo getStaffByJobNumber(String job_number) {
		// TODO Auto-generated method stub
		return null;
	}
	@Resource(name="gangweiDao")
	private GangweiDao gangweiDao;
	
	//人员基本信息list页面
	@SuppressWarnings({ "rawtypes" })
	@Override
	public HashMap getPersonByDepId(Pager pager, String searchData) {
																																			
		return gangweiDao.getPersonByDepId(pager, searchData);
	}


}
