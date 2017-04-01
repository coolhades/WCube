package com.hades.wcube.cubecontrol.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hades.baselib.net.RetrofitManager;
import com.hades.baselib.net.RootDataBean;
import com.hades.baselib.projectutils.ACache;
import com.hades.baselib.projectutils.GsonUtils;
import com.hades.baselib.ui.NormalBaseFragment;
import com.hades.wcube.cubecontrol.ControlActivity;
import com.hades.wcube.cubecontrol.R;
import com.hades.wcube.cubecontrol.bean.TokenBean;
import com.hades.wcube.cubecontrol.utils.ApiCollection;
import com.hades.wcube.cubecontrol.utils.Constant;

import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hades on 2017/3/26.
 */

public class AccountFragment extends NormalBaseFragment {
    private FancyButton btnStartconnect;
    private FancyButton mdCloud;
    private EditText edCloud;
    private FancyButton mdUser;
    private EditText tvName;
    private FancyButton mdPwd;
    private EditText tvPwd;

    private CheckBox mdCheck;
    private TextView mdTextview;
    private boolean isChecked = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstaneState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.ft_account, container, false);
        init(v,savedInstaneState);
        return v;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        btnStartconnect = (FancyButton) view.findViewById(R.id.btn_startconnect);

        mdPwd = (FancyButton)  view.findViewById(R.id.md_pwd);
        mdPwd.setCustomIconFont("iconfont.ttf");
        tvPwd = (EditText)  view.findViewById(R.id.tv_pwd);

        mdUser = (FancyButton)  view.findViewById(R.id.md_user);
        mdUser.setCustomIconFont("iconfont.ttf");
        tvName = (EditText)  view.findViewById(R.id.tv_name);

        mdCloud = (FancyButton)  view.findViewById(R.id.md_cloud);
        mdCloud.setCustomIconFont("iconfont.ttf");
        edCloud = (EditText)  view.findViewById(R.id.ed_cloud);

        mdCheck = (CheckBox) view.findViewById(R.id.md_check);
        mdTextview = (TextView) view.findViewById(R.id.md_textview);

        if (null != ACache.get(getActivity()).getAsString("isrember") && ACache.get(getActivity()).getAsString("isrember").equalsIgnoreCase("true")){
            mdCheck.setChecked(true);
            isChecked = true;
        }
    }

    @Override
    protected void initData() {
        if (isChecked) {
            if (null != ACache.get(getActivity()).getAsString("baseurl") && !ACache.get(getActivity()).getAsString("baseurl").equalsIgnoreCase("")){
                edCloud.setText(ACache.get(getActivity()).getAsString("baseurl"));
            }

            if (null != ACache.get(getActivity()).getAsString("name") && !ACache.get(getActivity()).getAsString("name").equalsIgnoreCase("")) {
                tvName.setText(ACache.get(getActivity()).getAsString("name"));
            }

            if (null != ACache.get(getActivity()).getAsString("pass") && !ACache.get(getActivity()).getAsString("pass").equalsIgnoreCase("")) {
                tvPwd.setText(ACache.get(getActivity()).getAsString("pass"));
            }
        }
    }

    @Override
    protected void initEvent() {
        mdCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mdCheck.isChecked()){
                    ACache.get(getActivity()).put("baseurl", edCloud.getText().toString());

                    ACache.get(getActivity()).put("name", tvName.getText().toString());
                    ACache.get(getActivity()).put("pass", tvPwd.getText().toString());
                    ACache.get(getActivity()).put("isrember", "true");
                }else {
                    ACache.get(getActivity()).put("baseurl", "");
                    ACache.get(getActivity()).put("name", "");
                    ACache.get(getActivity()).put("pass", "");
                    ACache.get(getActivity()).put("isrember", "false");
                }
            }
        });



        btnStartconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edCloud.getText().toString().trim().isEmpty()){
                    Toast.makeText(getActivity(), "请输入服务器地址", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (tvName.getText().toString().trim().isEmpty()){
                    Toast.makeText(getActivity(), "请输入账户名称", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (tvPwd.getText().toString().trim().isEmpty()){
                    Toast.makeText(getActivity(), "请输入登录密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                Constant.baseUrl = edCloud.getText().toString();
                RetrofitManager.getInstance().setBaseUrl(Constant.baseUrl + "/" );
                login(tvName.getText().toString(), tvPwd.getText().toString());
            }
        });

    }

    public void login(String name, String pass){
        Call<RootDataBean> login = RetrofitManager.getInstance().getDefaultRetrofit().create(ApiCollection.login.class)
                .login(name, pass);
        login.enqueue(new Callback<RootDataBean>() {
            @Override
            public void onResponse(Call<RootDataBean> call, Response<RootDataBean> response) {
                if (0 == response.body().Status){
                    TokenBean tokenBean = GsonUtils.getInstance().fromJson(GsonUtils.getInstance().toJson(response.body().BizData), TokenBean.class);
                    Constant.token = tokenBean.getToken();
                    getActivity().startActivity(new Intent(getActivity(), ControlActivity.class));
                }else {
                    Toast.makeText(getActivity(), response.body().Msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RootDataBean> call, Throwable t) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
