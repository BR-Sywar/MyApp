package com.example.ui.Maps;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.MainActivity;
import com.example.data.adapters.CustomInfoWindowAdapter;
import com.example.data.adapters.EntrepriseMapAdapter;
import com.example.data.models.Category;
import com.example.data.models.Entreprise;
import com.example.myapp.R;
import com.example.network.RetrofitInstance;
import com.example.network.api.EntrepriseApi;
import com.example.network.response.EntrepriseResponse;
import com.example.utils.OnRecyclerViewItemClickListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Maps extends AppCompatActivity implements OnMapReadyCallback {


    private static final String TAG = "MapActivity";
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 12f;
    private SearchView searchView;
    private Boolean mLocationPermissionsGranted = false;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Entreprise entreprise ;
    private Category category ;
    private RecyclerView recyclerView ;
    private EntrepriseMapAdapter entrepriseMapAdapter ;
    private EntrepriseApi api ;
    List<Entreprise> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        entreprise = getIntent().getParcelableExtra("entre") ;
        category = getIntent().getParcelableExtra("cat") ;
        searchView = findViewById(R.id.sv_location);
        api = RetrofitInstance.getInstance().create(EntrepriseApi.class);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
              /*  String location=searchView.getQuery().toString();
                List<Address>addressList = null ;

                if (location!= null || !location.equals("")){
                    Geocoder geocoder= new Geocoder(Maps.this );
                    try {
                        addressList= geocoder.getFromLocationName(location,1);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Address address= addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(),address.getLatitude());



                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                }*/

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                entrepriseMapAdapter.getFilter().filter(newText);
                return true;
            }
        });
        recyclerView = findViewById(R.id.entre_recycler);
        getLocationPermission();
        getEntreprises();

    }
    private void getEntreprises() {
        if (category != null) {
            searchView.setVisibility(View.VISIBLE);
            entrepriseMapAdapter = new EntrepriseMapAdapter(this);
            recyclerView.setVisibility(View.VISIBLE);
            final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
            final SnapHelper snapHelper = new PagerSnapHelper() ;
            recyclerView.setLayoutManager(mLayoutManager);
            //snapHelper.
            snapHelper.attachToRecyclerView(recyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(entrepriseMapAdapter);
            Call<EntrepriseResponse> call = api.getEntreprises(category.getId());
            call.enqueue(new Callback<EntrepriseResponse>() {
                @Override
                public void onResponse(Call<EntrepriseResponse> call, Response<EntrepriseResponse> response) {
                    EntrepriseResponse entrepriseResponse = response.body();
                    if (entrepriseResponse != null && entrepriseResponse.getEntreprises() != null) {

                        list = entrepriseResponse.getEntreprises();
                        entrepriseMapAdapter.setEntrepriseList(list);
                        for (Entreprise entreprise:
                                list) {
                            LatLng HotelJulius = new LatLng(entreprise.getLat(), entreprise.getLng());

                            Marker marker =    mMap.addMarker(new MarkerOptions().position(HotelJulius).title(entreprise.getNom())
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker)));
                            marker.setTag(entreprise);
                        }
                    }
                }

                @Override
                public void onFailure(Call<EntrepriseResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                }
            });
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    View centerView = snapHelper.findSnapView(mLayoutManager);
                    int position = mLayoutManager.getPosition(centerView);
                    Entreprise entreprise = list.get(position);
                    LatLng latLng = new LatLng(entreprise.getLat(),entreprise.getLng()) ;
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng ,17));
                }
            });

        entrepriseMapAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onRecyclerViewItemClicked(int position, int id) {
                Entreprise entreprise1 = list.get(position);
                Intent intent = new Intent(Maps.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("entre",entreprise1);
                intent.putExtras(bundle) ;
                startActivity(intent);
            }
        });


        }
    }



    private void getDeviceLocation(){
        Log.d(TAG, "getDeviceLocation: getting the devices current location");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try{
            if(mLocationPermissionsGranted){

                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "onComplete: found location!");
                            Location currentLocation = (Location) task.getResult();

                      /*      moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                                    DEFAULT_ZOOM,
                                    "My Location");*/

                        }else{
                            Log.d(TAG, "onComplete: current location is null");
                            Toast.makeText(Maps.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }catch (SecurityException e){
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage() );
        }
    }

  /*  private void moveCamera(LatLng latLng, float zoom, String title){
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        if(!title.equals("My Location")){
            MarkerOptions options = new MarkerOptions()
                    .position(latLng)
                    .title(title);
            mMap.addMarker(options);
        }

        hideSoftKeyboard();
    }*/

    private void initMap(){
        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(Maps.this);
    }

    private void getLocationPermission(){
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
                initMap();
            }else{
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: called.");
        mLocationPermissionsGranted = false;

        switch(requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if(grantResults.length > 0){
                    for(int i = 0; i < grantResults.length; i++){
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionsGranted = false;
                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    mLocationPermissionsGranted = true;
                    //initialize our map
                    initMap();
                }
            }
        }
    }

    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
       // Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onMapReady: map is ready");
        mMap = googleMap;
          if (entreprise!=null)
        { LatLng HotelJulius = new LatLng(entreprise.getLat(), entreprise.getLng());

    Marker marker =    mMap.addMarker(new MarkerOptions().position(HotelJulius).title(entreprise.getNom())
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker)));
    marker.setTag(entreprise);
        }
        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(getApplicationContext()));
      //  mMap.setMaxZoomPreference(15f);
      //  mMap.setMinZoomPreference(13f);
      mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(Maps.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("entre",entreprise);
                intent.putExtras(bundle) ;
                startActivity(intent);
            }
        });
      //  LatLng clubksareljem = new LatLng(35.335905, 10.687385);
      //  mMap.addMarker(new MarkerOptions().position(clubksareljem).title("Cnam"));




        if (mLocationPermissionsGranted) {
          //  getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);


        }


    }


}