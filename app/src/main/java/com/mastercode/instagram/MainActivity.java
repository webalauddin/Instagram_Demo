package com.mastercode.instagram;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    MaterialToolbar meterialToolbar;
    NavigationView navigationView;
    BottomNavigationView BottomNavigationView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM ){

            EdgeToEdge.enable(this);

        }

        setContentView(R.layout.activity_main);

        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM ){
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawerLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.coordinatorLayout), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, v.getPaddingBottom());
                return insets;
            });

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.navigationView), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, v.getPaddingBottom());
                return insets;
            });
        }


//==================================================================================================

        drawerLayout = findViewById(R.id.drawerLayout);
        meterialToolbar = findViewById(R.id.meterialToolbar);
        navigationView = findViewById(R.id.navigationView);
        BottomNavigationView = findViewById(R.id.BottomNavigationView);



        Fragment(new MainFragment());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (MainActivity.this, drawerLayout, meterialToolbar, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);


        meterialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId()==R.id.search){

                    Toast.makeText(MainActivity.this, "hi search", Toast.LENGTH_LONG).show();

                } else if (item.getItemId()==R.id.notification){

                    Toast.makeText(MainActivity.this, "hi Notification", Toast.LENGTH_LONG).show();

                }


                return false;
            }
        });


        BottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId()==R.id.home){

                    Fragment(new MainFragment());

                } else if (item.getItemId()==R.id.search){

                    Fragment(new SearchFragment());

                } else if (item.getItemId()==R.id.explore){

                    Fragment(new ExploreFragment());

                } else if (item.getItemId()==R.id.reels){

                    Fragment(new ReelsFragment());

                } else if (item.getItemId()==R.id.profile){

                    Fragment(new MainFragment());

                }


                return true;
            }
        });




    }
    //==============================//
    //==============================//

    private void Fragment (Fragment fragment){

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame, fragment);
        transaction.commit();

    }



}