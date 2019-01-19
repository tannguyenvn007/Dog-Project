package com.example.tannguyen.moneyapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class HistoryUser extends AppCompatActivity {
    TextView txtName,txtPhone, txtAddress, txtBirthday, txtCardNumber, txtEmail, txtUsername;
    Actions actions = new Actions();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_user);

        txtName = (TextView)findViewById(R.id.user_profile_name);
        txtEmail = (TextView)findViewById(R.id.user_profile_email);
        txtPhone = (TextView)findViewById(R.id.user_profile_phone);
        txtAddress = (TextView)findViewById(R.id.user_profile_address);
        txtBirthday = (TextView)findViewById(R.id.user_profile_birthday);
        txtCardNumber = (TextView)findViewById(R.id.user_profile_identifyCardNumber);
        txtUsername = (TextView)findViewById(R.id.user_profile_userName);


        Intent intent = getIntent();
        String username = intent.getStringExtra("email");
        Log.e("asadasd",username);

        getUsers(HistoryUser.this,username);

    }
    public void getUsers(final Context context, final String username){

        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
        query.whereEqualTo("username", username);
        Log.e("asadasd",username);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject user, ParseException e) {
                if (e == null) {
                    String name = user.getString("fullName");
                    String email = user.getString("email");
                    String phone = user.getString("phone");
                    String address =  user.getString("address");
                    String cardNumber = user.getString("indentifyCartNumber");
                    String userName = user.getString("username");

                    txtName.setText(name);
                    txtEmail.setText("Email: "+email);
                    txtPhone.setText(phone);
                    txtAddress.setText("Address: "+address);
                    txtCardNumber.setText("PID: "+cardNumber);
                    txtUsername.setText("UserName: "+userName);

                } else {
                    Toast.makeText(context,"eo zo ddwoc.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
