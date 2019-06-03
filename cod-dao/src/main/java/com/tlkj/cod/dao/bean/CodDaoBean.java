package com.tlkj.cod.dao.bean;

import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Updater;

/**
 * Desc codDao
 *
 * @author sourcod
 * @version 1.0
 * @className CodDaoBean
 * @date 2019/5/17 3:24 PM
 */
public class CodDaoBean {

    private Finder finder;

    private Updater updater;

    public Finder getFinder() {
        return finder;
    }

    public void setFinder(Finder finder) {
        this.finder = finder;
    }

    public Updater getUpdater() {
        return updater;
    }

    public void setUpdater(Updater updater) {
        this.updater = updater;
    }
}
