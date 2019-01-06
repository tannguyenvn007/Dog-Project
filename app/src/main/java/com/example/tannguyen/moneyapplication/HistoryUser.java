package com.example.tannguyen.moneyapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class HistoryUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_user);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigation_home:
                        Intent i = new Intent(HistoryUser.this,MainActivity.class);
                        startActivity(i);
                    case R.id.navigation_profile:
                        break;
                    case R.id.navigation_history:
                        Intent a = new Intent(HistoryUser.this,History.class);
                        startActivity(a);
                        break;

                }
                return false;
            }
        });
    }
}
