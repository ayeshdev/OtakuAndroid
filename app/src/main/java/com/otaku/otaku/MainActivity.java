package com.otaku.otaku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.otaku.otaku.Fragments.AccountFragment;
import com.otaku.otaku.Fragments.CartFragment;
import com.otaku.otaku.Fragments.CatalogFragment;
import com.otaku.otaku.Fragments.FavoriteFragment;
import com.otaku.otaku.Fragments.HomeFragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.otaku.otaku.Fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, NavigationBarView.OnItemSelectedListener {


    private DrawerLayout sideNavbar;
    private NavigationView navigationView;
    private MaterialToolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        sideNavbar = findViewById(R.id.mainDrawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolBar);
        bottomNavigationView = findViewById(R.id.bottomNavigationBar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(MainActivity.this,sideNavbar,R.string.side_navbar_open,R.string.side_navbar_close);
        sideNavbar.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sideNavbar.open();
            }
        });

        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView.setOnItemSelectedListener(this);
    }


    private void updateUI(FirebaseUser user){
        if (user == null){
            Toast.makeText(MainActivity.this,"Please verify your email address",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.navHome){
            loadFragmentView(new HomeFragment());
            return true;
        }else if (item.getItemId() == R.id.navCatalog){
            loadFragmentView(new CatalogFragment());
            return true;
        }else if (item.getItemId() == R.id.navCart){
            loadFragmentView(new CartFragment());
            return true;
        }else if (item.getItemId() == R.id.navFavorite){
            loadFragmentView(new FavoriteFragment());
            return true;
        }else if (item.getItemId() == R.id.navAccount){
            loadFragmentView(new ProfileFragment());
            return true;
        }else;
        return false;
    }

    public void loadFragmentView(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }
}