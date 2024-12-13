package com.sistemaDeFarmacia.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.sistemaDeFarmacia.rest.controller.tda.list.LinkedList;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import com.sistemaDeFarmacia.rest.controller.dao.services.FacturaServices;
import com.sistemaDeFarmacia.rest.controller.dao.services.PersonaServices;

@Path("sell")
public class FacturaApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        HashMap map = new HashMap<>();
        FacturaServices ps = new FacturaServices();
        map.put("msg", "OK");
        map.put("data", ps.listAll().toArray());
        if (ps.listAll().isEmpty()) {
            map.put("data", new Object[] {});
        }

        return Response.ok(map).build();
    }

    /*
     * @Path("/list/search/{texto}")
     * 
     * @GET
     * 
     * @Produces(MediaType.APPLICATION_JSON)
     * public Response getName(@PathParam("texto") String texto) {
     * HashMap map = new HashMap<>();
     * FacturaServices ps = new FacturaServices();
     * map.put("msg", "OK");
     * LinkedList lsita = ps.buscar_apellidos(texto);
     * map.put("data", lsita.toArray());
     * if (lsita.isEmpty()) {
     * map.put("data", new Object[] {});
     * }
     * 
     * return Response.ok(map).build();
     * }
     */
    @Path("/list/Factura/{attribute}/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFactura(@PathParam("attribute") String attribute, @PathParam("type") Integer type) {
        HashMap map = new HashMap<>();
        FacturaServices ps = new FacturaServices();
        map.put("msg", "OK");
        // pd.Factura_object(LinkedList.ASC, "apellidos")
        try {
            LinkedList lsita = ps.factura_object(type, attribute);
            map.put("data", lsita.toArray());
            if (lsita.isEmpty()) {
                map.put("data", new Object[] {});
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return Response.ok(map).build();
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("id") Integer id) {
        HashMap map = new HashMap<>();
        FacturaServices ps = new FacturaServices();
        try {
            ps.setFactura(ps.get(id));
        } catch (Exception e) {
            // TODO: handle exception
        }
        map.put("msg", "OK");
        map.put("data", ps.getFactura());
        if (ps.getFactura().getId() == null) {
            map.put("data", "No existe la Factura con ese identificador");
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }
        return Response.ok(map).build();
    }

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap map) {
        // TODO
        // VALIDATION ---- BAD REQUEST

        HashMap res = new HashMap<>();
        Gson g = new Gson();
        String a = g.toJson(map);
        System.out.println("******* " + a);
        try {

            /*
             * ObjectMapper mapper = new ObjectMapper();
             * JsonNode jsonNodeMap = mapper.convertValue(map, JsonNode.class);
             * 
             * JsonSchemaFactory factory =
             * JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
             * JsonSchema schema = factory.getSchema(new
             * FileInputStream("media/validation/person_v.json"));
             * System.out.println(schema.toString());
             * java.util.Set<ValidationMessage> errors = schema.validate(jsonNodeMap);
             * if (errors.isEmpty()) {
             */
            if (map.get("person") != null && map.get("details") != null) {
                PersonaServices personaServices = new PersonaServices();
                personaServices.setPersona(personaServices.get(Integer.parseInt(map.get("person").toString())));
                if (personaServices.getPersona().getId() != null) {
                    FacturaServices ps = new FacturaServices();
                    ps.getFactura().setFechaEmision(new Date());
                    ps.getFactura().setId_persona(personaServices.getPersona().getId());
                    ps.getFactura().setMetodoPago(ps.getMetodoPago(map.get("metodoPago").toString()));
                    ps.getFactura().setObservaciones(map.get("observaciones").toString());
                    ps.getFactura().setSubTotal(Float.parseFloat(map.get("subtotal").toString()));
                    ps.getFactura().setIVA(Float.parseFloat(map.get("iva").toString()));
                    ps.getFactura().setTotal_USD(Float.parseFloat(map.get("total").toString()));
                    ps.save();
                    
                    res.put("msg", "OK");
                    res.put("data", "Factura registrada correctamente");
                    return Response.ok(res).build();
                } else {
                    res.put("msg", "Error");
                    res.put("data", "La persona o la marca no existen");
                    return Response.status(Status.BAD_REQUEST).entity(res).build();
                }

            } else {
                res.put("msg", "Error");
                res.put("data", "Faltan datos");
                return Response.status(Status.BAD_REQUEST).entity(res).build();
            }

            /*
             * } else {
             * res.put("msg", "Error");
             * // res.put("msg", "ERROR");
             * res.put("data", errors.toArray());
             * return Response.status(Status.BAD_REQUEST).entity(res).build();
             * }
             */

        } catch (Exception e) {
            System.out.println("Error en save data " + e.toString());
            res.put("msg", "Error");
            // res.put("msg", "ERROR");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
            // TODO: handle exception
        }

    }

}