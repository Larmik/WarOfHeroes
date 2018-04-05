package fr.wcs.warofheroes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView ivLogo = findViewById(R.id.image_glide);
        String url = "https://wildcodeschool.fr/wp-content/uploads/2017/01/logo_orange_pastille.png";
        Glide.with(this).load(url) .into(ivLogo);




    }
}
