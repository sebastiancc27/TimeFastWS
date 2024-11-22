/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dominio.IMPCliente;
import dominio.IMPColaborador;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import static javax.ws.rs.client.Entity.json;
import javax.ws.rs.core.MediaType;
import pojo.Cliente;
import pojo.Colaborador;
import pojo.LoginColaborador;
import pojo.Mensaje;


@Path("colaborador")
public class WSColaborador {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WSColaborador
     */
    public WSColaborador() {
        
    }

    @Path("registrar-colaborador")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarColaborador(String json){
        try {
        Gson gson = new Gson();      
        Colaborador colaborador = gson.fromJson(json, Colaborador.class);      
       return IMPColaborador.registrarColaborador(colaborador);
        } catch (Exception e) {
             throw new BadRequestException();
        }
    }
    
    @Path("editar-colaborador")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarColaborador(String json){
        try {
        Gson gson = new Gson();      
        Colaborador colaborador = gson.fromJson(json, Colaborador.class);      
       return IMPColaborador.editarColaborador(colaborador);
        } catch (Exception e) {
             throw new BadRequestException();
        }
    }
    
    @Path("eliminar-colaborador/{idColaborador}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarColaborador(@PathParam("idColaborador") Integer idColaborador){
        return IMPColaborador.eliminarMensaje(idColaborador);
    }    

    @Path("buscar-colaborador/{busqueda}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Colaborador> eliminarColaborador(@PathParam("busqueda") String busqeuda){
        return IMPColaborador.buscarColaborador(busqeuda);
    }    
        
    
}
