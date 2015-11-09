package com.witmer.nicholas.npwitmerlab6;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private static final String SETTINGS = "SETTINGS";
    public static final String FIRST_USE = "FIRST_USE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences prefs = getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
        boolean first = prefs.getBoolean(FIRST_USE, true);
        getFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentHolder, new MainFragment())
                .commit();
        if(first)
        {
            createSettings();
            prefs.edit().putBoolean(FIRST_USE, false).commit();
        }
    }

    private void createSettings()
    {
        FragmentTransaction trans = getFragmentManager().beginTransaction();
        trans.replace(R.id.fragmentHolder, new SettingsFragment(), "SETTINGS");
        trans.addToBackStack(null);
        trans.commit();
    }

    @Override
    public void onBackPressed()
    {
        int count = getFragmentManager().getBackStackEntryCount();
        if (count > 0)
        {
            getFragmentManager().popBackStack();
        }
        else
        {
            super.onBackPressed();
        }
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
        if (id == R.id.action_settings)
        {
            createSettings();
        }
        if (id == R.id.action_about) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this) ;
            builder.setTitle("About")
                    .setMessage("Nicholas Witmer, CSCD 372, Fall 2015, Lab 6")
                    .setNeutralButton("OK", null)
                    .setCancelable(false)
                    .create()
                    .show();
        }

        return super.onOptionsItemSelected(item);
    }
}
