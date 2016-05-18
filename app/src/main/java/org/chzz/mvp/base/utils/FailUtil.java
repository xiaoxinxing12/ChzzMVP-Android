package org.chzz.mvp.base.utils;

import android.content.Context;

import org.chzz.mvp.base.BaseEntity;


/**
 * ============================================================
 * 版权 ：闪现数据 版权所有 (c)   2016/2/26
 * 作者:copy
 * 版本 ：1.0
 * 创建日期 ： 2016/2/26--13:56
 * 描述 ： 失败时的操作
 * 修订历史 ：
 * ============================================================
 **/
public class FailUtil {

    public static boolean checkResult(Context context, String results) {
        boolean result = false;
        if (CheckUtils.checkEmpty(results)) {
            //连接服务器失败
            ToastUtils.showInfo(context, ConstantValues.FAILUREMSG);
            result = true;
        } else {
            BaseEntity baseModel = GsonTools.jsonToBean(results, BaseEntity.class);

        }
        return result;
    }

}
