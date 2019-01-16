package com.example.tannguyen.moneyapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button btnNap, btnHistory, btnProfile;
    Actions actions = new Actions();
    boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actions.parseConnection(MainActivity.this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigation_home:
                        break;
                    case R.id.navigation_history:
                        Intent a = new Intent(MainActivity.this,History.class);
                        startActivity(a);
                        break;
                    case R.id.navigation_wallet:
                        Intent i = new Intent(MainActivity.this,TopUpWalletActivity.class);
                        startActivity(i);
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menProfile:
                Intent i = new Intent(MainActivity.this, HistoryUser.class);
                startActivity(i);
                break;
            case R.id.menuSetting:
                Toast.makeText(this,"Bạn chọn Setting",Toast.LENGTH_LONG).show();
                break;
            case R.id.menuLogout:
                final String user = actions.currentUser(MainActivity.this);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setTitle("Are you sure you want to log out? - "+ user);
                alertDialogBuilder.setMessage("Your session will be destroyed after logout on this application.");
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder
                        .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               boolean logout =  actions.logout(MainActivity.this);
                               if (logout == true){
                                   Intent i = new Intent(MainActivity.this, LoginActivity.class);
                                   startActivity(i);
                                   finish();
                               }
                            }
                        })
                        .setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        })
                        .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            //                              Cancel and close the dialog
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click back again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
