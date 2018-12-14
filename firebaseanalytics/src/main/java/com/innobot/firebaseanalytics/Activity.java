package com.innobot.firebaseanalytics;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by innobot-linux-7 on 13/11/17,13,MyApplication.
 */

public class Activity extends AppCompatActivity implements View.OnClickListener {

    Button btn_login, btn_ForgotPassword, btn_Join_Team, btn_Create_Team;

    private FirebaseAnalytics mFBanalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
    }


    private void initview() {

        // Obtain the Firebase Analytics instance.
        mFBanalytics = FirebaseAnalytics.getInstance(this);
        //Sets whether analytics collection is enabled for this app on this device.
        mFBanalytics.setAnalyticsCollectionEnabled(true);
        //Sets the minimum engagement time required before starting a session. The default value is 10000 (10 seconds). Let's make it 20 seconds just for the fun
        mFBanalytics.setMinimumSessionDuration(10000);
        //Sets the duration of inactivity that terminates the current session. The default value is 1800000 (30 minutes).
        mFBanalytics.setSessionTimeoutDuration(500);


        btn_login = (Button) findViewById(R.id.btn_login);
        btn_ForgotPassword = (Button) findViewById(R.id.btn_ForgotPassword);
        btn_Join_Team = (Button) findViewById(R.id.btn_Join_Team);
        btn_Create_Team = (Button) findViewById(R.id.btn_Create_Team);


        btn_login.setOnClickListener(this);
        btn_ForgotPassword.setOnClickListener(this);
        btn_Join_Team.setOnClickListener(this);
        btn_Create_Team.setOnClickListener(this);

    }

    @SuppressLint("InvalidAnalyticsName")
    public void onClick(View v) {

        Bundle params = new Bundle();
        params.putInt("ButtonID", v.getId());
        String str_Eventname = " ";
        switch (v.getId()) {
            case R.id.btn_login:
                str_Eventname = "btn_login_release";
                Toast.makeText(this, "Login Succesfully", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_ForgotPassword:
                str_Eventname = "btn_ForgotPassword_release";
                Intent i = new Intent(Activity.this, SeccondActivity.class);
                startActivity(i);

                break;
            case R.id.btn_Join_Team:
                Intent i_join = new Intent(Activity.this, SeccondActivity.class);
                startActivity(i_join);
                str_Eventname = "btn_Join_Team_release";

                break;
            case R.id.btn_Create_Team:
                Intent i_team = new Intent(Activity.this, SeccondActivity.class);
                startActivity(i_team);
                str_Eventname = "btn_Create_Team_release";
                break;

        }

        mFBanalytics.logEvent(str_Eventname, params);
    }


}
