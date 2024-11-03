package com.sistemaFarmacia.rest.models;

import java.util.Date;

public class FacturaCompra {
    private int id_factura;
    private int id_proveedor;
    private Date fechaEmision;
    private int cantidad;
    private String metodo_pago;
    private float subTotal;
    private float iva;
    private float total_USD;

    public FacturaCompra() {
    }

    public FacturaCompra(int id_factura, int id_proveedor, Date fechaEmision, int cantidad, String metodo_pago) {
        this.id_factura = id_factura;
        this.id_proveedor = id_proveedor;
        this.fechaEmision = fechaEmision;
        this.cantidad = cantidad;
        this.metodo_pago = metodo_pago;
    }

    public void calcularTotales(float subTotal) {
        this.subTotal = subTotal;
        this.iva = subTotal * 0.12f; // IVA del 12%
        this.total_USD = this.subTotal + this.iva;
    }

    // Getters y Setters
    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public float getIva() {
        return iva;
    }

    public float getTotal_USD() {
        return total_USD;
    }
}
