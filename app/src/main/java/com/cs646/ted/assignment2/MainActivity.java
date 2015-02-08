package com.cs646.ted.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends ActionBarActivity implements DessertFragment.OnSelectionChangeListener{

    public static final int     DATE_ACTIVITY       = 0,
                                KEYBOARD_ACTIVITY   = 1,
                                LIST_ACTIVITY       = 2;

    public static final String  PACKAGE_NAME        = "com.cs646.ted.assignment2",
                                DATE_ACTIVITY_NAME  = "com.cs646.ted.assignment2.DateActivity",
                                KEYB_ACTIVITY_NAME  = "com.cs646.ted.assignment2.KeyboardActivity",
                                DESS_ACTIVITY_NAME  = "com.cs646.ted.assignment2.DessertActivity";

    public static final String  EXTRA_SELECTED_POSITION = "listactivityextra",
                                EXTRA_MAIN_EDITTEXT     = "mainexittext";


    public static int mListItemSelected = -1;

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
                int code = -1;
                switch(((Spinner) findViewById(R.id.main_spinner)).getSelectedItemPosition()){
                    case(DATE_ACTIVITY):
                        code = DATE_ACTIVITY;
                        go.setClassName(PACKAGE_NAME, DATE_ACTIVITY_NAME);
                        break;
                    case(KEYBOARD_ACTIVITY):
                        code = KEYBOARD_ACTIVITY;
                        go.setClassName(PACKAGE_NAME, KEYB_ACTIVITY_NAME);
                        String toSend = ((EditText)findViewById(R.id.main_editText))
                                .getText()
                                .toString();
                        go.putExtra(EXTRA_MAIN_EDITTEXT, toSend);
                        break;
                    case(LIST_ACTIVITY):
                        code = LIST_ACTIVITY;
                        onSelectionChange();
                        go.putExtra(EXTRA_SELECTED_POSITION, mListItemSelected);
                        go.setClassName(PACKAGE_NAME, DESS_ACTIVITY_NAME);
                        break;
                }
                startActivityForResult(go, code);
            }
        });

        EditText mainEditText = (EditText) findViewById(R.id.main_editText);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != LIST_ACTIVITY){
                return;
        }
        else {
            switch (resultCode) {
                case RESULT_OK:
                    mListItemSelected = data.getIntExtra(EXTRA_SELECTED_POSITION, -1);
                    onSetSelection(mListItemSelected);
                    break;
                case RESULT_CANCELED:
                    break;
            }
        }
    }

    @Override
    public void onSelectionChange() {
        DessertFragment dessertFragment = (DessertFragment) getFragmentManager()
                .findFragmentById(R.id.main_activity_list_fragment);

        mListItemSelected = dessertFragment.getSelected();
    }

    @Override
    public void onSetSelection(int position) {
        DessertFragment dessertFragment = (DessertFragment) getFragmentManager()
                .findFragmentById(R.id.main_activity_list_fragment);

        dessertFragment.select(position);
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
