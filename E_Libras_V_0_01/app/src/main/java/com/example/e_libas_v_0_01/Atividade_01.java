package com.example.e_libas_v_0_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.Fragment_Atividades_01.Fragment_Atividade_1_10;

public class Atividade_01 extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_01);

        getSupportFragmentManager().beginTransaction().replace
                (R.id.fragment_container_Atividade_01, new Fragment_Atividade_1_10()).commit();


    }

}
