/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sinfy.gestao.venda.modelo.exception;

/**
 *
 * @author Orlandinho Namba
 */
public class NegocioException extends RuntimeException{
    
    public NegocioException(String mensagem) {
        super(mensagem);
    }
}
