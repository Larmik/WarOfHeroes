package fr.wcs.warofheroes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
