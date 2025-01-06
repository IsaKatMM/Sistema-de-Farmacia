package com.sistemaDeFarmacia.rest.models;

import com.sistemaDeFarmacia.rest.models.enumerador.TipoProducto;

public class Proveedor extends Persona {
    private String nombreEmpresa;
    private TipoProducto tipoProductos;
    private String pedidos;
    private Boolean productosDisponibles;

    public Proveedor() {
    }

    public Proveedor(Integer id, String nombreEmpresa, TipoProducto tipoProductos, String pedidos, Boolean productosDisponibles) {
        super(id);
        this.nombreEmpresa = nombreEmpresa;
        this.tipoProductos = tipoProductos;
        this.pedidos = pedidos;
        this.productosDisponibles = productosDisponibles;
    }

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

    public Boolean isProductosDisponibles() {
        return this.productosDisponibles;
    }

    public Boolean getProductosDisponibles() {
        return this.productosDisponibles;
    }

    public void setProductosDisponibles(Boolean productosDisponibles) {
        this.productosDisponibles = productosDisponibles;
    }
}
