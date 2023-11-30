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
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planmyday.R;
import com.example.planmyday.home.HomepageActivity;
import com.example.planmyday.home.MainActivity;
import com.example.planmyday.models.Attraction;
import com.example.planmyday.models.SavedPlan;
import com.example.planmyday.models.TourPlan;
import com.example.planmyday.models.TourStop;
import com.example.planmyday.models.UserAccount;
import com.example.planmyday.planning.LATour;
import com.example.planmyday.planning.LocationsActivity;
import com.example.planmyday.planning.MyAdapter;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.android.data.geojson.GeoJsonPoint;
import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.TravelMode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class ItineraryActivity extends AppCompatActivity implements OnMapReadyCallback {

    public GoogleMap map;
    //FusedLocationProviderClient mFusionLocationProviderClient;
    LatLngBounds mMapBoundary;
    Location userLocation;
    TextView tt;
    ArrayList<Attraction> attractions;
    AppCompatButton home;
    ListView itineraryList;

    TextView back;
    TextView front;
    TextView day;
    TextView estimated;
    Button dir;
    Button saveButton;
    public ArrayList<TourPlan> tourPlans;
    GeoApiContext mGeoApiContext;
    public int currDay = 0;
    double bounds;
    FirebaseAuth mAuth;
    UserAccount userAccount;
    String uid;
    String type;
    //DatabaseReference dbRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://planmyday-16506-default-rtdb.firebaseio.com/");
    TravelMode travelMode;
    SwitchMaterial toggle;
    private static Context context;

    private FusedLocationProviderClient fusedLocationClient;

    //TODO: check for all permissions
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);
        ItineraryActivity.context = this;

        back = findViewById(R.id.back);
        front = findViewById(R.id.forward);
        day = findViewById(R.id.day);
        dir = findViewById(R.id.redirect);
//        saveButton = findViewById(R.id.saveButton);
        toggle = findViewById(R.id.toggleTransit);

        //GET INTENTS
        Intent intent = getIntent();
        Bundle args2 = intent.getBundleExtra("BUNDLE2");
        attractions = (ArrayList<Attraction>) args2.getSerializable("ARRAYLIST2");
        for (int i = 0; i < attractions.size(); i++) {
            Log.d("SA2", attractions.get(i).getName());
        }
        //Set TourOptimizer
        int numDays = intent.getIntExtra("Days", -1);
        ArrayList<Attraction> attractionsCopy = new ArrayList<>(attractions);
        Log.d("DaysIntent", String.valueOf(numDays));
        tourPlans = TourOptimizer.optimizeTour(attractionsCopy, numDays); // TODO PASS THE CORRECT PARAMETER
        if (tourPlans == null) {
            Toast.makeText(this, "Itinerary is not feasible, please try again", Toast.LENGTH_SHORT).show();
            goHome();
            return;
        }

        String type = intent.getStringExtra(Intent.EXTRA_TEXT);
        tt = findViewById(R.id.itinerary);
        if (type.equals("usc")) {
            tt.setText("USC Itinerary");
            travelMode = TravelMode.WALKING;
            this.type = "usc";
            bounds = 0.0075;
            toggle.setVisibility(View.GONE);
        } else if (type.equals("la")) {
            tt.setText("LA Itinerary");
            travelMode = TravelMode.DRIVING;
            this.type = "la";
            bounds = 0.15;
        }

        estimated = findViewById(R.id.estimatedRouteTime);
        Log.d("hello", TourOptimizer.calculateTotalTime(tourPlans.get(currDay)) + "");
        estimated.setText("estimated route time: " + TourOptimizer.calculateTotalTime(tourPlans.get(currDay)) + "" + " min");


        //VIEWS AND ONCLICKLISTENERS
        home = findViewById(R.id.homeButton);

        home.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                goHome();
            }
        });


        itineraryList = findViewById(R.id.itineraryList);

        if (tourPlans.size() == 1) {
            front.setText("");
        }

        //set onClickListeners
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (currDay > 0) {

                    if (currDay < tourPlans.size()) {
                        front.setText("→");
                    }
                    updateDay(-1);
                    updateStops();
                    day.setText("Day " + (currDay + 1));
                    estimated.setText("estimated route time: " + TourOptimizer.calculateTotalTime(tourPlans.get(currDay)) + "" + " min");

                    if (currDay == 0) {
                        back.setText("");
                    }
                }

                goToAdapter();
            }
        });

        front.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (currDay < tourPlans.size() - 1) {
                    updateDay(1);
                    updateStops();
                    day.setText("Day " + (currDay + 1));
                    estimated.setText("estimated route time: " + TourOptimizer.calculateTotalTime(tourPlans.get(currDay)) + "" + " min");
                }
                if (currDay > 0) {
                    back.setText("←");
                }

                if (currDay == tourPlans.size() - 1) {
                    front.setText("");
                }

                goToAdapter();
            }
        });

        dir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toGoogleMaps(tourPlans.get(currDay));
            }
        });

        toggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // The switch is checked
                travelMode = TravelMode.TRANSIT;
                updateStops();
            } else {
                // The switch isn't checked.
                travelMode = TravelMode.DRIVING;
                updateStops();
            }
        });

        goToAdapter();

