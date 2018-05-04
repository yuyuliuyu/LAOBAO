package com.lingnet.hcm.action.train;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Restrictions;

import com.lingnet.common.action.BaseAction; 
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.qxgl.entity.QxDepartment;
import com.lingnet.qxgl.entity.QxUser;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
/**
 * @ClassName: DepartAction 
 * @Description: TODO 
 * @author 马晓鹏
 * @date 2017年3月21日 上午9:13:53
 */
public class DepartAction extends BaseAction{
    private static final long serialVersionUID = 1L;
    
    private String id;              //主键id
    private String jsondata;        //页面获取的json数据
    private String state;           //状态标志位
    private String ids;             //批量操作的id数组
    private List<Branch> banchlist=new ArrayList<Branch>(); 
    public String getbranch(){
        String usernam=LingUtil.userName();
        QxUser userinfo= this.getBeanByCriteria(QxUser.class,Restrictions.eq("username", usernam)); 
        id=userinfo.getCid();
        List<Branch> branchlist=new ArrayList<Branch>();
        Branch ranch=this.getBeanById(Branch.class, id);
        branchlist=this.getBeanListByCriteria(Branch.class, Restrictions.eq("pid", id));
        banchlist.add(ranch);
        banchlist=getchildinfo(branchlist,banchlist);
        List<Map<String, String>> rtmap=new ArrayList<Map<String,String>>();
        for (int i = 0; i < banchlist.size(); i++) {
            Map<String, String> map=new HashMap<String, String>();
            map.put("id", banchlist.get(i).getId());
            map.put("name", banchlist.get(i).getFzx()); 
            rtmap.add(map);
        }
        return ajax(Status.success,JsonUtil.toJson(rtmap));
    }
    /**
     * 获取子公司信息
     * @Title: getchildinfo 
     * @return 
     * List<Branch> 
     * @author 马晓鹏
     * @since 2017年3月23日 V 1.0
     */
    public List<Branch> getchildinfo(List<Branch> brchlist,List<Branch> brchlistrt){
        List<Branch> bchild=new ArrayList<Branch>();
        for (int i = 0; i < brchlist.size(); i++) {
            bchild=this.getBeanListByCriteria(Branch.class, Restrictions.eq("pid", brchlist.get(i).getId()));
            if(bchild!=null&& bchild.size()>0){
                brchlistrt.addAll(bchild); 
                bchild= getchildinfo(bchild,brchlistrt); 
            }
        }
        return brchlistrt;
    }

    @SuppressWarnings("rawtypes")
    public String list(){ 
        if(id==null||"".equals(id)){
            return ajax(Status.success,"请选择一个所属公司");
        } 
      Map allmap= getcompanyinfo(id);
      String returnmap=JsonUtil.toJson(allmap).replace("\"", "'");
    return ajax(Status.success,returnmap);
    }
    /**
     * 展现结构图
     * @Title: list
     * @return
     * 获得所有父为root的列表。
     * @author 马晓鹏
     * @since 2017年3月8日 V 1.0
     */
    @SuppressWarnings({ "unchecked", "rawtypes", "unused" })
    public Map getcompanyinfo(String id ){ 
        /* 根据前台传来的公司id获取公司信息
         * 根据公司信息 
         */ 
        if(id==null||"".equals(id)){
            return null;
        }
        Branch branch=new Branch();
        branch=this.getBeanByCriteria(Branch.class,Restrictions.eq("id", id));
        List<QxDepartment> deptlist_p=new ArrayList<QxDepartment>(); 
        List<QxDepartment> deptlist_c=new ArrayList<QxDepartment>();  
        List<Branch> branchlist_c=new ArrayList<Branch>();  
        List<Map> maplist=new ArrayList<Map>();
        Map retmap=new HashMap();
            retmap.put("id", "1");
            retmap.put("name", branch.getFzx());
            retmap.put("title",branch.getFzx());
            Map<String, String > rlationmap=new HashMap<String, String>();
            rlationmap.put("children_num", "2"); 
            retmap.put("relationship", rlationmap);
            deptlist_p=this.getBeanListByCriteria(QxDepartment.class, Restrictions.eq("barchId",id), Restrictions.eq("isDelete",0));
            branchlist_c=this.getBeanListByCriteria(Branch.class, Restrictions.eq("barchId",id), Restrictions.eq("isDelete",0));
            if(branchlist_c!=null&&branchlist_c.size()>0){
                getcompanyinfo(id);
            }
            if(deptlist_p!=null&&deptlist_p.size()>0){ 
                retmap.put("children",getchild(deptlist_p,2,0,"ROOT",id));
            } 
        return retmap;
    }
    /**
     * 
     * @Title: getchild 
     * @param deptlist
     * @return 
     * Map 
     * @author 马晓鹏
     * @since 2017年3月22日 V 1.0
     */
    @SuppressWarnings({ "unchecked", "unused", "rawtypes" })
    public List<Map> getchild(List<QxDepartment> deptlist,int zj,int child,String parent,String branchid){   
        List<QxDepartment> deptlist_p=new ArrayList<QxDepartment>(); 
        List<QxDepartment> deptlist_c=new ArrayList<QxDepartment>();
        List<Map> childmap=new ArrayList<Map>();
        for (int i = 0; i < deptlist.size(); i++){
            Map retmap=new HashMap();
           //循环写一下，如果有子，则需要写一个children属性，并回调本方法。 
            retmap.put("id", zj);
            zj++;
            retmap.put("name", deptlist.get(i).getName());
            retmap.put("title",deptlist.get(i).getDescription()+"");
            Map<String, String > rlationmap=new HashMap<String, String>();
            deptlist_p=this.getBeanListByCriteria(QxDepartment.class, Restrictions.eq("parent", deptlist.get(i).getId()), Restrictions.eq("isDelete",0));
            rlationmap.put("children_num", "0");
            rlationmap.put("parent_num", (zj-1)+"");
            rlationmap.put("sibling_num", (zj-1)+"");
            retmap.put("relationship", rlationmap);
            if(deptlist_p!=null&&deptlist_p.size()>0){
                rlationmap.put("children_num", "2");
                retmap.put("children", getchild(deptlist_p,zj,child,deptlist.get(i).getId(),null));
            }
            childmap.add(retmap); 
            List<Branch> branchlist=new ArrayList<Branch>(); 
            branchlist=this.getBeanListByCriteria(Branch.class, Restrictions.eq("pid", branchid));
            if(branchlist!=null&&branchlist.size()>0){
                for (int j = 0; j < branchlist.size(); j++) {
                    childmap.add(getcompanyinfo(branchlist.get(j).getId())); 
                }
            }
        }
        return childmap;
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
    public List<Branch> getBanchlist() {
        return banchlist;
    }
    public void setBanchlist(List<Branch> banchlist) {
        this.banchlist = banchlist;
    } 
}
