package com.example.timetest;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by XuYanping on 2016/8/30 11:47
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    public void finishAll() {
        ActivityCollector.finishAllActivity();
    }
}
