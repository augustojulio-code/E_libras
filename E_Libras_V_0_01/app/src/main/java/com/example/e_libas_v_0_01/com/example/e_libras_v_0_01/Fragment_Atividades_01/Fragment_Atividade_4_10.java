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

public class Fragment_Atividade_4_10 extends Fragment implements View.OnClickListener
{

    Button proximo_at4, exibir_at4;
    int pontos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_atividade_4_10,container,false);

        proximo_at4 = view.findViewById(R.id.btn_atividade_4_10);
        exibir_at4 = view.findViewById(R.id.btn_atividade_4_10_exibir);

        proximo_at4.setOnClickListener(this);
        exibir_at4.setOnClickListener(this);



        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Bundle bundle_at4 = getArguments();

        pontos = bundle_at4.getInt("pontos_at3");


    }

    @Override
    public void onClick(View view)
    {
        if (view == proximo_at4)
        {
            pontos = pontos + 2;
            Bundle bundle_at4_proximo= new Bundle();

            bundle_at4_proximo.putInt("pontos_at4", pontos);

            Fragment_Atividade_5_10 fragmentAtividade510 = new Fragment_Atividade_5_10();
            fragmentAtividade510.setArguments(bundle_at4_proximo);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_Atividade_01,fragmentAtividade510);
            transaction.commit();
        }
        if (view == exibir_at4)
        {
            Toast.makeText(getActivity(),""+pontos,Toast.LENGTH_LONG).show();
        }
    }
}
