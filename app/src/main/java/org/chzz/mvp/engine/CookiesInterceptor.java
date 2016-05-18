package org.chzz.mvp.engine;

import android.content.Context;
import android.util.Log;

import org.chzz.mvp.App;
import org.chzz.mvp.base.utils.CheckUtils;
import org.chzz.mvp.base.utils.SharePrefUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by copy on 2016/4/25.
 */
public class CookiesInterceptor implements Interceptor {
    private Context context;

    public CookiesInterceptor(Context context) {
        this.context = context;
    }

    //重写拦截方法，处理自定义的Cookies信息
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request compressedRequest = request.newBuilder()
                .header("Cookie", "JSESSIONID=" + SharePrefUtil.getString(App.getInstance().getApplicationContext(), "cookies", ""))
                .build();
        Response response = chain.proceed(compressedRequest);
        // CookieUtil.saveCookies(response.headers(), context);
        Log.i("header", request.url() + "");
        // Log.i("header", response.body()+"");
        if (!CheckUtils.checkEmpty(response.headers().get("Set-Cookie")))
            SharePrefUtil.saveString(App.getInstance().getApplicationContext(), "cookies", response.headers().get("Set-Cookie"));
        Log.i("cookie11", SharePrefUtil.getString(App.getInstance().getApplicationContext(), "cookies", ""));
        //if (null != response.body())
            //Log.i("json", GsonTools.createGsonString(response.body()));
        return response;
    }

}