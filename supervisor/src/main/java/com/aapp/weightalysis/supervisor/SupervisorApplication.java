package com.aapp.weightalysis.supervisor;

import android.content.Context;

import com.app.weightalysis.data_storage.PrimaryDBProvider;
import com.app.weightalysis.data_storage.SharedPreferenceManager;
import com.app.weightalysis.data_storage.StandardStorageHelper;
import com.app.weightalysis.oneui.custom_spinner.StandardSpinnerAdapter;
import com.app.weightalysis.oneui.toast_manager.ToastManager;
/**
 * Created by Aakash Singh on 24-11-2016.
 */

/**
 * class for initializing all initial instances required throughout application
 */
public class SupervisorApplication {

    private static Context context;

    public SupervisorApplication(Context mContext) {
        context = mContext;
        try {
            PrimaryDBProvider.createInstance(mContext);
            StandardStorageHelper.createInstance(mContext);
            PrimaryDBProvider.getInstance().getWritableDatabase();
            ToastManager.createInstance(mContext);
            SharedPreferenceManager.createInstance(mContext);
            StandardSpinnerAdapter.createInstance(mContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Context getContext() {
        return context;
    }
}
