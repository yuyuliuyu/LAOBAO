package com.lingnet.qxgl.service.impl; 
import java.util.Map;

import javax.annotation.Resource; 

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service; 

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.entity.WorkFlowChild;
import com.lingnet.qxgl.dao.WorkFlowChildDao;
import com.lingnet.qxgl.service.WorkFlowService;

@Service("workflowservice")
public class WorkFlowServiceImpl extends BaseServiceImpl<String, WorkFlowChild> implements WorkFlowService{

    @Resource(name = "workFlowChildDao")
    private WorkFlowChildDao workflowdao;
    @Override
    public WorkFlowChild findnextbyid(String id) { 
        WorkFlowChild childid=new WorkFlowChild();
        childid=this.get(WorkFlowChild.class,Restrictions.eq("id", id));
        int sort=(childid.getSort()+1);
        WorkFlowChild returnchildid=new WorkFlowChild();
        returnchildid=this.get(WorkFlowChild.class
                , Restrictions.eq("pid", childid.getPid())
                , Restrictions.eq("sort",sort)); 
        return returnchildid;
    }

    @Override
    public Integer findmaxbypid(String pid) { 
        Integer maxnum=0;  
        maxnum=workflowdao.findmaxbypid(pid);
        if(maxnum==null){
            maxnum=1;
        }
        return maxnum;
    }

    @Override
    public Map<String, Object> getNextCheckDept(String pid, String nodeid) {
        return workflowdao.getNextCheckDept(pid, nodeid);
    }

}
