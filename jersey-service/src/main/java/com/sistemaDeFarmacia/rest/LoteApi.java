package com.sistemaDeFarmacia.rest;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sistemaDeFarmacia.rest.controller.dao.services.LoteServices;
import com.sistemaDeFarmacia.rest.controller.tda.list.LinkedList;
import com.sistemaDeFarmacia.rest.models.Lote;

@Path("lote")
public class LoteApi {

    private LoteServices loteServices = new LoteServices();
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create(); // Configuración de fechas

    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLotes() {
        HashMap<String, Object> response = new HashMap<>();
        try {
            response.put("msg", "OK");
            response.put("data", loteServices.listAll().toArray());
            return Response.ok(response).build();
        } catch (Exception e) {
            response.put("msg", "ERROR");
            response.put("data", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
        }
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLote(@PathParam("id") Integer id) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            Lote lote = loteServices.get(id);
            if (lote == null) {
                response.put("msg", "ERROR");
                response.put("data", "No existe un lote con ese identificador");
                return Response.status(Status.NOT_FOUND).entity(response).build();
            }
            response.put("msg", "OK");
            response.put("data", lote);
            return Response.ok(response).build();
        } catch (Exception e) {
            response.put("msg", "ERROR");
            response.put("data", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
        }
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(String json) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            Lote lote = gson.fromJson(json, Lote.class);
            Lote existingLote = loteServices.get(lote.getIdLote());
            if (existingLote == null) {
                response.put("msg", "ERROR");
                response.put("data", "No existe un lote con ese identificador");
                return Response.status(Status.NOT_FOUND).entity(response).build();
            }
            loteServices.setLote(lote);
            loteServices.update();
            response.put("msg", "OK");
            response.put("data", "Lote actualizado correctamente");
            return Response.ok(response).build();
        } catch (Exception e) {
            response.put("msg", "ERROR");
            response.put("data", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
        }
    }

    @Path("/list/order/{attribute}/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFechaEntrega(@PathParam("attribute") String attribute, @PathParam("type") Integer type) {
        HashMap map = new HashMap<>();
        LoteServices fs = new LoteServices();
        map.put("msg", "OK");
        
        try {
            LinkedList lsita = fs.order_object(type, attribute);
            map.put("data", lsita.toArray());
            if (lsita.isEmpty()) {
                map.put("data", new Object[] {});
            }
        } catch (Exception e) {
           
        }

        return Response.ok(map).build();
    }

    @Path("/saveWithProduct/{nombreProducto}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveLoteWithProduct(String json, @PathParam("nombreProducto") String nombreProducto) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            Lote lote = gson.fromJson(json, Lote.class);
            loteServices.setLote(lote);
            loteServices.saveWithProduct(nombreProducto);
            response.put("msg", "OK");
            response.put("data", "Lote guardado correctamente con el producto asociado");
            return Response.ok(response).build();
        } catch (Exception e) {
            response.put("msg", "ERROR");
            response.put("data", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
        }
    }

    @Path("/byProduct/{nombreProducto}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLotesByProducto(@PathParam("nombreProducto") String nombreProducto) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            LinkedList<Lote> lotes = loteServices.getLotesByProducto(nombreProducto);
            response.put("msg", "OK");
            response.put("data", lotes.toArray());
            return Response.ok(response).build();
        } catch (Exception e) {
            response.put("msg", "ERROR");
            response.put("data", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
        }
    }
}
