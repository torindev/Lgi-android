package c.github.lgi_android;

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
    public static boolean sShowLongString = true;
    public static String sTag = "fatal";
    public static long sLongStringLength = 1100L;

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



    private static final String SEPAR = ": ";
    private static long sStartTime = 0;

    // Выводит в лог имя класса и имя метода, в котором был вызван
    public static void p() {
        if (sLog) {
            StackTraceElement ste = Thread.currentThread().getStackTrace()[3];
            String className = ste.getFileName().split("\\.")[0];
            String method = ste.getMethodName();
            log("[" + className + "." + method + "]");
        }
    }

    // Выводит в лог имя класса + String переданный в параметре
    public static void p(String s) {
        if (sLog) {
            StackTraceElement ste = Thread.currentThread().getStackTrace()[3];
            String className = ste.getFileName().split("\\.")[0];
            String method = ste.getMethodName();
            log("[" + className + "." + method + "]" + SEPAR + s);
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
            StackTraceElement ste = Thread.currentThread().getStackTrace()[3];
            String className = ste.getFileName().split("\\.")[0];
            String method = ste.getMethodName();
            log("[" + className + "." + method + "]" + SEPAR + s + ", " + sDiffTime + " seconds;");
        }
    }

    public static void p(Object s) {
        if (sLog) {
            StackTraceElement ste = Thread.currentThread().getStackTrace()[3];
            String className = ste.getFileName().split("\\.")[0];
            String method = ste.getMethodName();
            log("[" + className + "." + method + "]" + SEPAR + getShortClassTag(s) + "\n" + s.toString());
        }
    }

    public static void printLongString(String s) {
        if (sLog && sShowLongString) {
            StackTraceElement ste = null;
            ste = Thread.currentThread().getStackTrace()[3];
            String str = ste.getClassName() + "." + ste.getMethodName() +
                    "(" + ste.getFileName() + ":" + ste.getLineNumber() + ")";
            if (s != null && s.length() <= 1100) {
                log(str + " > " + s);
            } else if (s != null && s.length() > 1100) {
                log(str + " > " + s.substring(0, 1100) + " <<NOT A FULL STRING>>, see full in DEBUG");
            }
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

    public static void p(int s) {
        if (sLog) {
            StackTraceElement ste = Thread.currentThread().getStackTrace()[3];
            String className = ste.getFileName().split("\\.")[0];
            String method = ste.getMethodName();
            log("[" + className + "." + method + "]" + SEPAR + s);
        }
    }

    public static void p(double s) {
        if (sLog) {
            StackTraceElement ste = Thread.currentThread().getStackTrace()[3];
            String className = ste.getFileName().split("\\.")[0];
            String method = ste.getMethodName();
            log("[" + className + "." + method + "]" + SEPAR + s);
        }
    }

    public static void p(float s) {
        if (sLog) {
            StackTraceElement ste = Thread.currentThread().getStackTrace()[3];
            String className = ste.getFileName().split("\\.")[0];
            String method = ste.getMethodName();
            log("[" + className + "." + method + "]" + SEPAR + s);
        }
    }

    public static void p(long s) {
        if (sLog) {
            StackTraceElement ste = Thread.currentThread().getStackTrace()[3];
            String className = ste.getFileName().split("\\.")[0];
            String method = ste.getMethodName();
            log("[" + className + "." + method + "]" + SEPAR + s);
        }
    }

    public static void p(char s) {
        if (sLog) {
            StackTraceElement ste = Thread.currentThread().getStackTrace()[3];
            String className = ste.getFileName().split("\\.")[0];
            String method = ste.getMethodName();
            log("[" + className + "." + method + "]" + SEPAR + s);
        }
    }

    public static void p(byte s) {
        if (sLog) {
            StackTraceElement ste = Thread.currentThread().getStackTrace()[3];
            String className = ste.getFileName().split("\\.")[0];
            String method = ste.getMethodName();
            log("[" + className + "." + method + "]" + SEPAR + s);
        }
    }

    public static void p(boolean s) {
        if (sLog) {
            StackTraceElement ste = Thread.currentThread().getStackTrace()[3];
            String className = ste.getFileName().split("\\.")[0];
            String method = ste.getMethodName();
            log("[" + className + "." + method + "]" + SEPAR + s);
        }
    }

    // просто перенос строки
    public static void separatorLine() {
        if (sLog) {
            log("\t-\t-\t-\t-\t-");
        }
    }


    public static long time() {
        return System.currentTimeMillis();
    }

    public static void toastShort(Context context, String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    public static void toastLong(Context context, String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }

    // выводит класс, метод, и строку, в которой был вызван
    public static void err() {
        if (sLog) {
            StackTraceElement ste = null;
            ste = Thread.currentThread().getStackTrace()[3];
            String str = ste.getClassName() + "." + ste.getMethodName() +
                    "(" + ste.getFileName() + ":" + ste.getLineNumber() + ")";
            log(str);
        }
    }
    // выводит класс, метод, и строку, в которой был вызван + текст
    public static void err(String s) {
        if (sLog) {
            StackTraceElement ste = null;
            ste = Thread.currentThread().getStackTrace()[3];
            String str = ste.getClassName() + "." + ste.getMethodName() +
                    "(" + ste.getFileName() + ":" + ste.getLineNumber() + ")";
            log(str + " > " + s);
        }
    }

    // выводит в лог String s и Exception
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

    // используется для замера промежутка времени
    public static void startTime() {
        sStartTime = time();
    }

    // используется для замера промежутка времени, возвращает время между
    // startTime и endTime
    public static void endTime() {
        if (sStartTime == 0) {
            log("Error, startTime = 0. Don't use endTime() without startTime() before.");
            return;
        } else {
            long diff = time() - sStartTime;
            sStartTime = 0;
            log("Time: " + diff + " ms.");
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

    private static void log(String s) {
        if (sLog) {
            android.util.Log.v(sTag, s);
        }
    }

    public static class Utils {

        public static boolean isOnline(Context context) {

            ConnectivityManager manager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            if(manager == null)
                return false;

            // 3g-4g available
            boolean is3g = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                    .isConnectedOrConnecting();
            // wifi available
            boolean isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                    .isConnectedOrConnecting();

            System.out.println(is3g + " net " + isWifi);

            if (!is3g && !isWifi) {
                return false;
            } else
                return true;

        }

        public static void hideKeyboard(@NonNull Activity activity) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            //Find the currently focused view, so we can grab the correct window token from it.
            View view = activity.getCurrentFocus();
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = new View(activity);
            }
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
