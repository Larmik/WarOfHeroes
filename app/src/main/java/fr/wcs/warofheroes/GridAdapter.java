package fr.wcs.warofheroes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.util.ArrayList;


public class GridAdapter extends ArrayAdapter<HeroesModel> {


    public GridAdapter(Context context, ArrayList<HeroesModel> heroes) {
        super(context, 0, heroes);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        HeroesModel hero = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_hero, parent, false);

        }

        ImageView heroImage = convertView.findViewById(R.id.item_img);
        Glide.with(parent).load(hero.getImage()).into(heroImage);

        return convertView;

    }
}
