/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 */
package com.tlkj.cod.model.business.jx.dto;


/**
 * codFrame auto create
 * Dto
 */
public class JxUserDto {

    /**
     * 主键
     */
    private String id;

    /**
     * qqid
     */
    private String qqId;

    /**
     * 微博id
     */
    private String weiboId;

    /**
     * openid
     */
    private String openid;

    /**
     * unionid
     */
    private String unionid;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮件地址
     */
    private String email;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像地址
     */
    private String headUrl;

    /**
     * 精享币
     */
    private String coin;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getQqId(){
        return this.qqId;
    }

    public void setQqId(String qqId){
        this.qqId = qqId;
    }

    public String getWeiboId(){
        return this.weiboId;
    }

    public void setWeiboId(String weiboId){
        this.weiboId = weiboId;
    }

    public String getOpenid(){
        return this.openid;
    }

    public void setOpenid(String openid){
        this.openid = openid;
    }

    public String getUnionid(){
        return this.unionid;
    }

    public void setUnionid(String unionid){
        this.unionid = unionid;
    }

    public String getPhone(){
        return this.phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getNickname(){
        return this.nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getHeadUrl(){
        return this.headUrl;
    }

    public void setHeadUrl(String headUrl){
        this.headUrl = headUrl;
    }

    public String getCoin(){
        return this.coin;
    }

    public void setCoin(String coin){
        this.coin = coin;
    }

    public String getCreateTime(){
        return this.createTime;
    }

    public void setCreateTime(String createTime){
        this.createTime = createTime;
    }

    public String getUpdateTime(){
        return this.updateTime;
    }

    public void setUpdateTime(String updateTime){
        this.updateTime = updateTime;
    }
}