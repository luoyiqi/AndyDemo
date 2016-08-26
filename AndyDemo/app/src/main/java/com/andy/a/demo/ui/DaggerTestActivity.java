package com.andy.a.demo.ui;


import android.content.Context;
import android.widget.Toast;

import com.andy.a.demo.di.components.DaggerGoodsComponent;
import com.andy.a.demo.di.components.GoodsComponent;
import com.andy.a.demo.domain.RecommendGoods;
import com.andy.a.demo.presenter.GoodsPresenter;
import com.andy.a.demo.ui.adapter.RecommendGoodsRecyclerViewAdapter;

import java.util.List;

import javax.inject.Inject;

import cn.bestkeep.android.util.Preconditions;


/*
*
* 这是使用依赖注入解耦MVP组件的例子
*
* *  ApiService和ImageHelper工具类可以通过Application中的静态方法来获取
*  -    ApiService :Retrofit联网的接口类
*  -    ImageHelper：使用Fresco加载图片的工具类
*
*   其实例由Application组件统一管理，生命周期伴随app生命周期
*   业务部分不需要关注他们怎么来
*
*
* */
public class DaggerTestActivity extends AbsRefreshActivity
        implements IGoodsView {

    @Inject
    GoodsPresenter presenter;
    @Inject
    RecommendGoodsRecyclerViewAdapter adapter;

    /*
    * 这里用注入的方式获取需要的组件实例
    * */
    @Override
    protected void init() {
        GoodsComponent goodsComponent = DaggerGoodsComponent.builder()
                .appComponent(getComponent())
                .build();
        goodsComponent.inject(this);
        Preconditions.checkNotNull(presenter, "presenter can not be null!");
        Preconditions.checkNotNull(adapter, "presenter can not be null!");
        presenter.setView(this);
    }

    @Override
    protected void setupList() {
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void loadData() {
        presenter.loadGoodsList();
    }

    @Override
    public void onLoadGoodsSuccess(List<RecommendGoods> goodsList) {
        onRefreshComplete();
        adapter.changeData(goodsList);
    }

    @Override
    public void onLoadGoodsFailure(String message) {
        onRefreshComplete();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isExist() {
        return !isFinishing();
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }
}
