package org.chzz.mvp.presenter;

import org.chzz.mvp.App;
import org.chzz.mvp.base.utils.LogUtil;
import org.chzz.mvp.base.utils.SharePrefUtil;
import org.chzz.mvp.model.bean.UserLoginEntity;

import java.util.Map;

/**
 * ============================================================
 * 版权 ：闪现数据 版权所有 (c)   2016/5/17
 * 作者:copy   xiaoxinxing12@qq.com
 * 版本 ：1.0
 * 创建日期 ： 2016/5/17--16:30
 * 描述 ：
 * 修订历史 ：
 * ============================================================
 **/
public class LoginPresenter extends LoginContract.Presenter {
    @Override
    public void login(Map<String, String> params) {
        mRxManage.add(mModel.login(params).subscribe(
                user -> {
                    processLogin(user);
                    LogUtil.i(user);
                },
                throwable -> {
                    mView.showMsg(throwable.getLocalizedMessage());
                }));

    }

    private void processLogin(UserLoginEntity bean) {
        if (null != bean.getMsg() && 0 == bean.getCode()) {
            UserLoginEntity.MsgEntity beanMsg = bean.getMsg();
            //医院ID
            App.hospitalId = beanMsg.getHospitalId();
            //角色Code 0 1 2 3
            App.managerTypeCode = beanMsg.getManagerTypeCode();
            //医院名称
            App.hospitalName = beanMsg.getHospitalName();
            //角色ID
            App.Id = beanMsg.getId();
            App.cookies = bean.getMsg().getCookie();
            SharePrefUtil.saveString(App.getInstance().getApplicationContext(), "cookies", beanMsg.getCookie());
            mView.loginSuccess();
        } else {
            mView.showMsg(bean.getDesc());
        }
    }
}
