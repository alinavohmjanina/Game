package com.mechsim.msim;

import android.database.sqlite.SQLiteDatabase;

public interface IFunctionDB<T> {
    T exec(SQLiteDatabase db);
}

