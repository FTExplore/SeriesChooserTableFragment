package com.example.administrator.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by FTE on 2016/12/8.
 */

public class AdatperTable extends RecyclerView.Adapter<AdatperTable.TableViewHolder> {

    private List<Integer> dataList;

    public void setDataList(List<Integer> dataList) {
        this.dataList = dataList;
    }

    @Override
    public TableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new TableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TableViewHolder holder, int position) {
        if (holder == null)
            return;

        holder.num.setText(String.valueOf(dataList.get(position)));
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    class TableViewHolder extends RecyclerView.ViewHolder {

        private TextView num;

        TableViewHolder(View itemView) {
            super(itemView);
            num = (TextView) itemView.findViewById(R.id.item);
        }
    }
}
