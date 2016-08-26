package com.andy.a.demo.ui;


import com.andy.a.demo.domain.RecommendGoods;

import java.util.List;

import cn.bestkeep.android.view.ILoView;

public interface IGoodsView extends ILoView {

    void onLoadGoodsSuccess(List<RecommendGoods> goodsList);

    void onLoadGoodsFailure(String message);
}
