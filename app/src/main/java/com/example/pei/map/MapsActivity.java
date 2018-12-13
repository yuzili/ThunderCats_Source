package com.example.pei.map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
// This is the Map View page activity which include Google Maps implementation, location marker name pinpointing

public class MapsActivity extends AppCompatActivity implements
        OnMapReadyCallback,
        GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;

    // Setting position for each location marker
    private static final LatLng GSU = new LatLng(42.3509182,-71.1111392);
    private static final LatLng CAS = new LatLng(42.3502584,-71.1070807);
    private static final LatLng Questrom = new LatLng(42.349605,-71.1019157);
    private static final LatLng FitRec = new LatLng(42.3518687,-71.1186489);

    private Marker mGSU;
    private Marker mCAS;
    private Marker mQuestrom;
    private Marker mFitRec;

    public static String MarkerName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void LoginPage(View view)
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void viewEvents(View view)
    {
        Intent intent = new Intent(this, viewEventActivity.class);
        startActivity(intent);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override

    // Create location marker name
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng BostonUniversity = new LatLng(42.3504997, -71.1075878);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(BostonUniversity));

        mGSU = mMap.addMarker(new MarkerOptions()
                .position(GSU)
                .title("GSU"));
        mGSU.setTag(0);

        mCAS = mMap.addMarker(new MarkerOptions()
                .position(CAS)
                .title("CAS"));
        mCAS.setTag(0);

        mQuestrom = mMap.addMarker(new MarkerOptions()
                .position(Questrom)
                .title("Questrom"));
        mQuestrom.setTag(0);

        mFitRec = mMap.addMarker(new MarkerOptions()
                .position(FitRec)
                .title("FitRec"));
        mFitRec.setTag(0);

        mMap.setOnInfoWindowClickListener(this);
    }

    // Send location marker name to location event list page
    @Override
    public void onInfoWindowClick(final Marker marker) {

        MarkerName = marker.getTitle();

        Intent intent = new Intent(this, MarkerActivity.class);
        intent.putExtra("PASS_NAME",MarkerName);

        startActivity(intent);


    }

}
