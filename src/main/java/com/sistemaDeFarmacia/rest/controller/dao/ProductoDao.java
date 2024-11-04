package com.sistemaDeFarmacia.rest.controller.dao;

import java.io.IOException;
import java.rmi.server.ExportException;

import com.google.gson.JsonSyntaxException;
import com.sistemaDeFarmacia.rest.controller.dao.implement.AdapterDao;
import com.sistemaDeFarmacia.rest.models.Producto;
import com.sistemaDeFarmacia.rest.models.enumerador.TipoProducto;
import com.sistemaDeFarmacia.rest.controller.tda.list.LinkedList;

public class ProductoDao extends AdapterDao<Producto> {
    private Producto producto;
    private LinkedList listAll;
    public ProductoDao() {
        super(Producto.class);
    }

    public Producto getProducto() {
        if (producto == null) {
            producto = new Producto();
        }
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public LinkedList getListAll() {
        if(listAll == null){
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize()+1;
        producto.setIdProducto(id);
        this.persist(this.producto);
        return true;
    }

    public boolean update() throws Exception{
        this.merge(getProducto(), getProducto().getIdProducto()-1);
        this.listAll = listAll();
        return true;
    }

    public TipoProducto getTipoProducto(String tipo){
        return TipoProducto.valueOf(tipo);
    }

    public TipoProducto[] getTipos(){
        return TipoProducto.values();
    }

/// METODOS PARA PRODUCTO SOLO SE LOS PONE AQUI**********************
}