package utils.driver;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class DateGenerator {
    public static void main(String[] args) {
        String date = DateGenerator.getNextMonday().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        System.out.println(date);
        System.out.println(DateGenerator.twoWeeksAfterNextMonday());
    }

    public static LocalDate getNextMonday() {
        return LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
    }

    public static LocalDate twoWeeksAfterNextMonday() {
        return getNextMonday().plusDays(14);
    }
}
