package com.example.e_libas_v_0_01;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.ms.square.android.expandabletextview.ExpandableTextView;

public class ActivityAjuda extends Activity {

    ExpandableTextView expandableTextView;
    String questions;
    String reply;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda);

        expandableTextView = findViewById(R.id.expandable_text_view);

        // DefaultValues
        questions = "O que e o E-Libras?" +
                "\nE-Libras e um aplicativo desenvolvido para o aprendizado de libras";

        expandableTextView.setText(questions);
    }
}