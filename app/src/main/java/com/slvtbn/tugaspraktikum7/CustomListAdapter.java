package com.slvtbn.tugaspraktikum7;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Skincare> Skincare ;
    public CustomListAdapter(Activity activity, List<Skincare> Skincare) {
        this.activity = activity;
        this.Skincare = Skincare;
    }
    @Override
    public int getCount() {
        return Skincare.size();
    }

    @Override
    public Object getItem(int location) {
        return Skincare.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_list, null);

            TextView nama = (TextView) convertView.findViewById(R.id.text_nama);
            TextView harga = (TextView) convertView.findViewById(R.id.text_harga);

            ImageView imageView = (ImageView) convertView.findViewById(R.id.iconid);
            Skincare s = Skincare.get(position);

            nama.setText("Nama  : " + s.get_nama());
            harga.setText("Kelas : " + s.get_harga());

        }
        return convertView;
    }
}
