/**
 * @SingleTableAction.java
 * @com.qdport.qdcc.action.basemanage
 * @Description：
 * 
 * @author xuhp 
 * @copyright  2016
 * @version V
 * @since 2016-4-8
 */
package com.lingnet.common.action.basemanage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;

import com.lingnet.common.action.BaseAction;
import com.lingnet.common.service.BaseService;
import com.lingnet.qxgl.entity.QxUsers;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
import com.lingnet.util.ToolUtil;

/**
 * 单表维护通用Action
 * 
 * @ClassName: SingleTableAction
 * @author xuhp
 * @date 2016-4-8 上午9:28:43
 * 
 */
@Controller
@ParentPackage("basemanage")
@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
public class BaseManageAction extends BaseAction implements Serializable {

    private static final long serialVersionUID = 4939231597590140072L;

    @Resource(name = "baseService")
    private BaseService baseService;
    @Resource(name = "toolUtil")
    private ToolUtil toolUtil;

    private String className;// 类名首字母小写
    private String packageName;
    private String entityPacage = "com.lingnet.mec.entity.";// 实体类包
    private String childPackage;// 子包
    private String inputName;

    /**
     * 列表页
     * 
     * @return
     */
    public String list() {
        try {
            this.getRequest().getRequestDispatcher("/jsp/" + packageName + "/" + getStr(className) + "_list.jsp").forward(getRequest(), getResponse());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "list";
    }

    /**
     * 获取输入码
     * 
     * @Title: blur
     * @return String
     * @author luls
     * @since 2016-8-3 V 1.0
     */
    public String getBlurData() {
        return ajax(Status.success, toolUtil.getGuihuaHeadChar(inputName).toUpperCase());
    }

    /**
     * 列表数据
     */
    public String getListData() {
        String json = "";
        // System.out.println(searchData);
        try {
            Class c = Class.forName(getClass(className));
            List resultList = baseService.findPager(c, pager).getResult();
            HashMap<Object, Object> result = new HashMap<Object, Object>();
            result.put("data", resultList);
            result.put("total", pager.getTotalCount());
            json = JsonUtil.Encode(result);
            // json = JsonUtil.Encode(ReflectionUtil.getMapList(resultList));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ajax(Status.success, json);
        // try {
        // Class c=Class.forName(getClass(className));
        // List resultList = this.getAllList(c);
        // json = JsonUtil.Encode(ReflectionUtil.getMapList(resultList));
        // }catch (Exception e) {
        // e.printStackTrace();
        // }
        // return ajax(Status.success,json);
    }

    /**
     * 新增页
     */
    public String add() {
        try {
            this.getRequest().getRequestDispatcher("/jsp/" + packageName + "/" + getStr(className) + "_add.jsp").forward(getRequest(), getResponse());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "add";
    }

    /**
     * 编辑页
     */
    public String edit() {
        try {
            Class c = Class.forName(getClass(className));
            HttpServletRequest request = getRequest();
            request.setAttribute(className, getBeanById(c, id));
            String a = "/jsp/" + packageName + "/" + getStr(className) + "_add.jsp";
            request.getRequestDispatcher(a).forward(getRequest(), getResponse());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "edit";
    }

    /**
     * 保存更新
     */
    public String saveOrUpdate() {
        try {
            Class c = Class.forName(getClass(className));
            // 当前登录用户Id
            String currentUserId = getBeanByCriteria(QxUsers.class, Restrictions.eq("username", LingUtil.userName())).getId();
            // 将json转换为MAP
            Map<String, String> data = JsonUtil.parseProperties(formdata);
            String id = data.get("id");
            // save
            if (StringUtils.isEmpty(id)) {
                // 创建人(即使c没有该字段也可以)
                data.put("creater", currentUserId);
                String name = data.get("name");

                if (!StringUtils.isEmpty(name)) {
                    try {
                        data.put("input_code", ToolUtil.getGuihuaHeadChar(name));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // 锁定状态
                data.put("status", "1");
                saveObj(JsonUtil.toObject(JsonUtil.Encode(data), c));
            } else {// update
                Map<String, String> updateData = JsonUtil.parseProperties(JsonUtil.toJson(getBeanByCriteria(c, Restrictions.eq("id", id))));
                // 修改人
                String name = data.get("name");

                if (!StringUtils.isEmpty(name)) {
                    try {
                        data.put("input_code", ToolUtil.getGuihuaHeadChar(name));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                updateData.put("modifyer", currentUserId);
                for (String key : data.keySet()) {
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
    public String remove() {
        try {
            Class c = Class.forName(getClass(className));
            List<Object> objs = getBeanListByCriteria(c, Restrictions.in("id", ids.split(",")));
            for (Object obj : objs) {
                if ("1".equals(JsonUtil.parseProperties(JsonUtil.toJson(obj)).get("status"))) {
                    return ajax(Status.success, "存在已锁定的记录，请解锁以后再删除");
                }
            }
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
    public String lock() {
        try {
            Class c = Class.forName(getClass(className));
            List<Object> objs = new ArrayList<Object>();
            // PropertyDescriptor pd = new PropertyDescriptor("status", c);
            // Method wM = pd.getWriteMethod();
            for (String id : ids.split(",")) {
                JSONObject jo = new JSONObject().fromObject(JsonUtil.Encode(getBeanByCriteria(c, Restrictions.eq("id", id))));
                jo.put("status", "1");
                // Object bean=getBeanByCriteria(c,Restrictions.eq("id",id));
                // Map<String,String> map=JsonUtil.parseProperties(
                // JsonUtil.Encode(getBeanByCriteria(c,Restrictions.eq("id",id))));
                // map.put("status", "1");
                // wM.invoke(bean, "1");
                // objs.add(JsonUtil.toObject(JsonUtil.Encode(map), c));
                objs.add(jo.toBean(jo, c));
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
    public String unlock() {
        try {
            Class c = Class.forName(getClass(className));
            List<Object> objs = new ArrayList<Object>();
            for (String id : ids.split(",")) {
                JSONObject jo = new JSONObject().fromObject(JsonUtil.Encode(getBeanByCriteria(c, Restrictions.eq("id", id))));
                jo.put("status", "0");
                objs.add(jo.toBean(jo, c));
                // Map<String,String> map=JsonUtil.parseProperties(
                // JsonUtil.Encode(getBeanByCriteria(c,Restrictions.eq("id",id))));
                // map.put("status", "0");
                // objs.add(JsonUtil.toObject(JsonUtil.Encode(map), c));
            }
            baseService.updateBatch(objs);
            return ajax(Status.success, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return ajax(Status.success, "错误");
        }
    }

    /**
     * 弹窗页
     */
    public void show() {
        try {
            this.getRequest().getRequestDispatcher("/jsp/" + packageName + "/" + getStr(className) + "_show.jsp").forward(getRequest(), getResponse());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 弹窗数据
     */
    public String getShowData() {
        String json = "";
        try {
            Class c = Class.forName(getClass(className));
            List resultList = baseService.findPager(c, pager, Restrictions.eq("status", 1)).getResult();// 锁定
            HashMap<Object, Object> result = new HashMap<Object, Object>();
            result.put("data", resultList);
            result.put("total", pager.getTotalCount());
            json = JsonUtil.Encode(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ajax(Status.success, json);
    }

    /**
     * 弹窗数据 没有锁定
     */
    public String getShowData2() {
        String json = "";
        try {
            Class c = Class.forName(getClass(className));
            List resultList = baseService.findPager(c, pager).getResult();
            HashMap<Object, Object> result = new HashMap<Object, Object>();
            result.put("data", resultList);
            result.put("total", pager.getTotalCount());
            json = JsonUtil.EncodeDate(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ajax(Status.success, json);
    }

    /** 获取下拉框数据 */
    public String getBoxData() {
        String json = "";
        try {
            Class c = Class.forName(getClass(className));
            List<Object> result = getBeanListByCriteria(c, Restrictions.eq("status", 1));
            json = JsonUtil.Encode(result);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ajax(Status.success, json);
    }

    /** 获取下拉框数据 */
    public String getBoxData2() {
        String json = "";
        try {
            Class c = Class.forName(getClass(className));
            List<Object> result = getBeanListByCriteria(c);
            json = JsonUtil.Encode(result);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ajax(Status.success, json);
    }

    /** 获取JSP名 */
    public String getStr(String s) {
        StringBuffer result = new StringBuffer(s);
        char[] chars = s.toCharArray();
        int temp;
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            temp = (int) chars[i];
            if (temp <= 90 && temp >= 65) {
                result = result.insert(i + count, "_");
                count++;
            }
        }
        return result.toString().toLowerCase();
    }

    /** 获取完整类名 */
    public String getClass(String className) {
        if (null != childPackage && !childPackage.equals(""))
            entityPacage += childPackage + ".";
        return entityPacage + className.substring(0, 1).toUpperCase() + className.substring(1);
    }

    // /////////getter and setter/////////////////
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

    public String getChildPackage() {
        return childPackage;
    }

    public void setChildPackage(String childPackage) {
        this.childPackage = childPackage;
    }

    public String getInputName() {
        return inputName;
    }

    public void setInputName(String inputName) {
        this.inputName = inputName;
    }

}
