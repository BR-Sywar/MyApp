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
import com.example.data.models.Ticket;
import com.example.myapp.R;
import com.example.utils.OnRecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.utils.AppConstants.BASE_URL;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.MyViewHolder> {
    private Context mContext;
    private List<Ticket> ticketList;
    OnRecyclerViewItemClickListener listener ;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView username , entreprise , num , date , heure;
        

        public MyViewHolder(View view) {
            super(view);

            username = view.findViewById(R.id.ticket_nom_user);

            entreprise = view.findViewById(R.id.ticket_nom_entre);
            num = view.findViewById(R.id.ticket_num);
            date     = view.findViewById(R.id.ticket_date);
            heure = view.findViewById(R.id.ticket_heure);
            

        }

    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    public TicketAdapter(Context mContext) {
        ticketList = new ArrayList<>();
        this.mContext = mContext;

    }

    public void setTicketList
            (List<Ticket> ticketList) {
        this.ticketList = ticketList;
        notifyDataSetChanged();
    }

    public Ticket getItem (int position) {
        return  ticketList.get(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ticket_item, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Ticket ticket = ticketList.get(position);
        holder.username.setText(ticket.getUtilisateur());
        holder.entreprise.setText(ticket.getEntreprise());
        holder.num.setText(ticket.getNum());
        holder.date.setText(ticket.getDate().getDate());
        holder.heure.setText(ticket.getHeure());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecyclerViewItemClicked(position,0);
            }
        });


    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }
}
