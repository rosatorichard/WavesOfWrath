package com.batchmates.android.wavesofwrath.view.secondactivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.service.quicksettings.TileService;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.batchmates.android.wavesofwrath.R;
import com.batchmates.android.wavesofwrath.injection.secondactivity.DaggerSecondActivityComponent;
import com.batchmates.android.wavesofwrath.model.PlaceInformation;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, SecondActivityContract.View, android.location.LocationListener {

    private static final String TAG = "Map Activity";
    @Inject
    SecondActivityPresenter presenter;
    private GoogleMap mMap;
    private Location currentLocation;
    private FusedLocationProviderClient fusedClient;
    private Bitmap smallMarker;
    private Marker myself;
    private LatLng myselfLatLgn;
    private List<PlaceInformation> currentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        setUpDagger();
        presenter.addView(this);

        LocationTracker();


    }

    private void createMyMarker() {
        int height = 300;
        int width = 300;
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.pirate_ship);
        Bitmap b = bitmapdraw.getBitmap();
        smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        LatLng position = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        myself = mMap.addMarker(new MarkerOptions().position(position)
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                .title(getIntent().getStringExtra("shipName"))
                .snippet(getIntent().getStringExtra("captainName")));

    }

    private void LocationTracker() {
        fusedClient = LocationServices.getFusedLocationProviderClient(this);
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
        fusedClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                currentLocation = location;
                LatLng firstLatLng = new LatLng(location.getLatitude(), location.getLongitude());

                //animate the camera to my position
                CameraUpdate cam = CameraUpdateFactory.newLatLngZoom(firstLatLng, 15);
                mMap.animateCamera(cam);
                //create marker
                createMyMarker();
                presenter.placesCloseby(firstLatLng);
            }
        });

        LocationManager locationManger = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //should check for wheather netword or GPS is available

        if (LocationManager.GPS_PROVIDER != null) {
            locationManger.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, this);
        } else {
            if (LocationManager.NETWORK_PROVIDER != null) {
                locationManger.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 0, this);
            } else {
                LocationTracker();
            }
        }
        Log.d(TAG, "LocationTracker: ");
    }

    private void setUpDagger() {
        DaggerSecondActivityComponent.create().inject(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(this, "You need GPS For this Silly", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json));
        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                Toast.makeText(MapsActivity.this, "This Be ye next Target?", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onMarkerClick: " + marker.getTitle());
                Log.d(TAG, "onMarkerClick: " + marker.getSnippet());
                return false;
            }
        });
        
        
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                PlaceInformation placeInformation= (PlaceInformation) marker.getTag();
                if (currentLocation.getLatitude()==placeInformation.getLocation().latitude 
                        && currentLocation.getLongitude()==placeInformation.getLocation().longitude)
                {
                    Log.d(TAG, "onInfoWindowClick: enter battle");
                }
//                marker.remove();
            }
        });
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onLocationChanged(Location location) {


        currentLocation = location;
        myselfLatLgn = new LatLng(location.getLatitude(), location.getLongitude());
        myself.setPosition(myselfLatLgn);

        //updates the map as you move
        presenter.placesCloseby(myselfLatLgn);
        Log.d(TAG, "onLocationChanged: Location Changed");
        //how i will set up encounters
        //presenter.checkEncounter(myself);

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

        Log.d(TAG, "onStatusChanged: " + s);
    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void returnedPlaces(List<PlaceInformation> placeInformations) {

        Log.d(TAG, "returnedPlaces: replacing markers");
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.treasure_chest);
        presenter.setMarkersOnMap(mMap, placeInformations, bitmapdraw);
//        createMyMarker();
    }
}
