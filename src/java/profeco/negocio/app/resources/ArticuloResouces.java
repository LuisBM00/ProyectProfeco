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
import profeco.negocio.app.services.ArticuloServices;
import profeco.negocio.app.services.SupermercadoServices;
import profeco.negocio.app.dto.Articulo;
import profeco.negocio.app.dto.Supermercado;

/**
 *
 * @author Luis Barroso
 */
@Path("/articulos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArticuloResouces {
    
    private final ArticuloServices servicio;

    public ArticuloResouces() {
        this.servicio = new ArticuloServices();
    }

    @GET
    public Response getArticulo(@QueryParam("ArticuloNombre") String nombre) {
        Articulo[] arr = null;
        
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
        List<Articulo> list = new LinkedList<>();
        list = Arrays.asList(arr);
        GenericEntity<List<Articulo>> entities = new GenericEntity<List<Articulo>>(list) {
        };

        return Response.ok(entities).build();
    }

    @GET
    @Path("/{articuloId}")

    public Response getArticulo(@PathParam("articuloId") int id) {
        Articulo objSuper = null;
        Response response = null;
        try {
            objSuper = servicio.Buscar(id);
            Articulo[] arr = new Articulo[1];
            arr[0] = objSuper;
            response = Response.ok(arr).build();
        } catch (Exception e) {
            response = Response.status(Response.Status.NOT_FOUND).build();
        }
        return response;
    }


    @POST
    public Response addArticulo(Articulo obj, @Context UriInfo uriInfo) {

        try {
            servicio.Agregar(obj);
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(obj.getIdArticulo())).build();
        //return Response.status(Response.Status.CREATED).build();
        return Response.created(uri).entity(obj).build();
    }

    @PUT
    @Path("/{articuloId}")
    public Response updateSupermercado(@PathParam("articuloId") int id, Articulo objSuper) {
        objSuper.setIdArticulo(id);
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
    @Path("/{articuloId}")

    public Response deleteArticulo(@PathParam("articuloId") int id) {
        Response response = null;

        Articulo objSuper = new Articulo();

        try {
            objSuper.setIdArticulo(id);
            servicio.Eliminar(objSuper);
            response = Response.ok().build();
        } catch (Exception e) {
            response = Response.status(Response.Status.NOT_FOUND).build();
        }

        return response;
    }
}
