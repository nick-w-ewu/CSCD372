package com.witmer.nicholas.npwitmerlab7;

import android.app.AlertDialog;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener
{

    int[] cells = {R.id.cell1, R.id.cell2, R.id.cell3, R.id.cell4, R.id.cell5, R.id.cell6,
            R.id.cell7, R.id.cell8, R.id.cell9};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView cell;
        ImageView player1 = (ImageView)findViewById(R.id.player1);
        ImageView player2 = (ImageView)findViewById(R.id.player2);
        player1.setTag(R.drawable.x);
        player2.setTag(R.drawable.o);
        player1.setOnTouchListener(this);
        player2.setOnTouchListener(this);
        if(savedInstanceState == null)
        {
            for(int i = 0; i < cells.length; i ++)
            {
                cell = (ImageView)findViewById(cells[i]);
                cell.setTag(R.drawable.blank);
                cell.setOnDragListener(this);
            }
        }
        else
        {
            int[] images = savedInstanceState.getIntArray("images");
            for(int i = 0; i < cells.length; i ++)
            {
                cell = (ImageView)findViewById(cells[i]);
                cell.setTag(images[i]);
                cell.setImageResource(images[i]);
                cell.setOnDragListener(this);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle b)
    {
        super.onSaveInstanceState(b);
        int[] images = new int[9];
        for(int i = 0; i < images.length; i++)
        {
            ImageView v = (ImageView)findViewById(this.cells[i]);
            images[i] = (int)v.getTag();
        }
        b.putIntArray("images", images);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
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
                    .setMessage("Nicholas Witmer, CSCD 372, Fall 2015, Lab 7")
                    .setNeutralButton("OK", null)
                    .setCancelable(false)
                    .create()
                    .show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            View.DragShadowBuilder drag = new View.DragShadowBuilder(v);
            v.startDrag(null, drag, v, 0);
            return true;
        }
        return false;
    }

    @Override
    public boolean onDrag(View v, DragEvent event)
    {

        if(event.getAction() == DragEvent.ACTION_DROP)
        {
            if((int)v.getTag() != R.drawable.blank)
            {
                Toast.makeText(this, "Invalid Move", Toast.LENGTH_LONG).show();
                return false;
            }
            ImageView d = (ImageView)event.getLocalState();
            ImageView curCell = (ImageView)v;
            curCell.setImageResource((int)d.getTag());
            curCell.setTag(d.getTag());
            return true;
        }
        return true;
    }
}
