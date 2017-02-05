package knowhere.mx.milanopartner.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.google.gson.Gson;

import knowhere.mx.milanopartner.pojo.MilanoPartner;
import knowhere.mx.milanopartner.pojo.MilanoState;
import knowhere.mx.milanopartner.utils.MilanoEnums;
import knowhere.mx.milanopartner.utils.MilanoLogger;

/**
 * Created by Corona on 4/23/2016.
 */
public class DataBaseScript extends DataBaseManager {

    public static String TAG = DataBaseScript.class.getSimpleName();
    private static DataBaseScript INSTANCE;
    private final String SELECT = " select ";
    private final String FROM = " from ";
    private final String INSERT =" insert ";
    private final String INTO= " into ";
    private final String UPDATE =" update ";
    private final String SET =" set ";
    private final String GROUP_BY = "group by ";
    private final String WHERE = " where ";
    private final String LIKE = " like ";
    private final String HAVING = " having ";
    private final String ORDER_BY = " order by ";
    private final String LIMIT = " limit ";
    private final String ALL = " * ";
    private final String TABLE_APP_SETTIGS = " APP_SETTINGS ";
    private final String TABLE_MILANO_PARTNER= "MILANO_PARTNER";
    private final String APP_SETTINGS_KEY_ID =" ID_SETTINGS ";
    private final String APP_SETTINGS_KEY_IS_FIRST_RUN = " IS_FIRST_RUN ";
    private final String APP_SETTINGS_KEY_IS_UPDATED = " IS_UPDATED ";
    private final String APP_SETTINGS_KEY_APP_VERSION = " APP_VERSION ";
    private final String MILANO_PARTNER_KEY_JSON =" JSON_MILANO_PARTNER ";
    private final String MILANO_PARTNER_KEY_ID=" ID_PARTNER ";

    private final int MILANO_UNIQUE_ID =1;
    private final int APP_SETTINGS_UNIQUE_ID=1;

    private SQLiteDatabase database;

    private DataBaseScript(Context context) {
        super(context);
    }

