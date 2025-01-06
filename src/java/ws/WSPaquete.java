package ws;

import com.google.gson.Gson;
import dominio.IMPPaquete;
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
import javax.ws.rs.core.MediaType;
import pojo.Mensaje;
import pojo.Paquete;

@Path("paquete")
public class WSPaquete {

    @Context
    private UriInfo context;

    public WSPaquete() {
    }

    @Path("registrar-paquete")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarPaquete(String paquete) {
        try {
            Gson gson = new Gson();
            Paquete paqueteGson = gson.fromJson(paquete, Paquete.class);
            return IMPPaquete.registrarPaquete(paqueteGson);
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @Path("actualizar-paquete")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje actualizarPaquete(String paquete) {
        try {
            Gson gson = new Gson();
            Paquete paqueteGson = gson.fromJson(paquete, Paquete.class);
            return IMPPaquete.actualizarPaquete(paqueteGson);
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @Path("obtener-paquete-envio/{envio}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paquete> obtenerPaquetePorEnvio(@PathParam("envio") Integer envio) {
        return IMPPaquete.obtenerPaquetePorEnvio(envio);
    }

    @Path("obtener-paquetes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paquete> obtenerPaquetePorEnvio() {
        return IMPPaquete.obtnerPaquetes();
    }

    @Path("eliminar-paquete/{idPaquete}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarPaquete(@PathParam("idPaquete") Integer idPaquete) {
        try {
            return IMPPaquete.eliminarPaquete(idPaquete);
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

}
