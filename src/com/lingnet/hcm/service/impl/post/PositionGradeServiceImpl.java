package com.lingnet.hcm.service.impl.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lingnet.common.action.BaseAction.Status;
import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.post.PositionGradeDao;
import com.lingnet.hcm.entity.PositionGrade;
import com.lingnet.hcm.service.post.PositionGradeService;
import com.lingnet.qxgl.entity.Branch;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;
import com.lingnet.util.ReflectionUtil;

@Service("positionGradeService")
public class PositionGradeServiceImpl extends BaseServiceImpl<PositionGrade,String> implements
        PositionGradeService {

    @Resource(name="positionGradeDao")
    private PositionGradeDao positionGradeDao;
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public String positionList(String id, Pager pager) {
        // 根据
        if(id!=null&&!"".equals(id)){
            pager = this.findPager(PositionGrade.class,pager, Restrictions.eq("companyId", id), Restrictions.eq("isDelete", 0));
        }else{
            pager = this.findPager(PositionGrade.class,pager, Restrictions.eq("isDelete", 0));
        }
        List<HashMap> mapList = ReflectionUtil.getMapList(pager.getResult());
        for(HashMap map :mapList){
            if(map.get("companyId")!=null&&!"".equals(map.get("companyId").toString())){
                Branch branch = this.get(Branch.class, map.get("companyId").toString());
                if(branch!=null){
                    map.put("fzx", branch.getFzx());
                }
                
            }
        }

        Map result = new HashMap();
        result.put("data", mapList);
        result.put("total", pager.getTotalCount()); 
        return JsonUtil.toJson(result);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,
    propagation = Propagation.REQUIRED)
    public String deleteGrades(String id) throws Exception {
        String[] ids = id.split(",");
        for (int i = 0, l = ids.length; i < l; i++) {
            PositionGrade b = this.get(PositionGrade.class,
                    Restrictions.eq("id", ids[i]),
                    Restrictions.eq("isDelete", 0));
            if (b != null) {
                b.setIsDelete(1);
                this.update(b);
            }
        }
        return "success";
    }

    @Override
    @Transactional(rollbackFor = Exception.class,
    propagation = Propagation.REQUIRED)
    public String saveOrUpdate(String formdata) throws Exception {
        PositionGrade grade = JsonUtil.toObject(formdata, PositionGrade.class);
        if (grade.getId() != null && !"".equals(grade.getId())) {
            PositionGrade oldBean = this.get(PositionGrade.class,grade.getId());
            oldBean.copyFrom(grade);
            positionGradeDao.update(oldBean);
        }else{
            PositionGrade bean = this.get(PositionGrade.class,Restrictions.eq("name",
                    grade.getName()),
                            Restrictions.eq("positionLayer",
                                    grade.getPositionLayer()));
            if (bean != null) {
                throw new Exception("系统已经存在同名的职务族！");
            }
            grade.setIsDelete(0);
            positionGradeDao.save(grade);
        }
        return "success";
    }

}
