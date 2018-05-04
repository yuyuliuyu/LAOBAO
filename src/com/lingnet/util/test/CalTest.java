package com.lingnet.util.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CalTest {

    public static void main(String[] args) {
//        try {
//            Calendar cal = Calendar.getInstance();
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
//            cal.setTime(dateFormat.parse("2017年3月14日" + " 00时00分00秒"));
//            System.out.println(cal.getTime());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        Date curDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(curDate);
        calendar.add(Calendar.MONTH, -1);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        System.out.println("year:" + year + "  month:" + month);
        String succ = "success:231321321";
        System.out.println(succ.substring(8, succ.length()));
        
        Calendar cal= Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        System.out.println(cal.getTime());
    }

}
