package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.Fragment_Atividades_01;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.e_libas_v_0_01.Previa_Atividade_1;
import com.example.e_libas_v_0_01.R;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.Evento_Botao.Manipula_Button;

public class Fragment_Atividade_2_10 extends Fragment implements View.OnClickListener
{
    Button opcao_01, opcao_02,opcao_03,opcao_04;
    int pontos,acertos,erros;
    ImageView proximo;
    Manipula_Button evento_click = new Manipula_Button();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_atividade_2_10,container,false);

        proximo = view.findViewById(R.id.btn_proximo02_atividade01);
        opcao_01= view.findViewById(R.id.btn_atividade1_2_10_opcao1);
        opcao_02= view.findViewById(R.id.btn_atividade1_2_10_opcao2);
        opcao_03= view.findViewById(R.id.btn_atividade1_2_10_opcao3);
        opcao_04= view.findViewById(R.id.btn_atividade1_2_10_opcao4);





        proximo.setOnClickListener(this);
        opcao_01.setOnClickListener(this);
        opcao_02.setOnClickListener(this);
        opcao_03.setOnClickListener(this);
        opcao_04.setOnClickListener(this);




        return view;
    }

    @Override
    public void onClick(View view)
    {
        if (view == proximo)
        {

            Bundle bundle_at2= new Bundle();

            bundle_at2.putInt("ponto_at2", pontos);
            bundle_at2.putInt("acertos",acertos);
            bundle_at2.putInt("erros",erros);

            Fragment_Atividade_3_10 fragment_atividade_3_10= new Fragment_Atividade_3_10();

            fragment_atividade_3_10.setArguments(bundle_at2);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container_Atividade_01,fragment_atividade_3_10);

            transaction.commit();

        }
        if (view == opcao_01)
        {
            acertos ++;

            pontos = pontos +40;

            evento_click.TrocarCorBotao1(opcao_01,opcao_02,opcao_03,opcao_04);
            evento_click.Desabilitar_botao(opcao_01,opcao_02,opcao_03,opcao_04,proximo);
        }
        if (view == opcao_02)
        {
            erros++;

            evento_click.TrocarCorBotao1(opcao_01,opcao_02,opcao_03,opcao_04);
            evento_click.Desabilitar_botao(opcao_01,opcao_02,opcao_03,opcao_04,proximo);
        }
        if (view == opcao_03)
        {
            erros++;

            evento_click.TrocarCorBotao1(opcao_01,opcao_02,opcao_03,opcao_04);
            evento_click.Desabilitar_botao(opcao_01,opcao_02,opcao_03,opcao_04,proximo);
        }
        if (view == opcao_04)
        {
            erros++;

            evento_click.TrocarCorBotao1(opcao_01,opcao_02,opcao_03,opcao_04);
            evento_click.Desabilitar_botao(opcao_01,opcao_02,opcao_03,opcao_04,proximo);
        }
    }

    @Override
    public void onStart()
    {
        super.onStart();

        Bundle bundle_at2 = getArguments();

        pontos = bundle_at2.getInt("pontos");

        acertos = bundle_at2.getInt("acertos");

        erros = bundle_at2.getInt("erros");

        proximo.setEnabled(false);


    }
}

