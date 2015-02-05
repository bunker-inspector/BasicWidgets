package com.cs646.ted.assignment2;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class DessertFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        String[] desserts = getResources().getStringArray(R.array.dessert_array);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                desserts);
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}