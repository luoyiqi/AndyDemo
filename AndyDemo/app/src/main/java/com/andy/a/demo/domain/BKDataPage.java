package com.andy.a.demo.domain;


import java.util.List;

public class BKDataPage<T> {

    private int totalPage;
    private int currentPage;
    private List<T> list;

    public int getTotalPage() {
        return totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public List<T> getList() {
        return list;
    }
}
