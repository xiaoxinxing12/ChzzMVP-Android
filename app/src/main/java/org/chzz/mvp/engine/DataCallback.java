package org.chzz.mvp.engine;

import android.util.Log;

import org.chzz.mvp.base.BaseEntity;
import org.chzz.mvp.base.utils.GsonTools;
import org.chzz.mvp.base.utils.ToastUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by copy on 2016/4/23.
 */
public class DataCallback<T extends BaseEntity> implements Callback<T> {
    private OnDataListener mOnDataListener;
    private int code;
    private Class<T> clazz;
    private String mName;

    public DataCallback(OnDataListener baseListener) {
        this.mOnDataListener = baseListener;
    }

    public DataCallback(OnDataListener onDataListener, int code) {
        this.mOnDataListener = onDataListener;
        this.code = code;

    }

    public DataCallback(String name, OnDataListener onDataListener, int code) {
        this.mOnDataListener = onDataListener;
        this.code = code;
        this.mName = name;
    }


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        switch (response.code()) {
            case 200:
                this.mOnDataListener.result(response.body(), code);
                Log.i("json", GsonTools.createGsonString(response.body()));
                break;
            case 302:
                ToastUtil.show("找不到相应方法,请联系管理员.");
                break;
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.i("Throwable", mName + t.getLocalizedMessage());
        // ToastUtil.show(mName+t.getMessage());
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public interface OnDataListener {
        void result(BaseEntity model, int code);
    }
}
