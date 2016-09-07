package com.example.timetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by XuYanping on 2016/9/2 16:34
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context mContext;

    private static final String CREATE_BOOK = "create table book ("
            + "id integer primary key autoincrement, "
            + "author text,"
            + "price real,"
            + "pages integer,"
            + "name text)";

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        onCreate(db);
    }
}
