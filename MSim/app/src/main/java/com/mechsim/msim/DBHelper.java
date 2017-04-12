package com.mechsim.msim;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "DBHelper";

    private static DBHelper instance = null;

    public static synchronized DBHelper getInstance(Context context) {
        if(instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }

    private DBHelper(Context context) {
        super(context, "mechsim.db", null, DBProvider.dbVersion);
//        SQLiteDatabase db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG,"onCreate: tables: " + DBProvider.tables.size());
        for (ITable table : DBProvider.tables.values()) {
            Log.d(TAG, "table create: " + table.getName());
            table.createTable(db);
            table.initTable(db);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        try {
            for (ITable table : DBProvider.tables.values()) {
                Log.d(TAG, "table upgrade: " + table.getName());
                table.dropTable(db);
                table.createTable(db);
                table.initTable(db);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
