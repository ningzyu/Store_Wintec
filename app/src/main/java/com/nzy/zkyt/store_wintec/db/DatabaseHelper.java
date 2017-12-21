package com.nzy.zkyt.store_wintec.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 2017/12/20.
 * sqlite助手类
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DBNAME = "db_wintec";

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建搜索历史记录表
        String sql = "CREATE TABLE table_history (" +
                "  ID integer PRIMARY KEY AUTOINCREMENT," +
                "  KEYWORD varchar(100) NOT NULL" +
                " " +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
