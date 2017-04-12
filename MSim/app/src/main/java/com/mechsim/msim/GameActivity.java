package com.mechsim.msim;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class GameActivity extends AppCompatActivity {
    private int fragmentsContainerId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        fragmentsContainerId = R.id.frameLayout;
    }

    @Override
    protected void onPostResume() {
        final Activity activity = this;
        super.onPostResume();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, OverViewFragment.newInstance("view 1"));
        fragmentTransaction.commit();

        Button b1 = (Button) this.findViewById(R.id.b1);
        Button b2 = (Button) this.findViewById(R.id.b2);
        Button b3 = (Button) this.findViewById(R.id.b3);
        Button b4 = (Button) this.findViewById(R.id.b4);
        Button b5 = (Button) this.findViewById(R.id.b5);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, OverViewFragment.newInstance("view 1"));
                fragmentTransaction.commit();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, OverViewFragment.newInstance("view 2"));
                fragmentTransaction.commit();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, OverViewFragment.newInstance("view 3"));
                fragmentTransaction.commit();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, OverViewFragment.newInstance("view 4"));
                fragmentTransaction.commit();
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, OverViewFragment.newInstance("view 5"));
                fragmentTransaction.commit();
            }
        });

    }

}
