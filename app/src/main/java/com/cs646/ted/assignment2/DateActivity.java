package com.cs646.ted.assignment2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Date;


public class DateActivity extends ActionBarActivity {

    public final static String DATE_FILE_NAME = "datesavefile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        final DatePicker datePicker = (DatePicker) findViewById(R.id.date_activity_date_picker);

        File dateFile = new File(getFilesDir(), DATE_FILE_NAME);
        try {
            if (dateFile.exists()) {
                FileInputStream fis = new FileInputStream(dateFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                int[] savedDate = (int[])ois.readObject();

                datePicker.updateDate(savedDate[2], savedDate[1], savedDate[0]);
                Toast.makeText(getApplicationContext(),
                        "Saved date found",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else{
                datePicker.updateDate(2015, 0, 1);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        final Button setButton = (Button) findViewById(R.id.date_set_button);
        setButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                alertDialogBuilder.setView(findViewById(R.id.prompt_layout));
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int id) {
                                           int[] saveDate = {datePicker.getDayOfMonth(),
                                                datePicker.getMonth(),
                                                datePicker.getYear()};

                                        try {
                                            FileOutputStream fos = openFileOutput(DATE_FILE_NAME,
                                                    Context.MODE_PRIVATE);
                                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                                            oos.writeObject(saveDate);
                                            oos.close();
                                            fos.close();
                                            Toast.makeText(getApplicationContext(),
                                                    "Date saved",
                                                    Toast.LENGTH_SHORT)
                                                    .show();
                                        } catch (FileNotFoundException e) {
                                            e.printStackTrace();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        d.dismiss();
                                    }
                                })
                        .setNegativeButton("No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface d, int id) {
                                        d.cancel();
                                    }
                                })
                        .setMessage("Save date?");

                AlertDialog altertDialog = alertDialogBuilder.create();
                alertDialogBuilder.show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_date, menu);
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

    public static  byte[] intToByteArray(int myInteger){
        return ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(myInteger).array();
    }

    public static int byteArrayToInt(byte [] byteBarray){
        return ByteBuffer.wrap(byteBarray).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }
}
