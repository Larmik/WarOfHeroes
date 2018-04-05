package fr.wcs.warofheroes;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import android.widget.Button;

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

    public static final String EXTRA_PARCEL_HERO1 = "EXTRA_PARCEL_HERO1";
    public static final String EXTRA_PARCEL_HERO2 = "EXTRA_PARCEL_HERO2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_heroes);
        Intent gotoSplashVs = new Intent(ChooseHeroesActivity.this, VsActivity.class);
        ChooseHeroesActivity.this.startActivity(gotoSplashVs);


        final ArrayList<HeroesModel> heroList = new ArrayList<>();
        final ImageView imgHero = findViewById(R.id.img_hero);
        final TextView intelligence = findViewById(R.id.intelligence_value);
        final TextView strength = findViewById(R.id.strength_value);
        final TextView speed = findViewById(R.id.speed_value);
        final TextView durability = findViewById(R.id.durability_value);
        final TextView power = findViewById(R.id.power_value);
        final TextView combat = findViewById(R.id.combat_value);
        final TextView intelligenceTxt = findViewById(R.id.intelligence);
        final TextView strengthTxt = findViewById(R.id.strength);
        final TextView speedTxt = findViewById(R.id.speed);
        final TextView durabilityTxt = findViewById(R.id.durability);
        final TextView powerTxt = findViewById(R.id.power);
        final TextView combatTxt = findViewById(R.id.combat);
        final TextView chooseHero = findViewById(R.id.choose);
        final TextView name = findViewById(R.id.name_hero);
        final TextView desc = findViewById(R.id.desc_hero);
        final TextView player = findViewById(R.id.player);
        final GridView gridHero = findViewById(R.id.grid_hero);
        final Button btnGo = findViewById(R.id.btn_go);
        final Intent intent = new Intent(ChooseHeroesActivity.this, ArenaActivity.class);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://cdn.rawgit.com/akabab/superhero-api/0.2.0/api/all.json";

        final JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        // TODO : traiter la réponse
                        try {

                            for (int i = 0; i < 30; i++) {

                                JSONObject heroStats = response.getJSONObject(i);

                                JSONObject powerStats = heroStats.getJSONObject("powerstats");
                                JSONObject work = heroStats.getJSONObject("work");
                                JSONObject picture = heroStats.getJSONObject("images");
                                String heroName = heroStats.getString("name");

                                String url = picture.getString("lg");

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
                            ;

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
        gridHero.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                player.setVisibility(View.GONE);
                chooseHero.setVisibility(View.GONE);
                intelligenceTxt.setVisibility(View.VISIBLE);
                strengthTxt.setVisibility(View.VISIBLE);
                speedTxt.setVisibility(View.VISIBLE);
                durabilityTxt.setVisibility(View.VISIBLE);
                powerTxt.setVisibility(View.VISIBLE);
                combatTxt.setVisibility(View.VISIBLE);
                intelligence.setVisibility(View.VISIBLE);
                strength.setVisibility(View.VISIBLE);
                speed.setVisibility(View.VISIBLE);
                durability.setVisibility(View.VISIBLE);
                power.setVisibility(View.VISIBLE);
                combat.setVisibility(View.VISIBLE);
                btnGo.setVisibility(View.VISIBLE);
                imgHero.setVisibility(View.VISIBLE);
                name.setVisibility(View.VISIBLE);


                Parcelable hero1 = new HeroesModel(heroList.get(i).getName(), heroList.get(i).getIntelligence(), heroList.get(i).getStrength(),
                        heroList.get(i).getSpeed(), heroList.get(i).getDurability(), heroList.get(i).getPower(), heroList.get(i).getCombat(),
                        heroList.get(i).getDescription(), heroList.get(i).getImage());
                intent.putExtra(EXTRA_PARCEL_HERO1, hero1);
                intelligence.setText(String.valueOf(heroList.get(i).getIntelligence()));
                strength.setText(String.valueOf(heroList.get(i).getStrength()));
                speed.setText(String.valueOf(heroList.get(i).getSpeed()));
                durability.setText(String.valueOf(heroList.get(i).getDurability()));
                power.setText(String.valueOf(heroList.get(i).getPower()));
                combat.setText(String.valueOf(heroList.get(i).getCombat()));
                name.setText(heroList.get(i).getName());
                desc.setText(heroList.get(i).getDescription());
                Glide.with(ChooseHeroesActivity.this).load(heroList.get(i).getImage()).into(imgHero);
                btnGo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        player.setText("Player 2");
                        player.setVisibility(View.VISIBLE);
                        chooseHero.setVisibility(View.VISIBLE);
                        intelligenceTxt.setVisibility(View.GONE);
                        strengthTxt.setVisibility(View.GONE);
                        speedTxt.setVisibility(View.GONE);
                        durabilityTxt.setVisibility(View.GONE);
                        powerTxt.setVisibility(View.GONE);
                        combatTxt.setVisibility(View.GONE);
                        intelligence.setVisibility(View.GONE);
                        strength.setVisibility(View.GONE);
                        speed.setVisibility(View.GONE);
                        durability.setVisibility(View.GONE);
                        power.setVisibility(View.GONE);
                        combat.setVisibility(View.GONE);
                        btnGo.setVisibility(View.GONE);
                        imgHero.setVisibility(View.GONE);
                        name.setVisibility(View.GONE);
                        gridHero.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                                player.setVisibility(View.GONE);
                                chooseHero.setVisibility(View.GONE);
                                intelligenceTxt.setVisibility(View.VISIBLE);
                                strengthTxt.setVisibility(View.VISIBLE);
                                speedTxt.setVisibility(View.VISIBLE);
                                durabilityTxt.setVisibility(View.VISIBLE);
                                powerTxt.setVisibility(View.VISIBLE);
                                combatTxt.setVisibility(View.VISIBLE);
                                intelligence.setVisibility(View.VISIBLE);
                                strength.setVisibility(View.VISIBLE);
                                speed.setVisibility(View.VISIBLE);
                                durability.setVisibility(View.VISIBLE);
                                power.setVisibility(View.VISIBLE);
                                combat.setVisibility(View.VISIBLE);
                                btnGo.setVisibility(View.VISIBLE);
                                imgHero.setVisibility(View.VISIBLE);
                                name.setVisibility(View.VISIBLE);

                                Parcelable hero2 = new HeroesModel(heroList.get(i).getName(), heroList.get(i).getIntelligence(), heroList.get(i).getStrength(),
                                        heroList.get(i).getSpeed(), heroList.get(i).getDurability(), heroList.get(i).getPower(), heroList.get(i).getCombat(),
                                        heroList.get(i).getDescription(), heroList.get(i).getImage());
                                intent.putExtra(EXTRA_PARCEL_HERO2, hero2);
                                intelligence.setText(String.valueOf(heroList.get(i).getIntelligence()));
                                strength.setText(String.valueOf(heroList.get(i).getStrength()));
                                speed.setText(String.valueOf(heroList.get(i).getSpeed()));
                                durability.setText(String.valueOf(heroList.get(i).getDurability()));
                                power.setText(String.valueOf(heroList.get(i).getPower()));
                                combat.setText(String.valueOf(heroList.get(i).getCombat()));
                                name.setText(heroList.get(i).getName());
                                desc.setText(heroList.get(i).getDescription());
                                Glide.with(ChooseHeroesActivity.this).load(heroList.get(i).getImage()).into(imgHero);
                                btnGo.setText("Let's fight !");
                                btnGo.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        ChooseHeroesActivity.this.startActivity(intent);
                                    }
                                });
                            }
                        });


                    }
                });


            }
        });
    }
}
