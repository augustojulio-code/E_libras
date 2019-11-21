package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.e_libas_v_0_01.R;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.Evento_Botao.Evento_Firebase;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.modelo.Perguntas;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class MainFragmentComunity extends Fragment implements View.OnClickListener
{
    ListView lista;
    Button btn_pergunta;
    EditText edt_pergunta;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    Evento_Firebase evento_firebase = new Evento_Firebase();


    private List<Perguntas> listPerguntas = new ArrayList<Perguntas>();
    private ArrayAdapter<Perguntas> arrayAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {


        View view = inflater.inflate(R.layout.layout_comunity,container,false);

        lista = view.findViewById(R.id.listperguntas);
        btn_pergunta = view.findViewById(R.id.btnpergunta);
        edt_pergunta = view.findViewById(R.id.txtpergunta);


        btn_pergunta.setOnClickListener(this);



        return view;
    }


    @Override
    public void onStart()
    {
        super.onStart();

        firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference("Perguntas");

        databaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (!dataSnapshot.exists())
                {
                    return;
                }

                listPerguntas.clear();

                for (DataSnapshot objSnapshot: dataSnapshot.getChildren())
                {
                    Perguntas p = objSnapshot.getValue(Perguntas.class);
                    listPerguntas.add(p);
                }

                arrayAdapter = new ArrayAdapter<Perguntas>(getActivity(),android.R.layout.simple_list_item_1,listPerguntas);

                lista.setAdapter(arrayAdapter);
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
        if (view == btn_pergunta)
        {
           Perguntas pergunta = new Perguntas();

           pergunta.setPerg_id(UUID.randomUUID().toString());
           pergunta.setPergunta(edt_pergunta.getText().toString());
           pergunta.setUser_id(firebaseAuth.getUid());

            databaseReference.child(pergunta.getPerg_id()).setValue(pergunta).addOnCompleteListener(new OnCompleteListener<Void>()
            {
                @Override
                public void onComplete(@NonNull Task<Void> task)
                {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(getActivity(),"Comentario enviado",Toast.LENGTH_LONG).show();
                    }
                }
            });

           edt_pergunta.setText("");
        }

    }

}