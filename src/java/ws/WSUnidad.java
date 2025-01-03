/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dominio.IMPCliente;
import dominio.IMPColaborador;
import dominio.IMPUnidad;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import static javax.ws.rs.client.Entity.json;
import javax.ws.rs.core.MediaType;
import static org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode;
import pojo.Cliente;
import pojo.Colaborador;
import pojo.HistorialUnidad;
import pojo.Mensaje;
import pojo.Unidad;

/**
 * REST Web Service
 *
 * @author sebas
 */
@Path("unidad")
public class WSUnidad {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WSUnidad
     */
    public WSUnidad() {
    }

    @Path("registrar-unidad")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarUnidad(String json) {
        Mensaje respuesta = new Mensaje();
        try {
            Gson gson = new Gson();
            Unidad unidad = gson.fromJson(json, Unidad.class);
            respuesta = IMPUnidad.registrarUnidad(unidad);
        } catch (Exception e) {
            respuesta.setError(true);
            respuesta.setMensaje("Errro al registrar unidad: " + e.toString());
        }
        return respuesta;
    }

    @Path("editar-unidad")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarUnidad(String json) {
        Mensaje respuesta = new Mensaje();
        try {
            Gson gson = new Gson();
            Unidad unidad = gson.fromJson(json, Unidad.class);
            respuesta = IMPUnidad.editarUnidad(unidad);
        } catch (Exception e) {
            respuesta.setError(true);
            respuesta.setMensaje("Errro al editar unidad: " + e.toString());
        }
        return respuesta;
    }

    @Path("eliminar-unidad/{idUnidad}/{motivo}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje elminarUnidad(@PathParam("idUnidad") Integer idUnidad, @PathParam("motivo") String motivo) {
        Mensaje respuesta = new Mensaje();
        try {
            String motivoDecodificado = URLDecoder.decode(motivo, "UTF-8");
            respuesta = IMPUnidad.eliminarUnidad(idUnidad, motivoDecodificado);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(WSUnidad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    @Path("buscar-unidad/{parametro}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unidad> buscarUnidad(@PathParam("parametro") String parametros) {
        String unidadDecodificada = URLDecoder.decode(parametros);
        return IMPUnidad.buscarUnidad(unidadDecodificada);
    }

    @Path("obtener-unidades")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unidad> obtenerColaboradores() {
        return IMPUnidad.obtenerUnidades();
    }

    @Path("obtener-historial")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<HistorialUnidad> obtenerHistorial() {
        return IMPUnidad.obtenerHistorialUndiades();
    }

    @Path("obtener-historial-marca/{marca}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<HistorialUnidad> obtenerHistorialMarca(@PathParam("marca") String marca) {
        List<HistorialUnidad> unidades=null;
        try {
            String marcaDecodificada = URLDecoder.decode(marca,"UTF-8");
            unidades= IMPUnidad.buscarrHistorialMarca(marca);
        } catch (Exception e) {
            System.out.println("Error obtener historial marca "+e.toString());
        }
        return unidades;
    }
}
