package com.bill.android.skymeal.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public class SummaryItemAdapter extends RecyclerView.Adapter<SummaryItemAdapter.ViewHolder> {

    private static final String LOG_TAG = SummaryItemAdapter.class.getSimpleName();

    @NonNull
    @Override
    public SummaryItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SummaryItemAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
