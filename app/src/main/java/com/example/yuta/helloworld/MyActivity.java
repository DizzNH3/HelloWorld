package com.example.yuta.helloworld;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.app.AlertDialog;
import android.widget.Toast;
import android.content.ContentValues;



public class MyActivity extends Activity {

    DataBase db;
    SQLiteDatabase mydb;
    Context thiscontext;

    void init(){
        db = new DataBase(this);
        mydb = db.getWritableDatabase();
        thiscontext = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my);

        init();

        Button b_ManualInput = (Button)findViewById(R.id.Manual_Input_Bu);
        b_ManualInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tenKeyDialog();
            }
        });
        Button b_AllClear = (Button)findViewById(R.id.All_Clear_Bu);


    }

    public void deleteAllItem(){
        mydb.delete("testTable",null,null);
    }


    
    public void showToastMessage(String str,boolean isLong){
        if(isLong)
            Toast.makeText(this,str,Toast.LENGTH_LONG).show();
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();

        return;
    }

    boolean isPOSItemDetected;

    void btnClick(final Button btn,View vieww)
    {
        final String t = btn.getText().toString();
        final View view=vieww;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv=(TextView)view.findViewById(R.id.title);
                String dmy=tv.getText().toString();
                if(t.equals("C")) {//button cが押されたら
                    tv.setText("");
                }else{ //それ以外の数字のキーが押されたら
                    if(dmy.length()>=13)
                        dmy = "";

                    //テキストの追加
                    dmy+=t;
                    tv.setText(dmy);

                    if(dmy.length()>=13){
                        if(db.testOutPut(dmy.toString())) {
                            showToastMessage("一致", false);
                            isPOSItemDetected = true;
                            ContentValues cv = new ContentValues();
                            cv.put("_id", "12345567");
                            cv.put("data","12387");

                            mydb.insert("testTable",null,cv);
                            Cursor c = mydb.query("testTable",new String[]{"_id","data"},null,null,null,null,null);
                            c.moveToFirst();
                            //showToastMessage(c.getString(1),false);
                            ScrollView sv = (ScrollView)findViewById(R.id.scrollView);
                            TextView item = new TextView(thiscontext);

                            item.setText(c.getString(0));
                            sv.addView(item);


                            dialog.dismiss();
                            return;
                        }

                        showToastMessage("該当商品は存在ありません", false);

                    }

                }
            }
        });
    }

    AlertDialog dialog;

    public void tenKeyDialog()
    {
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.number4barcode, null, false);
        TextView ttl=(TextView)view.findViewById(R.id.title);
        ttl.setText("");
        Button b_0=(Button)view.findViewById(R.id.b_00);btnClick(b_0,view);
        Button b_1=(Button)view.findViewById(R.id.b_01);btnClick(b_1,view);
        Button b_2=(Button)view.findViewById(R.id.b_02);btnClick(b_2,view);
        Button b_3=(Button)view.findViewById(R.id.b_03);btnClick(b_3,view);
        Button b_4=(Button)view.findViewById(R.id.b_04);btnClick(b_4,view);
        Button b_5=(Button)view.findViewById(R.id.b_05);btnClick(b_5,view);
        Button b_6=(Button)view.findViewById(R.id.b_06);btnClick(b_6,view);
        Button b_7=(Button)view.findViewById(R.id.b_07);btnClick(b_7,view);
        Button b_8=(Button)view.findViewById(R.id.b_08);btnClick(b_8,view);
        Button b_9=(Button)view.findViewById(R.id.b_09);btnClick(b_9,view);
        Button b_c=(Button)view.findViewById(R.id.b_c);btnClick(b_c,view);
        Button b_search=(Button)view.findViewById(R.id.b_search);
        Button b_cancel=(Button)view.findViewById(R.id.b_cancel);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.MyDialog);
        //—–以下がきも———————————-
        builder.setView(view);
        // 次にbuilder内にdialogを作成表示する
        dialog = builder.create();
        //dialog.setTitle(“天皇代入力(1-123)”);
        dialog.show();

        b_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });
        b_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.enter_pos_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id ==R.id.regPOSItem)
        {
            Intent intent = new Intent();
            intent.setClassName("com.example.yuta.helloworld","com.example.yuta.helloworld.EditStoreItem");
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
