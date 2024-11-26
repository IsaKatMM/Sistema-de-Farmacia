package com.sistemaDeFarmacia.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.util.HashMap;

import com.sistemaDeFarmacia.controls.dao.services.DetalleFacturaServices;
import com.google.gson.Gson;

@Path("detalleFactura")
public class DetalleFacturaApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDetallesFactura() {
        HashMap<String, Object> map = new HashMap<>();
        DetalleFacturaServices dfs = new DetalleFacturaServices();
        map.put("msg", "Ok");
        map.put("data", dfs.listAll().toArray());
        if (dfs.listAll().isEmpty()) {
            map.put("data", new Object[]{});
        }
        return Response.ok(map).build();
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDetalleFactura(@PathParam("id") Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        DetalleFacturaServices dfs = new DetalleFacturaServices();
        try {
            dfs.setDetalleFactura(dfs.get(id));
        } catch (Exception e) {
            // TODO: handle exception
        }
        map.put("msg", "Ok");
        map.put("data", dfs.getDetalleFactura());
        if (dfs.getDetalleFactura().getId_detalleVenta() == null) {
            map.put("data", "No existe el detalle de factura con ese identificador");
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }

        return Response.ok(map).build();
    }

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap<String, Object> map){
        HashMap<String, Object> res = new HashMap<>();
        Gson g = new Gson();
        String a = g.toJson(map);
        System.out.println("**********" + a);
        try {
            DetalleFacturaServices dfs = new DetalleFacturaServices();
            dfs.getDetalleFactura().setCantidad(Integer.parseInt(map.get("cantidad").toString()));
            dfs.getDetalleFactura().setPrecioVenta(Float.parseFloat(map.get("precioVenta").toString()));
            dfs.getDetalleFactura().setTarifa_IVA(Float.parseFloat(map.get("tarifa_IVA").toString()));
            dfs.getDetalleFactura().setTotalVenta(Float.parseFloat(map.get("totalVenta").toString()));

            dfs.save();
            res.put("msg", "OK");
            res.put("msg", "Detalle de factura registrado correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error en save data " + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(HashMap<String, Object> map){
        HashMap<String, Object> res = new HashMap<>();
        try {
            DetalleFacturaServices dfs = new DetalleFacturaServices();
            dfs.setDetalleFactura(dfs.get(Integer.parseInt(map.get("id_detalleVenta").toString())));
            dfs.getDetalleFactura().setCantidad(Integer.parseInt(map.get("cantidad").toString()));
            dfs.getDetalleFactura().setPrecioVenta(Float.parseFloat(map.get("precioVenta").toString()));
            dfs.getDetalleFactura().setTarifa_IVA(Float.parseFloat(map.get("tarifa_IVA").toString()));
            dfs.getDetalleFactura().setTotalVenta(Float.parseFloat(map.get("totalVenta").toString()));

            dfs.update();
            res.put("msg", "OK");
            res.put("msg", "Detalle de factura actualizado correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error en update data " + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
}