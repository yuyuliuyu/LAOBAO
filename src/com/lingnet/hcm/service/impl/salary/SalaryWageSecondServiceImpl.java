package com.lingnet.hcm.service.impl.salary;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.entity.salary.SalaryWageSecond;
import com.lingnet.hcm.service.salary.SalaryWageSecondService;

/**
 * 工资条关联副表
 * @ClassName: SalaryWageSecondServiceImpl 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年6月11日 下午9:20:50 
 *
 */
@Service("salaryWageSecondService")
public class SalaryWageSecondServiceImpl extends BaseServiceImpl<SalaryWageSecond, String>
              implements SalaryWageSecondService {
}
