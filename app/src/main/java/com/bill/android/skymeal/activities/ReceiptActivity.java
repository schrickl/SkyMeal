package com.bill.android.skymeal.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bill.android.skymeal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceiptActivity extends AppCompatActivity {

    private static final String LOG_TAG = ReceiptActivity.class.getSimpleName();
    private String RESULT_CODE = "RESULT_CODE";
    private int mResultCode;
    @BindView(R.id.img_transaction_result) ImageView mTransactionResultImage;
    @BindView(R.id.tv_transaction_result) TextView mTransactionResultText;
    @BindView(R.id.tv_charge) TextView mChargeResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
        Toolbar menuToolbar = (Toolbar) findViewById(R.id.receipt_toolbar);
        setSupportActionBar(menuToolbar);
        getSupportActionBar().setTitle(getString(R.string.title_activity_receipt));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        Intent i = getIntent();
        mResultCode = i.getIntExtra(RESULT_CODE, 0);

        if (mResultCode == Activity.RESULT_OK) {
            mTransactionResultImage.setImageDrawable(getResources().getDrawable(R.drawable.success));
            mTransactionResultText.setText(getResources().getString(R.string.order_success));
            mChargeResult.setText(getResources().getString(R.string.charge_msg_success));
        } else {
            mTransactionResultImage.setImageDrawable(getResources().getDrawable(R.drawable.failure));
            mTransactionResultText.setText(getResources().getString(R.string.order_failure));
            mChargeResult.setText(getResources().getString(R.string.charge_msg_failure));
        }
    }
}
