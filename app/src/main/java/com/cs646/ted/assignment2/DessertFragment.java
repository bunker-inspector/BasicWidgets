package com.cs646.ted.assignment2;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DessertFragment extends ListFragment{

    OnSelectionChangeListener mCallback;
    public static int mSelected = -1;
    private View mListView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        String[] desserts = getResources().getStringArray(R.array.dessert_array);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                desserts);
        setListAdapter(adapter);

        if(getActivity().getIntent().hasExtra("listactivityextra")) {
            int mSelected = getActivity()
                    .getIntent()
                    .getExtras()
                    .getInt("listactivityextra");
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnSelectionChangeListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " implement OnSelectionChangeFragment interface");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        l.getChildAt(position).setBackgroundColor(Color.CYAN);

        if(mSelected > -1 && position != mSelected){
            l.getChildAt(mSelected).setBackgroundColor(Color.TRANSPARENT);
        }

        mSelected = position;
    }

    public void selectionChange(){
        mCallback.onSelectionChange();
    }

    public void sendSelection(){
        mCallback.onSendSelection();
    }

    public interface OnSelectionChangeListener {
        public void onSelectionChange();
        public void onSendSelection();
    }
}