package com.example.yuta.helloworld;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yuta on 2015/02/20.
 */
public class StoreItem extends SQLiteOpenHelper {
    public StoreItem(Context context){
        super(context,"POS.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table "+"m_StoreItem"+" ( itemCode text primary key,itemName text, priceWithoutTax REAL not null,tax REAL not null,taxType integer nut null);");
        //db.execSQL("create table "+"testTable"+" ( _id text primary key autoincrement, data text not null );");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //デバッグ用にテーブルの削除
        db.execSQL("drop table m_StoreItem");
        onCreate(db);
    }
}
