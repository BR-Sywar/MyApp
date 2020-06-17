package com.example.ui.Accueil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapp.R;

import androidx.fragment.app.Fragment;

public class ActualitesDetailFragment {


    public class ActualitesFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View root = inflater.inflate(R.layout.fragment_actualitesdetailler, container, false);
            return root;
        }
    }
}
