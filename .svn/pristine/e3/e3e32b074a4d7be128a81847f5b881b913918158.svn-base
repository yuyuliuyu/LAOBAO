package com.lingnet.hcm.action.train;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.common.action.BaseAction.Status;
import com.lingnet.hcm.entity.basic.TableColumInfo;
import com.lingnet.hcm.service.train.TableColumService; 
import com.lingnet.util.JsonUtil;
/**
 * 动态表单展示
 * @ClassName: TableColumAction 
 * @Description: TODO 
 * @author 马晓鹏
 * @date 2017年3月24日 上午10:45:46 
 *
 */
public class TableColumAction extends BaseAction{
    private static final long serialVersionUID = 1L;
    @Resource(name = "tablecolumservice")
    private TableColumService tableService;

    private String id;              //主键id
    private String pid;              //主键id
    private String jsondata;        //页面获取的json数据
    private String state;           //状态标志位
    private String ids;             //批量操作的id数组
    private TableColumInfo tableinfo;
    private List<TableColumInfo> showlist=new ArrayList<TableColumInfo>();
    private String tablename;
    /**
     * 测试版本，导出数据
     * @Title: exporttest 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年4月7日 V 1.0
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String exporttest(){ 
      HttpServletResponse resp = getResponse(); 
      
      List<TableColumInfo> exportlist=new ArrayList<TableColumInfo>();
      exportlist=this.getBeanListByCriteria(TableColumInfo.class, Restrictions.eq("tablename", "动态表头"));  //获得要导出的数据集合
     /* 将需要导出的数据集合，转化成map，注意：map的key值对应动态表头设置里面填写的数据库字段名。*/
      List<Map> objlist=new ArrayList<Map>();
      for (int i = 0; i < exportlist.size(); i++) { 
          Map infomap=new HashMap();
        infomap.put("tablename", exportlist.get(i).getTablename()+"");
        infomap.put("columname", exportlist.get(i).getColumname()+"");
        infomap.put("chinaname", exportlist.get(i).getChinaname()+"");
        infomap.put("columlength", exportlist.get(i).getColumlength()+"");
        infomap.put("columstate", exportlist.get(i).getColumstate()+"");
        infomap.put("pid", exportlist.get(i).getPid());
        infomap.put("columtype",exportlist.get(i).getColumtype()+"");   
        infomap.put("id", exportlist.get(i).getId()+"");
        infomap.put("ptype", exportlist.get(i).getPtype()+"");
        infomap.put("listsort", exportlist.get(i).getListsort()+"");
        infomap.put("exportsort", exportlist.get(i).getExportsort()+"");
        objlist.add(infomap);
    }  
      
