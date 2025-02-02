package com.sistemaDeFarmacia.rest.controller.dao;

import java.io.IOException;
import java.rmi.server.ExportException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sistemaDeFarmacia.rest.controller.dao.implement.AdapterDao;
import com.sistemaDeFarmacia.rest.models.Producto;
import com.sistemaDeFarmacia.rest.models.enumerador.TipoProducto;
import com.sistemaDeFarmacia.rest.controller.tda.list.LinkedList;

public class ProductoDao extends AdapterDao<Producto> {
    private Producto producto;
    private LinkedList<Producto> listAll;

    private Gson g = new Gson();
    
    public ProductoDao(){
        super(Producto.class);
    }

    public Producto getProducto(){
        if(this.producto == null){
            this.producto = new Producto();
        }
        return this.producto;
    }

    public void setProducto(Producto producto){
        this.producto = producto;
    }

    public LinkedList<Producto> getListAll() throws Exception{
        if(listAll == null){
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception{
        Integer idProducto = getListAll().getSize()+1;
        producto.setIdProducto(idProducto);
        this.persist(this.producto);
        this.listAll = listAll();
        return true; 
    }

    public Boolean update() throws Exception {
        this.merge(getProducto(), getProducto().getIdProducto() -1);
        this.listAll = listAll();
        return true;
    }

    public LinkedList<Producto> order(Integer type_order, String atributo){
        LinkedList<Producto> listita = listAll();
        if (!listAll().isEmpty()) {
            Producto[] lista = (Producto[]) listita.toArray();
            listita.reset();
            for(int i = 1; i < lista.length; i++){
                Producto aux = lista[i];
                int j = i - 1;
                while (j>=0 && (verify(lista[j], aux, type_order, atributo))) {
                    lista[j + 1] = lista[j--];
                }
                lista[j + 1] = aux;
            }
            listita.toList(lista);
        }
        return listita;
    }

    private Boolean verify(Producto a, Producto b, Integer type_order, String atributo){
        if(type_order == 1){
            if (atributo.equalsIgnoreCase("nombreProducto")) {
                return a.getNombreProducto().compareTo(b.getNombreProducto()) > 0;
            }else if (atributo.equalsIgnoreCase("idProducto")) {
                return a.getIdProducto() > b.getIdProducto();
            }
        }else{
            if (atributo.equalsIgnoreCase("nombreProducto")) {
                return a.getNombreProducto().compareTo(b.getNombreProducto()) < 0;
            }else if (atributo.equalsIgnoreCase("idProducto")) {
                return a.getIdProducto() < b.getIdProducto();
            }
        }
        return false;
    }

    // Método de búsqueda por nombreProducto y tipoProducto
    public LinkedList<Producto> searchByNombreAndTipo(String nombreProducto, TipoProducto tipoProducto) throws Exception {
        LinkedList<Producto> result = new LinkedList<>();
        LinkedList<Producto> allProducts = getListAll();
    
        // Usar un bucle for tradicional
        for (int i = 0; i < allProducts.getSize(); i++) {
            Producto producto = allProducts.get(i); // Asume que tienes un método get(int index) en tu LinkedList
            if ((nombreProducto == null || producto.getNombreProducto().toLowerCase().contains(nombreProducto.toLowerCase())) &&
                (tipoProducto == null || producto.getTipoProducto() == tipoProducto)) {
                result.add(producto);
            }
        }
    
        return result;
    }
}