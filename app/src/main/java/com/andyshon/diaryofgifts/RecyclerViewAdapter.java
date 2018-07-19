package com.andyshon.diaryofgifts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by andyshon on 18.07.18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<GiftModel> giftModelList;
    private View.OnLongClickListener longClickListener;

    public RecyclerViewAdapter(List<GiftModel> giftModelList, View.OnLongClickListener longClickListener) {
        this.giftModelList = giftModelList;
        this.longClickListener = longClickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        GiftModel giftModel = giftModelList.get(position);
        holder.itemTextView.setText(giftModel.getGiftName());
        holder.nameTextView.setText(giftModel.getPersonName());
        holder.dateTextView.setText(giftModel.getBorrowDate().toLocaleString().substring(0, 11));
        holder.itemView.setTag(giftModel);
        holder.itemView.setOnLongClickListener(longClickListener);
    }

    @Override
    public int getItemCount() {
        return giftModelList.size();
    }

    public void addItems(List<GiftModel> giftModelList) {
        this.giftModelList = giftModelList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView itemTextView;
        private TextView nameTextView;
        private TextView dateTextView;

        RecyclerViewHolder(View view) {
            super(view);
            itemTextView = (TextView) view.findViewById(R.id.giftTextView);
            nameTextView = (TextView) view.findViewById(R.id.nameTextView);
            dateTextView = (TextView) view.findViewById(R.id.dateTextView);
        }
    }
}
