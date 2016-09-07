package com.example.timetest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;

/**
 * Created by XuYanping on 2016/9/7 16:00
 */
public class NotificationUtil {

    private static NotificationUtil instance;
    private NotificationManager manager;
    private NotificationCompat.Builder builder;
    private Notification notification;
    private static final int DEFAULT_ID = 1;
    private Context mContext;

    public NotificationUtil(Context context) {

        this.mContext = context;
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(context);
    }

    //单例模式
    public static NotificationUtil getInstance(Context context) {

        if (instance == null) {
            instance = new NotificationUtil(context);
        }
        return instance;
    }

    public void showNotifi() {
        if (notification == null) {
            initNotifi();
        }
        manager.notify(DEFAULT_ID, notification);
    }

    public void closeNotifi() {
        cancelNotifi();
    }

    public void initNotifi() {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("host://anotherActivity"));
        builder.setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher))
                .setContentInfo("这是一条通知内容")
                .setContentTitle("通知标题")
                .setContentText("通知文本")
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT));
        notification = builder.build();
    }

    public void cancelNotifi() {
        manager.cancel(DEFAULT_ID);
    }
}
