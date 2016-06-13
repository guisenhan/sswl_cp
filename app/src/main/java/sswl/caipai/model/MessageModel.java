package sswl.caipai.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/1 0001.
 */
public class MessageModel implements Serializable{


    private String content;
    private String nickName;
    private String level;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
