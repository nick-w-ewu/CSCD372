package nicholas.witmer.nwitmerlab3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener
{
    DrawerLayout navDrawer;
    ListView musicals;
    DrawerItem[] images;
    ActionBarDrawerToggle drawerToggle;
    final String title = "NPWitmerLab3";
    final String drawerTitle = "Select a Page";
    ImageView mainImage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);

        navDrawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        musicals = (ListView)findViewById(R.id.left_drawer);
        musicals.setOnItemClickListener(this);
        mainImage = (ImageView)findViewById(R.id.mainImage);
        images = new DrawerItem[6];
        images[0] = new DrawerItem(R.drawable.marypoppins, "Mary Poppins");
        images[1] = new DrawerItem(R.drawable.birdie, "Bye Bye Birdie");
        images[2] = new DrawerItem(R.drawable.fiddler, "Fiddler on the Roof");
        images[3] = new DrawerItem(R.drawable.seussical, "Seusical the Musical");
        images[4] = new DrawerItem(R.drawable.musicman, "The Music Man");
        images[5] = new DrawerItem(R.drawable.oz, "The Wizard of Oz");
        if(savedInstanceState != null)
        {
            int position = savedInstanceState.getInt("imgId");
            mainImage.setImageResource(images[position].getId());
            mainImage.setTag(position);
        }
        else
        {
            mainImage.setTag(0);
        }

        SelectionAdapter options = new SelectionAdapter(this, R.layout.nav_list_row, images);
        musicals.setAdapter(options);

        drawerToggle = new ActionBarDrawerToggle(this, navDrawer, toolbar, R.string.drawer_open, R.string.drawer_close)
        {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(title);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(drawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        navDrawer.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        boolean open = navDrawer.isDrawerOpen(musicals);
        menu.findItem(R.id.action_settings).setVisible(!open);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        int imgId = (int)mainImage.getTag();
        outState.putInt("imgId", imgId);
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
            Toast.makeText(getApplicationContext(), "Android Lab 3, Nicholas Witmer", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id)
    {
        mainImage.setImageResource(images[position].getId());
        mainImage.setTag(position);
    }
}
