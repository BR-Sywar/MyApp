package com.example.ui.Maps;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.myapp.R;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MapsService extends AppCompatActivity implements OnMapReadyCallback ,
        GoogleMap.OnMapLongClickListener{

    private GoogleMap mMap;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private SearchView searchView;

    @SuppressLint("MissingPermission")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_maps_service);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        searchView = findViewById(R.id.sv_location);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("location", location.toString());

            }


        };


        if (Build.VERSION.SDK_INT < 23) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    0, 0, (android.location.LocationListener) locationListener);
        } else {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        0, 0, (android.location.LocationListener) locationListener);
            }
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,

                        0, 0, (android.location.LocationListener) locationListener);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    0, 0, (android.location.LocationListener) locationListener);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLongClickListener((GoogleMap.OnMapLongClickListener) this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {


            @Override
            public void onLocationChanged(Location location) {
                Log.d("location", location.toString());
                String myFullAdress = "";


                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                try {
                    List<Address> addressList = geocoder.getFromLocation(location.getLatitude()
                            , location.getLongitude(), 1);


                    if (addressList != null && addressList.size() > 0) {
                        Log.d("Address", addressList.get(0).toString());

                        if (addressList.get(0).getCountryName() != null) {
                            myFullAdress += addressList.get(0).getCountryName() + "";
                        }

                        if (addressList.get(0).getAddressLine(0) != null) {
                            myFullAdress += addressList.get(0).getAddressLine(0) + "";
                        }

                        if (addressList.get(0).getSubAdminArea() != null) {
                            myFullAdress += addressList.get(0).getSubAdminArea() + "";
                        }

                        Toast.makeText(MapsService.this, myFullAdress, Toast.LENGTH_SHORT).show();

                    } else {
                        Log.d("Address", "Address not found");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mMap.clear();

                LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(sydney).title(myFullAdress));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


            }


        };
    }


    @Override
    public void onMapLongClick(LatLng latLng) {
        mMap.addMarker(new MarkerOptions().position(latLng).title(latLng.toString())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}