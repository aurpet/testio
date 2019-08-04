package com.example.testio;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;


import com.example.testio.webService.HttpMethod;
import com.example.testio.webService.HttpRequest;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.testio.webService.HttpRequest.URL_AUTHORIZATION;

public class LoginActivity extends AppActivity {

    @BindView(R.id.edt_user_name)
    EditText edtUserName;

    @BindView(R.id.edt_password)
    EditText edtPassword;

    private static String userName, password, token;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //hardcoded values for easy testing
        edtUserName.setText("tesonet");
        edtPassword.setText("partyanimal");
    }

    @OnClick(R.id.btn_login)
    public void clickBtnLogin() {
        if (isNotEmptyEditText(edtUserName, edtPassword)) {
            authorization(userName, password);
        }
    }

    private boolean isNotEmptyEditText(EditText et1, EditText et2) {
        userName = et1.getText().toString();
        password = et2.getText().toString();
        if (StringUtils.isBlank(userName) && StringUtils.isBlank(password)) {
            showToastMessage(this, getString(R.string.empty_both_login_fields));
            return false;
        } else if (StringUtils.isBlank(userName)) {
            showToastMessage(this, getString(R.string.empty_username));
            return false;
        } else if (StringUtils.isBlank(password)) {
            showToastMessage(this, getString(R.string.empty_pass));
            return false;
        }
        return true;
    }

    @SuppressLint("StaticFieldLeak")
    private void authorization (String userName, String password){
        new AsyncTask<String, Void, String>(){

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showProgressDialog(LoginActivity.this, getString(R.string.dialog_connecting));
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (progressDialog.isShowing()){
                    progressDialog.cancel();
                }
                if (token != null && !token.isEmpty()){
                    Intent intent = new Intent(LoginActivity.this, LoaderActivity.class);
                    intent.putExtra("token", token);
                    startActivity(intent);
                } else {
                    showToastMessage(LoginActivity.this, getString(R.string.authorization_error));
                }
            }

            @Override
            protected String doInBackground(String... strings) {
                try {
                    HttpRequest httpRequest = new HttpRequest(URL_AUTHORIZATION);

                    String response = httpRequest.prepare(HttpMethod.POST).login(userName, password).readResponse();
                    JSONObject jObject = new JSONObject(response);
                    token = jObject.optString("token");

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return token;
            }
        }.execute(userName, password);

    }

}
