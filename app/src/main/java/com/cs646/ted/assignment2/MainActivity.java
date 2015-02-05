package com.cs646.ted.assignment2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    public static final int     DATE_ACTIVITY       = 0,
                                KEYBOARD_ACTIVITY   = 1,
                                LIST_ACTIVITY       = 2;

    public static final String  PACKAGE_NAME        = "com.cs646.ted.assignment2",
                                DATE_ACTIVITY_NAME  = "com.cs646.ted.assignment2.DateActivity",
                                KEYB_ACTIVITY_NAME  = "com.cs646.ted.assignment2.KeyboardActivity",
                                DESS_ACTIVITY_NAME  = "com.cs646.ted.assignment2.DessertActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.main_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.activity_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final Button goButton = (Button) findViewById(R.id.main_button);
        goButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent go = new Intent();
                switch(((Spinner) findViewById(R.id.main_spinner)).getSelectedItemPosition()){
                    case(DATE_ACTIVITY):
                        go.setClassName(PACKAGE_NAME, DATE_ACTIVITY_NAME);
                        break;
                    case(KEYBOARD_ACTIVITY):
                        go.setClassName(PACKAGE_NAME, KEYB_ACTIVITY_NAME);
                        break;
                    case(LIST_ACTIVITY):
                        go.setClassName(PACKAGE_NAME, DESS_ACTIVITY_NAME);
                        break;
                }
                startActivity(go);
            }
        });

        EditText mainEditText = (EditText) findViewById(R.id.main_editText);
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

        return super.onOptionsItemSelected(item);
    }
}
