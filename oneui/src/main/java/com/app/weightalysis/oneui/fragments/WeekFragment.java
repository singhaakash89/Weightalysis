package com.app.weightalysis.oneui.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.weightalysis.oneui.R;
import com.app.weightalysis.oneui.toast_manager.ToastManager;

/**
 * Created by Aakash Singh on 24-05-2016.
 */
public class WeekFragment extends Fragment {
    private final String TAG = DayFragment.class.getSimpleName();
    private ToastManager toastManager;
    private Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        toastManager = ToastManager.getInstance();
        return inflater.inflate(R.layout.fragment_week, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}



