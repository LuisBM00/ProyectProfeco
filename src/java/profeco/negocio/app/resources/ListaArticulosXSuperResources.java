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
import profeco.negocio.app.services.ListaArticulosXSuperServices;
import profeco.negocio.app.dto.ListaArticulosXSuper;

@Path("/ListaArticulosXSupermercados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ListaArticulosXSuperResources {

    private final ListaArticulosXSuperServices servicio;

    public ListaArticulosXSuperResources() {
        this.servicio = new ListaArticulosXSuperServices();
    }

    @GET
    public Response getListaArticulosXSupermercado(@QueryParam("idSupermercado") int idSupermercado) {
        ListaArticulosXSuper[] arr = null;
        
       try{
                arr = servicio.Listar(idSupermercado);
            

        } catch (Exception e) {
            Response.status(Response.Status.INTERNAL_SERVER_ERROR);
        }
        List<ListaArticulosXSuper> list = new LinkedList<>();
        list = Arrays.asList(arr);
        GenericEntity<List<ListaArticulosXSuper>> entities = new GenericEntity<List<ListaArticulosXSuper>>(list) {
        };

        return Response.ok(entities).build();
    }

    @GET
    @Path("/{superMercadoId}")

    public Response getSupermercado(@PathParam("superMercadoId") int id) {
        ListaArticulosXSuper objSuper = null;
        Response response = null;
        try {
            objSuper = servicio.Buscar(id);
            ListaArticulosXSuper[] arr = new ListaArticulosXSuper[1];
            arr[0] = objSuper;
            response = Response.ok(arr).build();
        } catch (Exception e) {
            response = Response.status(Response.Status.NOT_FOUND).build();
        }

        return response;
    }


    @POST
    public Response addListaArticulosXSuper(ListaArticulosXSuper obj, @Context UriInfo uriInfo) {

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
    public Response updateListaArticulosXSuper(@PathParam("superMercadoId") int id, ListaArticulosXSuper objSuper) {
        objSuper.setIdLista(id);
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

    public Response deleteListaArticulosXSuper(@PathParam("superMercadoId") int id) {
        Response response = null;

        ListaArticulosXSuper objSuper = new ListaArticulosXSuper();

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