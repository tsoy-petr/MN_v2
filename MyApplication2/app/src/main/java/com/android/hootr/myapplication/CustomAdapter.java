package com.android.hootr.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomAdapter extends ArrayAdapter<String> {

    private Context context;
    private int textViewResourceId;
    private String[] objects;
    public static boolean flag = false;

    public CustomAdapter(Context context, int textViewResourceId,
                         String[] objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.textViewResourceId = textViewResourceId;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = View.inflate(context, textViewResourceId, null);
        if (flag != false) {
            TextView tv = (TextView) convertView;
            tv.setText(objects[position]);
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TextView row = (TextView) convertView;
        if (row == null) {
            row = (TextView) View.inflate(context, R.layout.support_simple_spinner_dropdown_item, null);
        }

        row.setText(objects[position]);

        return super.getDropDownView(position, convertView, parent);
    }
}
