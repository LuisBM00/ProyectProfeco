/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package profeco.negocio.app.resources;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import profeco.negocio.app.dto.CalificacionesComentarios;
import profeco.negocio.app.dto.ListaArticulosXSuper;
import profeco.negocio.app.dto.Multa;
import profeco.negocio.app.services.MultaService;

/**
 *
 * @author Luis Barroso
 */
@Path("/multas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MultaResources {
    private final MultaService servicio;

    public MultaResources() {
        this.servicio = new MultaService();
    }
    
    @GET
    public Response getCalificacion() {
        Multa[] arr = null;
        
        try {

           
                arr = servicio.Listar(0);
            

        } catch (Exception e) {
            Response.status(Response.Status.INTERNAL_SERVER_ERROR);
        }
        List<Multa> list = new LinkedList<>();
        list = Arrays.asList(arr);
        GenericEntity<List<Multa>> entities = new GenericEntity<List<Multa>>(list) {
        };

        return Response.ok(entities).build();
    }
    
    @DELETE
    @Path("/{multaId}")

    public Response deleteListaArticulosXSuper(@PathParam("multaId") int id) {
        Response response = null;

        Multa objSuper = new Multa();

        try {
            objSuper.setIdMulta(id);
            servicio.Eliminar(objSuper);
            response = Response.ok().build();
        } catch (Exception e) {
            response = Response.status(Response.Status.NOT_FOUND).build();
        }

        return response;
    }

    
    
}
