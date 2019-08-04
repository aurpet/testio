package com.example.testio.dataListHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.testio.R;

import java.util.ArrayList;

public class DataAdapter extends ArrayAdapter<DataObject> {

    private ArrayList<DataObject> data;
    private int lastPosition = -1;

    public DataAdapter (Context c, ArrayList<DataObject> listData){
        super(c, R.layout.custom_list_view, listData);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final DataObject data = getItem(position);
        final ViewHolder viewHolder;
        final View result;
        String countryName, distance;

        if (convertView == null){
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_list_view, parent, false);
            viewHolder.tvCountryName = convertView.findViewById(R.id.tv_country_name);
            viewHolder.tvDistance = convertView.findViewById(R.id.tv_distance);

            result = convertView;
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        lastPosition = position;

        countryName = data.getCountryName();
        distance = data.getDistance();

        viewHolder.tvCountryName.setText(countryName);
        viewHolder.tvDistance.setText(distance);
        return convertView;
    }
}
