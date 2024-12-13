package com.sistemaDeFarmacia.rest.models;

public class DetalleFactura {
    private Integer id;
    private int cantidad;
    private float precioVenta;
    private float tarifa_IVA;
    private float totalVenta;
    private Integer id_factura;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getId_factura() {
        return this.id_factura;
    }

    public void setId_factura(Integer id_factura) {
        this.id_factura = id_factura;
    }
}
