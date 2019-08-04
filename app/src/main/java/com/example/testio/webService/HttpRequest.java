package com.example.testio.webService;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpRequest {
    public static final String URL_AUTHORIZATION = "http://playground.tesonet.lt/v1/tokens";
    public static final String URL_GET_DATA_LIST = "http://playground.tesonet.lt/v1/servers";

    private URL url;
    private HttpURLConnection connection;
    private DataOutputStream dataOutputStream;
    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public HttpRequest(String url) throws IOException {
        this(new URL(url));
        Log.d("URL", url);
    }

    public HttpRequest(URL url) throws IOException {
        this.url = url;
        connection = (HttpURLConnection) this.url.openConnection();
    }

    private void prepareAll(HttpMethod method) throws IOException {
        connection.setDoInput(true);
        connection.setRequestMethod(method.name());
        connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        connection.setRequestProperty("Accept", "application/json");
        switch (method) {
            case POST:
                connection.setDoOutput(true);
                break;
            case GET:
                connection.setRequestProperty("Authorization","Bearer " + token);
                break;
        }
        connection.disconnect();
    }

    public HttpRequest prepare(HttpMethod method) throws IOException {
        prepareAll(method);
        return this;
    }

    public HttpRequest login(String userName, String pass) throws JSONException, IOException {
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("username", userName);
        jsonParam.put("password", pass);
        dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.writeBytes(jsonParam.toString());
        dataOutputStream.flush();
        dataOutputStream.close();
        return this;
    }

    public String readResponse() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        for (String line; (line = br.readLine()) != null; ) response.append(line + "\n");
        Log.d("Response", response.toString());
        return response.toString();
    }

}
