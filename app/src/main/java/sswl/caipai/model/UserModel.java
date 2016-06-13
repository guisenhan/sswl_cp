package sswl.caipai.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
public class UserModel implements Serializable{

    public UserModel() {
        ;
    }

    /*
        * icon": null,
            "area_id": null,
            "sex": null,
            "nickname": null,
            "province_id": null,
            "reg_from": 3,
            "score": null,
            "userid": 5,
            "state": 1,
            "lv": null,
            "job": null,
            "city": null,
            "city_id": null,
            "area": null,
            "login_time": null,
            "emotional": null,
            "age": null,
            "create_time": 1463981588000,
            "province": null,
            "xingzuo": null,
            "account": "18627543354",
            "sessionKey": "07B4CFF909C3BA86DEEF56E4186ECD00",
            "motto": null
        * */
    private String icon;
    private String area_id;
    private String sex;
    private String nickname;
    private String province_id;
    private String reg_from;
    private String score;
    private String userid;
    private String state;
    private String lv;
    private String job;
    private String city;
    private String city_id;
    private String area;
    private String login_time;
    private String emotional;
    private String age;
    private String create_time;
    private String province;
    private String xingzuo;
    private String account;
    private String sessionKey;
    private String motto;

    private UserAuth userAuth;
    private UserYunxin userYunXin;

    public UserYunxin getUserYunXin() {
        return userYunXin;
    }

    public void setUserYunXin(UserYunxin userYunXin) {
        this.userYunXin = userYunXin;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getEmotional() {
        return emotional;
    }

    public void setEmotional(String emotional) {
        this.emotional = emotional;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getLogin_time() {
        return login_time;
    }

    public void setLogin_time(String login_time) {
        this.login_time = login_time;
    }

    public String getLv() {
        return lv;
    }

    public void setLv(String lv) {
        this.lv = lv;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getReg_from() {
        return reg_from;
    }

    public void setReg_from(String reg_from) {
        this.reg_from = reg_from;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public UserAuth getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(UserAuth userAuth) {
        this.userAuth = userAuth;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getXingzuo() {
        return xingzuo;
    }

    public void setXingzuo(String xingzuo) {
        this.xingzuo = xingzuo;
    }
}
