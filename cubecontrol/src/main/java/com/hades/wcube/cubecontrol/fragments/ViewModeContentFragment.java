package com.hades.wcube.cubecontrol.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.hades.baselib.net.RetrofitManager;
import com.hades.baselib.net.RootDataBean;
import com.hades.baselib.projectutils.GsonUtils;
import com.hades.baselib.ui.NormalBaseFragment;
import com.hades.wcube.cubecontrol.R;
import com.hades.wcube.cubecontrol.bean.ProjectBean;
import com.hades.wcube.cubecontrol.bean.event.EVProjectDetail;
import com.hades.wcube.cubecontrol.utils.ApiCollection;
import com.hades.wcube.cubecontrol.utils.Constant;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hades on 2017/3/28.
 */

public class ViewModeContentFragment extends NormalBaseFragment {
    private LRecyclerView rcList;
    CommonAdapter adapter;

    List<ProjectBean.ProjectsBean> projectList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.rc_list, container, false);
        init(v, savedInstanceState);
        return v;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        rcList = (LRecyclerView) view.findViewById(R.id.rc_list);
    }

    @Override
    protected void initData() {
        projectList = new ArrayList<>();
        fetchListData();
    }

    private void fetchListData() {
        Call<RootDataBean> getProject = RetrofitManager.getInstance().getDefaultRetrofit().create(ApiCollection.GetProject.class)
                .GetProject(Constant.token);

        getProject.enqueue(new Callback<RootDataBean>() {
            @Override
            public void onResponse(Call<RootDataBean> call, Response<RootDataBean> response) {
                if (1 == response.body().Status) {
                    ProjectBean bean = GsonUtils.getInstance().fromJson(GsonUtils.getInstance().toJson(response.body().BizData), ProjectBean.class);
                    initRecycler(bean.getProjects());
                } else {
                    Toast.makeText(getActivity(), response.body().Msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RootDataBean> call, Throwable t) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initRecycler(List<ProjectBean.ProjectsBean> projects) {
        adapter = new CommonAdapter<ProjectBean.ProjectsBean>(getActivity(), R.layout.vh_viewmode_contentlist,
                projects) {

            @Override
            protected void convert(ViewHolder holder, final ProjectBean.ProjectsBean projectsBean, int position) {
                FancyButton leftPic;
                TextView tvTitle;
                TextView tvContent;
                FancyButton imagePic;
                FancyButton videoPic;

                leftPic = (FancyButton) holder.getView(R.id.leftPic);
                tvTitle = (TextView) holder.getView(R.id.tv_title);
                tvContent = (TextView) holder.getView(R.id.tv_content);
                imagePic = (FancyButton) holder.getView(R.id.imagePic);
                videoPic = (FancyButton) holder.getView(R.id.videoPic);

                tvTitle.setText(projectsBean.getSPrjName());
                tvContent.setText(projectsBean.getSPrjDesc());

                if (0 != (position / 2)) {
                    holder.getView(R.id.rl).setBackgroundColor(getResources().getColor(R.color.lightWhite));
                }
                holder.getView(R.id.rl).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EVProjectDetail detail = new EVProjectDetail();
                        detail.setTitle(projectsBean.getSPrjName());
                        EventBus.getDefault().post(detail);
                        //跳转详情Fragment

                        Constant.pid = projectsBean.getSid();

                    }
                });


            }
        };

        LRecyclerViewAdapter mLRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        StaggeredGridLayoutManager parentManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        parentManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcList.setLayoutManager(parentManager);
        rcList.setAdapter(mLRecyclerViewAdapter);
    }

    @Override
    protected void initEvent() {

    }
}
