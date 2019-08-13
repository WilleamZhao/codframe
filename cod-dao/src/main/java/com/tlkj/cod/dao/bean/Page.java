/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.dao.bean;


import com.tlkj.cod.dao.jdbc.Pagination;

import java.util.List;

/**
 * Desc 分页信息Model
 *
 * @author sourcod
 * @version 1.0
 * @className Page
 * @date 2018/10/30 上午12:39
 */
public class Page<T> {

    public Page(){

    }

    public Page(T T){
        this.data = T;
    }

    public Page(T T, int page, int pageSize, int total){
        this.data = T;
        this.p = new P(page, pageSize, total);
    }

    public Page(T T, Pagination pagination){
        this.data = T;
        this.p = new P(pagination);
    }

    public Page(Pagination pagination){
        this.data = (T) pagination.getData();
        this.p = new P(pagination);
    }

    public Page(int page, int pageSize, int total, int currentTotal){
        this.p = new P(page, pageSize, total, currentTotal);
    }

    /**
     * 分页信息
     */
    private P p;

    /**
     * 返回数据
     */
    private T data;

    public P getP() {
        return p;
    }

    public void setP(P p) {
        this.p = p;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static boolean isData(Page page){
        if (page == null){
            return false;
        }
        if (page.p.total == 0){
            return false;
        }
        return true;
    }

    public class P{

        P(Pagination pagination){
            this.page = pagination.page;
            this.pageSize = pagination.perPage;
            this.total = pagination.total;
            this.currentTotal = pagination.getData().size();
        }

        P(int page, int pageSize, int total){
            this.page = page;
            this.pageSize = pageSize;
            this.total = total;
            this.currentTotal = ((List) data).size();
        }

        P(int page, int pageSize, int total, int currentTotal){
            this.page = page;
            this.pageSize = pageSize;
            this.total = total;
            this.currentTotal = currentTotal;
        }

        /**
         * 当前第几页
         */
        private int page;

        /**
         * 每页显示条数
         */
        private int pageSize;

        /**
         * 总条数
         */
        private int total;

        /**
         * 当前页条数
         */
        private int currentTotal;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getCurrentTotal() {
            return currentTotal;
        }

        public void setCurrentTotal(int currentTotal) {
            this.currentTotal = currentTotal;
        }
    }
}
