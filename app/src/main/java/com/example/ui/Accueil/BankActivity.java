package com.example.ui.Accueil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.myapp.R;
import com.example.ui.Maps.Maps;
import com.example.ui.ui.SpinnerInteractionListener;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class BankActivity extends Fragment  {

    private boolean spin ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_bank, container, false);
        spin = false ;
        CardView cardView = root.findViewById(R.id.cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Maps.class);
                startActivity(intent);
            }
        });

        Spinner spinner = (Spinner) root.findViewById(R.id.spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.Services, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
       spinner.setSelected(false);
        spinner.setSelection(3);
        SpinnerInteractionListener listener =   new SpinnerInteractionListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                switch (pos) {
                    case 0 :
                        Navigation.findNavController(getView()).navigate(R.id.nav_sante); break;
                    case 3 : break ;
                    case 2 :
                        Navigation.findNavController(getView()).navigate(R.id.nav_post); break;
                    case 1 :
                        Navigation.findNavController(getView()).navigate(R.id.nav_public); break;

                }
            }
        };
        spinner.setOnTouchListener(listener);
        spinner.setOnItemSelectedListener(listener);

        return root ;

    }




}
