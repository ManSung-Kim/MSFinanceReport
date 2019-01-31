package com.gtk.msfinance.util;

public class Prt {
    private static boolean ALL_OFF = false;

    private static boolean USE_WRITE = (true || ALL_OFF);
    public static void w(String str) {
        if(USE_WRITE)
            System.out.println(str);
    }
}
