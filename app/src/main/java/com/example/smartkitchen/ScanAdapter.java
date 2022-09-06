package com.example.smartkitchen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ScanAdapter extends ArrayAdapter<ScanItem> {
    public ScanAdapter(@NonNull Context context, ArrayList<ScanItem> users){
        super(context, 0, users);
    }
//  Adapter untuk menampilkan bluetooth yang sudah di scan
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ScanItem item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_scan_layout, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_scanlist);
        // Populate the data into the template view using the data object
        tvName.setText(ScanItem.device_name + " " + ScanItem.device_mac);
        // Return the completed view to render on screen
        return convertView;
    }

}
