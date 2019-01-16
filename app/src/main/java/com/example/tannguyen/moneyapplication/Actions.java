package com.example.tannguyen.moneyapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.LogInCallback;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Actions {
    public void parseConnection(Context context) {
        Parse.initialize(new Parse.Configuration.Builder(context)
                .applicationId("n5k0cNPdmW1kLFInNlKdoGusTJ5WE9Gg6nL8uc3R")
                .clientKey("Wd81NWpxXIN2mES8tgPjmupI4dbOWBublRc2eyaN")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }

    public int login(final Context context, final String username, String password){

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    Log.e("asasas", username);
                    Intent i = new Intent(context, MainActivity.class);
                    i.putExtra("username",username);
                    context.startActivity(i);
                    ((Activity)context).finish();
                } else {
                    Toast.makeText(context,"User name or password wrong. Please try again.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return 0;
    }

    public boolean register(final Context context, final String username, final String password, String phone, String indentifyCartNumber , String confirmPassword, String address){
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.put("indentifyCartNumber", indentifyCartNumber);
        user.put("address", address);
        user.put("phone", phone);
        user.put("confirmPassword", confirmPassword);

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    System.out.print("Ok");
                    Log.i("QuyenApplication", "Oki");
                    login(context, username, password);
                } else {
                    System.out.print("Failed");
                    Log.i("QuyenApplication", "Notoki");
                }
            }
        });
        return true;
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

    public void getUsers(Context context, String objectID){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");

        query.getInBackground(objectID, new GetCallback<ParseObject>() {
            public void done(ParseObject result, ParseException e) {
                if (e == null) {
                    System.out.println(result);
                } else {
                    // something went wrong
                }
            }
        });
    }
}
