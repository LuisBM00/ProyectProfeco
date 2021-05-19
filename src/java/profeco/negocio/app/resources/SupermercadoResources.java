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
import profeco.negocio.app.services.SupermercadoServices;
import profeco.negocio.app.dto.Supermercado;

@Path("/supermercados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SupermercadoResources {

    private final SupermercadoServices servicio;

    public SupermercadoResources() {
        this.servicio = new SupermercadoServices();
    }

    @GET
    public Response getSupermercado(@QueryParam("SuperNombre") String nombre) {
        Supermercado[] arr = null;
        
        try {

            if (nombre != null && nombre.trim().length() > 0) {
                //arr = servicio.Buscar(nombre);
                arr = servicio.Listar(nombre);
            } else {
                arr = servicio.Listar("");
            }

        } catch (Exception e) {
            Response.status(Response.Status.INTERNAL_SERVER_ERROR);
        }
        List<Supermercado> list = new LinkedList<>();
        list = Arrays.asList(arr);
        GenericEntity<List<Supermercado>> entities = new GenericEntity<List<Supermercado>>(list) {
        };

        return Response.ok(entities).build();
    }

    @GET
    @Path("/{superMercadoId}")

    public Response getSupermercado(@PathParam("superMercadoId") int id) {
        Supermercado objSuper = null;
        Response response = null;
        try {
            objSuper = servicio.Buscar(id);
            Supermercado[] arr = new Supermercado[1];
            arr[0] = objSuper;
            response = Response.ok(arr).build();
        } catch (Exception e) {
            response = Response.status(Response.Status.NOT_FOUND).build();
        }

        return response;
    }


    @POST
    public Response addSupermercado(Supermercado obj, @Context UriInfo uriInfo) {

        try {
            servicio.Agregar(obj);
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(obj.getIdSupermercado())).build();
        //return Response.status(Response.Status.CREATED).build();
        return Response.created(uri).entity(obj).build();
    }

    @PUT
    @Path("/{superMercadoId}")
    public Response updateSupermercado(@PathParam("superMercadoId") int id, Supermercado objSuper) {
        objSuper.setIdSupermercado(id);
        Response response = null;
        try {
            servicio.Modificar(objSuper);

            response = Response.status(Response.Status.CREATED).entity(objSuper).build();
        } catch (Exception e) {
            response = Response.status(Response.Status.NOT_FOUND).build();
        }

        return response;
    }

    @DELETE
    @Path("/{superMercadoId}")

    public Response deleteSupermercado(@PathParam("superMercadoId") int id) {
        Response response = null;

        Supermercado objSuper = new Supermercado();

        try {
            objSuper.setIdSupermercado(id);
            servicio.Eliminar(objSuper);
            response = Response.ok().build();
        } catch (Exception e) {
            response = Response.status(Response.Status.NOT_FOUND).build();
        }

        return response;
    }

}
