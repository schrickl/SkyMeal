package com.bill.android.skymeal.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bill.android.skymeal.R;

public class MainActivity extends AppCompatActivity {

    private static int SLEEP_TIME = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new mTask().execute();
    }

    private class mTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Intent mainClass = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(mainClass);
            finish();
        }
    }
}