//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                saveToDB();
//            }
//        });

        if (mGeoApiContext == null) {
            mGeoApiContext = new GeoApiContext.Builder()
                    .apiKey(getString(R.string.maps_key))
                    .build();
        }

        //mAuth = FirebaseAuth.getInstance();
        //getLastKnownLocation();
        //saveToDB();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    //save TourPlan to user's file
    public void saveToDB() {
        //DatabaseReference userRef = dbRef.child("users").child(uid);
        //DatabaseReference toursRef = userRef.child("tours");
        //String newPlanKey = toursRef.push().getKey();
        //Log.d("FIREBASE", newPlanKey);
        //SavedPlan sp = new SavedPlan(attractions, tourPlans.size(), "Jan 31, 2003");

        //toursRef.child(newPlanKey.toString()).setValue(sp);
        Log.d("FIREBASE", "saved to account");
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        uid = currentUser.getUid();
//        Log.d("FIREBASE", uid);
//        Log.d("FIREBASE", currentUser.getDisplayName());
//        if(currentUser == null){
//            //send to mainActivity
//            startActivity(new Intent(this, MainActivity.class));
//        }
//
//    }

    private void setCameraView(double lat, double lon, double bounds) {
        //Overall map view window: 0.2 * 0.2 = 0.04
        double bottomBoundary = lat - bounds;
        double leftBoundary = lon - bounds;
        double topBoundary = lat + bounds;
        double rightBoundary = lon + bounds;
        mMapBoundary = new LatLngBounds(
                new LatLng(bottomBoundary, leftBoundary),
                new LatLng(topBoundary, rightBoundary)
        );

        map.moveCamera(CameraUpdateFactory.newLatLngBounds(mMapBoundary, 0));
        //map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(userLocation.getLatitude(), userLocation.getLongitude())));
    }

    public void updateDay(int diff) {
        currDay += diff;
    }

    public void updateStops() {
        //reset the map markers
        map.clear();
        //set the new stops
        ArrayList<TourStop> stops = tourPlans.get(currDay).getStops();
        Attraction lastAttraction = null;
        int num = 1;
        for (TourStop stop : stops) {
            Attraction currAttraction = stop.getAttraction();
            LatLng curr = new LatLng(currAttraction.getLatitude(), currAttraction.getLongitude());
            Marker marker = map.addMarker(new MarkerOptions().position(curr).title(num + ": " + currAttraction.getName()));
            //create path between curr and last one
            if (num == 2) {
                calculateDirections(lastAttraction.getLatitude(), lastAttraction.getLongitude(),
                        currAttraction.getLatitude(), currAttraction.getLongitude(), true, travelMode);
            } else if (num > 2) {
                calculateDirections(lastAttraction.getLatitude(), lastAttraction.getLongitude(),
                        currAttraction.getLatitude(), currAttraction.getLongitude(), false, travelMode);
            }
            lastAttraction = currAttraction;
            num++;
        }
        //create lines
    }

    public void goHome() {
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
        map.clear();
        updateStops();
        setCameraView(34.0224, 118.2851, bounds);
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(34.0224, -118.2851)));
    }

    //must give credit to coding w mitch
    public void calculateDirections(double oLat, double oLng, double dLat, double dLng, boolean first, TravelMode travelMode) {
        String TAG = "Dir";
        Log.d(TAG, "calculateDirections: calculating directions.");

        com.google.maps.model.LatLng destination = new com.google.maps.model.LatLng(
                dLat,
                dLng
        );
        DirectionsApiRequest directions = new DirectionsApiRequest(mGeoApiContext);

        //directions.alternatives(true);
        directions.origin(
                new com.google.maps.model.LatLng(
                        oLat,
                        oLng
                )
        );
        Log.d("TOURTYPE", type);
        if (type != null && type.equals("usc")) {
            directions.mode(TravelMode.WALKING);
        } else {
            directions.mode(travelMode);
        }

        Log.d(TAG, "calculateDirections: destination: " + destination.toString());
        directions.destination(destination).setCallback(new PendingResult.Callback<DirectionsResult>() {
            @Override
            public void onResult(DirectionsResult result) {
                Log.d(TAG, "calculateDirections: routes: " + result.routes[0].toString());
                Log.d(TAG, "calculateDirections: duration: " + result.routes[0].legs[0].duration);
                Log.d(TAG, "calculateDirections: distance: " + result.routes[0].legs[0].distance);
                Log.d(TAG, "calculateDirections: geocodedWayPoints: " + result.geocodedWaypoints[0].toString());
                addPolylinesToMap(result, first);
            }

            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG, "calculateDirections: Failed to get directions: " + e.getMessage());

            }
        });
    }

    private void addPolylinesToMap(final DirectionsResult result, boolean first) {
        Context context = this;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                String TAG = "POLYLINE";
                Log.d(TAG, "run: result routes: " + result.routes.length);
                for (DirectionsRoute route : result.routes) {
                    Log.d(TAG, "run: leg: " + route.legs[0].toString());
                    List<com.google.maps.model.LatLng> decodedPath = PolylineEncoding.decode(route.overviewPolyline.getEncodedPath());
                    List<LatLng> newDecodedPath = new ArrayList<>();
// This loops through all the LatLng coordinates of ONE polyline.
                    for (com.google.maps.model.LatLng latLng : decodedPath) {
//                        Log.d(TAG, "run: latlng: " + latLng.toString());
                        newDecodedPath.add(new LatLng(
                                latLng.lat,
                                latLng.lng
                        ));
                    }
                    Polyline polyline = map.addPolyline(new PolylineOptions().addAll(newDecodedPath));
                    polyline.setColor(ContextCompat.getColor(context, R.color.primary));
                    if (first) polyline.setColor(ContextCompat.getColor(context, R.color.primary1));
                    polyline.setClickable(true);
                }
            }
        });
    }

    public void toGoogleMaps(TourPlan tp) {
        ArrayList<TourStop> stops = tp.getStops();
        //stops.get(currDay).getAttraction().getLatitude();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Open itinerary in Google Maps?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        String origin = "34.0224,-118.2851";
                        //last location
                        //String destination = "34.136555,-118.294197";
                        Attraction lastDest = stops.get(stops.size() - 1).getAttraction();
                        String dest = lastDest.getLatitude() + "," + lastDest.getLongitude();
                        //intermediary
                        Log.d("SS1", String.valueOf(stops.size()));
                        String waypts = "";
                        for (int i = 0; i < stops.size() - 1; i++) {
                            waypts += "|" + stops.get(i).getAttraction().getLatitude() + "," + stops.get(i).getAttraction().getLongitude();
                        }
                        //String query = "origin=my location"+ "&destination=" + dest + "&waypoints=" + waypts;
                        String query = "origin=" + origin + "&destination=" + dest + "&waypoints=" + waypts;

                        String latitude = String.valueOf(stops.get(0).getAttraction().getLatitude());
                        String longitude = String.valueOf(stops.get(0).getAttraction().getLongitude());
                        Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/dir/?api=1&" + query);
                        //Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/dir/?api=1&" + query);

                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");

                        try {
                            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                                startActivity(mapIntent);
                            }
                        } catch (NullPointerException e) {
                            Log.e("MapErr", "onClick: NullPointerException: Couldn't open map." + e.getMessage());
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
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //getLastKnownLocation();
            } else {
                Toast.makeText(this, "Please allow permissions to view map", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void goToAdapter() {
        ArrayList<TourStop> tourStops = tourPlans.get(currDay).getStops();
        TourStop[] stops = new TourStop[tourStops.size()];
        stops = tourStops.toArray(stops);
        ItineraryAdapter itineraryAdapter = new ItineraryAdapter(ItineraryActivity.this, stops);
        itineraryList.setAdapter(itineraryAdapter);
    }

    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                        }
                    }
                });
    }
}