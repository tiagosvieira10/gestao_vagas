package br.com.tiagosvieira.gestao_vagas.exceptions;

public class UserFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserFoundException() {
        super("Usuário já existe");
    }
  
}
