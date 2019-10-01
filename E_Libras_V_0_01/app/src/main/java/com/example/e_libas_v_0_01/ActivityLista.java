package com.example.e_libas_v_0_01;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.modelo.Userscore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ActivityLista extends AppCompatActivity implements View.OnClickListener
{
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    Button voltar;
    String[] mTitle = {"Doidera","Doidera","Doidera","Doidera","Doidera","Doidera"};
    int[] images = {R.mipmap.ic_internet, R.mipmap.ic_web, R.mipmap.ic_launcher_round, R.mipmap.ic_internet,R.mipmap.ic_internet,R.mipmap.ic_internet};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null)
        {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }


        voltar = (Button) findViewById(R.id.btninicio);

        databaseReference= FirebaseDatabase.getInstance().getReference("Userscore");

        listar();

        listView = (ListView) findViewById(R.id.txtlistview);

        /*arrayAdapter = new ArrayAdapter<String>(this ,android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(arrayAdapter);*/

        //voltar.setOnClickListener(this);

        MyAdapter adapter = new MyAdapter(this,mTitle,images);

        listView.setAdapter(adapter);



        //listar();



    }



    private void listar()
    {
        databaseReference.addChildEventListener(new ChildEventListener()
        {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            {

                String valor = dataSnapshot.getValue(Userscore.class).toString();

                arrayList.add(valor);

                //arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View view)
    {
        if (view == voltar)
        {
            finish();

        }
    }

    class MyAdapter extends ArrayAdapter<String>
    {
        Context context;
        String mtitle[];
        int mimages[];

        MyAdapter(Context c,String title[],int imgs[])
        {
            super(c,R.layout.listview_custon, R.id.textmainlist, title);

            this.context = c;
            this.mtitle = title;
            this.mimages = imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View row = layoutInflater.inflate(R.layout.listview_custon, parent,false);

            ImageView imageView = row.findViewById(R.id.listimagem);
            TextView mainTitle = row.findViewById(R.id.textmainlist);

            imageView.setImageResource(mimages[position]);
            mainTitle.setText(mtitle[position]);


            return row;
        }
    }
}
