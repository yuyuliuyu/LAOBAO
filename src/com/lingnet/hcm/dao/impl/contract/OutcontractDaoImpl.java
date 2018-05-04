package com.lingnet.hcm.dao.impl.contract;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.dao.contract.OutcontractDao;
import com.lingnet.hcm.entity.Outcontract;
/**
 * 
 * @ClassName: OutcontractDaoImpl 
 * @Description: 外包合同DAO实现类
 * @author duanjj
 * @date 2017年4月26日 下午4:04:27 
 *
 */
@Repository("outcontractDao")
public class OutcontractDaoImpl extends BaseDaoImpl<Outcontract,String> implements OutcontractDao {

}
