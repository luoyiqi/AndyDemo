package com.andy.a.demo.presenter;


import com.andy.a.demo.domain.RecommendGoods;
import com.andy.a.demo.respository.impl.GoodsRepository;
import com.andy.a.demo.ui.IGoodsView;


import javax.inject.Inject;

import cn.bestkeep.android.internal.di.PerActivity;
import cn.bestkeep.android.presenter.Presenter;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/*
*
* 获取数据的方式在仓库层
* Presenter只负责调用，返回
*
* */
@PerActivity
public class GoodsPresenter implements Presenter {

    private GoodsRepository repository;
    private Subscription subscription;
    private IGoodsView view;
    private int page = 1;

    @Inject
    public GoodsPresenter(GoodsRepository repository) {
        this.repository = repository;
    }

    public void setView(IGoodsView view) {
        this.view = view;
    }

    public void loadGoodsList() {
        subscription = repository.loadGoodsList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(goodsList -> {
                    if (view == null || !view.isExist()) {
                        return;
                    }
                    view.onLoadGoodsSuccess(goodsList);
                }, throwable -> {
                    if (view == null || !view.isExist()) {
                        return;
                    }
                    view.onLoadGoodsFailure(throwable.getMessage());
                });
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        view = null;
    }
}
