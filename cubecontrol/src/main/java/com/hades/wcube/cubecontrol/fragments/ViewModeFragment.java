package com.hades.wcube.cubecontrol.fragments;

import android.animation.LayoutTransition;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hades.baselib.ui.NormalBaseFragment;
import com.hades.wcube.cubecontrol.R;
import com.hades.wcube.cubecontrol.bean.event.EVCloseViewModeActivity;
import com.hades.wcube.cubecontrol.bean.event.EVProjectDetail;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Hades on 2017/3/26.
 * View浏览页面
 */

public class ViewModeFragment extends NormalBaseFragment {
    private LinearLayout toolbar;
    private FrameLayout itemContainer;


    ViewModeContentFragment contentFragment;
    private boolean isadd = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstaneState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.ft_viewmode, container, false);
        init(v,savedInstaneState);
        EventBus.getDefault().register(this);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        toolbar = (LinearLayout) view.findViewById(R.id.toolbar);
        itemContainer = (FrameLayout) view.findViewById(R.id.item_container);
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.setDuration(300);
        layoutTransition.setAnimator(LayoutTransition.APPEARING, layoutTransition.getAnimator(LayoutTransition.APPEARING));
        layoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, layoutTransition.getAnimator(LayoutTransition.CHANGE_APPEARING));
//        layoutTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, layoutTransition.getAnimator(LayoutTransition.CHANGE_DISAPPEARING));
        toolbar.setLayoutTransition(layoutTransition);
        initViews();


    }

    View viewMode;
    View homeItem;
    View fileItem;
    private FancyButton btnHome;
    private TextView btnViewmode;
    TextView tv_viewmode;
    private FancyButton btnFileItem;
    Typeface typeface;




    private void initViews() {
        initBar();
        initFragment();

    }

    private void initFragment() {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        contentFragment = new ViewModeContentFragment();
        transaction.replace(R.id.item_container, contentFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();

    }

    LinearLayout viewModeLy;
    LinearLayout homeLy;
    LinearLayout fileLy;
    private void initBar() {

        homeItem = inFlaterView(R.layout.v_homeitem, 1);
        btnHome = (FancyButton) homeItem.findViewById(R.id.btn_home);
        homeLy = (LinearLayout) homeItem.findViewById(R.id.ly);
        homeLy.setLayoutTransition(new LayoutTransition());

        toolbar.addView(homeItem);
        viewMode = inFlaterView(R.layout.v_viewmode, 2);
        btnViewmode = (TextView) viewMode.findViewById(R.id.btn_viewmode);
        viewModeLy = (LinearLayout) viewMode.findViewById(R.id.ly);

        typeface = Typeface.createFromAsset(getActivity().getAssets(), "iconfont.ttf");
        btnViewmode.setTypeface(typeface);
        tv_viewmode = (TextView) viewMode.findViewById(R.id.tv_viewmode);


        toolbar.addView(viewMode);

        fileItem = inFlaterView(R.layout.v_fileitem, 2);
        btnFileItem = (FancyButton) fileItem.findViewById(R.id.btn_fileItem);
        fileLy = (LinearLayout) fileItem.findViewById(R.id.ly);

    }


    private void reSizeItem(View view, float weight){
        LinearLayout.LayoutParams homeparams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, weight);
        view.setLayoutParams(homeparams);
    }




    private View inFlaterView(int layoutID, float weight){
        View view;
        LinearLayout.LayoutParams homeparams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, weight);
        view = LayoutInflater.from(getActivity()).inflate(layoutID, toolbar, false);
        view.setLayoutParams(homeparams);
        return view;
    }

    @Override
    protected void initData() {




    }

    @Override
    protected void initEvent() {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //回到control页面
                //getActivity().finish();//无效
                EventBus.getDefault().post(new EVCloseViewModeActivity());

            }
        });

        viewMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isadd) {
                    LayoutTransition layoutTransition = new LayoutTransition();
                    layoutTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING,layoutTransition
                            .getAnimator(LayoutTransition.CHANGE_DISAPPEARING)  );
                    //移除fileItem
                    toolbar.removeView(fileItem);
                    //homeItem 动画

                    reSizeItem(viewMode, 2);
                    btnViewmode.setGravity(Gravity.BOTTOM);

                    TranslateAnimation animation;
                    animation = new TranslateAnimation(0, 190, Animation.RELATIVE_TO_SELF,
                            Animation.RELATIVE_TO_SELF);
                    animation.setDuration(600);
                    animation.setFillAfter(true);
                    btnViewmode.startAnimation(animation);

                    tv_viewmode.setText("View Mode");
                    tv_viewmode.setVisibility(View.VISIBLE);

                    FragmentManager fm = getChildFragmentManager();
                    fm.popBackStack();
                    isadd = false;
                }
            }
        });

    }


    ProjectDetailFragment detailFragment;
    @Subscribe(priority = 2)
    public void jumpToDetail(EVProjectDetail projectDetail){

        tv_viewmode.setVisibility(View.GONE);
        reSizeItem(viewMode, 1);
        btnViewmode.setGravity(Gravity.CENTER);
        TranslateAnimation animation;
        animation = new TranslateAnimation(0, 0, Animation.RELATIVE_TO_SELF,
                Animation.RELATIVE_TO_SELF);
        animation.setDuration(600);
        animation.setFillAfter(true);
        btnViewmode.startAnimation(animation);

        btnFileItem.setText(projectDetail.getTitle());
        toolbar.addView(fileItem);
        isadd = true;

        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        detailFragment = new ProjectDetailFragment();
        transaction.setCustomAnimations(
                R.anim.push_left_in,
                R.anim.push_left_out,
                R.anim.back_left_in,
                R.anim.back_right_out);
        transaction.replace(R.id.item_container, detailFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();

    }



}
