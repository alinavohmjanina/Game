package com.mechsim.msim;

import android.content.ContentValues;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import static android.provider.BaseColumns._ID;

abstract class AbstractTable implements ITable{
    private String TAG = "AbstractTable";
    protected abstract String sql();
    @Override
    public void createTable(SQLiteDatabase db) {
        Log.i(TAG, sql());
        db.execSQL(sql());
        Log.d("TAG", "database " + getName() +" created.");
    }
    public void dropTable(SQLiteDatabase db) {
        db.execSQL(getDropSQL());
    }
    String getDropSQL(){
        return "DROP TABLE IF EXISTS " + getName();
    }

    public Long insert(ContentValues values, SQLiteDatabase db){
        long row = -1;
//        SQLiteDatabase db = Utils.getDB();
//        row = db.insert(getName(), null,
//                values);
        try {
            row = db.insertOrThrow(getName(), null, values);
        } catch (SQLiteConstraintException e){
            row = db.update(getName(), values, ITable.COL_ID + "=?",
                    new String[]{values.getAsString(ITable.COL_ID)});
            if(row == -1) throw e;
        }
        return row;
    }

    protected String colWithParams(String colName, String tp, String params){
        return colName + " " + tp + " " + params ;
    }
    protected String Integer(String colName){
        return IntegerWithParams(colName, "");
    }
    protected String BigInt(String colName){
        return colWithParams(colName, "BIGINT","");
    }
    protected String IntegerWithParams(String colName, String params){
        return colWithParams(colName, "INTEGER", params);
    }
    protected String VarChar(String colName){
        return VarCharWithParams(colName, "");
    }
    protected String VarCharWithParams(String colName, String params){
        return colWithParams(colName, "VARCHAR", params);
    }

}
