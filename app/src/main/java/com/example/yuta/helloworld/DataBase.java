package com.example.yuta.helloworld;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yuta on 2014/11/28.
 */
public class DataBase extends SQLiteOpenHelper{
    public DataBase(Context context){
        super(context,"database",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table "+"testTable"+" ( _id text , data text not null );");
        //db.execSQL("create table "+"testTable"+" ( _id text primary key autoincrement, data text not null );");
    }

    public void onCreate(SQLiteDatabase db,String tableName) {
        db.execSQL("create table "+tableName+" ( _id integer primary key autoincrement, data integer not null );");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //デバッグ用にテーブルの削除
        db.execSQL("drop table testTable");
        onCreate(db);
    }

    public boolean testOutPut(String in){
        if(in.equals( "0123456789123"))
            return true;
        return false;
    }
}
