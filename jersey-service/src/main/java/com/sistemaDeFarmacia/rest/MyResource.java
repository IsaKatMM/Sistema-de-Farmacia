
package com.sistemaDeFarmacia.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.sistemaDeFarmacia.rest.controller.dao.PersonaDao;
import java.util.HashMap;
import javax.ws.rs.core.Response;
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
    public String getIt() {
        HashMap mapa = new HashMap<>();
        PersonaDao pd = new PersonaDao();
        String aux = "";
        try {
            pd.getPersona().setNombre("Juan");
            pd.getPersona().setApellido("Perez");
            pd.getPersona().setTelefono("0999999999");
            pd.getPersona().setCedula("9999999999");
            pd.getPersona().setDireccion("Quito");
            pd.getPersona().setCorreo("correofalso@gmail.com");
            pd.save();
            aux = "La lista esta vasia"+pd.listAll().isEmpty();
        } catch (Exception e) {
            System.out.println("Error"+e);
        }
        mapa.put("msg","Ok");
        mapa.put("data", "test"+aux);
        return Response.ok(mapa).build().toString();
    }
}