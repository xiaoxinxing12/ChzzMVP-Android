package org.chzz.mvp;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.chzz.mvp.base.utils.ConstantValues;
import org.chzz.mvp.engine.CookiesInterceptor;
import org.chzz.mvp.engine.Engine;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者:copy 邮件:2499551993@qq.com
 * 创建时间:15/6/21 下午10:13
 * 描述:
 */
public class App extends Application {
    private static App sInstance;
    private Engine mEngine;
    /**
     * 显示各批次科室参数
     */
    public static int hospitalId;//医院id
    public static String hospitalName;//医院id
    public static int Id;//医院id、实习生id
    public static int batchId;//批次id
    public static String title = "规培管理";
    public static String cookies = "";
    public static String leave_id = "";
    public static String mobilePhone;
    public static int managerTypeCode;
    public static String departmentId = "0";//科室id
    public static int majorFieldId;//科室id
    public static String medicalInternId = "1";//科室id

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        Stetho.initializeWithDefaults(this);
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new CookiesInterceptor(this.getApplicationContext()))
                .addNetworkInterceptor(new StethoInterceptor())
                .followRedirects(false)
                .build();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
        mEngine = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(ConstantValues.BASE_URL)
                .build().create(Engine.class);

    }

    public static App getInstance() {
        return sInstance;
    }

    public Engine getEngine() {
        return mEngine;
    }
}