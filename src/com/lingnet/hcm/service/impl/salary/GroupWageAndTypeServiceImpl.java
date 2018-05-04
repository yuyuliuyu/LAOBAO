package com.lingnet.hcm.service.impl.salary;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.entity.salary.GroupWageAndType;
import com.lingnet.hcm.service.salary.GroupWageAndTypeService;

/**
 * 薪资组工资套
 * @ClassName: GroupWageAndTypeService 
 * @Description: 薪资组工资套 
 * @author zhanghj
 * @date 2017年4月5日 下午4:31:50 
 *
 */
@Service("groupWageAndTypeService")
public class GroupWageAndTypeServiceImpl extends BaseServiceImpl<GroupWageAndType, String>
              implements GroupWageAndTypeService {
}
