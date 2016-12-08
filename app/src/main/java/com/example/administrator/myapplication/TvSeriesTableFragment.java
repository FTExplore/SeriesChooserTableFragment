package com.example.administrator.myapplication;


import android.app.Fragment;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 电视剧集选择器(分类表格,可滑动,焦点)
 * Created by FTE on 2016/12/8.
 */

public class TvSeriesTableFragment extends Fragment {

    private AdatperTable adapterTable;
    private AdapterHead adapterHead;
    private List<Integer> tableList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapterTable = new AdatperTable();
        tableList = new ArrayList<>();
        for (int i = 1; i < 106; i++) {
            tableList.add(i);
        }
        adapterTable.setDataList(tableList);

        adapterHead = new AdapterHead();
        adapterHead.setList(tableList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tv_series, null);
        RecyclerView table = (RecyclerView) view.findViewById(R.id.table);

        table.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);

                outRect.top = 5;
                outRect.left = 5;
                outRect.right = 5;
                outRect.bottom = 5;

            }
        });
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 5);
        table.setLayoutManager(manager);
        table.setAdapter(adapterTable);
        adapterTable.notifyDataSetChanged();

        RecyclerView head = (RecyclerView) view.findViewById(R.id.head);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        head.setLayoutManager(linearLayoutManager);
        head.setAdapter(adapterHead);
        adapterHead.notifyDataSetChanged();
        return view;
    }

}
