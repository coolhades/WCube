package com.hades.wcube.cubecontrol.bean;

import java.util.List;

/**
 * Created by Hades on 2017/3/28.
 */

public class ProjectDetailBean {

    private List<ItemsBean> items;

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * sid : 14A08EC803C84C06B1B00355C8DF8DBA
         * sPrj : 4B4216C046DC402F9094AA5C702C7B80
         * sName : 清水湾
         * sAlbum : /Files/VRSI/14A08EC803C84C06B1B00355C8DF8DBA/panos/_________02.tiles/thumb.jpg
         * iType : 1
         * sUrl : /Files/VRSI/14A08EC803C84C06B1B00355C8DF8DBA/tour.html
         */

        private String sid;
        private String sPrj;
        private String sName;
        private String sAlbum;
        private int iType;
        private String sUrl;

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getSPrj() {
            return sPrj;
        }

        public void setSPrj(String sPrj) {
            this.sPrj = sPrj;
        }

        public String getSName() {
            return sName;
        }

        public void setSName(String sName) {
            this.sName = sName;
        }

        public String getSAlbum() {
            return sAlbum;
        }

        public void setSAlbum(String sAlbum) {
            this.sAlbum = sAlbum;
        }

        public int getIType() {
            return iType;
        }

        public void setIType(int iType) {
            this.iType = iType;
        }

        public String getSUrl() {
            return sUrl;
        }

        public void setSUrl(String sUrl) {
            this.sUrl = sUrl;
        }
    }
}
