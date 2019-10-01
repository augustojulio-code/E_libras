package com.example.e_libas_v_0_01;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;

public class SpashScreen extends AppCompatActivity
{

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash_screen);


        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser()!= null)
        {

            finish();
            startActivity(new Intent(getApplicationContext(),MainFragmentMenu.class));

        }
        else
        {


            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    finish();
                    startActivity(new Intent(getApplicationContext(),ActivitySlides.class));
                }
            },4500);
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }


}
