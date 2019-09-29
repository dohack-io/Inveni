package de.inveni.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import de.inveni.LostActivity;
import de.inveni.R;
import de.inveni.SearchActivity;

public class FragmentLost extends Fragment {

    private List<String> values = new ArrayList<>();

    public FragmentLost() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lost, container, false);
    }

    @Override
    public void onViewCreated(View view, final Bundle savedInstanceState) {
        final ListView listView = view.findViewById(R.id.listView2);

        values.add("Bombe");
        values.add("Grüner Spargel");

        final FragmentLost.CustomAdapter customAdapter = new FragmentLost.CustomAdapter();
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("LOST-CLICKED:" + position);
                gotoLost(customAdapter.getItem(position));
            }
        });

        FloatingActionButton floatingActionButton = view.findViewById(R.id.fabian);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
                getActivity().overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            }
        });

    }

    private void gotoLost(String title) {
        Intent intent = new Intent(getActivity(), LostActivity.class);
        Bundle b = new Bundle();
        b.putString("title", title);
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
        public String getItem(int pos) {
            return values.get(pos);
        }

        @Override
        public long getItemId(int pos) {
            return 0;
        }

        @Override
        public View getView(final int pos, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.listview_item_lost, null);

            ImageView imageView = view.findViewById(R.id.imageView);

            ImageButton imageButton = view.findViewById(R.id.imageButton);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Snackbar.make(FragmentLost.this.getView(), "Removed: " + values.get(pos), Snackbar.LENGTH_LONG).show();

                    values.remove(pos);
                    CustomAdapter.this.notifyDataSetChanged();
                }
            });

            final TextView textView = view.findViewById(R.id.textView);
            textView.setSelected(true);
            textView.setText(values.get(pos));

            return view;
        }

    }

}
