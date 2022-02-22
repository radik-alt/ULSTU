package com.example.gameulstu.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DbManager {

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public DbManager (Context context) {
        dbHelper = new DBHelper(context);
    }

    public DbManager open () {
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close () {
        dbHelper.close();
    }

    private Cursor getAll () {
        String[] columns = new String[] {DBHelper._ID, DBHelper.Date, DBHelper.ball};
        return database.query(DBHelper.TABLE_NAME, null, null, null
                , null, null, null);
    }

    public List<Model> getModel () {
        ArrayList<Model> models = new ArrayList<>();
        Cursor cursor = getAll();
        while (cursor.moveToNext()){
            int id = cursor.getInt((int)cursor.getColumnIndex(DBHelper._ID));
            String date = cursor.getString((int)cursor.getColumnIndex(DBHelper.Date));
            String scores = cursor.getString((int)cursor.getColumnIndex(DBHelper.ball));
            models.add(new Model(id, date, scores));
        }
        cursor.close();
        return models;
    }

    public long getCount(){
        return DatabaseUtils.queryNumEntries(database, DBHelper.TABLE_NAME);
    }

    public long insert(Model model){
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.Date, model.getDate());
        cv.put(DBHelper.ball, model.getScores());

        return  database.insert(DBHelper.TABLE_NAME, null, cv);
    }
}
