package framework.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DateUtils {
    private static final DateTimeFormatter DEFAULT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private DateUtils() {
    }

    public static String today() {
        return LocalDate.now().format(DEFAULT_FORMAT);
    }

    public static String format(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }
}

