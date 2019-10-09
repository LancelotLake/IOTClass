package com.my.study.iotclass;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.my.study.iotclass.Utils.SqlUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private final String url = "jdbc:mysql://rm-bp1s8wez9j4hw42oemo.mysql.rds.aliyuncs.com:3306/dizi-03";
    private final String username = "dizi03";
    private final String password = "Dizi1234";

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            ((TextView)findViewById(R.id.message)).setText((String)message.obj);
            String str = "查询不存在";
            if(message.what == 1) str = "查询成功";
            Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
            return false;
        }
    });


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage =  findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection cnn = null;
                cnn = SqlUtil.openConnection(url, username, password);
//                try {
//                    final String DRIVER = "com.mysql.jdbc.Driver";
//                    Class.forName(DRIVER);
//                    cnn = DriverManager.getConnection(url,username,password);
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
                Message msg = new Message();
                if (cnn == null) {
                    msg.what = 0;
                    msg.obj = "failure";
                    handler.sendMessage(msg);

                } else {
//                    System.out.print("success");
                    msg.what = 1;
                    msg.obj = "success";
                    handler.sendMessage(msg);
                }
            }
        }).start();


    }




}
