package com.example.tokov2.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.tokov2.R;

public class ListviewAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    String data[];

    public ListviewAdapter(Context context, String[] data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.card_list, null);
        TextView txtdataname = (TextView) view.findViewById(R.id.txtdataName);
        txtdataname.setText(data[i]);
        return view;
    }
}
