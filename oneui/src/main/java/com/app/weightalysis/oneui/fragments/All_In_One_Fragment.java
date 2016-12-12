package com.app.weightalysis.oneui.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.app.graph.GraphContract;
import com.app.graph.StandardGraphContract;
import com.app.weightalysis.oneui.R;
import com.app.weightalysis.oneui.activities.BaseActivity;
import com.app.weightalysis.oneui.custom_spinner.StandardSpinnerAdapter;
import com.app.weightalysis.oneui.toast_manager.ToastManager;
import com.jjoe64.graphview.GraphView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Aakash Singh on 12-12-2016.
 */

public class All_In_One_Fragment extends Fragment {
    private final String TAG = DayFragment.class.getSimpleName();
    private ToastManager toastManager;
    private Intent intent;
    private String month, week, year;
    private Spinner dailySpinner;
    private Spinner weeklySpinner;
    private Spinner monthlySpinner;
    private GraphContract graphContract;
    private GraphView graphView;
    private SpinnerModel spinnerModel;
    private List<String> arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        toastManager = ToastManager.getInstance();
        graphContract = new StandardGraphContract(BaseActivity.getContext());
        return inflater.inflate(R.layout.fragment_all_in_one, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dailySpinner = (Spinner) getActivity().findViewById(R.id.dailySpinner);
        weeklySpinner = (Spinner) getActivity().findViewById(R.id.weeklySpinner);
        monthlySpinner = (Spinner) getActivity().findViewById(R.id.monthlySpinner);

        spinnerModel = new SpinnerModel();
        for (int i = 0; i < spinnerModel.getStringArray().length; i++) {
            arrayList = Arrays.asList(getResources().getStringArray(spinnerModel.getStringArrayAtIndex(i)));
            StandardSpinnerAdapter.getInstance().setAdapter(spinnerModel.getSpinner(i), arrayList);
        }

        dailySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                month = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        weeklySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                week = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        monthlySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                year = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    class SpinnerModel {
        private int[] stringArray = {R.array.daily_spinner_entries, R.array.weekly_spinner_entries, R.array.monthly_spinner_entries};
        private Spinner[] spinner = {dailySpinner, weeklySpinner, monthlySpinner};

        public int getStringArrayAtIndex(int index) {
            return stringArray[index];
        }

        public Spinner getSpinner(int index) {
            return spinner[index];
        }

        public int[] getStringArray() {
            return stringArray;
        }
    }
}