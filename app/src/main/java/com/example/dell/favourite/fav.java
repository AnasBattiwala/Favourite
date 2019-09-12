package com.example.dell.favourite;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class fav extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    String[] listitem;
    ListView listView1;
    TextView textView;
    Button b ;
    ArrayList<ModelList> controller = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        b = (Button)findViewById(R.id.btn);
        listView1 = (ListView)findViewById(R.id.listview);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //MainActivity.sqLiteDatabase=openOrCreateDatabase("Anas", Context.MODE_PRIVATE,null);
                //sqLiteDatabase.execSQL("create table if not exists fav(name varchar2(20) UNIQUE,flag number(1))");

                Cursor c=MainActivity.sqLiteDatabase.rawQuery("select * from fav where flag=1",null);
                if(c != null)
                {
                    while(c.moveToNext()){
                        String name = c.getString(c.getColumnIndex("name"));
                        int flag = c.getInt(c.getColumnIndex("flag"));
                        ModelList m = new ModelList(flag,name);
                        controller.add(m);
                        // use these strings as you want
                    }
                }
                Adapter adapter=new Adapter(getApplicationContext(),controller);
                listView1.setAdapter(adapter);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}
