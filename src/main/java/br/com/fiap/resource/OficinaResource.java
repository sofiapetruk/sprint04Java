package br.com.fiap.resource;

import br.com.fiap.bo.OficinaBO;
import br.com.fiap.to.AdicionarCarroTO;
import br.com.fiap.to.OficinaTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
@Path("/oficina")
public class OficinaResource {

    private OficinaBO oficinaBO = new OficinaBO();
    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public Response findAll() {
        ArrayList<OficinaTO> resultado = oficinaBO.findAll();

        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok();
        } else {
            response = Response.status(404); // 404 (NOT FOUND)
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response findById(@PathParam("codigo") Long idOficina) {

        OficinaTO resultado = oficinaBO.findById(idOficina);

        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok(); //200 ok
        } else {
            response = Response.status(404); //404 NOT FOUND
        }
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(OficinaTO oficina) {
        OficinaTO resultado = oficinaBO.save(oficina);

        Response.ResponseBuilder response = null;

        if  (resultado != null) {
            response = Response.created(null); //201 - CREATED
        } else {
            response = Response.status(400); // 400 - BAD REQUEST
        }
        response.entity(resultado);
        return  response.build();
    }

    @PUT
    @Path("/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(OficinaTO oficina, @PathParam("codigo") Long idOficina) {
        oficina.setIdOficina(idOficina);
        OficinaTO resultado = oficinaBO.update(oficina);

        Response.ResponseBuilder response;

        if (resultado != null) {
            response = Response.ok(resultado); // 200 - OK, para indicar que a atualização foi bem-sucedida.
        } else {
            response = Response.status(400); // 400 - BAD REQUEST, quando a atualização falha.
        }

        return response.build();
    }

    @DELETE
    @Path("/{codigo}")
    public Response delete(@PathParam("codigo") Long idOficina) {
        Response.ResponseBuilder response = null;

        if (oficinaBO.delete(idOficina)) {
            response = Response.status(204); //204 NO CONTENT
        } else {
            response = Response.status(404); //404 NOT FOUND
        }
        return response.build();
    }
}
