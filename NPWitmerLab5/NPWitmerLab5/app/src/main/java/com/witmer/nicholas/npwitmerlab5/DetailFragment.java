package com.witmer.nicholas.npwitmerlab5;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        Bundle b = getArguments();
        if(b != null);
        {
            String time = b.getString("time");
            TextView text = (TextView)v.findViewById(R.id.details);
            text.setText(time);
        }
        return v;
    }

    public void updateText(String update)
    {
        View v = getView();
        TextView text = (TextView)v.findViewById(R.id.details);
        text.setText(update);
    }


}
