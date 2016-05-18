package org.chzz.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import org.chzz.mvp.base.BaseEntity;

import org.chzz.adapter.CHZZRecyclerViewAdapter;
import org.chzz.adapter.CHZZViewHolderHelper;

/**
 * ============================================================
 * 版权 ：闪现数据 版权所有 (c)   2016/3/21
 * 作者:copy
 * 版本 ：1.0
 * 创建日期 ： 2016/3/21--16:45
 * 描述 ：通用的RecyclerViewAdapter
 * 修订历史 ：
 * ============================================================
 **/
public class NormalRecyclerViewAdapter<T extends BaseEntity> extends CHZZRecyclerViewAdapter<T> {
    private Context context;
    private IFillDataListener iFillDataListener;

    public NormalRecyclerViewAdapter(RecyclerView recyclerView, int itemLayoutId, IFillDataListener fillDataListener) {
        super(recyclerView, itemLayoutId);
        this.iFillDataListener = fillDataListener;
    }

    @Override
    protected void setItemChildListener(CHZZViewHolderHelper viewHolderHelper) {
        super.setItemChildListener(viewHolderHelper);
    }

    @Override
    protected void fillData(CHZZViewHolderHelper chzzViewHolderHelper, int i, T t) {
        iFillDataListener.setFillDataListener(chzzViewHolderHelper, i, t);
    }

    public interface IFillDataListener {
        /**
         * 给item设置数据
         *
         * @param bgaViewHolderHelper
         * @param i                   第几项
         * @param
         */
        public void setFillDataListener(CHZZViewHolderHelper bgaViewHolderHelper, int i, BaseEntity t);

        public void onRVItemClickListener(ViewGroup parent, View itemView, int position);

        public void getListData(int flag);
    }

}
