package cn.edu.gdmec.android.mobileguard.m9Advancedtools.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import java.util.List;

import cn.edu.gdmec.android.mobileguard.R;
import cn.edu.gdmec.android.mobileguard.m4appmanager.entity.AppInfo;

/**
 * Created by student on 17/12/18.
 */

public class AppLockAdapter extends BaseAdapter {
    private List<AppInfo> appInfos;
    private Context context;

    public AppLockAdapter(List<AppInfo> appInfos,Context context){
        super();
        this.appInfos = appInfos;
        this.context = context;
    }

    @Override
    public int getCount(){
        return appInfos.size();
    }

    @Override
    public Object getItem(int i){
        return appInfos.get(i);
    }

    @Override
    public long getItemId(int i){
        return i;
    }
/*
    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        ViewHolder holder;
        if(view != null && view instanceof RelativeLayout){
            holder = (ViewHolder) view.getTag();
        }else{
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.item_list_applock,null);
        }
    }
*/
}
