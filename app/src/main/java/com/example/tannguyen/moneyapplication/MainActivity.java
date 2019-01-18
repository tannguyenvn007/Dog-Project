package com.example.tannguyen.moneyapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import java.io.File;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;


public class MainActivity extends AppCompatActivity {
    String TAG = "GenerateQRCode";
    Actions actions = new Actions();
    boolean doubleBackToExitPressedOnce = false;
    QRGEncoder qrgEncoder;
    Bitmap bitmap;
    String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
    String inputValue;
    ImageView qrImage;
    Button btnGenerate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actions.parseConnection(MainActivity.this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        qrImage = (ImageView)findViewById(R.id.QR_Image) ;
        btnGenerate =(Button)findViewById(R.id.generate);

        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent intent = getIntent();
                String username = intent.getStringExtra("username");
                switch (menuItem.getItemId()){
                    case R.id.navigation_home:
                        break;
                    case R.id.navigation_history:
                        Intent a = new Intent(MainActivity.this,History.class);
                        a.putExtra("username",username);
                        startActivity(a);
                        break;
                    case R.id.navigation_wallet:
                        Intent i = new Intent(MainActivity.this,TopUpWalletActivity.class);
                        i.putExtra("username",username);
                        startActivity(i);
                        break;
                }
                return false;
            }
        });
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        inputValue = username.toString().trim();
        File f = new File(savePath+inputValue+".jpg");
        if (f.isFile()){
            qrImage.setImageURI(Uri.parse(savePath+inputValue+".jpg"));
            btnGenerate.setVisibility(View.GONE);
        }else{
            btnGenerate.setVisibility(View.VISIBLE);
        }


        btnGenerate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean save;
                String result;
                Intent intent = getIntent();
                String username = intent.getStringExtra("username");
                inputValue = username.toString().trim();
                if (inputValue.length() > 0){
                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);
                    int width = point.x;
                    int height = point.y;
                    int smallerDimension = width < height ? width : height;
                    smallerDimension = smallerDimension * 3 / 4;

                    qrgEncoder = new QRGEncoder(inputValue,null,QRGContents.Type.TEXT,smallerDimension);
                    try {
                        bitmap = qrgEncoder.encodeAsBitmap();
                        qrImage.setImageBitmap(bitmap);
                        btnGenerate.setVisibility(View.GONE);
                    } catch (WriterException e) {
                        Log.v(TAG, e.toString());
                    }
                }
                try {
                    save = QRGSaver.save(savePath,inputValue,bitmap,QRGContents.ImageType.IMAGE_JPEG);
                    result = save ? "Image Saved" : "Image Not Saved";
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                } catch (WriterException e) {
                    e.printStackTrace();
                }
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
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        switch (item.getItemId()){
            case R.id.menProfile:
                Intent i = new Intent(MainActivity.this, HistoryUser.class);
                i.putExtra("username",username);
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
