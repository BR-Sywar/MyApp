package com.example.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;

import com.example.myapp.R;
import com.example.ui.Login;

public class CustomLoginDialog {


    private final Context mContext;
    private final AlertDialog alertDialog1;

    public CustomLoginDialog(Context context) {
        this.mContext = context;

        LayoutInflater li = LayoutInflater.from(mContext);
        //Creating a view to get the dialog box
        View confirmDialog = li.inflate(R.layout.dialog_login, null);
        final AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        // Include dialog.xml file
        dialog.setView(confirmDialog);
        alertDialog1 = dialog.create();



        // Set dialog title
        alertDialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog1.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        alertDialog1.setCanceledOnTouchOutside(false);
        alertDialog1.setCancelable(false);
        Button button = (Button) confirmDialog.findViewById(R.id.buttonConfirm);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1.dismiss();
           Intent intent=     new Intent(mContext, Login.class);
        //        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_NO_HISTORY) ;

                mContext.startActivity(intent);
            }
        });

    }

    public void show() {
        alertDialog1.show();
    }
}