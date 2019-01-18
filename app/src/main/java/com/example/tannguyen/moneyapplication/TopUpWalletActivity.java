package com.example.tannguyen.moneyapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class TopUpWalletActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up_wallet);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent = getIntent();
                String username = intent.getStringExtra("username");
                switch (menuItem.getItemId()){
                    case R.id.navigation_home:
                        Intent i = new Intent(TopUpWalletActivity.this,MainActivity.class);
                        i.putExtra("username",username);
                        startActivity(i);
                        finish();
                        break;
                    case R.id.navigation_history:
                        Intent a = new Intent(TopUpWalletActivity.this,History.class);
                        a.putExtra("username",username);
                        startActivity(a);
                        finish();
                        break;
                    case R.id.navigation_wallet:
                        break;

                }
                return false;
            }
        });
    }
}
