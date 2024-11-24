/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author sebas
 */
public class HistorialEnvio {
    private Integer idCambio;
    private Integer idEnvio;
    private String estatus;
    private String fechaCambio;
    private Integer idColaborador;

    public HistorialEnvio(Integer idCambio, Integer idEnvio, String estatus, String fechaCambio, Integer idColaborador) {
        this.idCambio = idCambio;
        this.idEnvio = idEnvio;
        this.estatus = estatus;
        this.fechaCambio = fechaCambio;
        this.idColaborador = idColaborador;
    }

    public HistorialEnvio() {
    }

    public Integer getIdCambio() {
        return idCambio;
    }

    public void setIdCambio(Integer idCambio) {
        this.idCambio = idCambio;
    }

    public Integer getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Integer idEnvio) {
        this.idEnvio = idEnvio;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(String fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public Integer getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }
    
    
}
