package com.gvendas.exceptions;

public class RegraNegocioException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public RegraNegocioException(String menssagem){
        super (menssagem);
    }
}
