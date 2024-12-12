package com.sistemaDeFarmacia.rest.controller.dao.services;

import com.sistemaDeFarmacia.rest.controller.dao.FacturaDao;
import com.sistemaDeFarmacia.rest.controller.tda.list.LinkedList;
import com.sistemaDeFarmacia.rest.models.Factura;

public class FacturaServices {
    private FacturaDao obj;

    public FacturaServices() {
        obj = new FacturaDao();
    }
    
    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public LinkedList<Factura> listAll() {
        return obj.getListAll();
    }

    public Factura getFactura() {
        return obj.getFactura();
    }

    public void setFactura(Factura factura) {
        obj.setFactura(factura);
    }
    
    public Factura get(Integer id) throws Exception {
        return obj.get(id);
    }
}