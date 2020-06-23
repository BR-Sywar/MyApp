package com.example.data.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.data.models.Category;
import com.example.myapp.R;
import com.example.utils.OnRecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private Context mContext;
    private List<Category> categoryList;
    OnRecyclerViewItemClickListener listener ;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Button name;
        ImageView photo;

        public MyViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.btn_sante);

            photo = view.findViewById(R.id.imagesante);

        }

    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    public CategoryAdapter(Context mContext) {
        categoryList = new ArrayList<>();
        this.mContext = mContext;

    }

    public void setCategoryList
            (List<Category> categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    public Category getItem (int position) {
        return  categoryList.get(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Category category = categoryList.get(position);
        holder.name.setText(category.getNom());
        Glide.with(mContext).load(category.getIcone()).into(holder.photo);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecyclerViewItemClicked(position,0);
            }
        });


    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
