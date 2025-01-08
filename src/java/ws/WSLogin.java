/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import dominio.IMPLogin;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import pojo.LoginColaborador;

/**
 * REST Web Service
 *
 * @author sebas
 */
@Path("login")
public class WSLogin {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WSLogin
     */
    public WSLogin() {
    }

    @Path("login-colaborador")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public LoginColaborador iniciarSesionColaborador(@FormParam("noPersonal") String noPersonal,
            @FormParam("contrasena") String contrasena) {
        return IMPLogin.loginColaborador(noPersonal, contrasena);
    }

    @Path("login-conductor")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public LoginColaborador inicioSesionConductor(@FormParam("noPersonal") String noPersonal,
            @FormParam("contrasena") String contrasena) {
        return IMPLogin.loginConductor(noPersonal, contrasena);
    }
}
