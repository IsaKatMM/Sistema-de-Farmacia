package com.sistemaDeFarmacia.rest.controller.dao.implement;
import com.sistemaDeFarmacia.rest.models.DetalleCompra;

public class DetalleCompraDao extends com.sistemaDeFarmacia.rest.controller.dao.implement.AdapterDao<DetalleCompra> {
    private static DetalleCompraDao instance;

    public DetalleCompraDao() {
        super(DetalleCompra.class);
    }
}