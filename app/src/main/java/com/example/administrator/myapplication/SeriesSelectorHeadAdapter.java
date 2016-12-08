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

public class SeriesSelectorHeadAdapter extends RecyclerView.Adapter<SeriesSelectorHeadAdapter.ViewHolder> {

    private List<Integer> list;

    public void setList(List<Integer> list) {
        this.list = list;
    }

    private OnItemClick mListener;

    public void setmListener(OnItemClick mListener) {
        this.mListener = mListener;
    }

    interface OnItemClick {
        void OnClick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_head, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder == null)
            return;

        holder.num.setText(String.valueOf(list.get(position)));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView num;

        ViewHolder(View itemView) {
            super(itemView);
            num = (TextView) itemView.findViewById(R.id.num_head);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null)
                mListener.OnClick(getAdapterPosition());
        }
    }
}
