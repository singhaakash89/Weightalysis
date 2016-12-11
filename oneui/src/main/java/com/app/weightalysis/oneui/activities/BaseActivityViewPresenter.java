package com.app.weightalysis.oneui.activities;

/**
 * Created by Aakash Singh on 11-12-2016.
 */

public class BaseActivityViewPresenter implements BaseActivityViewContract {

    private BaseActivityViewContract view;

    public BaseActivityViewPresenter(BaseActivityViewContract view) {
        this.view = view;
    }

    @Override
    public void inflateHomeFragment() {
        view.inflateHomeFragment();
    }

    @Override
    public void inflateDayFragment() {
        view.inflateDayFragment();
    }

    @Override
    public void inflateWeekFragment() {
        view.inflateWeekFragment();
    }

    @Override
    public void inflateMonthFragment() {
        view.inflateMonthFragment();
    }
}
