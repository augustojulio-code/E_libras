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

import com.example.e_libas_v_0_01.R;

public class Fragment_Atividade_1_10 extends Fragment implements View.OnClickListener
{
    Button proximo, exibir;
    int pontos = 2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_atividade_1_10,container, false);

        proximo = view.findViewById(R.id.btn_atividade_1_10);
        exibir = view.findViewById(R.id.btn_atividade_1_10_exibir);

        proximo.setOnClickListener(this);
        exibir.setOnClickListener(this);

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

            bundle.putInt("pontos", pontos);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_Atividade_01,fragment2);
            transaction.commit();


        }
        if (view == exibir)
        {

        }
    }
}
