package com.example.tannguyen.moneyapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class History extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histories);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent = getIntent();
                String email = intent.getStringExtra("email");
                switch (menuItem.getItemId()){
                    case R.id.navigation_home:
                        Intent i = new Intent(History.this,MainActivity.class);
                        i.putExtra("email",email);
                        startActivity(i);
                        finish();
                        break;
                    case R.id.navigation_history:
                        break;
                    case R.id.navigation_wallet:
                        Intent a = new Intent(History.this,TopUpWalletActivity.class);
                        a.putExtra("email",email);
                        startActivity(a);
                        finish();
                        break;

                }
                return false;
            }
        });
    }
}
