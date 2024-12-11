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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.sistemaDeFarmacia.rest.controller.dao.services.FacturaServices;
import com.sistemaDeFarmacia.rest.models.enumerador.MetodoPago;
import com.google.gson.Gson;

@Path("factura")
public class FacturaApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFacturas() {
        HashMap<String, Object> map = new HashMap<>();
        FacturaServices fs = new FacturaServices();
        map.put("msg", "Ok");
        map.put("data", fs.listAll().toArray());
        if (fs.listAll().isEmpty()) {
            map.put("data", new Object[]{});
        }
        return Response.ok(map).build();
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFactura(@PathParam("id") Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        FacturaServices fs = new FacturaServices();
        try {
            fs.setFactura(fs.get(id));
        } catch (Exception e) {
            // TODO: handle exception
        }
        map.put("msg", "Ok");
        map.put("data", fs.getFactura());
        if (fs.getFactura().getId_factura() == null) {
            map.put("data", "No existe la factura con ese identificador");
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
            FacturaServices fs = new FacturaServices();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaEmision = sdf.parse(map.get("fechaEmision").toString());
            fs.getFactura().setFechaEmision(fechaEmision);
            fs.getFactura().setMetodoPago(MetodoPago.valueOf(map.get("metodoPago").toString()));
            fs.getFactura().setObservaciones(map.get("observaciones").toString());
            fs.getFactura().setSubTotal(Float.parseFloat(map.get("subTotal").toString()));
            fs.getFactura().setIVA(Float.parseFloat(map.get("IVA").toString()));
            fs.getFactura().setTotal_USD(Float.parseFloat(map.get("total_USD").toString()));

            fs.save();
            res.put("msg", "OK");
            res.put("msg", "Factura registrada correctamente");
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
            FacturaServices fs = new FacturaServices();
            fs.setFactura(fs.get(Integer.parseInt(map.get("id_factura").toString())));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaEmision = sdf.parse(map.get("fechaEmision").toString());
            fs.getFactura().setFechaEmision(fechaEmision);
            
            fs.getFactura().setMetodoPago(MetodoPago.valueOf(map.get("metodoPago").toString()));
            fs.getFactura().setObservaciones(map.get("observaciones").toString());
            fs.getFactura().setSubTotal(Float.parseFloat(map.get("subTotal").toString()));
            fs.getFactura().setIVA(Float.parseFloat(map.get("IVA").toString()));
            fs.getFactura().setTotal_USD(Float.parseFloat(map.get("total_USD").toString()));

            fs.update();
            res.put("msg", "OK");
            res.put("msg", "Factura actualizada correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error en update data " + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
}