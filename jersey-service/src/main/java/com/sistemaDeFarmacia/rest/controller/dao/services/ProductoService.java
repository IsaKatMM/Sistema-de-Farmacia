package com.sistemaDeFarmacia.rest.controller.dao.services;

import com.sistemaDeFarmacia.rest.controller.dao.ProductoDao;
import com.sistemaDeFarmacia.rest.models.Producto;
import com.sistemaDeFarmacia.rest.models.enumerador.TipoProducto;
import com.sistemaDeFarmacia.rest.controller.tda.list.LinkedList;

public class ProductoService {
    private ProductoDao obj;

    public ProductoService() {
        obj = new ProductoDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public LinkedList<Producto> listAll() throws Exception {
        return obj.getListAll();
    }

    public Producto getProducto() {
        return obj.getProducto();
    }

    public void setProducto(Producto producto) {
        obj.setProducto(producto);
    }

    public Producto get(Integer id) throws Exception {
        return obj.get(id);
    }

    public LinkedList<Producto> order(Integer type_order, String atributo) {
        return obj.order(type_order, atributo);
    }

    public LinkedList<Producto> order_object(Integer type, String atributo) throws Exception {
        return obj.listAll().order(atributo, type);
    }

    // Método para buscar por nombre y tipo de producto
    public LinkedList<Producto> searchByNombreAndTipo(String nombreProducto, TipoProducto tipoProducto) throws Exception {
        return obj.searchByNombreAndTipo(nombreProducto, tipoProducto);
    }

    // Método para obtener los tipos de producto (enum)
    public TipoProducto[] getTipos() {
        return TipoProducto.values();
    }
}