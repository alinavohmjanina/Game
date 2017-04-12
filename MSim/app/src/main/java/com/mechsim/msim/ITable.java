package com.mechsim.msim;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import static android.provider.BaseColumns._ID;

interface ITable {
    String COL_ID = _ID;
    void createTable(SQLiteDatabase db);
    void dropTable(SQLiteDatabase db);
    String getName();
    Long insert(ContentValues values, SQLiteDatabase db);
    void initTable(SQLiteDatabase db);
}
