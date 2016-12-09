package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Reclye.SeriesTableFragment;
import vp_select.Selector;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void go(View view) {
        Selector selector = new Selector();
        List<View> list = new ArrayList<>();

        for (int i = 0; i < 100; i++){
            TextView tv = new TextView(this);
            tv.setText("tab-"+i);
            list.add(tv);
        }
        selector.setList(list);
        android.app.FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fl, selector);
        ft.commit();
    }
}
