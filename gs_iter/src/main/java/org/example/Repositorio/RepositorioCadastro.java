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
    @Override
    public void adicionar(Cadastro cadastro) {
        String sql = "INSERT INTO Cadastro(nome,email,senha) VALUES (?,?,?)";

        try(Connection conn = DataBaseConfig.getConnection();
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

        try(Connection conn = DataBaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

            while (rs.next()) {
                Cadastro cadastro = new Cadastro();
                cadastro.setId(rs.getInt("id"));
                cadastro.setNome(rs.getString("nome"));
                cadastro.setEmail(rs.getString("email"));
                cadastros.add(cadastro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logInfo("Erro ao mostrar");
        }

        return cadastros;
    }

    @Override
    public void editar(Cadastro cadastro, int id) {
        String sql = "UPDATE Cadastro SET nome = ?, email = ?, senha = ? WHERE id = ?";

        try(Connection conn = DataBaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, cadastro.getNome());
            stmt.setString(2, cadastro.getEmail());
            stmt.setString(2, cadastro.getSenha());
            stmt.setInt(3, cadastro.getId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0){
                logInfo("Usuário atualizado com sucesso");
            }

        } catch (SQLException e){
            e.printStackTrace();
            logInfo("Erro ao Atualizar");
        }

    }


    @Override
    public void excluir(int id) {

    }

    @Override
    public Cadastro buscarPorId(int id) {
        return null;
    }
}
