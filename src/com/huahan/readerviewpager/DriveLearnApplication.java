package com.huahan.readerviewpager;

import java.util.Map;

import org.litepal.LitePal;

import android.app.ActivityManager;
import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.SDKInitializer;
import com.huahan.hhbaseutils.HHLocationUtils;
import com.huahan.hhbaseutils.HHLocationUtils.LocationListener;
import com.huahan.hhbaseutils.manager.HHUiTopManager;
import com.huahan.hhbaseutils.model.HHLoadState;
import com.huahan.hhbaseutils.model.HHLoadViewInfo;
import com.huahan.hhbaseutils.ui.HHApplication;

public class DriveLearnApplication extends HHApplication
{

    @Override
    public void onCreate()
    {
        super.onCreate();
        // 设置标题大小
        HHUiTopManager.mTopViewInfo.titleSize = 18;
        // 设置标题颜色
        HHUiTopManager.mTopViewInfo.titleTextColor = ContextCompat.getColor(
                getBaseContext(), R.color.white);
        // 设置返回图标
        //HHUiTopManager.mTopViewInfo.backLeftDrawable = R.drawable.base_back_gray;
        // 设置头部线的颜色
        HHUiTopManager.mTopViewInfo.topLineColor = getResources().getColor(
                R.color.background);
        HHUiTopManager.mTopViewInfo.topLineHeight = 1;
        int myPid1 = android.os.Process.myPid();// 获取当前运行进程pid
        ActivityManager mActivityManager = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        // 获取主进程的id
        int pid = mActivityManager.getRunningAppProcesses().get(0).pid;
        if (pid == myPid1)
        {
            SDKInitializer.initialize(getApplicationContext());
            LitePal.initialize(getApplicationContext());
        }

    }

    @Override
    protected int getAppAcentColor()
    {
        return ContextCompat.getColor(getBaseContext(), R.color.main_base_color);
    }

    @Override
    protected Map<HHLoadState, HHLoadViewInfo> getLoadViewInfo()
    {
        return null;
    }

}
