package com.example.e_libas_v_0_01;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.modelo.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Conta_Usuario extends AppCompatActivity implements View.OnClickListener
{
    ImageView imageView;

    String email;

    Button btnalteraruser, btndeletar;

    EditText edtnome, edtapelido, edtemail;

    FirebaseAuth firebaseAuth;

    DatabaseReference databaseReference, referencia;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conta__usuario);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() ==null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }

        progressDialog = new ProgressDialog(this);

        imageView = (ImageView) findViewById(R.id.btnimageview);
        btnalteraruser = (Button) findViewById(R.id.btnperfilusuario);
        btndeletar =(Button) findViewById(R.id.btndeletarconta);

        edtapelido = (EditText) findViewById(R.id.txtperfilapelido);
        edtnome = (EditText) findViewById(R.id.txtperfilnome);
        edtemail = (EditText) findViewById(R.id.txtperfilemail);

        email = edtemail.getText().toString();


        imageView.setOnClickListener(this);
        btnalteraruser.setOnClickListener(this);
        btndeletar.setOnClickListener(this);

        final FirebaseUser user = firebaseAuth.getCurrentUser();

        referencia= FirebaseDatabase.getInstance().getReference("Userscore").child(user.getUid());

        databaseReference = FirebaseDatabase.getInstance().getReference("Usuario").child(user.getUid());


    }

    @Override
    protected void onStart()
    {
        super.onStart();

        final FirebaseUser user = firebaseAuth.getCurrentUser();

        referencia= FirebaseDatabase.getInstance().getReference("Userscore").child(user.getUid());

        databaseReference = FirebaseDatabase.getInstance().getReference("Usuario").child(user.getUid());

         //query = databaseReference;



        databaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                if (!dataSnapshot.exists())
                {
                    return;

                }

                String nome = dataSnapshot.child("nome").getValue().toString();

                String apelido = dataSnapshot.child("apelido").getValue().toString();

                String email = dataSnapshot.child("email").getValue().toString();

                edtnome.setText(nome);
                edtapelido.setText(apelido);
                edtemail.setText(email);

                edtnome.setEnabled(false);
                edtapelido.setEnabled(false);
                edtemail.setEnabled(false);
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
        if (view ==imageView)
        {
            finish();
            startActivity(new Intent(Conta_Usuario.this, MainFragmentMenu.class));
        }
        if (view == btnalteraruser)
        {

            String verificabtn = btnalteraruser.getText().toString();

            if (verificabtn.equals("Alterar Cadastro"))
            {
                edtnome.setEnabled(true);
                edtapelido.setEnabled(true);
                btnalteraruser.setText("Confirmar");
            }
            else
            {
                atualizarDados();
            }
        }
        if (view == btndeletar)
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(Conta_Usuario.this);
            dialog.setTitle("Voce tem Certeza?");
            dialog.setMessage("Você esta prestes a excluir sua conta, assim não terá " +
                                "mais acesso ao aplicativo. Seus niveis, pontos e " +
                                    "informações da conta serão perdidos");



            dialog.setPositiveButton("Confirmar", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    deletarconta();
                }
            });

            dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    dialogInterface.dismiss();
                }
            });

            AlertDialog alertDialog = dialog.create();
            alertDialog.show();

        }
    }

    private void deletarconta()
    {
        progressDialog.setMessage("Excluindo Usuario");
        progressDialog.show();

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = firebaseAuth.getCurrentUser();



        currentUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if (task.isSuccessful())
                {
                   firebaseAuth.signOut();

                   referencia.removeValue();

                  databaseReference.removeValue();

                    startActivity(new Intent(Conta_Usuario.this, LoginActivity.class));
                    finish();

                    progressDialog.dismiss();
                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Não foi possivel excluir sua conta",Toast.LENGTH_SHORT );
                }
            }
        });
    }


    private void atualizarDados()
    {

        String atulizanome = edtnome.getText().toString().trim();
        final String atualizaapelido = edtapelido.getText().toString().trim();

        if (!TextUtils.isEmpty(atulizanome) && !TextUtils.isEmpty(atualizaapelido))
        {
            progressDialog.setMessage("Alterando");
            progressDialog.show();

            databaseReference.child("nome").setValue(atulizanome).addOnCompleteListener(new OnCompleteListener<Void>()
            {
                @Override
                public void onComplete(@NonNull Task<Void> task)
                {
                    if (task.isSuccessful())
                    {
                        databaseReference.child("apelido").setValue(atualizaapelido).addOnCompleteListener(new OnCompleteListener<Void>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<Void> task)
                            {
                                if (task.isSuccessful()) {
                                    referencia.child("apelido").setValue(atualizaapelido).addOnCompleteListener(new OnCompleteListener<Void>()
                                    {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task)
                                        {
                                            if (task.isSuccessful()) {
                                                edtnome.setEnabled(false);
                                                edtapelido.setEnabled(false);

                                                btnalteraruser.setText("Alterar Cadastro");

                                                progressDialog.dismiss();

                                                Toast.makeText(getApplicationContext(), "Usuario Alterado com sucesso", Toast.LENGTH_SHORT).show();
                                            }
                                            else
                                                {
                                                    progressDialog.dismiss();
                                                Toast.makeText(getApplicationContext(), "Não foi possivel Atulizar os dados", Toast.LENGTH_SHORT).show();
                                                }
                                        }
                                    });
                                }
                                else
                                    {
                                        progressDialog.dismiss();

                                    Toast.makeText(getApplicationContext(), "Não foi possivel Atulizar os dados", Toast.LENGTH_SHORT).show();
                                    }
                            }
                        });
                    }
                    else
                        {
                            progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Não foi possivel Atulizar os dados", Toast.LENGTH_SHORT).show();
                        }
                }
            });

        }
        else
        {
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(), "Preencha os campos", Toast.LENGTH_SHORT).show();
        }

    }


}
