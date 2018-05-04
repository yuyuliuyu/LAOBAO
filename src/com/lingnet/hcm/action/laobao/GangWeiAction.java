package com.lingnet.hcm.action.laobao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.common.action.BaseAction.Status;

import com.lingnet.hcm.entity.laobao.Gangweiwupin;
import com.lingnet.hcm.service.laobao.GangweiService;

import com.lingnet.qxgl.entity.Branch;
import com.lingnet.qxgl.entity.QxResource;
import com.lingnet.qxgl.security.manage.metadata.BackendSecurityMetadataSource;
import com.lingnet.qxgl.service.LaobaoService;

import com.lingnet.util.JsonUtil;

@Controller
public class GangWeiAction extends BaseAction{

	@Resource(name="laobaoService")
	private LaobaoService laobaoService;

	@Resource(name="gangweiService")
	private GangweiService gangweiService;

	private List<QxResource> backendResources;

	private Branch branch;

	private String[] ids;




	public void setIds(String[] ids) {
		this.ids = ids;
	}

	private String data;//被选中的权限内容
	private String name;
	private String desc;
	private String json;//返回数据
	private String lastname; 

	private BackendSecurityMetadataSource backendSecurityMetadataSource;

	public BackendSecurityMetadataSource getBackendSecurityMetadataSource() {
		return backendSecurityMetadataSource;
	}

	public void setBackendSecurityMetadataSource(
			BackendSecurityMetadataSource backendSecurityMetadataSource) {
		this.backendSecurityMetadataSource = backendSecurityMetadataSource;
	}


	/**
	 * 删除
	 */
	 public String remove(){
		 try {
			 gangweiService.delete(Gangweiwupin.class,ids);

		 } catch (Exception e) {
			 return ajax(Status.error,e.toString().substring(e.toString().indexOf(":")+1));
		 }
		 return ajax(Status.success, "success");
	 }
	 public String delete(){
		 try {
			 /*将实现方法放到del中*/
			 gangweiService.delete(id);
		 } catch (Exception e) {
			 return ajax(Status.error,e.toString().substring(e.toString().indexOf(":")+1));
		 }
		 return ajax(Status.success, "success");
	 }

	 /** 
	  * 跳转到角色管理增加页
	  */
	 public String function(){
		 branch = gangweiService.get(id); 


		 return "function";

	 }
	 public String szadd(){
		 return "szadd";
	 }

	 public String list(){
		 return "list";
	 }

	 public String guanli(){
		 return "guanli";
	 }

	 /**
	  * 角色保存或修改方法
	  */
	 public String saveOrUpdate(){
		 try{
			 gangweiService.saveOrUpdate(id,name,desc,data,backendSecurityMetadataSource);
		 }catch (Exception e){
			 e.printStackTrace();
			 return ajax(Status.success, e.toString().substring(e.toString().indexOf(":")+1));
		 }
		 return ajax(Status.success, "success");
	 }

	 /**
	  * 获取当前用户数据权限
	  * @Title: getTreeListByUser 
	  * @return 
	  * String 
	  * @author xuhp
	  * @since 2017-3-16 V 1.0
	  */
	 public String getTreeListByUser(){
		 String json = "";
		 try {
			 json = laobaoService.getTreeListByUser();
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 return ajax(Status.success, json);
	 }
	 public String getPersonData(){
		 String josn=JsonUtil.Encode(gangweiService.getPersonByDepId(pager,searchData));
		 System.out.println(pager+"    "+searchData);
		 return ajax(Status.success,josn);
	 }
	 /**
	  * 获取角色信息
	  */
	 // 整理代码将实现方法放到impl中  栾胜鹏  2014-10-20
	 public String json(){
		 String json = gangweiService.json(key,pager);

		 return ajax(Status.success, json);
	 }


	 public Branch getBranch2() {
		 return branch;
	 }

	 public void setBranch2(Branch branch) {
		 this.branch = branch;
	 }

	 public String getData() {
		 return data;
	 }

	 public void setData(String data) {
		 this.data = data;
	 }

	 public String getName() {
		 return name;
	 }

	 public void setName(String name) {
		 this.name = name;
	 }

	 public String getDesc() {
		 return desc;
	 }

	 public void setDesc(String desc) {
		 this.desc = desc;
	 }

	 public String getJson() {
		 return json;
	 }

	 public void setJson(String json) {
		 this.json = json;
	 }

	 public String getLastname() {
		 return lastname;
	 }

	 public void setLastname(String lastname) {
		 this.lastname = lastname;
	 }



	 /**
	  * 获取角色对应用户名称,分页展现
	  */
	 //    public String getuser(){
	 //        HashMap result = gangweiService.findUserByRoleId(pager,id);
	 //        
	 //       // json = JsonUtil.Encode(result);
	 //        
	 //       // return ajax(Status.success, json);
	 //    }
}
