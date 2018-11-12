package com.bill.android.skymeal.activities;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.bill.android.skymeal.R;
import com.bill.android.skymeal.adapters.SummaryItemAdapter;
import com.bill.android.skymeal.models.MenuItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SummaryActivity extends AppCompatActivity {

    private static String LOG_TAG = SummaryActivity.class.getSimpleName();
    private SummaryItemAdapter mAdapter;
    private static ArrayList<MenuItem> mMenuItems = new ArrayList<>();
    private static final String SELECTED_ITEMS = "SelectedItems";
    private float mSub;
    private float mT;
    @BindView(R.id.rv_summary) RecyclerView mRecyclerView;
    @BindView(R.id.tv_subtotal) TextView mSubtotal;
    @BindView(R.id.tv_tip) TextView mTip;
    @BindView(R.id.tv_total) TextView mTotal;
    @BindView(R.id.btn_ten) Button mTen;
    @BindView(R.id.btn_fifteen) Button mFifteen;
    @BindView(R.id.btn_twenty) Button mTwenty;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Toolbar menuToolbar = (Toolbar) findViewById(R.id.summary_toolbar);
        setSupportActionBar(menuToolbar);
        getSupportActionBar().setTitle(getString(R.string.title_activity_summary));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        mMenuItems = intent.getParcelableArrayListExtra(SELECTED_ITEMS);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new SummaryItemAdapter(this, mMenuItems);
        mRecyclerView.setAdapter(mAdapter);

        // Subtotal
        for (int i = 0; i < mMenuItems.size(); i++) {
            mSub += Float.valueOf(mMenuItems.get(i).getPrice());
        }
        // Initial values
        mTip.setText(R.string.zero);
        mSubtotal.setText(String.valueOf("$" + String.format("%.2f", mSub)));
        mTotal.setText(String.valueOf("$" + String.format("%.2f", mSub)));

        mTen.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mT = (float) (mSub * .10);
                mTip.setText(String.valueOf("$" + String.format("%.2f", mT)));
                mTotal.setText(String.valueOf("$" + String.format("%.2f", (mT + mSub))));
            }
        });

        mFifteen.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mT = (float) (mSub * .15);
                mTip.setText(String.valueOf("$" + String.format("%.2f", mT)));
                mTotal.setText(String.valueOf("$" + String.format("%.2f", (mT + mSub))));
            }
        });

        mTwenty.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mT = (float) (mSub * .20);
                mTip.setText(String.valueOf("$" + String.format("%.2f", mT)));
                mTotal.setText(String.valueOf("$" + String.format("%.2f", (mT + mSub))));
            }
        });

        // Save last order in SharedPrefs for the widget to use
        SharedPreferences prefs = getSharedPreferences(getString(R.string.widget_prefs), Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat(getString(R.string.order_total), (mT + mSub));
        editor.putString(getString(R.string.order_details), orderDetailsBuilder());
        editor.apply();

        // and let the widget know there is a new order to display
        Intent i = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        sendBroadcast(intent);
    }

    private String orderDetailsBuilder() {
        String details = "";

        for (int i = 0; i < mMenuItems.size(); i++) {
            details += mMenuItems.get(i).getName() + " " + "$" + mMenuItems.get(i).getPrice();
            if (i != mMenuItems.size() - 1) {
                details += ("\n");
            }
        }

        return details;
    }
}
