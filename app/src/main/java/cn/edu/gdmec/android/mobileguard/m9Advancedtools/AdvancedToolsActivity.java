package cn.edu.gdmec.android.mobileguard.m9Advancedtools;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.edu.gdmec.android.mobileguard.R;

/**
 * Created by 84506 on 2017/12/9.
 */

public class AdvancedToolsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_tools);
        initView();
    }

    //初始化控件
    private void initView(){
        findViewById(R.id.rl_titlebar).setBackgroundColor(getResources().getColor(R.color.bright_red));
        ImageView mLeftImgv = (ImageView) findViewById(R.id.imgv_leftbtn);
        ((TextView) findViewById(R.id.tv_title)).setText("高级工具");
        mLeftImgv.setOnClickListener(this);
        mLeftImgv.setImageResource(R.drawable.back);

        findViewById(R.id.advanceview_applock).setOnClickListener(this);
        findViewById(R.id.advanceview_numbelongs).setOnClickListener(this);
        findViewById(R.id.advanceview_smsbackup).setOnClickListener(this);
        findViewById(R.id.advanceview_smsreduction).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgv_leftbtn:
                finish();
                break;
            case R.id.advanceview_numbelongs:
                //进入归属地查询页面
                startActivity(NumBelongtoActivity.class);
                break;
        }
    }

    private void startActivity(Class<?> cls) {
        Intent intent = new Intent(this,cls);
        startActivity(intent);
    }
}
