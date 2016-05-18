package org.chzz.mvp.base.utils;

import android.widget.Toast;

import org.chzz.mvp.App;


/**
 * 作者:copy 邮件:2499551993@qq.com
 * 创建时间:15/7/2 10:17
 * 描述:
 */
public class ToastUtil {

    private ToastUtil() {
    }

    public static void show(CharSequence text) {
        if (text.length() < 10) {
            Toast.makeText(App.getInstance(), text, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(App.getInstance(), text, Toast.LENGTH_LONG).show();
        }
    }

    public static void show( int resId) {
        show(App.getInstance().getString(resId));
    }

}