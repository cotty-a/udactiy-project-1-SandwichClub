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

            //Get Sandwich main name
            mainName = name.getString("mainName");
            sandwich.setMainName(mainName);

            // TODO: Image JSON data

            // Get Sandwich place of origin
            String placeOfOrigin = baseJsonResponse.getString("placeOfOrigin");
            sandwich.setPlaceOfOrigin(placeOfOrigin);

            //Get sandwich also known as
            JSONArray alsoKnownAsArray = name.getJSONArray("alsoKnownAs");
            // TODO: Improve the for loop to be a separate method
            for(int i = 0; i < alsoKnownAsArray.length(); i++){
                String currentKnownName = alsoKnownAsArray.getString(i);
                alsoKnownAsList.add(currentKnownName);
            }
            sandwich.setAlsoKnownAs(alsoKnownAsList);

            // TODO: DESCRIPTION JSON data
            String description = baseJsonResponse.getString("description");
            sandwich.setDescription(description);

            JSONArray ingredientsArray = baseJsonResponse.getJSONArray("ingredients");
            // TODO: Improve the for loop to be a separate method
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


/*        JSONObject baseJsonResponse = new JSONObject(SAMPLE_JSON_RESPONSE);
        JSONArray earthquakeArray = baseJsonResponse.getJSONArray("features");

        for(int i = 0; i < earthquakeArray.length(); i++){
            JSONObject currentEarthquake = earthquakeArray.getJSONObject(i);
            JSONObject properties = currentEarthquake.getJSONObject("properties");
            String magnitude = properties.getString("mag");
            String location = properties.getString("place");
            String time = properties.getString("time");

            Earthquake earthquake = new Earthquake(magnitude, location, time);
            earthquakes.add(earthquake);*/



        System.out.println(json);
       //Test data for list
        List<String> testList = new ArrayList<String>();
        testList.add("List1");
        testList.add("List2");
        testList.add("List3");


        //TODO Assign real data on sandwich object, may need to re-arrange and put the sandwich objects separately
        //Test Data on assigning test data on sandwich object
        //Sandwich sandwich = new Sandwich(mainName, alsoKnownAsList, "test place of origin", "test description", "testImage", ingredientsList);



        return null;
    }



}
