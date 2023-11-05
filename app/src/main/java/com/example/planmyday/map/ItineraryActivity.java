package com.example.planmyday.map;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planmyday.R;
import com.example.planmyday.home.HomepageActivity;
import com.example.planmyday.models.Attraction;
import com.example.planmyday.models.TourPlan;
import com.example.planmyday.models.TourStop;
import com.example.planmyday.models.UserAccount;
import com.example.planmyday.planning.LATour;
import com.example.planmyday.planning.USCTour;
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
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.android.data.geojson.GeoJsonPoint;
import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class ItineraryActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map;
    //FusedLocationProviderClient mFusionLocationProviderClient;
    LatLngBounds mMapBoundary;
    Location userLocation;

    TextView tt;

    ArrayList<Attraction> attractions;
    AppCompatButton home;

    TextView back;
    TextView front;
    TextView day;
    Button dir;

    ArrayList<TourPlan> tourPlans;
    GeoApiContext mGeoApiContext;
    int currDay = 0;


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
        ArrayList<Attraction> attractionsCopy = new ArrayList<>(attractions);
        tourPlans = TourOptimizer.optimizeTour(attractionsCopy);

        String type = intent.getStringExtra(Intent.EXTRA_TEXT);
        tt = findViewById(R.id.itinerary);

        if (type.equals("usc")){
            tt.setText("USC Itinerary");
        }
        else if (type.equals("la")){
            tt.setText("LA Itinerary");
        }


        home = findViewById(R.id.homeButton);

        home.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
               goHome();
            }
        });

        back = findViewById(R.id.back);
        front = findViewById(R.id.forward);
        day = findViewById(R.id.day);
        dir = findViewById(R.id.redirect);

        if(tourPlans.size() == 1){
            front.setText("");
        }

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(currDay > 0) {

                    if(currDay < tourPlans.size()){
                        front.setText("→");
                    }
                    currDay--;
                    updateStops();
                    day.setText("Day " + (currDay+1));

                    if(currDay == 0){
                        back.setText("");
                    }
                }
            }
        });

        front.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(currDay < tourPlans.size() - 1) {
                    currDay++;
                    updateStops();
                    day.setText("Day "+(currDay+1));
                }
                if(currDay > 0){
                    back.setText("←");
                }

                if(currDay == tourPlans.size() - 1){
                    front.setText("");
                }
            }
        });

        dir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toGoogleMaps(tourPlans.get(currDay));
            }
        });
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


    public void updateDay(int diff){
        currDay += diff;
    }

    public void updateStops(){
        //reset the map markers
        map.clear();
        //set the new stops
        ArrayList<TourStop> stops = tourPlans.get(currDay).getStops();
        for (TourStop stop : stops){
            Attraction currAttraction = stop.getAttraction();
            LatLng curr = new LatLng(currAttraction.getLatitude(), currAttraction.getLongitude());
            map.addMarker(new MarkerOptions().position(curr).title(currAttraction.getName()));
        }
        //create lines
    }

    public void goHome(){
        Intent intentHome = new Intent(ItineraryActivity.this, HomepageActivity.class);
        startActivity(intentHome);
        finish();
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//            != PackageManager.PERMISSION_GRANTED
//            && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
//            != PackageManager.PERMISSION_GRANTED) return;

        //Log.d("AtLat", String.valueOf(attractions.get(0).getLatitude()));
        map = googleMap;
        ArrayList<TourStop> stops = tourPlans.get(currDay).getStops();
        for (TourStop stop : stops){
            Attraction currAttraction = stop.getAttraction();
            LatLng curr = new LatLng(currAttraction.getLatitude(), currAttraction.getLongitude());
            map.addMarker(new MarkerOptions().position(curr).title(currAttraction.getName()));
        }
//        if (mGeoApiContext == null){
//            mGeoApiContext = new GeoApiContext.Builder()
//                    .apiKey("")
//                    .build();
//        }
        setCameraView(34.0224, 118.2851);
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(34.0224, -118.2851)));
    }

//    private void calculateDirections(LatLng ll){
//        String TAG = "Dir";
//        Log.d(TAG, "calculateDirections: calculating directions.");
//
//        com.google.maps.model.LatLng destination = new com.google.maps.model.LatLng(
//                marker.getPosition().latitude,
//                marker.getPosition().longitude
//        );
//        DirectionsApiRequest directions = new DirectionsApiRequest(mGeoApiContext);
//
//        directions.alternatives(true);
//        directions.origin(
//                new com.google.maps.model.LatLng(
//                        mUserPosition.getGeo_point().getLatitude(),
//                        mUserPosition.getGeo_point().getLongitude()
//                )
//        );
//        Log.d(TAG, "calculateDirections: destination: " + destination.toString());
//        directions.destination(destination).setCallback(new PendingResult.Callback<DirectionsResult>() {
//            @Override
//            public void onResult(DirectionsResult result) {
//                Log.d(TAG, "calculateDirections: routes: " + result.routes[0].toString());
//                Log.d(TAG, "calculateDirections: duration: " + result.routes[0].legs[0].duration);
//                Log.d(TAG, "calculateDirections: distance: " + result.routes[0].legs[0].distance);
//                Log.d(TAG, "calculateDirections: geocodedWayPoints: " + result.geocodedWaypoints[0].toString());
//            }
//
//            @Override
//            public void onFailure(Throwable e) {
//                Log.e(TAG, "calculateDirections: Failed to get directions: " + e.getMessage() );
//
//            }
//        });
//    }


    public void toGoogleMaps(TourPlan tp){
        ArrayList<TourStop> stops = tp.getStops();
        stops.get(currDay).getAttraction().getLatitude();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Where would you like to see the your route??")
                .setCancelable(true)
                .setPositiveButton("Google Maps", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        String origin = "34.0224,-118.2851";
                        //last location
                        //String destination = "34.136555,-118.294197";
                        Attraction lastDest = stops.get(stops.size()-1).getAttraction();
                        String dest = lastDest.getLatitude() + "," + lastDest.getLongitude();
                        //intermediary
                        Log.d("SS1", String.valueOf(stops.size()));
                        String waypts = "";
                        for (int i = 0; i < stops.size() - 1; i++) {
                            waypts += "|" + stops.get(i).getAttraction().getLatitude() + "," + stops.get(i).getAttraction().getLongitude();
                        }
                        //String query = "origin=my location"+ "&destination=" + dest + "&waypoints=" + waypts;
                        String query = "origin="+ origin + "&destination=" + dest + "&waypoints=" + waypts;

                        String latitude = String.valueOf(stops.get(0).getAttraction().getLatitude());
                        String longitude = String.valueOf(stops.get(0).getAttraction().getLongitude());
                        Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/dir/?api=1&" + query);
                        //Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/dir/?api=1&" + query);

                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");

                        try{
                            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                                startActivity(mapIntent);
                            }
                        }catch (NullPointerException e){
                            Log.e("MapErr", "onClick: NullPointerException: Couldn't open map." + e.getMessage() );
                            //Toast.makeText("Please allow permissions to view map", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
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