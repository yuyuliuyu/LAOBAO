package com.lingnet.hcm.dao.impl.branch;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.dao.branch.ComChangeDao;
import com.lingnet.hcm.entity.ComChange;
@Repository("comChangeDao")
public class ComChangeDaoImpl extends BaseDaoImpl<ComChange,String> implements
        ComChangeDao {

}
