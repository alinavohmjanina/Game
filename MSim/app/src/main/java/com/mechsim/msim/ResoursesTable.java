package com.mechsim.msim;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class ResoursesTable extends AbstractTable{
    public static final String COL_RESNAME = "res_name";
    public static final String COL_PERIOD = "period";
    public static final String COL_APPLIED_ACTIVITY_ID = "applied_activity_id";
    private String tableName;
    public ResoursesTable(String tName) {
        tableName = tName;
    }
    @Override
    public String getName() {
        return tableName;
    }

    @Override
    protected String sql() {
        return "create table " + getName() + " ("
                + IntegerWithParams(COL_ID, "primary key autoincrement") + ", "
                + VarChar(COL_RESNAME) + ", "
                + Integer(COL_PERIOD) + ", "
                + Integer(COL_APPLIED_ACTIVITY_ID)
                + ");";
    }

    @Override
    public void initTable(SQLiteDatabase db) {
        ContentValues exp = createResource("Experience", 3000);
        ContentValues money = createResource("Money", 1000);
        ContentValues hunger = createResource("Hunger", 2000);
        ContentValues fatigue = createResource("Fatigue", 2000);
        insert(exp, db);
        insert(money, db);
        insert(hunger, db);
        insert(fatigue, db);
    }

    private ContentValues createResource(String name, int period) {
        ContentValues values = new ContentValues();
        values.put(COL_RESNAME, name);
        values.put(COL_PERIOD, period);
        return values;
    }
}
