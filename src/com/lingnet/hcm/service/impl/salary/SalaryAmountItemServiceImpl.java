package com.lingnet.hcm.service.impl.salary;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.entity.salary.SalaryAmountItem;
import com.lingnet.hcm.service.salary.SalaryAmountItemService;

/**
 * 总量其他薪资项目表
 * @ClassName: SalaryAmountItemServiceImpl 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年8月17日 下午10:46:26 
 *
 */
@Service("salaryAmountItemService")
public class SalaryAmountItemServiceImpl extends BaseServiceImpl<SalaryAmountItem, String>
              implements SalaryAmountItemService {
}
