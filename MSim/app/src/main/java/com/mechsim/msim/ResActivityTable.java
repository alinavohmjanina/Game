package com.mechsim.msim;

import android.database.sqlite.SQLiteDatabase;

public class ResActivityTable extends AbstractTable {
    private String tableName;
    public static final String COL_ACTIVITY_ID = "activity_id";
    public static final String COL_RESOURCE_ID = "resource_id";
    public static final String COL_REQUIRE_RES_VALUE = "require_res_value";

    public ResActivityTable(String tName) {
        tableName = tName;
    }
    @Override
    public String getName() {
        return tableName;
    }
    @Override
    protected String sql() {
        return "create table " + getName() + " ("
                + Integer(COL_ACTIVITY_ID) + ", "
                + Integer(COL_RESOURCE_ID) + ", "
                + BigInt(COL_REQUIRE_RES_VALUE)
                + ");";
    }

    @Override
    public void initTable(SQLiteDatabase db) {

    }
}
