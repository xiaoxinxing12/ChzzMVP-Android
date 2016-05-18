package org.chzz.mvp.engine;


import org.chzz.mvp.model.bean.BannerModel;
import org.chzz.mvp.model.bean.UserLoginEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;


//作者:copy 邮件:2499551993@qq.com
//创建时间:15/9/17 下午2:01
//描述:

public interface Engine {
    String comeFrom = "Android";


    //1、用户登录接口
    @FormUrlEncoded
    @POST("system/user/login")
    Observable<UserLoginEntity> userLogin(@FieldMap Map<String, String> params);


    @GET("banner/api/5item.json")
    Call<BannerModel> getBannerModel();
}