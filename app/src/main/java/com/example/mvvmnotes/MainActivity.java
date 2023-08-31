package com.example.mvvmnotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

//    NavController navController;
//    AppBarConfiguration appBarConfiguration;
//    DrawerLayout drawerLayout;
//    NavigationView navigationView;
//    NavHostFragment navHostFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
//        navController = navHostFragment.getNavController();
//        drawerLayout=findViewById(R.id.root_drawer_layout);
//        navigationView=findViewById(R.id.navigation_view);
//
//        appBarConfiguration= new AppBarConfiguration.Builder(navController.getGraph()).setDrawerLayout(drawerLayout).build();
//
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        //NavigationUI.setupWithNavController(navigationView,navController);
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController mNavigationController = Navigation.findNavController(this,R.id.fragmentContainerView);
//        return NavigationUI.navigateUp(mNavigationController,appBarConfiguration) || super.onSupportNavigateUp();
//    }
}