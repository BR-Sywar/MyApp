package com.example.data.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.data.models.HorairesDeTravail;
import com.example.myapp.R;
import com.example.utils.OnRecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.utils.AppConstants.BASE_URL;

public class HoraireAdapter extends RecyclerView.Adapter<HoraireAdapter.MyViewHolder> {
    private Context mContext;
    private List<HorairesDeTravail> horaireList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
       private TextView jour , debut_matin , fin_matin , debut_ap , fin_ap ;

        public MyViewHolder(View view) {
            super(view);

          jour = view.findViewById(R.id.h_jour);
          debut_matin = view.findViewById(R.id.h_debut_matin);
          fin_matin = view.findViewById(R.id.h_fin_matin);
          debut_ap = view.findViewById(R.id.h_debut_ap);
          fin_ap = view.findViewById(R.id.h_fin_ap);

        }

    }



    public HoraireAdapter(Context mContext) {
        horaireList = new ArrayList<>();
        this.mContext = mContext;

    }

    public void setHoraireList
            (List<HorairesDeTravail> horaireList) {
        this.horaireList = horaireList;
        notifyDataSetChanged();
    }

    public HorairesDeTravail getItem (int position) {
        return  horaireList.get(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.horaire_item, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final HorairesDeTravail horaire = horaireList.get(position);
        holder.jour.setText(horaire.getJours());
        holder.debut_matin.setText(horaire.getHeureDebutMatin());
        holder.fin_matin.setText(horaire.getHeureFinMatin());
        holder.debut_ap.setText(horaire.getHeureDebutAp());
        holder.fin_ap.setText(horaire.getHeureFinAp());



    }

    @Override
    public int getItemCount() {
        return horaireList.size();
    }
}
