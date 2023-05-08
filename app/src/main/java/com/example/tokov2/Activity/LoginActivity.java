package com.example.tokov2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tokov2.BackgroundTasks.BackgroundTaskAuth;
import com.example.tokov2.R;
import com.example.tokov2.util.AppConst;

public class LoginActivity extends AppCompatActivity {

    String username, password;
    Button btnlogin, btnregister;
    EditText txtusername, txtpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnlogin = (Button) findViewById(R.id.btnLogin1);
        btnregister = (Button) findViewById(R.id.btnRegister1);

        txtusername = (EditText) findViewById(R.id.txtUsername1);
        txtpassword = (EditText) findViewById(R.id.txtPassword1);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = txtusername.getText().toString();
                password = txtpassword.getText().toString();

                BackgroundTaskAuth taskAuth = new BackgroundTaskAuth(getApplicationContext());
                taskAuth.execute("login", username, password);
            }

        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                Toast.makeText(LoginActivity.this, AppConst.URL_LOGIN, Toast.LENGTH_SHORT).show();
            }
        });
    }
}