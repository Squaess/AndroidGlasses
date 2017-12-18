package com.example.squaess.androidglasses;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.squaess.androidglasses.ProductsActivityPackage.ItemModel;

import java.util.List;

/**
 * Created by Marcin on 2017-12-18.
 */

public class CartListAdapter extends ArrayAdapter<ItemModel> {

    private List<ItemModel> items;
    private LayoutInflater inflater;
    private int layoutId;

    public CartListAdapter(Context context, int layoutId, List<ItemModel> items){
        super(context, layoutId, items);
        this.items = items;
        this.layoutId = layoutId;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int index, View row, ViewGroup parent){
        if (row == null){
            row = inflater.inflate(layoutId, parent, false);
        }

        TextView nameOfProduct = row.findViewById(R.id.item_name);
        nameOfProduct.setText(items.get(index).getItem_name());

        TextView correction = row.findViewById(R.id.correction);
        correction.setText(Double.toString(items.get(index).getCorrection()));

        TextView price = row.findViewById(R.id.item_price);
        price.setText(Double.toString(items.get(index).getPrice()));


        new DownloadImageTask((ImageView) row.findViewById(R.id.productImage))
                .execute(items.get(index).getUrl());

        return row;
    }




}
