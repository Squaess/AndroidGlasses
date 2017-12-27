package com.example.squaess.androidglasses.ProductsActivityPackage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.squaess.androidglasses.R;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

/**
 * Created by silentnauscopy on 12/12/17.
 */

public class ProductListItemRowAdapter extends ArrayAdapter<ItemModel> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<ItemModel> data = null;

    public ProductListItemRowAdapter(Context context, int layoutResourceId,
                              ArrayList<ItemModel> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        RowBeanHolder rowBeanHolder;

        if (row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            rowBeanHolder = new RowBeanHolder();
            rowBeanHolder.imageView = row.findViewById(R.id.productImage);
            rowBeanHolder.name = row.findViewById(R.id.productName);
            rowBeanHolder.price = row.findViewById(R.id.productPrice);

            row.setTag(rowBeanHolder);
        } else {
            rowBeanHolder = (RowBeanHolder) row.getTag();
        }

        ItemModel object = data.get(position);
        rowBeanHolder.name.setText(object.getItem_name());
        rowBeanHolder.price.setText(String.valueOf(object.getPrice()));
        if(!object.isAvailable()){
            rowBeanHolder.name.setTextColor(Color.RED);
            rowBeanHolder.price.setTextColor(Color.RED);
        }

        RotateAnimation rotateAnimation = new RotateAnimation(30, 90,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        Ion.with(context)
                .load(object.getUrl())
                .withBitmap()
                .placeholder(R.drawable.twitter)
                .error(R.drawable.ic_error_web)
                .animateLoad(rotateAnimation)
                .intoImageView(rowBeanHolder.imageView);

        return row;
    }

    private static class RowBeanHolder{
        ImageView imageView;
        TextView name;
        TextView price;
    }
}
