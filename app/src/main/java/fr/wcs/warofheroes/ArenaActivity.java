package fr.wcs.warofheroes;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Random;

import static fr.wcs.warofheroes.ChooseHeroesActivity.EXTRA_PARCEL_HERO1;
import static fr.wcs.warofheroes.ChooseHeroesActivity.EXTRA_PARCEL_HERO2;



public class ArenaActivity extends AppCompatActivity {

    public static final String EXTRA_PARCEL_WIN = "EXTRA_PARCEL_WIN";
    public static final String EXTRA_PARCEL_LOSS = "EXTRA_PARCEL_LOSS";
    int damageFromHero1 = 0;
    int damageToHero1 = 0;
    int damageFromHero2= 0;
    int damageToHero2 = 0;
    int healHero1 = 0;
    int healHero2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arena);

        final HeroesModel hero1Parcel = getIntent().getParcelableExtra(EXTRA_PARCEL_HERO1);
        final HeroesModel hero2Parcel = getIntent().getParcelableExtra(EXTRA_PARCEL_HERO2);

        final ProgressBar health1 = findViewById(R.id.progressBarHero1);
        final ProgressBar health2 = findViewById(R.id.progressBarHero3);


        int attackValue = new Random().nextInt((5 - 2)+1) +2;
        final HeroesModel hero1 = new HeroesModel(100, attackValue, 0);
        final HeroesModel hero2 = new HeroesModel(100, attackValue, 0);
        final ImageView imgHero1 = findViewById(R.id.imageView_hero1);
        final ImageView imgHero2 = findViewById(R.id.imageView_hero2);
        final Button attack1 = findViewById(R.id.button_attack_1);
        final Button spell1 = findViewById(R.id.button_spell_1);
        final Button heal1 = findViewById(R.id.button_heal_1);
        final Button attack2 = findViewById(R.id.button_attack_2);
        final Button spell2 = findViewById(R.id.button_spell_2);
        final Button heal2 = findViewById(R.id.button_heal_2);
        final TextView ko = findViewById(R.id.is_ko);
        final TextView msgMana1 = findViewById(R.id.msg_mana_1);
        final TextView msgMana2 = findViewById(R.id.msg_mana_2);

        Glide.with(this).load(hero1Parcel.getImage()).into(imgHero1);
        Glide.with(this).load(hero2Parcel.getImage()).into(imgHero2);

        final Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);



        attack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imgHero1.setTranslationX(800);
                imgHero1.setTranslationY(0);

                final int SPLASH_DISPLAY_LENGTH = 1000;

                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {

                        imgHero1.setTranslationX(0);
                        imgHero1.setTranslationY(0);
                    }

                }, SPLASH_DISPLAY_LENGTH);

                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {

                        Animation anim = new AlphaAnimation(0.0f, 1.0f);
                        anim.setDuration(500);
                        anim.setStartOffset(20);
                        anim.setRepeatMode(Animation.REVERSE);
                        imgHero2.startAnimation(anim);
                    }

                }, 0);
                int mana = new Random().nextInt((15 - 8) + 1) + 8;
                int attackValue = new Random().nextInt((10 - 5)+1) +5;
                hero2.setLife(hero2.getLife() - attackValue);
                hero1.setMana(hero1.getMana() + mana);
                damageFromHero1 += attackValue;
                damageToHero2 += attackValue;

                if (hero1.getMana() >= 50) {
                    hero1.setMana(50);
                    msgMana1.startAnimation(anim);
                    msgMana1.setVisibility(View.VISIBLE);
                    spell1.setEnabled(true);
                    heal1.setEnabled(true);
                } else {
                    spell1.setEnabled(false);
                    heal1.setEnabled(false);
                    msgMana1.setVisibility(View.INVISIBLE);
                }
                if (hero1.getLife() >= 100) {
                    hero1.setLife(100);
                    heal1.setEnabled(false);
                }
                attack1.setVisibility(View.INVISIBLE);
                spell1.setVisibility(View.INVISIBLE);
                heal1.setVisibility(View.INVISIBLE);
                attack2.setVisibility(View.VISIBLE);
                spell2.setVisibility(View.VISIBLE);
                heal2.setVisibility(View.VISIBLE);


                hero2.setLife(hero2.getLife() - attackValue);
                health2.setSecondaryProgress(100);
                health2.setProgress(hero2.getLife());

                hero1.setMana(hero1.getMana() + mana);
                if (hero2.isKo()){
                    attack1.setVisibility(View.INVISIBLE);
                    spell1.setVisibility(View.INVISIBLE);
                    heal1.setVisibility(View.INVISIBLE);
                    attack2.setVisibility(View.INVISIBLE);
                    spell2.setVisibility(View.INVISIBLE);
                    heal2.setVisibility(View.INVISIBLE);
                    hero2.setLife(0);
                    ko.setText("Player 1 Wins !");
                    ko.setVisibility(View.VISIBLE);
                    imgHero2.setVisibility(View.INVISIBLE);
                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run() {

                            Intent intent = new Intent(ArenaActivity.this, ResumeFightActivity.class);
                            intent.putExtra(EXTRA_PARCEL_WIN, hero1Parcel);
                            intent.putExtra(EXTRA_PARCEL_LOSS, hero2Parcel);
                            intent.putExtra("damage_from_loss", damageFromHero2);
                            intent.putExtra("damage_from_win", damageFromHero1);
                            intent.putExtra("damage_to_win", damageToHero1);
                            intent.putExtra("damage_to_loss", damageToHero2);
                            intent.putExtra("heal_win", healHero1);
                            intent.putExtra("heal_loss", healHero2);
                            startActivity(intent);
                        }
                    }, 3000);

                }

            }
        });



        spell1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgHero1.setTranslationX(800);
                imgHero1.setTranslationY(0);

                final int SPLASH_DISPLAY_LENGTH = 1000;

                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {

                        imgHero1.setTranslationX(0);
                        imgHero1.setTranslationY(0);
                    }

                }, SPLASH_DISPLAY_LENGTH);

                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {

                        Animation anim = new AlphaAnimation(0.0f, 1.0f);
                        anim.setDuration(300);
                        anim.setStartOffset(20);
                        anim.setRepeatMode(Animation.REVERSE);
                        anim.setRepeatCount(3);
                        imgHero2.startAnimation(anim);
                    }

                }, 0);
                int spellHealValue = new Random().nextInt((20 - 15) +1) +15;
                hero2.setLife(hero2.getLife() - spellHealValue);
                hero1.setMana(0);
                damageFromHero1 += spellHealValue;
                damageToHero2 += spellHealValue;
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
                attack1.setVisibility(View.INVISIBLE);
                spell1.setVisibility(View.INVISIBLE);
                heal1.setVisibility(View.INVISIBLE);
                attack2.setVisibility(View.VISIBLE);
                spell2.setVisibility(View.VISIBLE);
                heal2.setVisibility(View.VISIBLE);


                hero2.setLife(hero2.getLife() - spellHealValue);
                health2.setSecondaryProgress(100);
                health2.setProgress(hero2.getLife());

                hero1.setMana(hero1.getMana() - 50);
                msgMana1.clearAnimation();
                msgMana1.setVisibility(View.INVISIBLE);

                if (hero2.isKo()) {
                    attack1.setVisibility(View.INVISIBLE);
                    spell1.setVisibility(View.INVISIBLE);
                    heal1.setVisibility(View.INVISIBLE);
                    attack2.setVisibility(View.INVISIBLE);
                    spell2.setVisibility(View.INVISIBLE);
                    heal2.setVisibility(View.INVISIBLE);
                    hero2.setLife(0);
                    ko.setText("Player 1 Wins !");
                    ko.setVisibility(View.VISIBLE);
                    imgHero2.setVisibility(View.INVISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Intent intent = new Intent(ArenaActivity.this, ResumeFightActivity.class);
                            intent.putExtra(EXTRA_PARCEL_WIN, hero1);
                            intent.putExtra(EXTRA_PARCEL_LOSS, hero2);
                            intent.putExtra("damage_from_loss", damageFromHero2);
                            intent.putExtra("damage_from_win", damageFromHero1);
                            intent.putExtra("damage_to_win", damageToHero1);
                            intent.putExtra("damage_to_loss", damageToHero2);
                            intent.putExtra("heal_win", healHero1);
                            intent.putExtra("heal_loss", healHero2);
                            startActivity(intent);
                        }

                    }, 3000);
                }

            }
        });

        heal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {

                        Animation anim = new AlphaAnimation(0.0f, 1.0f);
                        anim.setDuration(1200);
                        anim.setStartOffset(100);
                        anim.setRepeatMode(Animation.REVERSE);
                        imgHero1.startAnimation(anim);
                    }

                }, 0);
                int spellHealValue = new Random().nextInt((20 - 15) +1) +15;
                hero1.setLife(hero1.getLife() + spellHealValue);
                hero1.setMana(0);
                healHero1 += spellHealValue;
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

                attack1.setVisibility(View.INVISIBLE);
                spell1.setVisibility(View.INVISIBLE);
                heal1.setVisibility(View.INVISIBLE);
                attack2.setVisibility(View.VISIBLE);
                spell2.setVisibility(View.VISIBLE);
                heal2.setVisibility(View.VISIBLE);

                hero1.setLife(hero1.getLife() + spellHealValue);
                health1.setSecondaryProgress(100);
                health1.setProgress(hero1.getLife());

                hero1.setMana(hero1.getMana() - 50);
                msgMana1.setVisibility(View.INVISIBLE);
                msgMana1.clearAnimation();
            }
        });

        attack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ImageView imageViewAnimation = findViewById(R.id.imageView_hero1);
                final ImageView imageViewAnimation2 = findViewById(R.id.imageView_hero2);
                imageViewAnimation2.setTranslationX(-800);
                imageViewAnimation2.setTranslationY(0);

                final int SPLASH_DISPLAY_LENGTH = 1000;

                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {

                        imageViewAnimation2.setTranslationX(0);
                        imageViewAnimation2.setTranslationY(0);
                    }

                }, SPLASH_DISPLAY_LENGTH);

                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {

                        Animation anim = new AlphaAnimation(0.0f, 1.0f);
                        anim.setDuration(500);
                        anim.setStartOffset(20);
                        anim.setRepeatMode(Animation.REVERSE);
                        imageViewAnimation.startAnimation(anim);
                    }

                }, 0);
                int mana = new Random().nextInt((15 - 8) + 1) + 8;
                int attackValue = new Random().nextInt((10 - 5)+1) +5;
                hero1.setLife(hero1.getLife() - attackValue);
                hero2.setMana(hero2.getMana() + mana);
                damageFromHero2 += attackValue;
                damageToHero1 += attackValue;
                if (hero2.getMana() > 50) {
                    hero2.setMana(50);
                    msgMana2.startAnimation(anim);
                    msgMana2.setVisibility(View.VISIBLE);
                    spell2.setEnabled(true);
                    heal2.setEnabled(true);
                } else {
                    msgMana2.setVisibility(View.INVISIBLE);
                    spell2.setEnabled(false);
                    heal2.setEnabled(false);
                }
                if (hero2.getLife() >= 100) {
                    hero2.setLife(100);
                    heal2.setEnabled(false);
                }
                attack1.setVisibility(View.VISIBLE);
                spell1.setVisibility(View.VISIBLE);
                heal1.setVisibility(View.VISIBLE);
                attack2.setVisibility(View.INVISIBLE);
                spell2.setVisibility(View.INVISIBLE);
                heal2.setVisibility(View.INVISIBLE);


                hero1.setLife(hero1.getLife() - attackValue);
                health1.setSecondaryProgress(100);
                health1.setProgress(hero1.getLife());

                hero2.setMana(hero2.getMana() + mana);

                if (hero1.isKo()) {
                    attack1.setVisibility(View.INVISIBLE);
                    spell1.setVisibility(View.INVISIBLE);
                    heal1.setVisibility(View.INVISIBLE);
                    attack2.setVisibility(View.INVISIBLE);
                    spell2.setVisibility(View.INVISIBLE);
                    heal2.setVisibility(View.INVISIBLE);
                    hero1.setLife(0);
                    ko.setText("Player 2 Wins !");
                    ko.setVisibility(View.VISIBLE);
                    imgHero1.setVisibility(View.INVISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Intent intent = new Intent(ArenaActivity.this, ResumeFightActivity.class);
                            intent.putExtra(EXTRA_PARCEL_WIN, hero2Parcel);
                            intent.putExtra(EXTRA_PARCEL_LOSS, hero1Parcel);
                            intent.putExtra("damage_from_loss", damageFromHero1);
                            intent.putExtra("damage_from_win", damageFromHero2);
                            intent.putExtra("damage_to_win", damageToHero2);
                            intent.putExtra("damage_to_loss", damageToHero1);
                            intent.putExtra("heal_win", healHero2);
                            intent.putExtra("heal_loss", healHero1);
                            startActivity(intent);
                        }

                    }, 3000);
                }

            }
        });



        spell2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgHero2.setTranslationX(-800);
                imgHero2.setTranslationY(0);

                final int SPLASH_DISPLAY_LENGTH = 1000;

                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {

                        imgHero2.setTranslationX(0);
                        imgHero2.setTranslationY(0);
                    }

                }, SPLASH_DISPLAY_LENGTH);

                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {

                        Animation anim = new AlphaAnimation(0.0f, 1.0f);
                        anim.setDuration(300);
                        anim.setStartOffset(20);
                        anim.setRepeatMode(Animation.REVERSE);
                        anim.setRepeatCount(3);
                        imgHero1.startAnimation(anim);
                    }

                }, 0);
                int spellHealValue = new Random().nextInt((20 - 15) +1) +15;
                hero1.setLife(hero1.getLife() - spellHealValue);
                hero2.setMana(0);
                damageToHero1 += spellHealValue;
                damageFromHero2 += spellHealValue;
                if (hero2.getMana() > 50) {
                    hero2.setMana(50);
                    spell2.setEnabled(true);
                    heal2.setEnabled(true);
                } else {
                    spell2.setEnabled(false);
                    heal2.setEnabled(false);
                }
                if (hero2.getLife() >= 100) {
                    hero2.setLife(100);
                    heal2.setEnabled(false);
                }

                attack1.setVisibility(View.VISIBLE);
                spell1.setVisibility(View.VISIBLE);
                heal1.setVisibility(View.VISIBLE);
                attack2.setVisibility(View.INVISIBLE);
                spell2.setVisibility(View.INVISIBLE);
                heal2.setVisibility(View.INVISIBLE);


                hero1.setLife(hero1.getLife() - spellHealValue);
                health1.setSecondaryProgress(100);
                health1.setProgress(hero1.getLife());

                hero2.setMana(hero2.getMana() - 50);
                msgMana2.clearAnimation();
                msgMana2.setVisibility(View.INVISIBLE);

                if (hero1.isKo()) {
                    attack1.setVisibility(View.INVISIBLE);
                    spell1.setVisibility(View.INVISIBLE);
                    heal1.setVisibility(View.INVISIBLE);
                    attack2.setVisibility(View.INVISIBLE);
                    spell2.setVisibility(View.INVISIBLE);
                    heal2.setVisibility(View.INVISIBLE);
                    hero1.setLife(0);
                    ko.setText("Player 2 Wins !");
                    ko.setVisibility(View.VISIBLE);
                    imgHero1.setVisibility(View.INVISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(ArenaActivity.this, ResumeFightActivity.class);
                            intent.putExtra(EXTRA_PARCEL_WIN, hero2);
                            intent.putExtra(EXTRA_PARCEL_LOSS, hero1);
                            intent.putExtra("damage_from_loss", damageFromHero1);
                            intent.putExtra("damage_from_win", damageFromHero2);
                            intent.putExtra("damage_to_win", damageToHero2);
                            intent.putExtra("damage_to_loss", damageToHero1);
                            intent.putExtra("heal_win", healHero2);
                            intent.putExtra("heal_loss", healHero1);
                            startActivity(intent);
                        }

                    }, 3000);
                }
            }
        });

        heal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {

                        Animation anim = new AlphaAnimation(0.0f, 1.0f);
                        anim.setDuration(1200);
                        anim.setStartOffset(100);
                        anim.setRepeatMode(Animation.REVERSE);
                        imgHero2.startAnimation(anim);
                    }

                }, 0);
                int spellHealValue = new Random().nextInt((20 - 15) +1) +15;
                hero2.setLife(hero2.getLife() + spellHealValue);
                hero2.setMana(0);
                healHero2 += spellHealValue;
                if (hero2.getMana() > 50) {
                    hero2.setMana(50);
                    spell2.setEnabled(true);
                    heal2.setEnabled(true);
                } else {
                    heal2.setEnabled(false);
                    spell2.setEnabled(false);
                }
                if (hero2.getLife() >= 100) {
                    hero2.setLife(100);
                    heal2.setEnabled(false);
                }
                attack1.setVisibility(View.VISIBLE);
                spell1.setVisibility(View.VISIBLE);
                heal1.setVisibility(View.VISIBLE);
                attack2.setVisibility(View.INVISIBLE);
                spell2.setVisibility(View.INVISIBLE);
                heal2.setVisibility(View.INVISIBLE);

                health2.setSecondaryProgress(100);
                health2.setProgress(hero2.getLife());

                hero2.setMana(hero2.getMana() - 50);
                msgMana2.setVisibility(View.INVISIBLE);
                msgMana2.clearAnimation();
            }
        });
    }

    public ArenaActivity(){

    }

}
