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

import de.inveni.FoundActivity;
import de.inveni.R;

public class FragmentFound extends Fragment {

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
    }

    private void gotoFound(String title) {
        Intent intent = new Intent(getActivity(), FoundActivity.class);
        Bundle b = new Bundle();
        b.putString("title", title);
        intent.putExtras(b);

        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
    }

    class CustomAdapter extends BaseAdapter {

        private String[] titles = new String[getCount()];

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public String getItem(int pos) {
            return titles[pos];
        }

        @Override
        public long getItemId(int pos) {
            return 0;
        }

        @Override
        public View getView(final int pos, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.listview_item, null);

            ImageView imageView = view.findViewById(R.id.imageView);

            ImageButton imageButton = view.findViewById(R.id.imageButton);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("FOUND-OPTIONS:" + pos);
                }
            });

            final TextView textView = view.findViewById(R.id.textView);
            textView.setSelected(true);
            textView.setText("Braunes Portmonee");
            titles[pos] = textView.getText().toString();

            return view;
        }

    }

}
