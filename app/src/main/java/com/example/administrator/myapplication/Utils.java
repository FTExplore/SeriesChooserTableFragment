package com.example.administrator.myapplication;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FTE on 2016/12/9.
 */

public class utils {


    /**
     * 将集合按照指定区块大小分隔
     *
     * @param list      待分解集合
     * @param blockArgs 区块大小
     * @return 分隔好的集合
     */
    public static <T> List<List<T>> cal(List<T> list, int blockArgs) {

        int blockSize = blockArgs;
        List<List<T>> result = new ArrayList<>();
        int TotalCount = list.size();
        if (TotalCount <= 50) {
            return result;
        }

        int BlockNum = TotalCount / blockSize;
        int LastBlockContentCount = TotalCount % blockSize;

        Log.e("ZHZ", "总数 ： " + TotalCount + " block 数量: " + BlockNum + " 最后一个block size : " + LastBlockContentCount);

        int startIndex = 0;

        for (int i = 0; i < BlockNum; i++) {
            startIndex = i * blockSize;
            List<T> temp = list.subList(startIndex, startIndex + blockSize);
            result.add(temp);
        }

        if (LastBlockContentCount > 0) {
            List<T> temp3 = list.subList(startIndex + blockSize, TotalCount - 1);
            result.add(temp3);
        }

        return result;
    }
}
