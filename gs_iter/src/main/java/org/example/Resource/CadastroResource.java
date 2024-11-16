package org.example.Resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.Entidades.Cadastro;
import org.example.Entidades.Login;
import org.example.Repositorio.RepositorioCadastro;
import org.example.Service.CadastroService;


import java.util.List;
import java.util.Map;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("cadastro")
public class CadastroResource {
    private RepositorioCadastro repositorioCadastro;
    private CadastroService cadastroService;

    public CadastroResource() {
        this.repositorioCadastro = new RepositorioCadastro();
        this.cadastroService = new CadastroService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cadastro> getAllCadastro() {
        return repositorioCadastro.exibir();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCadastro(Cadastro cadastro) {
        if (cadastro.getNome() == null || cadastro.getNome().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("O nome é obrigatório").build();
        }
        if (cadastro.getEmail() == null || cadastro.getEmail().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("O email é obrigatório").build();
        }
        try {
            cadastroService.Criar(cadastro);
            return Response.status(Response.Status.CREATED).entity("Cadastro criado com sucesso").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCadastro(@PathParam("id") int id, Cadastro cadastro) {
        try {
            cadastroService.Atualizar(cadastro, id);
            return Response.status(Response.Status.OK).entity("Cadastro atualizado com sucesso").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCadastro(@PathParam("id") int id) {
        Cadastro cadastro = repositorioCadastro.buscarPorId(id);
        if (cadastro != null) {
            repositorioCadastro.excluir(id);
            return Response.status(Response.Status.OK).entity("Cadastro excluído com sucesso").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Cadastro não encontrado").build();
        }
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Login loginRequest) {
        try {
            Cadastro cadastro = cadastroService.authenticate(loginRequest.getEmail(), loginRequest.getSenha());
            return Response.status(Response.Status.OK).entity(cadastro).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }
}

