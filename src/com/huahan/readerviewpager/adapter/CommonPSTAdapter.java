package com.huahan.readerviewpager.adapter;

import java.util.List;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * PagerSlidingTabStrip 公共的Adapter
 * 
 * @author wjh
 * 
 */
public class CommonPSTAdapter extends FragmentPagerAdapter
{
    private List<Fragment> list;
    private String[] typeArray;//

    public CommonPSTAdapter(FragmentManager fm)
    {
        super(fm);
    }

    public CommonPSTAdapter(FragmentManager fm, Context context,
            List<Fragment> list)
    {
        super(fm);
        this.list = list;
    }

    public CommonPSTAdapter(FragmentManager fm, Context context,
            List<Fragment> list, String[] typeArray)
    {
        super(fm);
        this.list = list;
        this.typeArray = typeArray;
    }

    @Override
    public Fragment getItem(int arg0)
    {
        return list.get(arg0);
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return typeArray[position];
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

}
