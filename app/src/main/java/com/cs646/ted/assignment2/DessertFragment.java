package com.cs646.ted.assignment2;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class DessertFragment extends ListFragment {
    public static final String EXTRA_SELECTED_POSITION = "listactivityextra";
    OnSelectionChangeListener mCallback;

    private int mSelected = ListView.INVALID_POSITION;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Get the optional selected index from the fragment arguments
        if(getActivity().getIntent().hasExtra(EXTRA_SELECTED_POSITION)) {
            mSelected = getActivity()
                    .getIntent()
                    .getExtras()
                    .getInt(EXTRA_SELECTED_POSITION);
        }

        String[] desserts = getResources().getStringArray(R.array.dessert_array);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_activated_1,
                desserts);
        setListAdapter(adapter);

        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        if (mSelected != ListView.INVALID_POSITION) {
            getListView().setItemChecked(mSelected, true);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        getListView().setItemChecked(position, true);

        mSelected = position;
    }

    public void select(int position){
        getListView().setItemChecked(position, true);
        mSelected = position;
    }

    public int getSelected(){
        return mSelected;
    }

    public void setSelection(int position){
        mCallback.onSetSelection(position);
    }

    public void selectionChange(){
        mCallback.onSelectionChange();
    }

    public interface OnSelectionChangeListener {
        public void onSelectionChange();
        public void onSetSelection(int position);
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
}