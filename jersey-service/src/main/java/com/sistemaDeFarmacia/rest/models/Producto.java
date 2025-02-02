package com.sistemaDeFarmacia.rest.models;

import com.sistemaDeFarmacia.rest.models.enumerador.TipoProducto;

public class Producto {
    private Integer idProducto; 
    private String nombreProducto;
    private String laboratio;
    private boolean requiereReceta;
    private String categoria;
    private int peso;
    private String marca;
    private TipoProducto tipoProducto; // Campo de tipo enum

    // Constructor
    public Producto() { 
    }

    // Getters and Setters

    public Integer getIdProducto() {
        return this.idProducto;
    }
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return this.nombreProducto;
    }
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getLaboratio() {
        return this.laboratio;
    }
    public void setLaboratio(String laboratio) {
        this.laboratio = laboratio;
    }

    public boolean getRequiereReceta() {
        return this.requiereReceta;
    }
    public void setRequiereReceta(boolean requiereReceta) {
        this.requiereReceta = requiereReceta;
    }

    public String getCategoria() {
        return this.categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getPeso() {
        return this.peso;
    }
    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getMarca() {
        return this.marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    // Getter para tipoProducto (devuelve el enum directamente)
    public TipoProducto getTipoProducto() {
        return this.tipoProducto;
    }

    // Setter para tipoProducto (acepta el enum directamente)
    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    // Método adicional para manejar String (opcional)
    public void setTipoProducto(String tipoProductoStr) {
        this.tipoProducto = TipoProducto.valueOf(tipoProductoStr.toUpperCase());
    }
}