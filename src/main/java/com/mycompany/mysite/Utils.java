package com.mycompany.mysite;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;

public class Utils {
    public String itod(Integer idate){
        if(idate==null) return "";
        Date d = new Date();
        d.setTime(idate*1000L);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dtf.format(Instant.ofEpochSecond(idate).atZone(ZoneId.of("Asia/Shanghai")));
    }
}
