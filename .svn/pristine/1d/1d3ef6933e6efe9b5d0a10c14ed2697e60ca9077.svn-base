package com.lingnet.hcm.dao.impl.check;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lingnet.common.dao.impl.BaseDaoImpl;
import com.lingnet.hcm.dao.check.YearDayDao;
import com.lingnet.hcm.entity.check.CkYearDay;
/**
 * 
 * @ClassName: YearDayDaoImpl 
 * @Description: 全年白班班制Dao实现类 
 * @author wangqiang
 * @date 2017年4月14日 下午1:54:23 
 *
 */
@Repository("yearDayDao")
public class YearDayDaoImpl extends BaseDaoImpl<CkYearDay, String> implements YearDayDao{

	@Override
	public CkYearDay getDayInfoByCond(String thisYear, String thisMonth) {
		return (CkYearDay) this.getSession().createCriteria(CkYearDay.class)
				.add(Restrictions.eq("yearCalendar", thisYear))
				.add(Restrictions.eq("monthCalendar", thisMonth)).uniqueResult();
	}
	
}
