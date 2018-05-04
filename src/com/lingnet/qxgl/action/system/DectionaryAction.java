package com.lingnet.qxgl.action.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.hcm.entity.basic.TableColumInfo;
import com.lingnet.hcm.service.train.TableColumService;
import com.lingnet.qxgl.entity.JcDictionary;
import com.lingnet.util.JsonUtil;
/**
 * 
 * @ClassName: DectionaryAction 
 * @Description: 数据字典控制层 
 * @author mxp
 * @date 2017年4月11日 下午1:57:22 
 *
 */
public class DectionaryAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "tablecolumservice")
	private TableColumService tableService;

	private String id; // 主键id
	private String pid; // 主键id
	private String jsondata; // 页面获取的json数据
	private String state; // 状态标志位
	private String ids; // 批量操作的id数组
	private JcDictionary jcdictionary;
	private List<TableColumInfo> showlist = new ArrayList<TableColumInfo>();
	private String tablename;
	private String moduleType;// 模块类型（考勤：check；）

	/**
	 * 列表页面展现
	 * 
	 * @Title: list
	 * @return String
	 * @author 马晓鹏
	 * @since 2017年3月8日 V 1.0
	 */
	public String list() {
		showlist = tableService.getshowcolum("数据字典", new Integer[] { 4, 2, 3 });
		return "list";
	}

	/**
	 * 列表数据获取
	 * 
	 * @Title: listjson
	 * @return String
	 * @author 马晓鹏
	 * @since 2017年3月8日 V 1.0
	 */
	public String listjson(){
		if (id == null || "".equals(id.trim())){
			return ajax(Status.success,"");
		}
		List<JcDictionary> tbinfolist=this.getOrderBeanListByCriteria(
				JcDictionary.class, Order.asc("dicvalue"), Restrictions.eq("pid", id));
		return ajax(Status.success,JsonUtil.toJson(tbinfolist));
	}
	/**
	 * 数菜单
	 * @Title: treejson
	 * @return String
	 * @author 马晓鹏
	 * @since 2017年3月29日 V 1.0
	 */
	public String treejson() {
		List<JcDictionary> tbinfolist = null;
		try {
			tbinfolist = this.getOrderBeanListByCriteria(JcDictionary.class, Order.asc("dicname"),
					Restrictions.eq("pid", "0"), Restrictions.eq("moduleType", moduleType));
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		if (tbinfolist != null && tbinfolist.size() > 0) {
			for (JcDictionary b : tbinfolist) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("id", b.getId());
				map.put("pid", b.getPid());
				map.put("name", b.getDicname());
				map.put("branchId", b.getId());
				map.put("isEdit", b.getIsEdit()+"");
				map.put("img", "../jsp/basis/company.gif");
				list.add(map);
			}
		}
		return ajax(Status.success, JsonUtil.toJson(list));
	}

	/**
	 * 获取上级菜单
	 * @Title: getpid
	 * @return String
	 * @author 马晓鹏
	 * @since 2017年3月29日 V 1.0
	 */
	public String getpid() {
		List<JcDictionary> tbinfolist = null;
		try {
			tbinfolist = this.getBeanListByCriteria(JcDictionary.class,
					Restrictions.eq("pid", pid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		if (tbinfolist != null && tbinfolist.size() > 0) {
			for (JcDictionary b : tbinfolist) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("id", b.getId());
				map.put("text", b.getDicname());
				list.add(map);
			}
		}
		return ajax(Status.success, JsonUtil.toJson(list));
	}

	/**
	 * 增加页面展现
	 * 
	 * @Title: add
	 * @return String
	 * @author 马晓鹏
	 * @since 2017年3月8日 V 1.0
	 */
	public String add() {
		if (pid == null || "".equals(pid.trim())) {
			return "error";
		}
		if (id == null || "".equals(id.trim())) {
			jcdictionary = new JcDictionary();
			jcdictionary.setModuleType(moduleType);
			jcdictionary.setPid(pid);
		} else {
			jcdictionary = this.getBeanById(JcDictionary.class, id);
			// 父值
			if (null != jcdictionary) {
			    JcDictionary dictionary = getBeanByCriteria(JcDictionary.class, Restrictions.eq("id", jcdictionary.getPid()));
			    if (null != dictionary) {
			        state = String.valueOf(dictionary.getIsEdit());
			    }
			}
		}
		return "add";
	}

	/**
	 * 保存修改方法
	 * 
	 * @Title: saveorupdate
	 * @return String
	 * @author 马晓鹏
	 * @since 2017年3月8日 V 1.0
	 */
	public String saveorupdate() {
		if (jsondata == null || jsondata == "") {
			return ajax(Status.error, "数据错误请联系管理员");
		} else {
			jcdictionary = JsonUtil.toObject(jsondata, JcDictionary.class);
		}
		try {
			if (jcdictionary.getId() == null
					|| "".equals(jcdictionary.getId().trim())) {
				this.save(jcdictionary);
			} else {
				this.update(jcdictionary);
			}
		} catch (Exception e) {
			return ajax(Status.error, "操作失败");
		}
		return ajax(Status.success, "操作成功");
	}

	/**
	 * 删除方法
	 * 
	 * @Title: remove
	 * @return String
	 * @author 马晓鹏
	 * @since 2017年3月8日 V 1.0
	 */
	public String remove() {
		if (id == null || "".equals(id.trim())) {
			return ajax(Status.error, "参数错误，请重新操作");
		}
		try {
			this.deleteByCriteria(JcDictionary.class,
					Restrictions.eq("id", id));
		} catch (Exception e) {
			return ajax(Status.error, "删除失败");
		}
		return ajax(Status.success, "删除成功");
	}

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public TableColumService getTableService() {
		return tableService;
	}

	public void setTableService(TableColumService tableService) {
		this.tableService = tableService;
	}

	public List<TableColumInfo> getShowlist() {
		return showlist;
	}

	public void setShowlist(List<TableColumInfo> showlist) {
		this.showlist = showlist;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public JcDictionary getJcdictionary() {
		return jcdictionary;
	}

	public void setJcdictionary(JcDictionary jcdictionary) {
		this.jcdictionary = jcdictionary;
	}
}