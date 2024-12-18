package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class InventoryAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> inventoryData;
    private LayoutInflater inflater;

    public InventoryAdapter(Context context, ArrayList<String> inventoryData) {
        this.context = context;
        this.inventoryData = inventoryData;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return inventoryData.size();
    }

    @Override
    public Object getItem(int position) {
        return inventoryData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_item, parent, false);
        }

        TextView itemTextView = convertView.findViewById(R.id.itemTextView);
        TextView dateTextView = convertView.findViewById(R.id.dateTextView);
        TextView quantityTextView = convertView.findViewById(R.id.quantityTextView);

        // Get the data for the current item
        String itemData = inventoryData.get(position);
        String[] itemParts = itemData.split(" \\| ");

        itemTextView.setText(itemParts[0]);
        dateTextView.setText(itemParts[1]);
        quantityTextView.setText(itemParts[2]);

        return convertView;
    }
}