package com.lingnet.hcm.service.impl.check;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.check.ContentDao;
import com.lingnet.hcm.dao.check.TimerDao;
import com.lingnet.hcm.dao.personnel.PersonnelDao;
import com.lingnet.hcm.entity.check.CkTimer;
import com.lingnet.hcm.entity.person.BasicInformation;
import com.lingnet.hcm.service.check.TimerService;
/**
 * 
 * @ClassName: TimerServiceImpl 
 * @Description: 考勤时间service实现类 
 * @author wangqiang
 * @date 2017年4月11日 下午4:17:36 
 *
 */
@Service("timerService")
public class TimerServiceImpl extends BaseServiceImpl<CkTimer, String> implements TimerService{
	
	@Resource(name = "timerDao")
	private TimerDao timerDao;
	
	@Resource(name="personnelDao")
	private PersonnelDao personnelDao;
	
	@Resource(name = "contentDao")
	private ContentDao contentDao;

	@Override
	public Map<String, String> getListByCond(String jobNumber, String monthCalendar) {
		//获得员工月份的打卡时间
	       BasicInformation basiinfo=new BasicInformation();
	    if(jobNumber==null){
	        return null;
	    }else{
	       basiinfo=personnelDao.get(Restrictions.or(Restrictions.eq("jobNumber", jobNumber), Restrictions.eq("practiceNum", jobNumber))); 
	       if(basiinfo!=null){
	           jobNumber=basiinfo.getCheckNo();
	       }
	    }
		List<CkTimer> timerList = timerDao.getListByCond(jobNumber, monthCalendar);
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
		Map<String, String> map = new HashMap<String, String>();
		int day = 0;
		if (timerList.size() > 0){
			for (int i=0; i <timerList.size(); i++){
				Date thisDate = timerList.get(i).getCheckTime();
				int thisDay = Integer.parseInt(sdf.format(thisDate));
				if (day == 0){
					day = thisDay;
					map.put("startTime"+day, sdfTime.format(thisDate)+"");
				} else {
					if (day == thisDay){
						map.put("endTime"+day, sdfTime.format(thisDate)+"");
					} else {
						if (map.get("startTime"+day) != null && map.get("endTime"+day) == null){
							map.put("endTime"+day, map.get("startTime"+day));
						}
						day = thisDay;
						map.put("startTime"+day, sdfTime.format(thisDate)+"");
					}
				}
			}
		}
		if (map.get("startTime"+day) != null && map.get("endTime"+day) == null){
			map.put("endTime"+day, map.get("startTime"+day));
		}
		return map;
	}

	@Override
	public String getAllListByCond(String startTime, String endTime,
			String jobNumber) {
		return timerDao.getAllListByCond(startTime, endTime, jobNumber);
	}
	
	
}
