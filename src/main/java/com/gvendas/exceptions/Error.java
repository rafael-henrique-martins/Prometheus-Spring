package com.gvendas.exceptions;

public class Error {
    private String msgUsuario;
    private String megDesenvolvedor;

    public Error(String msgUsuario, String megDesenvolvedor) {
        this.msgUsuario = msgUsuario;
        this.megDesenvolvedor = megDesenvolvedor;
    }

    public String getMsgUsuario() {
        return msgUsuario;
    }

    public String getMegDesenvolvedor() {
        return megDesenvolvedor;
    }

}
