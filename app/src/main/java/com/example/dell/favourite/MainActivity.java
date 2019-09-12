package com.example.dell.favourite;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button button;
    String[] listitem;
    ArrayList<ModelList> controller = new ArrayList<>();
    static SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button) findViewById(R.id.button);
        listView= (ListView) findViewById(R.id.listview);
        listitem = getResources().getStringArray(R.array.subjects);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                sqLiteDatabase=openOrCreateDatabase("Anas",Context.MODE_PRIVATE,null);
                sqLiteDatabase.execSQL("create table if not exists fav(name varchar2(20) UNIQUE,flag number(1))");
                for (String aListitem : listitem) {
                    try {
                        sqLiteDatabase.execSQL("insert into fav values('" + aListitem + "',0)");
                    }catch (Exception e){}
                }
                Cursor c=sqLiteDatabase.rawQuery("select * from fav",null);
                if(c != null)
                {
                    while(c.moveToNext()){

                        String name = c.getString(c.getColumnIndex("name"));
                        int flag = c.getInt(c.getColumnIndex("flag"));
                        ModelList m = new ModelList(flag,name);
                        Log.e("ghusa","ok"+m.getname());
                        controller.add(m);
                    }
                }
                Log.e("cont",""+controller.isEmpty());
                Adapter adapter=new Adapter(getApplicationContext(),controller);
                listView.setAdapter(adapter);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),fav.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
        //System.exit(0);
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
        //super.onBackPressed();
    }
}
