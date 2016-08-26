package com.andy.a.demo.respository.impl;


import com.andy.a.demo.context.api.ApiService;
import com.andy.a.demo.domain.BKDataPage;
import com.andy.a.demo.domain.JsonResponse;
import com.andy.a.demo.domain.RecommendGoods;
import com.andy.a.demo.respository.IGoodsRepository;

import java.util.List;

import javax.inject.Inject;

import cn.bestkeep.android.internal.di.PerActivity;
import cn.bestkeep.android.util.Preconditions;
import rx.Observable;
import rx.functions.Func1;


/*
*
*
* 数据仓库,即Model，对上层Presenter屏蔽数据来源差异（网络or数据库or偏好or文件）
* 方便以后增加缓存，扩展性
*
* */
@PerActivity
public class GoodsRepository implements IGoodsRepository {

    private ApiService apiService;

    @Inject
    public GoodsRepository(ApiService apiService) {
        this.apiService = Preconditions.checkNotNull(apiService, "apiService is null!");
    }

    /*
    *
    * 这里可以去判断应该从网络还是数据库拉取数据，包装成Observable返回给调用处
    * 可以用Rx一些操作符很方便的处理数据
    * 也可以在更外层或者基类中统一处理常见错误
    *
    * （这里只调用联网方式获取数据）
    * */
    @Override
    public Observable<List<RecommendGoods>> loadGoodsList(int currentPage) {
        return apiService.recommendGoodsList(currentPage)
                .flatMap(response -> {
                    if (response == null) {//响应为空 - 网络异常
                        return Observable.error(new Exception("network error."));
                    }
                    if (!response.isSuccess()) {//响应异常 - 打出错误信息或者根据错误码不同处理

                        if ("025".equals(response.getCode()) || "023".equals(response.getCode())) {
                            //登录失效，请重新登录
                        }

                        return Observable.error(new Exception(response.getMsg()));
                    }

                    return Observable.just(response.getData().getList());//获取返回数据
                });
    }
}
