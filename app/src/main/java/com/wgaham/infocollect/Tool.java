package com.wgaham.infocollect;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 应用中使用的各种工具函数
 * Created by Wgaham on 2019/4/25.
 */
class Tool {
    private Tool() {
    }

    static final String USERNAMENULL = "没有用户名";
    static final String MYSQLCONNECT = "jdbc:mysql://154.8.214.100:3306/wgaham?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    static final String MYSQLUSERNAME = "root";
    static final String MYSQLPASS = "MyPass4!";
    static final String INSSQL = "insert into ins_app (app_id,ins_time,user_id) values(?,?,?)";
    static final String UNINSSQL = "insert into unins_app (app_id,unins_time,user_id) values(?,?,?)";

    /**
     * 返回定义好的事件格式
     *
     * @return 时间字符串
     */
    static String getTimeStr() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);

    }

}


