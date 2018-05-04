package com.lingnet.hcm.service.impl.salary;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.entity.salary.InsuranceMiddle;
import com.lingnet.hcm.service.salary.InsuranceMiddleService;

/**
 * 参保人员与福利项目中间表
 * @ClassName: InsuranceMiddleServiceImpl 
 * @Description: 参保人员与福利项目中间表
 * @author zhanghj
 * @date 2017年3月21日 下午2:46:24 
 *
 */
@Service("insuranceMiddleService")
public class InsuranceMiddleServiceImpl extends BaseServiceImpl<InsuranceMiddle, String>
              implements InsuranceMiddleService {
}
