package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        String mainName = "";
        List<String> alsoKnownAsList = new ArrayList<>();
        List<String> ingredientsList = new ArrayList<>();

        try {
            Sandwich sandwich = new Sandwich();
            JSONObject baseJsonResponse = new JSONObject(json);
            JSONObject name = baseJsonResponse.getJSONObject("name");

            // Get Sandwich main name
            mainName = name.getString("mainName");
            sandwich.setMainName(mainName);

            // Get Sandwich image
            String image = baseJsonResponse.getString("image");
            sandwich.setImage(image);

            // Get Sandwich place of origin
            String placeOfOrigin = baseJsonResponse.getString("placeOfOrigin");
            sandwich.setPlaceOfOrigin(placeOfOrigin);

            //Get sandwich also known as
            JSONArray alsoKnownAsArray = name.getJSONArray("alsoKnownAs");
            for(int i = 0; i < alsoKnownAsArray.length(); i++){
                String currentKnownName = alsoKnownAsArray.getString(i);
                alsoKnownAsList.add(currentKnownName);
            }
            sandwich.setAlsoKnownAs(alsoKnownAsList);

            // Get sandwich description
            String description = baseJsonResponse.getString("description");
            sandwich.setDescription(description);

            // Get sandwich description
            JSONArray ingredientsArray = baseJsonResponse.getJSONArray("ingredients");
            for(int i = 0; i < ingredientsArray.length(); i++){
                String ingredient = ingredientsArray.getString(i);
                ingredientsList.add(ingredient);
            }
            sandwich.setIngredients(ingredientsList);

            return sandwich;

        }catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("JsonUtils", "Problem parsing the JSON results", e);
        }

        return null;
    }

}
