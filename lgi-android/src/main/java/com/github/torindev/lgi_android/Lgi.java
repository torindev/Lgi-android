package com.github.torindev.lgi_android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Lgi {
    public static boolean sLog = true;
    public static String sTag = "fatal";
    public static int sStringMaxLength = 1100;
    private static final String DELIMITER = ": ";
    private static long sStartTime = 0;

    public static void p() {
        if (sLog) print(Thread.currentThread().getStackTrace()[3], "");
    }

    public static void p(String s) {
        if (sLog) print(Thread.currentThread().getStackTrace()[3], s);
    }

    public static void p(Object s) {
        if (sLog) print(Thread.currentThread().getStackTrace()[3], getShortClassTag(s));
    }

    public static void p(int s) {
        if (sLog) print(Thread.currentThread().getStackTrace()[3], s + "");
    }

    public static void p(double s) {
        if (sLog) print(Thread.currentThread().getStackTrace()[3], s + "");
    }

    public static void p(float s) {
        if (sLog) print(Thread.currentThread().getStackTrace()[3], s + "");
    }

    public static void p(long s) {
        if (sLog) print(Thread.currentThread().getStackTrace()[3], s + "");
    }

    public static void p(char s) {
        if (sLog) print(Thread.currentThread().getStackTrace()[3], s + "");
    }

    public static void p(byte s) {
        if (sLog) print(Thread.currentThread().getStackTrace()[3], s + "");
    }

    public static void p(boolean s) {
        if (sLog) print(Thread.currentThread().getStackTrace()[3], s + "");
    }

    private static void print(StackTraceElement ste, String s) {
        String className = ste.getFileName().split("\\.")[0];
        String method = ste.getMethodName();
        log(getShortenedString(getFirstPart(className, method) + s));
    }

    public static void err() {
        if (sLog) {
            StackTraceElement ste = null;
            ste = Thread.currentThread().getStackTrace()[3];

            String str = getFirstPartErr(ste.getClassName(), ste.getMethodName(), ste.getFileName(), ste.getLineNumber());
            log(str);
        }
    }

    public static void err(String s) {
        if (sLog) {
            StackTraceElement ste = null;
            ste = Thread.currentThread().getStackTrace()[3];
            String str = getFirstPartErr(ste.getClassName(), ste.getMethodName(), ste.getFileName(), ste.getLineNumber());
            log(str + " > " + s);
        }
    }

    public static void err(String s, Throwable ex) {
        if (sLog) {
            err("Message: " + s);
            android.util.Log.v(sTag, "stacktrace: ", ex);
        }
    }

    public static void err(Throwable ex) {
        if (sLog) {
            err("Message: " + ex.getMessage());
            android.util.Log.v(sTag, "stacktrace: ", ex);
        }
    }

    public static void i(String tag, String string) {
        if (sLog) android.util.Log.i(tag, string);
    }
    public static void e(String tag, String string) {
        if (sLog) android.util.Log.e(tag, string);
    }
    public static void d(String tag, String string) {
        if (sLog) android.util.Log.d(tag, string);
    }
    public static void v(String tag, String string) {
        if (sLog) android.util.Log.v(tag, string);
    }
    public static void w(String tag, String string) {
        if (sLog) android.util.Log.w(tag, string);
    }

    public static void i(String string) {
        if (sLog) android.util.Log.i(sTag, string);
    }
    public static void e(String tag, String string, IOException e) {
        if (sLog) android.util.Log.e(sTag, string);
    }
    public static void d(String string) {
        if (sLog) android.util.Log.d(sTag, string);
    }
    public static void v(String string) {
        if (sLog) android.util.Log.v(sTag, string);
    }
    public static void w(String string) {
        if (sLog) android.util.Log.w(sTag, string);
    }

    public static void toastShort(Context context, String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    public static void toastLong(Context context, String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }

    private static String getFirstPart(String className, String method) {
        StringBuilder sb = new StringBuilder("");
        sb.append("[").append(className).append(".").append(method).append("]").append(DELIMITER);
        return sb.toString();
    }

    private static String getFirstPartErr(String className, String method, String fileName, int lineNumber) {
        StringBuilder sb = new StringBuilder("");
        sb.append(className).append(".").append(method).append("(").append(fileName).append(":").append(lineNumber).append(")");
        return sb.toString();
    }

    private static String getShortenedString(String s) {
        if (s != null && s.length() <= sStringMaxLength) {
            return s;
        } else if (s != null && s.length() > sStringMaxLength) {
            return s.substring(0, sStringMaxLength) + " <<NOT A FULL STRING>>";
        } else {
            return s;
        }
    }

    private static void log(String s) {
        if (sLog) {
            android.util.Log.v(sTag, s);
        }
    }

    private static long sLastTime = 0L;
    private static double sDiffTime = 0L;

    public static void checkTimeToDo(String s) {
        if (sLog) {
            if (sLastTime == 0L) {
                sLastTime = time();
            } else {
                sDiffTime = ((time() * 1.0d) - (sLastTime * 1.0d)) / 1000;
                sLastTime = time();
            }
            print(Thread.currentThread().getStackTrace()[3], s + ", " + sDiffTime + " seconds;");
        }
    }

    public static long time() {
        return System.currentTimeMillis();
    }

    public static void startTime() {
        sStartTime = time();
    }

    public static long stopTimeAndGetDiff() {
        if (sStartTime == 0) {
            print(Thread.currentThread().getStackTrace()[3],
                    "Error, startTime == 0. Don't use stopTime() without calling startTime().");
            return sStartTime;
        } else {
            long diff = time() - sStartTime;
            sStartTime = 0;
            return diff;
        }
    }

    public static void stopTimeAndPrint() {
        if (sStartTime == 0) {
            print(Thread.currentThread().getStackTrace()[3],
                    "Error, startTime == 0. Don't use stopTime() without calling startTime().");
            return;
        } else {
            long diff = time() - sStartTime;
            sStartTime = 0;
            print(Thread.currentThread().getStackTrace()[3], "Spent time: " + diff + " ms.");
        }
    }


    public static String getShortClassTag(Object cls) {
        if (cls == null) {
            return "null";
        } else {
            String simpleName = cls.getClass().getSimpleName();
            if (simpleName == null || simpleName.length() <= 0) {
                simpleName = cls.getClass().getName();
                int end = simpleName.lastIndexOf('.');
                if (end > 0) {
                    simpleName = simpleName.substring(end + 1);
                }
            }
            return simpleName + " [" + Integer.toHexString(System.identityHashCode(cls))  + "]";
        }
    }

    public static String getFormatDate(long d) {
        return new SimpleDateFormat("dd MMMM yyyy 'at' HH:mm:ss", Locale.getDefault()).format(new Date(d));
    }

    public static String getFormatDate(long d, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(0,0,0,0,0,0);
        calendar.setTimeInMillis(d);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getFormatDateNew(long d, String format) {
        return new SimpleDateFormat(format).format(new Date(d));
    }
}
