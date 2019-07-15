package com.fenglangjuxu.apiServices;

import com.fenglangjuxu.base.client.ApiResult;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserApi {

    @POST("/login")
    @FormUrlEncoded
    Observable<ApiResult<String>> loginWithPassword(@Field("mobile") String mobile,
                                                    @Field("pwd") String pwd);
}
