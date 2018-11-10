package com.bill.android.skymeal.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bill.android.skymeal.R;

import butterknife.ButterKnife;

public class SummaryActivity extends AppCompatActivity {

    private static String LOG_TAG = SummaryActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        ButterKnife.bind(this);
    }
}
