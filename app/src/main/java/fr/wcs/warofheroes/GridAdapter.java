package fr.wcs.warofheroes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import java.util.ArrayList;


/**
 * Created by wilder on 05/04/18.
 */

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



        heroImage.setImageResource(hero.getImage());

        return convertView;

    }
}