    public static DataBaseScript getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DataBaseScript(context);
        }
        return INSTANCE;
    }


    public MilanoState getAppSettings() {
        MilanoState milanoState = null ;
        try {
            milanoState = new MilanoState();
            database = this.getReadableDatabase();
            Cursor cursor;
            String selectQuery = SELECT + ALL + FROM + TABLE_APP_SETTIGS;
            cursor = database.rawQuery(selectQuery, null);
            if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
              milanoState.setFirstRun(cursor.getInt(cursor.getColumnIndex(APP_SETTINGS_KEY_IS_FIRST_RUN.trim())));
                milanoState.setIsUpdated(cursor.getInt(cursor.getColumnIndex(APP_SETTINGS_KEY_IS_UPDATED.trim())));
                milanoState.setVersion(cursor.getString(cursor.getColumnIndex(APP_SETTINGS_KEY_APP_VERSION.trim())));
                milanoState.setIdState(cursor.getInt(cursor.getColumnIndex(APP_SETTINGS_KEY_ID.trim())));
                cursor.close();
            }


        } catch (Exception e) {
            MilanoLogger.getINSTANCE().escribeArchivo(TAG, "getAppSettings", e.getMessage());
        } finally {
            if (database != null) {
                database.close();
            }
        }
        return  milanoState;
    }

    public void updateAppSetings(MilanoState milanoState){
        try {
        database= this.getWritableDatabase();
            String updateStringQuery = UPDATE + TABLE_APP_SETTIGS + SET + APP_SETTINGS_KEY_APP_VERSION + "=?," +
                    APP_SETTINGS_KEY_IS_FIRST_RUN + "=?," + APP_SETTINGS_KEY_IS_UPDATED + "=?"
                    + WHERE + APP_SETTINGS_KEY_ID +"=?;";
            SQLiteStatement updateStatement = database.compileStatement(updateStringQuery);
            updateStatement.bindString(1,milanoState.getVersion());
            updateStatement.bindLong(2,milanoState.getFirstRun());
            updateStatement.bindLong(3,milanoState.getIsUpdated());
            updateStatement.bindLong(4,APP_SETTINGS_UNIQUE_ID);
            updateStatement.executeInsert();
            updateStatement.clearBindings();
        }catch (Exception e){
            MilanoLogger.getINSTANCE().escribeArchivo(TAG, "updateAppSetings", e.getMessage());
        }
        finally {
            if (database != null) {
                database.close();
            }
        }
    }

    public MilanoPartner getMilanoPartner(){
        MilanoPartner milanoPartner = null;
        String jsonStringMilanoPartner = "{}";
        int idMilano;
        Gson gson = new Gson();
        try {
            milanoPartner = new MilanoPartner();
            database = this.getReadableDatabase();
            Cursor cursor;
            String selectQuery = SELECT + ALL + FROM + TABLE_MILANO_PARTNER;
            cursor = database.rawQuery(selectQuery, null);
            if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                jsonStringMilanoPartner =cursor.getString(cursor.getColumnIndex(MILANO_PARTNER_KEY_JSON.trim()));
                idMilano =cursor.getInt(cursor.getColumnIndex(MILANO_PARTNER_KEY_ID.trim()));
                milanoPartner = gson.fromJson(jsonStringMilanoPartner,MilanoPartner.class);
                milanoPartner.setUserId(idMilano);
                cursor.close();
            }

        }catch (Exception e){
            MilanoLogger.getINSTANCE().escribeArchivo(TAG, "getMilanoPartner", e.getMessage());
        }finally {
            if(database != null){
                database.close();
            }
        }
        return milanoPartner;
    }

    public int addMilanoPartner(MilanoPartner partner){
         int rowId = -1;
        try {
            Gson gson = new Gson();
            String jsonStringPartner = gson.toJson(partner);
            String insertQuery;
            database = this.getReadableDatabase();
            insertQuery = INSERT + INTO + TABLE_MILANO_PARTNER + " (" + MILANO_PARTNER_KEY_ID + ","
                    + MILANO_PARTNER_KEY_JSON + ") " + "VALUES(?,?);";
            SQLiteStatement insertStatement = database.compileStatement(insertQuery);
            insertStatement.bindLong(1,MILANO_UNIQUE_ID);
            insertStatement.bindString(2,jsonStringPartner);
            rowId =(int) insertStatement.executeInsert();
            insertStatement.clearBindings();

        }catch (Exception e){
            MilanoLogger.getINSTANCE().escribeArchivo(TAG, "addMilanoPartner", e.getMessage());
        }
        finally {
            if (database != null) {
                database.close();
            }
        }

        return  rowId;

    }

    public int updateMilanoPartner(MilanoPartner partner){
        int rowId = -1;
        try {
            Gson gson = new Gson();
            String jsonStringParthner = gson.toJson(partner);
            database = this.getWritableDatabase();
            String updateQuery = UPDATE + TABLE_MILANO_PARTNER + SET + MILANO_PARTNER_KEY_JSON +"=? "
                    + WHERE + MILANO_PARTNER_KEY_ID + "=?";
            SQLiteStatement updateStatement = database.compileStatement(updateQuery);
            updateStatement.bindString(1,jsonStringParthner);
            updateStatement.bindLong(2,MILANO_UNIQUE_ID);
            rowId = updateStatement.executeUpdateDelete();
            updateStatement.clearBindings();
        }catch (Exception e){
            MilanoLogger.getINSTANCE().escribeArchivo(TAG, "updateMilanoPartner", e.getMessage());
        }
        finally {
            if (database != null) {
                database.close();
            }
        }

        return  rowId;
    }

    public String getLastKnowDataFinances(MilanoEnums.TimePeriod timePeriod){
        return "";
    }

    public String getLastKnowDataRate(){
        return "";
    }


}
