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
        if (ps.listAll().isEmpty()){
            map.put("data", new Object[]{});
        }
        return Response.ok(map).build();
    }
    

    //
    @Path("/listType")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getType(){
        HashMap map = new HashMap<>();
        PersonaServices ps = new PersonaServices();
        map.put("msg", "Ok");
        //map.put("data", ps.getTipos());
        return Response.ok(map).build();
    }
    @Path("/get/{id}")//actualizar
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
    @Path ("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap map){
        HashMap res = new HashMap<>();
        Gson g = new Gson();
        String a = g.toJson(map);
        System.out.println("****"+a);

        try{
         //TODO
        //VALIDATION (poner en la practica y trabajo final)-- como que la cedula no se repita y asi
        //para acceder a los datos del HashMap es map.get --BAD REQUEST
        PersonaServices ps= new PersonaServices();
        ps.getPersona().setNombre(map.get("nombres").toString());
        ps.getPersona().setApellido(map.get("apellidos").toString());
        ps.getPersona().setTelefono(map.get("telefono").toString());
        ps.getPersona().setCedula(map.get("cedula").toString());
        ps.getPersona().setDireccion(map.get("direccion").toString());
        ps.getPersona().setCorreo(map.get("correo").toString());
        ps.save();
                res.put("msg","OK");
                res.put("data","Persona registrada correctamente");
                return Response.ok(res).build();
 
        } catch (Exception e){
            System.out.println("Error en sav en data"+e.toString());
            res.put("msg","Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
            //TODO
        }
    }

}
