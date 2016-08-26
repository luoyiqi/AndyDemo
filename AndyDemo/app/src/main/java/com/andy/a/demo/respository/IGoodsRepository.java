package com.andy.a.demo.respository;


import com.andy.a.demo.domain.RecommendGoods;

import java.util.List;

import rx.Observable;

public interface IGoodsRepository {

    Observable<List<RecommendGoods>> loadGoodsList(int currentPage);
}
