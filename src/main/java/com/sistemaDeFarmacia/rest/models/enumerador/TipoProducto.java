package com.sistemaDeFarmacia.rest.models.enumerador;

public enum TipoProducto {
    MEDICAMENTO("MEDICAMENTO"), 
    SUPLEMENTO("SUPLEMENTO"), 
    HIGIENE("HIGIENE"), 
    COSMETICO("COSMETICO");

    private String name; // Cambiado a "private"

    private TipoProducto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
