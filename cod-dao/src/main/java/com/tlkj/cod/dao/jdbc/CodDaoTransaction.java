package com.tlkj.cod.dao.jdbc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Desc cod-dao 事物管理
 *
 * @author sourcod
 * @version 1.0
 * @className CodDaoTransaction
 * @date 2019/6/18 9:36 PM
 */
public class CodDaoTransaction {

    /**
     * 更新操作list
     */
    private List<Updater.Update> updaterList;

    /**
     * 开始事物
     */
    public void add(Updater.Update... updates){
        updaterList.addAll(Arrays.stream(updates).map(Updater.Update::closeAutoCommit).collect(Collectors.toList()));
    }

    /**
     * 提交事物
     */
    public void commit(){
        for (Updater.Update update : updaterList){
            try {
                update.update();
                update.commit();
            } catch (RuntimeException e){
                this.rollback();
            }
        }
        close();
    }

    /**
     * 事物回滚
     */
    public void rollback(){
        for (Updater.Update update : updaterList){
            update.rollback();
        }
        close();
    }

    /**
     * 关闭连接
     */
    private void close(){
        for (Updater.Update update : updaterList){
            update.close();
        }
    }
}
