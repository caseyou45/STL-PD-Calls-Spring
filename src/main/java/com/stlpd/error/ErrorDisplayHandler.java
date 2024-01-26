package com.stlpd.error;

public class ErrorDisplayHandler {

    public static String GetErrorString(Exception exception) {
        return getFormattedError(exception);

    }

    private static String getFormattedError(Exception exception) {
        StackTraceElement[] stackTrace = exception.getStackTrace();
        if (stackTrace.length > 0) {

            StackTraceElement topElement = stackTrace[0];
            String methodName = topElement.getMethodName();
            String className = topElement.getClassName();
            return exception.getMessage() + "-" + className + "-" + methodName;
        }

        return exception.getMessage();
    }
}
