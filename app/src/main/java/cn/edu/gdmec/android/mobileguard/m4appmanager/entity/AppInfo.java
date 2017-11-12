package cn.edu.gdmec.android.mobileguard.m4appmanager.entity;

import android.graphics.drawable.Drawable;

/**
 * Created by 84506 on 2017/11/9.
 */

public class AppInfo {
    //应用程序包名
    public String packageName;
    //应用程序图标
    public Drawable icon;
    //应用程序名称
    public String appName;
    //应用程序路径
    public String apkPath;
    //应用程序大小
    public long appSize;
    //是否手机存储
    public boolean isInRoom;
    //是否用户响应
    public boolean isUserApp;
    //是否选中，默认是false
    public boolean isSelected = false;
    //应用程序版本号
    public String appVersion;
    //应用程序安装时间
    public String appInstallTime;
    //应用程序的所有权限
    public String appPermissions;
    //应用程序的签名
    public String appSignatures;

    //拿到App位置字符串
    public String getAppLocation(boolean isInRoom){
        if (isInRoom){
            return "手机内存";
        }else{
            return "外部存储";
        }
    }
}
