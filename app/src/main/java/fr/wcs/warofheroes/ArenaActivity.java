package fr.wcs.warofheroes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Random;

import static fr.wcs.warofheroes.ChooseHeroesActivity.EXTRA_PARCEL_HERO1;
import static fr.wcs.warofheroes.ChooseHeroesActivity.EXTRA_PARCEL_HERO2;

public class ArenaActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arena);

        HeroesModel hero1Parcel = getIntent().getParcelableExtra(EXTRA_PARCEL_HERO1);
        HeroesModel hero2Parcel = getIntent().getParcelableExtra(EXTRA_PARCEL_HERO2);

        int mana = new Random().nextInt((25 - 10) + 1) + 10;
        int attackValue = new Random().nextInt((5 - 2)+1) +2;
        int spellHealValue = new Random().nextInt((15 - 10) + 1) + 10;
        final HeroesModel hero1 = new HeroesModel(100, attackValue, 0);
        final HeroesModel hero2 = new HeroesModel(100, attackValue, 0);
        final ImageView imgHero1 = findViewById(R.id.imageView_hero1);
        final ImageView imgHero2 = findViewById(R.id.imageView_hero2);
        final TextView life1 = findViewById(R.id.life_pl_1);
        final TextView mana1 = findViewById(R.id.mana_pl_1);
        final TextView life2 = findViewById(R.id.life_pl_2);
        final TextView mana2 = findViewById(R.id.mana_pl_2);
        final Button attack1 = findViewById(R.id.button_attack_1);
        final Button spell1 = findViewById(R.id.button_spell_1);
        final Button heal1 = findViewById(R.id.button_heal_1);
        Glide.with(this).load(hero1Parcel.getImage()).into(imgHero1);
        Glide.with(this).load(hero2Parcel.getImage()).into(imgHero2);



        attack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mana = new Random().nextInt((25 - 10) + 1) + 10;
                int attackValue = new Random().nextInt((5 - 2)+1) +2;
                hero2.setLife(hero2.getLife() - attackValue);
                hero1.setMana(hero1.getMana() + mana);
                if (hero1.getMana() > 50) {
                    hero1.setMana(50);
                    spell1.setEnabled(true);
                    heal1.setEnabled(true);
                } else {
                    spell1.setEnabled(false);
                    heal1.setEnabled(false);
                }
                if (hero1.getLife() >= 100) {
                    hero1.setLife(100);
                    heal1.setEnabled(false);
                }
                mana1.setText(String.valueOf(hero1.getMana()));
                life2.setText(String.valueOf(hero2.getLife()));
            }
        });



        spell1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int spellHealValue = new Random().nextInt((15 - 10) +1) +10;
                hero2.setLife(hero2.getLife() - spellHealValue);
                hero1.setMana(0);

                if (hero1.getMana() > 50) {
                    hero1.setMana(50);
                    spell1.setEnabled(true);
                    heal1.setEnabled(true);
                } else {
                    spell1.setEnabled(false);
                    heal1.setEnabled(false);
                }
                if (hero1.getLife() >= 100) {
                    hero1.setLife(100);
                    heal1.setEnabled(false);
                }
                life2.setText(String.valueOf(hero2.getLife()));
                mana1.setText(String.valueOf(hero1.getMana()));
            }
        });

        heal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int spellHealValue = new Random().nextInt((15 - 10) +1) +10;
                hero1.setLife(hero1.getLife() + spellHealValue);
                hero1.setMana(0);
                if (hero1.getMana() > 50) {
                    hero1.setMana(50);
                    spell1.setEnabled(true);
                    heal1.setEnabled(true);
                } else {
                    spell1.setEnabled(false);
                    heal1.setEnabled(false);
                }
                if (hero1.getLife() >= 100) {
                    hero1.setLife(100);
                    heal1.setEnabled(false);
                }
                life1.setText(String.valueOf(hero1.getLife()));
                mana1.setText(String.valueOf(hero1.getMana()));
            }
        });




    }

    public ArenaActivity(){

    }

}
