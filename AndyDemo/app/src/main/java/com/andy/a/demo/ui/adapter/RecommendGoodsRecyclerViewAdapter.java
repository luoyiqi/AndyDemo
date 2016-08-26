package com.andy.a.demo.ui.adapter;


import android.graphics.Paint;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andy.a.demo.R;
import com.andy.a.demo.domain.RecommendGoods;
import com.andy.a.demo.helper.ImageHelper;
import com.andy.a.demo.util.PriceUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import org.xutils.view.annotation.ViewInject;

import javax.inject.Inject;

import cn.bestkeep.android.internal.di.PerActivity;
import cn.bestkeep.android.ui.adapter.RecyclerViewAdapter;
import cn.bestkeep.android.ui.adapter.RecyclerViewHolder;
import cn.bestkeep.android.util.UIUtil;

@PerActivity
public class RecommendGoodsRecyclerViewAdapter extends RecyclerViewAdapter {

    private ImageHelper imageHelper;

    @Inject
    public RecommendGoodsRecyclerViewAdapter(ImageHelper imageHelper) {
        this.imageHelper = imageHelper;
    }

    @Override
    protected int getItemViewLayout(int viewType) {
        return R.layout.item_shop_cart_recommend_goods;
    }

    @Override
    protected RecyclerViewHolder buildViewHolder(View view, int viewType) {
        return new RecommendGoodsRecyclerViewHolder(view);
    }

    private class RecommendGoodsRecyclerViewHolder extends RecyclerViewHolder<RecommendGoods> {
        public RecommendGoodsRecyclerViewHolder(View itemView) {
            super(itemView);
        }

        @ViewInject(R.id.recommendGoodsItemContainer)
        private RelativeLayout recommendGoodsItemContainer;
        @ViewInject(R.id.globalImageView)
        private ImageView globalImageView;
        @ViewInject(R.id.jjsxImageView)
        private ImageView jjsxImageView;
        @ViewInject(R.id.goodsImageDraweeView)
        private SimpleDraweeView goodsImageDraweeView;
        @ViewInject(R.id.goodsNameTextView)
        private TextView goodsNameTextView;
        @ViewInject(R.id.goodsNameLayout2)
        private LinearLayout goodsNameLayout2;
        @ViewInject(R.id.goodsNameTextView2)
        private TextView goodsNameTextView2;
        @ViewInject(R.id.goodsPriceTextView)
        private TextView goodsPriceTextView;
        @ViewInject(R.id.vip_dgj_price)
        private TextView vip_dgj_price;

        @Override
        public void display() {
            int margin = UIUtil.dip2px(itemView.getContext(), 10);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) itemView.getLayoutParams();
            if (getLayoutPosition() % 2 == 0) {
                layoutParams.setMargins(margin, 0, margin / 2, margin);
            } else {
                layoutParams.setMargins(margin / 2, 0, margin, margin);
            }

            int imageWidth = UIUtil.dip2px(itemView.getContext(), 100);
            imageHelper.displayImage(goodsImageDraweeView, Uri.parse(data().getGoodsCoverImg()), imageWidth, imageWidth);
            UIUtil.setVisibleOrGone(globalImageView, data().isGlobal());
            UIUtil.setVisibleOrGone(jjsxImageView, data().isJJSX());

            goodsNameTextView.setText(data().getGoodsName());
            goodsNameLayout2.setVisibility(View.GONE);

            goodsPriceTextView.setText("￥" + PriceUtil.parsePrice(data().getGoodsPrice()));
            vip_dgj_price.setText("￥" + PriceUtil.parsePrice(data().getGoodsMarketPrice()));
            vip_dgj_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}
