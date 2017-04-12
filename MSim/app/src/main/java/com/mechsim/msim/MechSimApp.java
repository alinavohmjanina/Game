package com.mechsim.msim;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class MechSimApp extends Application {
    private static int stateCounter;
    private String TAG = "MechSimApp";
    public DBHelper dbHelper;
    public static Context context;
    private Handler uiHandler;
    private Handler handler;
    private Map<String, Thread> threads = new HashMap<>();
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        dbHelper = DBHelper.getInstance(context);
        uiHandler = new Handler(context.getMainLooper());
        handler = new Handler();
        initThreads();
        Log.d(TAG,"onCreate");
    }

    @Override
    public void onTerminate() {
        for (Thread t : threads.values()){
            t.interrupt();
        }
        super.onTerminate();
    }

    private void initThreads(){
        Cursor cursor = getAllTableRows();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndex(ITable.COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(ResoursesTable.COL_RESNAME));
                int period = cursor.getInt(cursor.getColumnIndex(ResoursesTable.COL_PERIOD));

                threads.put(name, createThread(id, name, period));

                cursor.moveToNext();
            }
        }
    }

    private Thread createThread(final int id, final String name, final int period) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // DO your work here
                // get the data
//                if (MechSimApp.isApplicationOnBackground()) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            //uddate UI
//
//                        }
//                    });
//                }
                Log.i(TAG, "thread: " + name + ", period: " + period);
                handler.postDelayed(this, period);
            }
        });
        handler.postDelayed(thread, period);
        return thread;
    }

    private Cursor getAllTableRows(){
        return Utils.withDB(new IFunctionDB<Cursor>() {
            @Override
            public Cursor exec(SQLiteDatabase db) {
                return db.rawQuery("SELECT * FROM " + DBProvider.RESOURCE_TABLE, null);
            }
        });
    }

    private void runOnUiThread(Runnable r) {
        uiHandler.post(r);
    }

    public static Context getContext() {
        return context;
    }

    public static boolean isApplicationOnBackground()
    {
        return stateCounter == 0;
    }

    //to be called on each Activity onStart()
    public static void activityStarted()
    {
        stateCounter++;
    }

    //to be called on each Activity onStop()
    public static void activityStopped()
    {
        stateCounter--;
    }
}
