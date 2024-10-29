/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sinfy.gestao.venda.modelo.dao;

import com.sinfy.gestao.venda.modelo.conexao.Conexao;
import com.sinfy.gestao.venda.modelo.conexao.ConexaoMysql;
import com.sinfy.gestao.venda.modelo.dominio.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author oNamba
 */
public class UsuarioDao {
    
    private final Conexao conexao;
    
    public UsuarioDao() {
        this.conexao = new ConexaoMysql();
    }

    public String salvar(Usuario usuario) {
        return usuario.getId() == 0L ? adicionar(usuario) : editar(usuario);
    }

    private String adicionar(Usuario usuario){
        String sql = "INSERT INTO usuario(nome, usuario, senha, perfil, estado) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql);
            
            preencherValoresPreparedStatement(preparedStatement, usuario);
            
            int resultado = preparedStatement.executeUpdate();
            
            return resultado == 1 ?"Usuário adicionado com sucesso" : "Não foi possível adicionar usuário";
        } catch (SQLException e) {
            return String.format("Error: %s", e.getMessage());
        }
    }

    private String editar(Usuario usuario) {
        String sql = "UPDATE categoria SET nome = ?, usuario = ?, senha = ?, perfil = ?, estado WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql);
            
            preencherValoresPreparedStatement(preparedStatement, usuario);
            
            int resultado = preparedStatement.executeUpdate();
            
            return resultado == 1 ?"Usuário editado com sucesso" : "Não foi possível editar usuário";
        } catch (SQLException e) {
            return String.format("Error: %s", e.getMessage());
        }
    }

    private void preencherValoresPreparedStatement(PreparedStatement preparedStatement, Usuario usuario) throws SQLException {
        preparedStatement.setString(1, usuario.getNome());
        preparedStatement.setString(2, usuario.getUsuario());
        preparedStatement.setString(3, usuario.getSenha());
        preparedStatement.setString(4, usuario.getPerfil().name());
        preparedStatement.setBoolean(5, usuario.isEstado());
        
        if(usuario.getId() != 0L) {
            preparedStatement.setLong(6, usuario.getId());
        }
    }
    
}
