package com.example.ui.entreprise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.data.adapters.EventAdapter;
import com.example.data.adapters.HoraireAdapter;
import com.example.data.models.Category;
import com.example.data.models.Entreprise;
import com.example.data.models.User;
import com.example.myapp.R;
import com.example.network.RetrofitInstance;
import com.example.network.api.EntrepriseApi;
import com.example.network.api.TicketApi;
import com.example.network.response.EventResponse;
import com.example.network.response.HoraireResponse;
import com.example.network.response.RegisterResponse;
import com.example.utils.CustomLoginDialog;
import com.example.utils.ReservationDialog;
import com.example.utils.SessionHandler;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.utils.AppConstants.BASE_URL;

public class DetailsBureauxFragment extends Fragment {
        private TextView nom , address ,email , tel ,ville , code_postal ;
        private ImageView logo ;
        private Entreprise entreprise ;
        private Button btnReserver ,btnActualite ;
        private TicketApi api ;
        private EntrepriseApi entrepriseApi ;
        private CustomLoginDialog customLoginDialog  ;
        private ReservationDialog reservationDialog  ;
        private SessionHandler sessionHandler ;
        private ProgressBar mProgressBar ;
        private HoraireAdapter adapter ;
        private RecyclerView recyclerView ;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            final View root = inflater.inflate(R.layout.fragment_details_bureaux, container, false);
            if (getArguments() != null) {
                entreprise = getArguments().getParcelable("entre");
            }
            logo = root.findViewById(R.id.d_logo);
            nom = root.findViewById(R.id.d_name);
            address = root.findViewById(R.id.d_address);
            ville = root.findViewById(R.id.d_ville) ;
            email = root.findViewById(R.id.d_email);
            tel = root.findViewById(R.id.d_tel) ;
            recyclerView = root.findViewById(R.id.recycler_horaires);
            mProgressBar = root.findViewById(R.id.progressBar);
            code_postal = root.findViewById(R.id.d_code_Postal);
            btnReserver = root.findViewById(R.id.btn_reserve) ;
            btnActualite =root.findViewById(R.id.btn_actualite);
            api = RetrofitInstance.getInstance().create(TicketApi.class);
            sessionHandler = SessionHandler.getInstance(getContext());
            customLoginDialog = new CustomLoginDialog(getContext());
            setDetails();
            reserveTicket();
            btnActualite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("entre",entreprise);
                    Navigation.findNavController(root).navigate(R.id.nav_Act,bundle);

                }
            });
            return root;
        }
        private void  setDetails() {

            if (entreprise!=null) {
                Glide.with(getContext()).load(BASE_URL +entreprise.getLogo()).into(logo);
                nom.setText(entreprise.getNom());
                ville.setText(entreprise.getVille());
                address.setText(entreprise.getAdresse());
                email.setText(entreprise.getEmail());
                tel.setText(entreprise.getTel());
                code_postal.setText(String.valueOf(entreprise.getCodePostal()));
                geHoraires();

            }
        }
        private void reserveTicket() {




            btnReserver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (sessionHandler.isLoggedIn()) {
                        User user = sessionHandler.getUserDetails();
                        reservationDialog = new ReservationDialog(getContext(), user , entreprise);
                        reservationDialog.show();


                    }else customLoginDialog.show();

                }
            });
        }
    private void prepareRecyclerView() {
        adapter = new HoraireAdapter(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }
    private void geHoraires ( ) {
            prepareRecyclerView();
            entrepriseApi   = RetrofitInstance.getInstance().create(EntrepriseApi.class);
        Call<HoraireResponse> call = entrepriseApi.getHoraires(entreprise.getId());
        mProgressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<HoraireResponse>() {
            @Override
            public void onResponse(Call<HoraireResponse> call, Response<HoraireResponse> response) {
                mProgressBar.setVisibility(View.GONE);
                HoraireResponse horaireResponse = response.body() ;
                if (horaireResponse != null && horaireResponse.getHorairesDeTravail() != null) {
                    adapter.setHoraireList(horaireResponse.getHorairesDeTravail());
                }

            }

            @Override
            public void onFailure(Call<HoraireResponse> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }
}
