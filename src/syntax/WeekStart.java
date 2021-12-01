package syntax;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class WeekStart {

    private static int getWeekNumber(LocalDate date) {
        final Locale aDefault = Locale.getDefault();
         final WeekFields weekFields = WeekFields.of(aDefault);
        return date.get(weekFields.weekOfWeekBasedYear());
    }


    public static void main(String[] args) {


       ZonedDateTime t  = ZonedDateTime.of(2018, 9, 29, 10, 0, 0, 0, ZoneId.of("Europe/London"));

        System.out.println(getWeekNumber(t.toLocalDate()));

    }

}
