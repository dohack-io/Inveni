package de.inveni;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import de.inveni.network.Country;
import de.inveni.network.Property;
import de.inveni.network.RequestManager;
import de.inveni.network.User;
import de.inveni.ui.main.FragmentLost;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_lost);

        final TextView tags = findViewById(R.id.textview_search);
        final TextView lat = findViewById(R.id.textview_search_lat);
        final TextView lon = findViewById(R.id.textview_search_lon);
        final TextView rad = findViewById(R.id.textview_search_rad);
        final TextView date = findViewById(R.id.textview_search_date);

        Button searchButton = findViewById(R.id.button_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestManager.setCurrentUser(new User(2, "nocon", "noc",
                        "Flughafenstraße", "104d", "44309", "",
                        "", new Country(1, "Deutschland"), new ArrayList<Property>()));

                List<Property> results = RequestManager.queryProperties(201909200000L, 201909290000L,
                        tags.getText().toString(), Double.valueOf(lat.getText().toString()),
                        Double.valueOf(lon.getText().toString()), Double.valueOf(rad.getText().toString()));

                FragmentLost.values.addAll(results);

                startActivity(new Intent(SearchActivity.this, MainActivity.class));
                SearchActivity.this.overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_empty, menu);
        return true;
    }

}
