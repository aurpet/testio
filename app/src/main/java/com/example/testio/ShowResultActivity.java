package com.example.testio;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


import com.example.testio.dataListHelper.DataAdapter;
import com.example.testio.dataListHelper.DataObject;
import com.example.testio.dataListHelper.OnClickI;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.testio.LoaderActivity.jsonArray;

public class ShowResultActivity extends AppActivity {
    @BindView(R.id.lv_data)
    ListView listView;

    DataAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);
        ButterKnife.bind(this);
        setCustomToolbar(false, R.id.toolbar, null);
        showDataList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.log_out:
                finishAffinity();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent intent = new Intent(ShowResultActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void showDataList() {
        ArrayList<DataObject> servers = DataObject.fromJson(jsonArray);
        dataAdapter = new DataAdapter(this, servers);

        // action on item click
        OnClickI onClickI = new OnClickI() {
            @Override
            public void onNameClick(int position, DataObject dataObject) {
                showToastMessage(ShowResultActivity.this,
                        getString(R.string.message_server_name) + dataObject.getCountryName());
            }

            @Override
            public void onDistanceClick(int position, DataObject dataObject) {
                // AlertDialog of remove item
                AlertDialog.Builder builder = new AlertDialog.Builder(ShowResultActivity.this);
                builder.setCancelable(false);
                builder.setTitle(getString(R.string.title_delete));
                builder.setMessage(getString(R.string.message_delete));
                builder.setPositiveButton(getString(R.string.positive_btn), (dialogInterface, i) -> {
                    dataAdapter.remove(dataAdapter.getItem(position));
                    showToastMessage(ShowResultActivity.this, getString(R.string.message_success));
                });
                builder.setNegativeButton(getString(R.string.negative_btn), (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                });
                builder.create().show();
            }
        };

        dataAdapter.setDataOnclick(onClickI);
        if (listView != null)
        listView.setAdapter(dataAdapter);
    }
}
