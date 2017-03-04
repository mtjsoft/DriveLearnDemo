package com.huahan.readerviewpager;

import android.view.View;

/**
 * @author wjh 用于处理Adaper中的view被点击
 */
public interface AdapterViewClickListener
{
    public abstract void adapterViewClick(int position, View view);
}
