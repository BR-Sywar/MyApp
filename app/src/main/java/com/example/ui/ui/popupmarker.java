package com.example.ui.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;

import static android.R.layout.select_dialog_item;

public class popupmarker extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final String[] option = {"Add", "View", "Select", "Delete"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), select_dialog_item, option);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Select option");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        final AlertDialog a = builder.create();
        return null;
    }
}
