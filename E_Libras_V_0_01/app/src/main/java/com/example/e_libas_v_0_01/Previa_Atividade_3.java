package com.example.e_libas_v_0_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.Fragment_Atividade_3_Previa.Fragment_Previa_Splash_Atv_3;

public class Previa_Atividade_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previa__atividade_3);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_previa_Atividade_03,new Fragment_Previa_Splash_Atv_3()).commit();
    }
}
