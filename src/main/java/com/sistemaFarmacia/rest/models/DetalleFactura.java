package com.sistemaFarmacia.rest.models;

public class DetalleFactura {
    private String id_detalleVenta;
    private int cantidad;
    private float precioVenta;
    private float tarifa_IVA;
    private float totalVenta;

    public DetalleFactura(String id_detalleVenta, int cantidad, float precioVenta, float tarifa_IVA, float totalVenta) {
        this.id_detalleVenta = id_detalleVenta;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.tarifa_IVA = tarifa_IVA;
        this.totalVenta = totalVenta;
    }

    public String getId_detalleVenta() {
        return this.id_detalleVenta;
    }

    public void setId_detalleVenta(String id_detalleVenta) {
        this.id_detalleVenta = id_detalleVenta;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioVenta() {
        return this.precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public float getTarifa_IVA() {
        return this.tarifa_IVA;
    }

    public void setTarifa_IVA(float tarifa_IVA) {
        this.tarifa_IVA = tarifa_IVA;
    }

    public float getTotalVenta() {
        return this.totalVenta;
    }

    public void setTotalVenta(float totalVenta) {
        this.totalVenta = totalVenta;
    }

}
