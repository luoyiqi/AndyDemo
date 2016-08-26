package com.andy.a.demo.domain;


import cn.bestkeep.android.util.ObjectUtil;

public class RecommendGoods {

    private String goodsId;
    private String goodsName;
    private String goodsCoverImg;
    private int status;//商品状态1：上架，4：即将上线
    private double goodsPrice;
    private double goodsMarketPrice;
    private int channelType;////商品渠道[1:国内商品，2：全球购，3：有糖自营，4：有糖众筹]

    public String getGoodsId() {
        return goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getGoodsCoverImg() {
        return goodsCoverImg;
    }

    public int getStatus() {
        return status;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public double getGoodsMarketPrice() {
        return goodsMarketPrice;
    }

    public int getChannelType() {
        return channelType;
    }

    public boolean isGlobal() {
        return channelType == 2;
    }

    public boolean isJJSX() {
        return status == 4;
    }


    @Override
    public int hashCode() {
        return ObjectUtil.hashCode(goodsId, super.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof RecommendGoods)) {
            return false;
        }
        return ObjectUtil.equals(goodsId, ((RecommendGoods) obj).goodsId);
    }
}
