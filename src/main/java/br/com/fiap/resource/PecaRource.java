package br.com.fiap.resource;

import br.com.fiap.bo.PecaBO;
import br.com.fiap.to.PecaTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/peca")
public class PecaRource {

    private PecaBO pecaBO = new PecaBO();
    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public Response findAll() {
        ArrayList<PecaTO> resultado = pecaBO.findAll();

        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok(); // 200 (ok)
        } else {
            response = Response.status(404); // 404 (NOT FOUND)
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("codigo") Long idPeca) {

        PecaTO resultado = pecaBO.findByCodigo(idPeca);

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
    public Response save(@Valid PecaTO peca) {
        PecaTO resultado = pecaBO.save(peca);

        Response.ResponseBuilder response = null;

        if  (resultado != null) {
            response = Response.created(null); //201 - CREATED
        } else {
            response = Response.status(400); // 400 - BAD REQUEST
        }
        response.entity(resultado);
        return  response.build();

    }

    @DELETE
    @Path("/{codigo}")
    public Response delete(@PathParam("codigo") Long idPeca) {

        Response.ResponseBuilder response = null;

        if (pecaBO.delete(idPeca)) {
            response = Response.status(204); //204 NO CONTENT
        } else {
            response = Response.status(404);
        }
        return response.build();
    }

    @GET
    @Path("/media/{peca}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findMediaPeca(@PathParam("peca") String nomePeca) {
        double resultado = pecaBO.media(nomePeca);

        Response.ResponseBuilder response;

        if (resultado >= 0) {
            response = Response.ok(Double.valueOf(resultado)); //200 ok
        } else {
            response = Response.status(404); //404 NOT FOUND
        }

        return response.build();
    }

    @PUT
    @Path("/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid PecaTO peca, @PathParam("codigo") Long idPeca) {

        peca.setIdPeca(idPeca);
        PecaTO resultado = pecaBO.update(peca);

        Response.ResponseBuilder response = null;


        if  (resultado != null) {
            response = Response.created(null); //201 - CREATED
        } else {
            response = Response.status(400); // 400 - BAD REQUEST
        }
        response.entity(resultado);
        return  response.build();
    }

}
