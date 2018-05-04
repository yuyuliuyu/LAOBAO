package com.lingnet.hcm.dao.impl.post;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.dao.post.DeptPositionDao;
import com.lingnet.hcm.entity.DeptPosition;
@Repository("deptPositionDao")
public class DeptPositionDaoImpl extends BaseDaoImpl<DeptPosition,String> implements
        DeptPositionDao {

}
