package com.lingnet.hcm.dao.impl.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.dao.post.PostPositionDao;
import com.lingnet.hcm.entity.PostPosition;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

@Repository("postPositionDao")
public class PostPositionDaoImpl extends BaseDaoImpl<PostPosition, String>
        implements PostPositionDao {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public String personList(String id, Pager pager) {

        String sql1 = "select count(id) from jc_basic_information ";
        String sqlin = "";
        String sqlin1 = "";
        if (id != null && !"".equals(id)) {
            String ids = id.replace(",", "','");
            ids = "'" + ids + "'";
            sqlin = " where post_id in (select id from post_position where duty_id in ("
                    + ids + "))";
            sql1 = sql1 + sqlin;
            sqlin1 = " where post_id in (select id from post_position where duty_id in ()) ";
        }

        // 循环数据，返回hashMap
        StringBuffer sql = new StringBuffer();
        sql.append("select i.id,i.job_number,i.name, i.film_name,i.depart_name,i.specific_post,p.duty_name,i.in_port_time,i.on_job from jc_basic_information i  ");
        sql.append(sqlin1);
        sql.append(" left join post_position p on p.id= i.post_id ");
        SQLQuery query1 = this.getSession().createSQLQuery(sql1);
        Integer count = Integer.parseInt(query1.list().get(0).toString());
        SQLQuery query = this.getSession().createSQLQuery(sql.toString());
        Integer pageNumber = pager.getPageNumber();
        pager.setTotalCount(count);
        Integer pageSize = pager.getPageSize();
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<Object[]> list = query.list();
        // 循环数据，返回hashMap
        List<HashMap> mapList = new ArrayList<HashMap>();
        for (Object[] ob : list) {
            HashMap map = new HashMap();
            map.put("id", ob[0]);
            map.put("jobNumber", ob[1]);
            map.put("name", ob[2]);
            map.put("filmName", ob[3]);
            map.put("departName", ob[4]);
            map.put("specificPost", ob[5]);
            map.put("dutyName", ob[6]);
            map.put("inPortTime", ob[7]);
            map.put("onJob", ob[8]);
            mapList.add(map);
        }
        HashMap result = new HashMap();
        result.put("data", mapList);
        result.put("total", pager.getTotalCount());
        return JsonUtil.toJson(result);
    }

}
