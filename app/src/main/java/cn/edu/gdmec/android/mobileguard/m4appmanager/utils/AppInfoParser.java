package cn.edu.gdmec.android.mobileguard.m4appmanager.utils;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.lang.reflect.Array;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.gdmec.android.mobileguard.m4appmanager.entity.AppInfo;

/**
 * Created by 84506 on 2017/11/9.
 */

public class AppInfoParser {
    //获取手机里面的所有的应用程序
    //@param context 上下文
    //@return
    public static List<AppInfo> getAppInfos(Context context){
        //获取包管理器
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> packInfos = pm.getInstalledPackages(0);
        List<AppInfo> appinfos = new ArrayList<AppInfo>();
        for(PackageInfo packInfo:packInfos){
            AppInfo appinfo = new AppInfo();
            //获取app包名
            String packname = packInfo.packageName;
            appinfo.packageName = packname;
            //获取app图标
            Drawable icon = packInfo.applicationInfo.loadIcon(pm);
            appinfo.icon = icon;
            //获取app名称
            String appname = packInfo.applicationInfo.loadLabel(pm).toString();
            appinfo.appName = appname;

            //获取app版本号
            String appversion = packInfo.versionName;
            appinfo.appVersion = appversion;
            //应用的安装时间
            appinfo.appInstallTime = new Date(packInfo.firstInstallTime).toString();
            //获取app的所有权限
            PackageInfo packpermissions = null;
            try {
                packpermissions = pm.getPackageInfo(packname, PackageManager.GET_PERMISSIONS);
                if (packpermissions.requestedPermissions!=null){
                    for (String pio : packpermissions.requestedPermissions){
                        appinfo.appPermissions= appinfo.appPermissions+pio+"\n";
                    }
                }
//                String[] p = packinfo2.requestedPermissions;
//                for (String s : p) {
//                    appinfo.permisstion+=s;
//                }

            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            //获取app的签名
            try {
                PackageInfo packsignatures = pm.getPackageInfo(packname, PackageManager.GET_SIGNATURES);
                byte[] ss = packsignatures.signatures[0].toByteArray();
                CertificateFactory cf = CertificateFactory.getInstance("X.509");
                X509Certificate cert = (X509Certificate) cf.generateCertificate(
                        new ByteArrayInputStream(ss));
                appinfo.appSignatures=cert.getIssuerDN().toString();
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            } catch (CertificateException e) {
                e.printStackTrace();
            }

            //获取app活动//

            PackageInfo packactivityes = null;
            try {
                packactivityes = pm.getPackageInfo(packname, PackageManager.GET_ACTIVITIES);


                ActivityInfo[] a = packactivityes.activities;
                    if (a != null) {
                        for (ActivityInfo activityInfo : a){
                        appinfo.appActivity = appinfo.appActivity + activityInfo.name + "\n";
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            //应用程序apk包的路径
            String apkpath = packInfo.applicationInfo.sourceDir;
            appinfo.apkPath = apkpath;
            File file = new File(apkpath);

            long appSize = file.length();
            appinfo.appSize = appSize;
            //应用程序安装的位置
            int flags = packInfo.applicationInfo.flags;//二进制映射  大bit-map
            if ((ApplicationInfo.FLAG_EXTERNAL_STORAGE & flags) != 0){
                //外部存储
                appinfo.isInRoom = false;
            }else{
                //手机内存
                appinfo.isInRoom = true;
            }
            if ((ApplicationInfo.FLAG_SYSTEM & flags) != 0){
                //系统应用
                appinfo.isUserApp = false;
            }else{
                //用户应用
                appinfo.isUserApp = true;
            }
            appinfos.add(appinfo);
            appinfo = null;
        }
        return appinfos;
    }
}
