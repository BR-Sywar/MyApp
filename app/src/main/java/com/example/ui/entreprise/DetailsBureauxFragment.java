package com.example.ui.entreprise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.data.models.Category;
import com.example.data.models.Entreprise;
import com.example.data.models.User;
import com.example.myapp.R;
import com.example.network.RetrofitInstance;
import com.example.network.api.TicketApi;
import com.example.network.response.RegisterResponse;
import com.example.utils.CustomLoginDialog;
import com.example.utils.ReservationDialog;
import com.example.utils.SessionHandler;

import androidx.fragment.app.Fragment;

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
        private Button btnReserver ;
        private TicketApi api ;
        private CustomLoginDialog customLoginDialog  ;
        private ReservationDialog reservationDialog  ;
        private SessionHandler sessionHandler ;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View root = inflater.inflate(R.layout.fragment_details_bureaux, container, false);
            if (getArguments() != null) {
                entreprise = getArguments().getParcelable("entre");
            }
            logo = root.findViewById(R.id.d_logo);
            nom = root.findViewById(R.id.d_name);
            address = root.findViewById(R.id.d_address);
            ville = root.findViewById(R.id.d_ville) ;
            email = root.findViewById(R.id.d_email);
            tel = root.findViewById(R.id.d_tel) ;
            code_postal = root.findViewById(R.id.d_code_Postal);
            btnReserver = root.findViewById(R.id.btn_reserve) ;
            api = RetrofitInstance.getInstance().create(TicketApi.class);
            sessionHandler = SessionHandler.getInstance(getContext());
            customLoginDialog = new CustomLoginDialog(getContext());
            setDetails();
            reserveTicket();

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
}
