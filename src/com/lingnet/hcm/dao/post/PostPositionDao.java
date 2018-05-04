package com.lingnet.hcm.dao.post;

import com.lingnet.common.dao.BaseDao;
import com.lingnet.hcm.entity.PostPosition;
import com.lingnet.util.Pager;

public interface PostPositionDao extends BaseDao<PostPosition, String> {

    public String personList(String id, Pager pager);

}
