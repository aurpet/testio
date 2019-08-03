package com.example.testio;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public abstract class AppActivity extends AppCompatActivity {
    private static final String errorLog = "ERROR_LOG";
    private static final String infoLog = "INFO_LOG";

    protected void logError (String error){
        Log.e(errorLog, error);
    }

    protected void logInfo (String info){
        Log.i(infoLog, info);
    }

    protected void showToastMessage (Context context, String m){
        Toast.makeText(context, m, Toast.LENGTH_LONG).show();
    }




}
