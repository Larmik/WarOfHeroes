package fr.wcs.warofheroes;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class VsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vs);
        final int SPLASH_DISPLAY_LENGTH = 2500;

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent mainIntent = new Intent(VsActivity.this,MainActivity.class);
                VsActivity.this.startActivity(mainIntent);
                VsActivity.this.finish();
            }

        }, SPLASH_DISPLAY_LENGTH);
    }
}
