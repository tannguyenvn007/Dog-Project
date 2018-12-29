package com.example.tannguyen.moneyapplication;

import android.content.Intent;
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


        btnNap = (Button)findViewById(R.id.main_btnNap);
        btnHistory = (Button)findViewById(R.id.main_btnHistory);
        btnProfile = (Button)findViewById(R.id.main_btnProfile);

        btnNap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TopUpWalletActivity.class);
                startActivity(i);
            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, History.class);
                startActivity(i);
            }
        });
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HistoryUser.class);
                startActivity(i);
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
