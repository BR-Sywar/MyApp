package com.example.ui.Accueil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapp.R;

import androidx.appcompat.view.menu.ListMenuItemView;
import androidx.fragment.app.Fragment;

public class ActualitesFragment extends Fragment {
    ListMenuItemView myListe;
    String[] myData = {"Acc1", "Acc2", "Acc3", "Acc4"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_actualites, container, false);

        ListView myListe = (ListView) root.findViewById(R.id.myListe);

        ArrayAdapter<String> arrayAdapter
                = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, myData);
        myListe.setAdapter(arrayAdapter);

        return root;
    }
}