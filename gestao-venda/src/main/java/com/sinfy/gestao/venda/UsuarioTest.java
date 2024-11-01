/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sinfy.gestao.venda;

import com.sinfy.gestao.venda.modelo.dao.UsuarioDao;
import com.sinfy.gestao.venda.modelo.dominio.Perfil;
import com.sinfy.gestao.venda.modelo.dominio.Usuario;
import java.time.LocalDateTime;

/**
 *
 * @author Orlandinho Namba
 */
public class UsuarioTest {
    
    public static void main(String[] args) {
        Usuario usuario = new Usuario(1L, "Orlando Namba", "1234", "sinfy", Perfil.ADMIN, null, null);
                
        UsuarioDao usuarioDao = new UsuarioDao();
        String mensagem = usuarioDao.salvar(usuario);
        System.out.println(mensagem);
    }
    
}
