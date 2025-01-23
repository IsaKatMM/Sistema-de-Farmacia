package com.sistemaDeFarmacia.rest;

import java.util.HashMap;
import java.util.LinkedList;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import com.sistemaDeFarmacia.rest.controller.dao.ProveedorDao;
import com.sistemaDeFarmacia.rest.controller.dao.services.PersonaServices;
import com.sistemaDeFarmacia.rest.controller.dao.services.ProveedorServices;
import com.sistemaDeFarmacia.rest.models.Proveedor;
import com.sistemaDeFarmacia.rest.models.enumerador.TipoProducto;

@Path("provetor")
public class ProveedorApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProvetors() {
        HashMap map = new HashMap<>();
        ProveedorServices ps = new ProveedorServices();
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
        ProveedorServices ps = new ProveedorServices();
        map.put("msg", "Ok");
        map.put("data", ps.getTipos());
        return Response.ok(map).build();
    }

    @Path("/get/{id}") // actualizar
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProvetor(@PathParam("id") Integer id) {
        HashMap map = new HashMap<>();
        ProveedorServices ps = new ProveedorServices();
        try {
            ps.setProveedor(ps.get(id));
        } catch (Exception e) {
            // TODO: handle exception
        }
        map.put("msg", "Ok");
        map.put("data", ps.getProveedor());
        if (ps.getProveedor().getId() == null) {
            map.put("data", "No existe ele proveedor con ese identificador");
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }

        return Response.ok(map).build();
    }

    //
    /*
     * @Path("/save")
     * 
     * @POST
     * 
     * @Consumes(MediaType.APPLICATION_JSON)
     * 
     * @Produces(MediaType.APPLICATION_JSON)
     * public Response save(HashMap map) {
     * HashMap res = new HashMap<>();
     * Gson g = new Gson();
     * String a = g.toJson(map);
     * System.out.println("****" + a);
     * 
     * try {
     * ProveedorServices ps = new ProveedorServices();
     * 
     * ps.getProveedor().setNombre(map.get("nombre").toString());
     * ps.getProveedor().setApellido(map.get("apellido").toString());
     * 
     * // Validación del teléfono y nombre de empresa
     * String telefono = map.get("telefono").toString();
     * String nombreEmpresa = map.get("nombreEmpresa").toString();
     * if (!telefono.matches("\\d{10}")) {
     * throw new Exception("Número de teléfono inválido");
     * }
     * 
     * // Verificar si el teléfono o el nombre de la empresa ya existen
     * if (ps.telefonoEmpresaExiste(telefono, nombreEmpresa)) {
     * throw new
     * Exception("El número de teléfono o el nombre de la empresa ya están registrados"
     * );
     * }
     * 
     * ps.getProveedor().setTelefono(telefono);
     * ps.getProveedor().setNombreEmpresa(nombreEmpresa);
     * 
     * ps.getProveedor().setTipoProductos(TipoProducto.valueOf(map.get(
     * "tipoProductos").toString()));
     * ps.getProveedor().setPedidos(map.get("pedidos").toString());
     * ps.getProveedor().setProductosDisponibles(Integer.parseInt(map.get(
     * "productosDisponibles").toString()));
     * 
     * ps.save();
     * res.put("msg", "OK");
     * res.put("data", "Proveedor registrado correctamente");
     * return Response.ok(res).build();
     * 
     * } catch (Exception e) {
     * System.out.println("Error en sav en data" + e.toString());
     * res.put("msg", "Error");
     * res.put("data", e.toString());
     * return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
     * }
     * }
     */
    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap map) {
        HashMap res = new HashMap<>();
        Gson g = new Gson();
        String a = g.toJson(map);
        System.out.println("****" + a);

        try {
            ProveedorServices ps = new ProveedorServices();

            if (map.get("nombre") != null) {
                System.out.println("Nombre: " + map.get("nombre"));
                ps.getProveedor().setNombre(map.get("nombre").toString());
            } else {
                System.out.println("Nombre es nulo");
            }

            if (map.get("apellido") != null) {
                System.out.println("Apellido: " + map.get("apellido"));
                ps.getProveedor().setApellido(map.get("apellido").toString());
            } else {
                System.out.println("Apellido es nulo");
            }

            if (map.get("telefono") != null) {
                System.out.println("Telefono: " + map.get("telefono"));
                ps.getProveedor().setTelefono(map.get("telefono").toString());
            } else {
                System.out.println("Telefono es nulo");
            }

            if (map.get("nombreEmpresa") != null) {
                System.out.println("NombreEmpresa: " + map.get("nombreEmpresa"));
                ps.getProveedor().setNombreEmpresa(map.get("nombreEmpresa").toString());
            } else {
                System.out.println("NombreEmpresa es nulo");
            }

            if (map.get("tipoProductos") != null) {
                System.out.println("TipoProducto: " + map.get("tipoProductos"));
                ps.getProveedor().setTipoProductos(TipoProducto.valueOf(map.get("tipoProductos").toString()));
            } else {
                System.out.println("TipoProducto es nulo");
            }

            if (map.get("pedidos") != null) {
                System.out.println("Pedidos: " + map.get("pedidos"));
                ps.getProveedor().setPedidos(map.get("pedidos").toString());
            } else {
                System.out.println("Pedidos es nulo");
            }

            if (map.get("productosDisponibles") != null) {
                System.out.println("ProductosDisponibles: " + map.get("productosDisponibles"));
                ps.getProveedor().setProductosDisponibles(Integer.parseInt(map.get("productosDisponibles").toString()));
            } else {
                System.out.println("ProductosDisponibles es nulo");
            }

            ps.save();
            res.put("msg", "OK");
            res.put("data", "Proveedor registrado correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error en sav en data" + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(HashMap map) {
        HashMap res = new HashMap<>();

        try {
            ProveedorServices ps = new ProveedorServices();
            if (map.get("id") != null) {
                int id = Integer.parseInt(map.get("id").toString());
                System.out.println("ID: " + id);
                Proveedor proveedor = ps.get(id);
                if (proveedor == null) {
                    throw new Exception("Proveedor con ID " + id + " no encontrado.");
                }
                ps.setProveedor(proveedor);
            } else {
                throw new Exception("El campo 'id' es obligatorio para la actualización.");
            }

            if (map.get("nombre") != null) {
                System.out.println("Nombre: " + map.get("nombre"));
                ps.getProveedor().setNombre(map.get("nombre").toString());
            } else {
                System.out.println("Nombre es nulo");
            }

            if (map.get("apellido") != null) {
                System.out.println("Apellido: " + map.get("apellido"));
                ps.getProveedor().setApellido(map.get("apellido").toString());
            } else {
                System.out.println("Apellido es nulo");
            }

            if (map.get("telefono") != null) {
                System.out.println("Telefono: " + map.get("telefono"));
                ps.getProveedor().setTelefono(map.get("telefono").toString());
            } else {
                System.out.println("Telefono es nulo");
            }

            if (map.get("nombreEmpresa") != null) {
                System.out.println("NombreEmpresa: " + map.get("nombreEmpresa"));
                ps.getProveedor().setNombreEmpresa(map.get("nombreEmpresa").toString());
            } else {
                System.out.println("NombreEmpresa es nulo");
            }

            if (map.get("tipoProducto") != null) {
                System.out.println("TipoProducto: " + map.get("tipoProducto"));
                ps.getProveedor().setTipoProductos(TipoProducto.valueOf(map.get("tipoProducto").toString()));
            } else {
                System.out.println("TipoProducto es nulo");
            }

            if (map.get("pedidos") != null) {
                System.out.println("Pedidos: " + map.get("pedidos"));
                ps.getProveedor().setPedidos(map.get("pedidos").toString());
            } else {
                System.out.println("Pedidos es nulo");
            }

            if (map.get("productosDisponibles") != null) {
                System.out.println("ProductosDisponibles: " + map.get("productosDisponibles"));
                ps.getProveedor().setProductosDisponibles(Integer.parseInt(map.get("productosDisponibles").toString()));
            } else {
                System.out.println("ProductosDisponibles es nulo");
            }

            ps.update();
            res.put("msg", "OK");
            res.put("data", "Proveedor registrado correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error en sav en data" + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    // eliminar

    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProveedor(@PathParam("id") Integer id) {
        try {
            // Llamar al método delete del ProveedorDao
            boolean result = proveedorDao.delete(id);

            // Verificar si la eliminación fue exitosa
            if (result) {
                return Response.status(Response.Status.OK) // HTTP 200
                        .entity("{\"message\": \"Proveedor eliminado exitosamente\"}")
                        .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST) // HTTP 400
                        .entity("{\"message\": \"No se pudo eliminar el proveedor\"}")
                        .build();
            }
        } catch (Exception e) {
            // Si ocurre un error, devolver un mensaje de error
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR) // HTTP 500
                    .entity("{\"message\": \"Error al eliminar el proveedor: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    /// search
    @Path("/list/search/{texto}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProvetorsLastName(@PathParam("texto") String texto) {
        HashMap map = new HashMap<>();
        ProveedorServices ps = new ProveedorServices();
        map.put("msg", "Ok");
        com.sistemaDeFarmacia.rest.controller.tda.list.LinkedList<Proveedor> lsita = ps.buscar_apellidos(texto);
        map.put("data", lsita.toArray());
        if (lsita.isEmpty()) {
            map.put("data", new Object[] {});
        }
        return Response.ok(map).build();
    }

    @Path("/list/search/telefono/{texto}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProvetorsTelef(@PathParam("texto") String texto) {
        HashMap map = new HashMap<>();
        ProveedorServices ps = new ProveedorServices();
        map.put("msg", "Ok");
        try {
            System.out.println("Buscando proveedor con teléfono: " + texto);
            Proveedor proveedor = (Proveedor) ps.buscar_telefono(texto);
            System.out.println(
                    "Proveedor encontrado: " + (proveedor != null ? proveedor.getTelefono() : "No encontrado"));
            if (proveedor != null) {
                ps.setProveedor(proveedor);
                map.put("data", proveedor);
                return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();
            } else {
                map.put("data", "No existe el proveedor con ese telefono");
                return Response.status(Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").entity(map)
                        .build();
            }
        } catch (Exception e) {
            System.out.println("Error en getProvetorsTelef: " + e.getMessage());
            e.printStackTrace(); // Esto imprimirá la traza completa del error en la consola
            map.put("msg", "Error");
            map.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*").entity(map)
                    .build();
        }
    }

    // ordenar
   
    @Path("/list/order/{attribute}/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonsLastName(@PathParam("attribute") String attribute, @PathParam("type") Integer type) {
        HashMap map = new HashMap<>();
        ProveedorServices ps = new ProveedorServices();
        map.put("msg", "Ok");
        try {
            System.out.println("Ordenando proveedores por atributo: " + attribute + " y tipo: " + type);
            com.sistemaDeFarmacia.rest.controller.tda.list.LinkedList<Proveedor> lista = ps.order(type, attribute);
            System.out.println("Proveedores ordenados: " + lista.getSize() + " elementos");
            map.put("data", lista.toArray());
            if (lista.isEmpty()) {
                map.put("data", new Object[] {});
            }
        } catch (Exception e) {
            e.printStackTrace(); // Para asegurarte de que se vea cualquier error
            map.put("msg", "Error");
            map.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
        return Response.ok(map).build();
    }

}
