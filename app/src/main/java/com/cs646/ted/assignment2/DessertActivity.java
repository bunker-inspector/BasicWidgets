package com.cs646.ted.assignment2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class DessertActivity extends ActionBarActivity implements DessertFragment.OnSelectionChangeListener{

    public static int mListItemSelected = -1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dessert_list);

        final Button back = (Button) findViewById(R.id.dessert_back_button);
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });

        mListItemSelected = getIntent().getExtras().getInt("listactivityextra");
    }

    @Override
    protected void onStart() {
        if(mListItemSelected > -1){
            onSendSelection();
        }

        super.onStart();
    }

    @Override
    public void onSelectionChange() {
        DessertFragment dessertFragment = (DessertFragment)getFragmentManager()
                .findFragmentById(R.id.dessert_activity_list_fragment);

        mListItemSelected = dessertFragment.mSelected;
    }

    @Override
    public void onSendSelection() {
        DessertFragment dessertFragment = (DessertFragment)getFragmentManager()
                .findFragmentById(R.id.dessert_activity_list_fragment);

        dessertFragment.getListView().getItemAtPosition(mListItemSelected);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_desert_list, menu);
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
