package org.example.Service;

import org.example.Entidades.Cadastro;
import org.example.Log.Loggable;
import org.example.Repositorio.RepositorioCadastro;


public class CadastroService implements Loggable<CadastroService> {
    private RepositorioCadastro repositorioCadastro = new RepositorioCadastro();

    public CadastroService(){
        RepositorioCadastro repositorioCadastro = new RepositorioCadastro();
    }

    public boolean Criar(Cadastro cadastro){
        java.util.Map<Boolean, String> validation = cadastro.validate();
        try {
            if(validation.containsKey(false)) {
                throw new IllegalArgumentException(validation.get(false).toString());
            } else {
                repositorioCadastro.adicionar(cadastro);
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void Atualizar(Cadastro cadastro, int id) {
        try {
            java.util.Map<Boolean, String> validation = cadastro.validate();
            if (validation.containsKey(false)) {
                throw new IllegalArgumentException(validation.get(false).toString());
            } else {
                repositorioCadastro.editar(cadastro, id);
                logInfo("Cadastro atualizado com sucesso");
            }
        } catch (Exception e) {
            logErro("Erro ao atualizar cadastro: " + e.getMessage());
            throw e;
        }
    }


    public Cadastro authenticate(String email, String senha) {
        Cadastro cadastro = repositorioCadastro.acharPorEmail(email);
        if (cadastro != null && cadastro.getSenha().equals(senha)) {
            return cadastro;
        }
        throw new IllegalArgumentException("Credenciais inválidas");
    }
}
