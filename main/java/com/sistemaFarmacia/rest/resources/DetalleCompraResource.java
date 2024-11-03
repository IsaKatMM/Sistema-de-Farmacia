package com.sistemaFarmacia.rest.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.sistemaDeFarmacia.rest.models.DetalleCompra;
import com.sistemaDeFarmacia.rest.controller.dao.implement.DetalleCompraDao;

@Path("/detallecompra")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DetalleCompraResource {
    
    private DetalleCompraDao dao = DetalleCompraDao.getInstance();

    @GET
    public Response getAll() {
        return Response.ok(dao.listAll()).build();
    }

    @POST
    public Response create(DetalleCompra detalle) {
        try {
            dao.persist(detalle);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                         .entity(e.getMessage())
                         .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, DetalleCompra detalle) {
        try {
            dao.merge(detalle, id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                         .entity(e.getMessage())
                         .build();
        }
    }
}
