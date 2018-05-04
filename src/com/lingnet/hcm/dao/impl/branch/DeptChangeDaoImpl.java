package com.lingnet.hcm.dao.impl.branch;

import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.dao.branch.DeptChangeDao;
import com.lingnet.hcm.entity.DeptChange;
/**
 * 
 * @ClassName: DeptChangeDaoImpl 
 * @Description: 部门变更Dao
 * @author duanjj
 * @date 2017年4月20日 上午10:40:46 
 *
 */
@Repository("deptChangeDao")
public class DeptChangeDaoImpl extends BaseDaoImpl<DeptChange,String> implements
        DeptChangeDao {

}
