package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.modelo;

public class Perguntas
{
    private String perg_id;
    private String pergunta;
    private String user_id;

    public Perguntas() {
    }

    public String getPerg_id() {
        return perg_id;
    }

    public void setPerg_id(String perg_id) {
        this.perg_id = perg_id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


    @Override
    public String toString() {
        return pergunta;
    }
}
