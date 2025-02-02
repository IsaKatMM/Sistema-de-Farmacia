package com.sistemaDeFarmacia.rest.controller.dao.services;

import com.sistemaDeFarmacia.rest.controller.dao.CompraAProveedorDao;
import com.sistemaDeFarmacia.rest.controller.tda.list.LinkedList;
import com.sistemaDeFarmacia.rest.models.CompraAProveedor;

public class CompraAProveedorServices {
     private CompraAProveedorDao obj;

    public CompraAProveedorServices() {
        this.obj = new CompraAProveedorDao();
    }

    public Boolean save() throws Exception {
        return this.obj.save();
    }

    public LinkedList<CompraAProveedor> listAll() {
        return this.obj.listAll();
    }

    public CompraAProveedor getCompraAProveedor() {
        return this.obj.getCompraAProveedor();
    }

    public void setCompraAProveedor(CompraAProveedor compraAProveedor) {
        this.obj.setCompraAProveedor(compraAProveedor);
    }

    public CompraAProveedorDao getCompraAProveedorDao() {
        return this.obj;
    }

    public CompraAProveedor get(Integer id) throws Exception {
        return (CompraAProveedor) obj.get(id);
    }

    public Boolean update() throws Exception {
        return obj.update();
    }
}
