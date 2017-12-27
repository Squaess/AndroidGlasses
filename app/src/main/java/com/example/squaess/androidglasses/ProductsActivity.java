package com.example.squaess.androidglasses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.squaess.androidglasses.ProductsActivityPackage.ItemModel;
import com.example.squaess.androidglasses.ProductsActivityPackage.ProductListItemRowAdapter;
import com.example.squaess.androidglasses.ProductsActivityPackage.ProductsListOnClickListener;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ProductsActivity extends AppCompatActivity implements ProductsListOnClickListener.OnItemClick {

    private static final String BASE_URL = "http://192.168.1.35:8080/";

    static final int RETURN_CODE = 1;
    ListView listView;
    ArrayList<ItemModel> mArray;
    ProductListItemRowAdapter rowAdapter;
    ProductsListOnClickListener onClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        mArray = new ArrayList<>();
        listView = findViewById(R.id.products_list_view);
        fetchData();
        rowAdapter = new ProductListItemRowAdapter(this,
                R.layout.custom_product_row, mArray);
        listView.setAdapter(rowAdapter);
        onClickListener = new ProductsListOnClickListener(this);
        listView.setOnItemClickListener(onClickListener);
    }

    private void fetchData() {
        String urlString = BASE_URL+"item/all/";
        Ion.with(this).load(urlString).asString().setCallback(new FutureCallback<String>() {
            @Override
            public void onCompleted(Exception e, String result) {
                try {
                    JSONArray json = new JSONArray(result);
                    //JSONObject json = new JSONObject(result);
                    processData(json);
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void processData(JSONArray array) {
        try {

            for (int i = 0; i<array.length(); i++) {
                JSONObject o = array.getJSONObject(i);
                ItemModel itemModel = new ItemModel(o.getInt("itemId"),
                        o.getString("itemName"), o.getBoolean("available"),
                        o.getDouble("price"), o.getDouble("correction"),
                        o.getString("url"));
                mArray.add(itemModel);
            }

            rowAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSentText(int row) {

        ItemModel model = mArray.get(row);
        int item_id = model.getItem_id();
        String item_name = model.getItem_name();
        boolean available = model.isAvailable();
        if(available) {
            double price = model.getPrice();
            double correction = model.getCorrection();
            String url = model.getUrl();

            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("item_id", item_id);
            intent.putExtra("item_name", item_name);
            intent.putExtra("available", available);
            intent.putExtra("price", price);
            intent.putExtra("correction", correction);
            intent.putExtra("url", url);
            intent.putExtra("server", BASE_URL);
            startActivityForResult(intent, RETURN_CODE);
        } else {
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast,
                    (ViewGroup) findViewById(R.id.custom_toast_container));
            TextView text = layout.findViewById(R.id.text);
            text.setText("Produkt nidostępny");

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER,0,0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();
        }

    }
}
