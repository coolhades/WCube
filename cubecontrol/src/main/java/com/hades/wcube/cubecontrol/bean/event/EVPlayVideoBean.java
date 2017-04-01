package com.hades.wcube.cubecontrol.bean.event;

/**
 * Created by Hades on 2017/3/31.
 */

public class EVPlayVideoBean {
    int type;// 1 : mp4

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
