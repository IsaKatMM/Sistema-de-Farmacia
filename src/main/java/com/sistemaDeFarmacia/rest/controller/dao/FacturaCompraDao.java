package com.sistemaDeFarmacia.rest.controller.dao.implement;

import com.sistemaDeFarmacia.rest.models.FacturaCompra;

public class FacturaCompraDao extends com.sistemaDeFarmacia.rest.controller.dao.implement.AdapterDao<FacturaCompra> {
    private static FacturaCompraDao instance;

    public FacturaCompraDao() {
        super(FacturaCompra.class);
    }

    public static FacturaCompraDao getInstance() {
        if (instance == null) {
            instance = new FacturaCompraDao();
        }
        return instance;
    }
}
