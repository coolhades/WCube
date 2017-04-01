package com.hades.wcube.cubecontrol.bean;

import java.util.List;

/**
 * Created by Hades on 2017/3/28.
 */

public class ProjectBean {

    private List<ProjectsBean> projects;

    public List<ProjectsBean> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectsBean> projects) {
        this.projects = projects;
    }

    public static class ProjectsBean {
        /**
         * sid : 4843BAC62FCE41F1BA9B1F2EDA17F467
         * sPrjName : 冬博会-冬奥组委展区
         * sPrjDesc : 冬博会-冬奥组委展区vr展示
         */

        private String sid;
        private String sPrjName;
        private String sPrjDesc;

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getSPrjName() {
            return sPrjName;
        }

        public void setSPrjName(String sPrjName) {
            this.sPrjName = sPrjName;
        }

        public String getSPrjDesc() {
            return sPrjDesc;
        }

        public void setSPrjDesc(String sPrjDesc) {
            this.sPrjDesc = sPrjDesc;
        }
    }
}
