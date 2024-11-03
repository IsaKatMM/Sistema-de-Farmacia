package com.sistemaFarmacia.rest.controller.dao;

import java.io.IOException;
import java.rmi.server.ExportException;

import com.google.gson.JsonSyntaxException;
import com.sistemaFarmacia.rest.controller.dao.implement.AdapterDao;
import com.sistemaFarmacia.rest.models.Producto;


public class ProductoDao extends AdapterDao<Producto>{
    private Producto producto;
    private Producto[] listAll;

    public ProductoDao(){
        super(Producto.class);
    }

    public Producto getProducto(){
        if (this.producto == null) {
            this.producto = new Producto();
        }
        return this.producto;
    }

    public void setProducto(Producto producto){
        this.producto = producto;
    }

    public LinkedList<Producto> getListAll(){
        if (listAll == null) {
            listAll = new LinkedList<>();
        }
        return this.listAll;
    }

    public boolean save() throws Exception{
        Integer id = listAll().getSize + 1;
        producto.setIdProducto(id);
        this.persist(this.producto);
        this.listAll = listAll;
        return false;
    }

    public Boolean update() throws Exception{
        this.merge(getProducto(), getProducto().getIdProducto()-1);
        this.listAll = listAll();
        return true;
    }


}
