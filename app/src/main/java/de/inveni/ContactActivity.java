package de.inveni;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            TextView tv1 = findViewById(R.id.textView7);
            tv1.setText(bundle.getString("name"));

            TextView tv2 = findViewById(R.id.textView74747);
            tv2.setText(bundle.getString("given_name"));

            TextView tv3 = findViewById(R.id.textView67377);
            tv3.setText(bundle.getString("street"));

            TextView tv4 = findViewById(R.id.textView147);
            tv4.setText(bundle.getString("number"));

            TextView tv5 = findViewById(R.id.textView867);
            tv5.setText(bundle.getString("plz"));

            TextView tv6 = findViewById(R.id.textView2357);
            tv6.setText(bundle.getString("email"));

            TextView tv7 = findViewById(R.id.textView667);
            tv7.setText(bundle.getString("phone"));

            TextView tv8 = findViewById(R.id.textView7213);
            tv8.setText(bundle.getString("country"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_empty, menu);
        return true;
    }

}
