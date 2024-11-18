package org.example.Entidades;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Cadastro extends _EntidadeBase {

    private static final Pattern senhaPadrao = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*()_+]).{8,}$");

    private static final Pattern emailPadrao = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]{2,}$");



    private String nome;
    private String email;
    private String senha;

    public Cadastro() {
    }

    public Cadastro(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Cadastro(int id, String nome, String email, String senha) {
        super(id);
        this.nome = nome;
        this.email = email;
        this.senha = senha;
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
        if(!emailPadrao.matcher(email).matches()) {
            throw new IllegalArgumentException("Email inválido");
        }
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (!senhaPadrao.matcher(senha).matches()){
            throw new IllegalArgumentException("Senha inválida");
        }
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

    public Map<Boolean, String> validate(){
        Map<Boolean, String> validation = new HashMap<>();
        if (this.nome == null || this.nome.isEmpty())
            validation.put(false, "O nome do usuário não pode ser vazio");
        if (this.email == null || this.email.isEmpty())
            validation.put(false, "O email do cliente não pode ser vazio");
        if (this.senha == null || this.senha.isEmpty())
            validation.put(false, "A senha do cliente não pode ser vazia");
        return validation;
    }
}
