package com.lingnet.hcm.dao.impl.contract;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.dao.contract.AutoProjectCodeDao;
import com.lingnet.hcm.entity.AutoProjectCode;

@Repository("autoProjectCodeDao")
public class AutoProjectCodeDaoImpl extends BaseDaoImpl<AutoProjectCode, String>
        implements AutoProjectCodeDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<AutoProjectCode> findCode() {
        String Hql = "from AutoProjectCode";
        return getHibernateTemplate().find(Hql);

    }

}
