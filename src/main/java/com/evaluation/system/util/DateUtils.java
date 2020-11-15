package com.evaluation.system.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static Date getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(date);
        try {
            date = df.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static void main(String[] args) {
        Date date = getCurrentDate();
        SimpleDateFormat df = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(date));
    }

}
