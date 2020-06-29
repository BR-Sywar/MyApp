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
import com.example.utils.OnRecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.utils.AppConstants.BASE_URL;

public  class EntrepriseAdapter extends BaseAdapter  {
    private List<Entreprise> entrepriseList;
    private Context context;
    private OnRecyclerViewItemClickListener listener ;
    public EntrepriseAdapter(Context context) {
        this.context=context;
    }

    public void setEntrepriseList(List<Entreprise> entrepriseList) {
        this.entrepriseList = entrepriseList;
        notifyDataSetChanged();
    }
    public void  setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener =listener ;
    }

    @Override
    public int getCount() {
        if (entrepriseList==null) return 0;
        return entrepriseList.size();
    }

    public  Entreprise getItem (int position) {
        return  entrepriseList.get(position) ;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
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
                .load(BASE_URL+entreprise.getLogo())
                .into(holder.entreprise_logo) ;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecyclerViewItemClicked(position,0);
            }
        });
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
