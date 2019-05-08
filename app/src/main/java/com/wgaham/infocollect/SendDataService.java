package com.wgaham.infocollect;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 本服务负责发送信息到数据库
 *
 * @author Wgaham
 */
public class SendDataService extends Service {
    public SendDataService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final String type = intent.getStringExtra("type");
        final String time = intent.getStringExtra("app_time");
        final String appName = intent.getStringExtra("app_name");
        SharedPreferences userNamePref = getSharedPreferences("userId", MODE_PRIVATE);
        final String userId = userNamePref.getString("id", Tool.USERNAMENULL);

        Runnable runnable = new Runnable() {

            private Connection connection = null;

            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connection = DriverManager.getConnection(Tool.MYSQLCONNECT, Tool.MYSQLUSERNAME, Tool.MYSQLPASS);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                PreparedStatement preparedStatement;
                try {
                    if ("Ins".equals(type)) {
                        preparedStatement = (PreparedStatement) connection.prepareStatement(Tool.INSSQL);
                    } else {
                        preparedStatement = (PreparedStatement) connection.prepareStatement(Tool.UNINSSQL);
                    }
                    preparedStatement.setString(1, appName);
                    preparedStatement.setString(2, time);
                    preparedStatement.setString(3, userId);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        };
        new Thread(runnable).start();
        stopSelf();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
