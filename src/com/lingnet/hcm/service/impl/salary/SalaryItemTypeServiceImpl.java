package com.lingnet.hcm.service.impl.salary;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.entity.salary.SalaryItemType;
import com.lingnet.hcm.service.salary.SalaryItemTypeService;

/**
 * 薪酬类别
 * @ClassName: SalaryItemTypeServiceImpl 
 * @Description: 薪酬类别
 * @author zhanghj
 * @date 2017年3月21日 下午2:46:24 
 *
 */
@Service("salaryItemTypeService")
public class SalaryItemTypeServiceImpl extends BaseServiceImpl<SalaryItemType, String>
              implements SalaryItemTypeService {

}
