package com.app.weightalysis.oneui.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.app.graph.GraphContract;
import com.app.graph.StandardGraphContract;
import com.app.weightalysis.oneui.R;
import com.app.weightalysis.oneui.activities.BaseActivity;
import com.app.weightalysis.oneui.toast_manager.ToastManager;
import com.jjoe64.graphview.GraphView;

/**
 * Created by Aakash Singh on 24-05-2016.
 */
public class WeekFragment extends Fragment {
    private final String TAG = WeekFragment.class.getSimpleName();
    private ToastManager toastManager;
    private Intent intent;
    private String month, year;
    private Spinner monthSpinner;
    private Spinner yearSpinner;
    private Button applyButton;
    private GraphContract graphContract;
    private GraphView graphView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        toastManager = ToastManager.getInstance();
        graphContract = new StandardGraphContract(BaseActivity.getContext());
        return inflater.inflate(R.layout.fragment_week, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        monthSpinner = (Spinner) getActivity().findViewById(R.id.monthSpinner);
        yearSpinner = (Spinner) getActivity().findViewById(R.id.yearSpinner);
        applyButton = (Button) getActivity().findViewById(R.id.applyButton);

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                month = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                year = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!month.equalsIgnoreCase("Month") && !year.equalsIgnoreCase("Year")) {
                    graphView = (GraphView) getActivity().findViewById(R.id.graph);
                    graphView.setVisibility(View.INVISIBLE);
                    graphContract.drawGraphByWeek(graphView, month, year);
                } else {
                    toastManager.showSimpleToastShort("Please select filter");
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}



