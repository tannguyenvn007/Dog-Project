package com.example.tannguyen.moneyapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;
import com.parse.SignUpCallback;

import java.util.ArrayList;
import java.util.List;


public class Actions {
    public ArrayList<HistoryAct> mData = new ArrayList<>();
    public void parseConnection(Context context) {
        Parse.initialize(new Parse.Configuration.Builder(context)
                .applicationId("n5k0cNPdmW1kLFInNlKdoGusTJ5WE9Gg6nL8uc3R")
                .clientKey("Wd81NWpxXIN2mES8tgPjmupI4dbOWBublRc2eyaN")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }

    public int login(final Context context, final String email, String password){

        ParseUser.logInInBackground(email, password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    Intent i = new Intent(context, MainActivity.class);
                    i.putExtra("email",email);
                    context.startActivity(i);
                    ((Activity)context).finish();
                } else {
                    Toast.makeText(context,"User name or password wrong. Please try again.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return 0;
    }

    public boolean register(final Context context, String username, final String password, String phone, String indentifyCartNumber , String confirmPassword, String address,final String email){
        ParseUser user = new ParseUser();
        user.setEmail(email);
        user.setPassword(password);
        user.put("username",username);
        user.put("indentifyCartNumber", indentifyCartNumber);
        user.put("address", address);
        user.put("phone", phone);
        user.put("confirmPassword", confirmPassword);
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(context,"Register successful",Toast.LENGTH_SHORT).show();
                    login(context, email, password);
                } else {
                    System.out.print("Failed");
                    Toast.makeText(context,"Email was existed. Please try again",Toast.LENGTH_SHORT).show();
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
            Toast.makeText(context,"Welcome to login",Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public void passwordReset(final Context context, String email) {
        // An e-mail will be sent with further instructions
        ParseUser.requestPasswordResetInBackground(email, new RequestPasswordResetCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(context,"Kiem tra thu email",Toast.LENGTH_SHORT).show();
                    ((Activity)context).finish();
                } else {
                    // Something went wrong. Look at the ParseException to see what's up.
                    Toast.makeText(context,"deo fui dc",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public ArrayList<HistoryAct> readObject() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        Log.i("username", currentUser.getEmail());
        ParseQuery<ParseObject> query = ParseQuery.getQuery("history");
        query.whereEqualTo("userEmail",currentUser.getEmail());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < objects.size(); i++) {
                        HistoryAct history = new HistoryAct();
                        history.setAddress(String.valueOf(objects.get(i).get("Address")));
                        history.setMoney(String.valueOf(objects.get(i).get("totalMoney")));
                        history.setAddress(String.valueOf(objects.get(i).getCreatedAt()));
                        mData.add(history);
                        Log.i("usernamess1", ""+mData);
                    }
                } else {
                    System.out.println(e);
                }
            }});
        Log.i("username1", mData.toString());
        return mData;
    }


}
