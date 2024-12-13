package com.sistemaDeFarmacia.rest.controller.dao;

import java.util.function.ToIntBiFunction;

import com.sistemaDeFarmacia.rest.controller.dao.implement.AdapterDao;
import com.sistemaDeFarmacia.rest.controller.tda.list.LinkedList;
import com.sistemaDeFarmacia.rest.models.Factura;
import com.sistemaDeFarmacia.rest.models.enumerador.MetodoPago;

public class FacturaDao extends AdapterDao<Factura> {
    private Factura Factura;
    private LinkedList listAll;

    public FacturaDao() {
        super(Factura.class);
    }

    public Factura getFactura() {
        if (Factura == null) {
            Factura = new Factura();
        }
        return this.Factura;
    }

    public void setFactura(Factura Factura) {
        this.Factura = Factura;
    }
    
    public LinkedList getListAll() {
        if(listAll == null){
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1;
        Factura.setId(id);
        this.persist(this.Factura);
        this.listAll = listAll();
        return true;
    }

    public Boolean update() throws Exception {
        this.merge(getFactura(), getFactura().getId() - 1);
        this.listAll = listAll();
        return true;
    }

    public MetodoPago getMetodoPago(String tipo) {
        return MetodoPago.valueOf(tipo);
    }

    public MetodoPago[] getMetodosPago() {
        return MetodoPago.values();
    }

}