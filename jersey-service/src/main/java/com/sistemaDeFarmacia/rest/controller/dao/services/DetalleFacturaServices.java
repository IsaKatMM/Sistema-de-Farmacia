package com.sistemaDeFarmacia.rest.controller.dao.services;

import com.sistemaDeFarmacia.rest.controller.dao.DetalleFacturaDao;
import com.sistemaDeFarmacia.rest.controller.tda.list.LinkedList;
import com.sistemaDeFarmacia.rest.models.DetalleFactura;

public class DetalleFacturaServices {
    private DetalleFacturaDao obj;

    public DetalleFacturaServices() {
        obj = new DetalleFacturaDao();
    }
    
    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public LinkedList<DetalleFactura> listAll() {
        return obj.getListAll();
    }

    public DetalleFactura getDetalleFactura() {
        return obj.getDetalleFactura();
    }

    public void setDetalleFactura(DetalleFactura detalleFactura) {
        obj.setDetalleFactura(detalleFactura);
    }
    
    public DetalleFactura get(Integer id) throws Exception {
        return obj.get(id);
    }
}