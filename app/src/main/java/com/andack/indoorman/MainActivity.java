package com.andack.indoorman;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.andack.indoorman.Fragment.ZaiNanFuLiShe;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ZaiNanFuLiShe zaiNanFuLiSheFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("OldDriver");
        zaiNanFuLiSheFragment=new ZaiNanFuLiShe();
        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        toolbar.setElevation(0);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        navigationView= (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout= (DrawerLayout) findViewById(R.id.drawerlayout);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        FragmentManager fragmentManager=this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_main,zaiNanFuLiSheFragment);
        fragmentTransaction.commit();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav4zai_nan_fulishe:
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case R.id.nav4zai_nan_cat:
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case R.id.nav4beat_girls:
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case R.id.nav4fan_hao:
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case R.id.nav4author:
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case R.id.nav4exit:
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
        }
        return true;
    }
}
