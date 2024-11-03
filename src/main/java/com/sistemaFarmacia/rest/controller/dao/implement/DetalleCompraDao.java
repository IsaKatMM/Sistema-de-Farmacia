package com.sistemaFarmacia.rest.controller.dao.implement;

import com.sistemaFarmacia.rest.models.DetalleCompra;

public class DetalleCompraDao extends com.sistemaDeFarmacia.rest.controller.dao.implement.AdapterDao<DetalleCompra> {
    private static DetalleCompraDao instance;

    public DetalleCompraDao() {
        super(DetalleCompra.class);
    }

    public static DetalleCompraDao getInstance() {
        if (instance == null) {
            instance = new DetalleCompraDao();
        }
        return instance;
    }
}
