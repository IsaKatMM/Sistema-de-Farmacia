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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

import com.sistemaDeFarmacia.rest.controller.dao.services.CompraAProveedorServices;
import com.sistemaDeFarmacia.rest.models.CompraAProveedor;
import com.sistemaDeFarmacia.rest.models.enumerador.MetodoPago;
import com.google.gson.Gson;

@Path("compra")
public class CompraAProveedorApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCompraAProveedors() {
        HashMap<String, Object> map = new HashMap<>();
        CompraAProveedorServices fs = new CompraAProveedorServices();
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
    public Response getCompraAProveedor(@PathParam("id") Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        CompraAProveedorServices fs = new CompraAProveedorServices();
        try {
            fs.setCompraAProveedor(fs.get(id));
        } catch (Exception e) {
            // TODO: handle exception
        }
        map.put("msg", "Ok");
        map.put("data", fs.getCompraAProveedor());
        if (fs.getCompraAProveedor().getId() == null) {
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
            CompraAProveedorServices fs = new CompraAProveedorServices();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String fechaActual = LocalDate.now().format(formatter);
            fs.getCompraAProveedor().setFechaCompra(fechaActual);
            fs.getCompraAProveedor().setProducto((map.get("producto").toString()));
            fs.getCompraAProveedor().setCantidad(Integer.parseInt(map.get("cantidad").toString()));
            fs.getCompraAProveedor().setTotalCompra(Float.parseFloat(map.get("totalCompra").toString()));
            fs.getCompraAProveedor().setId_proveedor(Integer.parseInt(map.get("id_proveedor").toString()));
            

            fs.save();
            res.put("msg", "OK");
            res.put("msg", "CompraAProveedor registrada correctamente");
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
        CompraAProveedorServices fs = new CompraAProveedorServices();
        
        try {
            CompraAProveedor existingCompra = fs.get(Integer.parseInt(map.get("id").toString()));
            
            if (existingCompra == null) {
                res.put("msg", "ERROR");
                res.put("data", "No existe un generador con ese identificador");
                return Response.status(Status.NOT_FOUND).entity(res).build();
            }

            fs.getCompraAProveedor().setProducto((map.get("producto").toString()));
            fs.getCompraAProveedor().setCantidad(Integer.parseInt(map.get("observaciones").toString()));
            fs.getCompraAProveedor().setTotalCompra(Float.parseFloat(map.get("totalCompra").toString()));
            fs.getCompraAProveedor().setId_proveedor(Integer.parseInt(map.get("id_proveedor").toString()));

            fs.update();
            res.put("msg", "OK");
            res.put("msg", "CompraAProveedor actualizada correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error en update data " + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
}