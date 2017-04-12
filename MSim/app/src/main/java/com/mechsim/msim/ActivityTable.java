package com.mechsim.msim;


import android.database.sqlite.SQLiteDatabase;

public class ActivityTable extends AbstractTable {
    private String tableName;
    public static final String COL_ACTIVITY_NAME = "activity_name";
    public static final String COL_USED_BY_RESOURCE_ID = "used_by_resource_id";
    public static final String COL_ACTIVITY_VALUE = "activity_value";
    public ActivityTable(String tName) {
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
                + VarChar(COL_ACTIVITY_NAME) + ", "
                + BigInt(COL_ACTIVITY_VALUE) + ", "
                + Integer(COL_USED_BY_RESOURCE_ID)
                + ");";
    }

    @Override
    public void initTable(SQLiteDatabase db) {

    }
}
