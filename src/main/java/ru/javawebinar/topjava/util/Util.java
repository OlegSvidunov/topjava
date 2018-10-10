package ru.javawebinar.topjava.util;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Util {
    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }

    public static LocalDateTime parceDateTime(String htmlDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(htmlDateTime, formatter);
        return dateTime;
    }

    public static boolean isRequestValid(final HttpServletRequest req) {

        final String description = req.getParameter("description");
        final String calories = req.getParameter("calories");
        final LocalDateTime dateTime;
        try {
            dateTime = Util.parceDateTime(req.getParameter("datetime"));
        } catch (Exception e) {
            return false;
        }

        return description != null && description.length() > 0 &&
                calories != null && calories.length() > 0 &&
                dateTime != null;
    }
}
