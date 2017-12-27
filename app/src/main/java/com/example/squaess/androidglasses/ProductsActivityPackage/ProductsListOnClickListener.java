package com.example.squaess.androidglasses.ProductsActivityPackage;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * Created by silentnauscopy on 12/26/17.
 */

public class ProductsListOnClickListener implements AdapterView.OnItemClickListener {

    private Context context;
    public OnItemClick mCallback;

    public ProductsListOnClickListener(Context context){
        this.context = context;
        try {
            mCallback = (OnItemClick) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString()+
            "must implement OnSendText()");
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mCallback.onSentText(i);
        Toast.makeText(context, i+" | "+l, Toast.LENGTH_SHORT).show();
    }

    public interface OnItemClick {
        void onSentText(int row);
    }
}
