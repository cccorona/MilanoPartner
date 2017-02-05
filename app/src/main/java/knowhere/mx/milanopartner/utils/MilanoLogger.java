package knowhere.mx.milanopartner.utils;

import android.os.Environment;
import android.util.Log;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 */
public class MilanoLogger {
    public static String TAG = MilanoLogger.class.getName();

    private static MilanoLogger INSTANCE;
    private static String ERROR = "error";
    private static String LOGFILE = "DATEMENOW.DATA";
    private static Logger logger = Logger.getLogger(TAG);
    private static SimpleFormatter formatter;
    private static FileHandler fh;

    private MilanoLogger() {
    }

    public static MilanoLogger getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new MilanoLogger();
        }
        return INSTANCE;
    }

    public void escribeArchivo(String TAG, String DETAIL, String message) {
        try {
            logMessage(TAG, "::" + DETAIL, message);
            writeLog(TAG, "::" + DETAIL, message);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

    }


    public String getExternalDirectory() {
        String externalDirectory = "";
        externalDirectory = Environment.getExternalStorageDirectory().getAbsolutePath();
        return externalDirectory;
    }

    private void logMessage(String TAG, String DETAIL, String message) {
        if (GlobalMember.DEBUG) {
            if (message != null && message.toLowerCase().trim().contains(ERROR)) {
                Log.e(TAG + DETAIL + ":", message);
            } else {
                Log.i(TAG + DETAIL + ":", message);
            }
        }
    }

    public void writeLog(String TAG, String DETAIL, String message) {
        if (GlobalMember.DEBUG) {
            writePublic(TAG, DETAIL + ":", message);
        } else {
            writePrivate(TAG, DETAIL + ":", message);
        }
    }


    private void writePublic(String TAG, String DETAIL, String message) {

        try {
            String pathLog;
            formatter = new SimpleFormatter();
            pathLog = getExternalDirectory() + "/" + LOGFILE;
            fh = new FileHandler(pathLog);
            logger.addHandler(fh);
            fh.setFormatter(formatter);
            logger.info(TAG + DETAIL + ":" + message);
        } catch (Exception e) {
            Log.e(TAG + DETAIL + ":", e.getMessage());
        } finally {
            if (fh != null) {
                fh.close();

            }
        }

    }

    private void writePrivate(String TAG, String DETAIL, String message) {

    }
}
