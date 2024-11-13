package org.example.Entidades;

import java.util.regex.Pattern;

public class Cadastro extends _EntidadeBase {

//    private static final Pattern senhaPadrao = Pattern.compile()


    private String nome;
    private String email;
    private String senha;

    public Cadastro() {
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Cadastro{" +
                "id=" + id +
                ", senha='" + senha + '\'' +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
