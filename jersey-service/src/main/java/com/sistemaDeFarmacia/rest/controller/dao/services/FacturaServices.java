package com.sistemaDeFarmacia.rest.controller.dao.services;

import com.sistemaDeFarmacia.rest.models.Factura;
import com.sistemaDeFarmacia.rest.controller.dao.FacturaDao;
import com.sistemaDeFarmacia.rest.controller.tda.list.LinkedList;
import com.sistemaDeFarmacia.rest.models.enumerador.MetodoPago;

public class FacturaServices {
    private FacturaDao obj;

    public LinkedList listAll() {
        return obj.getListAll();
    }

    public FacturaServices() {
        obj = new FacturaDao();
    }
    
    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public Factura getFactura() {
        return obj.getFactura();
    }

    public void setFactura(Factura Factura) {
        obj.setFactura(Factura);
    }
    
    public Factura get(Integer id) throws Exception {
        return obj.get(id);
    }

    public MetodoPago getMetodoPago(String tipo) {
        return obj.getMetodoPago(tipo);
    }

    public MetodoPago[] getMetodosPago() {
        return obj.getMetodosPago();
    }

    public LinkedList factura_object(Integer type, String atributo) throws Exception {
        return obj.listAll().order(atributo, type);
    }

}