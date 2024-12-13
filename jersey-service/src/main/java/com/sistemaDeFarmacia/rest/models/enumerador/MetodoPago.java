package com.sistemaDeFarmacia.rest.models.enumerador;

public enum MetodoPago {
    EFECTIVO("EFECTIVO"),
    //TARJETA_CREDITO("TARJETA_CREDITO"),
    //TARJETA_DEBITO("TARJETA_DEBITO"),
    TRANSFERENCIA("TRANSFERENCIA");

    private String name;

    MetodoPago(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}