package uoc.ded.practica.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static Date createDate(String date) {
        try {
            return new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
