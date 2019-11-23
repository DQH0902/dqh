package com.edu.huatec.httprequest.http.service;

import com.edu.huatec.httprequest.http.entity.GoodsEntity;
import com.edu.huatec.httprequest.http.entity.HttpResult;

import java.util.List;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface GoodsService {


    @FormUrlEncoded
    @POST("goods/find")
    Observable<HttpResult<List<GoodsEntity>>> listByKeyworids
            (@Field("input") String keywords);

    @GET("goods/cat/{catId}")
    Observable<HttpResult<List<GoodsEntity>>> list(
            @Path("catId") int catId
    );
}