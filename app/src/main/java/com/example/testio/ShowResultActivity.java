package com.example.testio;


import android.content.Intent;
import android.os.Bundle;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);
        ButterKnife.bind(this);

        showDataList();
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
        DataAdapter dataAdapter = new DataAdapter(this, servers);

        // action on item click
        OnClickI onClickI = new OnClickI() {
            @Override
            public void onNameClick(int position, DataObject dataObject) {
                showToastMessage(ShowResultActivity.this, "Server name is: " + dataObject.getCountryName());
            }

            @Override
            public void onDistanceClick(int position, DataObject dataObject) {
                // remove item
                dataAdapter.remove(dataAdapter.getItem(position));
                showToastMessage(ShowResultActivity.this, "Item successfully deleted");
            }
        };

        dataAdapter.setDataOnclick(onClickI);
        if (listView != null)
        listView.setAdapter(dataAdapter);

    }
}
