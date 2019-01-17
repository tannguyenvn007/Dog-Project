package com.example.tannguyen.moneyapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassword extends Activity {
    Actions actions = new Actions();
    EditText edtEmail;
    Button btnReset;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        edtEmail = (EditText) findViewById(R.id.user_forgotpass_email);
        btnReset = (Button) findViewById(R.id.user_forgotpass_reset);



        actions.parseConnection(ForgotPassword.this);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                if (email.equals("")){
                    Toast.makeText(ForgotPassword.this,"Vui long nhap email.",Toast.LENGTH_SHORT).show();
                }else {
                    actions.passwordReset(ForgotPassword.this,email);
                    Toast.makeText(ForgotPassword.this,"Thanh cong.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
