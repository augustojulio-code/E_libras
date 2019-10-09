package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.Fragment_Atividades_01;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.e_libas_v_0_01.R;

public class Fragment_Atividade_Splash extends Fragment
{
    ImageView view_gif_atividade;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_atividade_splash,container,false);

        view_gif_atividade = view.findViewById(R.id.gif_atividade);

        Glide.with(getActivity()).load(R.drawable.gif_previa).into(view_gif_atividade);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_Atividade_01,new Fragment_Atividade_1_10());
                transaction.commit();
            }
        },3500);

        return view;
    }


}
