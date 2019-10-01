package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.fragments;

import android.R.layout;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.e_libas_v_0_01.MainFragmentMenu;
import com.example.e_libas_v_0_01.R;
import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.modelo.Userscore;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainFragmentRank extends Fragment
{
    ListView listView;
    DatabaseReference databaseReference;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList = new ArrayList<>();
    String listarusuario;
    Query query;

    listaAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {


        final View view = inflater.inflate(R.layout.fragment_rank, container,false);

        listView = (ListView) view.findViewById(R.id.listviewfragment);

        //databaseReference = FirebaseDatabase.getInstance().getReference("Userscore");

        query = FirebaseDatabase.getInstance().getReference("Userscore").orderByChild("pontos");




        /*arrayAdapter = new ArrayAdapter<String>(getActivity(), layout.simple_list_item_1,arrayList);

        listar();

        listView.setAdapter(arrayAdapter);*/



        listar();

        adapter = new listaAdapter(getActivity().getApplicationContext(),arrayList);

        return view;

    }




    private void listar()
    {
        query.addChildEventListener(new ChildEventListener()
        {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            {
                 if (!dataSnapshot.exists())
                {
                    return;
                }

                listarusuario= dataSnapshot.getValue(Userscore.class).toString();

                arrayList.add(listarusuario);


                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot)
            {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });


    }

    class listaAdapter extends ArrayAdapter<String>
    {
        Context context;
        ArrayList<String> listauserscore;

        listaAdapter(Context c, ArrayList<String> lista)
        {
            super(c,R.layout.listview_custom_rank,R.id.customviewapelido,lista);

            this.context = c;
            this.listauserscore = lista;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater inflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View customlist = inflater.inflate(R.layout.listview_custom_rank,parent,false);

            TextView apelido = customlist.findViewById(R.id.customviewapelido);


            apelido.setText(listauserscore.get(position));

            return customlist;
        }
    }

}
