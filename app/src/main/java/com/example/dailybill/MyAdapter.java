package com.example.dailybill;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<BillItem> dataList;
    private MyAdapter.OnItemLongClickListener mOnItemLongClickListener;

    public MyAdapter(List<BillItem> dataList) {
        this.dataList = dataList;
    }

    public void setLongClickListener(MyAdapter.OnItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    public BillItem getItem(int position) {
        return dataList.get(position);
    }

    public void addItem(BillItem item, int position) {
        dataList.add(position, item);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
        if(position != dataList.size()){
            notifyItemRangeChanged(position, dataList.size()-position);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BillItem item = dataList.get(position);
        holder.category.setText(item.category);
        holder.amount.setText(String.valueOf(item.amount));
        if(mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemLongClickListener.onItemLongClick(holder.itemView, position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
