package com.lingnet.hcm.dao.impl.check;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.dao.check.AnnualLeaveRecordDao;
import com.lingnet.hcm.entity.check.CkAnnualLeave;
/**
 * 
 * @ClassName: AnnualLeaveRecordDaoImpl 
 * @Description: 员工年假休假记录Dao实现类 
 * @author wangqiang
 * @date 2017年4月24日 下午5:21:22 
 *
 */
@Repository("annualLeaveRecordDao")
public class AnnualLeaveRecordDaoImpl extends BaseDaoImpl<CkAnnualLeave, String> implements AnnualLeaveRecordDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<CkAnnualLeave> getRecordByJobNumbers(List<String> jobNumbers, String year) {
		return this.getSession().createCriteria(CkAnnualLeave.class)
				.add(Restrictions.in("jobNumber", jobNumbers))
				.add(Restrictions.eq("yearCalendar", Integer.parseInt(year)))
				.addOrder(Order.asc("jobNumber")).addOrder(Order.asc("monthCalendar")).list();
	}
	
	
}
