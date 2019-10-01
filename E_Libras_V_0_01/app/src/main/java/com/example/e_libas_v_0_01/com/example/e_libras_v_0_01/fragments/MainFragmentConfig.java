package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_libas_v_0_01.Conta_Usuario;
import com.example.e_libas_v_0_01.LoginActivity;
import com.example.e_libas_v_0_01.MainActivity;
import com.example.e_libas_v_0_01.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainFragmentConfig extends Fragment
{

    FirebaseAuth firebaseAuth;
    Button sair;
    TextView web, conta, faq, congiguracao;
    String[] confMenu = {"Conta","Sobre E-Libras","Configurações","Perguntas Frequentes","Ajuda","Sair"};
    int[] imagens = {R.mipmap.ic_conta,R.mipmap.ic_web2, R.mipmap.ic_config, R.mipmap.ic_pergunta,R.mipmap.ic_info,R.mipmap.ic_logout};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        View view = inflater.inflate(R.layout.fragment_config,container, false);

        ListView configlista = (ListView) view.findViewById(R.id.listviewconfig);

        AdapterConfig adapter = new AdapterConfig(getActivity().getApplicationContext(),confMenu, imagens);

        configlista.setAdapter(adapter);

        configlista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                if (position == 0)
                {

                    getActivity().finish();
                    startActivity(new Intent(getActivity().getApplicationContext(), Conta_Usuario.class));
                }
                if (position == 1)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com.br")));
                }
                if (position == 2)
                {
                    Toast.makeText(getActivity().getApplicationContext(),"Área em Desenvolvimento",Toast.LENGTH_SHORT).show();
                }
                if (position == 3)
                {
                    Toast.makeText(getActivity().getApplicationContext(),"Área em Desenvolvimento",Toast.LENGTH_SHORT).show();
                }
                if (position == 4)
                {
                    Toast.makeText(getActivity().getApplicationContext(),"Área em Desenvolvimento",Toast.LENGTH_SHORT).show();
                }
                if (position == 5)
                {
                    firebaseAuth.signOut();
                    getActivity().finish();
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }

            }
        });

        return view;

    }

    class AdapterConfig extends ArrayAdapter<String>
    {
        Context context;
        String aConfigmenu[];
        int mImagens[];

        AdapterConfig (Context c, String configmenu[], int imgs[])
        {
            super(c, R.layout.listview_custon,R.id.textmainlist, configmenu);

            this.context = c;
            this.aConfigmenu = configmenu;
            this.mImagens = imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater inflater =(LayoutInflater)getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View customList = inflater.inflate(R.layout.listview_custon, parent, false);

            ImageView image = customList.findViewById(R.id.listimagem);
            TextView textomain = customList.findViewById(R.id.textmainlist);

            image.setImageResource(mImagens[position]);
            textomain.setText(aConfigmenu[position]);

            return customList ;
        }
    }
}
