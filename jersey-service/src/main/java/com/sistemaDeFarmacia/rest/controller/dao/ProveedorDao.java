package com.sistemaDeFarmacia.rest.controller.dao;

import java.util.function.ToIntBiFunction;
import com.sistemaDeFarmacia.rest.controller.dao.implement.AdapterDao;
import com.sistemaDeFarmacia.rest.controller.tda.list.LinkedList;
import com.sistemaDeFarmacia.rest.models.Proveedor;
import com.sistemaDeFarmacia.rest.models.enumerador.TipoProducto;


public class ProveedorDao extends AdapterDao<Proveedor> {
    private Proveedor proveedor;
    private LinkedList listAll;
    public ProveedorDao() {
        super(Proveedor.class);
    }

    public Proveedor getProveedor() {
        if (proveedor == null) {
            proveedor = new Proveedor();
        }
        return this.proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor= proveedor;
    }
    
    public LinkedList getListAll() {
        if(listAll == null){
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize()+1;
        proveedor.setId(id);
        this.persist(this.proveedor);
        this.listAll=listAll();
        return true;
    }

    public Boolean update() throws Exception {
        
        this.merge(getProveedor(), getProveedor().getId() - 1);
        this.listAll=listAll();
        return true;
    }
   //metodos de provedor 

   public TipoProducto getTipoProductos(String tipo) {
       return TipoProducto.valueOf(tipo);
   }

   public TipoProducto[] getTipos() {
       return TipoProducto.values();
   }

  


}
