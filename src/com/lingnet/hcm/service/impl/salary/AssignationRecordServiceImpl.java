package com.lingnet.hcm.service.impl.salary;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.entity.salary.AssignationRecord;
import com.lingnet.hcm.service.salary.AssignationRecordService;

/**
 * 分配过程审批记录
 * @ClassName: AssignationRecordServiceImpl 
 * @Description: 分配过程审批记录 
 * @author zhanghj
 * @date 2017年5月12日 下午4:23:10 
 *
 */
@Service("assignationRecordService")
public class AssignationRecordServiceImpl extends BaseServiceImpl<AssignationRecord, String>
              implements AssignationRecordService {
}
