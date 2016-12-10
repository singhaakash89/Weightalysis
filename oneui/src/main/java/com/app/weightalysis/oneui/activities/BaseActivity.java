package com.app.weightalysis.oneui.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.app.graph.GraphContract;
import com.app.graph.StandardGraphContract;
import com.app.graph.data_storage.GraphDataDataStorageProvider;
import com.app.graph.data_storage.GraphDataStorageContract;
import com.app.weightalysis.logger.Logger;
import com.app.weightalysis.oneui.R;
import com.app.weightalysis.oneui.toast_manager.ToastManager;
import com.jjoe64.graphview.GraphView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static String TAG = BaseActivity.class.getSimpleName();
    private Context context;
    private DatePickerDialog datePickerDialog;
    private TextView dateTV;
    private EditText weightET;
    private SimpleDateFormat simpleDateFormat;
    private GraphContract graphContract;
    private GraphDataStorageContract graphDataStorageContract;
    private String dateString;
    private GraphView graphView;
    private static final int EXIT_TIME_OUT = 2000;
    private int backPressCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Weight-alysis");
        graphContract = new StandardGraphContract(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //for making icons colored in nav drawer
        navigationView.setItemIconTintList(null);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            //Below code is for 2 second Pause for Exit Using HANDLER
            backPressCount++;
            if (backPressCount == 1) {
                ToastManager.getInstance().showSimpleToastShort("Press again to Exit");
            } else if (backPressCount == 2) {
                BaseActivity.this.finish();
            }

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    backPressCount = 0;
                }
            }, EXIT_TIME_OUT);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            ToastManager.getInstance().showSimpleToastShort("Home is selected");
        } else if (id == R.id.nav_graph_date) {
            graphView = (GraphView) findViewById(R.id.graph_new);
            graphView.setVisibility(View.INVISIBLE);
            graphContract.drawGraphByDate(graphView);
            ToastManager.getInstance().showSimpleToastShort("Daily GraphContract is selected");
        } else if (id == R.id.nav_graph_week) {
            graphView = (GraphView) findViewById(R.id.graph_new);
            graphView.setVisibility(View.INVISIBLE);
            graphContract.drawGraphByWeek(graphView);
            ToastManager.getInstance().showSimpleToastShort("Weekly GraphContract is selected");
        } else if (id == R.id.nav_graph_month) {
            graphView = (GraphView) findViewById(R.id.graph_new);
            graphView.setVisibility(View.INVISIBLE);
            graphContract.drawGraphByMonth(graphView);
            ToastManager.getInstance().showSimpleToastShort("Monthly GraphContract is selected");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openDialog() {
        final Dialog dialog = new Dialog(BaseActivity.this);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_layout);
        dialog.setTitle("Enter your Weight and Select Date");
        weightET = (EditText) dialog.findViewById(R.id.weightET);
        dateTV = (TextView) dialog.findViewById(R.id.dateTV);
        dateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastManager.getInstance().showSimpleToastShort("Please choose date");
                showDatePickerDialog();
            }
        });

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                graphView = (GraphView) findViewById(R.id.graph_new);
                graphView.setVisibility(View.INVISIBLE);
                if (null != dateString) {
                    String[] str = dateString.split("-");
                    for (String s : str) {
                        Logger.putInDebugLog(TAG, s, "");
                    }
                    graphDataStorageContract = new GraphDataDataStorageProvider();
                    long id = graphDataStorageContract.insertData(weightET.getText().toString(), str[0], str[1], str[2]);
                    ToastManager.getInstance().showSimpleToastShort("row stored at : " + id + " position");
                }
            }
        });

        dialog.show();
    }

    private void showDatePickerDialog() {
        simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateTV.setText(simpleDateFormat.format(newDate.getTime()));
                dateString = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(newDate.getTime());
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}
