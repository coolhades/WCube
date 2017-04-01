package com.hades.wcube.cubecontrol;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hades.baselib.ui.NormalBaseActivity;

import de.hdodenhof.circleimageview.CircleImageView;
import mehdi.sakout.fancybuttons.FancyButton;

public class ControlActivity extends NormalBaseActivity {
    private FancyButton btnBack;
    private CircleImageView imgHead;
    private TextView tvName;
    private TextView tvEmail;

    private LinearLayout btnViewmode;
    private TextView tvViewmode;
    private LinearLayout btnControlmode;
    private TextView tvControlmode;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        init(savedInstanceState);
    }

    @Override
    protected void hideStatusBar() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        btnBack = (FancyButton) findViewById(R.id.btn_back);
        btnBack.setCustomIconFont("iconfont.ttf");
        imgHead = (CircleImageView) findViewById(R.id.img_head);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvEmail = (TextView) findViewById(R.id.tv_email);
        btnViewmode = (LinearLayout) findViewById(R.id.btn_viewmode);
        btnControlmode = (LinearLayout) findViewById(R.id.btn_controlmode);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "iconfont.ttf");

        tvViewmode = (TextView) findViewById(R.id.tv_viewmode);
        tvViewmode.setTypeface(typeface);
        tvControlmode = (TextView) findViewById(R.id.tv_controlmode);
        tvControlmode.setTypeface(typeface);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControlActivity.this.finish();
            }
        });

        btnViewmode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnViewmode.setBackgroundColor(getResources().getColor(R.color.viewBg));
                startActivity(new Intent(ControlActivity.this, ViewModeActivity.class));
            }
        });

        btnControlmode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnControlmode.setBackgroundColor(getResources().getColor(R.color.controlBG));
                startActivity(new Intent(ControlActivity.this, ControlModeActivity.class));
            }
        });


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        btnViewmode.setBackgroundColor(getResources().getColor(R.color.Ground));
        btnControlmode.setBackgroundColor(getResources().getColor(R.color.noColor));
    }
}
