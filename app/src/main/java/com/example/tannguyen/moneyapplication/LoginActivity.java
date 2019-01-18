package com.example.tannguyen.moneyapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
    Button btnRegister,btnLogin;
    TextView txtForgotPass;
    Actions actions = new Actions();
    EditText email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnRegister = (Button) findViewById(R.id.login_btnRegister);
        btnLogin = (Button) findViewById(R.id.login_btnLogin);
        txtForgotPass = (TextView) findViewById(R.id.login_txtForgotPass);
        email = (EditText) findViewById(R.id.login_Email);
        password = (EditText) findViewById(R.id.login_password);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, Register.class);
                startActivity(i);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().equals("") || password.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this, "All of filed is required", Toast.LENGTH_SHORT).show();
                }else {
                    actions.parseConnection(LoginActivity.this);
                    int login = actions.login(LoginActivity.this, email.getText().toString(), password.getText().toString());
                }
            }
        });
        txtForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(i);
            }
        });

        actions.getTokenAuthencation(LoginActivity.this);
    }
}
