package com.lingnet.hcm.service.impl.empdata;

import java.util.HashMap;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lingnet.common.action.BaseAction.Status;
import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.dao.empdata.RecordDao;
import com.lingnet.hcm.entity.person.Record;
import com.lingnet.hcm.service.empdata.RecordService;
import com.lingnet.hcm.service.personnel.PersonnelService;
import com.lingnet.util.JsonUtil;
import com.lingnet.util.Pager;

/**
 * 档案service实现类
 */
@Service("recordService")
public class RecordServiceImpl extends BaseServiceImpl<Record, String> implements RecordService{
	@Resource(name = "recordDao")
	private RecordDao recordDao;
	
	@Resource(name="personnelService")
    private PersonnelService personnelService;
	@Override
	public HashMap getListData(Pager pager, String searchData) {
		String depIds = personnelService.getAllDepIdStrs();
		return recordDao.getListData(pager,searchData,depIds);
	}
	
	/**
	 * 保存数据
	 */
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
    public String saveOrUpdata(String formdata) throws Exception {
		
        Record record=JsonUtil.toObject(formdata, Record.class);
       
        if(record.getId()==null||"".equals(record.getId().trim())){
            this.save(record);
        }else{
        	Record oldInfo=this.get(Record.class, Restrictions.eq("id", record.getId()));
        	if (oldInfo == null) {
                throw new Exception("该条记录不存在");
            }
        	record.setPersonId(oldInfo.getPersonId());
        	oldInfo.copyFrom(record);
            this.update(oldInfo);
        }
        
        return "success";
	}
}
