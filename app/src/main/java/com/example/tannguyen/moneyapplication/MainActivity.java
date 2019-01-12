package com.example.tannguyen.moneyapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnNap, btnHistory, btnProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
