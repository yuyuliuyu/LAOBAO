package com.lingnet.hcm.service.impl.salary;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.entity.salary.BaseProcessFuli;
import com.lingnet.hcm.service.salary.BaseProcessFuliService;

/**
 * 缴费基数过程福利项目中间表
 * @ClassName: BaseProcessFuliServiceImpl 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年6月19日 下午4:10:23 
 *
 */
@Service("baseProcessFuliService")
public class BaseProcessFuliServiceImpl extends BaseServiceImpl<BaseProcessFuli, String>
              implements BaseProcessFuliService {
}
