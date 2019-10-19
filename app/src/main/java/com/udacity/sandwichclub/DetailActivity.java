package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

        //Find the TextView with view ID origin_tv
        TextView originView = findViewById(R.id.origin_tv);
        // Set the text of Place of origin
        originView.setText(sandwich.getPlaceOfOrigin());

        //Find the TextView with view ID description_tv
        TextView descriptionView = findViewById(R.id.description_tv);
        // Set the text of Place of origin
        descriptionView.setText(sandwich.getDescription());

        //Add also known as list to Text view
        StringBuilder builder = new StringBuilder();
        for (String currentItem : sandwich.getAlsoKnownAs()) {
            builder.append(currentItem + "\n");
        }
        TextView alsoKnownAsView2 = findViewById(R.id.also_known_tv);
        alsoKnownAsView2.setText(builder.toString());

        //Add Ingredients list to Text view
        StringBuilder builder2 = new StringBuilder();
        for (String ingredient : sandwich.getIngredients()) {
            builder2.append(ingredient + "\n");
        }
        TextView ingredientsView = findViewById(R.id.ingredients_tv);
        ingredientsView.setText(builder2.toString());

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

    }
}
