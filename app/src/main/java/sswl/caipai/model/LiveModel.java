package sswl.caipai.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/31 0031.
 */
public class LiveModel implements Serializable{
   /*
   *  "icon": null,
            "valid": 1,
            "announcement": null,
            "push_url": "http://123456789",
            "status": null,
            "userid": 12,
            "type": null,
            "ctime": 123456789,
            "cid": "1234567890",
            "total_count": 0,
            "ext": "",
            "online_count": 0,
			"like_count": 0,
            "creator": "12",
            "id": 10,
            "roomid": 193534,
            "rtmp_pull_url": "http://123456789",
            "hls_pull_url": "http://123456789",
            "update_time": null,
            "name": "大家都直播",
            "create_time": 1464265739000,
            "http_pull_url": "http://123456789",
            "broadcasturl": null
   * */

    private String icon;
    private String valid;
    private String announcement;
    private String push_url;
    private String status;
    private String userid;
    private String type;
    private String ctime;
    private String cid;
    private String total_count;
    private String ext;
    private String online_count;
    private String like_count;
    private String creator;
    private String id;
    private String roomid;
    private String rtmp_pull_url;
    private String hls_pull_url;
    private String update_time;
    private String name;
    private String create_time;
    private String http_pull_url;
    private String broadcasturl;
    private UserModel creatuser;

    public UserModel getCreatuser() {
        return creatuser;
    }

    public void setCreatuser(UserModel creatuser) {
        this.creatuser = creatuser;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public String getBroadcasturl() {
        return broadcasturl;
    }

    public void setBroadcasturl(String broadcasturl) {
        this.broadcasturl = broadcasturl;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
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

    public String getHttp_pull_url() {
        return http_pull_url;
    }

    public void setHttp_pull_url(String http_pull_url) {
        this.http_pull_url = http_pull_url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLike_count() {
        return like_count;
    }

    public void setLike_count(String like_count) {
        this.like_count = like_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOnline_count() {
        return online_count;
    }

    public void setOnline_count(String online_count) {
        this.online_count = online_count;
    }

    public String getPush_url() {
        return push_url;
    }

    public void setPush_url(String push_url) {
        this.push_url = push_url;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getRtmp_pull_url() {
        return rtmp_pull_url;
    }

    public void setRtmp_pull_url(String rtmp_pull_url) {
        this.rtmp_pull_url = rtmp_pull_url;
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

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }
}
