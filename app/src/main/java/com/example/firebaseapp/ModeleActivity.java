package com.example.firebaseapp;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;


public class ModeleActivity extends AppCompatActivity {
    private Fragment feed;
    private Fragment uploader;
    private Fragment profile;


    private FragmentManager fragmentManager = getSupportFragmentManager();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.addItem:
                    fragmentManager.beginTransaction().replace(R.id.frame_id_modele,uploader).commit();
                    return true;
                case R.id.mannequinat:
                    fragmentManager.beginTransaction().replace(R.id.frame_id_modele,feed).commit();
                    return true;
                case R.id.profile:
                    fragmentManager.beginTransaction().replace(R.id.frame_id_modele,profile).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modele);
        BottomNavigationView navView = findViewById(R.id.nav_view_modele);

        this.feed = new Feed();
        this.profile = new Profile();
        this.uploader = new Uploader();

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        this.fragmentManager.beginTransaction().replace(R.id.frame_id_modele,profile).commit();
    }

}
