package fr.wcs.warofheroes;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ChooseHeroesActivity extends FragmentActivity {

    private PagerAdapter scrollAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_heroes);
        final ArrayList<HeroesModel> heroList = new ArrayList<>();
        final TextView intelligence = findViewById(R.id.intelligence_value);
        final TextView strength = findViewById(R.id.strength_value);
        final TextView speed = findViewById(R.id.speed_value);
        final TextView durability = findViewById(R.id.durability_value);
        final TextView power = findViewById(R.id.power_value);
        final TextView combat = findViewById(R.id.combat_value);
        final TextView name = findViewById(R.id.name_hero);
        final TextView desc = findViewById(R.id.desc_hero);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://cdn.rawgit.com/akabab/superhero-api/0.2.0/api/all.json";

        final JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {


                    @Override
                    public void onResponse(JSONArray response) {
                        // TODO : traiter la réponse
                        try {

                            for(int i = 0; i < 24; i++) {
                                JSONObject heroStats = response.getJSONObject(i);

                                JSONObject powerStats = heroStats.getJSONObject("powerstats");
                                JSONObject work = heroStats.getJSONObject("work");
                                JSONObject picture = heroStats.getJSONObject("images");
                                String heroName = heroStats.getString("name");

                                String url = picture.getString("md");

                                int heroIntelligence = powerStats.getInt("intelligence");
                                int heroStrength = powerStats.getInt("strength");
                                int heroSpeed = powerStats.getInt("speed");
                                int heroDurability = powerStats.getInt("durability");
                                int heroPower = powerStats.getInt("power");
                                String heroDescription = work.getString("occupation");
                                int heroCombat = powerStats.getInt("combat");



                                intelligence.setText(String.valueOf(heroIntelligence));
                                strength.setText(String.valueOf(heroStrength));
                                speed.setText(String.valueOf(heroSpeed));
                                durability.setText(String.valueOf(heroDurability));
                                power.setText(String.valueOf(heroPower));
                                combat.setText(String.valueOf(heroCombat));
                                name.setText(heroName);
                                desc.setText(heroDescription);

                                heroList.add(new HeroesModel(heroName, heroIntelligence, heroStrength, heroSpeed, heroDurability, heroPower, heroCombat, heroDescription, url));
                            }



                            final GridAdapter adapter = new GridAdapter(ChooseHeroesActivity.this, heroList);
                            GridView gridHero = findViewById(R.id.grid_hero);

                            gridHero.setAdapter(adapter);


                            } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Afficher l'erreur
                        Log.d("VOLLEY_ERROR", "onErrorResponse: " + error.getMessage());
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);




    }
}