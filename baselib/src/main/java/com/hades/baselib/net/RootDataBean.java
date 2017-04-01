package com.hades.baselib.net;

/**
 * Gson返回Ret基本格式
 * 成功:ret=1 + 业务数据
 * 失败:ret=0 + err_code + err_msg
 * Created by Hades on 16/10/9.
 * data = List<BaseBean> list;
 */
public class RootDataBean<T> {
    public int Status;         //成功-1 失败-0
    public String Msg;  //成功、错误msg
    public T BizData;          //成功返回的数据

}
