package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.Fragment_Atividade_1_Previa;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.e_libas_v_0_01.Atividade_01;
import com.example.e_libas_v_0_01.R;

public class Fragment_Previa_01 extends Fragment implements View.OnClickListener
{
    Button proximo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_previa_01_ativ_1,container,false);

        proximo = view.findViewById(R.id.btn_previa_01_atvi_01);

        proximo.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view)
    {
        if (view == proximo)
        {
            Fragment_Previa_02 fragmentPrevia02 = new Fragment_Previa_02();

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_previa_Atividade_01,fragmentPrevia02);
            transaction.commit();



        }

    }
}
