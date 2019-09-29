package de.inveni;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import de.inveni.network.User;

public class FoundActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    private MapView mapView;
    private double lat, lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_found);
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        String title;
        long date;
        String desc;

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            lat = bundle.getInt("lat");
            lon = bundle.getInt("lon");

            title = bundle.getString("title");
            date = bundle.getLong("date");
            desc = bundle.getString("desc");
        } else {

            title = "-";
            desc = "-";
            date = 201909291253l;

            lat = 51.504350;
            lon = 7.526680;
        }

        TextView textViewTitle = findViewById(R.id.textView_title_found);
        textViewTitle.setText(title);

        TextView textViewDate = findViewById(R.id.textView_date_found);
        textViewDate.setText(parseDate(date));

        TextView textViewDesc = findViewById(R.id.textView_desc_found);
        textViewDesc.setText(desc);

        mapView = findViewById(R.id.mapView_found);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);

        final List<String> list = new ArrayList<>();
        list.add("Mercedes");
        list.add("AMG");
        list.add("Ich bin dabei!");

        ListView scrollView = findViewById(R.id.possible_matches);
        scrollView.setAdapter(new ArrayAdapter<>(FoundActivity.this, android.R.layout.simple_list_item_1, list));
        scrollView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

             //   User user = new User();//TODO GET USER FROM PROPERTY

               // Intent intent = new Intent(FoundActivity.this, ContactActivity.class);
               // intent.putExtra("name", user.getName());
              //  intent.putExtra("given_name", user.getGivenName());
               // intent.putExtra("street", user.getStreet());
               // intent.putExtra("number", user.getHouseNumber());
               // intent.putExtra("plz", user.getPlz());
               // intent.putExtra("email", user.getEmail());
               // intent.putExtra("phone", user.getPhone());
              //  intent.putExtra("country", user.getCountry().getName());

               // startActivity(intent);
                FoundActivity.this.overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
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
        getMenuInflater().inflate(R.menu.menu_found, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item2:
            case R.id.item3:
                //TODO
                return true;
            default:
                return true;
        }
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
        LatLngBounds ADELAIDE = new LatLngBounds(
                new LatLng(lat - 0.01, lon - 0.01), new LatLng(lat + 0.01, lon + 0.01));
        googleMap.setLatLngBoundsForCameraTarget(ADELAIDE);
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(13.5f));

        googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).title("Found here!"));


        LatLng pos = new LatLng(lat, lon);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(pos));
    }

}
