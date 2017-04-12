package com.mechsim.msim;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DBProvider {
    public static int dbVersion = 15;
    public static final String RESOURCE_TABLE = "resource_table";
    public static final String ACTIVITY_TABLE = "activity_table";
    public static final String RES_ACTIVITY_TABLE = "res_activity_table";

    public static final Map<String, ITable> tables;
    static {
        Map<String, ITable> table = new LinkedHashMap<>();
        table.put(RESOURCE_TABLE, new ResoursesTable(RESOURCE_TABLE));
        table.put(ACTIVITY_TABLE, new ActivityTable(ACTIVITY_TABLE));
        table.put(RES_ACTIVITY_TABLE, new ResActivityTable(RES_ACTIVITY_TABLE));
        tables = Collections.unmodifiableMap(table);
    }
}
