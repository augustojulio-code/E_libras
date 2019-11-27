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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_libas_v_0_01.R;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.modelo.Perguntas;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.modelo.Respostas;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainFragmentComuResposta extends Fragment implements View.OnClickListener
{
    TextView pergunta;
    ListView listareposta;
    Button btn_resposta;
    EditText edt_resposta;
    ImageView btn_voltar;

    String pergunta_id;

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    Query query, query_respostas;


    private List<Respostas> respostasArrayList = new ArrayList<Respostas>();
    private ArrayAdapter<Respostas> arrayAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.layout_respostas,container,false);

        pergunta = view.findViewById(R.id.txtviewpergunta);
        btn_voltar = view.findViewById(R.id.btnvoltar_resposta);
        edt_resposta = view.findViewById(R.id.txtresposta);
        btn_resposta = view.findViewById(R.id.btnresposta);
        listareposta = view.findViewById(R.id.listrespostas);

        btn_voltar.setOnClickListener(this);
        btn_resposta.setOnClickListener(this);


        arrayAdapter = new ArrayAdapter<Respostas>(getActivity(),android.R.layout.simple_list_item_1,respostasArrayList);



        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();

        Bundle get_bunble = getArguments();

        pergunta_id  =  get_bunble.getString("pergunta_id");


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference("Respostas");


        query_respostas = FirebaseDatabase.getInstance().getReference("Respostas").orderByChild("pergunta_id")
        .equalTo(pergunta_id);


        query = FirebaseDatabase.getInstance().getReference("Perguntas").orderByChild("perg_id")
        .equalTo(pergunta_id);


        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(!dataSnapshot.exists())
                {
                    return;
                }
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    Perguntas p = snapshot.getValue(Perguntas.class);

                    pergunta.setText(""+p);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        ValueEventListener valueEventListener1 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (!dataSnapshot.exists())
                {
                    return;
                }

                for (DataSnapshot objsnapshot: dataSnapshot.getChildren())
                {
                    Respostas respostas = objsnapshot.getValue(Respostas.class);

                    respostasArrayList.add(respostas);

                }

                listareposta.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        };

        query.addListenerForSingleValueEvent(valueEventListener);

        query_respostas.addListenerForSingleValueEvent(valueEventListener1);






    }

    @Override
    public void onClick(View view)
    {
        if (view == btn_voltar)
        {
           getFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainFragmentComunity())
           .commit();
        }
        if (view == btn_resposta)
        {
            Respostas r = new Respostas();

            r.setResposta_id(UUID.randomUUID().toString());
            r.setResposta_coment(edt_resposta.getText().toString());
            r.setUser_id(firebaseAuth.getUid());
            r.setPergunta_id(pergunta_id);

            databaseReference.child(r.getResposta_id()).setValue(r).addOnCompleteListener(new OnCompleteListener<Void>()
            {
                @Override
                public void onComplete(@NonNull Task<Void> task)
                {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(getActivity(),"Resposta enviada",Toast.LENGTH_LONG).show();
                    }
                }
            });

            edt_resposta.setText("");
        }

    }
}
