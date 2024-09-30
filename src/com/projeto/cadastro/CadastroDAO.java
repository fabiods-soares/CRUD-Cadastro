package com.projeto.cadastro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CadastroDAO {
    
    private Connection connection;
    
    public CadastroDAO(){
        this.connection = new ConnectionFactory().conectaBD();
    }
    
     
    //Metodo para Criar usuario
    public void criarUsuario(Cadastro cadastro){

        String sql = "INSERT INTO cliente(nome, cpf, data_nascimento, altura) VALUES(?,?,?,?)";
        PreparedStatement stmt = null;
        
        try{
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cadastro.getNome());
            stmt.setString(2, cadastro.getCpf());
            stmt.setDate(3, new java.sql.Date(cadastro.getData_nascimento().getTime()));
            stmt.setFloat(4, cadastro.getAltura());
            //Comando que cria o usuario
            stmt.executeUpdate();
            
            System.out.println("Usuario criando com sucesso");
            //COLOCA SQL para verificar o banco de dados        

        } catch (SQLException erro) {
            erro.printStackTrace();
        }finally{
            if(stmt !=null){
            try{
                
                stmt.close();
            }catch (SQLException e) {
                 System.out.println("Erro ao fechar o PreparedStatement "+e.getMessage());
            }
        }
    }
}
    
     //Metodo para Listar todos os usuarios
    public List<Cadastro> listarUsuarios() throws SQLException {
        String sql = "SELECT * FROM cliente";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cadastro> usuarios = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(sql);
            //Comando de busca e listagem dos usuarios
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cadastro cadastro = new Cadastro();
                cadastro.setId(rs.getInt("id"));
                cadastro.setNome(rs.getString("nome"));
                cadastro.setCpf(rs.getString("cpf"));
                cadastro.setData_nascimento(rs.getDate("data_nascimento"));
                cadastro.setAltura(rs.getFloat("altura"));
                usuarios.add(cadastro);

            }
        } catch (SQLException erro) {
            System.out.println("Erro ao listar " + erro.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
        return usuarios;
    }

    //Metodo para buscar usuarios por id
    public Cadastro buscarUsuarioPorId(int id) throws SQLException {
        String sql = "SELECT*FROM cliente WHERE id=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cadastro cadastro = null;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                cadastro = new Cadastro();
                cadastro.setId(rs.getInt("id"));
                cadastro.setNome(rs.getString("nome"));
                cadastro.setCpf(rs.getString("cpf"));
                cadastro.setData_nascimento(rs.getDate("data_nascimento"));
                cadastro.setAltura(rs.getFloat("altura"));
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao buscar usuário: " + erro.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
        return cadastro;
    }

    //Metodo para atualizar um usuario
    public void atualizarUsuario(Cadastro cadastro) throws SQLException {
        String sql = "UPDATE cliente SET nome=?, cpf=?, data_nascimento=?, altura=? WHERE id=?";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cadastro.getNome());
            stmt.setString(2, cadastro.getCpf());
            stmt.setDate(3, new java.sql.Date(cadastro.getData_nascimento().getTime()));
            stmt.setFloat(4, cadastro.getAltura());
            stmt.setInt(5, cadastro.getId());

            //Comando que executa a atualização
            stmt.executeUpdate();
            System.out.println("Usuário atualizado com sucesso!");

        } catch (SQLException erro) {
            System.out.println("Erro ao atualizar usuário: " + erro.getMessage());
        } finally {
            if (stmt != null) stmt.close();
        }
    }

    //Método para deletar um usuário
    public void deletarUsuario(int id) throws SQLException {
        String sql = "DELETE FROM cliente WHERE id=?";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Usuário deletado com sucesso!");

        } catch (SQLException erro) {
            System.out.println("Erro ao deletar usuário: " + erro.getMessage());
        } finally {
            if (stmt != null) stmt.close();
        }
    }

    //Metodo para fechar a conexão(caso precise ser fechado manualmente)
    public void fecharConexao() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
   
