package com.example.controls.dao;

import com.example.controls.dao.implement.AdapterDao;
import com.example.controls.tda.list.LinkedList;
import com.example.models.Factura;
import com.example.models.enumerador.TipoIdentificacion;

public class FacturaDao extends AdapterDao<Factura> {
    private Factura factura;
    private LinkedList listAll;

    public FacturaDao() {
        super(Factura.class);
    }

    public Factura getFactura() {
        if (factura == null) {
            factura = new Factura();
        }
        return this.factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
    
    public LinkedList getListAll() {
        if(listAll == null){
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1;
        factura.setId(id);
        this.persist(this.factura);
        this.listAll = listAll();
        return true;
    }

    public Boolean update() throws Exception {
        this.merge(getFactura(), getFactura().getId() - 1);
        this.listAll = listAll();
        return true;
    }

}