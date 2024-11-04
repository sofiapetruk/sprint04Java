package br.com.fiap.resource;

import br.com.fiap.bo.AvaliacoesClienteBO;
import br.com.fiap.to.AvaliacaoClienteTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/avaliacao")
public class AvaliacaoClienteResource {

    private AvaliacoesClienteBO avaliacaoBO = new AvaliacoesClienteBO();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<AvaliacaoClienteTO> resultado = avaliacaoBO.findAll();

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
    public Response findById(@PathParam("codigo") Long idAvaliacao) {

        AvaliacaoClienteTO resultado = avaliacaoBO.findById(idAvaliacao);

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
    public Response save(@Valid AvaliacaoClienteTO avaliacao) {

        AvaliacaoClienteTO resultado = avaliacaoBO.save(avaliacao);

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
    public Response delete(@PathParam("codigo") Long idAvaliacao) {
        Response.ResponseBuilder response = null;

        if (avaliacaoBO.delete(idAvaliacao)) {
            response = Response.status(204); //204 NO CONTENT
        } else {
            response = Response.status(404); //404 NOT FOUND
        }
        return response.build();
    }

    @PUT
    @Path("/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid AvaliacaoClienteTO avaliacao, @PathParam("codigo") Long idAvaliacao) {

        avaliacao.setIdAvaliacao(idAvaliacao);
        AvaliacaoClienteTO resultado = avaliacaoBO.update(avaliacao);

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