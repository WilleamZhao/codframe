package com.tlkj.cod.user.model.entity;

import com.tlkj.cod.common.CodCommonModelConvert;

import java.io.Serializable;

/**
 * Desc 通用用户模块
 *
 * @author sourcod
 * @version 1.0
 * @className CodCommonUserDo
 * @date 2019/3/19 7:02 PM
 */
public class CodCommonUserDo extends CodCommonModelConvert implements Serializable, Cloneable {

    public final static String TABLE_NAME = "cod_common_user";

    /**
     * 主键
     */
    private String id;

    /**
     * openid
     */
    private String openid;

    /**
     * unionid
     */
    private String unionid;

    /**
     * 微博id
     */
    private String weibo_id;

    /**
     * qqid
     */
    private String qq_id;

    /**
     * facebookId
     */
    private String facebook_id;

    /**
     * 推特Id
     */
    private String twitter_id;

    /**
     * 支付宝Id
     */
    private String alipay_id;

    /**
     * githubId
     */
    private String github_id;

    /**
     * other1 id
     */
    private String other1_id;

    /**
     * other2 id
     */
    private String other2_id;

    /**
     * other3 id
     */
    private String other3_id;

    /**
     * 电话号
     */
    private String phone;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 姓名
     */
    private String real_name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private String age;

    /**
     * 身份证
     */
    private String id_card;

    /**
     * 头像
     */
    private String head;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 最后一次登录时间
     */
    private String login_time;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态
     * -1: 删除
     * 0: 禁用
     * 1: 启用
     */
    private String state;

    /**
     * 虚拟币
     */
    private String coin;

    /**
     * 登录名
     */
    private String loginname;

    /**
     * 验证码
     */
    private String verify_code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getWeibo_id() {
        return weibo_id;
    }

    public void setWeibo_id(String weibo_id) {
        this.weibo_id = weibo_id;
    }

    public String getQq_id() {
        return qq_id;
    }

    public void setQq_id(String qq_id) {
        this.qq_id = qq_id;
    }

    public String getFacebook_id() {
        return facebook_id;
    }

    public void setFacebook_id(String facebook_id) {
        this.facebook_id = facebook_id;
    }

    public String getTwitter_id() {
        return twitter_id;
    }

    public void setTwitter_id(String twitter_id) {
        this.twitter_id = twitter_id;
    }

    public String getAlipay_id() {
        return alipay_id;
    }

    public void setAlipay_id(String alipay_id) {
        this.alipay_id = alipay_id;
    }

    public String getGithub_id() {
        return github_id;
    }

    public void setGithub_id(String github_id) {
        this.github_id = github_id;
    }

    public String getOther1_id() {
        return other1_id;
    }

    public void setOther1_id(String other1_id) {
        this.other1_id = other1_id;
    }

    public String getOther2_id() {
        return other2_id;
    }

    public void setOther2_id(String other2_id) {
        this.other2_id = other2_id;
    }

    public String getOther3_id() {
        return other3_id;
    }

    public void setOther3_id(String other3_id) {
        this.other3_id = other3_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getLogin_time() {
        return login_time;
    }

    public void setLogin_time(String login_time) {
        this.login_time = login_time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getVerify_code() {
        return verify_code;
    }

    public void setVerify_code(String verify_code) {
        this.verify_code = verify_code;
    }
}
