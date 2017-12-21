package com.nzy.zkyt.store_wintec.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nzy.zkyt.store_wintec.model.SearchHistoryItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017/12/20.
 */

public class DatabaseManager {

    private static DatabaseHelper dbHelper;
    private volatile static DatabaseManager mInstance;

    private DatabaseManager(Context context){
        dbHelper = new DatabaseHelper(context);
    }

    /**
     * 获取实例
     * @param context
     * @return
     */
    public static DatabaseManager getmInstance(Context context){
        DatabaseManager inst = mInstance;
        if(mInstance == null){
            synchronized (DatabaseManager.class){
                inst = mInstance;
                if(inst == null){
                    inst = new DatabaseManager(context);
                    mInstance = inst;
                }
            }
        }
        return inst;
    }

    /**
     * 获取历史记录
     * @return
     */
    public List<SearchHistoryItem> getSearchHistory(){
        List<SearchHistoryItem> list = new ArrayList<SearchHistoryItem>();
        String sql = "select ID,KEYWORD from table_history order by ID desc limit 10";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        try{
            cursor = db.rawQuery(sql, null);
            while(cursor.moveToNext()){
                SearchHistoryItem item = new SearchHistoryItem();
                item.id = cursor.getInt(0);
                item.keyword = cursor.getString(1);
                list.add(item);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            cursor.close();
            db.close();
        }

        return list;
    }

    /**
     * 插入搜索记录
     * @param keyword
     */
    public void insertSearchHistory( String keyword ){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "insert into table_history (KEYWORD) " +
                "values (?) ";
        try {
            db.execSQL(sql, new String[]{keyword});
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    /**
     * 删除搜索记录
     * @param id
     */
    public void deleteSearchHistory( int id ) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "delete from table_history where ID=" + id;
        try {
            db.execSQL(sql);
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

}
