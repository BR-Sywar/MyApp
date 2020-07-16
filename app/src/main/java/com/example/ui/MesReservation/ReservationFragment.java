package com.example.ui.MesReservation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.data.adapters.TicketAdapter;
import com.example.data.models.User;
import com.example.myapp.R;
import com.example.network.RetrofitInstance;
import com.example.network.api.TicketApi;
import com.example.network.response.TicketResponse;
import com.example.utils.CustomLoginDialog;
import com.example.utils.SessionHandler;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationFragment extends Fragment {

    private SessionHandler sessionHandler ;
    private CustomLoginDialog customLoginDialog ;
    private User user ;
    TicketApi api ;
    private RecyclerView recyclerView ;
    private TicketAdapter adapter ;
    private TextView no_ticket ;
    private ProgressBar progressBar ;
    @Override

    public View onCreateView (@NonNull LayoutInflater inflater,
                              ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_reservation, container, false);
        recyclerView = root.findViewById(R.id.ticket_recycler);
        no_ticket = root.findViewById(R.id.no_tickets);
        progressBar = root.findViewById(R.id.progressBar);
        sessionHandler = SessionHandler.getInstance(getContext());
        customLoginDialog = new CustomLoginDialog(getContext(),null);
        getUser();
        prepareRecycler();
        getReservations();
        return root;

    }
private void  getUser() {
    if (sessionHandler.isLoggedIn()) {
        user = sessionHandler.getUserDetails();

    }else customLoginDialog.show();
}
private void prepareRecycler() {
        adapter = new TicketAdapter(getContext());
    LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
    recyclerView.setLayoutManager(manager);
    recyclerView.setHasFixedSize(true);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(adapter);
}
private void getReservations() {
        api = RetrofitInstance.getInstance().create(TicketApi.class);
        progressBar.setVisibility(View.VISIBLE);
    Call<TicketResponse> call = api.getUserTickets(user.getId());
    call.enqueue(new Callback<TicketResponse>() {
        @Override
        public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response) {
            TicketResponse ticketResponse = response.body();
            progressBar.setVisibility(View.GONE);
            if (ticketResponse != null && ticketResponse.getData() != null) {
                adapter.setTicketList(ticketResponse.getData());
                if (ticketResponse.getData().size() == 0) {
                    no_ticket.setVisibility(View.VISIBLE);
                }

            }
        }

        @Override
        public void onFailure(Call<TicketResponse> call, Throwable t) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getContext(),"error",Toast.LENGTH_SHORT).show();
        }
    });

}
}
