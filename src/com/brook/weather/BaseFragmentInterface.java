package com.brook.weather;

import android.os.Bundle;
import android.view.View;

/**
 *
 */
public interface BaseFragmentInterface {

    void initView(View view);

    /**
     * 加载网络数据或者本地数据
     * @param savedInstanceState
     */
    void initData(Bundle savedInstanceState);

    void release();
}
