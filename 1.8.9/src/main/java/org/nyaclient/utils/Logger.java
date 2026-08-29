package org.nyaclient.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void info(Object info) {
        LocalDateTime dateObj = LocalDateTime.now();
        System.out.println("[" + formatter.format(dateObj) + " INFO] " + info);
    }

    public static void error(Object info) {
        LocalDateTime dateObj = LocalDateTime.now();
        System.out.println("[" + formatter.format(dateObj) + " ERROR] " + info);
    }

    public static void warn(Object info) {
        LocalDateTime dateObj = LocalDateTime.now();
        System.out.println("[" + formatter.format(dateObj) + " WARN] " + info);
    }
}
