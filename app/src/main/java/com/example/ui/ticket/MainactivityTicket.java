package com.example.ui.ticket;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.R.style.Theme_Holo_Light_Dialog_MinWidth;

public abstract class MainactivityTicket extends AppCompatActivity  implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    ViewPager ViewPager;
    Dialog myDialog;
    EditText txdate,txtime;
    Button rdv,reserver, annuler,valider;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    private int year, month, day, hour, minute;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDialog=new Dialog(this);

        //La Bouton Reserver de popup

        rdv =(Button) findViewById(R.id.Rdv);
        rdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent rdv =  new Intent (MainactivityTicket.this, MainReserver.class);

                startActivity(rdv);
            }
        });



        //Date et Time
        txdate= (EditText) findViewById(R.id.date);
        txtime = (EditText) findViewById(R.id.time);
        reserver = (Button) findViewById(R.id.btn_reserve);


        txdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainactivityTicket.this,
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
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                txtime.setText(date);
            }
        };

        txtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR);
                int minute = c.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(MainactivityTicket.this,
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

                Log.d(TAG, "onTimeSet: " +hourOfDay+" " +minute);

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
        //btn annuler


        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rest= getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                rest.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(rest);
            }
        });
        //btn reserver
        reserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }




    public void ShowPopup(View v){
        TextView txtclose;
        myDialog.setContentView(R.layout.custompopup);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        Button btnMes;
        Button btnrdv,btnPos,btnban,btnpos,btnsante,btnsocieaux;
        btnMes =(Button) myDialog.findViewById(R.id.Mes);
        btnrdv =(Button) myDialog.findViewById(R.id.Rdv);
        btnpos =(Button) myDialog.findViewById(R.id.pluspos);
        btnban = (Button) myDialog.findViewById(R.id.plusban);
        btnsante =(Button) myDialog.findViewById(R.id.plussante);
        btnsocieaux=(Button) myDialog.findViewById(R.id.plussocieaux);


        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss ();
            }
        }) ;
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }




    class AuthenticationPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragmentList = new ArrayList<>();

        public AuthenticationPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override

        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

       /* void addFragmet(LoginFragment fragment) {
            fragmentList.add(fragment);
        }


        public void addFragmet(RegisterFragment registerFragment) {
        }

        */
    }
}