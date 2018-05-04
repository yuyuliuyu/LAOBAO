package com.lingnet.hcm.action.laobao;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.common.action.BaseAction.Status;
import com.lingnet.hcm.service.laobao.WupinhistoryService;

import com.lingnet.qxgl.entity.Branch;
import com.lingnet.util.ExcelUtil;
import com.lingnet.util.JsonUtil;

@Controller
public class WuPinHistoryAction extends BaseAction {

	@Resource(name = "wupinhistoryService")
	private WupinhistoryService wupinhistoryService;

	private String ids;
	private String departId;
	private Branch branch2;
	private String id;

	// 获取人员list列表数据
	public String getPersonData() {
		String josn = JsonUtil.Encode(wupinhistoryService.getPersonByDepId(
				pager, searchData,ids));
		System.out.println(pager + "    " + searchData);
		return ajax(Status.success, josn);
	}
	//删除
	public String delete(){

		try {
			/*将实现方法放到del中*/
			wupinhistoryService.delete(ids);
		} catch (Exception e) {
			return ajax(Status.error,e.toString().substring(e.toString().indexOf(":")+1));
		}
		return ajax(Status.success, "success");


	}

	/** 导出 */
	public void exportClassInfo() {
		try {
			String[] columns = new String[] { "职工编号","姓名", "物品名称", "领取数量", "规格型号",
					"计量单位", "创建时间", "下次领取时间" };
			Map<String, String> searchValue = StringUtils.isEmpty(searchData) ? null
					: JsonUtil.parseProperties(searchData);

			List<Map<String, String>> data = new ArrayList<Map<String, String>>();
			pager.setPageSize(999999999);
			Map<String, Object> map = new HashMap<String, Object>();
			map = wupinhistoryService.getPersonByDepId(pager, searchData,ids);
			data = JsonUtil.getMapList(JsonUtil.Encode((List<String>) map
					.get("data")));

			ArrayList<ArrayList> exportData = getExportData(data, new String[] {
					"jobNumber","name", "wpmc", "count", "ggxh", "jldw", "createdate",
					"nextdate" });
			ExcelUtil.export("劳保物品领取记录", null, columns, exportData, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private ArrayList<ArrayList> getExportData(List<Map<String, String>> data,
			String[] keys) {
		ArrayList<ArrayList> result = new ArrayList<ArrayList>();
		for (int i = 0; i < data.size(); i++) {
			ArrayList<String> list = new ArrayList<String>();
			Map<String, String> map = data.get(i);
			for (int j = 0; j < keys.length; j++) {
				list.add(map.get(keys[j]));
			}
			result.add(list);
		}
		return result;
	}

	/**
	 * 导出劳保历史信息
	 * 
	 * @Title: exportStandardInfo
	 * @author wangqiang
	 * @since 2017年4月6日 V 1.0
	 */
	/*
	 * public void exportStandardInfo(){ HttpServletResponse resp =
	 * getResponse(); try { HSSFWorkbook hwb =
	 * wupinhistoryService.exportInfos();
	 * resp.setContentType("application/x-msdownload");
	 * resp.setHeader("Content-Disposition", "attachment;filename=\"" + new
	 * String("劳保标准信息".getBytes("gb2312"), "ISO8859-1")+ ".xls" + "\"");
	 * OutputStream out = resp.getOutputStream(); hwb.write(out); out.flush();
	 * out.close(); }catch(Exception e){ e.printStackTrace(); } } /* public
	 * String saveOrUpdate(){ try{ wupingangweiService.saveOrUpdate(id,ids);
	 * }catch (Exception e){ e.printStackTrace(); return ajax(Status.success,
	 * e.toString().substring(e.toString().indexOf(":")+1)); } return
	 * ajax(Status.success, "success"); }
	 */
	// 进入发放历史维护界面
	public String hislist() {
		return "hislist";
	}

	// 进入发放历史界面
	public String history() {
		return "history";
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

	public Branch getBranch() {
		return branch2;
	}

	public void setBranch2(Branch branch) {
		this.branch2 = branch;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
