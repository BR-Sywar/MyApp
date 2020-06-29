package com.example.data.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.data.models.Category;
import com.example.data.models.Entreprise;
import com.example.myapp.R;
import com.example.utils.OnRecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.utils.AppConstants.BASE_URL;

public class EntrepriseMapAdapter extends RecyclerView.Adapter<EntrepriseMapAdapter.ViewHolder> implements Filterable {
    private List<Entreprise> entrepriseList=new ArrayList<>();
    private Context context;
    public List<Entreprise> orig;
    private OnRecyclerViewItemClickListener listener;

    public EntrepriseMapAdapter(Context context) {
        this.context = context;
    }

    public void setEntrepriseList(List<Entreprise> entrepriseList) {
        this.entrepriseList = entrepriseList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_nom, tv_adresse, tv_tel, tv_code;
        private ImageView entreprise_logo;

        public ViewHolder(@NonNull View convertView) {
            super(convertView);
            tv_nom = convertView.findViewById(R.id.item_nom);
            tv_adresse = convertView.findViewById(R.id.item_adresse);
            tv_code = convertView.findViewById(R.id.item_code);
            tv_tel = convertView.findViewById(R.id.item_tel);
            entreprise_logo = convertView.findViewById(R.id.item_logo);
        }
    }
    @Override
    public int getItemCount() {
        if (entrepriseList == null) return 0;
        return entrepriseList.size();
    }

    public Entreprise getItem(int position) {
        return entrepriseList.get(position);
    }

    @Override
    public EntrepriseMapAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_entreprise, parent, false);


        return new EntrepriseMapAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, final int position) {
        Entreprise entreprise = entrepriseList.get(position);


        holder.tv_tel.setText(entreprise.getTel());
        holder.tv_adresse.setText(entreprise.getAdresse());
        holder.tv_code.setText(String.valueOf(entreprise.getCodePostal()));
        holder.tv_nom.setText(entreprise.getNom());
        Glide.with(context)
                .load(BASE_URL + entreprise.getLogo())
                .into(holder.entreprise_logo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecyclerViewItemClicked(position,0);
            }
        });

    }

    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<Entreprise> results = new ArrayList<Entreprise>();
                if (orig == null)
                    orig = entrepriseList;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final Entreprise g : orig) {
                            if (g.getNom().toLowerCase()
                                    .contains(constraint.toString()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                entrepriseList = (ArrayList<Entreprise>) results.values;
                EntrepriseMapAdapter.this.notifyDataSetChanged();
            }
        };
    }








}
