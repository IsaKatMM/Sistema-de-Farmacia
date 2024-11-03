package com.sistemaDeFarmacia.rest.models;

public class Producto {
    private int idProducto; 
    private String nombreProducto;
    private String laboratio;
    private boolean requiereReceta;
    private String categoria;
    private int peso;
    private String marca;
    private int stuck;
    private int stuckMinimo;

//constructor
    public Producto(){ 

    }

//getters and setters


    public int getIdProducto() {
        return this.idProducto;
    }
    public void setIdProducto(int idProducto) {
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

    public int getStuck() {
        return this.stuck;
    }
    public void setStuck(int stuck) {
        this.stuck = stuck;
    }

    public int getStuckMinimo() {
        return this.stuckMinimo;
    }
    public void setStuckMinimo(int stuckMinimo) {
        this.stuckMinimo = stuckMinimo;
    }

}
