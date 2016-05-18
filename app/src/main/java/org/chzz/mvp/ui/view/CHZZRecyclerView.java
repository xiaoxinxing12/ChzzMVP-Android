package org.chzz.mvp.ui.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.chzz.mvp.App;
import org.chzz.mvp.R;
import org.chzz.mvp.adapter.NormalRecyclerViewAdapter;
import org.chzz.mvp.base.BaseEntity;
import org.chzz.mvp.base.utils.ConstantValues;
import org.chzz.mvp.base.utils.ThreadUtil;
import org.chzz.mvp.base.utils.ToastUtil;
import org.chzz.mvp.ui.view.widget.Divider;

import org.chzz.adapter.CHZZOnRVItemClickListener;
import org.chzz.refresh.CHZZMoocStyleRefreshViewHolder;
import org.chzz.refresh.CHZZRefreshLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ============================================================
 * 版权 ：闪现数据 版权所有 (c)   2016/5/18
 * 作者:copy   xiaoxinxing12@qq.com
 * 版本 ：1.0
 * 创建日期 ： 2016/5/18--14:45
 * 描述 ：
 * 修订历史 ：
 * ============================================================
 **/
public class CHZZRecyclerView extends LinearLayout implements CHZZRefreshLayout.RefreshLayoutDelegate, CHZZOnRVItemClickListener {
    private Context mContext;
    @Bind(R.id.rl_recyclerView)
    public CHZZRefreshLayout mRefreshLayout;
    @Bind(R.id.rv_data)
    RecyclerView mDataRv;
    NormalRecyclerViewAdapter mAdapter;
    protected App mApp;
    private NormalRecyclerViewAdapter.IFillDataListener mIFillDataListener;
    private int mItemLayout;
    private View mHeaderView;
    private Boolean mLoadingMore = true;

    /**
     * @param context
     * @param itemLayout        item布局
     * @param loadingMore       是否加载更多
     * @param iFillDataListener 回调
     */
    public CHZZRecyclerView(Context context, int itemLayout, boolean loadingMore, NormalRecyclerViewAdapter.IFillDataListener iFillDataListener) {
        super(context);
        mContext = context;
        mApp = App.getInstance();
        mIFillDataListener = iFillDataListener;
        mItemLayout = itemLayout;
        mLoadingMore = loadingMore;
        init();
    }

    /**
     * @param context
     * @param itemLayout        item布局
     * @param loadingMore       是否加载更多
     * @param headerView        头部
     * @param iFillDataListener 回调
     */
    public CHZZRecyclerView(Context context, int itemLayout, boolean loadingMore, View headerView, NormalRecyclerViewAdapter.IFillDataListener iFillDataListener) {
        super(context);
        mContext = context;
        mApp = App.getInstance();
        mIFillDataListener = iFillDataListener;
        mItemLayout = itemLayout;
        mHeaderView = headerView;
        mLoadingMore = loadingMore;
        init();
    }

    private void init() {
        View layout = View.inflate(mContext, R.layout.common_list_recycler, null);
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.MATCH_PARENT));
        addView(layout);
        ButterKnife.bind(this, layout);
        initView();
    }

    private void initView() {
        //设置下拉刷新上拉加载更多代理
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setIsShowLoadingMoreView(mLoadingMore);
        mAdapter = new NormalRecyclerViewAdapter(mDataRv, mItemLayout, mIFillDataListener);
        //自定义头
        if (null != mHeaderView)
            mRefreshLayout.setCustomHeaderView(mHeaderView, true);
        //列表数据
        CHZZMoocStyleRefreshViewHolder leftRefreshViewHolder = new CHZZMoocStyleRefreshViewHolder(mApp, true);
        leftRefreshViewHolder.setSpringDistanceScale(2);
        //刷新图标
        leftRefreshViewHolder.setOriginalImage(R.mipmap.ic_refresh);
        //刷新头背景色
        leftRefreshViewHolder.setUltimateColor(R.color.white);
        //设置刷新头
        mRefreshLayout.setRefreshViewHolder(leftRefreshViewHolder);
        //item分割线
        mDataRv.addItemDecoration(new Divider(mApp));
        //布局管理器
        mDataRv.setLayoutManager(new GridLayoutManager(mApp, 1, GridLayoutManager.VERTICAL, false));
        mDataRv.setAdapter(mAdapter);
        mAdapter.setOnRVItemClickListener(this);

    }

    @Override
    public void onRefreshLayoutBeginRefreshing(CHZZRefreshLayout refreshLayout) {
        ThreadUtil.runInUIThread(new Runnable() {
            @Override
            public void run() {
                //  mRefreshLayout.endRefreshing();
                mIFillDataListener.getListData(1);
            }
        }, ConstantValues.LOADING_DURATION);
    }

    @Override
    public boolean onRefreshLayoutBeginLoadingMore(CHZZRefreshLayout refreshLayout) {
        if (mLoadingMore) {
            // 测试数据
            ThreadUtil.runInUIThread(new Runnable() {
                @Override
                public void run() {
                    mIFillDataListener.getListData(2);
                    // dismissLoadingDialog();
                }
            }, ConstantValues.LOADING_MORE);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onRVItemClick(ViewGroup parent, View itemView, int position) {
        mIFillDataListener.onRVItemClickListener(parent, itemView, position);
    }

    public void setData(int flag, List<BaseEntity> list) {
        switch (flag) {
            case 0:
                mAdapter.setDatas(list);
                break;
            case 1:
                mRefreshLayout.endRefreshing();
                //mAdapter.addNewDatas(list);
                mAdapter.setDatas(list);

                break;
            case 2:
                if (null == list || list.size() == 0)
                    ToastUtil.show("暂无更多数据");
                mRefreshLayout.endLoadingMore();
                mAdapter.addMoreDatas(list);

                break;
        }

    }

}
