package Reclye;


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

import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 电视剧集选择器(分类表格,可滑动,焦点)
 * Created by FTE on 2016/12/8.
 */

public class SeriesTableFragment extends Fragment {

    private SeriesSelectorTableAdapter adapterTable;
    private SeriesSelectorHeadAdapter seriesSelectorHeadAdapter;
    private SeriesCallback mListener;
    private final int blockSize = 50;
    private boolean overLim = false;
    List<List<Integer>> resultList;

    public void setmListener(SeriesCallback mListener) {
        this.mListener = mListener;
    }

    interface SeriesCallback {
        void OnSelect();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Integer> tableList = new ArrayList<>();
        for (int i = 1; i < 706; i++) {
            tableList.add(i);
        }


        resultList = cal(tableList);

        if (resultList.size() <= 0)
            return;

        // head
        seriesSelectorHeadAdapter = new SeriesSelectorHeadAdapter();
        seriesSelectorHeadAdapter.setmListener(OnClickHead);
        seriesSelectorHeadAdapter.setList(resultList);

        // 表格
        adapterTable = new SeriesSelectorTableAdapter();
        adapterTable.setmListener(OnClickTable);
        adapterTable.setDataList(resultList.get(0));
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
        head.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.left = 10;
                outRect.right = 10;
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        head.setLayoutManager(linearLayoutManager);
        head.setAdapter(seriesSelectorHeadAdapter);
        seriesSelectorHeadAdapter.notifyDataSetChanged();
        return view;
    }


    private List<List<Integer>> cal(List<Integer> list) {
        List<List<Integer>> result = new ArrayList<>();
        int TotalCount = list.size();
        if (TotalCount <= 50) {
            overLim = false;
            return result;
        }

        overLim = true;

        int BlockNum = TotalCount / blockSize;
        int LastBlockContentCount = TotalCount % blockSize;

        Log.e("ZHZ", "总数 ： " + TotalCount + " block 数量: " + BlockNum + " 最后一个block size : " + LastBlockContentCount);

        int startIndex = 0;

        for (int i = 0; i < BlockNum; i++) {
            startIndex = i * blockSize;
            List<Integer> temp = list.subList(startIndex, startIndex + blockSize);
            result.add(temp);
        }

        if (LastBlockContentCount > 0) {
            List<Integer> temp3 = list.subList(startIndex + blockSize, TotalCount - 1);
            result.add(temp3);
        }

        return result;
    }

    private SeriesSelectorHeadAdapter.OnItemClick OnClickHead = new SeriesSelectorHeadAdapter.OnItemClick() {
        @Override
        public void OnClick(int position) {
            Log.e("ZHZ", "头部点击");
            adapterTable.setDataList(resultList.get(position));
            adapterTable.notifyDataSetChanged();
        }
    };

    private SeriesSelectorTableAdapter.OnItemClick OnClickTable = new SeriesSelectorTableAdapter.OnItemClick() {
        @Override
        public void OnClick(int positoin) {
            Log.e("ZHZ", "内容点击 : " + positoin);
            if (mListener == null)
                return;
            mListener.OnSelect();
        }
    };


}
