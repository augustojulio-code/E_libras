package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.Fragment_Atividade_7_Previa;

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

import com.bumptech.glide.Glide;
import com.example.e_libas_v_0_01.R;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.Evento_Botao.Manipula_Button;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.Fragment_Atividade_6_Previa.Fragment_Atividade_6_2;

public class Fragment_Atividade_7_1 extends Fragment implements View.OnClickListener
{
    int pontos, acertos,erros;
    Button opcao01,opcao02,opcao03,opcao04;
    ImageView gif_palavra,btn_next;
    Manipula_Button evento_click = new Manipula_Button();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.layout_atividades_palavras, container,false);

        opcao01 = view.findViewById(R.id.btn_opcao_01_palavra);
        opcao02 = view.findViewById(R.id.btn_opcao_02_palavra);
        opcao03 = view.findViewById(R.id.btn_opcao_03_palavra);
        opcao04 = view.findViewById(R.id.btn_opcao_04_palavra);

        gif_palavra = view.findViewById(R.id.img_palavra_atv);
        btn_next = view.findViewById(R.id.btn_proxima_palavra);

        Glide.with(getActivity()).load(R.drawable.animais__elefante).into(gif_palavra);

        opcao01.setText("Coelho");
        opcao02.setText("Elefante");
        opcao03.setText("Gato");
        opcao04.setText("Cachorro");

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
            erros++;

            evento_click.TrocarCorBotao2(opcao01,opcao02,opcao03,opcao04);

            evento_click.Desabilitar_botao(opcao01,opcao02,opcao03,opcao04,btn_next);

        }
        if (view == opcao02)
        {
            acertos++;

            pontos = pontos+40;

            evento_click.TrocarCorBotao2(opcao01,opcao02,opcao03,opcao04);

            evento_click.Desabilitar_botao(opcao01,opcao02,opcao03,opcao04,btn_next);
        }
        if (view == opcao03)
        {
            erros++;

            evento_click.TrocarCorBotao2(opcao01,opcao02,opcao03,opcao04);

            evento_click.Desabilitar_botao(opcao01,opcao02,opcao03,opcao04,btn_next);
        }
        if (view == opcao04)
        {
            erros++;

            evento_click.TrocarCorBotao2(opcao01,opcao02,opcao03,opcao04);

            evento_click.Desabilitar_botao(opcao01,opcao02,opcao03,opcao04,btn_next);
        }
        if (view == btn_next)
        {
            Bundle bundle = new Bundle();
            Fragment_Atividade_7_2 fragment = new Fragment_Atividade_7_2();
            bundle.putInt("pontos", pontos);
            bundle.putInt("acertos",acertos);
            bundle.putInt("erros",erros);

            fragment.setArguments(bundle);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_previa_Atividade_07, fragment);
            transaction.commit();
        }
    }

    @Override
    public void onStart()
    {
        super.onStart();
        btn_next.setEnabled(false);

        Bundle bundle_at5 = getArguments();

        pontos = bundle_at5.getInt("pontos");
        acertos = bundle_at5.getInt("acertos");
        erros = bundle_at5.getInt("erros");
    }
}
