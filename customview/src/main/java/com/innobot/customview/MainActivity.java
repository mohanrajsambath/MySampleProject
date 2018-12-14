package com.innobot.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private CleanableEditText mUserNameCleanableEditText;
    private CleanableEditText mPassWordCleanableEditText;
    private Button mLoginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }



    private void init(){
        mUserNameCleanableEditText=(CleanableEditText) findViewById(R.id.eTxt_userNameEditText);
        mPassWordCleanableEditText=(CleanableEditText) findViewById(R.id.eTxt_passwordEditText);
        mLoginButton=(Button) findViewById(R.id.loginButton);
        mLoginButton.setOnClickListener(new OnClickListenerImpl());
    }

    private class OnClickListenerImpl implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (TextUtils.isEmpty(mUserNameCleanableEditText.getText())) {
                mUserNameCleanableEditText.setShakeAnimation();
                Toast.makeText(MainActivity.this, "Please enter your username", Toast.LENGTH_SHORT).show();
            }

            if (TextUtils.isEmpty(mPassWordCleanableEditText.getText())) {
                mPassWordCleanableEditText.setShakeAnimation();
                Toast.makeText(MainActivity.this, "Please enter the password", Toast.LENGTH_SHORT).show();
            }
        }

    }








}
