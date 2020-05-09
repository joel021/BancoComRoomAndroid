package com.persistencia.room.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.persistencia.room.R;
import com.persistencia.room.model.Headset;

import java.util.List;

public class HeadsetAdapter extends BaseAdapter {
    private Context context;
    private List<Headset> list;

    public HeadsetAdapter(Context context, List<Headset> lista){
        this.context = context;
        this.list = lista;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Headset headset = list.get(position);
        View layout;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.item_readset_list_view, null);
        } else {
            layout = convertView;
        }

        TextView title = layout.findViewById(R.id.textViewTitle);
        title.setText(headset.title);

        TextView model = layout.findViewById(R.id.textViewModel);
        model.setText(headset.model);

        TextView price = layout.findViewById(R.id.textViewPrice);
        price.setText("R$"+headset.price);

        return layout;
    }

}