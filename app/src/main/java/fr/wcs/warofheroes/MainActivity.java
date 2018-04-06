package fr.wcs.warofheroes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView ivLogo = findViewById(R.id.image_glide);
        String url = "https://vignette.wikia.nocookie.net/soulcalibur/images/0/0d/051.jpg/revision/latest?cb=20120112143431";
        Glide.with(this).load(url) .into(ivLogo);

        Button buttonVersus = findViewById(R.id.button_versus);
        Button buttonTraining = findViewById(R.id.button_training);

        buttonVersus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentVersus = new Intent(MainActivity.this, ChooseHeroesActivity.class);
                startActivity(intentVersus);
            }
        });
        
        buttonTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                Intent intentTraining = new Intent(MainActivity.this, ChooseHeroesActivity.class);
                startActivity(intentTraining);
            }
        });
    }
}
