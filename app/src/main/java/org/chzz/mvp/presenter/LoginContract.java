package org.chzz.mvp.presenter;


import org.chzz.mvp.base.BaseModel;
import org.chzz.mvp.base.BasePresenter;
import org.chzz.mvp.base.BaseView;
import org.chzz.mvp.model.bean.UserLoginEntity;

import java.util.Map;

import rx.Observable;

/**
 * Created by baixiaokang on 16/4/29.
 */
public interface LoginContract {
    interface Model extends BaseModel {
        Observable<UserLoginEntity> login(Map<String, String> params);


    }
    interface View extends BaseView {
        void loginSuccess();
        void showMsg(String msg);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void login(Map<String, String> params);

        @Override
        public void onStart() {
        }
    }
}
