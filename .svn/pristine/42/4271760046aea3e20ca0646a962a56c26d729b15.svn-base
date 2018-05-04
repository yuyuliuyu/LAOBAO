package com.lingnet.util;

import java.math.BigDecimal;

/** 
 * @ClassName: JvmSize 
 * @Description: TODO 
 * @author zhmf
 * @date 2014-1-20 上午8:17:30 
 *  
 */

public class JvmSize {

        
        /**
          * @param args
          */
        public static void main(String[] args) {
            Double l = 2.72241265500d;
            Math.floor(l);
            System.out.println(new BigDecimal(l).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            Double q = MathUtil.sub(l, Math.floor(l));
            int n = 0;
            while(q>0){
                q = MathUtil.sub(MathUtil.multiply(q, 10d), Math.floor(MathUtil.multiply(q, 10d)));
                n++;
            }
            System.out.println(n);

           System. out .println( " 内存信息 :" + toMemoryInfo ());
        }
     
        /**
          * 获取当前 jvm 的内存信息
          *
          * @return
          */
        public static String toMemoryInfo() {
     
           Runtime currRuntime = Runtime.getRuntime ();
           int nFreeMemory = ( int ) (currRuntime.freeMemory() / 1024 / 1024);
           int nTotalMemory = ( int ) (currRuntime.totalMemory() / 1024 / 1024);
           return nFreeMemory + "M/" + nTotalMemory + "M(free/total)" ;
        }
    }

