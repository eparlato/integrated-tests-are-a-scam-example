package it.eparlato.itse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {
    public static Calendar calendarFromString(String date) throws ParseException {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        cal.setTime(sdf.parse(date));

        return cal;
    }
}
