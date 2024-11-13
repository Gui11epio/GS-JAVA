package org.example.Repositorio;

import org.example.Entidades.Cadastro;
import org.example.Log.Loggable;

import java.util.ArrayList;

public class RepositorioCadastro implements RepositorioGenerico<Cadastro>, Loggable<Cadastro> {
    @Override
    public void adicionar(Cadastro entidade) {

    }

    @Override
    public ArrayList<Cadastro> exibir() {
        return null;
    }

    @Override
    public void editar(Cadastro entidade) {

    }

    @Override
    public void excluir(int id) {

    }

    @Override
    public Cadastro buscarPorId(int id) {
        return null;
    }
}
