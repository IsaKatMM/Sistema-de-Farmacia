package com.sistemaDeFarmacia.rest;

import javax.annotation.Generated;
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

import com.sistemaDeFarmacia.rest.controller.dao.services.ProductoService;
import com.google.gson.Gson;

@Path("product")

public class ProductoApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts(){
        HashMap map = new HashMap<>();
        ProductoService ps = new ProductoService();
        map.put("msg", "Ok");
        map.put("data", ps.listAll().toArray());
        //map.put("data", ps.listAll().toArray);
        if (ps.listAll().isEmpty()) {
            map.put("data", new Object[]{});
        }
        return Response.ok(map).build();
    }

    @Path("/listType")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getType(){
        HashMap map = new HashMap<>();
        ProductoService ps = new ProductoService();
        map.put("msg", "Ok");
        map.put("data", ps.getTipos());
        if (ps.listAll().isEmpty()) {
            map.put("data", new Object[]{});
        }
        return Response.ok(map).build();
    }

    @Path("/get/{IdProducto}")
    @GET@Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("IdProducto") Integer IdProducto){
        HashMap map = new HashMap<>();
        ProductoService ps = new ProductoService();
        try {
            ps.setProducto(ps.get(IdProducto));
        } catch (Exception e) {
            // TODO: handle exception
        }
        map.put("msg", "Ok");
        map.put("data", ps.getProducto());
        if (ps.getProducto().getIdProducto() == null) {
            map.put("data", "No existe un producto con este identificador");
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }
        return Response.ok(map).build();
    }

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap map){
        HashMap res = new HashMap<>();
        Gson g = new Gson();
        String a = g.toJson(map);
        System.out.println("**********"+a);
        try {
            ProductoService ps = new ProductoService();
            ps.getProducto().setNombreProducto(map.get(("NombreProducto")).toString());
            ps.getProducto().setLaboratio(map.get(("Laboratorio")).toString());
            ps.getProducto().setRequiereReceta(map.get(("RequiereReceta")).toString());
            ps.getProducto().setCategoria(map.get(("Categoria")).toString());
            ps.getProducto().setPeso(map.get(("Peso")).toString());
            ps.getProducto().setMarca(map.get(("Marca")).toString());
            ps.getProducto().setStuck(map.get(("Stuck")).toString());
            ps.getProducto().setStuckMinimo(map.get(("StuckMinimo")).toString());

            ps.save();
            res.put("msf", "Ok");
            res.put("msg", "Producto registrado correctamente");
            return Response.ok(res).build();
        } catch (Exception e) {
            System.out.println("Error al guardar" + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(HashMap map){
        HashMap res = new HashMap<>();
        try {
            ps.setProducto().ps.get(Integer.parseInt(map.get("IdProducto").toString()));
            ps.getProducto().setNombreProducto(map.get(("NombreProducto")).toString());
            ps.getProducto().setLaboratio(map.get(("Laboratorio")).toString());
            ps.getProducto().setRequiereReceta(map.get(("RequiereReceta")).toString());
            ps.getProducto().setCategoria(map.get(("Categoria")).toString());
            ps.getProducto().setPeso(map.get(("Peso")).toString());
            ps.getProducto().setMarca(map.get(("Marca")).toString());
            ps.getProducto().setStuck(map.get(("Stuck")).toString());
            ps.getProducto().setStuckMinimo(map.get(("StuckMinimo")).toString());

            ps.update();
            res.put("msf", "Ok");
            res.put("msg", "persona registrada correctamente");
            return Response.ok(res).build();
        } catch (Exception e) {
            System.out.println("Error al guardar la informacion" + e.toString());
            res.put("msf", "Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
}