package sswl.caipai.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/30 0030.
 */
public class UserYunxin implements Serializable {
    /*
    * "icon": null,
            "token": "2c87a5205656fa4c093850c77e46ee5d",
            "accid": "1",
            "name": "18627543353",
            "create_time": 1464088633000,
            "userid": 1
    * */
    private String icon;
    private String token;
    private String accid;
    private String name;
    private String create_time;
    private String user_id;

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
