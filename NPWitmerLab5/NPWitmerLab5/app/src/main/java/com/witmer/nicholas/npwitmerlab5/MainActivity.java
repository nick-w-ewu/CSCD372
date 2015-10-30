package com.witmer.nicholas.npwitmerlab5;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MainFragment.UpdateListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.portHolder, new MainFragment())
                    .commit();
        }
    }

    private boolean landscapeOrientation()
    {
        return getResources().getBoolean(R.bool.dual_pane);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Toast.makeText(getApplicationContext(), "Android Lab 5, Nicholas Witmer", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(String data)
    {
        DetailFragment toUpdate;
        if(landscapeOrientation())
        {
            toUpdate = (DetailFragment) getFragmentManager().findFragmentById(R.id.detailHolder);
            toUpdate.updateText(data);
        }
        else
        {
            toUpdate = (DetailFragment)getFragmentManager().findFragmentByTag("DETAIL_FRAGMENT");
            if(toUpdate != null)
            {
                Log.d("Good", "Details fragment found");
                toUpdate.updateText(data);
            }
            else
            {
                Log.d("Bad", "No Details Fragment found, creating new");
                toUpdate = new DetailFragment();
                Bundle b = new Bundle();
                b.putString("time", data);
                toUpdate.setArguments(b);
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.portHolder, toUpdate, "DETAIL_FRAGMENT");
                trans.commit();
            }
        }
    }
}
