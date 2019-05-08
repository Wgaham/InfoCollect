package com.wgaham.infocollect;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动管理类，方便结束活动
 * Created by Wgaham on 2019/4/1.
 */
public class ActivityCollector {

    public static List<Activity> activities=new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void removeAllActivities(){
        for (Activity activity:activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }

}
