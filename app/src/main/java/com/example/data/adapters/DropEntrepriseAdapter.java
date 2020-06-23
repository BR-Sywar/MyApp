package com.example.data.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.data.models.Entreprise;
import com.example.myapp.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.utils.AppConstants.BASE_URL;

// Creating an Adapter Class
public class DropEntrepriseAdapter extends ArrayAdapter<Entreprise> {


    public DropEntrepriseAdapter(Context context,List<Entreprise> entreprises) {
        super(context,0,entreprises);

    }

    // It gets a View that displays in the drop down popup the data at the specified position
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getView(position,convertView,parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.item_category_drop,parent,false);

        Entreprise entreprise = getItem(position);

        ImageView image = (ImageView)listItem.findViewById(R.id.img_item);
        TextView name = (TextView) listItem.findViewById(R.id.tv_item);
        if (entreprise!=null)
        {Glide.with(getContext()).load(BASE_URL+entreprise.getLogo()).into(image) ;
        name.setText(entreprise.getNom());}


        return listItem;
    }
}
