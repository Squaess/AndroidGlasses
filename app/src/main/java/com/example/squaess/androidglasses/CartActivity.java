package com.example.squaess.androidglasses;

import android.content.ClipData;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.squaess.androidglasses.ProductsActivityPackage.ItemModel;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private CartListAdapter adapter;
    ListView listView;
    private static final String BASE_URL = "http://192.168.0.14:8080/";
    ArrayList<ItemModel> mArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mArray = new ArrayList<>();
        listView = findViewById(R.id.products_list_view);
        fetchData();
        adapter = new CartListAdapter(this, R.layout.cart_row, mArray);

        listView.setAdapter(adapter);
    }

    //TODO
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
    //TODO
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

            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //szukanie w jsonie

    private List<ItemModel> getItem(){
        List<ItemModel> teams = new ArrayList<>();
        Log.i("info", "created teams");
        return teams;
    }


}
