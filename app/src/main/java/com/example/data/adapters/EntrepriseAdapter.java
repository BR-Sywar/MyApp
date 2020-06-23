package com.example.data.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.data.models.Entreprise;
import com.example.myapp.R;

import java.util.ArrayList;
import java.util.List;

public  class EntrepriseAdapter extends BaseAdapter  {
    private List<Entreprise> entrepriseList;
    private Context context;
    public EntrepriseAdapter(Context context) {
        this.context=context;
    }

    public void setEntrepriseList(List<Entreprise> entrepriseList) {
        this.entrepriseList = entrepriseList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (entrepriseList==null) return 0;
        return entrepriseList.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder  ;
        Entreprise entreprise = entrepriseList.get(position);
        if(convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.entreprise_item, null, true);

            holder.tv_title  = convertView.findViewById(R.id.entreprise_title);
            holder.entreprise_logo=convertView.findViewById(R.id.entreprise_photo) ;

            convertView.setTag(holder);
        }
        else  {holder = (ViewHolder)convertView.getTag();}
        holder.tv_title.setText(entreprise.getNom());
        Glide.with(context)
                .load(entreprise.getLogo())
                .into(holder.entreprise_logo) ;
        return convertView;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    private class ViewHolder {

        private TextView tv_title;
        private ImageView entreprise_logo;

    }
   

   
}
