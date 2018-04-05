package fr.wcs.warofheroes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private final static String API_KEY ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        ImageView ivLogo = findViewById(R.id.image_glide);
        String url = "https://cdn.rawgit.com/akabab/superhero-api/0.2.0/api/all.json";
        Glide.with(this).load(url) .into(ivLogo);



        // TODO : URL de la requête vers l'API


        // Création de la requête vers l'API, ajout des écouteurs pour les réponses et erreurs possibles
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        // TODO : traiter la réponse
                        try {
                            JSONArray hero = response.getJSONArray(Integer.parseInt("hero1"));
                            for (int i = 0; i < hero.length(); i++) {
                                JSONArray heroInfos = (JSONArray) hero.get(i);
                                JSONObject hero1 = new JSONObject();
                                try {
                                    hero1.put("id", "1");
                                    hero1.put("name", "A-Bomb");
                                    hero1.put("intelligence", "38");
                                    hero1.put("strength", "100");
                                    hero1.put("speed", "17");
                                    hero1.put("durability","80");
                                    hero1.put("power",24);
                                    hero1.put("combat",64);
                                    

                                } catch (JSONException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }

                                JSONObject hero2 = new JSONObject();
                                try {
                                    hero2.put("id", "2");
                                    hero2.put("name", "Abe Sapien");
                                    hero2.put("intelligence", "88");
                                    hero2.put("strength", "28");
                                    hero2.put("speed", "35");
                                    hero2.put("durability","65");
                                    hero2.put("power",100);
                                    hero2.put("combat",85);

                                } catch (JSONException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }


                                JSONArray jsonArray = new JSONArray();

                                jsonArray.put(hero1);
                                jsonArray.put(hero2);

                                JSONObject studentsObj = new JSONObject();
                                studentsObj.put("Students", jsonArray);
                            }
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

        // On ajoute la requête à la file d'attente
        requestQueue.add(jsonArrayRequest);




        Button buttonVersus = findViewById(R.id.button_versus);
        Button buttonTraining = findViewById(R.id.button_training);

        buttonVersus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentVersus = new Intent(MainActivity.this, ArenaActivity.class);
                startActivity(intentVersus);
            }
        });
        
        buttonTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                Intent intentTraining = new Intent(MainActivity.this, ArenaActivity.class);
                startActivity(intentTraining);
            }
        });


    }

}
