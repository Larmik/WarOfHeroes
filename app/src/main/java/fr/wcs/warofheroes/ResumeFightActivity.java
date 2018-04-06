package fr.wcs.warofheroes;

import android.content.Intent;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ResumeFightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_fight);

        Button bLeave = findViewById(R.id.button_leave_game);
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
