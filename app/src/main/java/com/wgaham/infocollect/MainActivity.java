package com.wgaham.infocollect;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    //public static final String USERNAMENULL = "没有用户名";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(R.string.app_name_cn);
        SharedPreferences userNamePref = getSharedPreferences("userId", MODE_PRIVATE);
        String userId = userNamePref.getString("id", Tool.USERNAMENULL);
        if (Tool.USERNAMENULL.equals(userId)) {
            Toast.makeText(MainActivity.this, "没有找到用户，请新建一个", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
