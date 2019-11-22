package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.Fragment_Atividades_01;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.e_libas_v_0_01.R;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.Evento_Botao.Evento_Firebase;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.Evento_Botao.Manipula_Button;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Fragment_Atividade_5_10 extends Fragment implements View.OnClickListener
{
    Button opcao_01,opcao_02,opcao_03,opcao_04;
    ImageView proximo;
    int pontos, acertos, erros;
    Manipula_Button evento_click = new Manipula_Button();
    Evento_Firebase updatescore = new Evento_Firebase();
    DatabaseReference databaseReference, databaseReference2;
    FirebaseAuth firebaseAuth;
    int retorno_pontos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_atividade_5_10,container,false);

        proximo = view.findViewById(R.id.btn_proximo05_atividade01);
        opcao_01 = view.findViewById(R.id.btn_atividade1_5_10_opcao1);
        opcao_02 = view.findViewById(R.id.btn_atividade1_5_10_opcao2);
        opcao_03 = view.findViewById(R.id.btn_atividade1_5_10_opcao3);
        opcao_04 = view.findViewById(R.id.btn_atividade1_5_10_opcao4);

        proximo.setOnClickListener(this);
        opcao_01.setOnClickListener(this);
        opcao_02.setOnClickListener(this);
        opcao_03.setOnClickListener(this);
        opcao_04.setOnClickListener(this);


        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();

        Bundle bundle_at5 = getArguments();

        pontos = bundle_at5.getInt("pontos_at4");
        acertos = bundle_at5.getInt("acertos");
        erros = bundle_at5.getInt("erros");

        proximo.setEnabled(false);

        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference("Userscore").child(user.getUid());

        databaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String sponto= dataSnapshot.child("pontos").getValue().toString();

                retorno_pontos = Integer.parseInt(sponto);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });


    }

    @Override
    public void onClick(View view)
    {
        if (view == proximo)
        {
            StringBuffer buffer = new StringBuffer();

            buffer.append("Acertos: "+acertos+"\n");
            buffer.append("Erros  : "+erros+"\n");

            AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

            dialog.setTitle("Parabéns");
            dialog.setMessage(buffer);
            dialog.setIcon(R.mipmap.elibraslogo);
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    updatescore.Update_pontos(retorno_pontos,pontos);
                    getActivity().finish();

                    dialogInterface.dismiss();
                }
            });

            dialog.show();



        }
        if (view == opcao_01)
        {
            erros++;
            evento_click.TrocarCorBotao3(opcao_01,opcao_02,opcao_03,opcao_04);
            evento_click.Desabilitar_botao(opcao_01,opcao_02,opcao_03,opcao_04,proximo);
        }
        if (view == opcao_02)
        {
            erros++;
            evento_click.TrocarCorBotao3(opcao_01,opcao_02,opcao_03,opcao_04);
            evento_click.Desabilitar_botao(opcao_01,opcao_02,opcao_03,opcao_04,proximo);
        }
        if (view == opcao_03)
        {
            acertos++;

            pontos = pontos +40;

            evento_click.TrocarCorBotao3(opcao_01,opcao_02,opcao_03,opcao_04);
            evento_click.Desabilitar_botao(opcao_01,opcao_02,opcao_03,opcao_04,proximo);
        }
        if (view == opcao_04)
        {
            erros++;

            evento_click.TrocarCorBotao3(opcao_01,opcao_02,opcao_03,opcao_04);
            evento_click.Desabilitar_botao(opcao_01,opcao_02,opcao_03,opcao_04,proximo);
        }
    }


}
