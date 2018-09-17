package com.coinwind.bifeng.ui.windrecords.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;

public class WindRecordsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wind_records);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wind_records;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void loadDate() {

    }
}