      /*固定写法，将service生成的excell实体类流化输出*/
      try {
          HSSFWorkbook hwb = tableService.excellmap(objlist,"动态表头",new Integer[]{4,2,3});  
          resp.setContentType("application/x-msdownload");
          resp.setHeader("Content-Disposition", "attachment;filename="
                  + URLEncoder.encode("处理处罚统计", "UTF-8") + ".xls");
          OutputStream out = resp.getOutputStream();
          hwb.write(out);
//          out.flush();
          out.close();
      } catch (Exception e) {
          e.printStackTrace();
      } 
        return ajax(Status.success,"导出成功");
    } 
    
    
    /**
     * 批量设置动态表头
     * @Title: setcolums 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年4月7日 V 1.0
     */
    public String setcolums(){
        if(ids==null||"".equals(ids.trim())){
            return ajax(Status.success,"设置失败");
        }
        String columid[]=ids.split(",");
        for (int i = 0; i < columid.length; i++) {
            if(columid[i]==null||"".equals(columid[i].trim())){
                continue;
            }
            tableinfo=this.getBeanById(TableColumInfo.class, columid[i]);
            tableinfo.setColumstate(Integer.parseInt(state.trim())); 
            this.update(tableinfo);
        }
        return ajax(Status.success,"设置成功");
    }
    /**
     * 列表页面展现动态表头调整页面
     * @Title: list 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String setcolum(){
        showlist=tableService.getshowcolum("动态表头", new Integer[]{4,2,3});  
        return "test";
    }
    /**
     * 列表数据获取
     * @Title: listjson 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String testjson(){ 
        List<TableColumInfo> tbinfolist=this.getBeanListByCriteria(TableColumInfo.class,
                Restrictions.eq("tablename", tablename),
                Restrictions.ne("pid", "0"));
        return ajax(Status.success,JsonUtil.toJson(tbinfolist));
    }
    
    /**
     * 列表页面展现
     * @Title: list 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    @SuppressWarnings("unchecked")
    public String list(){
        showlist=tableService.getshowcolum("动态表头",new Integer[]{4,2,3});
        
        return "list";
    }
    /**
     * 列表数据获取
     * @Title: listjson 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String listjson(){
        if(id==null||"".equals(id.trim())){
            return ajax(Status.success,"");
        }
        List<TableColumInfo> tbinfolist=this.getBeanListByCriteria(TableColumInfo.class,Restrictions.eq("pid",id ));

        Map result = new HashMap();
        result.put("data", tbinfolist);
        result.put("total", pager.getTotalCount());
        String json = JsonUtil.Encode(result);
        return ajax(Status.success,json); 
//        return ajax(Status.success,JsonUtil.toJson(tbinfolist));
    }
    /**
     * 数菜单
     * @Title: treejson 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月29日 V 1.0
     */
    public String treejson(){
        List<TableColumInfo> tbinfolist=null;
        try {
            tbinfolist=this.getBeanListByCriteria(TableColumInfo.class,Restrictions.eq("pid", "0"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>(); 
        if(tbinfolist!=null&&tbinfolist.size()>0){
            for(TableColumInfo b : tbinfolist){
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("id", b.getId());
                map.put("pid",b.getPid());
                map.put("name", b.getChinaname()); 
                map.put("branchId", b.getId());
                map.put("img", "../jsp/basis/company.gif");
                list.add(map);
            }
        }
        return ajax(Status.success,JsonUtil.toJson(list));
    }
    /**
     * 获取上级菜单
     * @Title: getpid 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月29日 V 1.0
     */
    public String getpid(){
        List<TableColumInfo> tbinfolist=null;
        try {
            tbinfolist=this.getBeanListByCriteria(TableColumInfo.class,Restrictions.eq("pid", pid),Restrictions.isNull("ptype")); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>(); 
        if(tbinfolist!=null&&tbinfolist.size()>0){
            for(TableColumInfo b : tbinfolist){
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("id", b.getId()); 
                map.put("text", b.getChinaname());  
                list.add(map);
            }
        }
        return ajax(Status.success,JsonUtil.toJson(list));
    }
    /**
     * 增加页面展现
     * @Title: add 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String add(){
        if(pid==null||"".equals(pid.trim())){
            return "error";
        }
        if(id==null||"".equals(id.trim())){
            tableinfo=new TableColumInfo();
            tableinfo.setPid(pid);
        }else{
            tableinfo=this.getBeanById(TableColumInfo.class,id);
        }
        return "add";
    }
    /**
     * 保存修改方法
     * @Title: saveorupdate 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    public String saveorupdate(){
        if(jsondata==null||jsondata==""){
            return ajax(Status.success,"数据错误请联系管理员");
        }else{
            tableinfo=JsonUtil.toObject(jsondata, TableColumInfo.class);
        }
        if("0".equals(tableinfo.getPid().trim())){ 
        }else{ 
            tableinfo.setTablename(gettablename(tableinfo.getPid()));
        }
        try {
            if(tableinfo.getPtype()==null||"".equals(tableinfo.getPtype().trim())){
                tableinfo.setPtype(null);
            }
            if(tableinfo.getId()==null||"".equals(tableinfo.getId().trim())){
                this.save(tableinfo);
            }else{
                this.update(tableinfo);
            }
        } catch (Exception e) {
            return ajax(Status.success,"保存/修改失败");
        }
        return ajax(Status.success,"保存/修改成功");
    }
    
    public String gettablename(String pid){
        String tablename=""; 
        TableColumInfo tableinfo=this.getBeanByCriteria(TableColumInfo.class,Restrictions.eq("id", pid));
        if("0".equals(tableinfo.getPid().trim())){
            return tableinfo.getChinaname();
        }else{
            gettablename(tableinfo.getPid());
        }
        return tablename;
    }
    /**
     * 删除方法
     * @Title: remove 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */ 
    public String remove(){
        if(id==null||"".equals(id.trim())){
            return ajax(Status.success,"参数错误，请重新操作");
        }
        try {
            if(pid==null||"".equals(pid.trim())){ 
                this.deleteByCriteria(TableColumInfo.class, Restrictions.eq("id", id));
            }else{
                showlist=(List<TableColumInfo>) this.getBeanListByCriteria(TableColumInfo.class,Restrictions.eq("pid", pid));
                if(showlist!=null&&showlist.size()>0){
                    for (int i = 0; i < showlist.size(); i++) {
                        this.deleteByCriteria(TableColumInfo.class,  Restrictions.eq("id", showlist.get(i).getId()));
                    }
                }
                this.deleteByCriteria(TableColumInfo.class, Restrictions.eq("id", id));
            }
        } catch (Exception e) {
            return ajax(Status.success,"删除失败");
        }
        return ajax(Status.success,"删除成功");
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
    public TableColumInfo getTableinfo() {
        return tableinfo;
    }
    public void setTableinfo(TableColumInfo tableinfo) {
        this.tableinfo = tableinfo;
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
}