package org.example.Repositorio;

import org.example.Entidades.Cadastro;
import org.example.Infraestrutura.DataBaseConfig;
import org.example.Log.Loggable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepositorioCadastro implements RepositorioGenerico<Cadastro>, Loggable<Cadastro> {

    DataBaseConfig connection = new DataBaseConfig();
    @Override
    public void adicionar(Cadastro cadastro) {
        String sql = "INSERT INTO Cadastro(nome,email,senha) VALUES (?,?,?)";

        try(Connection conn = connection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, cadastro.getNome());
            stmt.setString(2, cadastro.getEmail());
            stmt.setString(3, cadastro.getSenha());

            stmt.executeUpdate();
            logInfo("Usuário cadastrado com sucesso");
        } catch (SQLException e){
            e.printStackTrace();
            logInfo("Erro ao cadastrar");
        }

    }

    @Override
    public ArrayList<Cadastro> exibir() {
        String sql = "SELECT * FROM Cadastro";
        ArrayList<Cadastro> cadastros = new ArrayList<>();

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cadastro cadastro = new Cadastro();
                cadastro.setId(rs.getInt("id"));
                cadastro.setNome(rs.getString("nome"));
                cadastro.setEmail(rs.getString("email"));
                cadastro.setSenha(rs.getString("senha")); // Adicionado a senha
                cadastros.add(cadastro);
            }
            logInfo("Listagem de usuários realizada com sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
            logInfo("Erro ao listar usuários");
        }

        return cadastros;
    }


    @Override
    public void editar(Cadastro cadastro, int id) {
        String sql = "UPDATE Cadastro SET nome = ?, email = ?, senha = ? WHERE id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cadastro.getNome());
            stmt.setString(2, cadastro.getEmail());
            stmt.setString(3, cadastro.getSenha());
            stmt.setInt(4, id); // Corrigido para usar o ID do parâmetro

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                logInfo("Usuário com ID " + id + " atualizado com sucesso");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            logInfo("Erro ao atualizar usuário com ID " + id);
        }
    }



    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM Cadastro WHERE id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1,id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0){
                logInfo("Usuário removido com sucesso");
            }

        } catch (SQLException e){
            e.printStackTrace();
            logInfo("Erro ao deletar");
        }

    }

    @Override
    public Cadastro buscarPorId(int id) {
        String sql = "SELECT * FROM Cadastro WHERE id = ?";
        Cadastro cadastro = null;

        try(Connection conn = connection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                cadastro = new Cadastro();
                cadastro.setId(rs.getInt("id"));
                cadastro.setNome(rs.getString("nome"));
                cadastro.setEmail(rs.getString("email"));
                cadastro.setSenha(rs.getString("senha"));
            }
            return cadastro;

        } catch (SQLException e){
            e.printStackTrace();
            logInfo("Erro na busca por id");
        }

        return cadastro;
    }

    public Cadastro acharPorEmail(String email) {
        String sql = "SELECT * FROM Cadastro WHERE email = ?";
        Cadastro cadastro = null;

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                cadastro = new Cadastro();
                cadastro.setId(rs.getInt("id"));
                cadastro.setNome(rs.getString("nome"));
                cadastro.setEmail(rs.getString("email"));
                cadastro.setSenha(rs.getString("senha"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
            logInfo("Erro ao buscar por email");
        }

        return cadastro;
    }
}
