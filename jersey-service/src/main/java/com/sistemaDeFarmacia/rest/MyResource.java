
package com.sistemaDeFarmacia.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import javax.ws.rs.core.Response;

import com.sistemaDeFarmacia.rest.controller.dao.PersonaDao;
import com.sistemaDeFarmacia.rest.controller.dao.services.PersonaServices;
import com.sistemaDeFarmacia.rest.controller.tda.list.LinkedList;
import com.sistemaDeFarmacia.rest.controller.tda.list.Exception.ListEmptyException;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt(){
        HashMap mapa = new HashMap();
        PersonaServices pd = new PersonaServices();
        String aux = "";
        try {
            pd.getPersona().setApellido("Morocho");
            pd.getPersona().setNombre("Isabel");
            pd.save();
            aux ="La lista esta vacia"+ pd.listAll().isEmpty();
        }catch (Exception e) {
            
            System.out.println("Error: "+e);
            // TODO: handle exception

        } 
        mapa.put ("msg", "OK");
        mapa.put ("data", "test");
        return Response.ok(mapa).build();
    }       
}
        
