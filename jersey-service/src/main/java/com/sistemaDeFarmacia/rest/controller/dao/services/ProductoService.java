package com.sistemaDeFarmacia.rest.controller.dao.services;
import com.sistemaDeFarmacia.rest.controller.dao.ProductoDao;
import com.sistemaDeFarmacia.rest.controller.dao.implement.AdapterDao;
import com.sistemaDeFarmacia.rest.models.Producto;
import com.sistemaDeFarmacia.rest.models.enumerador.TipoProducto;
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

    public Producto getProducto(){
        return obj.getProducto();
    }

    public void setProducto(Producto producto){
        obj.setProducto(producto);
    }

    public TipoProducto geTipoProducto(String tipo){
        return obj.getTipoProducto(tipo);
    }

    public TipoProducto[] getTipos(){
        return obj.getTipos();
    }

    public Producto get(Integer idProducto) throws Exception{
        return obj.get(idProducto);
    }

}
