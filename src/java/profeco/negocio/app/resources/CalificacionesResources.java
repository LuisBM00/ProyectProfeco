/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package profeco.negocio.app.resources;

import java.net.URI;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import profeco.negocio.app.services.CalificacionesSevices;
import profeco.negocio.app.dto.CalificacionesComentarios;

/**
 *
 * @author Carlo
 */

@Path("/calificaciones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CalificacionesResources {
    private final CalificacionesSevices servicio;

    public CalificacionesResources() {
        this.servicio = new CalificacionesSevices();
    }

    @GET
    public Response getCalificacion(@QueryParam("idSupermercado") int idSupermercado) {
        CalificacionesComentarios[] arr = null;
        
        try {

           
                arr = servicio.Listar(idSupermercado);
            

        } catch (Exception e) {
            Response.status(Response.Status.INTERNAL_SERVER_ERROR);
        }
        List<CalificacionesComentarios> list = new LinkedList<>();
        list = Arrays.asList(arr);
        GenericEntity<List<CalificacionesComentarios>> entities = new GenericEntity<List<CalificacionesComentarios>>(list) {
        };

        return Response.ok(entities).build();
    }

    @GET
    @Path("/{idSupermercado}")

    public Response getCalificacionUno(@PathParam("idSupermercado") int id) {
        CalificacionesComentarios objSuper = null;
        Response response = null;
        try {
        
            CalificacionesComentarios[] arr = new CalificacionesComentarios[1];
            arr[0] = objSuper;
            response = Response.ok(arr).build();
        } catch (Exception e) {
            response = Response.status(Response.Status.NOT_FOUND).build();
        }

        return response;
    }


    @POST
    public Response addCalificacion(CalificacionesComentarios obj, @Context UriInfo uriInfo) {

        try {
            servicio.Agregar(obj);
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(obj.getIdSupermercado())).build();
        //return Response.status(Response.Status.CREATED).build();
        return Response.created(uri).entity(obj).build();
    }

   

}
