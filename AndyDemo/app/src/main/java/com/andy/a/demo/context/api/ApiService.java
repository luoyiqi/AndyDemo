package com.andy.a.demo.context.api;


import com.andy.a.demo.domain.BKDataPage;
import com.andy.a.demo.domain.JsonResponse;
import com.andy.a.demo.domain.RecommendGoods;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface ApiService {


    /*
    * 获取推荐商品列表
    * */
    @FormUrlEncoded
    @POST("getRecommendGoodsList")
    Observable<JsonResponse<BKDataPage<RecommendGoods>>> recommendGoodsList(@Field("currentPage") int currentPage);

}
