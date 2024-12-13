package com.sistemaDeFarmacia.rest.controller.dao;

import com.sistemaDeFarmacia.rest.controller.dao.implement.AdapterDao;
import com.sistemaDeFarmacia.rest.controller.tda.list.LinkedList;
import com.sistemaDeFarmacia.rest.models.DetalleFactura;
import com.sistemaDeFarmacia.rest.models.Persona;

public class DetalleFacturaDao extends AdapterDao<DetalleFactura> {
    private DetalleFactura detalleFactura;
    private LinkedList listAll;

    public DetalleFacturaDao() {
        super(DetalleFactura.class);
    }

    public DetalleFactura getDetalleFactura() {
        if (detalleFactura == null) {
            detalleFactura = new DetalleFactura();
        }
        return this.detalleFactura;
    }

    public void setDetalleFactura(DetalleFactura detalleFactura) {
        this.detalleFactura = detalleFactura;
    }
    
    public LinkedList getListAll() {
        if(listAll == null){
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1;
        detalleFactura.setId(id);
        this.persist(this.detalleFactura);
        this.listAll = listAll();
        return true;
    }

    public Boolean update() throws Exception {
        this.merge(getDetalleFactura(), getDetalleFactura().getId() - 1);
        this.listAll = listAll();
        return true;
    }

}