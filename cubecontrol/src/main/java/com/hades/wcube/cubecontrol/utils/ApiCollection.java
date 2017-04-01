package com.hades.wcube.cubecontrol.utils;

import com.hades.baselib.net.RootDataBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Hades on 2017/3/28.
 */

public class ApiCollection {

        //获取基准域名
        public interface login {
            @FormUrlEncoded
            @POST("VRApi/User/Login")
            Call<RootDataBean> login(@Field("name") String name,
                                     @Field("pass") String pass
            );
        }

        //Main/GetProject
        public interface GetProject {
            @FormUrlEncoded
            @POST("Main/GetProject")
            Call<RootDataBean> GetProject(@Field("token") String token
            );
        }

        //Main/GetProjectItem
        public interface getProjectItem {
            @FormUrlEncoded
            @POST("Main/GetProjectItem")
            Call<RootDataBean> getProjectItem(@Field("pid") String pid,
                                     @Field("token") String token
            );
        }
}
