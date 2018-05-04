package com.lingnet.hcm.action.laobao;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.common.action.BaseAction.Status;
import com.lingnet.hcm.entity.Branch2;

import com.lingnet.hcm.service.laobao.GangweiService;
import com.lingnet.hcm.service.laobao.WupingangweiService;


import com.lingnet.qxgl.entity.Branch;
import com.lingnet.util.JsonUtil;

@Controller
public class WupinGangweiAction extends BaseAction{

	@Resource(name="wupingangweiService")
	private WupingangweiService wupingangweiService;
	@Resource(name="gangweiService")
	private GangweiService gangweiService;

	private String ids;
	private String departId;
	private Branch branch2;
	private String id;
	private String gwId;// 岗位ID
	private String staffId;// 用户ID

	//人事资料添加页
	public String add(){
		branch2 = gangweiService.get(departId);
		return "add";
	}
	public String delete(){

		try {
			/*将实现方法放到del中*/
			wupingangweiService.delete(ids);
		} catch (Exception e) {
			return ajax(Status.error,e.toString().substring(e.toString().indexOf(":")+1));
		}
		return ajax(Status.success, "success");


	}
	/**
	 * @Title: 物品选择
	 * @return 
	 * String 
	 * @author zhanghj
	 * @since 2017年7月25日 V 1.0
	 */
	public String thing() {
		return "thing";
	}

	//获取人员list列表数据
	public String getPersonData(){
		String josn=JsonUtil.Encode(wupingangweiService.getPersonByDepId(pager,searchData));
		System.out.println(pager+"    "+searchData);
		return ajax(Status.success,josn);
	}
	/**
	 * 物品保存
	 */
	public String saveOrUpdate(){
		try{
			wupingangweiService.saveOrUpdate(gwId, griddata);
		}catch (Exception e){
			e.printStackTrace();
			return ajax(Status.success, e.toString().substring(e.toString().indexOf(":")+1));
		}
		return ajax(Status.success, "success");
	}

	/**
	 * @Title: 获取岗位关联的物品信息
	 * @return 
	 * String 
	 * @author zhanghj
	 * @since 2017年7月25日 V 1.0
	 */
	public String getGwAndWpData() {
		return ajax(Status.success, JsonUtil.Encode(wupingangweiService.getGwAndWpData(searchData, pager)));
	}

	/**
	 * @Title: 获取当前用户可以领取的物品信息
	 * @return 
	 * String 
	 * @author zhanghj
	 * @since 2017年7月25日 V 1.0
	 */
	public String getCurStaffWupinData() {
		

		return ajax(Status.success, JsonUtil.Encode(wupingangweiService.getCurStaffWupinData(staffId,departId, searchData, pager)));
	}

	/**
	 * @Title: 保存员工领取的物品
	 * @return 
	 * String 
	 * @author zhanghj
	 * @since 2017年7月25日 V 1.0
	 */
	public String saveLingquWp() {
		return ajax(Status.success, wupingangweiService.saveLingquWp(griddata, staffId));
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	public Branch getBranch2() {
		return branch2;
	}

	public void setBranch2(Branch branch2) {
		this.branch2 = branch2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGwId() {
		return gwId;
	}

	public void setGwId(String gwId) {
		this.gwId = gwId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}


}
