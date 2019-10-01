package com.example.e_libas_v_0_01.com.example.e_libras_v_0_01.modelo;

public class Usuario
{
    public String nome, email, apelido, idUsuario;

    public Usuario()
    {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(String nome, String email, String apelido, String id)
    {
        this.idUsuario = id;
        this.nome = nome;
        this.email = email;
        this.apelido = apelido;
    }

    public String toString()
    {
        return this.idUsuario +" "+ nome+" "+ apelido+" "+email;
    }
}
