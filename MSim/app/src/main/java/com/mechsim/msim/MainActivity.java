package com.mechsim.msim;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        initButtons();
    }

    private void initButtons(){
        final Activity thisActivity = this;
        Button continueBtn = (Button) this.findViewById(R.id.continueBtn);
        Button newGameBtn = (Button) this.findViewById(R.id.newGame);
        Button helpBtn = (Button) this.findViewById(R.id.help);
        Button saveBtn = (Button) this.findViewById(R.id.save);
        Button loadBtn = (Button) this.findViewById(R.id.load);
        Button rateGameBtn = (Button) this.findViewById(R.id.rateGame);

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Continue Btn");
            }
        });
        newGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.startActivity(thisActivity, GameActivity.class, null);
            }
        });

    }

    @Override
    public void onStart()
    {
        super.onStart();
        MechSimApp.activityStarted();
    }

    @Override
    public void onStop()
    {
        MechSimApp.activityStopped();
        super.onStop();
    }


}
