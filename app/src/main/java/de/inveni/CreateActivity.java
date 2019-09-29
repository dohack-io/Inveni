package de.inveni;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import de.inveni.network.Property;
import de.inveni.network.RequestManager;
import de.inveni.ui.main.FragmentFound;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_found);

        Button button_gps = findViewById(R.id.button_gps);
        button_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(CreateActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CreateActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            10);
                }

                LocationManager lm = (LocationManager) CreateActivity.this.getSystemService(Context.LOCATION_SERVICE);

                try {
                    Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    TextView textView = findViewById(R.id.textView__lat);
                    textView.setText(location.getLatitude() + "");

                    TextView textView2 = findViewById(R.id.textView__lon);
                    textView2.setText(location.getLongitude() + "");

                } catch (SecurityException e) {
                    e.printStackTrace();
                }
            }
        });

        TextView textViewTitle = findViewById(R.id.textView__title);
        TextView textViewDesc = findViewById(R.id.textView__desc);
        TextView textViewLat = findViewById(R.id.textView__lat);
        TextView textViewLon = findViewById(R.id.textView__lon);

        Button button_create = findViewById(R.id.button_create);
        button_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestManager.
                FragmentFound.values.add(new Property());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_empty, menu);
        return true;
    }

}
