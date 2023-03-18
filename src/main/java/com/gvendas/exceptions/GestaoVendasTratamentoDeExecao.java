package com.gvendas.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
public class GestaoVendasTratamentoDeExecao extends ResponseEntityExceptionHandler {

    public static final String NOT_BLANK = "NotBlank";
    public static final String LENGTH = "Length";
    public static final String NOT_NULL = "NotNull";


    // Tratando quando usuário deixa o campo nome em branco ou menor que 3 caracteres
    protected ResponseEntity<Object> handlerMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                   HttpHeaders headers, HttpStatus status, WebRequest request){
        List<Error> erros = gerarListaDeErros(ex.getBindingResult()); // passando o resultado das execoes

        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    // Tratando quando tenta atualizar por id que não existe
    // Ela não existe na classe que estou extendendo, então preciso pegar a execao que lançou e coloco no nome da classe e dentro do () da funcao, e colocar @ExceptionHandler(EmptyResultDataAccessException.class)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handlerEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request){
        String msgUsuario = "Recurso não encontrado";
        String msgDesenvolvedor = ex.toString();

        List<Error> erros = Arrays.asList(new Error(msgUsuario, msgDesenvolvedor));

        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<Object> handlerRegraNegocioException(RegraNegocioException ex, WebRequest request){
        String msgUsuario = ex.getMessage();
        String msgDesenvolvedor = ex.getMessage();

        List<Error> erros = Arrays.asList(new Error(msgUsuario, msgDesenvolvedor));

        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handlerDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
        String msgUsuario = "Recurso não encontrado";
        String msgDesenvolvedor = ex.toString();

        List<Error> erros = Arrays.asList(new Error(msgUsuario, msgDesenvolvedor));

        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private List<Error> gerarListaDeErros(BindingResult bindingResult){
        List<Error> erros = new ArrayList<Error>();
        bindingResult.getFieldErrors().forEach(fieldError -> {  // pegando todos os erros que podem chegar
            String msgUsuario = tratarMensagemDeErroParaUsuario(fieldError);
            String msgDesenvolvedor = fieldError.toString();
            erros.add(new Error(msgUsuario, msgDesenvolvedor));
        });
        return erros;
    }
    private String tratarMensagemDeErroParaUsuario(FieldError fieldError){
        if(fieldError.getCode().equalsIgnoreCase(NOT_BLANK)){
            return fieldError.getDefaultMessage().concat(" é obrigatório");
        }
        if(fieldError.getCode().equalsIgnoreCase(NOT_NULL)){
            return fieldError.getDefaultMessage().concat(" é obrigatório");
        }
        if(fieldError.getCode().equalsIgnoreCase(LENGTH)){
            return fieldError.getDefaultMessage().concat(String.format(" deve ter entre %s e %s caracteres.",
                    Objects.requireNonNull(fieldError.getArguments())[2], fieldError.getArguments()[1]));
        }
        return fieldError.toString();
    }
}
