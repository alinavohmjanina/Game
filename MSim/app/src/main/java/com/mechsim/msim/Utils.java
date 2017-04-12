package com.mechsim.msim;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Map;

public class Utils {
    public static SQLiteDatabase getDB(){
        SQLiteOpenHelper dbHelper = DBHelper.getInstance(MechSimApp.getContext());
        return dbHelper.getWritableDatabase();
    }
    public static <T> T withDB(IFunctionDB<T> f){
        T result = f.exec(getDB());
        return result;
    }

    public static <T> T withDBTx(IFunctionDB<T> f){
        T result;
        SQLiteDatabase db = getDB();
        db.beginTransaction();
        result = f.exec(db);
        db.setTransactionSuccessful();
        db.endTransaction();
        return result;
    }

    public static void startActivity(Activity activity, Class<?> aClass, Map<String,String> extra) {
        if(!MechSimApp.isApplicationOnBackground()){
            Intent intent = new Intent(activity, aClass);
            if(extra != null){
                for (Map.Entry<String, String> entry : extra.entrySet()) {
                    intent.putExtra(entry.getKey(),entry.getValue());
                }
            }
            activity.startActivity(intent);
        }
    }

}
