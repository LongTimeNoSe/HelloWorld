package com.example.timetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

    }

    public void sendNotifi(View view) {

        NotificationUtil.getInstance(this).showNotifi();
    }

    public void cancelNotifi(View view) {
        NotificationUtil.getInstance(this).cancelNotifi();
    }
}
