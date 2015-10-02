package com.witmer.nicholas.nwitmerlab1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SurveyActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        TextView greeting = (TextView)findViewById(R.id.greeting);
        Intent incoming = getIntent();
        String name = incoming.getStringExtra("Name");
        greeting.setText("Hello " + name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_survey, menu);
        return true;
    }

    public void onSubmit(View v)
    {
        EditText ageField = (EditText)findViewById(R.id.age);
        String contents = ageField.getText().toString();
        if(contents.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Enter your age", Toast.LENGTH_SHORT).show();
        }
        else
        {
            try
            {
                int age = Integer.parseInt(contents);
                Intent returnIntent = new Intent();
                returnIntent.putExtra("Age", age);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
            catch(Exception e)
            {
                Toast.makeText(getApplicationContext(), "Please enter a number for your age", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
