package com.sistemaDeFarmacia.rest.controller.dao;

import java.util.function.ToIntBiFunction;
import com.sistemaDeFarmacia.rest.controller.dao.implement.AdapterDao;
import com.sistemaDeFarmacia.rest.controller.tda.list.LinkedList;
import com.sistemaDeFarmacia.rest.models.Persona;
import com.sistemaDeFarmacia.rest.models.Proveedor;
import com.sistemaDeFarmacia.rest.models.enumerador.TipoProducto;
import com.sistemaDeFarmacia.rest.controller.tda.list.LinkedList;

public class ProveedorDao extends AdapterDao<Proveedor> {
    private Proveedor proveedor;
    private LinkedList listAll;

    public ProveedorDao() {
        super(Proveedor.class);
    }

    public Proveedor getProveedor() {
        if (proveedor == null) {
            proveedor = new Proveedor();
        }
        return this.proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public LinkedList<Proveedor> getListAll() {
        if (this.listAll == null) {
            this.listAll = listAll();
        }
        return this.listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1;
        proveedor.setId(id);
        this.persist(this.proveedor);
        this.listAll = listAll();
        return true;
    }

    public Boolean update() throws Exception {

        this.merge(getProveedor(), getProveedor().getId() - 1);
        this.listAll = listAll();
        return true;
    }
    // metodos de provedor

    public TipoProducto getTipoProductos(String tipo) {
        return TipoProducto.valueOf(tipo);
    }

    public TipoProducto[] getTipos() {
        return TipoProducto.values();
    }

    // Método delete 
    public Boolean delete(Integer id) throws Exception {
        for (int i = 0; i < getListAll().getSize(); i++) {
            Proveedor pro = getListAll().get(i);
            if (pro.getId().equals(id)) {
                getListAll().delete(i);
                return true;
            }
        }
        throw new Exception("Proveedor no encontrado con ID: " + id);
    }
    ///////////////////////////////////////////////////////////////////////////////////////
    public LinkedList<Proveedor> order(Integer type_order, String atributo) {
        LinkedList<Proveedor> listita = listAll();
        if (!listita.isEmpty()) {
            Proveedor[] lista = listita.toArray();
            listita.reset();
            for (int i = 1; i < lista.length; i++) {
                Proveedor aux = lista[i]; // valor a ordenar
                int j = i - 1; // índice anterior
                while (j >= 0 && verify(lista[j], aux, type_order, atributo)) {
                    lista[j + 1] = lista[j]; // desplaza elementos hacia la derecha
                    j--;
                }
                lista[j + 1] = aux; // insertar el valor en su posición correcta
            }
            listita.toList(lista);
        }
        return listita;
    }

    public Boolean verify(Proveedor a, Proveedor b, Integer type_order, String atributo) {
        if (type_order == 1) { // Orden ascendente
            if (atributo.equalsIgnoreCase("apellido")) {
                return a.getApellido().compareTo(b.getApellido()) > 0;
            } else if (atributo.equalsIgnoreCase("nombre")) { // Corregido de "nombres" a "nombre"
                return a.getNombre().compareTo(b.getNombre()) > 0;
            } else if (atributo.equalsIgnoreCase("id")) {
                return a.getId() > b.getId(); // se lo pone así por ser numérico
            }
        } else { // Orden descendente
            if (atributo.equalsIgnoreCase("apellido")) {
                return a.getApellido().compareTo(b.getApellido()) < 0;
            } else if (atributo.equalsIgnoreCase("nombre")) { // Corregido de "nombres" a "nombre"
                return a.getNombre().compareTo(b.getNombre()) < 0;
            } else if (atributo.equalsIgnoreCase("id")) {
                return a.getId() < b.getId(); // se lo pone así por ser numérico
            }
        }
        return false;
    }

    // busqueda secuencial

    public LinkedList<Proveedor> buscar_apellidos(String texto) {
        LinkedList<Proveedor> lista = new LinkedList<>();
        LinkedList<Proveedor> listita = listAll();
        if (!listita.isEmpty()) {
            Proveedor[] aux = listita.toArray();
            for (int i = 0; i < aux.length; i++) {
                if (aux[i].getApellido().toLowerCase().startsWith(texto.toLowerCase())) { // startsWith me compara si la
                                                                                          // letra que estoy buscando
                                                                                          // esta en la primera posicion
                    lista.add(aux[i]);
                }
            }
        }
        return lista;
    }

    // busquedas atomicas: devuelve un solo objeto, cuando son varias busquedas
    // devuelve un linkedlist
    /*
     * public Persona buscar_telefono(String texto){
     * Proveedor proveedor = null;
     * LinkedList <Proveedor> listita = listAll();
     * if (!listita.isEmpty()){
     * Proveedor[] aux = listita.toArray();
     * for (int i = 0; i < aux.length; i++){
     * if (aux [i].getTelefono().equals(texto)){
     * proveedor = aux[i];
     * break;
     * }
     * }
     * }
     * return proveedor;
     * }
     */
    public Proveedor buscar_cedula(String texto) {
        Proveedor proveedor = null;
        LinkedList<Proveedor> listita = listAll();
        System.out.println("Lista de proveedores obtenida: " + listita.getSize() + " elementos");
        if (!listita.isEmpty()) {
            Proveedor[] aux = listita.toArray();
            for (Proveedor p : aux) {
                System.out.println("Revisando cedula: " + p.getCedula());
                if (p.getCedula().equals(texto)) {
                    proveedor = p;
                    break;
                }
            }
        }
        System.out.println("Proveedor encontrado: " + (proveedor != null ? proveedor.getCedula() : "No encontrado"));
        return proveedor;
    }

    public Proveedor buscar_telefono(String texto) {
        Proveedor proveedor = null;
        LinkedList<Proveedor> listita = listAll();
        System.out.println("Lista de proveedores obtenida: " + listita.getSize() + " elementos");
        if (!listita.isEmpty()) {
            Proveedor[] aux = listita.toArray();
            for (Proveedor p : aux) {
                System.out.println("Revisando teléfono: " + p.getTelefono());
                if (p.getTelefono().equals(texto)) {
                    proveedor = p;
                    break;
                }
            }
        }
        System.out.println("Proveedor encontrado: " + (proveedor != null ? proveedor.getTelefono() : "No encontrado"));
        return proveedor;
    }

}
