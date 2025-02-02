package com.sistemaDeFarmacia.rest.models;
import java.util.Date;

public class Lote {
    private Integer idLote;
    private Integer cantidad;
    private Date fechaEntrega;
    private Float precioLote;
    private Date fechaCaducidad;
    private Float precioVenta;
    private Float precioCompra;
    private String codigoLote;
    private Producto producto;


    public Lote() {}

    public Integer getIdLote() {
        return idLote;
    }

    public void setId(Integer idLote) {
        this.idLote = idLote;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Float getPrecioLote() {
        return precioLote;
    }

    public void setPrecioLote(Float precioLote) {
        this.precioLote = precioLote;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public String getCodigoLote() {
        return codigoLote;
    }

    public void setCodigoLote(String codigoLote) {
        this.codigoLote = codigoLote;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}