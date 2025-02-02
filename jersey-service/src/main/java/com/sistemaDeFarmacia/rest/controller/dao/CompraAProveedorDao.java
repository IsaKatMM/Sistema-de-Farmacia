package com.sistemaDeFarmacia.rest.controller.dao;

import com.sistemaDeFarmacia.rest.controller.dao.implement.AdapterDao;
import com.sistemaDeFarmacia.rest.controller.tda.list.LinkedList;
import com.sistemaDeFarmacia.rest.models.CompraAProveedor;

public class CompraAProveedorDao extends AdapterDao{

    private CompraAProveedor compraAProveedor;
    private LinkedList<CompraAProveedor> listAll;

    public CompraAProveedorDao() {
        super(CompraAProveedor.class);
    }

    public void setCompraAProveedor(CompraAProveedor compraAProveedor) {
        this.compraAProveedor = compraAProveedor;
    }

    public CompraAProveedor getCompraAProveedor() {
        if(this.compraAProveedor == null) {
            this.compraAProveedor = new CompraAProveedor();
        }
        return this.compraAProveedor;
    }

    public LinkedList<CompraAProveedor> getListAll() {
        if(listAll == null){
            this.listAll = listAll();
        }
        return this.listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1;
        compraAProveedor.setId(id);
        this.persist(this.compraAProveedor);
        this.listAll = listAll();
        return true;
    }

    public Boolean update() throws Exception {
        Integer index = getCompraAProveedor().getId() - 1;
    
        if (index < 0 || index >= getListAll().getSize()) {
            throw new Exception("Índice de CompraAProveedor inválido");
        }
    
        this.merge(getCompraAProveedor(), index);
        this.listAll = listAll();
    
        return true;
    }
    
    public void deleteCompraAProveedor(Integer id) throws Exception {
        
        if (listAll == null) {
            this.listAll = listAll(); 
        }
    
        for (int i = 0; i < listAll.getSize(); i++) {
            CompraAProveedor compraAProveedor = listAll.get(i); 
            if (compraAProveedor.getId().equals(id)) {
                listAll.delete(i);
                return; 
            }
        }
    
      
        throw new Exception("CompraAProveedor no encontrada");
    }
    
}
