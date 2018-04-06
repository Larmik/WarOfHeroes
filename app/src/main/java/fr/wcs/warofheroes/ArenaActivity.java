package fr.wcs.warofheroes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import static fr.wcs.warofheroes.ChooseHeroesActivity.EXTRA_PARCEL_HERO1;
import static fr.wcs.warofheroes.ChooseHeroesActivity.EXTRA_PARCEL_HERO2;

public class ArenaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arena);

        HeroesModel hero1 = getIntent().getParcelableExtra(EXTRA_PARCEL_HERO1);
        HeroesModel hero2 = getIntent().getParcelableExtra(EXTRA_PARCEL_HERO2);

        final ImageView imgHero1 = findViewById(R.id.imageView_hero1);
        final ImageView imgHero2 = findViewById(R.id.imageView_hero2);
        Glide.with(this).load(hero1.getImage()).into(imgHero1);
        Glide.with(this).load(hero2.getImage()).into(imgHero2);

    }
}
