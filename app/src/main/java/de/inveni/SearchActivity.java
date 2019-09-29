package de.inveni;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_lost);

        TextView tags = findViewById(R.id.textview_search);
        TextView lat = findViewById(R.id.textview_search_lat);
        TextView lon = findViewById(R.id.textview_search_lon);
        TextView rad = findViewById(R.id.textview_search_rad);
        TextView date = findViewById(R.id.textview_search_date);

        Button searchButton = findViewById(R.id.button_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //tags+lat+lon+rad+date .getText()
                //TODO CREATE NEW SEARCH
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_empty, menu);
        return true;
    }

}
