package com.sistemaFarmacia.rest.controller.dao.implement;

import com.sistemaFarmacia.rest.models.DetalleCompra;

public class DetalleCompraDao extends AdapterDao<DetalleCompra> {
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
