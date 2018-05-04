package com.lingnet.hcm.action.contract; 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.common.action.BaseAction.Status;
import com.lingnet.hcm.entity.DeptPosition;
import com.lingnet.hcm.entity.check.ContractLogs;
import com.lingnet.hcm.entity.contract.ContractManager;
import com.lingnet.hcm.entity.contract.EmployAgreement;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.qxgl.entity.QxDepartment;
import com.lingnet.qxgl.entity.QxUser;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
/**
 * 
 * @ClassName: EmployAgreementAction 
 * @Description: TODO 
 * @author 马晓鹏
 * @date 2017年5月11日 下午2:45:05 
 *
 */
@SuppressWarnings("serial")
public class EmployAgreementAction extends BaseAction {

    @Resource(name="personnelService")
    private PersonnelService personnelService;
    private String id;
    private String jsondata;
    private EmployAgreement employagre;
    private List<EmployAgreement> agrelist;
    private BasicInformation information;//基础数据
    private String state;
    private String username;
    private String deptname;
    private String pname;
    private String show;//查看合同使用
    private String personId;
    private String flag;
    
    public String list(){
        return "list";
    }
    @SuppressWarnings("unchecked")
    public String jsonlist(){
        try {
        	if(personId!=null&&!"".equals(personId)){
        		pager=this.findPagerByOrder(EmployAgreement.class, pager,Order.desc("userid"),Restrictions.isNotNull("id"),
        				Restrictions.eq("userid", personId));
        	}else{
        		pager=this.findPagerByOrder(EmployAgreement.class, pager,Order.desc("userid"),Restrictions.isNotNull("id"));
        	}
            
        } catch (Exception e) { 
            e.printStackTrace();
        }   
        agrelist=(List<EmployAgreement>) pager.getResult();
        List<Map<String, String>> list=new ArrayList<Map<String,String>>();
        for (int i = 0; i < agrelist.size(); i++) {
 
            Map<String, String> map=new HashMap<String, String>();
            map.put("id", agrelist.get(i).getId());
            /*map.put("userid", agrelist.get(i).getUserid());
            map.put("username", agrelist.get(i).getUsername());*/
            try {
            	information=this.getBeanByCriteria(BasicInformation.class, Restrictions.eq("id",agrelist.get(i).getUserid())); 
            	if(information!=null){
            		map.put("userid",information.getJobNumber());
            		map.put("username",information.getName());
            	}
                deptname=this.getBeanByCriteria(QxDepartment.class, Restrictions.eq("id",agrelist.get(i).getDeptid())).getName(); 
                map.put("deptid",deptname);
            } catch (Exception e) {
                map.put("deptid", "");
            } 
            try {
                pname=this.getBeanByCriteria(DeptPosition.class, Restrictions.eq("id", agrelist.get(i).getPositionid())).getPositionName(); 
                map.put("positionid",pname);
            } catch (Exception e) {
                map.put("positionid","");
            }
            map.put("contractid", agrelist.get(i).getContractid());
            map.put("agreno", agrelist.get(i).getAgreno());
            map.put("agretype", agrelist.get(i).getAgretype());
            try {
                deptname=this.getBeanByCriteria(QxDepartment.class, Restrictions.eq("id",agrelist.get(i).getSigndept())).getName(); 
                map.put("signdept",deptname);
            } catch (Exception e) {
                map.put("signdept","");
            }
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd");
            try {
                map.put("signdate", sdf.format(agrelist.get(i).getSigndate()));
            } catch (Exception e) {
                map.put("signdate", "");
            }
            try {
                map.put("effecdate", sdf.format(agrelist.get(i).getEffecdate()));
            } catch (Exception e) {
                map.put("effecdate","");
            }
            map.put("agreterm", agrelist.get(i).getAgreterm());
            map.put("penalpymoney", agrelist.get(i).getPenalpymoney());
            map.put("compenmoney", agrelist.get(i).getCompenmoney());  
            list.add(map);
        }

        Map result = new HashMap();
        result.put("data", list);
        result.put("total", pager.getTotalCount());
        String json = JsonUtil.Encode(result);
        return ajax(Status.success,json); 
    }
    public String add(){
        if(id==null||"".equals(id.trim())){
            employagre=new EmployAgreement();
            employagre.setEmploystate("0");
        }else{
            employagre=this.getBeanById(EmployAgreement.class, id);
            try {
                username=this.getBeanByCriteria(BasicInformation.class, Restrictions.eq("id",employagre.getUserid())).getName(); 
            } catch (Exception e) {
                username="";
            }
            try {
                deptname=this.getBeanByCriteria(QxDepartment.class, Restrictions.eq("id",employagre.getDeptid())).getName(); 
            } catch (Exception e) {
                deptname="";
            }
            try {
//                pname=this.getBeanByCriteria(PositionGrade.class, Restrictions.eq("id", contract.getPostid())).getName();
                pname=this.getBeanByCriteria(DeptPosition.class, Restrictions.eq("id", employagre.getPositionid())).getPositionName();
            } catch (Exception e) {
                pname="";
            }
        }
        return "add";
    }
    public String saveOrUpdate(){
        if(jsondata==null||"".equals(jsondata.trim())){
            return ajax(Status.success,"数据错误，请查证后重新操作");
        } 
        employagre=JsonUtil.toObject(jsondata,EmployAgreement.class);

        try {
            username=personnelService.get(Restrictions.eq("jobNumber", employagre.getUserid())).getName();
        } catch (Exception e) {
            username="";
        }
        employagre.setUsername(username);
        if(employagre==null){
            return ajax(Status.success,"数据错误，请查证后重新操作");
        }else if(employagre.getId()==null||"".equals(employagre.getId().trim())){
//            contract.setState(1);
            this.save(employagre);
        }else{
            this.update(employagre); 
            ContractLogs logs=new ContractLogs();
            QxUser userinfo=LingUtil.userinfo();
            logs.setChangemanid(userinfo.getId());
            logs.setChangeman(userinfo.getName());
            logs.setContractid(employagre.getId());
            logs.setCreateDate(new Date());
            logs.setModifyDate(new Date());
            logs.setChengememo(employagre.getContractid());
            this.save(logs);
        }
        return ajax(Status.success,"保存/修改成功");
    } 
    public String lookchange(){
        return "lookchange";
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String lookjson(){
        if(id==null||"".equals(id)){
            Map result = new HashMap();
            result.put("data", "");
            result.put("total", 0);
            String json = JsonUtil.Encode(result);
            return ajax(Status.success,json);
        }else{
            try {
                pager=this.findPagerByOrder(ContractLogs.class, pager, 
                        Order.desc("createDate"),  Restrictions.eq("contractid", id));
            } catch (Exception e) { 
                e.printStackTrace();
            }
            if(pager.getResult()!=null&&pager.getResult().size()>0){ 
                Map result = new HashMap();
                result.put("data", pager.getResult());
                result.put("total", 0);
                String json = JsonUtil.Encode(result);
                return ajax(Status.success,json);
            }else{ 
                Map result = new HashMap();
                result.put("data", "");
                result.put("total", 0);
                String json = JsonUtil.Encode(result);
                return ajax(Status.success,json);
            }
        } 
    }
    public String changestate(){

      if(id==null||"".equals(id.trim())){
          return ajax(Status.success,"数据错误，操作失败");
      }else{
          employagre =this.getBeanById(EmployAgreement.class,id);
          employagre.setEmploystate(state);
          this.update(employagre);
      }
      return ajax(Status.success,"操作成功");
    }
//    public String remove(){
//        if(id==null||"".equals(id.trim())){
//            return ajax(Status.success,"数据错误，删除失败");
//        }else{
//            this.deleteByCriteria(EmployAgreement.class,Restrictions.eq("id",id));
//        }
//        return ajax(Status.success,"删除成功");
//    }
    /*------------------------------getter and setter-------------------------------------------------*/
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
    public EmployAgreement getEmployagre() {
        return employagre;
    }
    public void setEmployagre(EmployAgreement employagre) {
        this.employagre = employagre;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public List<EmployAgreement> getAgrelist() {
        return agrelist;
    }
    public void setAgrelist(List<EmployAgreement> agrelist) {
        this.agrelist = agrelist;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getDeptname() {
        return deptname;
    }
    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }
    public String getPname() {
        return pname;
    }
    public void setPname(String pname) {
        this.pname = pname;
    }
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public BasicInformation getInformation() {
		return information;
	}
	public void setInformation(BasicInformation information) {
		this.information = information;
	}
    
}
