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
import profeco.negocio.app.services.ReporteInconsistenciaServices;
import profeco.negocio.app.dto.ReporteInconsistencia;

@Path("/ReporteInconsistencia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReporteInconsistenciaResources {

    private final ReporteInconsistenciaServices servicio;

    public ReporteInconsistenciaResources() {
        this.servicio = new ReporteInconsistenciaServices();
    }

    @GET
    public Response getInconsistencia(@QueryParam("idSupermercado") int id) {
        ReporteInconsistencia[] arr = null;
        
        try {

        
                arr = servicio.Listar(id);
            
        } catch (Exception e) {
            Response.status(Response.Status.INTERNAL_SERVER_ERROR);
        }
        List<ReporteInconsistencia> list = new LinkedList<>();
        list = Arrays.asList(arr);
        GenericEntity<List<ReporteInconsistencia>> entities = new GenericEntity<List<ReporteInconsistencia>>(list) {
        };

        return Response.ok(entities).build();
    }

    @GET
    @Path("/{superMercadoId}")

    public Response getSupermercado(@PathParam("superMercadoId") int id) {
        ReporteInconsistencia objSuper = null;
        Response response = null;
        try {
            objSuper = servicio.Buscar(id);
            ReporteInconsistencia[] arr = new ReporteInconsistencia[1];
            arr[0] = objSuper;
            response = Response.ok(arr).build();
        } catch (Exception e) {
            response = Response.status(Response.Status.NOT_FOUND).build();
        }

        return response;
    }


    @POST
    public Response addInconsistencia(ReporteInconsistencia obj, @Context UriInfo uriInfo) {

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
