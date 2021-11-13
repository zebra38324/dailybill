package com.example.dailybill;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static int Version = 1;

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SQLiteHelper(Context context, String name, int version) {
        this(context, name, null, version);
    }

    public SQLiteHelper(Context context, String name) {
        this(context, name, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("创建数据库");
        String sql = "create table bill(Id integer primary key autoincrement, date Date, category String, amount Float)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // String sql = "alter table bill add sum double";
        // db.execSQL(sql);
        System.out.println("更新数据库版本为：" + newVersion);
    }
}
