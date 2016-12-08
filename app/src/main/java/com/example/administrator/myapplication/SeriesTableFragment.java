package com.example.administrator.myapplication;


import android.app.Fragment;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 电视剧集选择器(分类表格,可滑动,焦点)
 * Created by FTE on 2016/12/8.
 */

public class SeriesTableFragment extends Fragment {

    private SeriesSelectorTableAdapter adapterTable;
    private SeriesSelectorHeadAdapter seriesSelectorHeadAdapter;
    private List<Integer> tableList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tableList = new ArrayList<>();
        for (int i = 1; i < 106; i++) {
            tableList.add(i);
        }

        // head
        seriesSelectorHeadAdapter = new SeriesSelectorHeadAdapter();
        seriesSelectorHeadAdapter.setmListener(OnClickHead);
        seriesSelectorHeadAdapter.setList(tableList);

        // 表格
        adapterTable = new SeriesSelectorTableAdapter();
        adapterTable.setmListener(OnClickTable);
        adapterTable.setDataList(tableList);
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
        head.setAdapter(seriesSelectorHeadAdapter);
        seriesSelectorHeadAdapter.notifyDataSetChanged();
        return view;
    }


    private SeriesSelectorHeadAdapter.OnItemClick OnClickHead = new SeriesSelectorHeadAdapter.OnItemClick() {
        @Override
        public void OnClick(int position) {
            Log.e("ZHZ", "头部点击");
        }
    };

    private SeriesSelectorTableAdapter.OnItemClick OnClickTable = new SeriesSelectorTableAdapter.OnItemClick() {
        @Override
        public void OnClick(int positoin) {
            Log.e("ZHZ", "内容点击");
        }
    };


}
