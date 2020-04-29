package ru.mishanin.rest;

import ru.mishanin.service.ProductCustom;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/product")
public interface ProductServiceRs {

    @PUT
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(ProductCustom product);

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    void update(ProductCustom product);

    @DELETE
    @Path("/{id}/id")
    void delete(@PathParam("id") int id);

    @GET
    @Path("/{id}/id")
    @Produces(MediaType.APPLICATION_JSON)
    ProductCustom findById(@PathParam("id") int id);

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductCustom> findAll();
}
