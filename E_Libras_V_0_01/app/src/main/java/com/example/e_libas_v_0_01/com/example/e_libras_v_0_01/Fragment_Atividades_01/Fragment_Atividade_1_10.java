package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.Fragment_Atividades_01;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.drawable.Drawable;
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

import com.example.e_libas_v_0_01.R;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.Evento_Botao.Manipula_Button;

public class Fragment_Atividade_1_10 extends Fragment implements View.OnClickListener
{
    Button opcao_01,opcao_02,opcao_03,opcao_04;
    int pontos = 0, acertos =0, erros=0;
    ImageView proximo;
    Manipula_Button evento_click = new Manipula_Button();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_atividade_1_10,container, false);

        proximo = view.findViewById(R.id.btn_proximo01_atividade01);

        opcao_01 = view.findViewById(R.id.btn_atividade1_1_10_opcao1);
        opcao_02 = view.findViewById(R.id.btn_atividade1_1_10_opcao2);
        opcao_03 = view.findViewById(R.id.btn_atividade1_1_10_opcao3);
        opcao_04 = view.findViewById(R.id.btn_atividade1_1_10_opcao4);

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
            Bundle bundle = new Bundle();
            Fragment_Atividade_2_10  fragment2 = new Fragment_Atividade_2_10();
            fragment2.setArguments(bundle);

            bundle.putInt("acertos",acertos);
            bundle.putInt("erros",erros);
            bundle.putInt("pontos", pontos);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_Atividade_01,fragment2);
            transaction.commit();


        }
        if (view == opcao_01)
        {
            erros++;
            // Setando as cores com click

            evento_click.TrocarCorBotao4(opcao_01,opcao_02,opcao_03,opcao_04);

            //Desabilitandos os botões após click

            evento_click.Desabilitar_botao(opcao_01,opcao_02,opcao_03,opcao_04,proximo);

        }
        if (view == opcao_02)
        {
            erros++;
            evento_click.TrocarCorBotao4(opcao_01,opcao_02,opcao_03,opcao_04);

            evento_click.Desabilitar_botao(opcao_01,opcao_02,opcao_03,opcao_04,proximo);


        }
        if (view == opcao_03)
        {
            erros++;

            evento_click.TrocarCorBotao4(opcao_01,opcao_02,opcao_03,opcao_04);

            evento_click.Desabilitar_botao(opcao_01,opcao_02,opcao_03,opcao_04,proximo);

        }
        if (view== opcao_04)
        {
            acertos++;

            pontos = pontos + 40;

            evento_click.TrocarCorBotao4(opcao_01,opcao_02,opcao_03,opcao_04);

            evento_click.Desabilitar_botao(opcao_01,opcao_02,opcao_03,opcao_04,proximo);

        }

    }

    @Override
    public void onStart()
    {
        super.onStart();

        proximo.setEnabled(false);
    }

}
