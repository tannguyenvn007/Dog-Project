package com.example.tannguyen.moneyapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Register extends AppCompatActivity {

    Actions actions = new Actions();
    EditText register_etxtFullName, register_etxtemail, register_etxtPhone,register_etxtAddress, register_etxtCardNumber, register_etxtPassword, register_etxtConfirmPassword  ;
    Button button_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        actions.parseConnection(Register.this);

        register_etxtFullName = (EditText) findViewById(R.id.register_etxtFullName);
        register_etxtemail = (EditText) findViewById(R.id.register_etxtEmail);
        register_etxtPhone = (EditText) findViewById(R.id.register_etxtPhone);
        register_etxtAddress = (EditText) findViewById(R.id.register_etxtAddress);
        register_etxtCardNumber = (EditText) findViewById(R.id.register_etxtCardNumber);
        register_etxtPassword = (EditText) findViewById(R.id.register_etxtPassword);
        register_etxtConfirmPassword = (EditText) findViewById(R.id.register_etxtConfirmPassword);
        button_register = (Button) findViewById(R.id.button_register);


        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (register_etxtFullName.getText().toString().equals("")
                        || register_etxtPhone.getText().toString().equals("")
                        || register_etxtAddress.getText().toString().equals("")
                        || register_etxtCardNumber.getText().toString().equals("")
                        || register_etxtPassword.getText().toString().equals("")
                        || register_etxtConfirmPassword.getText().toString().equals("")
                        || register_etxtemail.getText().toString().equals("")){
                    Toast.makeText(Register.this,"All of filed is required.",Toast.LENGTH_SHORT).show();
                }else {
//
                    if (register_etxtPassword.getText().toString().equals(register_etxtConfirmPassword.getText().toString())){
                        actions.parseConnection(Register.this);
                        Log.e("QuyenApplication",register_etxtemail.getText().toString());
                        boolean re = actions.register(Register.this,
                                register_etxtFullName.getText().toString(),
                                register_etxtPassword.getText().toString(),
                                register_etxtPhone.getText().toString(),
                                register_etxtCardNumber.getText().toString(),
                                register_etxtConfirmPassword.getText().toString(),
                                register_etxtAddress.getText().toString(),
                                register_etxtemail.getText().toString());


                        if (re){
                            Log.i("QuyenApplication", "Okkk");
                        }else {
                            Log.i("QuyenApplication", "Failde");

                        }
                    }else {
                        Toast.makeText(Register.this,"Confrim password is wrong. "+register_etxtPassword.getText()+" and " +register_etxtConfirmPassword.getText(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
