package com.example.testio;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.testio.db.DbClient;
import com.example.testio.webService.HttpMethod;
import com.example.testio.webService.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import static com.example.testio.webService.HttpRequest.URL_GET_DATA_LIST;


public class LoaderActivity extends AppActivity {
    public static JSONArray jsonArray = null;
    private DbClient dbClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String token = extras.getString("token");
            if (token != null && !token.isEmpty()) {

                fetchData(token);
            }
            // create db
            dbClient = new DbClient(getApplicationContext());
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void fetchData(String token) {
        new AsyncTask<String, Void, JSONArray>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showProgressDialog(LoaderActivity.this, getString(R.string.progress_message_fetching));
            }

            @Override
            protected void onPostExecute(JSONArray jsonArray) {
                super.onPostExecute(jsonArray);
                progressDialog.cancel();
                if (jsonArray != null && jsonArray.length() > 0){
                    Intent intent = new Intent(LoaderActivity.this, ShowResultActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            protected JSONArray doInBackground(String... strings) {
                try {
                    HttpRequest httpRequest = new HttpRequest(URL_GET_DATA_LIST);
                    httpRequest.setToken(token);
                    String response = httpRequest.prepare(HttpMethod.GET).readResponse();

                    jsonArray = new JSONArray(response);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return jsonArray;
            }
        }.execute();

    }
}
