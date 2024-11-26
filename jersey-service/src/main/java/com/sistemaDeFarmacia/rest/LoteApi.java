package com.sistemaDeFarmacia.rest;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.sistemaDeFarmacia.rest.controller.dao.services.LoteServices;
import com.sistemaDeFarmacia.rest.models.Lote;

@Path("lote")
public class LoteApi {
    
    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLotes() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        LoteServices ls = new LoteServices();
        map.put("msg", "OK");
        map.put("data", ls.listAll().toArray());
        if (ls.listAll().isEmpty()) {
            map.put("data", new Object[]{});
        }
        return Response.ok(map).build();
    }

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap<String, Object> map) {
        LoteServices ls = new LoteServices();
        HashMap<String, Object> res = new HashMap<>();
        Gson g = new Gson();
        String a = g.toJson(map);
        System.out.println("**********" + a);
        try {
            ls.getLote().setId((int) map.get("idLote"));
            ls.getLote().setCantidad((int) map.get("cantidad"));
            ls.getLote().setFechaEntrega(g.fromJson(map.get("fechaEntrega").toString(), java.util.Date.class));
            ls.getLote().setPrecioLote(Float.parseFloat(map.get("precioLote").toString()));
            ls.getLote().setFechaCaducidad(g.fromJson(map.get("fechaCaducidad").toString(), java.util.Date.class));
            ls.getLote().setPrecioVenta(Float.parseFloat(map.get("precioVenta").toString()));
            ls.getLote().setPrecioCompra(Float.parseFloat(map.get("precioCompra").toString()));
            ls.getLote().setCodigoLote(map.get("codigoLote").toString());
            ls.save();
            res.put("msg", "OK");
            res.put("data", "Lote guardado");
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("msg", "ERROR");
            res.put("data", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLote(@PathParam("id") Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        LoteServices ls = new LoteServices();
        
        try {
            ls.setLote(ls.get(id));
        } catch (Exception e) {
            map.put("msg", "ERROR");
            map.put("data", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
        
        map.put("msg", "OK");
        map.put("data", ls.getLote());

        if (ls.getLote().getIdLote() == 0) { 
            map.put("data", "No existe un lote con ese identificador");
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }

        return Response.ok(map).build();
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(HashMap<String, Object> map) {
        HashMap<String, Object> res = new HashMap<>();
        LoteServices ls = new LoteServices();
        
        try {
            Lote existingLote = ls.get(Integer.parseInt(map.get("idLote").toString()));
            
            if (existingLote == null) {
                res.put("msg", "ERROR");
                res.put("data", "No existe un lote con ese identificador");
                return Response.status(Status.NOT_FOUND).entity(res).build();
            }
            
            existingLote.setCantidad((int) map.get("cantidad"));
            existingLote.setFechaEntrega(new Gson().fromJson(map.get("fechaEntrega").toString(), java.util.Date.class));
            existingLote.setPrecioLote(Float.parseFloat(map.get("precioLote").toString()));
            existingLote.setFechaCaducidad(new Gson().fromJson(map.get("fechaCaducidad").toString(), java.util.Date.class));
            existingLote.setPrecioVenta(Float.parseFloat(map.get("precioVenta").toString()));
            existingLote.setPrecioCompra(Float.parseFloat(map.get("precioCompra").toString()));
            existingLote.setCodigoLote(map.get("codigoLote").toString());
            
            ls.setLote(existingLote);
            ls.update();
            
            res.put("msg", "OK");
            res.put("data", "Lote actualizado correctamente");
            return Response.ok(res).build();
            
        } catch (Exception e) {
            System.out.println("Error al actualizar el lote: " + e.toString());
            res.put("msg", "ERROR");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
}
