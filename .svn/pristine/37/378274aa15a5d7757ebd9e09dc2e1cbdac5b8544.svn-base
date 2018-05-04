package com.lingnet.qxgl.action.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction;
import com.lingnet.common.action.BaseAction.Status;
import com.lingnet.qxgl.entity.WorkBench;
import com.lingnet.util.JsonUtil;

public class WorkBenchAction extends BaseAction{
    private static final long serialVersionUID = 1L;
    private String id;
    private String jsondata; 
    private WorkBench workbench;
    private List<WorkBench> benchlist=new ArrayList<WorkBench>();
    /**
     * 控制台展示信息
     * @Title: show 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年4月13日 V 1.0
     */
    @SuppressWarnings("unchecked")
    public String show(){
        try {
            pager=this.findPager(WorkBench.class, pager,Restrictions.eq("state",1));
        } catch (Exception e) { 
            e.printStackTrace();
        }
         benchlist=(List<WorkBench>)pager.getResult();
        return "show";
    }
    /**
     * 展示列表页面
     * @Title: list 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年4月13日 V 1.0
     */
    public String list(){
        return "list";
    }
    /**
     * 展示列表页面数据
     * @Title: jsonlist 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年4月13日 V 1.0
     */
    @SuppressWarnings("unchecked")
    public String jsonlist(){
        try {
            pager=this.findPagerByOrder(WorkBench.class, pager,Order.desc("state"),Restrictions.isNotNull("id"));
        } catch (Exception e) { 
            e.printStackTrace();
        }   
        Map result = new HashMap();
        benchlist=(List<WorkBench>) pager.getResult();
        List<Map<String, String>> list=new ArrayList<Map<String,String>>();
        for (int i = 0; i < benchlist.size(); i++) {
            Map<String, String> map=new HashMap<String, String>();
            map.put("id", benchlist.get(i).getId());
            map.put("title", benchlist.get(i).getTitle());
            map.put("content", benchlist.get(i).getContent());
            map.put("pageURL", benchlist.get(i).getPageURL());
            map.put("dataURL", benchlist.get(i).getDataURL());
            map.put("memo", benchlist.get(i).getMemo());
            if(benchlist.get(i).getState()==null||benchlist.get(i).getState()==0){
                map.put("state", "停用");
            }else{
                map.put("state", "启用");
            }
            list.add(map);
        }

        result.put("data", list);
        result.put("total", pager.getTotalCount());
        String json = JsonUtil.Encode(result);
        return ajax(Status.success,json); 
    } 
    /**
     * 展示增加页面
     * @Title: add
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年4月13日 V 1.0
     */
    public String add(){
        if(id==null||"".equals(id.trim())){
            workbench=new WorkBench();
        }else{
            workbench=this.getBeanById(WorkBench.class, id);
        }
        return "add";
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
        workbench=this.getBeanById(WorkBench.class, id);
        if(workbench.getState()==null||workbench.getState()==0){
            workbench.setState(1);
            this.update(workbench);
            return ajax(Status.success,"修改成功，当前为启用状态");
        }else{
            workbench.setState(0);
            this.update(workbench);
            return ajax(Status.success,"修改成功，当前为停用状态");
        }
    }
    /**
     *保存增加方法
     * @Title: saveOrUpdate 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年4月13日 V 1.0
     */
    public String saveOrUpdate(){
        if(jsondata==null||"".equals(jsondata.trim())){
            return ajax(Status.success,"数据错误，请查证后重新操作");
        }
        workbench=JsonUtil.toObject(jsondata,WorkBench.class);
        if(workbench==null){
            return ajax(Status.success,"数据错误，请查证后重新操作");
        }else if(workbench.getId()==null||"".equals(workbench.getId().trim())){
            workbench.setState(1);
            this.save(workbench);
        }else{
            this.update(workbench);
        }
        return ajax(Status.success,"保存/修改成功");
    }
    /**
     * 删除方法
     * @Title: remove 
     * @return 
     * String 
     * @author 马晓鹏
     * @since 2017年4月13日 V 1.0
     */
    public String remove(){
        if(id==null||"".equals(id.trim())){
            return ajax(Status.success,"数据错误，删除失败");
        }else{
            this.deleteByCriteria(WorkBench.class,Restrictions.eq("id",id));
        }
        return ajax(Status.success,"删除成功");
    }
    /*----------------------------------------------------------------------------------------------------------------*/
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
    public WorkBench getWorkbench() {
        return workbench;
    }
    public void setWorkbench(WorkBench workbench) {
        this.workbench = workbench;
    }
    public List<WorkBench> getBenchlist() {
        return benchlist;
    }
    public void setBenchlist(List<WorkBench> benchlist) {
        this.benchlist = benchlist;
    }
}
