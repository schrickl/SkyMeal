package com.bill.android.skymeal.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bill.android.skymeal.R;
import com.bill.android.skymeal.models.MenuItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.ViewHolder> {

    private static final String LOG_TAG = MenuItemAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<MenuItem> mMenuItems;
    private ArrayList<MenuItem> mSelected = new ArrayList<>();

    public MenuItemAdapter(Context context, ArrayList<MenuItem> itemList) {
        mContext = context;
        mMenuItems = itemList;
    }

    @Override
    public int getItemCount() {
        return mMenuItems == null ? 0 : mMenuItems.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final MenuItem item = mMenuItems.get(position);

        Uri uri = Uri.parse(item.getUrl());
        Picasso.get().load(uri).into(holder.image);

        holder.name.setText(item.getName());
        holder.description.setText(item.getDescription());
        holder.price.setText("$" + item.getPrice());

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelected.contains(item)) {
                    mSelected.remove(item);
                    unhighlightView(holder);
                } else {
                    mSelected.add(item);
                    highlightView(holder);
                }
            }
        });

        if (mSelected.contains(item)) {
            highlightView(holder);
        } else {
            unhighlightView(holder);
        }
    }

    private void highlightView(ViewHolder holder) {
        holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
    }

    private void unhighlightView(ViewHolder holder) {
        holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, android.R.color.white));
    }

    private void clearAll(boolean isNotify) {
        mMenuItems.clear();
        mSelected.clear();
        if (isNotify) notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder  {

        @BindView(R.id.cv_container) CardView card;
        @BindView(R.id.ll_menu_card) LinearLayout container;
        @BindView(R.id.iv_item_image) ImageView image;
        @BindView(R.id.tv_item_name) TextView name;
        @BindView(R.id.tv_item_description) TextView description;
        @BindView(R.id.tv_item_price) TextView price;

        ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
