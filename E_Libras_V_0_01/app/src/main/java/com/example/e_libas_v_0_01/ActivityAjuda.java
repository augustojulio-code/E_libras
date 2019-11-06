package com.example.e_libas_v_0_01;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import com.ms.square.android.expandabletextview.ExpandableTextView;

public class ActivityAjuda extends AppCompatActivity {

    ExpandableTextView expandableTextView;
    String questions;
    TextView contato;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda);

        expandableTextView = findViewById(R.id.expandable_text_view);
        contato = findViewById(R.id.ajuda_email);
        contato.setMovementMethod(LinkMovementMethod.getInstance());

        // DefaultValues
        questions = getString(R.string.ajuda_pergunta1);

        expandableTextView.setText(Html.fromHtml(questions));
    }
}