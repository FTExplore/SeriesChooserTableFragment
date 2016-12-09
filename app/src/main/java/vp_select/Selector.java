package vp_select;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FTE on 2016/12/9.
 */

public class Selector extends Fragment {

    private List<View> list;

    public void setList(List<View> list) {
        this.list = list;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.selector, null);
        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.vp);
        TabLayout tablayout = (TabLayout) view.findViewById(R.id.tablayout);
        mViewPager.setAdapter(mPagerAdapter);
        tablayout.setupWithViewPager(mViewPager);
        return view;
    }


    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "tab-" + position;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    };

}
