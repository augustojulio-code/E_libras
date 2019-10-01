package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.e_libas_v_0_01.Atividade_01;
import com.example.e_libas_v_0_01.MainActivity;
import com.example.e_libas_v_0_01.Previa_Atividade_1;
import com.example.e_libas_v_0_01.ProfileLogin;
import com.example.e_libas_v_0_01.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainFragmentHome extends Fragment implements View.OnClickListener
{

    FirebaseAuth firebaseAuth;

    DatabaseReference reference;

    TextView nickname;

    Button atividade;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        firebaseAuth = FirebaseAuth.getInstance();

        View view = inflater.inflate(R.layout.fragment_home,container, false);

        nickname = (TextView) view.findViewById(R.id.txthomeusuario);

        Button botao = (Button) view.findViewById(R.id.botao123);

         atividade = view.findViewById(R.id.botao_atividade);


        botao.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(getActivity(), ProfileLogin.class));
            }
        });

        atividade.setOnClickListener(this);


        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();

        final FirebaseUser user = firebaseAuth.getCurrentUser();

        reference = FirebaseDatabase.getInstance().getReference("Usuario").child(user.getUid());

        reference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (!dataSnapshot.exists())
                {
                    return;
                }
                String nick = dataSnapshot.child("apelido").getValue().toString();

                nickname.setText("Olá "+ nick);


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
        if (view == atividade)
        {
            startActivity(new Intent(getActivity(), Previa_Atividade_1.class));
        }
    }
}
