package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.Fragment_Atividades_01;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.e_libas_v_0_01.R;

public class Fragment_Atividade_5_10 extends Fragment implements View.OnClickListener
{
    Button proximo_at5, exibir_at5;
    int pontos;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_atividade_5_10,container,false);

        proximo_at5 = view.findViewById(R.id.btn_atividade_5_10);
        exibir_at5 = view.findViewById(R.id.btn_atividade_5_10_exibir);

        proximo_at5.setOnClickListener(this);
        exibir_at5.setOnClickListener(this);


        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();

        Bundle bundle_at5 = getArguments();

        pontos = bundle_at5.getInt("pontos_at4");
    }

    @Override
    public void onClick(View view)
    {
        if (view == proximo_at5)
        {

        }
        if (view == exibir_at5)
        {
            Toast.makeText(getActivity(),""+pontos,Toast.LENGTH_LONG).show();
        }
    }
}
