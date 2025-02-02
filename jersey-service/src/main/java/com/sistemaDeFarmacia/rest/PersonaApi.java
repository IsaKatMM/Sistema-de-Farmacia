package com.sistemaDeFarmacia.rest;

import java.util.HashMap;

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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.sistemaDeFarmacia.rest.controller.dao.services.PersonaServices;
import com.sistemaDeFarmacia.rest.models.Persona;

@Path("person")
public class PersonaApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        HashMap map = new HashMap<>();
        PersonaServices ps = new PersonaServices();
        map.put("msg", "Ok");
        map.put("data", ps.listAll().toArray());
        if (ps.listAll().isEmpty()) {
            map.put("data", new Object[] {});
        }
        return Response.ok(map).build();
    }

    //
    @Path("/listType")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getType() {
        HashMap map = new HashMap<>();
        PersonaServices ps = new PersonaServices();
        map.put("msg", "Ok");
        // map.put("data", ps.getTipos());
        return Response.ok(map).build();
    }

    @Path("/get/{id}") // actualizar
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("id") Integer id) {
        HashMap map = new HashMap<>();
        PersonaServices ps = new PersonaServices();
        try {
            ps.setPersona(ps.get(id));
        } catch (Exception e) {
            // TODO: handle exception
        }
        map.put("msg", "Ok");
        map.put("data", ps.getPersona());
        if (ps.getPersona().getId() == null) {
            map.put("data", "No existe la persona con ese identificador");
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }

        return Response.ok(map).build();
    }

    //
    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap<String, Object> map) {
        HashMap res = new HashMap<>();
        Gson gson = new Gson();
        String jsonData = gson.toJson(map);
        System.out.println("****" + jsonData);

        try {
            PersonaServices ps = new PersonaServices();
            String cedula = (String) map.get("cedula");
            String telefono = (String) map.get("telefono");
            String correo = (String) map.get("correo");

            // Validación para verificar que la cédula no se repita
            if (ps.isCedulaDuplicada(cedula)) {
                res.put("msg", "Error");
                res.put("data", "La cédula ya está registrada.");
                return Response.status(Status.BAD_REQUEST).entity(res).build();
            }

            // Validación para el teléfono
            if (telefono.isEmpty() || !telefono.matches("\\d{10}")) {
                res.put("msg", "Error");
                res.put("data", "El teléfono es obligatorio y debe tener 10 dígitos.");
                return Response.status(Status.BAD_REQUEST).entity(res).build();
            }

            // Validación para el correo
            if (correo.isEmpty() || !correo.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                res.put("msg", "Error");
                res.put("data", "El correo es obligatorio y debe tener un formato válido.");
                return Response.status(Status.BAD_REQUEST).entity(res).build();
            }

            // Crear y asignar valores al objeto Persona
            Persona persona = new Persona();
            persona.setNombre((String) map.get("nombre"));
            persona.setApellido((String) map.get("apellido"));
            persona.setTelefono(telefono);
            persona.setCedula(cedula);
            persona.setDireccion((String) map.get("direccion"));
            persona.setCorreo(correo);

            // Asignar el objeto Persona al servicio y guardar
            ps.setPersona(persona);
            ps.save();

            res.put("msg", "OK");
            res.put("data", "Persona registrada correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            e.printStackTrace();
            res.put("msg", "Error");
            res.put("data", "Error en save: " + e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

}
