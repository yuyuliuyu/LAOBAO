package com.lingnet.hcm.service.impl.salary;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.entity.salary.PaymentCheckMonth;
import com.lingnet.hcm.service.salary.PaymentCheckMonthService;

/**
 * 每月保险缴费核对总表
 * @ClassName: PaymentCheckMonthServiceImpl 
 * @Description: 每月保险缴费核对总表 
 * @author zhanghj
 * @date 2017年6月7日 上午9:38:54 
 *
 */
@Service("paymentCheckMonthService")
public class PaymentCheckMonthServiceImpl extends BaseServiceImpl<PaymentCheckMonth, String>
              implements PaymentCheckMonthService {
}
