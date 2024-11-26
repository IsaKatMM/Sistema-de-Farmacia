package com.sistemaDeFarmacia.rest.models;

public class DetalleCompra {
    private String idDetalleCompra;
    private int cantidad;
    private float valor_unitarioProducto;
    private float valor_totalProducto;
    private float tarifa_IVA;
    private float subtotal;
    private float totalCompra;

    public DetalleCompra() {
    }

    public DetalleCompra(String idDetalleCompra, int cantidad, float valor_unitarioProducto) {
        this.idDetalleCompra = idDetalleCompra;
        this.cantidad = cantidad;
        this.valor_unitarioProducto = valor_unitarioProducto;
        calcularValores();
    }

    private void calcularValores() {
        this.valor_totalProducto = this.cantidad * this.valor_unitarioProducto;
        this.tarifa_IVA = this.valor_totalProducto * 0.12f; // IVA del 12%
        this.subtotal = this.valor_totalProducto;
        this.totalCompra = this.subtotal + this.tarifa_IVA;
    }

    // Getters y Setters
    public String getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public void setIdDetalleCompra(String idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        calcularValores();
    }

    public float getValor_unitarioProducto() {
        return valor_unitarioProducto;
    }

    public void setValor_unitarioProducto(float valor_unitarioProducto) {
        this.valor_unitarioProducto = valor_unitarioProducto;
        calcularValores();
    }

    public float getValor_totalProducto() {
        return valor_totalProducto;
    }

    public float getTarifa_IVA() {
        return tarifa_IVA;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public float getTotalCompra() {
        return totalCompra;
    }
}
