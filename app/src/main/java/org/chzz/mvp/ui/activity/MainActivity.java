package org.chzz.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.chzz.mvp.App;
import org.chzz.mvp.R;
import org.chzz.mvp.adapter.NormalViewPageAdapter;
import org.chzz.mvp.base.BaseViewPageActivity;
import org.chzz.mvp.model.LoginModel;
import org.chzz.mvp.model.bean.TabEntity;
import org.chzz.mvp.ui.view.widget.MyViewpager;
import org.chzz.mvp.presenter.LoginPresenter;

import org.chzz.tablayout.CommonTabLayout;
import org.chzz.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

/**
 * ============================================================
 * 版权 ：闪现数据 版权所有 (c)   2016/5/16
 * 作者:copy   xiaoxinxing12@qq.com
 * 版本 ：1.0
 * 创建日期 ： 2016/5/16--9:50
 * 描述 ：
 * 修订历史 ：
 * ============================================================
 **/
public class MainActivity extends BaseViewPageActivity<LoginPresenter, LoginModel> {
    //标题
    private String[] mTitles = {"首页", "考核", "管理", "我的"};
    //默认图标
    private int[] mIconUnselectIds = {R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect, R.mipmap.ic_tab_category_normal, R.mipmap.tab_contact_unselect};
    //选中图标
    private int[] mIconSelectIds = {R.mipmap.tab_home_select, R.mipmap.tab_speech_select, R.mipmap.ic_tab_category_selected, R.mipmap.tab_contact_select};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    //再按一次退出程序
    private long exitTime;
    //菜单
    private CommonTabLayout mTabLayout;
    //内容
    private MyViewpager mViewPage;

    private NormalViewPageAdapter mNormalViewPageAdapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        mTabLayout = (CommonTabLayout) findViewById(R.id.tl_menu);
        mViewPage = (MyViewpager) findViewById(R.id.vp_content);
        mTitle = (TextView) findViewById(R.id.tv_title);
        mToolbarTop = (LinearLayout) findViewById(R.id.toolbar_top);
        if (setTranslucentStatus)
            mToolbarTop.setVisibility(View.VISIBLE);
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mNormalViewPageAdapter = new NormalViewPageAdapter(getSupportFragmentManager(), mTitles);
    }

    @Override
    protected void setListener() {
        mViewPage.setAdapter(mNormalViewPageAdapter);
        mTabLayout.setTabData(mTabEntities);
        mTabLayout.setOnTabSelectListener(this);
        mViewPage.addOnPageChangeListener(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mViewPage.setScrollble(true);
        setTitles(0);
        mToolbar.setOnMenuItemClickListener(onMenuItemClick);
        setMenu();
    }

    @Override
    public void initPresenter() {

    }


    @Override
    public void onTabSelect(int position) {
        setTitles(position);
        mViewPage.setCurrentItem(position);
    }

    @Override
    public void onPageSelected(int position) {
        mTabLayout.setCurrentTab(position);
        setTitles(position);
    }

    /**
     * 设置标题
     *
     * @param position
     */
    private void setTitles(int position) {
        mToolbar.setLogo(null);
        mTitle.setText(position == 3 ? null : mTitles[position]);
    }

    /**
     *
     */
    private void setMenu() {
        onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String msg = "";
                switch (item.getItemId()) {
                    case R.id.action_edit:
                        msg += "Click edit";
                        break;
                    case R.id.action_share:
                        msg += "Click share";
                        break;
                }

                if (!msg.equals("")) {
                    Toast.makeText(App.getInstance(), msg, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        };
    }

    /**
     * 记录返回事件
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再次点击即可退出", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
                return true;
            } else if ((System.currentTimeMillis() - exitTime) <= 2000) {
                /**
                 *  1. 任务管理器方法
                 首先要说明该方法运行在Android 1.5 API Level为3以上才可以，同时需要权限
                 <uses-permission android:name=\"android.permission.RESTART_PACKAGES\"></uses-permission>
                 */
                // ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                //ActivityManager am= (ActivityManager) getApplication().getSystemService(Context.ACTIVITY_SERVICE);
                //am.restartPackage(getPackageName());

                /**
                 * 1. Dalvik VM的本地方法
                 * 获取PID
                 */
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {

    }
}
