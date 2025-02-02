package com.sistemaDeFarmacia.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.HashMap;

import com.sistemaDeFarmacia.rest.controller.dao.services.LoteServices;
import com.sistemaDeFarmacia.rest.controller.dao.services.ProductoService;
import com.sistemaDeFarmacia.rest.models.Producto; // Importar la clase Producto
import com.sistemaDeFarmacia.rest.models.enumerador.TipoProducto;
import com.sistemaDeFarmacia.rest.controller.tda.list.LinkedList; // Importar la clase LinkedList
import com.google.gson.Gson;

@Path("product")
public class ProductoApi {

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() {
        HashMap<String, Object> map = new HashMap<>();
        ProductoService ps = new ProductoService();
        try {
            map.put("msg", "Ok");
            map.put("data", ps.listAll().toArray());
            return Response.ok(map).build();
        } catch (Exception e) {
            map.put("msg", "Error");
            map.put("data", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }

    @Path("/listType")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getType() {
        HashMap<String, Object> map = new HashMap<>();
        ProductoService ps = new ProductoService();
        map.put("msg", "Ok");
        map.put("data", ps.getTipos());
        return Response.ok(map).build();
    }

    @Path("/get/{IdProducto}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("IdProducto") Integer IdProducto) {
        HashMap<String, Object> map = new HashMap<>();
        ProductoService ps = new ProductoService();
        try {
            Producto producto = ps.get(IdProducto);
            if (producto != null) {
                map.put("msg", "Ok");
                map.put("data", producto);
                return Response.ok(map).build();
            } else {
                map.put("msg", "Error");
                map.put("data", "No existe un producto con este identificador");
                return Response.status(Status.NOT_FOUND).entity(map).build();
            }
        } catch (Exception e) {
            map.put("msg", "Error");
            map.put("data", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap<String, Object> map) {
        HashMap<String, Object> res = new HashMap<>();
        ProductoService ps = new ProductoService();
        try {
            ps.getProducto().setNombreProducto(map.get("NombreProducto").toString());
            ps.getProducto().setLaboratio(map.get("Laboratorio").toString());
            ps.getProducto().setRequiereReceta(Boolean.parseBoolean(map.get("RequiereReceta").toString()));
            ps.getProducto().setCategoria(map.get("Categoria").toString());
            ps.getProducto().setPeso(Integer.parseInt(map.get("Peso").toString()));
            ps.getProducto().setMarca(map.get("Marca").toString());
            ps.getProducto().setTipoProducto(TipoProducto.valueOf(map.get("TipoProducto").toString())); // Asignar tipoProducto

            ps.save();
            res.put("msg", "Ok");
            res.put("data", "Producto registrado correctamente");
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("msg", "Error");
            res.put("data", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(HashMap<String, Object> map) {
        HashMap<String, Object> res = new HashMap<>();
        ProductoService ps = new ProductoService();
        try {
            ps.setProducto(ps.get(Integer.parseInt(map.get("IdProducto").toString())));

            ps.getProducto().setNombreProducto(map.get("NombreProducto").toString());
            ps.getProducto().setLaboratio(map.get("Laboratorio").toString());
            ps.getProducto().setRequiereReceta(Boolean.parseBoolean(map.get("RequiereReceta").toString()));
            ps.getProducto().setCategoria(map.get("Categoria").toString());
            ps.getProducto().setPeso(Integer.parseInt(map.get("Peso").toString()));
            ps.getProducto().setMarca(map.get("Marca").toString());
            ps.getProducto().setTipoProducto(TipoProducto.valueOf(map.get("TipoProducto").toString())); // Asignar tipoProducto

            ps.update();
            res.put("msg", "Ok");
            res.put("data", "Producto actualizado correctamente");
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("msg", "Error");
            res.put("data", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @Path("/search")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByNombreAndTipo(HashMap<String, Object> map) {
        HashMap<String, Object> res = new HashMap<>();
        ProductoService ps = new ProductoService();
        try {
            String nombreProducto = map.get("NombreProducto") != null ? map.get("NombreProducto").toString() : null;
            TipoProducto tipoProducto = map.get("TipoProducto") != null ? TipoProducto.valueOf(map.get("TipoProducto").toString()) : null;

            LinkedList<Producto> resultados = ps.searchByNombreAndTipo(nombreProducto, tipoProducto);
            res.put("msg", "Ok");
            res.put("data", resultados.toArray());
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("msg", "Error");
            res.put("data", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

        @Path("/list/order/{attribute}/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNombreProducto(@PathParam("attribute") String attribute, @PathParam("type") Integer type) {
        HashMap map = new HashMap<>();
        ProductoService fs = new ProductoService();
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
}