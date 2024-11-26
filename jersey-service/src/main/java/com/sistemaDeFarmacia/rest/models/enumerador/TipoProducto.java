package com.sistemaDeFarmacia.rest.models.enumerador;

public enum TipoProducto {
    MEDICAMENTO, 
    SUPLEMENTO, 
    HIGIENE, 
    COSMETICO;

    // Este método devuelve el nombre de la constante
    public String getName() {
        return this.name();
    }
}
