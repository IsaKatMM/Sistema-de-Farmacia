package com.sistemaDeFarmacia.rest.models;

public class CompraAProveedor {
    private Integer id;
    private Integer id_proveedor;
    private String producto;
    private Integer cantidad;
    private Float totalCompra;
    private String fechaCompra;

    public CompraAProveedor() {
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_proveedor() {
        return this.id_proveedor;
    }

    public void setId_proveedor(Integer id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getProducto() {
        return this.producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getTotalCompra() {
        return this.totalCompra;
    }

    public void setTotalCompra(Float totalCompra) {
        this.totalCompra = totalCompra;
    }

    public String getFechaCompra() {
        return this.fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }


    
    
}
