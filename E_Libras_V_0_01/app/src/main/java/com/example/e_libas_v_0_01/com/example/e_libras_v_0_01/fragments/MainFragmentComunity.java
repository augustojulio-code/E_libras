package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.e_libas_v_0_01.R;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.Evento_Botao.Evento_Firebase;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.modelo.Perguntas;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.modelo.Respostas;
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

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    Perguntas pergunta_selecionada;

    private List<Perguntas> listPerguntas = new ArrayList<Perguntas>();
    private ArrayAdapter<Perguntas> arrayAdapter;

    ImageView btn_voltar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {


        View view = inflater.inflate(R.layout.layout_comunity,container,false);

        lista = view.findViewById(R.id.listperguntas);
        btn_pergunta = view.findViewById(R.id.btnpergunta);
        edt_pergunta = view.findViewById(R.id.txtpergunta);
        btn_voltar = view.findViewById(R.id.btnvoltar_pergunta);


        btn_pergunta.setOnClickListener(this);
        btn_voltar.setOnClickListener(this);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l)
            {
                pergunta_selecionada = (Perguntas)parent.getItemAtPosition(position);
                String pergunta_id = pergunta_selecionada.getPerg_id();

                Bundle bundle = new Bundle();

                bundle.putString("pergunta_id",pergunta_id);

                MainFragmentComuResposta fragment = new MainFragmentComuResposta();

                fragment.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment)
                        .commit();
            }
        });

        arrayAdapter = new ArrayAdapter<Perguntas>(getActivity(),android.R.layout.simple_list_item_1,listPerguntas);

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
        else if (view == btn_voltar)
        {
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainFragmentConfig())
                    .commit();
        }

    }

}
