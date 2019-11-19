package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.Fragment_Atividade_7_Previa;

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

import com.bumptech.glide.Glide;
import com.example.e_libas_v_0_01.R;

public class Fragment_Previa_7_3 extends Fragment implements View.OnClickListener
{
    ImageView gif, btn_next;
    TextView texto_txt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.layout_palavras_custom, container,false);

        gif = view.findViewById(R.id.image_palavra);
        texto_txt = view.findViewById(R.id.text_palavra);
        btn_next = view.findViewById(R.id.btn_next_preview);

        Glide.with(getActivity()).load(R.drawable.animais_gato).into(gif);


        texto_txt.setText("Gato");

        btn_next.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view)
    {
        if (view == btn_next)
        {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_previa_Atividade_07,new Fragment_Previa_7_4());
            transaction.commit();
        }

    }
}
