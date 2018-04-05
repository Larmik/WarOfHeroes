package fr.wcs.warofheroes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
