package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.Evento_Botao;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.e_libas_v_0_01.MainFragmentMenu;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.modelo.Perguntas;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Evento_Firebase
{
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference_Perguntas;


    public void Update_pontos(int retorno,int pontos)
    {
        firebaseAuth = FirebaseAuth.getInstance();

        final FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference("Userscore").child(user.getUid()).child("pontos");

        pontos = pontos +retorno;

        databaseReference.setValue(pontos).addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if (task.isSuccessful())
                {

                }
            }
        });
    }


    public void Inserir_Pergunta(Perguntas pergunta)
    {
        firebaseAuth = FirebaseAuth.getInstance();

        final FirebaseUser user = firebaseAuth.getCurrentUser();



    }
}
