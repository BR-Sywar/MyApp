package com.example.ui.entreprise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.data.adapters.DropEntrepriseAdapter;
import com.example.data.adapters.EventAdapter;
import com.example.data.adapters.DropCategoryAdapter;
import com.example.data.models.Entreprise;
import com.example.myapp.R;
import com.example.network.RetrofitInstance;
import com.example.network.api.CategoryApi;
import com.example.network.api.EntrepriseApi;
import com.example.network.response.CategoryResponse;
import com.example.network.response.EntrepriseResponse;
import com.example.network.response.EventResponse;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActualitesFragment extends Fragment {
    private RecyclerView recyclerView ;
    private CategoryApi categoryApi ;
    private EntrepriseApi entrepriseApi ;
    private EventAdapter eventAdapter ;
    private ProgressBar mProgressBar ;
    private DropCategoryAdapter categoryAdapter ;
    private DropEntrepriseAdapter entrepriseAdapter ;
    private Spinner category_spinner , entreprise_spinner ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_actualites, container, false);
        category_spinner = root.findViewById(R.id.spinner_cat) ;
        entreprise_spinner = root.findViewById(R.id.spinner_entre) ;

        recyclerView =  root.findViewById(R.id.recycler_events);
        mProgressBar = root.findViewById(R.id.progressBar);
        entrepriseApi = RetrofitInstance.getInstance().create(EntrepriseApi.class);
        categoryApi = RetrofitInstance.getInstance().create(CategoryApi.class);
        prepareRecyclerView();
        getCategories();


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
    public void getEntreprises( int id) {

        Call<EntrepriseResponse> call = entrepriseApi.getEntreprises(id) ;
        mProgressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<EntrepriseResponse>() {
            @Override
            public void onResponse(Call<EntrepriseResponse> call, Response<EntrepriseResponse> response) {
                EntrepriseResponse entrepriseResponse = response.body();
                mProgressBar.setVisibility(View.GONE);
                if (entrepriseResponse != null && entrepriseResponse.getEntreprises() != null) {
                    entrepriseAdapter = new DropEntrepriseAdapter(getContext(),entrepriseResponse.getEntreprises()) ;
                    entreprise_spinner.setAdapter(entrepriseAdapter);
                    if (entrepriseResponse.getEntreprises().size()>0)
                    getEvents(entrepriseResponse.getEntreprises().get(0).getId());

                }
            }

            @Override
            public void onFailure(Call<EntrepriseResponse> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
            }
        });

        entreprise_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Entreprise entreprise = entrepriseAdapter.getItem(position);
                getEvents(entreprise.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private void getCategories() {

        Call<CategoryResponse> call = categoryApi.getCategories();
        mProgressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                mProgressBar.setVisibility(View.GONE);
                CategoryResponse categoryResponse = response.body() ;
                if (categoryResponse != null && categoryResponse.getCategories() != null) {
                    categoryAdapter = new DropCategoryAdapter(getContext(),categoryResponse.getCategories());
                    category_spinner.setAdapter(categoryAdapter);
                    getEntreprises(categoryResponse.getCategories().get(0).getId());
                }

            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
            }
        });

    }
}