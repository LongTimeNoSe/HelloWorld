package com.example.timetest;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SaveDataActivity extends AppCompatActivity {

    private EditText et;
    private TextView tv_show;
    private MyDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        helper = new MyDatabaseHelper(SaveDataActivity.this, "BookStore.db", null, 1);
        et = (EditText) findViewById(R.id.et_content);
        tv_show = (TextView) findViewById(R.id.tv_show);
        String content = loadData();
        if (!TextUtils.isEmpty(content)) {
            et.setText(content);
        }
    }

    public void save(View view) {
        String data = et.getText().toString();
        saveData(data);
        sharedSaveData(data, 24, false);
        Toast.makeText(SaveDataActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    public void showData(View view) {
        sharedShowData();
        Toast.makeText(SaveDataActivity.this, "显示成功", Toast.LENGTH_SHORT).show();
    }

    public void createDatabase(View view) {
        helper.getWritableDatabase();
        Log.d("dataBase", "数据库创建");
        queryData();
    }

    public void insertData(View view) {
        insertData();
        queryData();
    }

    public void updateData(View view) {
        updateData();
        queryData();
    }

    public void deleteData(View view) {
        deleteData();
        queryData();
    }

    public void queryData(View view) {
        queryData();
    }

    private void insertData() {
        SQLiteDatabase db = helper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("name", "jack");
//        values.put("author", "Dan Brown");
//        values.put("pages", 666);
//        values.put("price", 250);
//        db.insert("book", null, values);
//        values.clear();
        db.execSQL("insert into book (name,author,pages,price) values(?, ?, ?, ?)", new String[]{"jack", "Dan Brown", "666", "250"});
        Log.d("dataBase", "数据库插入");
    }

    private void updateData() {
        SQLiteDatabase db = helper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("price", 18.88);
//        db.update("book", values, "name = ?", new String[]{"jack"});
//        values.clear();
        db.execSQL("update book set price = ? where name = ?", new String[]{"18.88", "jack"});
        Log.d("dataBase", "数据库更新");
    }

    private void deleteData() {
        SQLiteDatabase db = helper.getWritableDatabase();
//        db.delete("book", "pages > ?", new String[]{"600"});
        db.execSQL("delete from book where pages > ?", new String[]{"500"});
        Log.d("dataBase", "数据库删除");
    }

    private void queryData() {
        tv_show.setText("");
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query("book", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                Double price = cursor.getDouble(cursor.getColumnIndex("price"));
                tv_show.setText(name + "\n" + author + "\n" + pages + "\n" + price);
            } while (cursor.moveToNext());
        }
        cursor.close();
//        Cursor c = db.rawQuery("select * from book", null);
//        tv_show.setText(c.toString());
        Log.d("dataBase", "数据库查询");
    }


    //IO流保存、读取数据
    private void saveData(String data) {

        FileOutputStream os = null;
        BufferedWriter writer = null;
        try {
            os = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(os));
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String loadData() {

        FileInputStream is = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();

        try {
            is = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    //SharedPreferences保存、读取数据
    private void sharedSaveData(String key1, int key2, Boolean key3) {

        SharedPreferences.Editor editor = getSharedPreferences("userInfo", Context.MODE_PRIVATE).edit();
        editor.putString("name", key1);
        editor.putInt("age", key2);
        editor.putBoolean("married", key3);
        editor.commit();
    }

    private void sharedShowData() {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String key1 = sharedPreferences.getString("name", "");
        int key2 = sharedPreferences.getInt("age", 0);
        Boolean key3 = sharedPreferences.getBoolean("married", false);

        tv_show.setText(key1 + "\n" + key2 + "\n" + key3);
    }
}
