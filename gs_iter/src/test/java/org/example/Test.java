package org.example;

import org.example.Entidades.Cadastro;
import org.example.Repositorio.RepositorioCadastro;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        RepositorioCadastro repositorioCadastro = new RepositorioCadastro();

        // Criando Cadastro
        Cadastro cadastro1 = new Cadastro();
        cadastro1.setNome("João");
        cadastro1.setEmail("joao@email.com");
        cadastro1.setSenha("A1b@cD!e");
        repositorioCadastro.adicionar(cadastro1);

        Cadastro cadastro2 = new Cadastro();
        cadastro2.setNome("Maria");
        cadastro2.setEmail("maria@email.com");
        cadastro2.setSenha("X2y!z@W9");
        repositorioCadastro.adicionar(cadastro2);

        Cadastro cadastro3 = new Cadastro();
        cadastro3.setNome("Jonas");
        cadastro3.setEmail("jonas@email.com");
        cadastro3.setSenha("M$3pQ@T5");
        repositorioCadastro.adicionar(cadastro3);

        // Atualizando e listando Clientes
        cadastro1.setEmail("joao@gmail.com");
        cadastro2.setEmail("maria@gmail.com");
        repositorioCadastro.editar(cadastro1, 1);
        repositorioCadastro.editar(cadastro2, 2);
        System.out.println(repositorioCadastro.buscarPorId(1));
        System.out.println(repositorioCadastro.buscarPorId(2));
        System.out.println(repositorioCadastro.exibir());

    }
}
