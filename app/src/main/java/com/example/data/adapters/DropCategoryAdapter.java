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
import com.example.data.models.Category;
import com.example.myapp.R;

import java.util.ArrayList;
import java.util.List;

// Creating an Adapter Class
public class DropCategoryAdapter extends ArrayAdapter<Category> {

    //private List<Category> categories  = new ArrayList<>();

    public DropCategoryAdapter(Context context ,List<Category> categories ) {
        super(context,0,categories);

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

        Category category = getItem(position);

        ImageView image = (ImageView)listItem.findViewById(R.id.img_item);
        TextView name = (TextView) listItem.findViewById(R.id.tv_item);
        if (category!=null)
        { Glide.with(getContext()).load(category.getIcone()).into(image) ;


        name.setText(category.getNom());
        }


        return listItem;
    }
}
