package com.sistemaDeFarmacia.rest.controller.dao.services;

import org.apache.commons.math3.stat.descriptive.summary.Product;

import com.sistemaDeFarmacia.rest.controller.dao.ProductoDao;
import com.sistemaDeFarmacia.rest.controller.dao.implement.AdapterDao;
import com.sistemaDeFarmacia.rest.models.Producto;
import com.sistemaDeFarmacia.rest.controller.tda.list.LinkedList;

public class ProductoService {
    private ProductoDao obj;
    
    public ProductoService(){
        obj = new ProductoDao();
    }

    public Boolean save() throws Exception{
        return obj.save();
    }

    public Boolean update() throws Exception{
        return obj.update();
    }

    public LinkedList listAll(){
        return obj.getListAll();
    }

    public Producto geProducto(){
        return obj.getProducto();
    }

    public void setProducto(Producto producto){
        obj.setProducto(producto);
    }


}
