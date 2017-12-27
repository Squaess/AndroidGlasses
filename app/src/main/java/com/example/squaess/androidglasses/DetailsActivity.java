package com.example.squaess.androidglasses;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.squaess.androidglasses.ProductsActivityPackage.ItemModel;
import com.koushikdutta.ion.Ion;

public class DetailsActivity extends AppCompatActivity {

    private static String BASE_URL;
    private ItemModel model;
    private ImageView image;
    private TextView name, price, correction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        unpackData(b);
        bindView();
        loadImage();
        setTextViewsValues();


    }

    private void setTextViewsValues() {
        name.setText(model.getItem_name());
        price.setText(String.valueOf(model.getPrice()));
        correction.setText(String.valueOf(model.getCorrection()));
    }

    private void loadImage() {
        Ion.with(getApplicationContext())
                .load(model.getUrl())
                .withBitmap()
                .placeholder(R.drawable.twitter)
                .error(R.drawable.ic_error_web)
                .intoImageView(image);
    }

    private void bindView() {
        correction = findViewById(R.id.detail_correction);
        image = findViewById(R.id.detail_image);
        price = findViewById(R.id.detail_price);
        name = findViewById(R.id.detail_name);
    }

    private void unpackData(Bundle b) {
        assert b != null;
        int item_id = b.getInt("item_id");
        String item_name = b.getString("item_name");
        boolean available = b.getBoolean("available");
        double price = b.getDouble("price");
        double cor = b.getDouble("correction");
        String url = b.getString("url");
        BASE_URL = b.getString("server");

        model = new ItemModel(item_id, item_name, available,
                price, cor, url);

    }
}
