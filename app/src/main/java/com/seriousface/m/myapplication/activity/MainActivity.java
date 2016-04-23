package com.seriousface.m.myapplication.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.seriousface.m.myapplication.R;
import com.seriousface.m.myapplication.constant.Constant;
import com.seriousface.m.myapplication.util.EmailUtil;

import java.io.IOException;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    RelativeLayout tvOfficial;
    RelativeLayout tvOwn;

    ImageView ivTopView;

    ImageView tvAboutUs;

    private int VALUE_INTENT_TO_OWN = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOfficial = (RelativeLayout)findViewById(R.id.tv_main_official);
        tvOwn = (RelativeLayout)findViewById(R.id.tv_main_own);
        ivTopView = (ImageView)findViewById(R.id.iv_main_top_view);
        tvAboutUs = (ImageView)findViewById(R.id.tv_about_us);

        tvOfficial.setOnClickListener(this);
        tvOwn.setOnClickListener(this);
        tvAboutUs.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.tv_main_official:
                i = new Intent(MainActivity.this,FaceTypeActivity.class);
                MainActivity.this.startActivity(i);
                break;
            case R.id.tv_main_own:
                i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/*");
                startActivityForResult(i, VALUE_INTENT_TO_OWN);
                break;
            case R.id.tv_about_us:
                i = new Intent(MainActivity.this,SettingActivity.class);
                MainActivity.this.startActivity(i);
                break;
        }                                                                                                                                        
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            //android.net.Uri url = data.getData();
            //ContentResolver resolver = getContentResolver();
            //try {
            //    Bitmap bitmap = MediaStore.Images.Media.getBitmap(resolver,url);
            //} catch (IOException e) {
            //    e.printStackTrace();
            //    Toast.makeText(MainActivity.this,"抱歉~无法成功获取本地图片",Toast.LENGTH_LONG).show();
            //}

            Intent i = new Intent(MainActivity.this,CameraFaceActivity.class);
            i.putExtra(Constant.KEY_PIC_CHOOSE_TYPE,Constant.VALUE_PIC_CHOOSE_TYPE_OWN);
            i.putExtra(Constant.KEY_PIC_CHOOSE_DATA,data.getData());
            startActivity(i);
        }
    }

    private void showAnim(){
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f,1.0f,0.0f,1.0f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setInterpolator(new OvershootInterpolator());
        ivTopView.startAnimation(scaleAnimation);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            showAnim();
        }
    }
}
