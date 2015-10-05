package com.witmer.nicholas.nwitmerlab1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends TracerActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent incoming = getIntent();
        if(incoming != null)
        {
            String action = incoming.getAction();
            String type = incoming.getType();

            if(action.equals(Intent.ACTION_SEND) && type.equals("text/plain"))
            {
                TextView greeting = (TextView)findViewById(R.id.result);
                String sent = incoming.getStringExtra(Intent.EXTRA_TEXT);
                greeting.setText(sent);
            }
        }
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
        if(id == R.id.action_about)
        {
            Toast.makeText(getApplicationContext(), "Android Lab 1, Nicholas Witmer", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void surveyClick(View v)
    {
        EditText editField = (EditText)findViewById(R.id.editText);
        String name = editField.getText().toString();
        if(name.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Enter a name", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent survey = new Intent(this, SurveyActivity.class);
            survey.putExtra("Name", name);
            startActivityForResult(survey, 2);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2 && resultCode == Activity.RESULT_OK)
        {
            TextView greeting = (TextView)findViewById(R.id.result);
            int age = data.getIntExtra("Age", 1);
            if(age < 40)
            {
                greeting.setText("You're under 40, so you're trustworthy");
            }
            else
            {
                greeting.setText("You're NOT under 40, so you're  NOT trustworthy");
            }
        }
    }

    public void websiteClick(View v)
    {
        Uri site = Uri.parse("https://sites.google.com/site/pschimpf99/");
        Intent gotoWebsite = new Intent(Intent.ACTION_VIEW, site);
        startActivity(gotoWebsite);
    }
}
