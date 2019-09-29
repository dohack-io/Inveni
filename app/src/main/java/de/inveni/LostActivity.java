package de.inveni;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class LostActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    private MapView mapView;
    private double lat, lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lost);
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        String title = "-";
        long date = 0;
        String desc = "-";

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            lat = bundle.getInt("lat");
            lon = bundle.getInt("lon");

            title = bundle.getString("title");
            date = bundle.getLong("date");
            desc = bundle.getString("desc");
        }

        lat = 51.504350;
        lon = 7.526680;

        TextView textViewTitle = findViewById(R.id.textView_title);
        textViewTitle.setText(title);

        TextView textViewDate = findViewById(R.id.textView_date);
        textViewDate.setText(parseDate(date));

        TextView textViewDesc = findViewById(R.id.textView_desc);
        textViewDesc.setText(desc);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);

        Button button = findViewById(R.id.button_finder);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LostActivity.this, ContactActivity.class));
                LostActivity.this.overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            }
        });
    }

    private String parseDate(long l) {
        String date = String.valueOf(l);
        if (date.length() < 12) {
            return parseDate(201909291253L);
        }
        return date.substring(6, 8) + "." + date.substring(4, 6) + "." + date.substring(0, 4) + " | " + date.substring(8, 10) + ":" + date.substring(10) + " Uhr";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lost, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item99:
                return true;
            case R.id.item90:
                startActivity(new Intent(LostActivity.this, ContactActivity.class));
                LostActivity.this.overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap gmap = googleMap;

        LatLngBounds ADELAIDE = new LatLngBounds(
                new LatLng(lat - 0.01, lon - 0.01), new LatLng(lat + 0.01, lon + 0.01));
        gmap.setLatLngBoundsForCameraTarget(ADELAIDE);
        gmap.moveCamera(CameraUpdateFactory.zoomTo(13.5f));

        gmap.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).title("Found here!"));


        LatLng pos = new LatLng(lat, lon);
        gmap.moveCamera(CameraUpdateFactory.newLatLng(pos));
    }

}
