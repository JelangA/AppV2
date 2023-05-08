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

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btnRegister = (Button) findViewById(R.id.btnRegister2);
        Button btnLogin = (Button) findViewById(R.id.btnBack2);

        EditText txtNamaLengkap = findViewById(R.id.txtNamaLengkap2);
        EditText txtusername = findViewById(R.id.txtUsername2);
        EditText txtalamat = findViewById(R.id.txtAlamat2);
        EditText txtpassword = findViewById(R.id.txtPassword2);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

                Toast.makeText(RegisterActivity.this, AppConst.URL_REGISTER, Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackgroundTaskAuth taskAuth = new BackgroundTaskAuth(getApplicationContext());
                taskAuth.execute(
                        "register",
                        txtusername.getText().toString(),
                        txtpassword.getText().toString()
                );
            }
        });
    }
}