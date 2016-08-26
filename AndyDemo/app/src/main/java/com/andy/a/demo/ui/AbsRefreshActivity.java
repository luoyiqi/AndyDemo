package com.andy.a.demo.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.andy.a.demo.R;
import com.andy.a.demo.context.AppContext;
import com.andy.a.demo.di.components.AppComponent;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.bestkeep.android.internal.di.HasComponent;

public abstract class AbsRefreshActivity extends AppCompatActivity
        implements HasComponent<AppComponent> {

    @ViewInject(R.id.swipeRefreshLayout)
    protected SwipeRefreshLayout swipeRefreshLayout;
    @ViewInject(R.id.recyclerView)
    protected RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs_refresh);
        x.view().inject(this);

        init();
        initView();
        setupList();
        loadData();
    }

    protected void initView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimaryDark));
        swipeRefreshLayout.setOnRefreshListener(() -> loadData());
    }

    protected void onRefreshComplete() {
        swipeRefreshLayout.setRefreshing(false);
    }

    protected abstract void init();

    protected abstract void setupList();

    protected abstract void loadData();

    @Override
    public AppComponent getComponent() {
        return AppContext.from(this).getAppComponent();
    }
}
