package org.example.Repositorio;

import org.example.Entidades._EntidadeBase;

import java.util.ArrayList;

public interface RepositorioGenerico<T extends _EntidadeBase> {
    void adicionar(T entidade);
    ArrayList<T> exibir();
    void editar(T entidade, int id);
    void excluir(int id);
    T buscarPorId(int id);
}
