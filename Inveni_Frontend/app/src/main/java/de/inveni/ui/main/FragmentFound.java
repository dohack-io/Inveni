package de.inveni.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import de.inveni.CreateActivity;
import de.inveni.FoundActivity;
import de.inveni.R;
import de.inveni.network.Property;

public class FragmentFound extends Fragment {

    public static List<Property> values = new ArrayList<>();

    public FragmentFound() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_found, container, false);
    }

    @Override
    public void onViewCreated(View view, final Bundle savedInstanceState) {
        final ListView listView = view.findViewById(R.id.listView);

        final CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("LOST-CLICKED:" + position);
                gotoFound(customAdapter.getItem(position));
            }
        });

        FloatingActionButton floatingActionButton = view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CreateActivity.class));
                getActivity().overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            }
        });
    }

    private void gotoFound(Property property) {
        Intent intent = new Intent(getActivity(), FoundActivity.class);

        Bundle b = new Bundle();
        b.putString("title", property.getTitle());
        b.putDouble("lat", property.getLatitude());
        b.putDouble("lon", property.getLongitude());
        b.putString("desc", property.getDescription());
        b.putLong("date", property.getDate());
        intent.putExtras(b);

        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return values.size();
        }

        @Override
        public Property getItem(int pos) {
            return values.get(pos);
        }

        @Override
        public long getItemId(int pos) {
            return 0;
        }

        @Override
        public View getView(final int pos, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.listview_item_found, null);

            ImageView imageView = view.findViewById(R.id.imageView);

            final TextView textView = view.findViewById(R.id.textView);
            textView.setSelected(true);
            textView.setText(values.get(pos).getTitle());

            return view;
        }

    }

}
