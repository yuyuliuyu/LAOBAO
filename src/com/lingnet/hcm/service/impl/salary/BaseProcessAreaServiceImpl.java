package com.lingnet.hcm.service.impl.salary;

import org.springframework.stereotype.Service;

import com.lingnet.common.service.impl.BaseServiceImpl;
import com.lingnet.hcm.entity.salary.BaseProcessArea;
import com.lingnet.hcm.service.salary.BaseProcessAreaService;

/**
 * 缴费基数过程缴费地域中间表
 * @ClassName: BaseProcessAreaServiceImpl 
 * @Description: TODO 
 * @author zhanghj
 * @date 2017年6月19日 下午4:08:21 
 *
 */
@Service("baseProcessAreaService")
public class BaseProcessAreaServiceImpl extends BaseServiceImpl<BaseProcessArea, String>
              implements BaseProcessAreaService {
}
