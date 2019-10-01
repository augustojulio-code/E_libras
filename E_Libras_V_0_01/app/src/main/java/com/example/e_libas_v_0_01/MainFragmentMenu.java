package com.example.e_libas_v_0_01;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.fragments.MainFragmentConfig;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.fragments.MainFragmentHome;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.fragments.MainFragmentRank;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.fragments.MainFragmentSuport;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.fragments.MainFramentDasboard;
import com.google.firebase.auth.FirebaseAuth;

public class MainFragmentMenu extends AppCompatActivity
{
    FirebaseAuth firebaseAuth;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            Fragment fragment = null;

            switch (item.getItemId())
            {
                case R.id.navigation_home:
                    fragment = new MainFragmentHome();
                    break;

                case R.id.navigation_dashboard:
                    fragment = new MainFramentDasboard();
                    break;

                case R.id.navigation_notifications:
                    fragment = new MainFragmentSuport();
                    break;

                case R.id.navigation_ranking:
                    fragment = new MainFragmentRank();
                    break;

                case R.id.navigation_config:
                    fragment = new MainFragmentConfig();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment_menu);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }


        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new MainFragmentHome()).commit();

    }

}
