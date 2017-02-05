package knowhere.mx.milanopartner.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

import knowhere.mx.milanopartner.R;
import knowhere.mx.milanopartner.utils.MilanoLogger;


/**
 * Created by Corona on 4/14/2016.
 */
public class DataBaseManager extends SQLiteOpenHelper {

    public static String TAG = DataBaseManager.class.getSimpleName();
    private static String DATA_BASE_NAME = "milanopartner";
    private static int DATA_BASE_VERSION = 1;
    private Context mContext;
    private ArrayList<String> dataBaseTables;
    private InputStream in;
    private OutputStream ou;
    private InputStreamReader reader;
    private BufferedReader buffreader;

    public DataBaseManager(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
        this.mContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        init(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void init(SQLiteDatabase db) {

        try {
            in = mContext.getResources().openRawResource(R.raw.database);
            reader = new InputStreamReader(in);
            buffreader = new BufferedReader(reader);
            String line;
            dataBaseTables = new ArrayList<>();
            while ((line = buffreader.readLine()) != null) {
                line = line.trim();
                dataBaseTables.add(line);
                db.execSQL(line);
                MilanoLogger.getINSTANCE().escribeArchivo(TAG, "init:", line);
            }

        } catch (Exception e) {
            MilanoLogger.getINSTANCE().escribeArchivo(TAG, "init:", e.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                    reader.close();
                    buffreader.close();
                } catch (IOException e) {
                    MilanoLogger.getINSTANCE().escribeArchivo(TAG, "init:", e.getMessage());
                }
            }
        }

    }


}
