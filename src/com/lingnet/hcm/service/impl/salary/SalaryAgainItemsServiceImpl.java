package com.lingnet.hcm.service.impl.salary;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.entity.salary.SalaryAgainItems;
import com.lingnet.hcm.service.salary.SalaryAgainItemsService;

/**
 * 二次分配薪资表
 * @ClassName: SalaryAgainItemsServiceImpl 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年5月17日 上午8:14:18 
 *
 */
@Service("salaryAgainItemsService")
public class SalaryAgainItemsServiceImpl extends BaseServiceImpl<SalaryAgainItems, String>
              implements SalaryAgainItemsService {
}
