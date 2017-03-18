package com.andack.indoorman;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("OldDriver");
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
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav4zai_nan_fulishe:
                Toast.makeText(this, "宅男福利社", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case R.id.nav4zai_nan_cat:
                Toast.makeText(this, "宅男猫", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case R.id.nav4beat_girls:
                Toast.makeText(this, "氢美女", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case R.id.nav4fan_hao:
                Toast.makeText(this, "番号库", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case R.id.nav4author:
                Toast.makeText(this, "关于作者", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case R.id.nav4exit:
                Toast.makeText(this, "退出", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
        }
        return true;
    }
}
