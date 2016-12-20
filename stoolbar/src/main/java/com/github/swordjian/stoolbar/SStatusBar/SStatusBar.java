package com.github.swordjian.stoolbar.SStatusBar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

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
                if (Build.MANUFACTURER.equalsIgnoreCase("HUAWEI")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        setTranslucentStatusAPI21(act);
                    } else {
                        setTranslucentStatusAPI19(act);
                    }
                } else {
                    setTranslucentStatusAPI21(act);
                }
            } else {
                setTranslucentStatusAPI19(act);
            }
        }
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
