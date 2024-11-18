package org.example;

import org.example.Entidades.ContaUsuario;
import org.example.Repositorio.RepositorioCadastro;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        RepositorioCadastro repositorioCadastro = new RepositorioCadastro();

        // Criando Cadastro
        ContaUsuario contaUsuario1 = new ContaUsuario();
        contaUsuario1.setNome("João");
        contaUsuario1.setEmail("joao@email.com");
        contaUsuario1.setSenha("A1b@cD!e");
        repositorioCadastro.adicionar(contaUsuario1);

//        Cadastro cadastro2 = new Cadastro();
//        cadastro2.setNome("Maria");
//        cadastro2.setEmail("maria@email.com");
//        cadastro2.setSenha("X2y!z@W9");
//        repositorioCadastro.adicionar(cadastro2);
//
//        Cadastro cadastro3 = new Cadastro();
//        cadastro3.setNome("Jonas");
//        cadastro3.setEmail("jonas@email.com");
//        cadastro3.setSenha("M$3pQ@T5");
//        repositorioCadastro.adicionar(cadastro3);

        // Atualizando e listando Clientes
        contaUsuario1.setEmail("joao@gmail.com");
        repositorioCadastro.editar(contaUsuario1, 1);
        System.out.println(repositorioCadastro.exibir());

    }
}
