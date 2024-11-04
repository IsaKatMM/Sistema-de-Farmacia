package com.sistemaDeFarmacia.rest.models.enumerador;

public enum TipoProducto {
    MEDICAMENTO("MEDICAMENTO"), SUPLEMENTO("SUPLEMENTO"), HIGIENE("HIGIENE"), COSMETICO("COSMETICO");
    Private String name;

    private TipoProducto(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
