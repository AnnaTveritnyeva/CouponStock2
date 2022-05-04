package myProject.Coupons_Project.utils;

import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtils {
    public static Date getStartDate (){
       return Date.valueOf(LocalDate.now().minusDays((int)((Math.random())*11)));
    }

    public static Date getEndDate(){
        return Date.valueOf(LocalDate.now().plusDays((int)((Math.random())*11)));
    }

    public static String beautifyDateTime(LocalDateTime localDate) {
        return String.format("%02d/%02d/%04d %02d:%02d:%02d",
                localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear(),
                localDate.getHour(), localDate.getMinute(), localDate.getSecond()
        );
    }

    public static String getLocalDateTime() {
        return "[" + beautifyDateTime(LocalDateTime.now()) + "]";
    }
}
