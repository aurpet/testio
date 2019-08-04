package com.example.testio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.testio.dataListHelper.DataAdapter;
import com.example.testio.dataListHelper.DataObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.testio.LoaderActivity.jsonArray;

public class ShowResultActivity extends AppCompatActivity {
    @BindView(R.id.lv_data)
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);
        ButterKnife.bind(this);

        showDataList();
    }

    private void showDataList() {
        ArrayList<DataObject> servers = DataObject.fromJson(jsonArray);

        DataAdapter dataAdapter = new DataAdapter(this, servers);
        if (listView != null)
        listView.setAdapter(dataAdapter);

    }
}
