package com.app.weightalysis.oneui.custom_spinner;

import android.widget.Spinner;

import java.util.List;

/**
 * Created by Aakash Singh on 29-11-2016.
 */

public interface SpinnerAdapterContract {
    public boolean setAdapter(Spinner spinner, List<String> list);

    public boolean setAdapterSelected(Spinner spinner, List<String> list);
}
