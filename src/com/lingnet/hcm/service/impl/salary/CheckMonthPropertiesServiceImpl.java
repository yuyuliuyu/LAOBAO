package com.lingnet.hcm.service.impl.salary;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.entity.salary.CheckMonthProperties;
import com.lingnet.hcm.service.salary.CheckMonthPropertiesService;

/**
 * 每月保险缴费核对配置表
 * @ClassName: CheckMonthPropertiesServiceImpl 
 * @Description: 每月保险缴费核对配置表 
 * @author zhanghj
 * @date 2017年6月8日 上午8:36:35 
 *
 */
@Service("checkMonthPropertiesService")
public class CheckMonthPropertiesServiceImpl extends BaseServiceImpl<CheckMonthProperties, String>
              implements CheckMonthPropertiesService {
}
