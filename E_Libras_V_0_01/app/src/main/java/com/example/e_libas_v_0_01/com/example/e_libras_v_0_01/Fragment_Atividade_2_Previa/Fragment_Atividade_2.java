package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.Fragment_Atividade_2_Previa;

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
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.Fragment_Atividades_01.Fragment_Atividade_2_10;

public class Fragment_Atividade_2 extends Fragment implements View.OnClickListener
{
    int pontos =0;
    Button opcao01,opcao02,opcao03,opcao04;
    ImageView img_letra,btn_next;
    Manipula_Button evento_click = new Manipula_Button();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.layout_atividades_public, container,false);

        opcao01 = view.findViewById(R.id.btn_opcao_01_atv);
        opcao02 = view.findViewById(R.id.btn_opcao_02_atv);
        opcao03 = view.findViewById(R.id.btn_opcao_03_atv);
        opcao04 = view.findViewById(R.id.btn_opcao_04_atv);

        img_letra = view.findViewById(R.id.img_letra_atv);
        btn_next = view.findViewById(R.id.btn_proxima);

        img_letra.setImageResource(R.drawable.d);

        opcao01.setText("A");
        opcao02.setText("C");
        opcao03.setText("F");
        opcao04.setText("D");

        btn_next.setOnClickListener(this);
        opcao01.setOnClickListener(this);
        opcao02.setOnClickListener(this);
        opcao03.setOnClickListener(this);
        opcao04.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view)
    {
        if (view == opcao01)
        {
            evento_click.TrocarCorBotao4(opcao01,opcao02,opcao03,opcao04);

            evento_click.Desabilitar_botao(opcao01,opcao02,opcao03,opcao04,btn_next);

        }
        if (view == opcao02)
        {
            evento_click.TrocarCorBotao4(opcao01,opcao02,opcao03,opcao04);

            evento_click.Desabilitar_botao(opcao01,opcao02,opcao03,opcao04,btn_next);
        }
        if (view == opcao03)
        {
            evento_click.TrocarCorBotao4(opcao01,opcao02,opcao03,opcao04);

            evento_click.Desabilitar_botao(opcao01,opcao02,opcao03,opcao04,btn_next);
        }
        if (view == opcao04)
        {
            pontos = pontos+40;

            evento_click.TrocarCorBotao4(opcao01,opcao02,opcao03,opcao04);

            evento_click.Desabilitar_botao(opcao01,opcao02,opcao03,opcao04,btn_next);
        }
        if (view == btn_next)
        {
            Bundle bundle = new Bundle();
            Fragment_Atividade_2_1 fragment = new Fragment_Atividade_2_1();
            bundle.putInt("pontos", pontos);

            fragment.setArguments(bundle);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_previa_Atividade_02,fragment);
            transaction.commit();
        }
    }

    @Override
    public void onStart()
    {
        super.onStart();
        btn_next.setEnabled(false);
    }
}
