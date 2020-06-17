package com.example.ui.MesReservation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapp.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ReservationFragment extends Fragment {


    @Override

    public View onCreateView (@NonNull LayoutInflater inflater,
                              ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_reservation, container, false);

        return root;

    }}