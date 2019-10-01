package com.example.e_libas_v_0_01;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.modelo.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class ProfileLogin extends AppCompatActivity implements View.OnClickListener
{
    private FirebaseAuth firebaseAuth;

    private EditText adicionarpontos;
    private TextView usuarioemail;
    private Button logout, lista, adicionar;
    DatabaseReference referencia, referencia2;
    int somapontos, resultado, pontos, bancoretorno;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null)
        {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();



        adicionarpontos = (EditText) findViewById(R.id.txtpontomanual);
        usuarioemail = (TextView) findViewById(R.id.txtviewuser);

        adicionar = (Button) findViewById(R.id.btnadicionar);
        logout = (Button) findViewById(R.id.btnlogout);
        lista = (Button) findViewById(R.id.btnlista);

        logout.setOnClickListener(this);
        lista.setOnClickListener(this);
        adicionar.setOnClickListener(this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        referencia = FirebaseDatabase.getInstance().getReference("Usuario").child(user.getUid());

        referencia.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                if (!dataSnapshot.exists())
                {
                    return;
                }

                String apelido = dataSnapshot.child("apelido").getValue().toString();
                usuarioemail.setText("Olá " + apelido);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });

        //Buscando string do banco

        referencia2 = FirebaseDatabase.getInstance().getReference("Userscore").child(user.getUid());

        referencia2.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (!dataSnapshot.exists())
                {
                    return;
                }
                String retornobanco = dataSnapshot.child("pontos").getValue().toString();

                bancoretorno = Integer.parseInt(retornobanco);


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
        if (view == logout)
        {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        if (view == lista)
        {
            startActivity(new Intent(ProfileLogin.this,ActivityLista.class));
        }

        if(view == adicionar)
        {
            AtualizarBanco();
        }
    }

    private void AtualizarBanco()
    {
        String ponto = adicionarpontos.getText().toString();

        pontos = Integer.parseInt(ponto);

        resultado = pontos + bancoretorno;

        //String devolucaocanco = Integer.toString(resultado);

        referencia2.child("pontos").setValue(resultado);

        Toast.makeText(ProfileLogin.this,"Valores alterados com sucesso",Toast.LENGTH_SHORT).show();

        limparCampos();

    }

    private void limparCampos()
    {
        adicionarpontos.setText("");
    }
}
