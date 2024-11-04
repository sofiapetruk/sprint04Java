package br.com.fiap.resource;

import br.com.fiap.bo.CadastroBO;
import br.com.fiap.to.CadastroTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


import java.util.ArrayList;

@Path("/cadastro")
public class CadastroResource {

    private CadastroBO cadastroBO = new CadastroBO();
    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public Response findAll() {
        ArrayList<CadastroTO> resultado = cadastroBO.findAll();
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
    public Response findByCodigo(@PathParam("codigo") Long codigo) {
        CadastroTO resultado = cadastroBO.findByCodigo(codigo);

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
    public Response save(@Valid CadastroTO cadastro) throws Exception {
        CadastroTO resultado = cadastroBO.save(cadastro);

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
    public Response delete(@PathParam("codigo") Long idCadastro) {

        Response.ResponseBuilder response = null;

        if (cadastroBO.delete(idCadastro)) {
            response = Response.status(204); //204 NO CONTENT
        } else {
            response = Response.status(404); //404 NOT FOUND
        }
        return response.build();
    }

    @PUT
    @Path("/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid CadastroTO cadastro,@PathParam("codigo") Long idCadastro) {

        cadastro.setIdCliente(idCadastro);
        CadastroTO resultado = cadastroBO.updtade(cadastro);

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
