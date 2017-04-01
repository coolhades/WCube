package com.hades.wcube.cubecontrol.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.hades.baselib.net.RetrofitManager;
import com.hades.baselib.net.RootDataBean;
import com.hades.baselib.projectutils.GsonUtils;
import com.hades.baselib.projectutils.imageutil.ImageLoaderUtils;
import com.hades.baselib.projectutils.imageutil.commenbean.ImageParameter;
import com.hades.baselib.ui.NormalBaseFragment;
import com.hades.wcube.cubecontrol.R;
import com.hades.wcube.cubecontrol.bean.ProjectDetailBean;
import com.hades.wcube.cubecontrol.dialog.PictureDialog;
import com.hades.wcube.cubecontrol.utils.ApiCollection;
import com.hades.wcube.cubecontrol.utils.Constant;
import com.hades.wcube.cubecontrol.video.PlayerFragmentAcivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hades on 2017/3/26.
 * View浏览页面
 */

public class ProjectDetailFragment extends NormalBaseFragment {

    private FrameLayout ftContainer;
    private LRecyclerView lrecyclerview;

    List<ProjectDetailBean.ItemsBean> listBean;
    CommonAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstaneState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.ft_projectdetail, container, false);
        init(v,savedInstaneState);

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ftContainer = (FrameLayout) view.findViewById(R.id.ft_container);
        lrecyclerview = (LRecyclerView) view.findViewById(R.id.lrecyclerview);

    }



    @Override
    protected void initData() {
        fetchData();



    }

    private void fetchData() {
        Call<RootDataBean> getDetail = RetrofitManager.getInstance().getDefaultRetrofit().create(ApiCollection.getProjectItem.class)
                .getProjectItem(Constant.pid, Constant.token);
        getDetail.enqueue(new Callback<RootDataBean>() {
            @Override
            public void onResponse(Call<RootDataBean> call, Response<RootDataBean> response) {
                if (1 == response.body().Status){
                    ProjectDetailBean bean = GsonUtils.getInstance().fromJson(GsonUtils.getInstance().toJson(response.body().BizData), ProjectDetailBean.class);
                    initRecycler(bean.getItems());
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

    private void initRecycler(List<ProjectDetailBean.ItemsBean> items) {
        adapter = new CommonAdapter<ProjectDetailBean.ItemsBean>(getActivity(), R.layout.vh_viewmode_itemdetail, items) {
            @Override
            protected void convert(ViewHolder holder, final ProjectDetailBean.ItemsBean itemsBean, int position) {
                 ImageView mIvItem;
                 TextView mTvContent;

                mIvItem = (ImageView) holder.getView(R.id.iv_item);
                mTvContent = (TextView) holder.getView(R.id.tv_content);

                final int type = itemsBean.getIType();

                ImageLoaderUtils.getmIntance().loadImage(mContext, new ImageParameter(Constant.baseUrl+itemsBean.getSAlbum(), mIvItem, 0, 0, 0));
                mTvContent.setText(itemsBean.getSName());
                holder.getView(R.id.rl).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (1 == type) {
                            PictureDialog dialog = new PictureDialog(mContext, Constant.baseUrl + itemsBean.getSUrl());
                            Window window = dialog.getWindow();
                            window.setGravity(Gravity.BOTTOM);
                            dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
                            dialog.show();
                        }else {
                            Constant.type = getFileExtensionFromUrl(itemsBean.getSUrl());
                            Constant.url = Constant.baseUrl+itemsBean.getSUrl();
                            //视频播放
                            getActivity().startActivity(new Intent(getActivity(), PlayerFragmentAcivity.class));

                        }
                    }
                });

            }
        };
        LRecyclerViewAdapter mLRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        StaggeredGridLayoutManager parentManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        parentManager.setOrientation(LinearLayoutManager.VERTICAL);
        lrecyclerview.setLayoutManager(parentManager);
        lrecyclerview.setAdapter(mLRecyclerViewAdapter);

    }

    @Override
    protected void initEvent() {


    }


    /**
     * Returns the file extension or an empty string iff there is no
     * extension. This method is a convenience method for obtaining the
     * extension of a url and has undefined results for other Strings.
     * @param url
     * @return The file extension of the given url.
     */
    public static String getFileExtensionFromUrl(String url) {
        if (!TextUtils.isEmpty(url)) {
            int fragment = url.lastIndexOf('#');
            if (fragment > 0) {
                url = url.substring(0, fragment);
            }

            int query = url.lastIndexOf('?');
            if (query > 0) {
                url = url.substring(0, query);
            }

            int filenamePos = url.lastIndexOf('/');
            String filename =
                    0 <= filenamePos ? url.substring(filenamePos + 1) : url;

            // if the filename contains special characters, we don't
            // consider it valid for our matching purposes:
            if (!filename.isEmpty() &&
                    Pattern.matches("[a-zA-Z_0-9\\.\\-\\(\\)\\%]+", filename)) {
                int dotPos = filename.lastIndexOf('.');
                if (0 <= dotPos) {
                    return filename.substring(dotPos + 1);
                }
            }
            return filename;
        }

        return "";
    }


}
