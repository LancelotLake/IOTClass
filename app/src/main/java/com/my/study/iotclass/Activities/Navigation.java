package com.my.study.iotclass.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.my.study.iotclass.R;

public class Navigation extends AppCompatActivity {

    private HomeFrag homeFrag;
    private BookFrag bookFrag;
    private AccountFrag accountFrag;
    private SelfFrag selfFrag;
    private FragmentTransaction transaction;
    private FragmentManager fragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    transaction.replace(R.id.content, homeFrag);
                    transaction.commit();
                    return true;
                case R.id.navigation_book:
                    transaction.replace(R.id.content, bookFrag);
                    transaction.commit();
                    return true;
                case R.id.navigation_account:
                    transaction.replace(R.id.content, accountFrag);
                    transaction.commit();
                    return true;
                case R.id.navigation_self:
                    transaction.replace(R.id.content, selfFrag);
                    transaction.commit();
                    return true;
            }
            return false;
        }
    };

    private void init() {
        homeFrag = new HomeFrag();
        selfFrag = new SelfFrag();
        bookFrag = new BookFrag();
        accountFrag = new AccountFrag();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content,new HomeFrag());
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        init();
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
