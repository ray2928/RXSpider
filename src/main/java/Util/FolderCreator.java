package Util;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

/**
 * Created by ruixie on 10/02/2017.
 */
public class FolderCreator {
    public static String getFolderName() {
        StringBuilder builder = new StringBuilder();
        LocalDateTime dayTime = new LocalDateTime();
        builder.append(dayTime.getYear() + "-" + dayTime.getMonthOfYear() + "-"+ dayTime.getDayOfMonth() + "-" + dayTime.getHourOfDay() + "-" + dayTime.getMinuteOfHour());
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(FolderCreator.getFolderName());
    }
}
