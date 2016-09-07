package com.example.timetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ResultIntentActivity extends AppCompatActivity {

//    private IntentFilter intentFilter;
//    private netWorkChangeReceiver networkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        intentFilter = new IntentFilter();
//        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        networkChangeReceiver = new netWorkChangeReceiver();
//        registerReceiver(networkChangeReceiver, intentFilter);

    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        unregisterReceiver(networkChangeReceiver);
//    }


//    class netWorkChangeReceiver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//            if (networkInfo != null && networkInfo.isAvailable()) {
//                Log.d("netState", "network is available");
//                Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();
//            } else {
//                Log.d("netState", "network is unavailable");
//                Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    public void sendBroadCast(View view) {
        Intent intent = new Intent("com.example.timetest.FORCE_OFFLINE");
        sendBroadcast(intent);
    }

    public void back(View view) {
        Intent intent = new Intent();
        intent.putExtra("backData", "你是大煞笔。。。");
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("backData", "你也是大煞笔。。。");
        setResult(RESULT_OK, intent);
        finish();
    }
}
