package com.example.tannguyen.moneyapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {
    ListView mListView;
    ArrayList<HistoryAct> mData = new ArrayList<>();
    int position;
    ListAdapter adapter;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histories);
        mListView = (ListView)findViewById(R.id.history_listview);
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

        Actions actions = new Actions();
        actions.parseConnection(History.this);

        ParseUser currentUser = ParseUser.getCurrentUser();
        Log.i("username", currentUser.getEmail());
        ParseQuery<ParseObject> query = ParseQuery.getQuery("history");
        Log.i("usernamess1", "Wait");
        query.whereEqualTo("userEmail",currentUser.getEmail());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < objects.size(); i++) {
                        HistoryAct history = new HistoryAct();
                        history.setAddress(String.valueOf(objects.get(i).get("Address")));
                        history.setMoney(String.valueOf(objects.get(i).get("totalMoney")));
                        history.setTime(String.valueOf(objects.get(i).getCreatedAt()));
                        mData.add(history);
                    }
                } else {
                    System.out.println(e);
                }
                adapter = new ListAdapter(History.this, mData);
                mListView.setAdapter(adapter);


            }});



    }
}
