package com.example.dailybill;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BillSQLite extends SQLiteHelper {
    private Context context;
    private static int version = 1;
    private String name;
    private SQLiteHelper dbHelper;

    public BillSQLite(Context context, String name, int version) {
        super(context, name, version);
        this.context = context;
        BillSQLite.version = version;
        this.name = name;
        dbHelper = new SQLiteHelper(context, name, version);
        SQLiteDatabase billDatabase = dbHelper.getWritableDatabase();
    }

    public BillSQLite(Context context, String name) {
        this(context, name, version);
    }

    public ArrayList<Map<String, String>> query() {
        SQLiteDatabase billDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = billDatabase.query("bill", new String[]{"id", "date", "category", "amount"},
                "id=?", new String[]{"1"}, null, null, null);
        return cursor2list(cursor);
    }

    private ArrayList<Map<String, String>> cursor2list(Cursor cursor) {
        ArrayList<Map<String, String>> list = new ArrayList<Map<String,String>>();
        while (cursor.moveToNext()) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", cursor.getString(0));
            map.put("date", cursor.getString(1));
            map.put("category", cursor.getString(2));
            map.put("amount", cursor.getString(3));
            list.add(map);
        }
        return list;
    }

}
