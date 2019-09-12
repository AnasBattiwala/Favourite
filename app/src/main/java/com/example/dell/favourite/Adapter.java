package com.example.dell.favourite;

import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

/**
 * Created by DELL on 06-09-2019.
 */

public class Adapter extends BaseAdapter {
    Context context;
    ArrayList<ModelList> arrayList;
    Adapter(Context context, ArrayList<ModelList> arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.list,parent,false);
        TextView textView=(TextView)view.findViewById(R.id.textview);
        textView.setText(arrayList.get(position).getname());
        ToggleButton tg = (ToggleButton)view.findViewById(R.id.tgb);
        if(arrayList.get(position).getFlag()==1){
            tg.setChecked(true);
        }
        tg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Log.e("yaha","ok flag 1");
                    MainActivity.sqLiteDatabase.execSQL("update fav set flag=1 where name='"+arrayList.get(position).getname()+"'");
                }else{
                    Log.e("yaha","ok");
                    MainActivity.sqLiteDatabase.execSQL("update fav set flag=0 where name='"+arrayList.get(position).getname()+"'");
                }
            }
        });
        return view;
    }
}
