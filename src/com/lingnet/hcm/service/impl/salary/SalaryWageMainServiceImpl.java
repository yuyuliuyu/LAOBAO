package com.lingnet.hcm.service.impl.salary;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.entity.salary.SalaryWageMain;
import com.lingnet.hcm.service.salary.SalaryWageMainService;

/**
 * 工资条关联主表
 * @ClassName: SalaryWageMainServiceImpl 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年6月11日 下午9:20:05 
 *
 */
@Service("salaryWageMainService")
public class SalaryWageMainServiceImpl extends BaseServiceImpl<SalaryWageMain, String>
              implements SalaryWageMainService {
}
