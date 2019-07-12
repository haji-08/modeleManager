package com.example.firebaseapp;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private Fragment connexion;
    private Fragment register;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.connexion:
                    fragmentManager.beginTransaction().replace(R.id.frame_id,connexion).commit();
                    return true;
                case R.id.inscription:
                    fragmentManager.beginTransaction().replace(R.id.frame_id,register).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        this.connexion = new Connexion();
        this.register = new Register();

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        this.fragmentManager.beginTransaction().replace(R.id.frame_id,connexion).commit();
    }

}
