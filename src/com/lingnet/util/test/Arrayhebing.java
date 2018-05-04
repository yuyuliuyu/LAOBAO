package com.lingnet.util.test;

import java.util.Arrays;

public class Arrayhebing {

    public static void main(String[] args) {
        //表格标题
        String[] tableCaption ={"序号","工号","姓名"};
        String[] items = "保留工资标准,年均工资,123".split(",");
        int len1 = tableCaption.length;
        int len2 = items.length;
        tableCaption = Arrays.copyOf(tableCaption, len1 + len2);
        System.arraycopy(items, 0, tableCaption, len1, len2);
        System.out.println(Arrays.toString(tableCaption));
        
        
        String [] str1 = {"J","a","v","a","中"};
        String [] str2 = {"如","何","把","两","个","数","组","合","并","为","一","个"};
        int strLen1=str1.length;//保存第一个数组长度
        int strLen2=str2.length;//保存第二个数组长度
        str1= Arrays.copyOf(str1,strLen1+ strLen2);//扩容
        System.arraycopy(str2, 0, str1, strLen1,strLen2 );//将第二个数组与第一个数组合并
        System.out.println(Arrays.toString(str1));//输出数组
    }

}
