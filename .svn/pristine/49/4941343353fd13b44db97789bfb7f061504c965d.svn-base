package com.lingnet.util;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.util.StrutsTypeConverter;

public class BigDecimalConverter extends StrutsTypeConverter {

    @SuppressWarnings("rawtypes")
    @Override
    public Object convertFromString(Map context, String[] values, Class toClass) {
        BigDecimal bd =null;
        if(BigDecimal.class == toClass){
            String bdStr =values[0];
            if(StringUtils.isNotBlank(bdStr)){
                bd =new BigDecimal(bdStr);
            }else{
                
            }
        }
        return bd;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public String convertToString(Map context, Object o) {
        if(o instanceof BigDecimal){
            // 向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则向相邻的偶数舍入。保留6位小数
            BigDecimal b =new BigDecimal(o.toString()).setScale(6, BigDecimal.ROUND_HALF_EVEN);
            return b.toString();
        }
        return o.toString();
    }

}
