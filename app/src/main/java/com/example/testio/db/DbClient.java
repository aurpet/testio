package com.example.testio.db;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Room;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DbClient {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static String DB_NAME = "test";
    private boolean successSaved;


    private AppDatabase appDatabase;

    public DbClient(Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }

    public void insertServer(String name, String distance) {
        Server server = new Server();
        server.setName(name);
        server.setDistance(distance);
        server.setCreatedAt(getDateTime(DATE_TIME_FORMAT));
        insertServer(server);
    }

    public void insertServer(final Server server) {

        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                try {
                    appDatabase.daoAccess().insertServer(server);
                    successSaved = true;
                } catch (Exception e) {
                    Log.e("Data not saved to DB", e.toString());
                }
                return successSaved;
            }

        }.execute();
    }

    private String getDateTime(String timeStampFormat) {
        SimpleDateFormat format = new SimpleDateFormat(timeStampFormat);
        return format.format(new Date());
    }

}
