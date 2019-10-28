package com.example.e_libas_v_0_01;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button login;
    private EditText txtedtemail,txtedtsenha;
    private TextView registrar, recuperarsenha;

    private ProgressDialog barra;

    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        firebaseAuth = FirebaseAuth.getInstance();

        /*if (firebaseAuth.getCurrentUser()!= null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileLogin.class));
        }*/

        barra = new ProgressDialog(this);

        txtedtemail = (EditText) findViewById(R.id.edttxtemail);
        txtedtsenha = (EditText) findViewById(R.id.edttxtsenha);

        login = (Button) findViewById(R.id.btnlogin);

        registrar = (TextView) findViewById(R.id.txtviewsignup);
        recuperarsenha = (TextView) findViewById(R.id.txtrecuperarsenha);

        login.setOnClickListener(this);
        registrar.setOnClickListener(this);
        recuperarsenha.setOnClickListener(this);

    }

    private void verificauser()
    {
        String lemail = txtedtemail.getText().toString().trim();
        String lsenha = txtedtsenha.getText().toString().trim();

        if(TextUtils.isEmpty(lemail)/* || TextUtils.isEmpty(senha)*/ )
        {
            Toast.makeText(this,"Digite um email",Toast.LENGTH_LONG).show();

            return;
        }
        if (TextUtils.isEmpty(lsenha))
        {
            Toast.makeText(this,"Digite uma senha", Toast.LENGTH_LONG).show();

            return;
        }

        //barra.setMessage("Verificando Usuário");
        //barra.show();

        firebaseAuth.signInWithEmailAndPassword(lemail, lsenha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {


                        if (task.isSuccessful())
                        {
                            // Adicionar uma intenção
                            //barra.dismiss();

                            Toast.makeText(LoginActivity.this,"Logado",Toast.LENGTH_SHORT).show();

                            finish();
                            startActivity(new Intent(getApplicationContext(),MainFragmentMenu.class));
                        }
                        else
                            {
                                //Toast.makeText(this,"Deu Errado", Toast.LENGTH_LONG).show();
                                //barra.dismiss();
                                Toast.makeText(LoginActivity.this,"Email ou senha Incorreto", Toast.LENGTH_LONG).show();
                            }
                    }
                });
    }

    @Override
    public void onClick(View view)
    {
        if (view == login)
        {
            verificauser();
        }
        if (view == registrar)
        {
            finish();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
        if (view == recuperarsenha)
        {
            recuperarSenhaDialog();
        }
    }

    private void recuperarSenhaDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Recuperar senha");
        builder.setMessage("A nova senha sera enviada para este email");

        LinearLayout linearLayout = new LinearLayout(this);

        final EditText emailtext = new EditText(this);
        emailtext.setHint("Email");
        emailtext.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        emailtext.setMinEms(10);

        linearLayout.addView(emailtext);
        linearLayout.setPadding(10,10,10,10);

        builder.setView(linearLayout);

        builder.setPositiveButton("Enviar", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                String semail = emailtext.getText().toString().trim();
                recuperarStart(semail);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.dismiss();
            }
        });

        builder.create().show();
    }

    private void recuperarStart(String semail)
    {
        barra.setMessage("Aguarde");
        barra.show();

        firebaseAuth.sendPasswordResetEmail(semail).addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if (task.isSuccessful())
                {
                    barra.dismiss();
                    Toast.makeText(LoginActivity.this,"Email enviado",Toast.LENGTH_LONG).show();
                }
                else
                {
                    barra.dismiss();
                    Toast.makeText(LoginActivity.this,"Falha ao enviar",Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                barra.dismiss();
                Toast.makeText(LoginActivity.this,""+e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
