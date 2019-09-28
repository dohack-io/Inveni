package de.inveni.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import de.inveni.R;

public class FragmentFound extends Fragment {

    public FragmentFound() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView listView = (ListView) findViewById(R.id.listView);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_found, container, false);
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Object getItem(int pos) {
            return null;
        }

        @Override
        public long getItemId(int pos) {
            return 0;
        }

        @Override
        public View getView(int pos, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.listview_item, null);

            ImageView imageView = view.findViewById(R.id.imageView3);
            TextView textView1 = view.findViewById(R.id.textView2);
            TextView textView2 = view.findViewById(R.id.textView3);

            textView1.setText("mIOJN");
            textView2.setText("mI23OJN");

            return view;
        }

    }

}
