package com.lingnet.util.test;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class numberTest {

    public static void main(String[] args) {
//        NumberFormat nf   =   NumberFormat.getPercentInstance();     
//       nf.setMinimumFractionDigits( 2 );  
//
//      System.out.println(nf.format(0.47));
      DecimalFormat df = new DecimalFormat();
//      String ss1 = "2";
//      System.out.println(String.format("%0"+ss1+"d%n", 0));
//      df.applyPattern("0."+String.format("%0"+ss1+"d%n", 0));
//
//      System.out.println(Math.ceil(2.339));
//      System.out.println(Math.floor(2.3));
      
//      BigDecimal bigDecimal = new BigDecimal(221.45556);
//      BigDecimal bd = bigDecimal.setScale(1, BigDecimal.ROUND_UP);
//      System.out.println(bd.doubleValue());
//      df.applyPattern("0.00");
//      System.out.println(df.format(222.222));
//      
//      String formatNumber = String.format("%02d", 1);
//      System.out.println(formatNumber);
//      
//
//      String[] arrays = new String[]{"1","2","4","3"};
//      
//      int positon = Arrays.binarySearch(arrays, "3");  
//
//      System.out.println("position is:"+positon); 
      
      
      
      String month = String.format("%02d", 11);
      System.out.println(month);
      String dd = "0011102497";
      System.out.println(dd.substring(dd.length()-7, dd.length()));
      String d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss") .format(new Date());
      System.out.println(d);
    }

}
