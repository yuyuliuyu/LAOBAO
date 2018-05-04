package com.lingnet.util.test;

import java.util.Calendar;
import java.util.Date;

public class PartitionTest {

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        for (int i = 0; i< 60;i++) {
            cal.setTime(cal.getTime());
            cal.add(Calendar.MONTH, 1);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            String nr = "partition P"+(i+1)+" values less than(to_date('"+year+"-"+month+"-1', 'yyyy-mm-dd')),";
            System.out.println(nr);
        }
    }

}
