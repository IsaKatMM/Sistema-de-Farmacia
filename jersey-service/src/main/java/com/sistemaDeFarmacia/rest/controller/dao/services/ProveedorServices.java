package com.sistemaDeFarmacia.rest.controller.dao.services;
import com.sistemaDeFarmacia.rest.controller.dao.ProveedorDao;
import com.sistemaDeFarmacia.rest.controller.tda.list.LinkedList;
import com.sistemaDeFarmacia.rest.models.Proveedor;

public class ProveedorServices {
    private ProveedorDao obj;

    public ProveedorServices() {
        obj = new ProveedorDao();
    }
    
    
    public Boolean save() throws Exception {
        return obj.save();
    }

    public LinkedList listAll() {
        return obj.getListAll();
    }

    public Proveedor getProveedor() {
        return obj.getProveedor();
    }

    public void setProveedor(Proveedor proveedor) {
        obj.setProveedor(proveedor);
    }

    public Proveedor get (Integer id) throws Exception {
        return obj.get(id);
    }

}