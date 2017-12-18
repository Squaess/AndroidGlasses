package com.example.squaess.androidglasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.squaess.androidglasses.ProductsActivityPackage.ItemModel;

import java.util.List;

/**
 * Created by Marcin on 2017-12-18.
 */

public class HistoryListAdapter extends ArrayAdapter<ItemModel> {

    private List<ItemModel> products;
    private LayoutInflater inflater;
    private int layoutId;

    public HistoryListAdapter(Context context, int layoutId, List<ItemModel> products){
        super(context, layoutId, products);
        this.products = products;
        this.layoutId = layoutId;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int index, View row, ViewGroup parent){
        if (row == null){
            row = inflater.inflate(layoutId, parent, false);
        }
/*
            TextView nameOfProduct = row.findViewById(R.id.nameOfProduct);
            nameOfProduct.setText(products.get(index).getName());

  */
            return row;
        }



}
