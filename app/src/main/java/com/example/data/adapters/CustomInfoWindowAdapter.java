package com.example.data.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.data.models.Entreprise;
import com.example.myapp.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final View mWindow;
    private Context mContext;

    public CustomInfoWindowAdapter(Context context) {
        mContext = context;
        mWindow = LayoutInflater.from(context).inflate(R.layout.custom_info_window, null);
    }

    private void rendowWindowText(Marker marker, View view){
        TextView tvAddress = (TextView) view.findViewById(R.id.address);
        TextView tvName = (TextView) view.findViewById(R.id.name);
        TextView tvPhone = (TextView) view.findViewById(R.id.phone);
        TextView tvCodePostal = (TextView) view.findViewById(R.id.code_postal);
        Entreprise entreprise =(Entreprise) marker.getTag() ;
        if (entreprise != null) {
            tvAddress.setText(entreprise.getAdresse());
            tvName.setText(entreprise.getNom());
            tvPhone.setText(entreprise.getTel());
            tvCodePostal.setText(String.valueOf(entreprise.getCodePostal()));
        }






    }

    @Override
    public View getInfoWindow(Marker marker) {
        rendowWindowText(marker, mWindow);
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        rendowWindowText(marker, mWindow);
        return mWindow;
    }
}
