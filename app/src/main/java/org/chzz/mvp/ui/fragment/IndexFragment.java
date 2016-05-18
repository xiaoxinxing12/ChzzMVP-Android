package org.chzz.mvp.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import org.chzz.mvp.R;
import org.chzz.mvp.adapter.NormalRecyclerViewAdapter;
import org.chzz.mvp.base.BaseEntity;
import org.chzz.mvp.base.BaseFragment;
import org.chzz.mvp.engine.DataEngine;
import org.chzz.mvp.ui.view.CHZZRecyclerView;

import org.chzz.adapter.CHZZViewHolderHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * 版权 ：闪现数据 版权所有 (c)   2016/5/18
 * 作者:copy   xiaoxinxing12@qq.com
 * 版本 ：1.0
 * 创建日期 ： 2016/5/18--11:05
 * 描述 ：
 * 修订历史 ：
 * ============================================================
 **/
public class IndexFragment extends BaseFragment implements NormalRecyclerViewAdapter.IFillDataListener {
    private CHZZRecyclerView mCHZZRecyclerView;

    public static IndexFragment newInstance() {
        Bundle arguments = new Bundle();
        IndexFragment fragment = new IndexFragment();
        fragment.setArguments(arguments);
        return fragment;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        //自定头
        View headerView = DataEngine.getCustomHeaderView(mApp);
        mCHZZRecyclerView = new CHZZRecyclerView(getActivity(), R.layout.item_normal,true, headerView, this);
        mContentView = mCHZZRecyclerView;

        List<BaseEntity> list = new ArrayList<>();
        list.add(new BaseEntity());
        list.add(new BaseEntity());
        list.add(new BaseEntity());
        mCHZZRecyclerView.setData(0, list);
    }

    @Override
    protected void setListener() {

    }


    @Override
    public void setFillDataListener(CHZZViewHolderHelper bgaViewHolderHelper, int i, BaseEntity t) {

    }

    @Override
    public void onRVItemClickListener(ViewGroup parent, View itemView, int position) {

    }

    @Override
    public void getListData(int flag) {
        List<BaseEntity> list = new ArrayList<>();
        list.add(new BaseEntity());
        list.add(new BaseEntity());
        list.add(new BaseEntity());
        mCHZZRecyclerView.setData(flag, list);
    }
}
