package com.lingnet.util;

import java.math.BigDecimal;
/**
 * ***********************************************/
/*
 * MathUtil
 * 摘要说明.
 * @Description:
 *  : 数学计算工具类
 * @author 高亚杰
 * @version 2013-1-6 高亚杰  初版.
*/
/************************************************
 */
public class MathUtil {
    /**
     * 
     * add(两个double类型的数相加)    
     * @param   v1 第一个参数
     * @param   v2 第二个参数     
     * @return double  计算结果   
     * @Exception 异常对象    
     * @author 高亚杰 version 2013-1-6 1.0
     */
    public static double add(Double v1,Double v2){
        if(v1==null)
            v1=0D;
        if(v2==null)
            v2=0D;
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }
    /**
     * 
     * sub(两个double类型的数相减)    
     * @param   v1 第一个参数
     * @param   v2 第二个参数     
     * @return double  计算结果   
     * @Exception 异常对象    
     * @author 高亚杰 version 2013-1-6 1.0
     */
    public static double sub(Double v1,Double v2){
        if(v1==null)
            v1=0D;
        if(v2==null)
            v2=0D;
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }
    /**
     * 
     * multiply(两个double类型的数的乘法运算)    
     * @param   v1 第一个参数
     * @param   v2 第二个参数     
     * @return double  计算结果   
     * @Exception 异常对象    
     * @author 高亚杰 version 2013-1-9 1.0
     */
    public static double multiply(Double v1,Double v2){
        if(v1==null)
            v1=0D;
        if(v2==null)
            v2=0D;
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }
    /**
     * 
     * multiply(两个double类型的数的除法运算)    
     * @param   v1 第一个参数
     * @param   v2 第二个参数     
     * @return double  计算结果   
     * @Exception 异常对象    
     * @author 高亚杰 version 2013-1-9 1.0
     * 段晶晶 2014-9-5 修改 考虑到除不尽的情况 给定截取小数位4
     */
    public static double divide(Double v1,Double v2){
        if(v1==null)
            v1=0D;
        if(v2==null || v2==0D)
            return 0D;
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2).setScale(4).doubleValue();
    }
    public static double dividexsd(Double v1,Double v2){
        if(v1==null)
            v1=0D;
        if(v2==null || v2==0D)
            return 0D;
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        BigDecimal aa=(new BigDecimal(Double.toString(b1.divide(b2).doubleValue()))).setScale(2, BigDecimal.ROUND_HALF_UP);
        return aa.doubleValue();
    }
    
    /**
     * 解决BigDecimal精度问题（统一要求用这个方法做除法操作）
     * 
     * **/
    public static double DrivedF(Double v1,Double v2){
        if(v1==null)
            v1=0D;
        if(v2==null || v2==0D)
            return 0D;
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
            BigDecimal fh=b1.divide(b2, 4, BigDecimal.ROUND_HALF_EVEN);
           Double fhl= fh.doubleValue();
        return fhl;
    }
    
    
    /**
     * 
     * isEqual(判断两个double值是否相等)    
     * @param   v1 第一个参数
     * @param   v2 第二个参数     
     * @return boolean   相等为true不相等为false
     * @Exception 异常对象    
     * @author 高亚杰 version 2013-1-9 1.0
     */
    public static boolean isEqual(Double v1,Double v2){
        if(v1==null && v2==null)
            return true;
        if(v1==null)
            return v2.equals(v1);
        return v1.equals(v2);
    }
    /**
     * 
     * isEqual(判断两个double值是否相等)    
     * @param   v1 第一个参数
     * @param   v2 第二个参数     
     * @return boolean   相等为true不相等为false
     * @Exception 异常对象    
     * @author 高亚杰 version 2013-1-9 1.0
     */
    public static boolean isEqual(Long v1,Long v2){
        if(v1==null && v2==null)
            return true;
        if(v1==null)
            return v2.equals(v1);
        return v1.equals(v2);
    }
}
