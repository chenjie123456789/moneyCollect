package com.shizheng.unit;

import android.annotation.SuppressLint;
import android.util.Log;

import com.shizheng.bean.Smsbean;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class timeFormater {

    public static String timeTransition(long timeNum){
        Date date = new Date(timeNum);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String getTodayTime(){
       Date date = new Date();
       @SuppressLint("SimpleDateFormat")
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return simpleDateFormat.format(date);

    }

}
