package sswl.caipai.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
public class AreaModel implements Serializable{
    /*
    * "id": 110000,
            "zip": null,
            "name": "北京",
            "type": 2,
            "ishot": 0,
            "parent_id": 1
    * */
    private String id;
    private String zip;
    private String name;
    private String type;
    private String ishot;
    private String parent_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIshot() {
        return ishot;
    }

    public void setIshot(String ishot) {
        this.ishot = ishot;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
