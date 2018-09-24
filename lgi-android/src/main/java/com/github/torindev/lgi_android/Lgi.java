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
        if (sLog) print(Thread.currentThread().getStackTrace()[3], getShortClassTag(s) + "\n" + s.toString());
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
            return simpleName + "[" + Integer.toHexString(System.identityHashCode(cls))  + "]";
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

    public static class Utils {

        public static boolean isOnline(Context context) {

            ConnectivityManager manager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            if(manager == null) {
                return false;
            }

            // 3g-4g available
            boolean is3g = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                    .isConnectedOrConnecting();
            // wifi available
            boolean isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                    .isConnectedOrConnecting();

            if (!is3g && !isWifi) {
                return false;
            } else
                return true;

        }

        public static boolean hideKeyboard(@NonNull Activity activity) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            //Find the currently focused view, so we can grab the correct window token from it.
            View view = activity.getCurrentFocus();
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = new View(activity);
            }
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                return true;
            } else {
                return false;
            }
        }




        public static int dpToPx(@NonNull final Context context, final float dp) {
            return (int) (dp * context.getResources().getDisplayMetrics().density);
        }

        public static int screenHeight(@NonNull final Activity context) {
            final Point size = new Point();

            context.getWindowManager().getDefaultDisplay().getSize(size);

            return size.y;
        }

        public static int screenWidth(@NonNull final Activity context) {
            final Point size = new Point();

            context.getWindowManager().getDefaultDisplay().getSize(size);

            return size.x;
        }

        public static double getInches(Activity activity) {
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            int width=dm.widthPixels;
            int height=dm.heightPixels;
            double wi=(double)width/(double)dm.xdpi;
            double hi=(double)height/(double)dm.ydpi;
            double x = Math.pow(wi,2);
            double y = Math.pow(hi,2);
            double screenInches = Math.sqrt(x+y);
            return screenInches;
        }

        public static byte[] getBytesFromInputStream(InputStream is) throws IOException {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            int nRead;
            byte[] data = new byte[16384];

            while (is.available() > 0 && (nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }

            buffer.flush();
            Lgi.p("loaded unzip size: " + (buffer.size() / 1000) + " kb.");

            return buffer.toByteArray();
        }



        @NonNull
        public static Point locationOnScreen(@NonNull final View view) {
            final int[] location = new int[2];
            view.getLocationOnScreen(location);
            return new Point(location[0], location[1]);
        }

        /**
         * Get a usable cache directory (external if available, internal otherwise).
         *
         * @param context The context to use
         * @param uniqueName A unique directory name to append to the cache dir
         * @return The cache dir
         */
        public static File getDiskCacheDir(Context context, String uniqueName) {
            // Check if media is mounted or storage is built-in, if so, try and use external cache dir
            // otherwise use internal cache dir
            final String cachePath =
                    Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
                            !isExternalStorageRemovable() ? (getExternalCacheDir(context) != null ? getExternalCacheDir(context).getPath() : context.getCacheDir().getPath()) :
                            context.getCacheDir().getPath();

            return new File(cachePath + File.separator + uniqueName);
        }

        /**
         * Check if external storage is built-in or removable.
         *
         * @return True if external storage is removable (like an SD card), false
         *         otherwise.
         */
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        public static boolean isExternalStorageRemovable() {
            if (Utils.hasGingerbread()) {
                return Environment.isExternalStorageRemovable();
            }
            return true;
        }

        /**
         * Get the external app cache directory.
         *
         * @param context The context to use
         * @return The external cache dir
         */
        @TargetApi(Build.VERSION_CODES.FROYO)
        public static File getExternalCacheDir(Context context) {
            if (hasFroyo()) {
                return context.getExternalCacheDir();
            }

            // Before Froyo we need to construct the external cache dir ourselves
            final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/";
            return new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
        }

        public static boolean hasFroyo() {
            // Can use static final constants like FROYO, declared in later versions
            // of the OS since they are inlined at compile time. This is guaranteed behavior.
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
        }

        public static boolean hasGingerbread() {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
        }
    }
}
