package com.github.swordjian.stoolbar.SStatusBar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import static android.os.Build.HOST;

/**
 * @author SwordJian
 */
public class SStatusBar {

    private static SStatusBar mInstance;

    public static SStatusBar getInstance() {
        if (mInstance == null) {
            synchronized (SStatusBar.class) {
                mInstance = new SStatusBar();
            }
        }
        return mInstance;
    }

    @TargetApi(21)
    public void setTranslucentStatus(Activity act) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (Build.MANUFACTURER.equalsIgnoreCase("HUAWEI"))
                {
                    setTranslucentStatusAPI19(act);
                } else {
                    setTranslucentStatusAPI21(act);
                }
            } else {
                setTranslucentStatusAPI19(act);
            }
        }
    }


//主板:PLK-TL01H
//    系统启动程序版本号:unknown
//    系统定制商:HONOR
//    cpu指令集:arm64-v8a
//            cpu指令集2
//    设置参数:HWPLK
//    显示屏参数:PLK-TL01HC01B211
//    硬件识别码:HONOR/PLK-TL01H/HWPLK:5.0.2/HONORPLK-TL01H/C01B211:user/release-keys
//    硬件名称:hi3635
//    HOST:huawei-desktop
//    修订版本列表:HONORPLK-TL01H
//    硬件制造商:HUAWEI
//    版本:PLK-TL01H
//    硬件序列号:A2JDU16429009693
//    手机制造商:PLK-TL01H
//    描述Build的标签:release-keys
//    TIME:1460804799000
//    builder类型:user
//    USER:huawei

    private String getDeviceInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("主板:" + Build.BOARD+ "\n");
        sb.append(
                "系统启动程序版本号:" + Build.BOOTLOADER+ "\n");
        sb.append(
                "系统定制商:" + Build.BRAND+ "\n");
        sb.append(
                "cpu指令集:" + Build.CPU_ABI+ "\n");
        sb.append(
                "cpu指令集2" + Build.CPU_ABI2+ "\n");
        sb.append(
                "设置参数:" + Build.DEVICE+ "\n");
        sb.append(
                "显示屏参数:" + Build.DISPLAY+ "\n"); //mk_huashan-userdebug 7.1 NDE63X 55d35c7b3b test-keys
//        sb.append(
//                "无线电固件版本:" + Build.getRadioVersion());
        sb.append(
                "硬件识别码:" + Build.FINGERPRINT+ "\n");
        sb.append(
                "硬件名称:" + Build.HARDWARE+ "\n");
        sb.append(
                "HOST:" + HOST+ "\n");
        sb.append(
                "修订版本列表:" + Build.ID+ "\n");
        sb.append(
                "硬件制造商:" + Build.MANUFACTURER + "\n");
        sb.append(
                "版本:" + Build.MODEL+ "\n");
        sb.append(
                "硬件序列号:" + Build.SERIAL+ "\n");
        sb.append(
                "手机制造商:" + Build.PRODUCT+ "\n");
        sb.append(
                "描述Build的标签:" + Build.TAGS+ "\n");
        sb.append(
                "TIME:" + Build.TIME+ "\n");
        sb.append(
                "builder类型:" + Build.TYPE+ "\n");
        sb.append(
                "USER:" + Build.USER+ "\n");
        return sb.toString();
    }

    @TargetApi(21)
    public void setTranslucentStatusAPI21(Activity act) {
        Window window = act.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    public void setTranslucentStatusAPI19(Activity act) {
        Window window = act.getWindow();
        WindowManager.LayoutParams winParams = window.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        winParams.flags |= bits;
        window.setAttributes(winParams);
    }

    @TargetApi(21)
    public void setTranslucentStatus(Activity act, View status_bar) {
        setTranslucentStatus(act);
        setStatusBarViewHeight(status_bar);
    }

    public void setStatusBarViewHeight(View status_bar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (status_bar != null) {
                status_bar.getLayoutParams().height = getStatusBarHeight();
            }
        }
    }

    public int getStatusBarHeight() {
        return Resources.getSystem().getDimensionPixelSize(
                Resources.getSystem().getIdentifier("status_bar_height",
                        "dimen", "android"));
    }

}
