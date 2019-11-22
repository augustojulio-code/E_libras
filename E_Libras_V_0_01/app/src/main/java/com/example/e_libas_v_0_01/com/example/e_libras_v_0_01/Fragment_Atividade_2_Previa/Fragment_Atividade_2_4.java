package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.Fragment_Atividade_2_Previa;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.Evento_Botao.Evento_Firebase;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.Evento_Botao.Manipula_Button;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Fragment_Atividade_2_4 extends Fragment implements View.OnClickListener
{
    Button opcao01,opcao02,opcao03,opcao04;
    ImageView img_letra,btn_next;
    Manipula_Button evento_click = new Manipula_Button();
    int pontos, acertos,erros;
    Evento_Firebase updatescore = new Evento_Firebase();
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    int retorno_pontos;

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

        img_letra.setImageResource(R.drawable.c);

        opcao01.setText("R");
        opcao02.setText("C");
        opcao03.setText("B");
        opcao04.setText("G");

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
}
