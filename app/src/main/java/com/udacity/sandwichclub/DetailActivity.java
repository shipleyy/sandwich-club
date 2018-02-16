package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;
import java.util.List;
import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

  public static final String EXTRA_POSITION = "extra_position";
  private static final int DEFAULT_POSITION = -1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    ImageView ingredientsIv = findViewById(R.id.image_iv);
    TextView alsoKnownAsTv = findViewById(R.id.also_known_tv);
    TextView originTv = findViewById(R.id.origin_tv);
    TextView ingredientsTv = findViewById(R.id.ingredients_tv);
    TextView descriptionTv = findViewById(R.id.description_tv);

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
    Sandwich sandwich = null;
    try {
      sandwich = JsonUtils.parseSandwichJson(json);
    } catch (JSONException e) {
      e.printStackTrace();
    }
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

    // Getting the list of alsoKnownAs and concatenates it in one String
    String concatenatedNames = "";
    List<String> alsoKnownAsNames = sandwich.getAlsoKnownAs();
    for (int i = 0; i < alsoKnownAsNames.size(); i++) {
      concatenatedNames += alsoKnownAsNames.get(i);
      if (i < alsoKnownAsNames.size() - 1) {
        concatenatedNames += ", ";
      }
    }
    alsoKnownAsTv.setText(concatenatedNames);

    originTv.setText(sandwich.getPlaceOfOrigin());

    // Getting the list of ingredients and concatenates it in one String
    String concatenatedIngredients = "";
    List<String> ingredients = sandwich.getIngredients();
    for (int i = 0; i < ingredients.size(); i++) {
      concatenatedIngredients += ingredients.get(i);
      if (i < ingredients.size() - 1) {
        concatenatedIngredients += ", ";
      }
    }
    ingredientsTv.setText(concatenatedIngredients);

    descriptionTv.setText(sandwich.getDescription());
  }

  private void closeOnError() {
    finish();
    Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
  }

  private void populateUI() {

  }
}
