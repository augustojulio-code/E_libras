package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.e_libas_v_0_01.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainFramentDasboard extends Fragment
{
    ProgressBar progressBar;
    TextView viewpontos, viewnivel;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    int pbPontos;
    String Spontos, Snivel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_dashboard,container,false);

        progressBar = (ProgressBar) view.findViewById(R.id.dashboardpb);
        viewpontos = (TextView) view.findViewById(R.id.dashboardpontos);
        viewnivel = (TextView) view.findViewById(R.id.dashboardniveluser);



        return view;

    }

    @Override
    public void onStart()
    {
        super.onStart();

        firebaseAuth = FirebaseAuth.getInstance();

        final FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference  = FirebaseDatabase.getInstance().getReference("Userscore").child(user.getUid());

        databaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (!dataSnapshot.exists())
                {
                    return;
                }

               Spontos = dataSnapshot.child("pontos").getValue().toString();
               Snivel = dataSnapshot.child("nivel").getValue().toString();

               pbPontos = Integer.parseInt(Spontos);

               progressBar.setProgress(pbPontos);
               viewpontos.setText(Spontos+"/1000");
               viewnivel.setText("Nível: "+Snivel);

               if (pbPontos>1000)
               {
                   progressBar.clearAnimation();
                   progressBar.setMax(2000);
                   viewpontos.setText(Spontos+"/2000");

                   if (pbPontos > 2000)
                   {
                       progressBar.clearAnimation();
                       progressBar.setMax(3000);
                       viewpontos.setText(Spontos+"/3000");
                   }
               }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
