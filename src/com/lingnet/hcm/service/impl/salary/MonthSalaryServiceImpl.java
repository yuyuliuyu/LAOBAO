package com.lingnet.hcm.service.impl.salary;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.entity.salary.MonthSalary;
import com.lingnet.hcm.service.salary.MonthSalaryService;

/**
 * 维护员工月平均工资
 * @ClassName: MonthSalaryServiceImpl 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年5月28日 下午3:54:48 
 *
 */
@Service("monthSalaryService")
public class MonthSalaryServiceImpl extends BaseServiceImpl<MonthSalary, String>
              implements MonthSalaryService {
}
