package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.modelo;

public class Respostas
{
    private String resposta_id;
    private String resposta_coment;
    private String user_id;
    private String pergunta_id;



    public Respostas()
    {

    }

    public String getResposta_id() {
        return resposta_id;
    }

    public void setResposta_id(String resposta_id) {
        this.resposta_id = resposta_id;
    }

    public String getResposta_coment() {
        return resposta_coment;
    }

    public void setResposta_coment(String resposta_coment) {
        this.resposta_coment = resposta_coment;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPergunta_id() {
        return pergunta_id;
    }

    public void setPergunta_id(String pergunta_id) {
        this.pergunta_id = pergunta_id;
    }


    @Override
    public String toString()
    {
        return resposta_coment;
    }
}
