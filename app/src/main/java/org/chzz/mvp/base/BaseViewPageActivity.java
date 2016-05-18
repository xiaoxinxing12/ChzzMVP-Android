package org.chzz.mvp.base;

import android.support.v4.view.ViewPager;

import org.chzz.tablayout.listener.OnTabSelectListener;

/**
 * ============================================================
 * 版权 ：闪现数据 版权所有 (c)   2016/4/21
 * 作者:copy   xiaoxinxing12@qq.com
 * 版本 ：1.0
 * 创建日期 ： 2016/4/21--17:29
 * 描述 ：为mainActivity省几行代码
 * 修订历史 ：
 * ============================================================
 **/
public abstract class BaseViewPageActivity<T extends BasePresenter, E extends BaseModel> extends BaseActivity implements OnTabSelectListener, ViewPager.OnPageChangeListener {
    @Override
    public void onTabSelect(int position) {

    }

    @Override
    public void onTabReselect(int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
