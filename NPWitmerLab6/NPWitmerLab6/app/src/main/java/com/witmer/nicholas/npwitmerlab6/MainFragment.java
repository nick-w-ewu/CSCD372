package com.witmer.nicholas.npwitmerlab6;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment
{
    private int[] images = {R.drawable.andoria, R.drawable.bajor, R.drawable.betazed, R.drawable.cardassia,
                            R.drawable.denobula, R.drawable.earth, R.drawable.ferenginar, R.drawable.fluidic,
                            R.drawable.kronos, R.drawable.remus, R.drawable.romulus, R.drawable.suliban,
                            R.drawable.talax, R.drawable.talos};

    public MainFragment()
    {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String name = sharedPref.getString("name", null);
        Boolean isStudent = sharedPref.getBoolean("is_student", true);
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        TextView nameView = (TextView) v.findViewById(R.id.Name);
        TextView greeting = (TextView) v.findViewById(R.id.welcome);
        TextView sugesstion = (TextView) v.findViewById(R.id.suggestion);
        ImageView backround = (ImageView) v.findViewById(R.id.mainImage);
        nameView.setText("Hello " + name);
        sugesstion.setText("May we suggest the following desktop backround for you?");
        if (isStudent) {
            String years = sharedPref.getString("years_remaining", null);
            greeting.setText("We wish you sucess during your " + years + " years at the academy");
            int homeworld = Integer.parseInt(sharedPref.getString("home_world", "0"));
            backround.setImageResource(this.images[homeworld]);
        } else {
            greeting.setText("Welcome back");
            backround.setImageResource(R.drawable.academy);
        }

        return v;
    }
}
