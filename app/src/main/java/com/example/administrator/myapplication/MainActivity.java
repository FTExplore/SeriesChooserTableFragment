package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Reclye.SeriesSelectorTableAdapter;
import vp_select.Selector;

public class MainActivity extends Activity {
    List<Integer> tempList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempList = new ArrayList<>();
        for (int i = 0; i < 706; i++) {
            tempList.add(i);
        }
        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
    }

    public void go(View view) {
        Selector selector = new Selector();
        List<View> list = new ArrayList<>();

        List<List<Integer>> fu = utils.cal(tempList, 50);
        for (int i = 0; i < fu.size(); i++) {
            RecyclerView recyclerView = new RecyclerView(this);
            recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                    super.getItemOffsets(outRect, view, parent, state);

                    outRect.top = 5;
                    outRect.left = 5;
                    outRect.right = 5;
                    outRect.bottom = 5;

                }
            });
            GridLayoutManager manager = new GridLayoutManager(this, 5);
            recyclerView.setLayoutManager(manager);
            SeriesSelectorTableAdapter adapter = new SeriesSelectorTableAdapter();
            adapter.setDataList(fu.get(i));
            recyclerView.setAdapter(adapter);
            list.add(recyclerView);
        }

        selector.setList(list);
        android.app.FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fl, selector);
        ft.commit();
    }
}
