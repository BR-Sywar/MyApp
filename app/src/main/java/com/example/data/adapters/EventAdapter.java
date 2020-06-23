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
import com.example.data.models.Event;
import com.example.data.models.Event;
import com.example.myapp.R;
import com.example.utils.OnRecyclerViewItemClickListener;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import static com.example.utils.AppConstants.sdf;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {
    private Context mContext;
    private List<Event> eventList;
    OnRecyclerViewItemClickListener listener ;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title , start_date ,end_date , start_hour , end_hour ;

        public MyViewHolder(View view) {
            super(view);

            title = view.findViewById(R.id.event_title);

            start_date = view.findViewById(R.id.event_start_date);
            end_date = view.findViewById(R.id.event_end_date) ;
            start_hour = view.findViewById(R.id.event_start_hour) ;
            end_hour = view.findViewById(R.id.event_end_hour) ;


        }

    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    public EventAdapter(Context mContext) {
        eventList = new ArrayList<>();
        this.mContext = mContext;

    }

    public void setEventList
            (List<Event> eventList) {
        this.eventList = eventList;
        notifyDataSetChanged();
    }


    public Event getItem (int position) {
        return  eventList.get(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Event event = eventList.get(position);
     holder.title.setText(event.getTitre());
        Timestamp ts = Timestamp.valueOf(event.getDateDebut().getDate());
        Date date = new Date(ts.getTime()) ;
     holder.start_date.setText( sdf.format(date));
     holder.start_hour.setText(event.getHeureDebut().subSequence(0,5));
        Timestamp ts1 = Timestamp.valueOf(event.getDateDebut().getDate());
        Date date1 = new Date(ts1.getTime()) ;
     holder.end_date.setText(sdf.format(date1));
     holder.end_hour.setText(event.getHeureFin().subSequence(0,5));


    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}

