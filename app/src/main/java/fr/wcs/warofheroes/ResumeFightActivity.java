package fr.wcs.warofheroes;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static fr.wcs.warofheroes.ArenaActivity.EXTRA_PARCEL_LOSS;
import static fr.wcs.warofheroes.ArenaActivity.EXTRA_PARCEL_WIN;

public class ResumeFightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_fight);

        Button bLeave = findViewById(R.id.button_leave_game);
        ImageView winPic = findViewById(R.id.win_pic);
        ImageView lossPic = findViewById(R.id.loss_pic);
        TextView winName = findViewById(R.id.win_name);
        TextView lossName = findViewById(R.id.loss_name);
        TextView damagesFromWin = findViewById(R.id.damages_from_win);
        TextView damagesToWin = findViewById(R.id.damages_to_win);
        TextView healWin = findViewById(R.id.heal_win);
        TextView damagesFromLoss = findViewById(R.id.damages_from_loss);
        TextView damagesToLoss = findViewById(R.id.damages_to_loss);
        TextView healLoss = findViewById(R.id.heal_loss);
        TextView thanks = findViewById(R.id.thanks);

        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(1000);
        anim.setStartOffset(600);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        thanks.startAnimation(anim);

        HeroesModel winner = getIntent().getParcelableExtra(EXTRA_PARCEL_WIN);
        HeroesModel loser = getIntent().getParcelableExtra(EXTRA_PARCEL_LOSS);

        Glide.with(this).load(winner.getImage()).into(winPic);
        Glide.with(this).load(loser.getImage()).into(lossPic);
        winName.setText(String.format("Winner : %s", winner.getName()));
        lossName.setText(String.format("Loser : %s", loser.getName()));

        damagesFromWin.setText(String.valueOf(getIntent().getIntExtra("damage_from_win", 0)));
        damagesToWin.setText(String.valueOf(getIntent().getIntExtra("damage_to_win", 0)));
        damagesFromLoss.setText(String.valueOf(getIntent().getIntExtra("damage_from_loss", 0)));
        damagesToLoss.setText(String.valueOf(getIntent().getIntExtra("damage_to_loss", 0)));
        healWin.setText(String.valueOf(getIntent().getIntExtra("heal_win", 0)));
        healLoss.setText(String.valueOf(getIntent().getIntExtra("heal_loss", 0)));




        bLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToMainActivity = new Intent(ResumeFightActivity.this, MainActivity.class);
                startActivity(goToMainActivity);
            }
        });

        Button bStartAgain = findViewById(R.id.button_start);
        bStartAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoChooseHeroes = new Intent(ResumeFightActivity.this,ChooseHeroesActivity.class);
                startActivity(gotoChooseHeroes);
            }
        });

    }
}
