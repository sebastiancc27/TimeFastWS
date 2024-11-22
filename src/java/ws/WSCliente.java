/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dominio.IMPCliente;
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
import pojo.Mensaje;

/**
 * REST Web Service
 *
 * @author sebas
 */
@Path("cliente")
public class WSCliente {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WSCliente
     */
    public WSCliente() {
    }


    @Path("registrar-cliente")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarCliente(String json){
        try {
        Gson gson = new Gson();      
        Cliente cliente = gson.fromJson(json, Cliente.class);      
       return IMPCliente.registrarCliente(cliente);
        } catch (Exception e) {
             throw new BadRequestException();
        }
    }
    @Path("editar-cliente")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarCliente(String json){
        try {
        Gson gson = new Gson();      
        Cliente cliente = gson.fromJson(json, Cliente.class);      
       return IMPCliente.editarCliente(cliente);
        } catch (Exception e) {
             throw new BadRequestException();
        }
    }
    
    @Path("eliminar-cliente/{idCliente}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarCliente(@PathParam("idCliente") Integer idCliente){
        return IMPCliente.eliminarCliente(idCliente);
    }    
    
    @Path("buscar-cliente/{busqueda}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> buscarClientes(@PathParam("busqueda") String busqueda){
        return IMPCliente.buscarClientes(busqueda);
    }
}
