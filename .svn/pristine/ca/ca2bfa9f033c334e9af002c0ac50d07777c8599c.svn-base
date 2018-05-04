package com.lingnet.qxgl.action.system;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.qxgl.entity.QxRoles;
import com.lingnet.qxgl.entity.QxUser;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.qxgl.entity.SystemInfo;
import com.lingnet.qxgl.service.AdminService;
import com.lingnet.qxgl.service.BackendRoleService;
import com.lingnet.util.JsonUtil;
public class SystemInfoAction extends BaseAction {
    @Resource(name = "adminService")
    private AdminService adminService;
    @Resource(name = "backendRoleService")
    private BackendRoleService backendRoleService;
    private static final long serialVersionUID = 1L;
    private String id;
    private String jsondata; 
    private SystemInfo sysinfo;
    private Integer state;

    /**
     * 
     * @Title: list 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年4月15日 V 1.0
     */
    public String list(){
//      BasicInformation bsinfo=new BasicInformation();
//      List<BasicInformation> basiclist=this.getAllList(BasicInformation.class);
//      for (int i = 0; i < basiclist.size(); i++) {
//          bsinfo=basiclist.get(i); 
//          QxUsers usenfo=adminService.getUserByName(bsinfo.getJobNumber());
//          if(usenfo==null||usenfo.getId()==null){ 
//              QxUser  userinfo=new QxUser();
//              userinfo.setUsername(bsinfo.getJobNumber());
//              userinfo.setName(bsinfo.getName());
//              userinfo.setPassword("123456");
//              userinfo.setIsDelete(0);
//              HashSet<QxRoles> rolelist =new HashSet<QxRoles>(); 
//              //超级管理员
//              QxRoles role = backendRoleService.get(QxRoles.class, Restrictions.eq("id","1"));
//              rolelist.add(role);
//              adminService.saveuser(userinfo, rolelist);
//          }else{
//              //这个用户已经存在，不需要重新添加
//          }
//    } 

        return "list";
    }
    /**
     * 
     * @Title: jsonlist 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年4月15日 V 1.0
     */
    @SuppressWarnings("unchecked")
    public String jsonlist(){
        try {
            if(state==null||state==1){
                state=1;
            }
            pager=this.findPagerByOrder(SystemInfo.class, pager,Order.desc("fabutime"),Restrictions.eq("state",state));
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<SystemInfo> sysinfolist=((List<SystemInfo>) pager.getResult());
        List<Map<String,String>> list=new ArrayList<Map<String,String>>();
        for (int i = 0; i < sysinfolist.size(); i++) {
            Map<String, String> map=new HashMap<String, String>();
            map.put("id", sysinfolist.get(i).getId());
            map.put("title", sysinfolist.get(i).getTitle());
            map.put("content", sysinfolist.get(i).getContent());
            if(sysinfolist.get(i).getFabutime()==null){ 
                map.put("fabutime",""); 
            }else{
                map.put("fabutime", new SimpleDateFormat("yyyy/MM/dd").format(sysinfolist.get(i).getFabutime())); 
            }
            list.add(map);
        }
        return ajax(Status.success,JsonUtil.toJson(list));
    }
    /**
     * 
     * @Title: add 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年4月15日 V 1.0
     */
    public String add(){
        if(id==null||"".equals(id.trim())){
            sysinfo=new SystemInfo();
        }else{
            sysinfo=this.getBeanById(SystemInfo.class, id);
        }
        return "add";
    }
    /**
     * 
     * @Title: saveOrUpdate 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年4月15日 V 1.0
     */
    public String saveOrUpdate(){
        if(jsondata==null||"".equals(jsondata.trim())){
            return ajax(Status.success,"数据错误，请查证后重新操作");
        }
        sysinfo=JsonUtil.toObject(jsondata,SystemInfo.class);
        if(sysinfo==null){
            return ajax(Status.success,"数据错误，请查证后重新操作");
        }else if(sysinfo.getId()==null||"".equals(sysinfo.getId().trim())){
            sysinfo.setState(1);
            this.save(sysinfo);
        }else{
            this.update(sysinfo);
        }
        return ajax(Status.success,"保存/修改成功");
    }
    /**
     * 
     * @Title: remove 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年4月15日 V 1.0
     */
    public String remove(){
        if(id==null||"".equals(id.trim())){
            return ajax(Status.success,"数据错误，删除失败");
        }else{
            this.deleteByCriteria(SystemInfo.class,Restrictions.eq("id",id));
        }
        return ajax(Status.success,"删除成功");
    }
    /**
     * 修改显示状态
     * @Title: chengestate 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年4月13日 V 1.0
     */
    public String chengestate(){
        if(id==null||"".equals(id.trim())){
            return ajax(Status.success,"未选择正确的项，修改失败");
        }
        sysinfo=this.getBeanById(SystemInfo.class, id);
        if(sysinfo.getState()==null||sysinfo.getState()==0){
            sysinfo.setState(1);
            this.update(sysinfo);
            return ajax(Status.success,"修改成功，当前为启用状态");
        }else{
            sysinfo.setState(0);
            this.update(sysinfo);
            return ajax(Status.success,"修改成功，当前为停用状态");
        }
    }
    /*-----------------------------------------------------*/
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getJsondata() {
        return jsondata;
    }
    public void setJsondata(String jsondata) {
        this.jsondata = jsondata;
    }
    public SystemInfo getSysinfo() {
        return sysinfo;
    }
    public void setSysinfo(SystemInfo sysinfo) {
        this.sysinfo = sysinfo;
    }
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    } 
}
