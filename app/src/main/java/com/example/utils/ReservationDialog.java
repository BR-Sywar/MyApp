package com.example.utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.data.models.Entreprise;
import com.example.data.models.Ticket;
import com.example.data.models.User;
import com.example.myapp.R;
import com.example.network.RetrofitInstance;
import com.example.network.api.TicketApi;
import com.example.network.response.RegisterResponse;
import com.example.ui.Login;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationDialog {


    private final Context mContext;
    private final User user;
    private final Entreprise entreprise;
    private final AlertDialog alertDialog1;
    private TicketApi api;
    private int mYear, mMonth, mDay;

    public ReservationDialog(Context context, final User user, final Entreprise entreprise) {
        this.mContext = context;
        this.user = user;
        this.entreprise = entreprise;

        LayoutInflater li = LayoutInflater.from(mContext);
        //Creating a view to get the dialog box
        View confirmDialog = li.inflate(R.layout.reservation, null);
        final AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        // Include dialog.xml file
        dialog.setView(confirmDialog);
        alertDialog1 = dialog.create();
        api = RetrofitInstance.getInstance().create(TicketApi.class);


        // Set dialog title
        alertDialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog1.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        alertDialog1.setCanceledOnTouchOutside(false);
        //  alertDialog1.setCancelable(false);
        final Button btnReserve = (Button) confirmDialog.findViewById(R.id.btn_reserve);
        ImageView img_date = confirmDialog.findViewById(R.id.img_date);
        ImageView img_hour = confirmDialog.findViewById(R.id.img_hour);
        Button btnAnnuler = confirmDialog.findViewById(R.id.annuler);
        TextView Nom = confirmDialog.findViewById(R.id.Nom);
        Nom.setText(user.getNom());
        TextView Prenom = confirmDialog.findViewById(R.id.prenom);
        Prenom.setText(user.getPrenom());
        TextView Tel = confirmDialog.findViewById(R.id.tel);
        Tel.setText(user.getTel());
        TextView Username = confirmDialog.findViewById(R.id.username);
        Username.setText(user.getUsername());
        TextView nom_e = confirmDialog.findViewById(R.id.nom_entreprise);
        nom_e.setText(entreprise.getNom());
        TextView tel_e = confirmDialog.findViewById(R.id.tel_entreprise);
        tel_e.setText(entreprise.getTel());
        TextView email_e = confirmDialog.findViewById(R.id.email_entreprise);
        email_e.setText(entreprise.getEmail());
        TextView address_e = confirmDialog.findViewById(R.id.adress_entreprise);
        address_e.setText(entreprise.getAdresse());
        final TextView tv_date = confirmDialog.findViewById(R.id.date);
        final TextView tv_heure = confirmDialog.findViewById(R.id.heure);


        final Date date = new Date();
        SimpleDateFormat date_formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat time_formatter = new SimpleDateFormat("hh:mm", Locale.getDefault());

        final String strDate = date_formatter.format(date);
        final String strTime = time_formatter.format(date);
        tv_date.setText(strDate);
        tv_heure.setText(strTime);
        final Calendar mcurrentDate = Calendar.getInstance();
        mYear = mcurrentDate.get(Calendar.YEAR);
        mMonth = mcurrentDate.get(Calendar.MONTH);
        mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
        img_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        tv_date.setText( selectedyear + "-" + selectedmonth + "-" + selectedday  );
                        mcurrentDate.set(selectedyear, selectedmonth, selectedday);
                        mMonth = selectedmonth - 1;
                        mDay = selectedday;
                        mYear = selectedyear;
                    }
                }, mYear, mMonth, mDay);

                mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                mDatePicker.show();

            }
        });
        img_hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mCurrentTime = Calendar.getInstance();
                int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mCurrentTime.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tv_heure.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, true);
                timePickerDialog.show();
            }
        });


        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnReserve.setEnabled(false);
                Call<RegisterResponse> call = api.reserverTicket(user.getId(), entreprise.getId(),
                        tv_date.getText().toString(), tv_heure.getText().toString());
                call.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        btnReserve.setEnabled(true);
                        if (response.code() == 200) {
                            Toast.makeText(mContext, "Ticket reservé avec succès", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(mContext, "erreur", Toast.LENGTH_SHORT).show();

                    }


                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        btnReserve.setEnabled(true);
                        Toast.makeText(mContext, "erreur", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        btnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1.dismiss();
            }
        });

    }

    public void show() {
        alertDialog1.show();
    }
}