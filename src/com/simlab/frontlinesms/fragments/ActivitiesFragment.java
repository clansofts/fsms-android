package com.simlab.frontlinesms.fragments;

import com.actionbarsherlock.app.SherlockFragment;
import com.simlab.frontlinesms.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ActivitiesFragment extends SherlockFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activities, container, false);
    }
}