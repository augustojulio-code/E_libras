package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.Evento_Botao;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Button;
import android.widget.ImageView;

import com.example.e_libas_v_0_01.R;

public class Manipula_Button
{

    public void TrocarCorBotao4(Button opcao_01, Button opcao_02, Button opcao_03, Button opcao_04)
    {
        opcao_01.setBackgroundResource(R.drawable.button_custom04);
        opcao_02.setBackgroundResource(R.drawable.button_custom04);
        opcao_03.setBackgroundResource(R.drawable.button_custom04);
        opcao_04.setBackgroundResource(R.drawable.button_custom03);
    }
    public void TrocarCorBotao3(Button opcao_01, Button opcao_02, Button opcao_03, Button opcao_04)
    {
        opcao_01.setBackgroundResource(R.drawable.button_custom04);
        opcao_02.setBackgroundResource(R.drawable.button_custom04);
        opcao_03.setBackgroundResource(R.drawable.button_custom03);
        opcao_04.setBackgroundResource(R.drawable.button_custom04);
    }
    public void TrocarCorBotao2(Button opcao_01, Button opcao_02, Button opcao_03, Button opcao_04)
    {
        opcao_01.setBackgroundResource(R.drawable.button_custom04);
        opcao_02.setBackgroundResource(R.drawable.button_custom03);
        opcao_03.setBackgroundResource(R.drawable.button_custom04);
        opcao_04.setBackgroundResource(R.drawable.button_custom04);
    }
    public void TrocarCorBotao1(Button opcao_01, Button opcao_02, Button opcao_03, Button opcao_04)
    {
        opcao_01.setBackgroundResource(R.drawable.button_custom03);
        opcao_02.setBackgroundResource(R.drawable.button_custom04);
        opcao_03.setBackgroundResource(R.drawable.button_custom04);
        opcao_04.setBackgroundResource(R.drawable.button_custom04);
    }


    public void Desabilitar_botao(Button opcao_01, Button opcao_02, Button opcao_03, Button opcao_04, ImageView proximo)
    {
        opcao_01.setEnabled(false);
        opcao_02.setEnabled(false);
        opcao_03.setEnabled(false);
        opcao_04.setEnabled(false);
        proximo.setEnabled(true);
    }



}
