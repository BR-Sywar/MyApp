package com.example.ui.entreprise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.data.adapters.DropCategoryAdapter;
import com.example.data.adapters.DropEntrepriseAdapter;
import com.example.data.adapters.EventAdapter;
import com.example.data.models.Category;
import com.example.data.models.Entreprise;
import com.example.myapp.R;
import com.example.network.RetrofitInstance;
import com.example.network.api.CategoryApi;
import com.example.network.api.EntrepriseApi;
import com.example.network.response.CategoryResponse;
import com.example.network.response.EntrepriseResponse;
import com.example.network.response.EventResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.utils.AppConstants.BASE_URL;

public class ActualitesEntrepriseFragment extends Fragment {
    private RecyclerView recyclerView ;
    private EntrepriseApi entrepriseApi ;
    private EventAdapter eventAdapter ;
    private ProgressBar mProgressBar ;
    private Entreprise entreprise ;
    private ImageView logo ;
    private TextView  name ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_entreprise_actualites, container, false);


        recyclerView =  root.findViewById(R.id.recycler_events);
        mProgressBar = root.findViewById(R.id.progressBar);
        logo = root.findViewById(R.id.a_logo);
        name =root.findViewById(R.id.a_name);
        entrepriseApi = RetrofitInstance.getInstance().create(EntrepriseApi.class);
        prepareRecyclerView();
       if (getArguments()!=null)
           entreprise = getArguments().getParcelable("entre");
       if (entreprise!=null)
       {  getEvents(entreprise.getId());
           Glide.with(getContext()).load(BASE_URL+entreprise.getLogo()).into(logo);
           name.setText(entreprise.getNom());
       }


        return root;
    }

    private void prepareRecyclerView() {
        eventAdapter = new EventAdapter(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(eventAdapter);
     
    }
    private void getEvents ( int id) {
        Call<EventResponse> call = entrepriseApi.getEvents(id);
        mProgressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                mProgressBar.setVisibility(View.GONE);
                EventResponse eventResponse = response.body() ;
                if (eventResponse != null && eventResponse.getEvents() != null) {
                    eventAdapter.setEventList(eventResponse.getEvents());
                }

            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

}