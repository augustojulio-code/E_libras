package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.modelo;

public class Userscore
{
    public String apelido ;
    public int pontos, nivel;

    public Userscore()
    {

    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Userscore(int nivel, int pontos, String apelido )
    {
        this.nivel = nivel;
        this.pontos = pontos;
        this.apelido = apelido;
    }



    public String toString()
    {
        return apelido+" - " + "Pontos "+pontos + " Nv."+nivel;
    }

}
