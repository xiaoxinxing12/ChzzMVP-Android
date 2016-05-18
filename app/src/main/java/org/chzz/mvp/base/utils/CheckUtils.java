package org.chzz.mvp.base.utils;


import android.content.Context;

public class CheckUtils {

    /**
     * @param text
     * @return false 不为空空
     **/
    public static boolean checkEmpty(String text) {
        if (text == null || "".equals(text) || text.isEmpty() || text.length() <= 0) {
            return true;
        }
        return false;
    }

    public static boolean checkEmpty(String text, Context context, String msg) {
        if (text == null || "".equals(text) || text.isEmpty() || text.length() <= 0) {
            ToastUtils.showInfo(context, msg);
            return true;
        }
        return false;
    }


}
