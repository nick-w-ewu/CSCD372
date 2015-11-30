package com.witmer.nicholas.ashman;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, UpdateGameStats, View.OnLongClickListener
{

    Maze maze;
    TextView cakeCount;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView up = (ImageView)findViewById(R.id.up);
        ImageView down = (ImageView)findViewById(R.id.down);
        ImageView left = (ImageView)findViewById(R.id.left);
        ImageView right = (ImageView)findViewById(R.id.right);
        TextView instructions = (TextView)findViewById(R.id.instructions);
        instructions.setOnLongClickListener(this);
        maze = (Maze)findViewById(R.id.mazeView);
        cakeCount = (TextView)findViewById(R.id.cakeCount);
        up.setTag(1);
        down.setTag(2);
        right.setTag(3);
        left.setTag(4);
        up.setOnClickListener(this);
        down.setOnClickListener(this);
        left.setOnClickListener(this);
        right.setOnClickListener(this);
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
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_about) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this) ;
            builder.setTitle("About")
                    .setMessage("Nicholas Witmer, CSCD 372, Fall 2015, Final Project AshMan")
                    .setNeutralButton("OK", null)
                    .setCancelable(false)
                    .create()
                    .show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {
        int direction = (int)v.getTag();
        this.maze.userMove(direction);
    }

    @Override
    public void updateCakeCount(int cakes)
    {
        cakeCount.setText(String.valueOf(cakes));
    }

    @Override
    public boolean onLongClick(View v)
    {
        this.maze.cheat();
        return true;
    }
}
