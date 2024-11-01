/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sinfy.gestao.venda.modelo.dao;

import com.sinfy.gestao.venda.modelo.dominio.Perfil;
import com.sinfy.gestao.venda.modelo.dominio.Usuario;
import com.sinfy.gestao.venda.modelo.exception.NegocioException;
import com.sinfy.gestao.venda.view.modelo.LoginDto;
import javax.swing.JOptionPane;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Orlandinho Namba
 */
public class AutenticacaoDao {
    
    private final UsuarioDao usuarioDao;

    public AutenticacaoDao() {
        this.usuarioDao = new UsuarioDao();
    }
    
    public boolean temPermissao(Usuario usuario) {
        try {
            permissao(usuario);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Usuário sem permissão", 0);
            return false;
        }
    }
    
    private void permissao(Usuario usuario) {
        if(!Perfil.ADMIN.equals(usuario.getPerfil())) {
            throw new NegocioException("Sem permissão para realizar essa ação");
        }
    }
    
    public Usuario login(LoginDto login) {
        Usuario usuario = usuarioDao.buscarUsuarioPeloUsuario(login.getUsuario());
        
        if(usuario == null || !usuario.isEstado())
            return null;
        
        if(usuario.isEstado() && validaSenha(usuario.getSenha(), login.getSenha())) {
            return usuario;
        }
        
        return null;
    }   

    // Sem a utilização do Spring Security
//    private boolean validaSenha(String senhaUsuario, String senhaLogin) {
//        return senhaUsuario.equals(senhaLogin);
//    }

    private boolean validaSenha(String senhaUsuario, String senhaLogin) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(senhaLogin, senhaUsuario);
    }
}
