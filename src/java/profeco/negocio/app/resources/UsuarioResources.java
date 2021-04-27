

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
import profeco.negocio.app.services.UsuarioServices;

import profeco.negocio.dto.Usuario;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResources {
     private final UsuarioServices servicio;

    public UsuarioResources() {
        this.servicio = new UsuarioServices();
    }

    @GET
    public Response getUsuario(@QueryParam("UsuarioID") String nombre) {
        Usuario[] arr = null;
        
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
        List<Usuario> list = new LinkedList<>();
        list = Arrays.asList(arr);
        GenericEntity<List<Usuario>> entities = new GenericEntity<List<Usuario>>(list) {
        };

        return Response.ok(entities).build();
    }

//    @GET
//    @Path("/{usuarioId}")
//
//    public Response getSupermercado(@PathParam("usuarioId") String id) {
//        Supermercado objSuper = null;
//        Response response = null;
//        try {
//            objSuper = servicio.Buscar(id);
//            Supermercado[] arr = new Supermercado[1];
//            arr[0] = objSuper;
//            response = Response.ok(arr).build();
//        } catch (Exception e) {
//            response = Response.status(Response.Status.NOT_FOUND).build();
//        }
//
//        return response;
//    }


    @POST
    public Response addUsuario(Usuario obj, @Context UriInfo uriInfo) {

        try {
            servicio.Agregar(obj);
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(obj.getIdUsuario())).build();
        //return Response.status(Response.Status.CREATED).build();
        return Response.created(uri).entity(obj).build();
    }

    @PUT
    @Path("/{usuarioId}")
    public Response updateSupermercado(@PathParam("usuarioId") String id, Usuario objSuper) {
        objSuper.setIdUsuario(id);
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
    @Path("/{usuarioId}")

    public Response deleteSupermercado(@PathParam("usuarioId") String id) {
        Response response = null;

        Usuario objSuper = new Usuario();

        try {
            objSuper.setIdUsuario(id);
            servicio.Eliminar(objSuper);
            response = Response.ok().build();
        } catch (Exception e) {
            response = Response.status(Response.Status.NOT_FOUND).build();
        }

        return response;
    }
    
}
