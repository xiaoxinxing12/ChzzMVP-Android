package org.chzz.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.chzz.mvp.R;
import org.chzz.mvp.base.BaseActivity;
import org.chzz.mvp.base.utils.Md5StringUtils;
import org.chzz.mvp.base.utils.ToastUtil;
import org.chzz.mvp.ui.view.widget.TextInputEditText;
import org.chzz.mvp.model.LoginModel;
import org.chzz.mvp.presenter.LoginContract;
import org.chzz.mvp.presenter.LoginPresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================
 * 版权 ：闪现数据 版权所有 (c)   2016/3/24
 * 作者:copy   xiaoxinxing12@qq.com
 * 版本 ：1.0
 * 创建日期 ： 2016/3/24--17:09
 * 描述 ：
 * 修订历史 ：
 * ============================================================
 **/
public class LoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View {
    private Button mLogin;
    private TextInputEditText mUserName;
    private TextInputEditText mPassWord;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        mLogin = getViewById(R.id.but_submit);
        mUserName = getViewById(R.id.ti_userName);
        mPassWord = getViewById(R.id.ti_passWord);

    }

    @Override
    protected void setListener() {
        mLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_submit:
                login();
                break;
        }
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    private void login() {
        String password = Md5StringUtils.getMD5Str32byte(mPassWord.getText().toString().trim());
        Map<String, String> userLogin = new HashMap<>();
        userLogin.put("loginName", mUserName.getText().toString().trim());
        userLogin.put("password", password);
        showLoadingDialog();
        mPresenter.login(userLogin);
    }

    @Override
    public void loginSuccess() {
        dismissLoadingDialog();
        Intent _login = new Intent(this, MainActivity.class);
        startActivity(_login);
        //startActivityForResult(_login, 100);
        finish();

    }

    @Override
    public void showMsg(String msg) {
        //用户名不能为空
        mUserName.setError(msg);
        ToastUtil.show(msg);
        dismissLoadingDialog();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            finish();
            //System.exit(0);
        }
    }
}
