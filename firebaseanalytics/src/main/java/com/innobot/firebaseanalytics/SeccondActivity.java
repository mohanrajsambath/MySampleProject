package com.innobot.firebaseanalytics;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by innobot-linux-7 on 30/10/17,30,MyApplication.
 */

public class SeccondActivity extends Activity implements View.OnClickListener {


    private FirebaseAnalytics mFBanalytics;
    private TextView txtView_video;
    private TextView txtView_audio;
    private TextView txtView_link, txtView_library;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initview();

//        // Obtain the Firebase Analytics instance.
//        mFBanalytics = FirebaseAnalytics.getInstance(this);
//        //Sets whether analytics collection is enabled for this app on this device.
//        mFBanalytics.setAnalyticsCollectionEnabled(true);
//        //Sets the minimum engagement time required before starting a session. The default value is 10000 (10 seconds). Let's make it 20 seconds just for the fun
//        mFBanalytics.setMinimumSessionDuration(10000);
//        //Sets the duration of inactivity that terminates the current session. The default value is 1800000 (30 minutes).
//        mFBanalytics.setSessionTimeoutDuration(500);


    }

    private void initview() {

        txtView_video = (TextView) findViewById(R.id.txtView_video);
        txtView_audio = (TextView) findViewById(R.id.txtView_audio);
        txtView_library = (TextView) findViewById(R.id.txtView_library);
        txtView_link = (TextView) findViewById(R.id.txtView_link);

        txtView_video.setOnClickListener(this);
        txtView_audio.setOnClickListener(this);
        txtView_library.setOnClickListener(this);
        txtView_link.setOnClickListener(this);

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtView_video:
                txtView_video.setBackground(getDrawable(R.drawable.buttoncorner_channel_pressed));
                txtView_audio.setBackgroundColor(Color.parseColor("#3F51B5"));
                txtView_library.setBackgroundColor(Color.parseColor("#3F51B5"));
                txtView_link.setBackgroundColor(Color.parseColor("#3F51B5"));


                txtView_video.setTextColor(Color.parseColor("#FF4081"));
                txtView_audio.setTextColor(Color.parseColor("#000000"));
                txtView_library.setTextColor(Color.parseColor("#000000"));
                txtView_link.setTextColor(Color.parseColor("#000000"));


                break;
            case R.id.txtView_audio:

                txtView_audio.setBackground(getDrawable(R.drawable.buttoncorner_channel_pressed));
                txtView_library.setBackgroundColor(Color.parseColor("#3F51B5"));
                txtView_link.setBackgroundColor(Color.parseColor("#3F51B5"));
                txtView_video.setBackgroundColor(Color.parseColor("#3F51B5"));

                txtView_audio.setTextColor(Color.parseColor("#FF4081"));
                txtView_library.setTextColor(Color.parseColor("#000000"));
                txtView_link.setTextColor(Color.parseColor("#000000"));
                txtView_video.setTextColor(Color.parseColor("#000000"));

                break;
            case R.id.txtView_library:

                txtView_library.setBackground(getDrawable(R.drawable.buttoncorner_channel_pressed));
                txtView_link.setBackgroundColor(Color.parseColor("#3F51B5"));
                txtView_video.setBackgroundColor(Color.parseColor("#3F51B5"));
                txtView_audio.setBackgroundColor(Color.parseColor("#3F51B5"));

                txtView_library.setTextColor(Color.parseColor("#FF4081"));
                txtView_link.setTextColor(Color.parseColor("#000000"));
                txtView_video.setTextColor(Color.parseColor("#000000"));
                txtView_audio.setTextColor(Color.parseColor("#000000"));

                break;
            case R.id.txtView_link:

                txtView_link.setBackground(getDrawable(R.drawable.buttoncorner_channel_pressed));
                txtView_video.setBackgroundColor(Color.parseColor("#3F51B5"));
                txtView_audio.setBackgroundColor(Color.parseColor("#3F51B5"));
                txtView_library.setBackgroundColor(Color.parseColor("#3F51B5"));

                txtView_link.setTextColor(Color.parseColor("#FF4081"));
                txtView_video.setTextColor(Color.parseColor("#000000"));
                txtView_audio.setTextColor(Color.parseColor("#000000"));
                txtView_library.setTextColor(Color.parseColor("#000000"));


                break;
            default:
                break;

        }
    }
}