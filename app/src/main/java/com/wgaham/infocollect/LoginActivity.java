package com.wgaham.infocollect;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText idText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        this.setTitle(R.string.title_activity_login);

        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        idText = (EditText) findViewById(R.id.userNameText);
        loginBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                String userNameStr = idText.getText().toString().trim();
                if (userNameStr.length() == 0) {
                    Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    break;
                }
                SharedPreferences.Editor userNameEditor = getSharedPreferences("userId", MODE_PRIVATE).edit();
                userNameEditor.putString("id", userNameStr);
                userNameEditor.apply();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
}
