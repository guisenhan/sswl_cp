package sswl.caipai.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/25 0025.
 */
public class UserAuth implements Serializable{
    /*
    * bank_name": "中国建设银行",
            "bankid": 3,
            "auditmsg": "1122",
            "auditid": 5,
            "idcard_img3": "http://o7ewskuoz.bkt.clouddn.com/2016/05/20/14/dd93aa70-4166-42f3-9011-6f4d7ef9a6ac.jpg",
            "userid": 2,
            "state": 0,
            "idcard_img1": "http://o7ewskuoz.bkt.clouddn.com/2016/05/20/14/958f868b-b55f-47c0-b497-99e1ad7d5c42.jpg",
            "idcard_img2": "http://o7ewskuoz.bkt.clouddn.com/2016/05/20/14/3f3fb590-aad1-486d-ac8a-95761ccd53a5.jpg",
            "bank_province_id": 430000,
            "bank_area_id": "430103",
            "bank_time": null,
            "bank_province": "湖南省",
            "bank_address": null,
            "auditime": null,
            "bank_city": "长沙市",
            "idcard": "1122",
            "bank_kaihu": "1122",
            "bank_area": "天心区",
            "bank_city_id": 430100,
            "realname_time": null,
            "create_time": 1463711108000,
            "bank_username": "1122",
            "bank_no": "1122",
            "realname": "1122",
            "mobile": "1122"
    * */
    private String bank_name;
    private String bank_id;
    private String auditmsg;
    private String auditid;
    private String idcard_img3;
    private String userid;
    private String state;
    private String idcard_img1;
    private String idcard_img2;
    private String bank_province_id;
    private String bank_area_id;
    private String bank_time;
    private String bank_province;
    private String bank_address;
    private String auditime;
    private String bank_city;
    private String idcard;
    private String bank_kaihu;
    private String bank_area;
    private String bank_city_id;
    private String realname_time;
    private String create_time;
    private String bank_username;
    private String bank_no;
    private String realname;
    private String mobile;

    public String getAuditid() {
        return auditid;
    }

    public void setAuditid(String auditid) {
        this.auditid = auditid;
    }

    public String getAuditime() {
        return auditime;
    }

    public void setAuditime(String auditime) {
        this.auditime = auditime;
    }

    public String getAuditmsg() {
        return auditmsg;
    }

    public void setAuditmsg(String auditmsg) {
        this.auditmsg = auditmsg;
    }

    public String getBank_address() {
        return bank_address;
    }

    public void setBank_address(String bank_address) {
        this.bank_address = bank_address;
    }

    public String getBank_area() {
        return bank_area;
    }

    public void setBank_area(String bank_area) {
        this.bank_area = bank_area;
    }

    public String getBank_id() {
        return bank_id;
    }

    public void setBank_id(String bank_id) {
        this.bank_id = bank_id;
    }

    public String getBank_province() {
        return bank_province;
    }

    public void setBank_province(String bank_province) {
        this.bank_province = bank_province;
    }

    public String getBank_city_id() {
        return bank_city_id;
    }

    public void setBank_city_id(String bank_city_id) {
        this.bank_city_id = bank_city_id;
    }

    public String getBank_city() {
        return bank_city;
    }

    public void setBank_city(String bank_city) {
        this.bank_city = bank_city;
    }

    public String getBank_area_id() {
        return bank_area_id;
    }

    public void setBank_area_id(String bank_area_id) {
        this.bank_area_id = bank_area_id;
    }

    public String getBank_no() {
        return bank_no;
    }

    public void setBank_no(String bank_no) {
        this.bank_no = bank_no;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_kaihu() {
        return bank_kaihu;
    }

    public void setBank_kaihu(String bank_kaihu) {
        this.bank_kaihu = bank_kaihu;
    }

    public String getBank_province_id() {
        return bank_province_id;
    }

    public void setBank_province_id(String bank_province_id) {
        this.bank_province_id = bank_province_id;
    }

    public String getBank_time() {
        return bank_time;
    }

    public void setBank_time(String bank_time) {
        this.bank_time = bank_time;
    }

    public String getBank_username() {
        return bank_username;
    }

    public void setBank_username(String bank_username) {
        this.bank_username = bank_username;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getIdcard_img1() {
        return idcard_img1;
    }

    public void setIdcard_img1(String idcard_img1) {
        this.idcard_img1 = idcard_img1;
    }

    public String getIdcard_img2() {
        return idcard_img2;
    }

    public void setIdcard_img2(String idcard_img2) {
        this.idcard_img2 = idcard_img2;
    }

    public String getIdcard_img3() {
        return idcard_img3;
    }

    public void setIdcard_img3(String idcard_img3) {
        this.idcard_img3 = idcard_img3;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getRealname_time() {
        return realname_time;
    }

    public void setRealname_time(String realname_time) {
        this.realname_time = realname_time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
