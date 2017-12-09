package cn.edu.gdmec.android.mobileguard.m9Advancedtools.widget;

import android.content.Context;
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

public class AdvancedToolsView extends RelativeLayout {
    private TextView mDescriptionTV;
    private String desc = "";
    private Drawable drawable;
    private ImageView mLeftImgv;

    public AdvancedToolsView(Context context) {
        super(context);
        init(context);
    }
    public AdvancedToolsView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        init(context);
    }
    public AdvancedToolsView(Context context,AttributeSet attrs){
        super(context, attrs);
        //拿到属性对象的值
        TypedArray mTypedArray =  context.obtainStyledAttributes(attrs, R.styleable.AdvancedToolsView);
        desc = mTypedArray.getString(R.styleable.AdvancedToolsView_desc);
        drawable = mTypedArray.getDrawable(R.styleable.AdvancedToolsView_android_src);
        mTypedArray.recycle();
        init(context);
    }
    //控件初始化
    private void init(Context context){
        //将资源转化成view对象显示在自己身上
        View view = View.inflate(context, R.layout.ui_advancedtools_view, null);
        this.addView(view);
        mDescriptionTV = (TextView) findViewById(R.id.tv_description);
        mLeftImgv = (ImageView) findViewById(R.id.imgv_left);
        mDescriptionTV.setText(desc);
        if(drawable != null){
            mLeftImgv.setImageDrawable(drawable);
        }

    }
}
