package com.bill.android.skymeal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.bill.android.skymeal.R;
import com.bill.android.skymeal.adapters.MenuItemAdapter;
import com.bill.android.skymeal.adapters.MenuItemAdapter.OnMenuItemClickListener;
import com.bill.android.skymeal.models.MenuItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

                public class MenuActivity extends AppCompatActivity implements OnMenuItemClickListener {

    private static final String LOG_TAG = MenuActivity.class.getSimpleName();
    private static final String SELECTED_ITEMS = "SelectedItems";
    private MenuItemAdapter mAdapter;
    private static ArrayList<MenuItem> mMenuItems = new ArrayList<>();
    private float mPrice = 0;
    @BindView(R.id.rv_menu) RecyclerView mRecyclerView;
    @BindView(R.id.btn_submit) Button mSelectItemsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ButterKnife.bind(this);

        // Initialize Firebase components
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference mMenuDatabaseReference = mFirebaseDatabase.getReference().child("/items/linner");

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int width = Math.round(displayMetrics.widthPixels / displayMetrics.density);
        int columns = Math.max(1, (int) Math.floor(width / 300));
        GridLayoutManager layoutManager = new GridLayoutManager(this, columns);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new MenuItemAdapter(this, mMenuItems, this);
        mRecyclerView.setAdapter(mAdapter);

        mMenuDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mMenuItems.clear();
                Iterable<DataSnapshot> snapIter = dataSnapshot.getChildren();
                for (DataSnapshot aSnapIter : snapIter) {
                    MenuItem item = aSnapIter.getValue(MenuItem.class);
                    Log.d(LOG_TAG, "Retrieved menu item: " + item.toString());
                    mMenuItems.add(item);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(LOG_TAG, "Firebase Database Error: " + databaseError.getMessage());
            }
        });

        mSelectItemsBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, SummaryActivity.class);
                i.putParcelableArrayListExtra(SELECTED_ITEMS, mAdapter.getSelected());
                startActivity(i);
            }
        });
    }

    @Override
    public void onMenuItemClick(float itemPrice) {
        mPrice += itemPrice;

        if (mPrice > 0) {
            mSelectItemsBtn.setText("$" + mPrice);
        } else {
            mSelectItemsBtn.setText(R.string.btn_select_items);
        }
    }
}
