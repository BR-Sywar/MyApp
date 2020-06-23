package com.example.ui.Accueil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.data.adapters.EntrepriseAdapter;
import com.example.data.models.Category;
import com.example.myapp.R;
import com.example.network.RetrofitInstance;
import com.example.network.api.EntrepriseApi;
import com.example.network.response.EntrepriseResponse;
import com.example.ui.Maps.Maps;
import com.example.ui.ui.SpinnerInteractionListener;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.utils.AppConstants.BASE_URL;

public class EntrepriseFragment extends Fragment  {
    private Category category ;
    private ListView mListView ;
    private EntrepriseAdapter entrepriseAdapter ;
    private EntrepriseApi api  ;
    private ProgressBar mProgressBar ;
    private TextView title ;
    private ImageView logo ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_entreprise, container, false);
            mListView = root.findViewById(R.id.list_entreprise);
            title = root.findViewById(R.id.title_entre);
            logo = root.findViewById(R.id.logo_entre);
            mProgressBar = root.findViewById(R.id.progressBar);
            entrepriseAdapter = new EntrepriseAdapter(getContext());
            mListView.setAdapter(entrepriseAdapter);
        if (getArguments() != null) {
            category = getArguments().getParcelable("cat");
            title.setText(category.getNom());
            Glide.with(getContext()).load(BASE_URL+category.getIcone()).into(logo);
            getEntreprises();
        }
        return root ;

    }

    public void getEntreprises() {
        api = RetrofitInstance.getInstance().create(EntrepriseApi.class);
        Call<EntrepriseResponse> call = api.getEntreprises(category.getId()) ;
        mProgressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<EntrepriseResponse>() {
            @Override
            public void onResponse(Call<EntrepriseResponse> call, Response<EntrepriseResponse> response) {
                EntrepriseResponse entrepriseResponse = response.body();
                mProgressBar.setVisibility(View.GONE);
                if (entrepriseResponse != null && entrepriseResponse.getEntreprises() != null) {
                    entrepriseAdapter.setEntrepriseList(entrepriseResponse.getEntreprises());
                }
            }

            @Override
            public void onFailure(Call<EntrepriseResponse> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }
}
