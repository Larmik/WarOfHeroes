package fr.wcs.warofheroes;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static fr.wcs.warofheroes.ChooseHeroesActivity.EXTRA_PARCEL_HERO1;
import static fr.wcs.warofheroes.ChooseHeroesActivity.EXTRA_PARCEL_HERO2;

public class VsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vs);
        final HeroesModel hero1Choose = getIntent().getParcelableExtra(EXTRA_PARCEL_HERO1);
        final HeroesModel hero2Choose = getIntent().getParcelableExtra(EXTRA_PARCEL_HERO2);


        final ImageView hero1Img = findViewById(R.id.img_hero1);
        final ImageView hero2Img = findViewById(R.id.img_hero2);
        final TextView hero1Txt = findViewById(R.id.txt_hero1);
        final TextView hero2Txt = findViewById(R.id.txt_hero2);

        Glide.with(this).load(hero1Choose.getImage()).into(hero1Img);
        Glide.with(this).load(hero2Choose.getImage()).into(hero2Img);
        hero1Txt.setText(hero1Choose.getName());
        hero2Txt.setText(hero2Choose.getName());


        final int SPLASH_DISPLAY_LENGTH = 2500;

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent mainIntent = new Intent(VsActivity.this, ArenaActivity.class);
                mainIntent.putExtra(EXTRA_PARCEL_HERO1, hero1Choose);
                mainIntent.putExtra(EXTRA_PARCEL_HERO2, hero2Choose);
                VsActivity.this.startActivity(mainIntent);
                VsActivity.this.finish();
            }

        }, SPLASH_DISPLAY_LENGTH);
    }
}
