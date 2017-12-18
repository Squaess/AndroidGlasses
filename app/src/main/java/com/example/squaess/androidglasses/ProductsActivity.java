package com.example.squaess.androidglasses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.squaess.androidglasses.ProductsActivityPackage.ItemModel;
import com.example.squaess.androidglasses.ProductsActivityPackage.ProductListItemRowAdapter;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://192.168.0.14:8080/";

    ListView listView;
    ArrayList<ItemModel> mArray;
    ProductListItemRowAdapter rowAdapter;

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

}
