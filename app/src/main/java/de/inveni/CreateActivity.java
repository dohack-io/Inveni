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

                    TextView textView = findViewById(R.id.textView1);
                    textView.setText(location.getLatitude() + "");

                    TextView textView2 = findViewById(R.id.textView31);
                    textView2.setText(location.getLongitude() + "");

                } catch (SecurityException e) {
                    e.printStackTrace();
                }
            }
        });

        Button button_create = findViewById(R.id.button_create);
        button_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO CReate new ..
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_empty, menu);
        return true;
    }

}
