package com.stlpd.error;

import java.util.Arrays;

public class ErrorDisplayHandler {

    public static String GetErrorString(Exception exception) {

        if (exception instanceof NumberFormatException) {
            return "Error: Number error" + exception.getMessage();
        } else if (exception instanceof IllegalArgumentException) {
            return "Error: " + exception.getMessage();
        } else if (exception instanceof ArrayIndexOutOfBoundsException) {
            return "ArrayIndexOutOfBoundsException: Array index is out of bounds.";
        } else {
            return "An unexpected error occurred: " + exception.getMessage();
        }
    }

}