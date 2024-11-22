/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dominio.IMPEnvio;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import pojo.Envio;
import pojo.Mensaje;

/**
 * REST Web Service
 *
 * @author sebas
 */
@Path("envio")
public class WSEnvio {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WSEnvio
     */
    public WSEnvio() {
    }

    /**
     * Retrieves representation of an instance of ws.WSEnvio
     * @return an instance of java.lang.String
     */
    @Path("registrar-envio")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarEnvio(String json){
        Mensaje respuesta= new Mensaje();
        try {
            Gson gson=new Gson();
            Envio envio = gson.fromJson(json, Envio.class);
            respuesta = IMPEnvio.registrarEnvio(envio);
        } catch (Exception e) {
            respuesta.setError(false);
            respuesta.setMensaje("Error al registrar envio: "+e.toString());
        }
        return respuesta;
    }
    
    @Path("editar-envio")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarEnvio(String json){
        Mensaje respuesta= new Mensaje();
        try {
            Gson gson=new Gson();
            Envio envio = gson.fromJson(json, Envio.class);
            respuesta = IMPEnvio.editarEnvio(envio);
        } catch (Exception e) {
            respuesta.setError(true);
            respuesta.setMensaje("Error al editar envio: "+e.toString());
        }
        return respuesta;
    }
    
}
