package com.andy.a.demo.ui;


import android.content.Context;
import android.widget.Toast;

import com.andy.a.demo.domain.RecommendGoods;
import com.andy.a.demo.presenter.GoodsPresenter;
import com.andy.a.demo.respository.impl.GoodsRepository;
import com.andy.a.demo.ui.adapter.RecommendGoodsRecyclerViewAdapter;

import java.util.List;


/*
*
* 这是手动new出需要的组件实例的例子
*
*  ApiService和ImageHelper工具类可以通过Application中的静态方法来获取
*  -    ApiService :Retrofit联网的接口类
*  -    ImageHelper：使用Fresco加载图片的工具类
*
*   其实例由Application组件统一管理，生命周期伴随app生命周期
*   业务部分不需要关注他们怎么来
*
* */
public class NormalTestActivity extends AbsRefreshActivity
        implements IGoodsView {

    GoodsPresenter presenter;
    RecommendGoodsRecyclerViewAdapter adapter;

    @Override
    protected void init() {
        //手动new出Presenter和adapter
        //其中创建Presenter 需要一个Repository，new出
        GoodsRepository repository = new GoodsRepository(getComponent().apiService());
        presenter = new GoodsPresenter(repository);
        adapter = new RecommendGoodsRecyclerViewAdapter(getComponent().imageHelper());
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
