package com.Util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aspect.AspectException;


public final class Util {


     public static String trim(String stringValueInp) {
        String stringValue = stringValueInp;
        if (!isNullString(stringValue)) {
            stringValue = stringValue.trim();
        }
        return stringValue;
    }

    public static boolean isNullString(String string) {
        boolean isNullString = true;
        if (string != null && !"".equals(string.trim())) {
            isNullString = false;
        }
        return isNullString;
    }


    public static boolean isNull(Object obj) {
        return null == obj ? true : false;
    }

    @SuppressWarnings("unchecked")
    public static boolean isNullList(Object obj) {
        boolean isNullList = true;
        if (obj != null) {
            if (obj instanceof List) {
                List<Object> list = (List<Object>) obj;
                isNullList = (list.isEmpty() || list.size() < 1);
            }
        }
        return isNullList;
    }

    public static String toUpper(String stringValueInp) {
        String stringValue = stringValueInp;
        if (!isNullString(stringValue)) {
            stringValue = trim(stringValue).toUpperCase();
        }
        return stringValue;
    }

  
    public static Timestamp getCurrentTimeStamp() {
        SimpleDateFormat timeStampFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss.SSS");

        String timeStamp = timeStampFormat.format(Calendar.getInstance()
                .getTime());
        Timestamp ts = Timestamp.valueOf(timeStamp);
        return ts;
    }

    
    public static void throwException(String msg) {
        throw new AspectException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Failure",msg );
    }

    
       

   
}
