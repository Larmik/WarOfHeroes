package fr.wcs.warofheroes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView ivLogo = findViewById(R.id.image_glide);
        String url = "https://wildcodeschool.fr/wp-content/uploads/2017/01/logo_orange_pastille.png";
        Glide.with(this).load(url) .into(ivLogo);

        // Crée une file d'attente pour les requêtes vers l'API
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url = "https://cdn.rawgit.com/akabab/superhero-api/0.2.0/api/all.json";

        // Création de la requête vers l'API, ajout des écouteurs pour les réponses et erreurs possibles
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // TODO : traiter la réponse
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
        requestQueue.add(jsonObjectRequest);

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
