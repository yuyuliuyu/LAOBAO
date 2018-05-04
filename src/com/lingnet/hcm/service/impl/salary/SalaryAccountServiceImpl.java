package com.lingnet.hcm.service.impl.salary;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.entity.salary.SalaryAccount;
import com.lingnet.hcm.service.salary.SalaryAccountService;

/**
 * 薪资账号
 * @ClassName: SalaryAccountServiceImpl 
 * @Description: 薪资账号 
 * @author zhanghj
 * @date 2017年4月25日 下午3:22:17 
 *
 */
@Service("salaryAccountService")
public class SalaryAccountServiceImpl extends BaseServiceImpl<SalaryAccount, String>
              implements SalaryAccountService {
}
