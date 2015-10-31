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
public class DetailFragment extends Fragment
{
    private String timestamp;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        Bundle b = getArguments();
        TextView text = (TextView) v.findViewById(R.id.details);
        String time;
        if(timestamp != null)
        {
            text.setText(timestamp);
        }
        else
        {
            try
            {
                time = b.getString("time");
                text.setText(time);
            }
            catch (NullPointerException e)
            {
                time = "";
            }
            text.setText(time);
        }
        return v;
    }

    public void updateText(String update)
    {
        this.timestamp = update;
        View v = getView();
        if(v != null)
        {
            TextView text = (TextView) v.findViewById(R.id.details);
            text.setText(update);
        }
    }


}
