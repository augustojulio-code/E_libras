package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.Fragment_Atividade_3_Previa;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.e_libas_v_0_01.R;

public class Fragment_Previa_3_2 extends Fragment implements View.OnClickListener
{
    ImageView letra, btn_next;
    TextView texto_txt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_previa_01_ativ_1, container,false);

        letra = view.findViewById(R.id.image_letra);
        texto_txt = view.findViewById(R.id.text_letra);
        btn_next = view.findViewById(R.id.btn_previa_01_atvi_01);

        letra.setImageResource(R.drawable.k);
        texto_txt.setText("K");

        btn_next.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view)
    {
        if (view == btn_next)
        {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_previa_Atividade_03,new Fragment_Previa_3_3());
            transaction.commit();
        }
    }
}
