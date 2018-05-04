package com.lingnet.hcm.service.impl.salary;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.entity.salary.PaymentProcessArea;
import com.lingnet.hcm.service.salary.PaymentProcessAreaService;

/**
 * 保险缴费过程缴费地域中间表
 * @ClassName: PaymentProcessAreaServiceImpl 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年4月26日 上午11:27:28 
 *
 */
@Service("paymentProcessAreaService")
public class PaymentProcessAreaServiceImpl extends BaseServiceImpl<PaymentProcessArea, String>
              implements PaymentProcessAreaService {
}
