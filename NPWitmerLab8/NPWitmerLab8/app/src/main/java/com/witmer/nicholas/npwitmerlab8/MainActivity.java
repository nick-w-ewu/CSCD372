package com.witmer.nicholas.npwitmerlab8;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.dropbox.chooser.android.DbxChooser;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements ExpandableListView.OnChildClickListener
{
    ArrayList<String> manufacturers;
    ArrayList<ArrayList<String>> models;
    MyListAdapter list;
    ExpandableListView listView;
    DbxChooser chooser;
    final int DBX_CHOOSER_REQUEST = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ExpandableListView) findViewById(R.id.expList);
        listView.setOnChildClickListener(this);

        this.chooser = new DbxChooser("oihqqqetn07tulk");

        if(savedInstanceState == null)
        {
            this.manufacturers = new ArrayList<String>();
            this.models = new ArrayList<ArrayList<String>>();
        }
        else
        {
            this.manufacturers = (ArrayList<String>)savedInstanceState.getSerializable("makes");
            this.models = (ArrayList<ArrayList<String>>)savedInstanceState.getSerializable("models");
            list = new MyListAdapter(this, manufacturers, models);
            listView.setAdapter(list);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putSerializable("makes", manufacturers);
        outState.putSerializable("models", models);
    }

    private boolean parseFile(InputStream fileIn)
    {
        AssetManager assets = getResources().getAssets();
        try
        {
            Scanner fin = new Scanner(fileIn);
            String temp = "";
            String[] items;
            ArrayList<String> tempModels;
            int i;

            while(fin.hasNext())
            {
                tempModels = new ArrayList<>();
                temp = fin.nextLine();
                items = temp.split(",");
                manufacturers.add(items[0]);
                for(i = 1; i < items.length; i++)
                {
                    tempModels.add(items[i]);
                }
                models.add(tempModels);
            }
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DBX_CHOOSER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                DbxChooser.Result result = new DbxChooser.Result(data);
                Uri uri = result.getLink();
                ContentResolver cr = getContentResolver();
                InputStream is;
                try {
                    is = cr.openInputStream(uri);
                    if (!parseFile(is))
                    {
                        Toast.makeText(getApplicationContext(), "Failed to parse file", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        list = new MyListAdapter(this, manufacturers, models);
                        listView.setAdapter(list);
                    }

                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Failed to parse file", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

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
        if (id == R.id.action_dropbox)
        {
            chooser.forResultType(DbxChooser.ResultType.FILE_CONTENT)
                    .launch(MainActivity.this, DBX_CHOOSER_REQUEST);
            return true;
        }
        if (id == R.id.action_about) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this) ;
            builder.setTitle("About")
                    .setMessage("Nicholas Witmer, CSCD 372, Fall 2015, Lab 8")
                    .setNeutralButton("OK", null)
                    .setCancelable(false)
                    .create()
                    .show();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onChildClick (ExpandableListView parent, View v,int groupPosition,
                                 int childPosition, long id)
    {
        String selection = models.get(groupPosition).get(childPosition);
        Toast.makeText(getApplicationContext(), selection, Toast.LENGTH_SHORT).show();
        return true;
    }
}

