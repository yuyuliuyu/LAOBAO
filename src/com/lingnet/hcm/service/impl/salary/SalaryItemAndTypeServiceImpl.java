package com.lingnet.hcm.service.impl.salary;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.entity.salary.SalaryItemAndType;
import com.lingnet.hcm.service.salary.SalaryItemAndTypeService;

/**
 * 薪资类别与薪资项目关联表
 * @ClassName: SalaryItemAndTypeServiceImpl 
 * @Description: 薪资类别与薪资项目关联表
 * @author zhanghj
 * @date 2017年3月21日 下午2:46:24 
 *
 */
@Service("salaryItemAndTypeService")
public class SalaryItemAndTypeServiceImpl extends BaseServiceImpl<SalaryItemAndType, String>
              implements SalaryItemAndTypeService {

}
