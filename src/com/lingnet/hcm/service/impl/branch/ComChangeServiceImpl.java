package com.lingnet.hcm.service.impl.branch;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.branch.ComChangeDao;
import com.lingnet.hcm.dao.branch.OrgHisMiddleDao;
import com.lingnet.hcm.entity.ComChange;
import com.lingnet.hcm.entity.DeptChange;
import com.lingnet.hcm.entity.OrgChangeLog;
import com.lingnet.hcm.entity.OrgHisMiddle;
import com.lingnet.hcm.entity.check.CkOvertimeSub;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.service.branch.ComChangeService;
import com.lingnet.qxgl.dao.BranchDao;
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.qxgl.entity.QxDepartment;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.LingUtil;
import com.lingnet.util.Pager;
import com.lingnet.util.ReflectionUtil;

@Service("comChangeService")
public class ComChangeServiceImpl extends BaseServiceImpl<ComChange, String>
        implements ComChangeService {

    @Resource(name="orgHisMiddleDao")
    private OrgHisMiddleDao orgHisMiddleDao;
    @Resource(name="comChangeDao")
    private ComChangeDao comChangeDao;
    @Resource(name="branchDao")
    private BranchDao branchDao;
    
    @SuppressWarnings({ "rawtypes", "unchecked"})
    @Override
    public String listdata(String id, Pager pager) {
        // 根据
        if(id!=null&&!"".equals(id)){
            pager = this.findPager(OrgHisMiddle.class,pager,Restrictions.eq("changeId", id));
        }else{
            pager = this.findPager(OrgHisMiddle.class,pager);
        }
        
        List<HashMap> mapList = ReflectionUtil.getMapList(pager.getResult());
        for(HashMap map :mapList){
            if(map.get("changeId")!=null){
                Branch branch = this.get(Branch.class, map.get("changeId").toString());
                if(branch!=null){
                    map.put("changeName", branch.getFzx());
                }else{
                    QxDepartment dept = this.get(QxDepartment.class, map.get("changeId").toString());
                    if(dept!=null){
                        map.put("changeName",dept.getName());
                    }
                }
            }
            if(map.get("applyComp")!=null){
                Branch branch = this.get(Branch.class, map.get("applyComp").toString());
                if(branch!=null){
                    map.put("applyCompName", branch.getFzx());
                }else{
                    QxDepartment dept = this.get(QxDepartment.class, map.get("applyComp").toString());
                    if(dept!=null){
                        map.put("applyCompName",dept.getName());
                    }
                }
            }
        }
        return JsonUtil.toJson(mapList);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String saveOrUpdata(String formdata, String img) throws Exception {
        HashMap<String,Object> map = JsonUtil.toObject(formdata,HashMap.class);
        if(map.get("id")!=null&&!"".equals(map.get("id").toString())){
            //修改
            OrgHisMiddle oldBean = this.get(OrgHisMiddle.class, map.get("id").toString());
            if(oldBean!=null){
                oldBean.setApplyComp(map.get("applyComp").toString());
                oldBean.setChangeReason(map.get("changeReason").toString());
                oldBean.setChangeName(map.get("changeName").toString());
                oldBean.setChangeno(map.get("srm").toString() );
                oldBean.setAttachment(img); 
                map.remove("applyComp");
                map.remove("changeName");
                map.remove("changeType");
                map.remove("changeReason");
                map.remove("attachment");
                map.remove("id");
                map.remove("pid");
                if(map.get("changeId")!=null){
                    oldBean.setChangeId(map.get("changeId").toString());
                    map.remove("changeId");
                }else{
                    oldBean.setChangeId(map.get("pid").toString());
                }
                orgHisMiddleDao.update(oldBean);
                //删除相关的子表重新保存
                ComChange oldChange = this.get(ComChange.class, Restrictions.eq("middleId", oldBean.getId()));
                comChangeDao.delete(oldChange);
                
                if(oldBean.getChangeType()==1){
                    formdata = JsonUtil.toJson(map);
                    ComChange comChange = JsonUtil.toObject(formdata, ComChange.class);
                    comChange.setMiddleId(oldBean.getId());
                    Branch branch = this.get(Branch.class, Restrictions.eq("id", oldBean.getChangeId()));
                    //变更 循环所有不为空字段，写成记录。
                    Field[] fields = comChange.getClass().getDeclaredFields();
                    // 遍历所有属性  
                    for (int j = 0; j < fields.length; j++) { 
                     // 获取属性的名字  
                        String name = fields[j].getName();  
                        // 将属性的首字符大写，方便构造get，set方法  
                        name = name.substring(0, 1).toUpperCase() + name.substring(1);
                        name = name.substring(0, 1).toUpperCase() + name.substring(1);
                        if("SerialVersionUID".equals(name)||"MiddleId".equals(name)){
                            continue;
                        }
                        Method m = comChange.getClass().getMethod("get" + name);
                        Object value = m.invoke(comChange);
                        if(value!=null&&!"".equals(value.toString())){
                            Object oldValue  = branch.getClass().getMethod("get" + name).invoke(branch);
                            OrgChangeLog log = new OrgChangeLog();
                            if(oldValue!=null&&!"".equals(oldValue.toString())){
                                log.setOldValue(oldValue.toString());
                            }
                            log.setNewValue(value.toString());
                            
                            
                        }
                        
                    }
                    comChangeDao.save(comChange);
                }
                
            }
        }else{
            //保存
            OrgHisMiddle middle = new OrgHisMiddle();
            middle.setApplyComp(map.get("applyComp").toString());
            if(map.get("fzx")!=null){
                middle.setChangeName(map.get("fzx").toString());
                map.remove("fzx");
                }
            if(map.get("srm")!=null){
                middle.setChangeno(map.get("srm").toString() );
                map.remove("srm");
                }
            if(map.get("pid")!=null){
                middle.setChangeId(map.get("pid").toString() );
                map.remove("pid");
                }else if(map.get("cid")!=null){
                    middle.setChangeId(map.get("cid").toString() );
                }
            map.remove("applyComp"); 
            middle.setChangeType(Integer.parseInt(map.get("changeType").toString()));
            map.remove("changeType");
            middle.setChangeReason(map.get("changeReason").toString());
            map.remove("changeReason");
            middle.setAttachment(img);
            map.remove("attachment");
            middle.setApplyDate(new Date());
            middle.setType(0);
            middle.setFlg(0);
            middle.setApplyPerson(LingUtil.userName());
            if(map.get("changeId")!=null){
                middle.setChangeId(map.get("changeId").toString());
                map.remove("changeId");
            }
            if(map.get("effectiveId")!=null){
                middle.setEffectiveId(map.get("effectiveId").toString());
            }
            orgHisMiddleDao.save(middle);
            //写入日志
            
            
            
            if(middle.getChangeType()==1){
              //对于剩下的字段归于公司变更
                formdata = JsonUtil.toJson(map);
                ComChange comChange = JsonUtil.toObject(formdata, ComChange.class);
                comChange.setMiddleId(middle.getId());
                Branch branch = this.get(Branch.class, Restrictions.eq("id", middle.getChangeId()));
                //变更 循环所有不为空字段，写成记录。
                Field[] fields = comChange.getClass().getDeclaredFields();
                // 遍历所有属性  
                for (int j = 0; j < fields.length; j++) { 
                 // 获取属性的名字  
                    String name = fields[j].getName();  
                    // 将属性的首字符大写，方便构造get，set方法  
                    name = name.substring(0, 1).toUpperCase() + name.substring(1);
                    name = name.substring(0, 1).toUpperCase() + name.substring(1);
                    if("SerialVersionUID".equals(name)||"MiddleId".equals(name)){
                        continue;
                    }
                    Method m = comChange.getClass().getMethod("get" + name);
                    Object value = m.invoke(comChange);
                    if(value!=null&&!"".equals(value.toString())){
                        Object oldValue  = branch.getClass().getMethod("get" + name).invoke(branch);
                        OrgChangeLog log = new OrgChangeLog();
                        if(oldValue!=null&&!"".equals(oldValue.toString())){
                            log.setOldValue(oldValue.toString());
                        }
                        log.setNewValue(value.toString());
                        
                        
                    }
                    
                }
                comChangeDao.save(comChange);
            }
            
          //写入日志
        }
        return "success";
    }

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String deleteByIds(String id) throws Exception {
        String[] ids = id.split(",");
        for(int i=0;i<ids.length;i++){
            OrgHisMiddle oldBean = this.get(OrgHisMiddle.class, ids[i]);
            if(oldBean.getFlg()!=0&&oldBean.getFlg()!=3){
                throw new  Exception("当前申请单不允许删除！");
            }
            ComChange oldChange = this.get(ComChange.class, Restrictions.eq("middleId", oldBean.getId()));
            comChangeDao.delete(oldChange);
            orgHisMiddleDao.delete(oldBean);
        }
        
        
        return "success";
    }

    @Override
    public String changeSubmit(String id) throws Exception {
        String[] ids = id.split(",");
        for(int i=0;i<ids.length;i++){
            OrgHisMiddle oldBean = this.get(OrgHisMiddle.class, ids[i]);
            if(oldBean.getFlg()!=0&&oldBean.getFlg()!=3){
                throw new  Exception("当前申请单不需要提交！");
            }
            oldBean.setFlg(1);
            orgHisMiddleDao.update(oldBean);
        }
        
        
        return "success";
    }

    @Override
    @Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String audit(String formdata) throws Exception {
        CkOvertimeSub sub = JsonUtil.toObject(formdata, CkOvertimeSub.class);
        OrgHisMiddle middle = this.get(OrgHisMiddle.class, Restrictions.eq("id", sub.getNodeId()));
        comChangeDao.save(sub);//保存审核记录
        if(sub.getAuditStatus()==0){
            //驳回
            middle.setFlg(3);
            middle.setAuditDes(sub.getOpinion());
        }else{
            //审核通过
            middle.setFlg(2);
            middle.setAuditDes(sub.getOpinion());
            middle.setApplyDate(new Date());
            //判断变更类型
            if(middle.getType()==0){
                //公司
                if ("0".equals(middle.getChangeType()+"")) {
                    //设立
                    Branch branch = new Branch();
//                    ComChange comChange = this.get(ComChange.class,
//                            Restrictions.eq("middleId", middle.getId()));
//                    branch.copyFrom(comChange);
                    branch.setFzx(middle.getChangeName());
                    branch.setSrm(middle.getChangeno());
                    branch.setPid(middle.getChangeId());
                    branchDao.save(branch);
                } else if ("1".equals(middle.getChangeType()+"")) {
                    //变更
                    ComChange comChange = this.get(ComChange.class,
                            Restrictions.eq("middleId", middle.getId()));
                    Branch branch = this.get(Branch.class,
                            Restrictions.eq("id", middle.getChangeId()));
                    branch.copyFrom(comChange);
                    branchDao.update(branch);
                } else if ("2".equals(middle.getChangeType()+"")) {
                    //转移--设置公司的上层为要转移过去的公司或者部门
                    Branch branch = this.get(Branch.class,
                            Restrictions.eq("id", middle.getChangeId()));
                    Branch pid = this.get(Branch.class,
                            Restrictions.eq("id", middle.getEffectiveId()));
                    if(pid==null){
                        throw new Exception("要转移的上级公司不存在！");
                    }
                    branch.setPid(pid.getId());
                    branchDao.update(branch);
                } else if ("3".equals(middle.getChangeType()+"")) {
                    //撤销--检查公司下面是否有人员
                    List<BasicInformation> infos = this.getList(BasicInformation.class, Restrictions.eq("filmId", middle.getChangeId()));
                    if(infos.size()>0){
                        throw new Exception("部门下面有人员存在，请先进行人员转移！");
                    }
                    Branch branch = this.get(Branch.class,
                            Restrictions.eq("id", middle.getChangeId()));
                    branch.setIsDelete(2);//撤销
                    
                    branchDao.update(branch);
                }
            }else{
                //部门
                if ("0".equals(middle.getChangeType()+"")) {
                    //设立
                    QxDepartment branch = new QxDepartment();
                    DeptChange comChange = this.get(DeptChange.class,
                            Restrictions.eq("middleId", middle.getId()));
                    branch.copyFrom(comChange);
                    branchDao.save(branch);
                } else if ("1".equals(middle.getChangeType()+"")) {
                    //变更
                    DeptChange comChange = this.get(DeptChange.class,
                            Restrictions.eq("middleId", middle.getId()));
                    QxDepartment branch = this.get(QxDepartment.class,
                            Restrictions.eq("id", middle.getChangeId()));
                    branch.copyFrom(comChange);
                    branchDao.update(branch);
                } else if ("2".equals(middle.getChangeType()+"")) {
                    //转移
                    QxDepartment branch = this.get(QxDepartment.class,
                            Restrictions.eq("id", middle.getChangeId()));
                    QxDepartment pid = this.get(QxDepartment.class,
                            Restrictions.eq("id", middle.getEffectiveId()));
                    if(pid==null){
                        throw new Exception("要转移的上级公司不存在！");
                    }
                    branch.setParent(pid.getId());
                    branchDao.update(branch);
                } else if ("3".equals(middle.getChangeType()+"")) {
                    //撤销
                    List<BasicInformation> infos = this.getList(BasicInformation.class, Restrictions.eq("departId", middle.getChangeId()));
                    if(infos.size()>0){
                        throw new Exception("部门下面有人员存在，请先进行人员转移！");
                    }
                    QxDepartment branch = this.get(QxDepartment.class,
                            Restrictions.eq("id", middle.getChangeId()));
                    if(branch==null){ 
                        branch = this.get(QxDepartment.class,
                            Restrictions.eq("id", middle.getApplyComp()));
                    }
                    branch.setIsDelete(2);//撤销
                    
                    branchDao.update(branch);
                } else if ("4".equals(middle.getChangeType()+"")) {
                    //合并
                    List<QxDepartment> infos = this.getList(QxDepartment.class, Restrictions.eq("parent", middle.getChangeId()));
                    for(QxDepartment info:infos){
                        info.setParent(middle.getEffectiveId());
                        branchDao.update(info);
                    }
                    
                }
            }
            
            
        }
        middle.setApplyPerson(LingUtil.userName());
        middle.setEffectiveDate(new Date());
        branchDao.update(middle);
        return "success";
    }
    

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public String auditData(String id, Pager pager) {
        // 根据
        if(id!=null&&!"".equals(id)){
            pager = this.findPager(OrgHisMiddle.class,pager,Restrictions.eq("changeId", id),Restrictions.eq("flg", 1));
        }else{
            pager = this.findPager(OrgHisMiddle.class,pager,Restrictions.eq("flg", 1));
        }
        
        List<HashMap> mapList = ReflectionUtil.getMapList(pager.getResult());
        for(HashMap map :mapList){
            if(map.get("changeId")!=null){
                Branch branch = this.get(Branch.class, map.get("changeId").toString());
                if(branch!=null){
                    map.put("changeName", branch.getFzx());
                }else{
                    QxDepartment dept = this.get(QxDepartment.class, map.get("changeId").toString());
                    if(dept!=null){
                        map.put("changeName",dept.getName());
                    }
                }
            }
            if(map.get("applyComp")!=null){
                Branch branch = this.get(Branch.class, map.get("applyComp").toString());
                if(branch!=null){
                    map.put("applyCompName", branch.getFzx());
                }else{
                    QxDepartment dept = this.get(QxDepartment.class, map.get("applyComp").toString());
                    if(dept!=null){
                        map.put("applyCompName",dept.getName());
                    }
                }
            }
        }
        return JsonUtil.toJson(mapList);
    }
    
    
    
}
