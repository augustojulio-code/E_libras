package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.Fragment_Atividades_01;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.e_libas_v_0_01.R;

public class Fragment_Atividade_2_10 extends Fragment implements View.OnClickListener
{
    Button proximo, exibir;
    int pontos;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_atividade_2_10,container,false);

        proximo = view.findViewById(R.id.btn_atividade_2_10);
        exibir = view.findViewById(R.id.btn_atividade_2_10_exibir);

        Bundle bundle_at2 = getArguments();

        pontos = bundle_at2.getInt("pontos");



        proximo.setOnClickListener(this);
        exibir.setOnClickListener(this);




        return view;
    }

    @Override
    public void onClick(View view)
    {
        if (view == proximo)
        {
            pontos = pontos+ 3;

            Bundle bundle_at2= new Bundle();

            bundle_at2.putInt("ponto_at2", pontos);

            Fragment_Atividade_3_10 fragment_atividade_3_10= new Fragment_Atividade_3_10();

            fragment_atividade_3_10.setArguments(bundle_at2);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container_Atividade_01,fragment_atividade_3_10);

            transaction.commit();

        }
        if (view == exibir)
        {
            Toast.makeText(getContext(),""+pontos,Toast.LENGTH_LONG).show();
        }
    }



}

