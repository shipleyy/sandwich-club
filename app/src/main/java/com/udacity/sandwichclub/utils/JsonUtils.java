package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

  public static Sandwich parseSandwichJson(String json) throws JSONException {

    Sandwich sandwich = new Sandwich();

    // Create a JSONObject from the JSON string
    JSONObject root = new JSONObject(json);

    // Extract the mainName from the JSON object add it to the Sandwich object
    JSONObject name = root.getJSONObject("name");
    sandwich.setMainName(name.getString("mainName"));

    // Extract the alsoKnownAs array from the JSON object and parse all the different
    // names to a list og type String add it to the Sandwich object
    JSONArray alsoKnownAsNames = name.getJSONArray("alsoKnownAs");
    List<String> alsoKnownAs = new ArrayList<String>();
    for (int i = 0; i < alsoKnownAsNames.length(); i++) {
      alsoKnownAs.add(alsoKnownAsNames.getString(i));
    }
    sandwich.setAlsoKnownAs(alsoKnownAs);

    // Extract the placeOfOrigin String and add it to the Sandwich object
    sandwich.setPlaceOfOrigin(root.getString("placeOfOrigin"));

    // Extract the description String and add it to the Sandwich object
    sandwich.setDescription(root.getString("description"));

    // Extract the image String and add it to the Sandwich object
    sandwich.setImage(root.getString("image"));

    // Extract the ingredients array from the JSON object and parse all the different
    // ingredients to a list og type String add it to the Sandwich object
    JSONArray ingredientsList = root.getJSONArray("ingredients");
    List<String> ingredients = new ArrayList<String>();
    for (int i = 0; i < ingredientsList.length(); i++) {
      ingredients.add(ingredientsList.getString(i));
    }
    sandwich.setIngredients(ingredients);

    return sandwich;


  }
}
