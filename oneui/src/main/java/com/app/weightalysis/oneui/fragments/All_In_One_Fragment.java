package com.app.weightalysis.oneui.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.graph.GraphContract;
import com.app.graph.StandardGraphContract;
import com.app.weightalysis.logger.Logger;
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
    private String month, year;
    private Spinner dailySpinner;
    private Spinner weeklySpinner;
    private Spinner monthlySpinner;
    private GraphContract graphContract;
    private GraphView graphView;
    private SpinnerModel spinnerModel;
    private List<String> arrayList;
    private StandardSpinnerAdapter standardSpinnerAdapter;
    private Button monthlyButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        toastManager = ToastManager.getInstance();
        graphContract = new StandardGraphContract(BaseActivity.getContext());
        standardSpinnerAdapter = StandardSpinnerAdapter.getInstance();
        return inflater.inflate(R.layout.fragment_all_in_one, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        graphView = (GraphView) getActivity().findViewById(R.id.graph);
        dailySpinner = (Spinner) getActivity().findViewById(R.id.dailySpinner);
        weeklySpinner = (Spinner) getActivity().findViewById(R.id.weeklySpinner);
//        monthlyButton = (Button) getActivity().findViewById(R.id.monthlyButton);
        monthlySpinner = (Spinner) getActivity().findViewById(R.id.monthlySpinner);

        spinnerModel = new SpinnerModel();
        for (int i = 0; i < spinnerModel.getStringArray().length; i++) {
            arrayList = Arrays.asList(getResources().getStringArray(spinnerModel.getStringArrayAtIndex(i)));
            standardSpinnerAdapter.setAdapter(spinnerModel.getSpinner(i), arrayList);
        }

        dailySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Logger.putInDebugLog(TAG, "Inside", "dailySpinner onItemSelected");
                Spinner spinner = (Spinner) parent;
                month = spinner.getSelectedItem().toString();
                if (!month.equalsIgnoreCase("Daily")) {
                    toastManager.showSimpleToastShort("Daily Filter selected");
                    //making other spinners as default value
                    weeklySpinner.setSelection(0);
                    monthlySpinner.setSelection(0);
                    //changing spinner color
                    dailySpinner.setBackground(getActivity().getResources().getDrawable(R.drawable.layout_background_button_navy_light));
                    weeklySpinner.setBackground(getActivity().getResources().getDrawable(R.drawable.layout_background_yellow_border_square));
                    monthlySpinner.setBackground(getActivity().getResources().getDrawable(R.drawable.layout_background_yellow_border_square));
                    //Constructing graph
                    graphContract.drawGraphByDay(graphView, month, "2016");
                } else if (month.equalsIgnoreCase("Daily")) {
                    //graphContract.resetGraph(graphView);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        weeklySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Logger.putInDebugLog(TAG, "Inside", "weeklySpinner onItemSelected");
                Spinner spinner = (Spinner) parent;
                month = spinner.getSelectedItem().toString();
                if (!month.equalsIgnoreCase("Weekly")) {
                    toastManager.showSimpleToastShort("Weekly Filter selected");
                    //making other spinners as default value
                    dailySpinner.setSelection(0);
                    monthlySpinner.setSelection(0);
                    //changing spinner color
                    weeklySpinner.setBackground(getActivity().getResources().getDrawable(R.drawable.layout_background_button_navy_light));
                    dailySpinner.setBackground(getActivity().getResources().getDrawable(R.drawable.layout_background_yellow_border_square));
                    monthlySpinner.setBackground(getActivity().getResources().getDrawable(R.drawable.layout_background_yellow_border_square));
                    //Constructing graph
                    graphContract.drawGraphByWeek(graphView, month, "2016");
                } else if (month.equalsIgnoreCase("Weekly")) {
                    //graphContract.resetGraph(graphView);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        monthlySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Logger.putInDebugLog(TAG, "Inside", "monthlySpinner onItemSelected");
                Spinner spinner = (Spinner) parent;
                year = spinner.getSelectedItem().toString();
                if (!year.equalsIgnoreCase("Monthly")) {
                    toastManager.showSimpleToastShort(year + " selected");
                    //making other spinners as default value
                    weeklySpinner.setSelection(0);
                    dailySpinner.setSelection(0);
                    //changing spinner color
                    spinner.setBackground(getActivity().getResources().getDrawable(R.drawable.layout_background_button_navy_light));
                    weeklySpinner.setBackground(getActivity().getResources().getDrawable(R.drawable.layout_background_yellow_border_square));
                    dailySpinner.setBackground(getActivity().getResources().getDrawable(R.drawable.layout_background_yellow_border_square));
                    //Constructing graph
                    graphContract.drawGraphByMonth(graphView, year);
                } else if (month.equalsIgnoreCase("Monthly")) {
                    //graphContract.resetGraph(graphView);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        monthlyButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toastManager.showSimpleToastShort("Monthly Filter selected");
//                //changing spinner color
//                monthlyButton.setBackground(getActivity().getResources().getDrawable(R.drawable.layout_background_button_navy_light));
//                weeklySpinner.setBackground(getActivity().getResources().getDrawable(R.drawable.layout_background_yellow_border_square));
//                dailySpinner.setBackground(getActivity().getResources().getDrawable(R.drawable.layout_background_yellow_border_square));
//                //Constructing graph
//                graphContract.drawGraphByMonth(graphView, year);
//            }
//        });

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