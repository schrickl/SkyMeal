package com.bill.android.skymeal.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bill.android.skymeal.R;
import com.bill.android.skymeal.models.MenuItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SummaryItemAdapter extends RecyclerView.Adapter<SummaryItemAdapter.ViewHolder> {

    private static final String LOG_TAG = SummaryItemAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<MenuItem> mMenuItems;
    private int mQuantity = 1;

    public SummaryItemAdapter(Context context, ArrayList<MenuItem> itemList) {
        mContext = context;
        mMenuItems = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.summary_item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SummaryItemAdapter.ViewHolder holder, int position) {
        final MenuItem item = mMenuItems.get(position);

        holder.quantity.setText(String.valueOf(mQuantity));
        holder.name.setText(item.getName());
        holder.price.setText("$" + item.getPrice());
    }

    @Override
    public int getItemCount() {
        return mMenuItems == null ? 0 : mMenuItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_summary_quantity) TextView quantity;
        @BindView(R.id.tv_summary_name) TextView name;
        @BindView(R.id.tv_summary_price) TextView price;

        ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
