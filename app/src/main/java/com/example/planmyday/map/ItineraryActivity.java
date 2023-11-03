package com.example.planmyday.map;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.planmyday.R;
import com.example.planmyday.models.Attraction;
import com.example.planmyday.models.UserAccount;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.GeoApiContext;
import com.google.maps.android.data.geojson.GeoJsonPoint;

import java.util.ArrayList;


public class ItineraryActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map;
    //FusedLocationProviderClient mFusionLocationProviderClient;
    LatLngBounds mMapBoundary;
    Location userLocation;

    ArrayList<Attraction> attractions;

    //TODO: check for all permissions
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);

        Intent intent = getIntent();
        Bundle args2 = intent.getBundleExtra("BUNDLE2");
        attractions = (ArrayList<Attraction>) args2.getSerializable("ARRAYLIST2");
        for (int i = 0; i < attractions.size(); i++){
            Log.d("SA2", attractions.get(i).getName());
        }

        //getLastKnownLocation();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    private void setCameraView(double lat, double lon) {
        //Overall map view window: 0.2 * 0.2 = 0.04
        double bottomBoundary = lat - 0.1;
        double leftBoundary = lon - 0.1;
        double topBoundary = lat + 0.1;
        double rightBoundary = lon + 0.1;
        mMapBoundary = new LatLngBounds(
                new LatLng(bottomBoundary, leftBoundary),
                new LatLng(topBoundary, rightBoundary)
        );

        map.moveCamera(CameraUpdateFactory.newLatLngBounds(mMapBoundary, 0));
        //map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(userLocation.getLatitude(), userLocation.getLongitude())));
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//            != PackageManager.PERMISSION_GRANTED
//            && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
//            != PackageManager.PERMISSION_GRANTED) return;

        //Log.d("AtLat", String.valueOf(attractions.get(0).getLatitude()));
        map = googleMap;
        for (int i = 0; i < attractions.size(); i++){
            LatLng curr = new LatLng(attractions.get(i).getLatitude(), attractions.get(i).getLongitude());
            map.addMarker(new MarkerOptions().position(curr).title(attractions.get(i).getName()));
        }
        setCameraView(34.0224, 118.2851);
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(34.0224, -118.2851)));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //getLastKnownLocation();
            }
            else {
                Toast.makeText(this, "Please allow permissions to view map", Toast.LENGTH_SHORT).show();
            }
        }
    }
}