package com.sistemaDeFarmacia.rest.controller.dao.services;
import com.sistemaDeFarmacia.rest.controller.dao.LoteDao;
import com.sistemaDeFarmacia.rest.controller.dao.ProductoDao;
import com.sistemaDeFarmacia.rest.controller.tda.list.LinkedList;
import com.sistemaDeFarmacia.rest.models.Lote;
import com.sistemaDeFarmacia.rest.models.Producto;

public class LoteServices {
    private LoteDao obj;
    private ProductoDao productoDao;
    public LoteServices(){
        obj = new LoteDao();
        productoDao = new ProductoDao();
    }

    // Método para guardar un lote con su producto asociado
    public Boolean saveWithProduct(String nombreProducto) throws Exception {
        return obj.saveWithProduct(nombreProducto);
    }


    public Boolean update() throws Exception{
        return obj.update();
    }

    public LinkedList<Lote> listAll() throws Exception{
        return obj.getListAll();
    }

    public Lote getLote(){
        return obj.getLote();
    }

    public void setLote(Lote Lote){
        obj.setLote(Lote);
    }

    public Lote get(Integer id) throws Exception{
        return obj.get(id);
    }

    public LinkedList order(Integer type_order, String atributo) {
        return obj.order(type_order, atributo);
    }
    public LinkedList order_object(Integer type, String atributo) throws Exception {
        return obj.listAll().order(atributo, type);
    }

    


    // Método para buscar lotes por producto
    public LinkedList<Lote> getLotesByProducto(String nombreProducto) throws Exception {
        return obj.searchByProducto(nombreProducto);
    }


}