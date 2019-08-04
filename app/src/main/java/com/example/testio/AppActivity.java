package com.example.testio;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

/**
The purpose of this class
is to reuse methods to
other activities
*/

public abstract class AppActivity extends AppCompatActivity {
    private static final String errorLog = "ERROR_LOG";
    private static final String infoLog = "INFO_LOG";

    public static ProgressDialog progressDialog;

    protected void logError (String error){
        Log.e(errorLog, error);
    }

    protected void logInfo (String info){
        Log.i(infoLog, info);
    }

    protected void showToastMessage (Context context, String m){
        Toast.makeText(context, m, Toast.LENGTH_LONG).show();
    }

    protected  static void showProgressDialog (Context context, String message){
        progressDialog = new ProgressDialog(context);
        if (progressDialog.isShowing()){
            progressDialog.cancel();
        } else {
            progressDialog.setMessage(message);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

    }

    protected static void setCustomActionbar(ActionBar mActionBar, Context context) {
        LayoutInflater mInflater;
        View mCustomView;
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);

            mInflater = LayoutInflater.from(context);
            mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);

            mActionBar.setCustomView(mCustomView);
            mActionBar.setDisplayShowCustomEnabled(true);
        }
    }

}
