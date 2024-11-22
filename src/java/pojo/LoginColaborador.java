/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author Hp
 */
public class LoginColaborador {
    private Boolean error;
    private String mensaje;
    private Colaborador colaborador;
    private String token;

    public LoginColaborador(Boolean error, String mensaje, Colaborador colaborador, String token) {
        this.error = error;
        this.mensaje = mensaje;
        this.colaborador = colaborador;
        this.token = token;
    }



    public LoginColaborador() {
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    
}
