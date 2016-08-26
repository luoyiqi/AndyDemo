package com.andy.a.demo.util;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by Administrator on 2015/11/20.
 */
public class PriceUtil {
    public static final String RMB = "￥";

    public static String parsePrice(double price) {

        return String.valueOf(new BigDecimal(price).setScale(2, RoundingMode.HALF_UP));

    }

    //保留两位小数点
    public static String parsePriceTwo(double price) {
        //  DecimalFormat formater = new DecimalFormat("#0.##");

        //double d = 3.1415926;

        //  String result = String .format("%.2f");
        //   DecimalFormat fnum   =   new   DecimalFormat("##0.00");
        // return fnum.format(data);

        DecimalFormat formater = new DecimalFormat();
        formater.setMaximumFractionDigits(2);
        formater.setGroupingSize(0);
        formater.setRoundingMode(RoundingMode.FLOOR);
        System.out.println(formater.format(price));
        return formater.format(price);
    }


    /*
    * 取整数部分 - 2行4列部分
    * */
    public static String getCutPrice(String price) {
        if (TextUtils.isEmpty(price)) {
            return "";
        }
        int intResult = (int) Double.parseDouble(price.replace(RMB, ""));
        return RMB + String.valueOf(intResult);
    }

    /*
    *
    * 小于1000直接返回 - 其他
    * 大于1000整数部分
    *
    * */
    public static String getIntPrice(String price) {
        if (TextUtils.isEmpty(price)) {
            return "";
        }
        double doublePrice = Double.parseDouble(price.replace(RMB, ""));
        if (doublePrice < 1000) {
            return price;
        }
        int intResult = (int) doublePrice;
        return RMB + String.valueOf(intResult);
    }
}
