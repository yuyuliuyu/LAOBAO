package com.lingnet.common.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.entity.BaseEntity;
import com.lingnet.common.service.BaseService;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
import com.lingnet.util.Pager;
import com.lingnet.util.ReflectionUtil;
import com.lingnet.util.ToolUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class BaseAction extends ActionSupport {

    private static final long serialVersionUID = 8224570550172140065L;

	@Resource(name="baseService")
    private BaseService baseService;
	private static final String HEADER_TEXT_CONTENT_TYPE = "text/plain";
	private static final String HEADER_JSON_CONTENT_TYPE = "text/plain";
	private static final String HEADER_ENCODING = "UTF-8";
	private static final boolean HEADER_NO_CACHE = true;
	public static final String VIEW = "view";
	public static final String LIST = "list";
	public static final String ADD = "add";
    public static final String ADDORUPDATE = "addorupdate";
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public static final String REDIRECT = "redirect";	
	public static final String STATUS_PARAMETER_NAME = "status";// 操作状态参数名称
	public static final String MESSAGE_PARAMETER_NAME = "message";// 操作消息参数名称
	public static String printerAddress;
	public static String printerAddress2;
	protected Pager pager = new Pager(); // 分页
	protected String id;
	protected String ids;
	protected String redirectUrl;// 跳转URL
	protected String key;
	protected String searchBy;
	protected int pageIndex;
	protected int pageSize;
	public String display;
	public static String rootPath;
	private String modulename;
	private String pmodulename;
	public String headpid;
	public String headfid;
	public String data;//新增数据
	public String depId;//部门ID

	/**
	 * 相当于Restrictions.eq
	 * @param fieldName 字段名
	 * @param value 值
	 * @return Criterion
	 */
	public Criterion eq(String fieldName, Object value) {
		return Restrictions.eq(fieldName, value);
	}
	/**
	 * 相当于Restrictions.ne
	 * @param fieldName 字段名
	 * @param value 值
	 * @return Criterion
	 */
	public Criterion ne(String fieldName, Object value) {
		return Restrictions.ne(fieldName, value);
	}
	/**
	 * 相当于Restrictions.in
	 * @param fieldName 字段名
	 * @param value 值集合
	 * @return Criterion
	 */
	public Criterion in(String fieldName, Collection value) {
		return Restrictions.in(fieldName, value);
	}
	/**
	 * 相当于Restrictions.in
	 * @param fieldName 字段名
	 * @param value 值集合
	 * @return Criterion
	 */
	public Criterion in(String fieldName, Object[] value) {
		return Restrictions.in(fieldName, value);
	}
	/**
	 * 相当于Restrictions.like
	 * @param fieldName 字段名
	 * @param value 值
	 * @return Criterion
	 */
	public Criterion like(String fieldName, Object value) {
		return Restrictions.like(fieldName, "%" + value + "%");
	}
	/**
	 * 相当于Restrictions.or
	 * @param value Criterion集合
	 * @return Criterion
	 */
	public Criterion or(Collection<Criterion> value) {
		Criterion cr = value.size() == 0 ? Restrictions.isNotNull("id") : Restrictions.isNull("id");
		for(Criterion c : value) {
			cr = Restrictions.or(cr, c);
		}
		return cr;
	}
	/**
	 * 相当于Restrictions.or
	 * @param value Criterion集合
	 * @return Criterion
	 */
	public Criterion or(Criterion... value) {
		Criterion cr = value.length == 0 ? Restrictions.isNotNull("id") : Restrictions.isNull("id");
		for(Criterion c : value) {
			cr = Restrictions.or(cr, c);
		}
		return cr;
	}
	/**
	 * 根据Criterion条件取得指定实体类的对象
	 * @param clazz 实体类
	 * @param cList 条件集合
	 * @return 实体类对象
	 */
	public <T> T getBeanByCriteria(Class<T> clazz, Object...cList) {
		return (T)baseService.get(clazz, cList);
	}
	
	
	/**
	 * 根据Criterion条件取得指定实体类的对象集合
	 * @param clazz 实体类
	 * @param cList 条件集合
	 * @return 实体类对象集合
	 */
	public <T> List<T> getBeanListByCriteria(Class<T> clazz, Object...cList) {
		return baseService.getList(clazz, cList);
	}
	/**
     * 根据Criterion条件取得指定实体类的排序对象集合
     * @param clazz 实体类
     * @param cList 条件集合
     *  * @param Order 排序条件
     * @return 实体类对象集合
     */
	public <T> List<T> getOrderBeanListByCriteria(Class<T> clazz, Order Order ,Object...cList) {
        return baseService.getOrderList(clazz, Order, cList);
    }
	/**
     * 根据Criterion条件取得指定实体类的对象集合
     * @param clazz 实体类
     * @param list 条件集合
     * @return 实体类对象集合
     */
	public <T> List<T> getFromBeanByCriteria(Class<T> clazz, List list) {
        return baseService.getFromList(clazz, list);
    }
	/**
	 * 根据ID取得指定实体类的对象
	 * @param clazz 实体类
	 * @param ID ID
	 * @return 实体类对象
	 */
	public <T> T getBeanById(Class<T> clazz, String id) {
		return (T)baseService.get(clazz, id);
	}
	/**
	 * 取得所有实体类的对象集合
	 * @param clazz 实体类
	 * @return 所有实体类对象集合
	 */
	public <T> List<T> getAllList(Class<T> clazz) {
		return baseService.getAllList(clazz);
	}
	/**
	 * 分页取得实体类对象集合的json串
	 * @param clazz 实体类
	 * @param pager 分页对象
	 * @param cList 条件集合
	 * @return 实体类对象集合的json串
	 */
	public <T> String getJsonByPager(Class<T> clazz, Pager pager, Object...cList) throws Exception {
		pager = baseService.findPager(clazz, pager, cList);
		HashMap result = new HashMap();
        result.put("data", ReflectionUtil.getMapList(pager.getResult()));
        result.put("total", pager.getTotalCount());
        String json = JsonUtil.Encode(result);
        return ajax(Status.success, json);
	}
	/**
	 * 取得指定实体类的分页信息
	 * @param clazz 实体类
	 * @param pager 分页对象
	 * @param cList 条件集合
	 * @return 指定实体类的分页信息
	 */
	public <T> Pager findPager(Class<T> clazz, Pager pager, Object...cList) throws Exception {
		return baseService.findPager(clazz, pager, cList);
	}
	
	public <T> Pager findPagerByOrder(Class<T> clazz, Pager pager, Order order,Criterion... criterions) throws Exception {
        return baseService.findPagerByOrder(clazz, pager, order,criterions);
    }
	/**
	 * 根据sql取得分页信息
	 * @param pager 分页对象
	 * @param sql sql语句
	 * @return 分页信息
	 */
	public Pager findPagerBySql(Pager pager, String sql) {
		return baseService.findPagerBySql(pager, sql);
	}
	/**
	 * 根据sql取得分页信息
	 * @param pager 分页对象
	 * @param sql sql语句
	 * @param mapPara 参数信息
	 * @return 分页信息
	 */
	public Pager findPagerBySql(Pager pager, String sql, HashMap<String, Object> mapPara) {
		return baseService.findPagerBySql(pager, sql, mapPara);
	}

	/**
	 * @Title: 根据sql获取信息
	 * @param sql
	 * @return 
	 * List<?> 
	 * @author zhanghj
	 * @since 2017年6月30日 V 1.0
	 */
	public List<?> findBySql(String sql) {
	    return baseService.findBySql(sql);
	}
	/**
	 * 更新实体类对象
	 * @param bean
	 */
	public void update(BaseEntity bean) {
		baseService.update(bean);
	}
	/**
	 * 更新对象
	 */
	public void updateObj(Object o){
		baseService.clear();
		baseService.update(o);
	}
	/**
	 * 保存实体类对象
	 * @param bean
	 */
	public void save(BaseEntity bean) {
		bean.setId(null);
		baseService.save(bean);
	}
	/**
	 * 保存对象
	 */
	public void saveObj(Object o){
		baseService.save(o);
	}
	/**
	 * 根据条件删除指定实体类的对象
	 * @param clazz 实体类
	 * @param cList 条件集合
	 */
	public <T> void deleteByCriteria(Class<T> clazz, Object... cList) {
		baseService.deleteByCriteria(clazz, cList);
	}
	// 操作状态（警告、错误、成功）
	public enum Status {
		warn, error, success
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	// 判断是否为添加或修改
	public Boolean getIsAddAction() {
		if (id == null) {
			return true;
		} else {
			return false;
		}
	}

	// 获取Attribute
	public Object getAttribute(String name) {
		return ServletActionContext.getRequest().getAttribute(name);
	}

	// 设置Attribute
	public void setAttribute(String name, Object value) {
		ServletActionContext.getRequest().setAttribute(name, value);
	}

	// 获取Parameter
	public String getParameter(String name) {
		return getRequest().getParameter(name);
	}

	// 获取Parameter数组
	public String[] getParameterValues(String name) {
		return getRequest().getParameterValues(name);
	}

	// 获取Session
	public Object getSession(String name) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		return session.get(name);
	}

	// 获取Session
	public Map<String, Object> getSession() {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		return session;
	}

	// 设置Session
	public void setSession(String name, Object value) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		session.put(name, value);
	}

	// 获取Request
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	// 获取Response
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	// 获取Application
	public ServletContext getApplication() {
		return ServletActionContext.getServletContext();
	}

	// 设置页面不缓存
	public void setResponseNoCache() {
		getResponse().setHeader("progma", "no-cache");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().setHeader("Cache-Control", "no-store");
		getResponse().setDateHeader("Expires", 0);
	}

	// AJAX输出
	protected String ajax(String content, String contentType) {
		try {
			HttpServletResponse response = initResponse(contentType);
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	/**
     * ajaxJsonp返回结果处理
     * @param status
     * @param message
     * @return
     */
    protected String ajaxJsonp(Status status, String result) {
        String callback = this.getRequest().getParameter("callback");
        String returnResult = "";
        if(callback != null && !"".equals(callback)){
//          returnResult = callback  + "(\""+JsonUtil.Encode(result)+"\")";
            JSONObject resultJSON = JSONObject.fromObject(result); 
            returnResult = callback  + "("+resultJSON.toString(1,1)+")";
//          returnResult=String.format("{0}({1})", callback, JsonUtil.Encode(result));
        }
        HttpServletResponse response = initResponse(HEADER_JSON_CONTENT_TYPE);
        try {
            /*Map<String, String> jsonMap = new HashMap<String, String>();
            jsonMap.put(STATUS_PARAMETER_NAME, status.toString());
            jsonMap.put(MESSAGE_PARAMETER_NAME, message);
            JsonUtil.getMapper().writeValue(response.getWriter(), jsonMap);*/
            response.getWriter().write(returnResult);
            response.getWriter().flush();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return NONE;
    }

	// 根据文本内容输出AJAX
	protected String ajax(String text) {
		return ajax(text, HEADER_TEXT_CONTENT_TYPE);
	}

	// 根据操作状态输出AJAX
	protected String ajax(Status status) {
		HttpServletResponse response = initResponse(HEADER_JSON_CONTENT_TYPE);
		try {
			Map<String, String> jsonMap = new HashMap<String, String>();
			jsonMap.put(STATUS_PARAMETER_NAME, status.toString());
			JsonUtil.getMapper().writeValue(response.getWriter(), jsonMap);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		return NONE;
	}

	// 根据操作状态、消息内容输出AJAX
	protected String ajax(Status status, String message) {
		HttpServletResponse response = initResponse(HEADER_JSON_CONTENT_TYPE);
		try {
			/*Map<String, String> jsonMap = new HashMap<String, String>();
			jsonMap.put(STATUS_PARAMETER_NAME, status.toString());
			jsonMap.put(MESSAGE_PARAMETER_NAME, message);*/
			//JsonUtil.getMapper().writeValue(response.getWriter(), message);
			response.getWriter().write(message);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		return NONE;
	}

	// 根据Object输出AJAX
	protected String ajax(Object object) {
		HttpServletResponse response = initResponse(HEADER_JSON_CONTENT_TYPE);
		try {
			JsonUtil.getMapper().writeValue(response.getWriter(), object);
			
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		return NONE;
	}

	// 根据boolean状态输出AJAX
	protected String ajax(boolean booleanStatus) {
		HttpServletResponse response = initResponse(HEADER_JSON_CONTENT_TYPE);
		try {
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put(STATUS_PARAMETER_NAME, booleanStatus);
			JsonUtil.getMapper().writeValue(response.getWriter(), jsonMap);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		return NONE;
	}
	
	public void download(String fileName) throws IOException {
		HttpServletResponse res = initResponse(HEADER_JSON_CONTENT_TYPE);    
		OutputStream os = res.getOutputStream();  
	    try {  
	        res.reset();  
	        
	        String saveName = fileName.substring(fileName.lastIndexOf("/") + 1);
	        res.setHeader("Content-Disposition", "attachment; filename=" + saveName);  
	        res.setContentType("application/octet-stream; charset=utf-8");  
	        os.write(FileUtils.readFileToByteArray(new File(ServletActionContext.getServletContext().getRealPath("/") + fileName)));  
	        os.flush();  
	    } finally {  
	        if (os != null) {  
	            os.close();  
	        }  
	    }  
	}

	private HttpServletResponse initResponse(String contentType) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType(contentType + ";charset=" + HEADER_ENCODING);
		if (HEADER_NO_CACHE) {
			response.setDateHeader("Expires", 1L);
			response.addHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
		}
		// 跨域请求
		String url = ToolUtil.getPropert("believeurl.properties", "url");
		if (!StringUtils.isBlank(url)) {
		    response.setHeader("Access-Control-Allow-Credentials", "true");
		    response.setHeader("Access-Control-Allow-Origin", url);
		}

		return response;
	}
	

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
    	if(StringUtils.isNotEmpty(key)){
    		 pager.setKeyword(key);
        }else{
            pager.setSearchBy(null);
        }
        pager.setKeyword(key);
        
        this.key = key;
    }
    
    

    /**
     * @return the searchBy
     */
    public String getSearchBy() {
        return searchBy;
    }
    /**
     * @param searchBy the searchBy to set
     */
    public void setSearchBy(String searchBy) {
        if(StringUtils.isNotEmpty(searchBy)){
            pager.setSearchBy(searchBy);
        }else{
            pager.setSearchBy(null);
        }
        
        this.searchBy = searchBy;
    }
    /**
     * @return the pageIndex
     */
    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * @param pageIndex the pageIndex to set
     */
    public void setPageIndex(int pageIndex) {
        pager.setPageNumber(pageIndex+1);
        this.pageIndex = pageIndex;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        pager.setPageSize(pageSize);
        this.pageSize = pageSize;
    }

	public String getDisplay() {
		if(display == null || "".equals(display)) {
			display = "block";
		}
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getPrinterAddress() throws IOException {
		if(BaseAction.printerAddress == null) {
			Properties properties = new Properties();
			properties.load(this.getClass().getResourceAsStream("/jdbc.properties"));
			String address = properties.getProperty("printerAddress");
			if(address == null) {
				setPrinterAddress("\\\\10.16.107.66\\TSC TTP-342 Pro");
			} else {
				BaseAction.printerAddress = address.replace("\\", "\\\\");
			}
		}
		return BaseAction.printerAddress;
	}

	public void setPrinterAddress(String printerAddress) throws IOException {
		Properties properties = new Properties();
		InputStream ios = this.getClass().getResourceAsStream("/jdbc.properties");
		properties.load(ios);
		ios.close();
		
        OutputStream fos = new FileOutputStream(getRootPath() + "/WEB-INF/classes/jdbc.properties");   
		properties.setProperty("printerAddress", printerAddress);
		properties.store(fos, "");
		fos.close();
		BaseAction.printerAddress = printerAddress.replace("\\", "\\\\");
	}

	public String getPrinterAddress2() throws IOException {
		if(BaseAction.printerAddress2 == null) {
			Properties properties = new Properties();
			properties.load(this.getClass().getResourceAsStream("/jdbc.properties"));
			String address = properties.getProperty("printerAddress2");
			if(address == null) {
				setPrinterAddress2("\\\\10.16.107.66\\TSC TTP-342E Pro");
			} else {
				BaseAction.printerAddress2 = address.replace("\\", "\\\\");
			}
		}
		return BaseAction.printerAddress2;
	}

	public void setPrinterAddress2(String printerAddress2) throws IOException {
		Properties properties = new Properties();
		InputStream ios = this.getClass().getResourceAsStream("/jdbc.properties");
		properties.load(ios);
		ios.close();
		
        OutputStream fos = new FileOutputStream(getRootPath() + "/WEB-INF/classes/jdbc.properties");   
		properties.setProperty("printerAddress2", printerAddress2);
		properties.store(fos, "");
		fos.close();
		BaseAction.printerAddress2 = printerAddress2.replace("\\", "\\\\");
	}

	public String getRootPath() {
		if(BaseAction.rootPath == null) {
			setRootPath(ServletActionContext.getServletContext().getRealPath("/"));
		}
		return BaseAction.rootPath;
	}


	public void setRootPath(String rootPath) {
		BaseAction.rootPath = rootPath;
	}
	

	
    /**
     * @return the modulename
     */
    public String getModulename() {
        return modulename;
    }
    /**
     * @param modulename the modulename to set
     */
    public void setModulename(String modulename) {
        this.modulename = modulename;
    }
    /**
     * @return the pmodulename
     */
    public String getPmodulename() {
        return pmodulename;
    }
    /**
     * @param pmodulename the pmodulename to set
     */
    public void setPmodulename(String pmodulename) {
        this.pmodulename = pmodulename;
    }
    public String operate(String djType, String czType,String czdj) {
        baseService.operate(djType, czType, czdj);
        return "success";
        
    }

    /**
     * 时间减8小时处理
     */
    public Date subTime(Date indate) {
        Long time=indate.getTime()-28800000;
        Date dat=new Date(time);
        return dat;
    }
    
    public String getHeadpid() {
        return headpid;
    }

    public void setHeadpid(String headpid) {
        this.headpid = headpid;
    }

    public String getHeadfid() {
        return headfid;
    }

    public void setHeadfid(String headfid) {
        this.headfid = headfid;
    }
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
    
	
	private String className;//类名首字母小写
	private String packageName;
	public String formdata;//保存更新数据
	public String searchData;//查询条件json
	private static final String entityPacage="com.lingnet.mec.entity.";//实体类包
	public String griddata;//保存表格数据
	public String show;//查看页标记   false为查看页

	/**
	 * 列表页
	 */
	public String list(){
		try {
			this.getRequest().getRequestDispatcher("/jsp/"+packageName+"/"+getStr(className)+"_list.jsp").forward(getRequest(), getResponse());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	/**
	 *列表数据
	 */
	public String getListData(){
		String json="";
		try {
			Class c=Class.forName(getClass(className));
			List resultList =baseService.findPager(c, pager).getResult();
			json = JsonUtil.Encode(ReflectionUtil.getMapList(resultList));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajax(Status.success,json);
	}
	
	/**
	 * 新增页
	 */
	public String  add(){
		try {
			this.getRequest().getRequestDispatcher("/jsp/"+packageName+"/"+getStr(className)+"_add.jsp").forward(getRequest(), getResponse());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "add";
	}
	
	/**
	 * 编辑页
	 */
	public String edit(){
		try {
			Class c=Class.forName(getClass(className));
			HttpServletRequest request=getRequest();
			request.setAttribute(className, getBeanById(c, id));
			request.getRequestDispatcher("/jsp/"+packageName+"/"+getStr(className)+"_add.jsp").forward(getRequest(), getResponse());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "edit";
	}
	
	/**
	 * 保存更新
	 */
	public String saveOrUpdate(){
		try {
			Class c=Class.forName(getClass(className));
			//当前登录用户Id
			String currentUserId=getBeanByCriteria(QxUsers.class
					, Restrictions.eq("username", LingUtil.userName())).getId();
			//将json转换为MAP
			Map<String,String> data=JsonUtil.parseProperties(formdata);
			String id=data.get("id");
			//save
			if(StringUtils.isEmpty(id)){
				//创建人(即使c没有该字段也可以)
				data.put("creater", currentUserId);
				//锁定状态
				data.put("status","1");
				saveObj(JsonUtil.toObject(JsonUtil.Encode(data), c));
			}else{//update
				Map<String,String> updateData=JsonUtil.parseProperties(
						JsonUtil.toJson(getBeanByCriteria(c, Restrictions.eq("id",id))));
				//修改人
				updateData.put("modifyer", currentUserId);
				for(String key:data.keySet()){
					updateData.put(key, data.get(key));
				}
				updateObj(JsonUtil.toObject(JsonUtil.Encode(updateData), c));
			}
			return ajax(Status.success, "success");
		} catch (Exception e) {
			e.printStackTrace();
			return ajax(Status.success, "错误"); 
		}
	}
	
	/**
	 * 删除
	 */
	public String delete(){
		try {
			Class c=Class.forName(getClass(className));
			deleteByCriteria(c, Restrictions.in("id", ids.split(",")));
			return ajax(Status.success, "success");
		} catch (Exception e) {
			e.printStackTrace();
			return ajax(Status.success, "错误");
		}
	}
	
	/**
	 * 锁定
	 */
	public String lock(){
		try {
			Class c = Class.forName(getClass(className));
			List<Object>objs=new ArrayList<Object>();
			for(String id:ids.split(",")){
				Map<String,String> map=JsonUtil.parseProperties(
						JsonUtil.Encode(getBeanByCriteria(c,Restrictions.eq("id",id))));
				map.put("status", "1");
				objs.add(JsonUtil.toObject(JsonUtil.Encode(map), c));
			}
			baseService.updateBatch(objs);
			return ajax(Status.success, "success");
		} catch (Exception e) {
			e.printStackTrace();
			return ajax(Status.success, "错误");
		}
	}
	
	/**
	 * 解锁
	 */
	public String unlock(){
		try {
			Class c = Class.forName(getClass(className));
			List<Object>objs=new ArrayList<Object>();
			for(String id:ids.split(",")){
				Map<String,String> map=JsonUtil.parseProperties(
						JsonUtil.Encode(getBeanByCriteria(c,Restrictions.eq("id",id))));
				map.put("status", "0");
				objs.add(JsonUtil.toObject(JsonUtil.Encode(map), c));
			}
			baseService.updateBatch(objs);
			return ajax(Status.success, "success");
		} catch (Exception e) {
			e.printStackTrace();
			return ajax(Status.success, "错误");
		}
	}
	
	/**获取JSP名*/
	public String getStr(String s){
		StringBuffer result=new StringBuffer(s);
		char[] chars=s.toCharArray();
		int temp;
		int count=0;
		for(int i=0;i<chars.length;i++){
			temp=(int)chars[i];
			if(temp<=90 && temp>=65){
				result=result.insert(i+count, "_");
				count++;
			}
		}
		return result.toString().toLowerCase();
	}
	
	/**获取完整类名*/
	public String getClass(String className){
		return entityPacage+className.substring(0, 1).toUpperCase()+className.substring(1);
	}
	
	///////////getter and setter/////////////////
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getFormdata() {
		return formdata;
	}

	public void setFormdata(String formdata) {
		this.formdata = formdata;
	}

	public String getSearchData() {
		return searchData;
	}

	public void setSearchData(String searchData) {
		if(StringUtils.isNotEmpty(searchData)){
			pager.setSearchData(searchData);
        }else{
            pager.setSearchData(null);
        }
		
		this.searchData = searchData;
	}
	public String getGriddata() {
		return griddata;
	}
	public void setGriddata(String griddata) {
		this.griddata = griddata;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
    
}
