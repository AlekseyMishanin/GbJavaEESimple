package ru.mishanin.rest;

import ru.mishanin.service.CategoryCustom;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/category")
public interface CategoryServiceWs {

    @PUT
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(CategoryCustom category);

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    void update(CategoryCustom category);

    @DELETE
    @Path("/{id}/id")
    @Consumes(MediaType.APPLICATION_JSON)
    void delete(CategoryCustom category);

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    List<CategoryCustom> findAll();
}
