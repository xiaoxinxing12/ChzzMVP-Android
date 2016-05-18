package org.chzz.mvp.model;

import org.chzz.mvp.base.utils.RxSchedulers;
import org.chzz.mvp.model.bean.UserLoginEntity;
import org.chzz.mvp.presenter.LoginContract;

import java.util.Map;

import rx.Observable;

/**
 * ============================================================
 * 版权 ：闪现数据 版权所有 (c)   2016/5/17
 * 作者:copy   xiaoxinxing12@qq.com
 * 版本 ：1.0
 * 创建日期 ： 2016/5/17--16:36
 * 描述 ：
 * 修订历史 ：
 * ============================================================
 **/
public class LoginModel implements LoginContract.Model {
    @Override
    public Observable<UserLoginEntity> login(Map<String, String> params) {
        return mEngine
                .userLogin(params)
                .compose(RxSchedulers.io_main());
    }
}
