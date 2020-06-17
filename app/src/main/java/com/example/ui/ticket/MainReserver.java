package com.example.ui.ticket;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.myapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

import static android.R.style.Theme_Holo_Light_Dialog_MinWidth;

public class MainReserver extends AppCompatActivity  {


    EditText txdate,txtime;
    Button rdv,reserver, annuler,valider;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    private int year, month, day, hour, minute;
    private String date,time;
    private static final String TAG = "Reserver";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);

        //Date et Time
        txdate= findViewById(R.id.date);
        txtime =  findViewById(R.id.time);
        annuler =  findViewById(R.id.annuler);
        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rest= getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                rest.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(rest);
            }
        });

        Button Reserver = findViewById(R.id.btn_reserve);
        Reserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(String.valueOf(MainTicketReserver.class));
                startActivity(intent);
            }
        });

        txdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainReserver.this,
                        Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month = month + 1;
                Log.d(TAG, "onDateSet mm/dd/yyyy" + month + "/" + day + "/" + year);
                String date =month+ "/" + day + "/" + year ;

                txdate.setText(date);
            }
        };

        txtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR);
                int minute = c.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(MainReserver.this,
                        Theme_Holo_Light_Dialog_MinWidth,
                        mTimeSetListener ,
                        hour,minute,true);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {



                Log.d(TAG , "onTimeSet: " +hourOfDay+" " +minute);

                String time = ""+hourOfDay+":"+minute;


                try {
                    //24 hours time format will convert to 12 hours format
                    SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
                    SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
                    Date _24HourDt = _24HourSDF.parse(time);
                    time = _12HourSDF.format(_24HourDt);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                txtime.setText(time);


            }
        };



    }
}
