package com.sistemaDeFarmacia.rest.models;

import com.sistemaDeFarmacia.rest.models.enumerador.TipoProducto;

public class Proveedor extends Persona {
    private String nombreEmpresa;
    private TipoProducto tipoProductos;
    private String pedidos;
    private Integer productosDisponibles;


    public String getNombreEmpresa() {
        return this.nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public TipoProducto getTipoProductos() {
        return this.tipoProductos;
    }

    public void setTipoProductos(TipoProducto tipoProductos) {
        this.tipoProductos = tipoProductos;
    }

    public String getPedidos() {
        return this.pedidos;
    }

    public void setPedidos(String pedidos) {
        this.pedidos = pedidos;
    }

    public Integer getProductosDisponibles() {
        return this.productosDisponibles;
    }

    public void setProductosDisponibles(Integer productosDisponibles) {
        this.productosDisponibles = productosDisponibles;
    }
    public Proveedor() {
    }

}