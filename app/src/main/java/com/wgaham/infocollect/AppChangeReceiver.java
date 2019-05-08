package com.wgaham.infocollect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AppChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //获取安装程序
        if ("android.intent.action.PACKAGE_ADDED".equals(intent.getAction())) {
            String packageName = intent.getDataString().substring(8);
            String instime=Tool.getTimeStr();
            Intent insIntent=new Intent(context,SendDataService.class);
            insIntent.putExtra("type","Ins");
            insIntent.putExtra("app_name",packageName);
            insIntent.putExtra("app_time",instime);
            context.startService(insIntent);
        }
        //获取卸载程序包名
        if ("android.intent.action.PACKAGE_REMOVED".equals(intent.getAction())) {
            String packageName = intent.getDataString().substring(8);
            String uninstime=Tool.getTimeStr();
            Intent uninsIntent=new Intent(context,SendDataService.class);
            uninsIntent.putExtra("type","Unins");
            uninsIntent.putExtra("app_name",packageName);
            uninsIntent.putExtra("app_time",uninstime);
            context.startService(uninsIntent);
        }
        //获取覆盖安装包名（按安装处理）
        if ("android.intent.action.PACKAGE_REPLACED".equals(intent.getAction())) {
            String packageName = intent.getDataString().substring(8);
            String reinstime=Tool.getTimeStr();
            Intent insIntent=new Intent(context,SendDataService.class);
            insIntent.putExtra("type","Ins");
            insIntent.putExtra("app_name",packageName);
            insIntent.putExtra("app_time",reinstime);
            context.startService(insIntent);
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
