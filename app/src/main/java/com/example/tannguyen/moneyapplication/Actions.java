package com.example.tannguyen.moneyapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.parse.LogInCallback;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Actions {
    public void parseConnection(Context context) {
        Parse.initialize(new Parse.Configuration.Builder(context)
                .applicationId("xmveNrevqQ6DodFaaYB5BdLlQrFa96iKf8QLBhvr")
                .clientKey("dKUykxREF3X4THVMzXs65CfKntv9JndoPrwWroEW")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }

    public int login(final Context context, String username, String password){

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    Intent i = new Intent(context, MainActivity.class);
                    context.startActivity(i);
                    ((Activity)context).finish();
                } else {
                    Toast.makeText(context,"User name or password wrong. Please try again.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return 0;
    }

    public String currentUser(final Context context){
        String user = "";
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            user = currentUser.getUsername();
            saveToken(context, currentUser.getSessionToken());
        } else {
            user = "";
        }
        return user;
    }

    boolean saveToken(Context context, String TOKEN){
        SharedPreferences pref = context.getSharedPreferences("auth", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("token", TOKEN);
        editor.commit();
        return true;
    }

    boolean removeToken(Context context){
        SharedPreferences pref = context.getSharedPreferences("auth",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
        return false;
    }

    public boolean logout(final Context context){
        removeToken(context);
        getTokenAuthencation(context);
        return true;
    }

    boolean getTokenAuthencation (Context context){
        SharedPreferences pref = context.getSharedPreferences("auth", context.MODE_PRIVATE);
        if(!pref.getString("token","").equals("")){
            Intent homepage = new Intent(context, MainActivity.class);
            context.startActivity(homepage);
            ((Activity)context).finish();
        }else {
            Toast.makeText(context,"Phiên làm việc đã hết hạn. Đăng nhập lại.",Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
