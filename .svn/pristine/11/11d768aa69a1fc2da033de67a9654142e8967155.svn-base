package com.lingnet.qxgl.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.entity.WorkFlowChild;
import com.lingnet.qxgl.dao.WorkFlowChildDao;
/**
 * 
 * @ClassName: WorkFlowChildDaoImpl 
 * @Description: 工作流节点Dao实现类 
 * @author wangqiang
 * @date 2017年5月3日 上午11:19:18 
 *
 */
@Repository("workFlowChildDao")
public class WorkFlowChildDaoImpl extends BaseDaoImpl<WorkFlowChild, String> implements WorkFlowChildDao{

    @Override
    public Integer findmaxbypid(String pid) {
        String sql = "select max(sort) from jc_work_flowc a where pid='"+pid+"'";
         Integer num=0;
         try {
             BigDecimal num1s= (BigDecimal) this.getSession().createSQLQuery(sql).uniqueResult();
             num=num1s.intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public Map<String, Object> getNextCheckDept(String pid, String nodeid) {
        StringBuilder nextSql = new StringBuilder();
        nextSql.append("   SELECT I.*                                           ");
        nextSql.append("   FROM                                                 ");
        nextSql.append("     (SELECT T.ID,                                      ");
        nextSql.append("       LEAD(T.ID,1,0) OVER(ORDER BY T.SORT ASC) NEXTS,  ");
        nextSql.append("       LEAD(T.APRO_ID,1,0) OVER(ORDER BY T.SORT ASC) APRO_ID   ");
        nextSql.append("     FROM JC_WORK_FLOWC T                               ");
        nextSql.append("     WHERE T.PID = '"+pid+"'                            ");
        nextSql.append("     ) I                                                ");
        nextSql.append("   WHERE I.ID='"+nodeid+"'                              ");// 上一个的节点ID

        List<?> list = findBySql(nextSql.toString());
        Map<String, Object> map = new HashMap<String, Object>();
        if (list.size() > 0) {
            Object[] obj = (Object[]) list.get(0);
            map.put("id", obj[0]);
            map.put("nextId", obj[1]);
            map.put("aproId", obj[2]);
        }

        return map;
    }
	
}
