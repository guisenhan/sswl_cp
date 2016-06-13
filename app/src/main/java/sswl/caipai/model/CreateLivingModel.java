package sswl.caipai.model;

import java.io.Serializable;

import sswl.caipai.ui.adapter.fragment.TestInterface;

/**
 * Created by guisen.han on 2016/5/30 0030.
 */
public class CreateLivingModel implements Serializable {
    /*
    * "valid": 1,
        "icon": null,
        "push_url": "http://123456789",
        "status": "",
        "userid": "12",
        "type": "1",
        "total_count": 0,
        "cid": "123456789",
        "ctime": 123456789,
        "ext": "",
        "online_count": 0,
        "creator": "12",
        "id": 12,
        "roomid": 195142,
        "rtmp_pull_url": "http://123456789",
        "hls_pull_url": "http://123456789",
        "name": "大家都直播",
        "http_pull_url": "http://123456789"
    * */
    private String valid;
    private String icon;
    private String push_url;
    private String status;
    private String user_id;
    private String type;
    private String total_count;
    private String cid;
    private String ctime;
    private String ext;
    private String online_count;
    private String creator;
    private String id;
    private String roomid;
    private String rtmp_pull_url;
    private String hls_pull_url;
    private String name;
    private String http_pull_url;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getHls_pull_url() {
        return hls_pull_url;
    }

    public void setHls_pull_url(String hls_pull_url) {
        this.hls_pull_url = hls_pull_url;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHttp_pull_url() {
        return http_pull_url;
    }

    public void setHttp_pull_url(String http_pull_url) {
        this.http_pull_url = http_pull_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPush_url() {
        return push_url;
    }

    public void setPush_url(String push_url) {
        this.push_url = push_url;
    }

    public String getOnline_count() {
        return online_count;
    }

    public void setOnline_count(String online_count) {
        this.online_count = online_count;
    }

    public String getRtmp_pull_url() {
        return rtmp_pull_url;
    }

    public void setRtmp_pull_url(String rtmp_pull_url) {
        this.rtmp_pull_url = rtmp_pull_url;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotal_count() {
        return total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }
}
