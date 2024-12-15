/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import dominio.IMPRol;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import pojo.Rol;

/**
 * REST Web Service
 *
 * @author sebas
 */
@Path("rol")
public class WSRol {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WSRol
     */
    public WSRol() {
    }

    @Path("obtener-roles")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rol> obtenerRoles(){
        return IMPRol.obteneRoles();
    }
}
