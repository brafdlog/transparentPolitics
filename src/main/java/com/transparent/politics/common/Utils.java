package com.transparent.politics.common;

public class Utils {

    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e1) {
        }
    }
    
}
