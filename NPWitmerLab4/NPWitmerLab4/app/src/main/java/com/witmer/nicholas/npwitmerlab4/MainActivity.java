package com.witmer.nicholas.npwitmerlab4;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int displayValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(savedInstanceState != null)
        {
            int temp = savedInstanceState.getInt("number");
            this.displayValue = temp;
        }
        else
        {
            this.displayValue = 0;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt("number", displayValue);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void incrementDisplay(View v)
    {
        SevenSegment display = (SevenSegment)findViewById(R.id.display1);
        SevenSegment display2 = (SevenSegment)findViewById(R.id.view);
        SevenSegment display3 = (SevenSegment)findViewById(R.id.view3);
        SevenSegment display4 = (SevenSegment)findViewById(R.id.view2);
        if(displayValue == 10)
        {
            displayValue = 0;
        }
        display.setCurNum(displayValue);
        display2.setCurNum(displayValue+1);
        display3.setCurNum(displayValue+2);
        display4.setCurNum(displayValue+3);
        displayValue++;
        display.invalidate();
        display2.invalidate();
        display3.invalidate();
        display4.invalidate();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Toast.makeText(getApplicationContext(), "Android Lab 4, Nicholas Witmer", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
